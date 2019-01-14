package com.example.cs_student.breakoutpong;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {

    Intent intent;
    //MediaPlayer sound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        /*sound = MediaPlayer.create(this, R.raw.music);
        sound.seekTo(0);
        sound.start();
        sound.setLooping(true);
        sound.setVolume((float).5,(float).5);*/



       intent = new Intent(this, MainActivity.class);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.Menu);

        ImageButton StartGame = new ImageButton(this);
        ImageView titlescreen = new ImageView(this);
        StartGame.setImageResource(R.drawable.start);
        titlescreen.setImageResource(R.drawable.titlescreen);

        layout.addView(StartGame,(size.x)/2,(size.y)/5);

        layout.addView(titlescreen,7*(size.x)/8,(size.y)/5);

        StartGame.setX((size.x)/4);
        StartGame.setY(3*(size.y)/4);
        StartGame.setScaleType(ImageView.ScaleType.FIT_XY);
        titlescreen.setScaleType(ImageView.ScaleType.FIT_XY);

        ///ImageView titlescreen = (ImageView) findViewById(R.id.imageView);






        titlescreen.setMaxWidth((size.x)/10);
        titlescreen.setMaxHeight((size.y)/5);




        titlescreen.setX(((size.x)/15));
        titlescreen.setY(((size.y)/3));



        StartGame.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View v) {

                                             intent.setAction(Intent.ACTION_RUN);
                                             startActivity(intent);

                                         }
                                     });


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {


        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();

        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
