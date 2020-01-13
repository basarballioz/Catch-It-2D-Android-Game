package com.basarballioz.catchit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class aboutScreen extends AppCompatActivity {

    Button turnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_about_screen);

        turnback = findViewById(R.id.turnBack);
    }

    public void turnBack(View view) {
        Intent welcomeScreen = new Intent(aboutScreen.this, WelcomeScreen.class);
        startActivity(welcomeScreen);
    }

    public void openGitHub(View view) {
        Intent gitHub = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/basarballioz"));
        startActivity(gitHub);
    }


}
