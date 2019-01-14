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

public class AgainAgainActivity extends AppCompatActivity {

    Intent intent;
    TextView GreenWins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_again_again);
        intent = new Intent(this, MainActivity.class);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.Gwin);

        final Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        ImageView Greenwins = new ImageView(this);
        Greenwins.setImageResource(R.drawable.greenwins);
        layout.addView(Greenwins,(size.x),(size.y)/2);



        Greenwins.setX((size.x)/50);
        Greenwins.setY((size.y)/2);

        ImageView Greenwins2 = new ImageView(this);
        Greenwins2.setImageResource(R.drawable.greenwins2);
        layout.addView(Greenwins2,(size.x),(size.y)/2);



        Greenwins2.setX((size.x)/50);
        Greenwins2.setY((size.y)/20);



        ImageButton StartGame = new ImageButton(this);
        StartGame.setImageResource(R.drawable.again2);




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
