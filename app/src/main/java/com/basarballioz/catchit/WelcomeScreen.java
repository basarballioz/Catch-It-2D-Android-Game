package com.basarballioz.catchit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {

    TextView pressStart;
    Button startGameButton;
    Button showAbout;
    Button gitHubButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome_screen);


        pressStart = findViewById(R.id.pressStart);
        startGameButton = findViewById(R.id.startGameButton);
        showAbout = findViewById(R.id.showAbout);
        gitHubButton = findViewById(R.id.gitHubButton);
    }

    public void startGame(View view) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(50);

        Intent startGame = new Intent(WelcomeScreen.this, MainActivity.class);
        startActivity(startGame);
    }

    public void showAbout(View view) {
        Intent aboutScreen = new Intent(WelcomeScreen.this, aboutScreen.class);
        startActivity(aboutScreen);
    }

    public void openGitHub(View view) {
        Intent gitHub = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/basarballioz"));
        startActivity(gitHub);
    }




}
