package servicestack.net.techstacks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
        App.getData().addListener(this);

        Bundle extras = getIntent().getExtras();
        App.getData().loadTechnology(extras.getString("slug"));

        setLoadingTextViews(
            R.id.lblTechnologyName,
            R.id.lblTechnologyVendor,
            R.id.lblTechnologyVendorUrl,
            R.id.lblTechnologyDescription);

        ImageView imgLogo = (ImageView) findViewById(R.id.imgTechnologyLogo);
        imgLogo.setImageBitmap(null);

        TextView url = (TextView)findViewById(R.id.lblTechnologyVendorUrl);
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                GetTechnologyResponse result = App.getData().getTechnology();
                if (result == null) return;
                String url = result.getTechnology().getVendorUrl();
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

    public Activity getActivity(){
        return this;
    }

    @Override
    public void onUpdate(App.AppData data, App.DataType dataType) {
        switch (dataType){
            case Technology:

                GetTechnologyResponse result = data.getTechnology();
                TextView lblName = (TextView) getActivity().findViewById(R.id.lblTechnologyName);
                if (lblName == null) return;
                lblName.setText(result.getTechnology().getName());

                TextView lblVendor = (TextView) getActivity().findViewById(R.id.lblTechnologyVendor);
                if (lblVendor == null) return;
                lblVendor.setText(result.getTechnology().getVendorName());

                TextView lblVendorUrl = (TextView) getActivity().findViewById(R.id.lblTechnologyVendorUrl);
                if (lblVendorUrl == null) return;
                lblVendorUrl.setText(Utils.toHumanFriendlyUrl(result.getTechnology().getVendorUrl()));

                TextView lblDescription = (TextView) getActivity().findViewById(R.id.lblTechnologyDescription);
                if (lblDescription == null) return;
                lblDescription.setText(result.getTechnology().getDescription());

                String logoUrl = result.getTechnology().getLogoUrl();
                if (logoUrl != null){
                    final ImageView imgLogo = (ImageView) getActivity().findViewById(R.id.imgTechnologyLogo);
                    if (imgLogo == null) return;

                    data.loadImage(logoUrl, new App.ImageResult() {
                        @Override
                        public void success(Bitmap img) {
                            imgLogo.setImageBitmap(img);
                        }
                    });
                }

                ListView list = (ListView)getActivity().findViewById(R.id.listTechnologyTechStacks);
                ArrayList<String> stackNames = map(result.getTechnologyStacks(), new Function<TechnologyStack,String>(){
                    @Override
                    public String apply(TechnologyStack x) {
                        return x.getName();
                    }
                });
                list.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stackNames));

                break;
        }
    }

}
