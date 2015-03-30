package servicestack.net.androidclient;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.servicestack.android.AndroidServiceClient;
import net.servicestack.client.AsyncResult;
import net.servicestack.client.Utils;

import java.util.ArrayList;
import servicestack.net.androidclient.techstacksdtos.*;
import static net.servicestack.client.Func.*;

public class MainActivity extends ActionBarActivity {

    AndroidServiceClient client = new AndroidServiceClient("http://techstacks.io");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lstResults = (ListView) findViewById(R.id.lstResults);

        final MainActivity self = this;
        client.getAsync(new AppOverview(), new AsyncResult<AppOverviewResponse>() {
            @Override
            public void success(AppOverviewResponse response) {
                ArrayList<String> topTechs = map(response.getTopTechnologies(), new Function<TechnologyInfo, String>() {
                    @Override
                    public String apply(TechnologyInfo x) {
                        return x.getName() + " (" + x.getStacksCount() + ")";
                    }
                });

                ArrayAdapter<String> adapter = new ArrayAdapter<>(self,
                    android.R.layout.simple_list_item_1,
                    topTechs);

                lstResults.setAdapter(adapter);
            }
        });
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
}
