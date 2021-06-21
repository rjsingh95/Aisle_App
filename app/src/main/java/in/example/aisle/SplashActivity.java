package in.example.aisle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.security.Signature;

import in.example.aisle.LoginActivity;
import in.example.aisle.MainActivity;
import in.example.aisle.Network.PrefUtils;
import in.example.aisle.R;


public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setAnimation();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);



        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(!PrefUtils.getApiKey(

                        getApplication(), "token").

                        toString().

                        isEmpty())

                {
                    intentToHome();
                } else

                {
                    intentToLogin();
                }

                finish();
            }
        }, SPLASH_TIME_OUT);

    }
    private void intentToLogin() {

        Intent intent = new Intent(this, LoginActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void intentToHome() {

        Intent intent = new Intent(this, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void setAnimation() {




        findViewById(R.id.appname).setAlpha(1.0F);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade);
        findViewById(R.id.appname).startAnimation(anim);

    }
}