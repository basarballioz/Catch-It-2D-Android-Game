package com.basarballioz.catchit;
//WARPFLARE ORNEK SPLASH SCREEN

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //EKRANI FULLSCREEN YAP
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2600);
                    Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splashThread.start();


    }
}
