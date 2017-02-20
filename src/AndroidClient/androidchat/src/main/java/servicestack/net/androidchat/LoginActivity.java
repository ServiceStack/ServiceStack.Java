package servicestack.net.androidchat;

import android.accounts.Account;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import net.servicestack.android.AndroidServiceClient;
import net.servicestack.client.Log;

import io.fabric.sdk.android.Fabric;


/**
 * Created by mythz on 2/16/2017.
 */

public class LoginActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private ObjectAnimator animation;

//  For: http://localhost:11001/
    private static final String TWITTER_KEY = "3H1FHjGbA1N0n0aT5yApA";
    private static final String TWITTER_SECRET = "MLrZ0ujK6DwyjlRk2YLp6HwSdoBjtuqwXeHDQLv0Q";

    private TwitterAuthClient twitterAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Create your application here
        // Set our view from the "main" layout resource
        setContentView(R.layout.login);

        Toolbar toolbar = (Toolbar)findViewById(R.id.loginToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 500); // see this max value coming back here, we animale towards that value
        animation.setRepeatMode(ValueAnimator.REVERSE);
        animation.setRepeatCount(100);
        animation.setDuration(1500);
        animation.setInterpolator(new FastOutLinearInInterpolator());

        ImageButton btnTwitter = (ImageButton)findViewById(R.id.btnTwitter);
        ImageButton btnAnon = (ImageButton)findViewById(R.id.btnAnon);
        LoginActivity activity = this;

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        twitterAuth = new TwitterAuthClient();
        btnTwitter.setOnClickListener(view -> {
            startProgressBar();
            twitterAuth.authorize(activity, new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    TwitterSession session = result.data;

                    App.get().getServiceClient().postAsync(new dtos.Authenticate()
                            .setProvider("twitter")
                            .setUserName(Long.toString(session.getUserId()))
                            .setAccessToken(session.getAuthToken().token)
                            .setAccessTokenSecret(session.getAuthToken().secret)
                            .setRememberMe(true),
                        r -> {
                            Intent intent = new Intent(activity, MainActivity.class);
                            startActivity(intent);
                            stopProgressBar();
                        },
                        error -> {
                            Log.e("TwitterAuthClient FAILED!", error);
                            stopProgressBar();
                        });
                }

                @Override
                public void failure(TwitterException exception) {
                    exception.printStackTrace();
                    stopProgressBar();
                }
            });
        });

        btnAnon.setOnClickListener(view -> {
            startProgressBar();
            startGuestChatActivity(App.get().getServiceClient());
            stopProgressBar();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterAuth.onActivityResult(requestCode, resultCode, data);
    }

    private void startProgressBar(){
        runOnUiThread(() -> {
            progressBar.setVisibility(View.VISIBLE);
            animation.start();
        });
    }

    private void stopProgressBar(){
        runOnUiThread(() -> {
            progressBar.clearAnimation();
            progressBar.setVisibility(View.INVISIBLE);
        });
    }

    private void performServiceStackAuth(AndroidServiceClient client){
//        var ssAuth = new ServiceStackAuthenticator(
//                MainActivity.BaseUrl,
//                "twitter",
//                jsonServiceClient =>
//                {
//                    var userDetails = jsonServiceClient.Get(new GetUserDetails());
//                    ISharedPreferences prefs = PreferenceManager.GetDefaultSharedPreferences(this);
//                    prefs.Edit().PutString("TwitterUserName", userDetails.UserName).Commit();
//                    return new Account(userDetails.UserName, jsonServiceClient.CookieContainer);
//                    });
//                    ssAuth.Title = "Twitter / Authorize Chat";
//                    ssAuth.ServiceClientFactory = baseUrl => client;
//                    StartActivity(ssAuth.GetUI(this));
//                    ssAuth.Completed += (authSender, authArgs) =>
//                    {
//                        if (authArgs.IsAuthenticated)
//                        {
//                            AccountStore.Create(this).Save(authArgs.Account, "Twitter");
//                            StartAuthChatActivity(client, authArgs.Account);
//                        }
//                    };
    }

    private void startAuthChatActivity(AndroidServiceClient client, Account existingAccount)
    {
//        client.CookieContainer = existingAccount.Cookies;
        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("SSCookie", client.CookieContainer.GetCookieHeader(new Uri(MainActivity.BaseUrl)));
        startActivity(intent);
    }

    private void startGuestChatActivity(AndroidServiceClient client)
    {
        client.clearCookies();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("SSCookie", "");
        startActivity(intent);
    }

    private Account tryResolveAccount(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = prefs.getString("TwitterUserName", null);

//        var existingTwitterAccount = AccountStore.Create(this).FindAccountsForService("Twitter");
//        var twitterAccount = existingTwitterAccount.FirstOrDefault(x => x.Username == userName);
//        return twitterAccount;
        return null;
    }
}
