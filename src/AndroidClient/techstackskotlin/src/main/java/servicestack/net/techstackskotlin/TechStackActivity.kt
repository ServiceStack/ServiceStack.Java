package servicestack.net.techstackskotlin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import net.servicestack.client.Utils
import java.util.*

class TechStackActivity : Activity(), App.AppDataListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_techstack)
        val extras = intent.extras

        App.data.addListener(this).loadTechStack(extras.getString("slug"))

        setLoadingTextViews(
                R.id.lblTechStackName,
                R.id.lblTechStackDescription,
                R.id.lblTechStackAppUrl)

        val img = findViewById(R.id.imgTechStackScreenshotUrl) as ImageView
        img.setImageBitmap(null)

        val txtUrl = findViewById(R.id.lblTechStackAppUrl) as TextView
        txtUrl.setOnClickListener(View.OnClickListener {
            val result = App.data.techStack ?: return@OnClickListener
            val url = result.Result!!.AppUrl ?: return@OnClickListener

            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
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
            App.DataType.TechStack -> {
                val result = data.techStack!!.Result!!

                val lblName = findViewById(R.id.lblTechStackName) as TextView
                lblName.text = result.Name

                val lblDescription = findViewById(R.id.lblTechStackDescription) as TextView
                lblDescription.text = result.Description

                val lblUrl = findViewById(R.id.lblTechStackAppUrl) as TextView
                lblUrl.text = Utils.toHumanFriendlyUrl(result.AppUrl)

                val imgUrl = result.ScreenshotUrl
                if (imgUrl != null) {
                    val img = findViewById(R.id.imgTechStackScreenshotUrl) as ImageView
                    Picasso.with(applicationContext).load(imgUrl).into(img)
                }

                renderCategories(result)
            }
        }
    }

    private fun renderCategories(result: TechStackDetails) {

        val layout = findViewById(R.id.layoutTechStackCategories) as LinearLayout
        layout.removeAllViews()
        for (o in App.data.appOverviewResponse!!.AllTiers) {
            val results = result.TechnologyChoices.filter { it.Tier === o.Value }

            if (results.size == 0)
                continue

            val lblCategory = TextView(this)
            lblCategory.setText(o.Title)
            lblCategory.setPadding(0, 20, 0, 0)
            layout.addView(lblCategory,
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))

            var layoutCategories: LinearLayout? = null

            val activity = this
            for (i in results.indices) {
                val x = results[i]

                val img = ImageView(this)
                img.maxHeight = 120
                img.adjustViewBounds = true
                img.setPadding(30, 10, 0, 30)
                img.setOnClickListener { App.openTechnology(activity, x.Slug) }
                Picasso.with(applicationContext).load(x.LogoUrl).into(img)

                if (i % 3 == 0) {
                    layoutCategories = LinearLayout(this)
                    layoutCategories.orientation = LinearLayout.HORIZONTAL
                    layout.addView(layoutCategories,
                            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))
                }

                layoutCategories!!.addView(img,
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT))
            }


        }
    }

}
