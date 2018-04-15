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

        val img = findViewById<ImageView>(R.id.imgTechnologyLogo)
        img.setImageBitmap(null)

        val list = findViewById<ListView>(R.id.listTechnologyTechStacks)
        list.adapter = null

        val txtUrl = findViewById<TextView>(R.id.lblTechnologyVendorUrl)
        val activity = this
        txtUrl.setOnClickListener(View.OnClickListener {
            val result = App.data.technology ?: return@OnClickListener
            App.openUrl(activity, result.technology!!.vendorUrl)
        })
    }

    internal fun setLoadingTextViews(vararg viewIds: Int) {
        for (viewId in viewIds) {
            val txtView = findViewById<TextView>(viewId)
            txtView.text = "Loading..."
        }
    }

    override fun onUpdate(data: App.AppData, dataType: App.DataType) {
        when (dataType) {
            App.DataType.Technology -> {
                val result = data.technology!!
                val lblName = findViewById<TextView>(R.id.lblTechnologyName)
                lblName.text = result.technology!!.name

                val lblVendor = findViewById<TextView>(R.id.lblTechnologyVendor)
                lblVendor.text = result.technology!!.vendorName

                val lblVendorUrl = findViewById<TextView>(R.id.lblTechnologyVendorUrl)
                lblVendorUrl.text = Utils.toHumanFriendlyUrl(result.technology!!.vendorUrl)

                val lblDescription = findViewById<TextView>(R.id.lblTechnologyDescription)
                lblDescription.text = result.technology!!.description

                val imgUrl = result.technology!!.logoUrl
                if (imgUrl != null) {
                    val img = findViewById<ImageView>(R.id.imgTechnologyLogo)
                    Picasso.with(applicationContext).load(imgUrl).into(img)
                }

                val list = findViewById<ListView>(R.id.listTechnologyTechStacks)
                val stackNames = result.technologyStacks.map { it.name }

                val activity = this
                list.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stackNames)
                list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    App.openTechStack(activity, result.technologyStacks[position].slug)
                }
            }
        }
    }

}
