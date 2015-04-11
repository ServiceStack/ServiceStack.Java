package servicestack.net.techstacks;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TechnologyActivity extends Activity implements App.AppDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);
        App.getData().addListener(this);

        Bundle extras = getIntent().getExtras();
        App.getData().loadTechnology(extras.getString("slug"));
    }

    public Activity getActivity(){
        return this;
    }

    @Override
    public void onUpdate(App.AppData data, App.DataType dataType) {
        switch (dataType){
            case Technology:

                dto.GetTechnologyResponse result = data.getTechnology();
                TextView lblName = (TextView) getActivity().findViewById(R.id.lblTechnologyName);
                if (lblName == null) return;
                lblName.setText(result.getTechnology().getName());

                TextView lblVendor = (TextView) getActivity().findViewById(R.id.lblTechnologyVendor);
                if (lblVendor == null) return;
                lblVendor.setText(result.getTechnology().getVendorName());

                TextView lblVendorUrl = (TextView) getActivity().findViewById(R.id.lblTechnologyVendorUrl);
                if (lblVendorUrl == null) return;
                lblVendorUrl.setText(result.getTechnology().getVendorUrl());

                String logoUrl = result.getTechnology().getLogoUrl();
                if (logoUrl == null) return;
                final ImageView imgLogo = (ImageView) getActivity().findViewById(R.id.imgTechnologyLogo);
                if (imgLogo == null) return;

                data.loadImage(logoUrl, new App.ImageResult() {
                    @Override
                    public void success(Bitmap img) {
                        imgLogo.setImageBitmap(img);
                    }
                });

                break;
        }
    }

}
