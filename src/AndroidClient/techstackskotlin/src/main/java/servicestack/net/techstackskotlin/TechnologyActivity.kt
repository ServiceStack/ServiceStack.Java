package servicestack.net.techstackskotlin

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.squareup.picasso.Picasso
import net.servicestack.client.Utils

class TechnologyActivity : Activity(), App.AppDataListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technology)
        val extras = intent.extras

        App.data.addListener(this).loadTechnology(extras.getString("slug"))

        setLoadingTextViews(
                R.id.lblTechnologyName,
                R.id.lblTechnologyVendor,
                R.id.lblTechnologyVendorUrl,
                R.id.lblTechnologyDescription)

        val img = findViewById(R.id.imgTechnologyLogo) as ImageView
        img.setImageBitmap(null)

        val list = findViewById(R.id.listTechnologyTechStacks) as ListView
        list.adapter = null

        val txtUrl = findViewById(R.id.lblTechnologyVendorUrl) as TextView
        val activity = this
        txtUrl.setOnClickListener(View.OnClickListener {
            val result = App.data.technology ?: return@OnClickListener
            App.openUrl(activity, result.Technology!!.VendorUrl)
        })
    }

    internal fun setLoadingTextViews(vararg viewIds: Int) {
        for (viewId in viewIds) {
            val txtView = findViewById(viewId) as TextView
            txtView.text = "Loading..."
        }
    }

    override fun onUpdate(data: App.AppData, dataType: App.DataType) {
        when (dataType) {
            App.DataType.Technology -> {
                val result = data.technology!!
                val lblName = findViewById(R.id.lblTechnologyName) as TextView
                lblName.text = result.Technology!!.Name

                val lblVendor = findViewById(R.id.lblTechnologyVendor) as TextView
                lblVendor.text = result.Technology!!.VendorName

                val lblVendorUrl = findViewById(R.id.lblTechnologyVendorUrl) as TextView
                lblVendorUrl.text = Utils.toHumanFriendlyUrl(result.Technology!!.VendorUrl)

                val lblDescription = findViewById(R.id.lblTechnologyDescription) as TextView
                lblDescription.text = result.Technology!!.Description

                val imgUrl = result.Technology!!.LogoUrl
                if (imgUrl != null) {
                    val img = findViewById(R.id.imgTechnologyLogo) as ImageView
                    Picasso.with(applicationContext).load(imgUrl).into(img)
                }

                val list = findViewById(R.id.listTechnologyTechStacks) as ListView
                val stackNames = result.TechnologyStacks.map { it.Name }

                val activity = this
                list.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stackNames)
                list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    App.openTechStack(activity, result.TechnologyStacks[position].Slug)
                }
            }
        }
    }

}
