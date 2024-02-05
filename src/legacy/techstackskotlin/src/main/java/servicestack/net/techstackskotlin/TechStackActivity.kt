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

        val img = findViewById<ImageView>(R.id.imgTechStackScreenshotUrl)
        img.setImageBitmap(null)

        val txtUrl = findViewById<TextView>(R.id.lblTechStackAppUrl)
        txtUrl.setOnClickListener(View.OnClickListener {
            val result = App.data.techStack ?: return@OnClickListener
            val url = result.result!!.appUrl ?: return@OnClickListener

            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
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
            App.DataType.TechStack -> {
                val result = data.techStack!!.result!!

                val lblName = findViewById<TextView>(R.id.lblTechStackName)
                lblName.text = result.name

                val lblDescription = findViewById<TextView>(R.id.lblTechStackDescription)
                lblDescription.text = result.description

                val lblUrl = findViewById<TextView>(R.id.lblTechStackAppUrl)
                lblUrl.text = Utils.toHumanFriendlyUrl(result.appUrl)

                val imgUrl = result.screenshotUrl
                if (imgUrl != null) {
                    val img = findViewById<ImageView>(R.id.imgTechStackScreenshotUrl)
                    Picasso.with(applicationContext).load(imgUrl).into(img)
                }

                renderCategories(result)
            }
        }
    }

    private fun renderCategories(result: TechStackDetails) {

        val layout = findViewById<LinearLayout>(R.id.layoutTechStackCategories)
        layout.removeAllViews()
        for (o in App.data.appOverviewResponse!!.allTiers) {
            val results = result.technologyChoices.filter { it.tier === o.value }

            if (results.size == 0)
                continue

            val lblCategory = TextView(this)
            lblCategory.setText(o.title)
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
                img.setOnClickListener { App.openTechnology(activity, x.slug) }
                Picasso.with(applicationContext).load(x.logoUrl).into(img)

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
