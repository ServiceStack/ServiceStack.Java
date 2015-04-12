package servicestack.net.techstacks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.internal.util.Predicate;

import net.servicestack.client.Utils;

import java.util.ArrayList;

import servicestack.net.techstacks.dto.GetTechnologyStackResponse;
import servicestack.net.techstacks.dto.Option;
import servicestack.net.techstacks.dto.TechStackDetails;
import servicestack.net.techstacks.dto.TechnologyInStack;

import static net.servicestack.client.Func.*;

public class TechStackActivity extends Activity implements App.AppDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techstack);
        Bundle extras = getIntent().getExtras();

        App.getData()
            .addListener(this)
            .loadTechStack(extras.getString("slug"));

        setLoadingTextViews(
            R.id.lblTechStackName,
            R.id.lblTechStackDescription,
            R.id.lblTechStackAppUrl);

        ImageView img = (ImageView) findViewById(R.id.imgTechStackScreenshotUrl);
        img.setImageBitmap(null);

        TextView txtUrl = (TextView)findViewById(R.id.lblTechStackAppUrl);
        txtUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTechnologyStackResponse result = App.getData().getTechStack();
                if (result == null) return;
                String url = result.getResult().getAppUrl();
                if (url == null) return;

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
    }

    void setLoadingTextViews(int... viewIds){
        for (int viewId : viewIds){
            TextView txtView = (TextView)findViewById(viewId);
            txtView.setText("Loading...");
        }
    }

    @Override
    public void onUpdate(App.AppData data, App.DataType dataType) {
        switch (dataType){
            case TechStack:
                final TechStackDetails result = data.getTechStack().getResult();

                TextView lblName = (TextView) findViewById(R.id.lblTechStackName);
                lblName.setText(result.getName());

                TextView lblDescription = (TextView) findViewById(R.id.lblTechStackDescription);
                lblDescription.setText(result.getDescription());

                TextView lblUrl = (TextView) findViewById(R.id.lblTechStackAppUrl);
                lblUrl.setText(Utils.toHumanFriendlyUrl(result.getAppUrl()));

                String imgUrl = result.getScreenshotUrl();
                if (imgUrl != null){
                    final ImageView img = (ImageView) findViewById(R.id.imgTechStackScreenshotUrl);
                    data.loadImage(imgUrl, new App.ImageResult() {
                        @Override
                        public void success(Bitmap response) {
                            img.setImageBitmap(response);
                        }
                    });
                }

                renderCategories(result);
                break;
        }
    }

    private void renderCategories(TechStackDetails result) {

        LinearLayout layout = (LinearLayout)findViewById(R.id.layoutTechStackCategories);
        layout.removeAllViews();
        for (final Option o : App.getData().getAppOverviewResponse().getAllTiers()){
            ArrayList<TechnologyInStack> results = filter(result.getTechnologyChoices(), new Predicate<TechnologyInStack>() {
                @Override public boolean apply(TechnologyInStack x) {
                    return x.getTier() == o.getValue();
                }
            });

            if (results.size() == 0)
                continue;

            TextView lblCategory = new TextView(this);
            lblCategory.setText(o.getTitle());
            lblCategory.setPadding(0, 20, 0, 0);
            layout.addView(lblCategory,
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            LinearLayout layoutCategories = null;

            final Activity activity = this;
            for (int i = 0; i < results.size(); i++) {
                final TechnologyInStack x = results.get(i);

                final ImageView img = new ImageView(this);
                img.setMaxHeight(120);
                img.setAdjustViewBounds(true);
                img.setPadding(30, 10, 0, 30);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        App.openTechnology(activity, x.getSlug());
                    }
                });
                App.getData().loadImage(x.getLogoUrl(), new App.ImageResult() {
                    @Override
                    public void success(Bitmap response) {
                        img.setImageBitmap(response);
                    }
                });

                if (i % 3 == 0){
                    layoutCategories = new LinearLayout(this);
                    layoutCategories.setOrientation(LinearLayout.HORIZONTAL);
                    layout.addView(layoutCategories,
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                }

                layoutCategories.addView(img,
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            }


        }
    }

}
