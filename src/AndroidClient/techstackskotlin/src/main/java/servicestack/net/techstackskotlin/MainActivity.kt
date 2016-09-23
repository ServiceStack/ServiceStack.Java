package servicestack.net.techstackskotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.*
import java.util.*

class MainActivity : ActionBarActivity(), ActionBar.TabListener {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * [FragmentPagerAdapter] derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    internal var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    /**
     * The [ViewPager] that will host the section contents.
     */
    internal var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the action bar.
        val actionBar = supportActionBar!!
        actionBar.navigationMode = ActionBar.NAVIGATION_MODE_TABS
        actionBar.displayOptions = ActionBar.DISPLAY_SHOW_HOME or ActionBar.DISPLAY_SHOW_TITLE
        actionBar.setBackgroundDrawable(resources.getDrawable(R.color.actionbar_background))
        actionBar.setStackedBackgroundDrawable(resources.getDrawable(R.color.tab_background))
        actionBar.setIcon(R.drawable.ic_actionbar)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.pager) as ViewPager
        mViewPager?.adapter = mSectionsPagerAdapter

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager?.setOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                actionBar.setSelectedNavigationItem(position)
            }
        })

        // For each of the sections in the app, add a tab to the action bar.
        for (i in 0..mSectionsPagerAdapter!!.count - 1) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab().setText(mSectionsPagerAdapter?.getPageTitle(i)).setTabListener(this))
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onTabSelected(tab: ActionBar.Tab, fragmentTransaction: FragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager?.currentItem = tab.position
    }

    override fun onTabUnselected(tab: ActionBar.Tab, fragmentTransaction: FragmentTransaction) {
    }

    override fun onTabReselected(tab: ActionBar.Tab, fragmentTransaction: FragmentTransaction) {
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence {
            when (position) {
                0 -> return "Top Rated"
                1 -> return "TechStacks"
                2 -> return "Technologies"
            }
            throw RuntimeException("Invalid position: " + position)
        }

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return TopRatedFragment.create(position + 1)
                1 -> return TechStacksFragment.create(position + 1)
                2 -> return TechnologiesFragment.create(position + 1)
            }
            throw RuntimeException("Invalid position: " + position)
        }
    }

    class TopRatedFragment : Fragment(), App.AppDataListener {
        internal var selectedCategory: Option? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            App.data.addListener(this)
        }

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            App.data.loadAppOverview()

            val rootView = inflater!!.inflate(R.layout.fragment_top_rated, container, false)

            val spinner = rootView.findViewById(R.id.spinnerCategory) as Spinner
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    selectedCategory = App.data.appOverviewResponse?.AllTiers!![position]
                    refreshTopTechnologies(App.data, rootView.findViewById(R.id.listTopRated) as ListView)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    selectedCategory = null
                }
            }
            val list = rootView.findViewById(R.id.listTopRated) as ListView
            list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val result = getTopTechnologies(App.data)[position]
                App.openTechnology(activity, result.Slug)
            }

            return rootView
        }

        internal val categorySpinner: Spinner?
            get() {
                if (activity == null)
                    return null
                return activity.findViewById(R.id.spinnerCategory) as Spinner?
            }

        internal val topRatedListView: ListView?
            get() {
                if (activity == null)
                    return null
                return activity.findViewById(R.id.listTopRated) as ListView?
            }

        override fun onUpdate(data: App.AppData, dataType: App.DataType) {
            when (dataType) {
                App.DataType.AppOverview -> {
                    val spinner = categorySpinner
                    if (spinner != null) {
                        val categories = data.appOverviewResponse!!.AllTiers.map { it.Title }
                        spinner.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, categories)
                    }

                    val list = topRatedListView
                    if (list != null) {
                        refreshTopTechnologies(data, list)
                    }
                }
            }
        }

        private fun refreshTopTechnologies(data: App.AppData, list: ListView) {
            val topTechnologyNames = getTopTechnologies(data).map { it.Name + " (" + it.StacksCount + ")" }
            list.adapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, topTechnologyNames)
        }

        private fun getTopTechnologies(data: App.AppData): ArrayList<TechnologyInfo> {
            var topTechnologies = data.appOverviewResponse!!.TopTechnologies
            if (selectedCategory != null && selectedCategory!!.Value != null) {
                topTechnologies = ArrayList(topTechnologies.filter { it.Tier == selectedCategory?.Value })
            }
            return topTechnologies
        }

        companion object {

            fun create(sectionNumber: Int): TopRatedFragment {
                return TopRatedFragment()
            }
        }
    }

    class TechStacksFragment : Fragment(), App.AppDataListener {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            App.data.addListener(this).searchTechStacks("")
        }

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater!!.inflate(R.layout.fragment_tech_stacks, container, false)

            val txtSearch = rootView.findViewById(R.id.searchTechStacks) as EditText
            txtSearch.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    App.data.searchTechStacks(s.toString())
                }

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                }

                override fun afterTextChanged(s: Editable) {
                }
            })

            val list = rootView.findViewById(R.id.listTechStacks) as ListView
            list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val result = App.data.searchTechStacksResponse!!.Results[position]
                App.openTechStack(activity, result.Slug)
            }

            return rootView
        }

        internal val listViewResults: ListView?
            get() {
                if (activity == null)
                    return null
                return activity.findViewById(R.id.listTechStacks) as ListView
            }

        override fun onUpdate(data: App.AppData, dataType: App.DataType) {
            when (dataType) {
                App.DataType.SearchTechStacks -> {
                    val list = listViewResults
                    if (list != null) {
                        val results = data.searchTechStacksResponse!!.Results.map { it.Name }
                        list.adapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, results)
                    }
                }
            }
        }

        companion object {
            fun create(sectionNumber: Int): TechStacksFragment {
                return TechStacksFragment()
            }
        }
    }

    class TechnologiesFragment : Fragment(), App.AppDataListener {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            App.data.addListener(this).searchTechnologies("")
        }

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater!!.inflate(R.layout.fragment_technologies, container, false)

            val txtSearch = rootView.findViewById(R.id.searchTechnologies) as EditText
            txtSearch.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    App.data.searchTechnologies(s.toString())
                }

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                }

                override fun afterTextChanged(s: Editable) {
                }
            })

            val list = rootView.findViewById(R.id.listTechnologies) as ListView
            list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val result = App.data.searchTechnologiesResponse!!.Results[position]
                App.openTechnology(activity, result.Slug)
            }
            return rootView
        }

        internal val listViewResults: ListView?
            get() {
                if (activity == null)
                    return null
                return activity.findViewById(R.id.listTechnologies) as ListView
            }

        override fun onUpdate(data: App.AppData, dataType: App.DataType) {
            when (dataType) {
                App.DataType.SearchTechnologies -> {
                    val list = listViewResults
                    if (list != null) {
                        val results = data.searchTechnologiesResponse!!.Results.map { it.Name }
                        list.adapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, results)
                    }
                }
            }
        }

        companion object {
            fun create(sectionNumber: Int): TechnologiesFragment {
                return TechnologiesFragment()
            }
        }
    }
}
