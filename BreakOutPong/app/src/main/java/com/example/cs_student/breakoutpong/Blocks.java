package com.example.cs_student.breakoutpong;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.text.Layout;
import android.widget.ImageView;

/**
 * Created by cs-student on 2/5/18.
 */

public class Blocks {


    MainActivity activity;
    ImageView imageView;
    double colorpicker;

    float x=0;
    float y=0;

    Blocks (MainActivity activity, float x, float y ) {

        this.activity = activity;

        this.x=x;
        this.y=y;

        imageView = new ImageView(activity);

        colorpicker = Math.round(5*Math.random()+1);


        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        if (colorpicker == 1) {
            //imageView.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.brickred);
        }
        if (colorpicker == 2) {
          // imageView.setBackgroundColor(Color.BLUE);
           imageView.setImageResource(R.drawable.brickblue);
        }
        if (colorpicker == 3) {
          imageView.setImageResource(R.drawable.brickyellow);
           //imageView.setBackgroundColor(Color.YELLOW);
        }
        if (colorpicker == 4) {
           imageView.setImageResource(R.drawable.brickgreen);
            //imageView.setBackgroundColor(Color.GREEN);
        }
        if (colorpicker == 5) {
            imageView.setImageResource(R.drawable.brickpurple);
           // imageView.setBackgroundColor(Color.MAGENTA);
        }
        if (colorpicker == 6) {
            imageView.setImageResource(R.drawable.brickorange);
            //imageView.setBackgroundColor(Color.CYAN);
        }



        //imageView.setMinimumWidth((int) activity.screenWidth / 10);
        //imageView.setMinimumHeight((int) activity.screenHeight / 30);
       // imageView.setMaxWidth((int) activity.screenWidth / 10);
       // imageView.setMaxHeight((int) activity.screenHeight / 30);


        activity.layout.addView(imageView, (int)(activity.screenWidth / 10.0), (int)(activity.screenHeight / 30));

    }


}
