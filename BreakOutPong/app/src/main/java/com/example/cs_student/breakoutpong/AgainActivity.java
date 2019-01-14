package com.example.cs_student.breakoutpong;

import android.content.Intent;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AgainActivity extends AppCompatActivity {

    Intent intent;
    TextView RedWins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_again2);

        intent = new Intent(this, MainActivity.class);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.REDWINS);



        final Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        ImageView Redwins = new ImageView(this);
        Redwins.setImageResource(R.drawable.redwins2);
        layout.addView(Redwins,(size.x),(size.y)/2);


        Redwins.setX((size.x)/50);
        Redwins.setY((size.y)/20);

        ImageView Redwins2 = new ImageView(this);
        Redwins2.setImageResource(R.drawable.redwins);
        layout.addView(Redwins2,(size.x),(size.y)/2);


        Redwins2.setX((size.x)/50);
        Redwins2.setY((size.y)/2);

        ImageButton StartGame = new ImageButton(this);
        StartGame.setImageResource(R.drawable.again);




        StartGame.setMinimumWidth((size.x)/5);
        StartGame.setMinimumHeight((size.y)/30);

        StartGame.setY(45*(size.y)/100);
        StartGame.setX(((size.x))/4);
        StartGame.setScaleType(ImageView.ScaleType.FIT_XY);
        layout.addView(StartGame,(size.x)/2,(size.y)/5);

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
