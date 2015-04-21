package servicestack.net.techstacks;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.android.internal.util.Predicate;

import java.util.ArrayList;

import servicestack.net.techstacks.dto.*;
import servicestack.net.techstacks.dto.Technology;
import servicestack.net.techstacks.dto.TechnologyInfo;
import servicestack.net.techstacks.dto.TechnologyStack;

import static net.servicestack.client.Func.Function;
import static net.servicestack.client.Func.filter;
import static net.servicestack.client.Func.map;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.actionbar_background));
        actionBar.setStackedBackgroundDrawable(getResources().getDrawable(R.color.tab_background));
        actionBar.setIcon(R.drawable.ic_actionbar);

            // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Top Rated";
                case 1:
                    return "TechStacks";
                case 2:
                    return "Technologies";
            }
            throw new RuntimeException("Invalid position: " + position);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TopRatedFragment.create(position + 1);
                case 1:
                    return TechStacksFragment.create(position + 1);
                case 2:
                    return TechnologiesFragment.create(position + 1);
            }
            throw new RuntimeException("Invalid position: " + position);
        }
    }

    public static class TopRatedFragment extends Fragment implements App.AppDataListener {
        Option selectedCategory;

        public static TopRatedFragment create(int sectionNumber) {
            return new TopRatedFragment();
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            App.getData().addListener(this);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            App.getData().loadAppOverview();

            final View rootView = inflater.inflate(R.layout.fragment_top_rated, container, false);

            Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerCategory);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedCategory = App.getData().getAppOverviewResponse().getAllTiers().get(position);
                    refreshTopTechnologies(App.getData(), (ListView) rootView.findViewById(R.id.listTopRated));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    selectedCategory = null;
                }
            });
            ListView list = (ListView) rootView.findViewById(R.id.listTopRated);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TechnologyInfo result = getTopTechnologies(App.getData()).get(position);
                    App.openTechnology(getActivity(), result.getSlug());
                }
            });

            return rootView;
        }

        Spinner getCategorySpinner() {
            if (getActivity() == null)
                return null;
            return (Spinner) getActivity().findViewById(R.id.spinnerCategory);
        }

        ListView getTopRatedListView() {
            if (getActivity() == null)
                return null;
            return (ListView) getActivity().findViewById(R.id.listTopRated);
        }

        @Override
        public void onUpdate(App.AppData data, App.DataType dataType) {
            switch (dataType) {
                case AppOverview:
                    Spinner spinner = getCategorySpinner();
                    if (spinner != null) {
                        ArrayList<String> categories = map(data.getAppOverviewResponse().getAllTiers(), new Function<Option, String>() {
                            @Override
                            public String apply(Option option) {
                                return option.getTitle();
                            }
                        });
                        spinner.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories));
                    }

                    ListView list = getTopRatedListView();
                    if (list != null) {
                        refreshTopTechnologies(data, list);
                    }
                    break;
            }
        }

        private void refreshTopTechnologies(App.AppData data, ListView list) {
            ArrayList<String> topTechnologyNames = map(getTopTechnologies(data), new Function<TechnologyInfo, String>() {
                @Override
                public String apply(TechnologyInfo technologyInfo) {
                    return technologyInfo.getName() + " (" + technologyInfo.getStacksCount() + ")";
                }
            });
            list.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, topTechnologyNames));
        }

        private ArrayList<TechnologyInfo> getTopTechnologies(App.AppData data) {
            ArrayList<TechnologyInfo> topTechnologies = data.getAppOverviewResponse().getTopTechnologies();
            if (selectedCategory != null && selectedCategory.getValue() != null) {
                topTechnologies = filter(topTechnologies, new Predicate<TechnologyInfo>() {
                    @Override
                    public boolean apply(TechnologyInfo tech) {
                        return tech.getTier() == selectedCategory.getValue();
                    }
                });
            }
            return topTechnologies;
        }
    }

    public static class TechStacksFragment extends Fragment implements App.AppDataListener {
        public static TechStacksFragment create(int sectionNumber) {
            return new TechStacksFragment();
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            App.getData()
                .addListener(this)
                .searchTechStacks("");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tech_stacks, container, false);

            EditText txtSearch = (EditText) rootView.findViewById(R.id.searchTechStacks);
            txtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    App.getData().searchTechStacks(s.toString());
                }

                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void afterTextChanged(Editable s) {}
            });

            ListView list = (ListView) rootView.findViewById(R.id.listTechStacks);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TechnologyStack result = App.getData().getSearchTechStacksResponse().getResults().get(position);
                    App.openTechStack(getActivity(), result.getSlug());
                }
            });

            return rootView;
        }

        ListView getListViewResults() {
            if (getActivity() == null)
                return null;
            return (ListView) getActivity().findViewById(R.id.listTechStacks);
        }

        @Override
        public void onUpdate(App.AppData data, App.DataType dataType) {
            switch (dataType) {
                case SearchTechStacks:
                    ListView list = getListViewResults();
                    if (list != null) {
                        ArrayList<String> results = map(data.getSearchTechStacksResponse().getResults(), new Function<TechnologyStack, String>() {
                            @Override
                            public String apply(TechnologyStack o) {
                                return o.getName();
                            }
                        });
                        list.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, results));
                    }
                    break;
            }
        }
    }

    public static class TechnologiesFragment extends Fragment implements App.AppDataListener {
        public static TechnologiesFragment create(int sectionNumber) {
            return new TechnologiesFragment();
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            App.getData()
                .addListener(this)
                .searchTechnologies("");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_technologies, container, false);

            EditText txtSearch = (EditText) rootView.findViewById(R.id.searchTechnologies);
            txtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    App.getData().searchTechnologies(s.toString());
                }

                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void afterTextChanged(Editable s) {}
            });

            ListView list = (ListView) rootView.findViewById(R.id.listTechnologies);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Technology result = App.getData().getSearchTechnologiesResponse().getResults().get(position);
                    App.openTechnology(getActivity(), result.getSlug());
                }
            });

            return rootView;
        }

        ListView getListViewResults() {
            if (getActivity() == null)
                return null;
            return (ListView) getActivity().findViewById(R.id.listTechnologies);
        }

        @Override
        public void onUpdate(App.AppData data, App.DataType dataType) {
            switch (dataType) {
                case SearchTechnologies:
                    ListView list = getListViewResults();
                    if (list != null) {
                        ArrayList<String> results = map(data.getSearchTechnologiesResponse().getResults(), new Function<Technology, String>() {
                            @Override
                            public String apply(Technology o) {
                                return o.getName();
                            }
                        });
                        list.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, results));
                    }
                    break;
            }
        }
    }
}
