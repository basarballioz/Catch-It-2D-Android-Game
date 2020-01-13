package com.basarballioz.catchit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score = 0;
    int lastScore;
    TextView scoreText;
    TextView timeText;
    TextView userRecord;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    TextView informationText;
    TextView showStatus;
    CountDownTimer gameTime = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        showStatus = findViewById(R.id.showStatus);
        userRecord = findViewById(R.id.userRecord);
        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageArray = new ImageView[] {imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11};
        hideImages();

        userRecord.setText("Last Score: " + lastScore);

        //SETTING TIMER FOR GAME TIME
        gameTime = new CountDownTimer(15000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: " + millisUntilFinished / 1000);

                if (4000 > millisUntilFinished) {
                    timeText.setTextColor(Color.RED);
                }
            }


            @Override
            public void onFinish() {

                //TİTREŞİM ÖZELLİĞİ EKLE
                final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(500);

                userRecord.setText("Last Score: " + lastScore);
                timeText.setText("Time is up!");

                //OYUN BİTTİĞİNDE HANDLER'I DURDURMA (GÖRSELLER ARTIK HAREKET EDEMEYECEK !!!)
                handler.removeCallbacks(runnable);

                //TÜM RESİMLERİ EN BAŞTA GÖRÜNMEZ HALE GETİRME
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder gameOver = new AlertDialog.Builder(MainActivity.this);

                //LOST FOCUS - ALERT DIALOGUNUN KAPANMASINI ONLEME KISMI
                gameOver.setCancelable(false);

                gameOver.setTitle("Time is up!");
                gameOver.setMessage("Your score was: " + score + "\nDo you want to play again ?");

                //IF USER WANTS TO PLAY AGAIN
                gameOver.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vibrator.vibrate(5);
                        //RESTART
                        Intent welcomeScreen = new Intent(MainActivity.this, WelcomeScreen.class);
                        finish();
                        //RESTART ACTIVITY
                        startActivity(welcomeScreen);
                    }
                });

                //IF GAME OVER
                gameOver.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vibrator.vibrate(5);
                        //NON-RESTART
                        showToast();

                        new CountDownTimer(3500, 1000) {

                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                Intent welcomeScreen = new Intent(MainActivity.this, WelcomeScreen.class);
                                startActivity(welcomeScreen);
                            }
                        }.start();
                    }

                });
                gameOver.show();
            }
        }.start();
        lastScore = score;
    }

    @Override
    public void onBackPressed() {
        gameTime.cancel();
        handler.removeCallbacks(runnable);

        Intent welcomeScreen = new Intent(MainActivity.this, WelcomeScreen.class);
        finish();
        //SHOW WELCOME SCREEN
        startActivity(welcomeScreen);
    }


    public void increaseScore(View view) {

        MediaPlayer woah = MediaPlayer.create(MainActivity.this, R.raw.crash_woah);
        woah.start();

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        int everyTouch = 50;
        vibrator.vibrate(everyTouch);

        score++;
        lastScore++;
        scoreText.setText("Score: " + score);

        if (score >= 1) {
            showStatus.setText("");
            showStatus.setTextSize(22);
            showStatus.setText("GREAT!");
        }

        if (score >= 5) {
            showStatus.setText("");
            showStatus.setTextSize(24);
            showStatus.setText("KEEP GOING!");
        }

        if (score >= 10) {
            showStatus.setText("");
            showStatus.setTextSize(26);
            showStatus.setTextColor(Color.rgb(255, 110, 110));
            showStatus.setText("PERFECT!");
        }

        if (score >= 15) {
            showStatus.setText("");
            showStatus.setTextSize(28);
            showStatus.setTextColor(Color.rgb(60, 100, 235));
            showStatus.setText("MASTER!");
        }

        if (score >= 20) {
            showStatus.setText("");
            showStatus.setTextSize(30);
            showStatus.setText("LIKE A PRO!");
        }

        if (score >= 30) {
            showStatus.setText("");
            showStatus.setTextSize(32);
            showStatus.setText("LIGHTSPEED!");
        }

        if (score >= 50) {
            showStatus.setText("");
            showStatus.setTextSize(34);
            showStatus.setText("UNSTOPPABLE!!!");
        }

    }

    public void hideImages() {

        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {

                //OYUNUN BAŞINDA HEPSİNİ GÖRÜNMEZ YAP
                for (ImageView crashpng : imageArray) {
                    crashpng.setVisibility(View.INVISIBLE);
                }

                //RANDOM ÇALIŞTIR BİRDEN DOKUZA KADAR
                Random random = new Random();
                int crashImage = random.nextInt(9);

                imageArray[crashImage].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 500);

            }
        };
        handler.post(runnable);
    }

    public void showToast() {
        Toast toast = Toast.makeText(MainActivity.this, "Game Over :) \nYour score was: " + score, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }



}
