package servicestack.net.techstacks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import net.servicestack.client.Utils;

import java.util.ArrayList;

import servicestack.net.techstacks.dto.GetTechnologyResponse;
import servicestack.net.techstacks.dto.TechnologyStack;

import static net.servicestack.client.Func.*;

public class TechnologyActivity extends Activity implements App.AppDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);
        Bundle extras = getIntent().getExtras();

        App.getData()
                .addListener(this)
                .loadTechnology(extras.getString("slug"));

        setLoadingTextViews(
            R.id.lblTechnologyName,
            R.id.lblTechnologyVendor,
            R.id.lblTechnologyVendorUrl,
            R.id.lblTechnologyDescription);

        ImageView img = (ImageView) findViewById(R.id.imgTechnologyLogo);
        img.setImageBitmap(null);

        ListView list = (ListView) findViewById(R.id.listTechnologyTechStacks);
        list.setAdapter(null);

        TextView txtUrl = (TextView)findViewById(R.id.lblTechnologyVendorUrl);
        final Activity activity = this;
        txtUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTechnologyResponse result = App.getData().getTechnology();
                if (result == null) return;
                App.openUrl(activity, result.getTechnology().getVendorUrl());
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
            case Technology:
                final GetTechnologyResponse result = data.getTechnology();
                TextView lblName = (TextView) findViewById(R.id.lblTechnologyName);
                lblName.setText(result.getTechnology().getName());

                TextView lblVendor = (TextView) findViewById(R.id.lblTechnologyVendor);
                lblVendor.setText(result.getTechnology().getVendorName());

                TextView lblVendorUrl = (TextView) findViewById(R.id.lblTechnologyVendorUrl);
                lblVendorUrl.setText(Utils.toHumanFriendlyUrl(result.getTechnology().getVendorUrl()));

                TextView lblDescription = (TextView) findViewById(R.id.lblTechnologyDescription);
                lblDescription.setText(result.getTechnology().getDescription());

                String imgUrl = result.getTechnology().getLogoUrl();
                if (imgUrl != null){
                    final ImageView img = (ImageView) findViewById(R.id.imgTechnologyLogo);
                    data.loadImage(imgUrl, new App.ImageResult() {
                        @Override
                        public void success(Bitmap response) {
                            img.setImageBitmap(response);
                        }
                    });
                }

                ListView list = (ListView) findViewById(R.id.listTechnologyTechStacks);
                ArrayList<String> stackNames = map(result.getTechnologyStacks(), new Function<TechnologyStack,String>(){
                    @Override
                    public String apply(TechnologyStack x) {
                        return x.getName();
                    }
                });

                final Activity activity = this;
                list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stackNames));
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        App.openTechStack(activity, result.getTechnologyStacks().get(position).getSlug());
                    }
                });

                break;
        }
    }

}
