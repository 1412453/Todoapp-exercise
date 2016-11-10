package com.a1412453.todoapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1412453.todoapp.R;

public class SplashActivity extends AppCompatActivity {

    protected boolean mActive = true;
    protected int mSplashTime = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView textAppName = (TextView) findViewById(R.id.text_app_name);
        TextView textAppMotto = (TextView) findViewById(R.id.text_app_motto);
        ImageView imageLogo = (ImageView) findViewById(R.id.image_logo);

        Thread splashThread = new Thread() {
            public void run() {

                try {
                    int waited = 0;
                    while (mActive && (waited < mSplashTime)) {
                        sleep(100);
                        if (mActive) {
                            waited += 100;
                        }
                    }
                }

                catch (InterruptedException e) {
                } finally {
                    finish();
                            startActivity(new Intent(getApplicationContext(),
                            MainActivity.class));
                }
            }
        };
        splashThread.start();

    }



}
