package servicestack.net.androidchat;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import net.servicestack.android.AndroidServiceClient;
import net.servicestack.client.Log;

import java.util.Arrays;

import io.fabric.sdk.android.Fabric;

/**
 * This Login Page signs in using Custom Facebook and Twitter Image Buttons
 */

public class LoginActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private ObjectAnimator animation;

    private TwitterAuthClient twitterAuth;
    private CallbackManager facebookCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Fabric.with(this, new Twitter(new TwitterAuthConfig(
            getString(R.string.twitter_key),
            getString(R.string.twitter_secret))));

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

        LoginActivity activity = this;

        ImageButton btnTwitter = (ImageButton)findViewById(R.id.btnTwitter);
        twitterAuth = new TwitterAuthClient();
        btnTwitter.setOnClickListener(view -> {
            startProgressBar();
            twitterAuth.authorize(activity, new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    TwitterSession session = result.data;

                    App.get().getServiceClient().postAsync(new dtos.Authenticate()
                            .setProvider("twitter")
                            .setAccessToken(session.getAuthToken().token)
                            .setAccessTokenSecret(session.getAuthToken().secret)
                            .setRememberMe(true),
                        r -> {
                            App.get().saveTwitterAccessToken(session.getAuthToken());
                            Intent intent = new Intent(activity, MainActivity.class);
                            stopProgressBar();
                            startActivity(intent);
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

        facebookCallback = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(facebookCallback, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginWithFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                stopProgressBar();
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e(exception);
                stopProgressBar();
            }
        });

        ImageButton btnFacebook = (ImageButton)findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(view -> {
            startProgressBar();
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email"));
        });

        ImageButton btnAnon = (ImageButton)findViewById(R.id.btnAnon);
        btnAnon.setOnClickListener(view -> {
            startProgressBar();
            startGuestChatActivity(App.get().getServiceClient());
            stopProgressBar();
        });

        //Login with facebook if already logged in
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null){
            loginWithFacebook(accessToken);
        }
    }

    private void loginWithFacebook(AccessToken accessToken){
        LoginActivity activity = this;
        App.get().getServiceClient().postAsync(new dtos.Authenticate()
            .setProvider("facebook")
            .setAccessToken(accessToken.getToken())
            .setRememberMe(true),
                r -> {
                    Intent intent = new Intent(activity, MainActivity.class);
                    startActivity(intent);
                    stopProgressBar();
                },
                error -> {
                    Log.e("Facebook LoginButton FAILED!", error);
                    stopProgressBar();
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterAuth.onActivityResult(requestCode, resultCode, data);
        facebookCallback.onActivityResult(requestCode, resultCode, data);
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

    private void startGuestChatActivity(AndroidServiceClient client)
    {
        client.clearCookies();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
