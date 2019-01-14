package com.example.cs_student.breakoutpong;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //TODO put in sound & fix image view

    Intent intent;
    Intent intent2;

    boolean point = false;
    boolean hit = false;
    boolean smack = false;


    ConstraintLayout layout;
    View topControl,bottomControl;
    ImageView paddle;
    ImageView paddle2;

    ImageView RedScore;
    ImageView GreenScore;

    TextView ScoreTop;
    TextView ScoreBottom;

    boolean removeBlock = false;
    boolean ListCom = false;

    float P1Score=0;
    float P2Score=0;

    ImageView ball;
    double ballvx;
    double ballvy;

    String P1;
    String P2;

    double tt = 0;

    double server;


    float screenWidth;
    float screenHeight;

    float xCoord;
    float xCoord2;

    float ChangeInterval = 5;
    float ChangeTimer=0;
    float changeTimerAcc = 5;


    boolean isDown = false;
    boolean isUp = false;

    Runnable frameUpdate;
    Runnable frameUpdate2;
    TimerTask frameTask;
    Timer timer;
    long currentSysTime;
    long PreviousSysTime;
    double LastTimeStep;

    double ballx;
    double bally;
    double paddlex;
    double paddley;

    double paddle2x;
    double paddle2y;

    ImageView ball2;
    double ball2vx;
    double ball2vy;
    double ball2x;
    double ball2y;

    MediaPlayer sound;
    MediaPlayer sound2;
    MediaPlayer sound3;
    MediaPlayer sound4;


    ArrayList<Blocks> block = new ArrayList();
    ArrayList<Coords> delBlock = new ArrayList();
    ArrayList<Blocks>  DeadBlock = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sound = MediaPlayer.create(this, R.raw.blipselect);
        sound2 = MediaPlayer.create(this, R.raw.hithurt);
        sound3 = MediaPlayer.create(this, R.raw.pickupcoin);

        sound4 = MediaPlayer.create(this, R.raw.music);
        sound4.seekTo(0);
        sound4.start();
        sound4.setLooping(true);
        sound4.setVolume((float).5,(float).5);


            server = Math.round(Math.random());
            //server = 0;

           // System.out.println(server);

            final Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
            screenHeight = size.y;


            int paddleWidth = ((int) screenWidth);

            int paddleHeight = ((int) screenHeight);


            ScoreBottom = (TextView) findViewById(R.id.textView);
            ScoreTop = (TextView) findViewById(R.id.textview2);


            layout = (ConstraintLayout) findViewById(R.id.mainLayout);

            topControl = new View(this);
            layout.addView(topControl, (int) screenWidth, (int) screenHeight / 2);
            //topControl.setBackgroundColor(Color.BLUE);


            bottomControl = new View(this);
            layout.addView(bottomControl, (int) screenWidth, (int) screenHeight / 2);
            bottomControl.setY(screenHeight / 2);
            //bottomControl.setBackgroundColor(Color.RED);


            //makeBlock((int)screenWidth/2 + 8*(paddleWidth/5),(5*screenHeight/12)-screenHeight/60);
            //makeBlock((int)screenWidth/2 + 6*(paddleWidth/5),(5*screenHeight/12)-screenHeight/60);
            // makeBlock((int)screenWidth/2 + 4*(paddleWidth/5),(5*screenHeight/12)-screenHeight/60);
            makeBlock(0, 19 * (screenWidth / 30));
            makeBlock((paddleWidth / 10), 19 * (screenWidth / 30));
            makeBlock((2 * paddleWidth / 10), 19 * (screenWidth / 30));
            makeBlock((3 * paddleWidth / 10), 19 * (screenWidth / 30));
            makeBlock((4 * paddleWidth / 10), 19 * (screenWidth / 30));
            makeBlock((5 * paddleWidth / 10), 19 * (screenWidth / 30));
            makeBlock((6 * paddleWidth / 10), 19 * (screenWidth / 30));
            makeBlock((7 * paddleWidth / 10), 19 * (screenWidth / 30));
            makeBlock((8 * paddleWidth / 10), 19 * (screenWidth / 30));
            makeBlock((9 * paddleWidth / 10), 19 * (screenWidth / 30));


            makeBlock(0, 21 * (screenWidth / 30));
            makeBlock((paddleWidth / 10), 21 * (screenWidth / 30));
            makeBlock((2 * paddleWidth / 10), 21 * (screenWidth / 30));
            makeBlock((3 * paddleWidth / 10), 21 * (screenWidth / 30));
            makeBlock((4 * paddleWidth / 10), 21 * (screenWidth / 30));
            makeBlock((5 * paddleWidth / 10), 21 * (screenWidth / 30));
            makeBlock((6 * paddleWidth / 10), 21 * (screenWidth / 30));
            makeBlock((7 * paddleWidth / 10), 21 * (screenWidth / 30));
            makeBlock((8 * paddleWidth / 10), 21 * (screenWidth / 30));
            makeBlock((9 * paddleWidth / 10), 21 * (screenWidth / 30));

            makeBlock(0, 23 * (screenWidth / 30));
            makeBlock((paddleWidth / 10), 23 * (screenWidth / 30));
            makeBlock((2 * paddleWidth / 10), 23 * (screenWidth / 30));
            makeBlock((3 * paddleWidth / 10), 23 * (screenWidth / 30));
            makeBlock((4 * paddleWidth / 10), 23 * (screenWidth / 30));
            makeBlock((5 * paddleWidth / 10), 23 * (screenWidth / 30));
            makeBlock((6 * paddleWidth / 10), 23 * (screenWidth / 30));
            makeBlock((7 * paddleWidth / 10), 23 * (screenWidth / 30));
            makeBlock((8 * paddleWidth / 10), 23 * (screenWidth / 30));
            makeBlock((9 * paddleWidth / 10), 23 * (screenWidth / 30));

            makeBlock(0, 25 * (screenWidth / 30));
            makeBlock((paddleWidth / 10), 25 * (screenWidth / 30));
            makeBlock((2 * paddleWidth / 10), 25 * (screenWidth / 30));
            makeBlock((3 * paddleWidth / 10), 25 * (screenWidth / 30));
            makeBlock((4 * paddleWidth / 10), 25 * (screenWidth / 30));
            makeBlock((5 * paddleWidth / 10), 25 * (screenWidth / 30));
            makeBlock((6 * paddleWidth / 10), 25 * (screenWidth / 30));
            makeBlock((7 * paddleWidth / 10), 25 * (screenWidth / 30));
            makeBlock((8 * paddleWidth / 10), 25 * (screenWidth / 30));
            makeBlock((9 * paddleWidth / 10), 25 * (screenWidth / 30));


            makeBlock(0, 27 * (screenWidth / 30));
            makeBlock((paddleWidth / 10), 27 * (screenWidth / 30));
            makeBlock(2 * (paddleWidth / 10), 27 * (screenWidth / 30));
            makeBlock(3 * (paddleWidth / 10), 27 * (screenWidth / 30));
            makeBlock(4 * (paddleWidth / 10), 27 * (screenWidth / 30));
            makeBlock(5 * (paddleWidth / 10), 27 * (screenWidth / 30));
            makeBlock(6 * (paddleWidth / 10), 27 * (screenWidth / 30));
            makeBlock(7 * (paddleWidth / 10), 27 * (screenWidth / 30));
            makeBlock(8 * (paddleWidth / 10), 27 * (screenWidth / 30));
            makeBlock(9 * (paddleWidth / 10), 27 * (screenWidth / 30));


            //  makeBlock((int)screenWidth/2 - 4*(paddleWidth/5),(7*screenHeight/12)+screenHeight/60);
            // makeBlock((int)screenWidth/2 - 6*(paddleWidth/5),(7*screenHeight/12)+screenHeight/60);
            //  makeBlock((int)screenWidth/2 + 8*(paddleWidth/5),(7*screenHeight/12)+screenHeight/60);


            // makeBlock((int)screenWidth/5,(int)screenHeight/2);
            // makeBlock((int)screenWidth/6,(int)screenHeight/2);
            //makeBlock((int)screenWidth/7,(int)screenHeight/2);

        intent = new Intent(this, AgainActivity.class);
        intent2 = new Intent(this, AgainAgainActivity.class);

            paddle = new ImageView(this);
            //paddle.setBackgroundColor(Color.RED);
            paddle.setMinimumWidth(paddleWidth / 5);
            paddle.setMinimumHeight(paddleHeight / 30);
            paddle.setX(screenWidth / 2);
            paddle.setY((screenHeight * 85) / 100);
            paddle.setImageResource(R.drawable.paddlered);
            layout.addView(paddle, paddleWidth / 5, paddleHeight / 30);

            paddle2 = new ImageView(this);
            //paddle2.setBackgroundColor(Color.GREEN);
            paddle2.setMinimumWidth(paddleWidth / 5);
            paddle2.setMinimumHeight(paddleHeight / 30);
            paddle2.setX(screenWidth / 2);
            paddle2.setY((screenHeight * 10) / 100);
            paddle2.setImageResource(R.drawable.paddlegreen);
            layout.addView(paddle2,paddleWidth / 5, paddleHeight / 30);

        RedScore = new ImageView(this);
        RedScore.setScaleType(ImageView.ScaleType.FIT_XY);
        RedScore.setY(199*(size.y/200));
        RedScore.setX((size.x/2));
        RedScore.setImageResource(R.drawable.red0);
        layout.addView(RedScore,(int)(screenWidth/20),(int)(screenHeight/20));

        GreenScore = new ImageView(this);
        GreenScore.setScaleType(ImageView.ScaleType.FIT_XY);
        GreenScore.setY(1*(size.y/200));
        GreenScore.setX((size.x/2));
        GreenScore.setImageResource(R.drawable.green0);
        layout.addView(GreenScore,(int)(screenWidth/20),(int)(screenHeight/20));


            ball = new ImageView(this);
           // ball.setBackgroundColor(Color.LTGRAY);
            ball.setMinimumHeight(paddleHeight / 30);
            ball.setMinimumWidth(paddleWidth / 20);
            ball.setX(screenWidth / 2);
            ball.setY(screenHeight / 2);
            ball.setImageResource(R.drawable.grayball);
            layout.addView(ball,paddleWidth / 20,paddleHeight / 30);

            ball2 = new ImageView(this);
            //ball2.setBackgroundColor(Color.BLACK);
            ball2.setMinimumHeight(paddleHeight / 30);
            ball2.setMinimumWidth(paddleWidth / 20);
            ball2.setX(screenWidth / 2);
            ball2.setY(screenHeight / 2);
            ball2.setImageResource(R.drawable.blackball);
            layout.addView(ball2,paddleWidth / 20,paddleHeight / 30);

            ballx = (screenWidth / 2);
            bally = (screenHeight / 2);


            //ball.setY(screenHeight/2);
            ballx = (screenWidth / 2);
            bally = (2 * screenHeight / 3);
            ballvy = (screenHeight * .5);

            ball2x = (screenWidth / 2);
            ball2y = (screenHeight / 3);
            ball2vy = -(screenHeight * .5);


            topControl.setOnTouchListener(new View.OnTouchListener() {
                boolean fingerOnScreen = false;

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        fingerOnScreen = true;

                    }

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        fingerOnScreen = false;
                    }

                    if (motionEvent.getAction() == MotionEvent.ACTION_MOVE && fingerOnScreen) {

                        xCoord = motionEvent.getX() - screenWidth / 10;


                        paddle2.setX(xCoord);
                    }
                    return true;
                }
            });

            bottomControl.setOnTouchListener(new View.OnTouchListener() {

                boolean fingerOnScreen = false;

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent2) {

                    if (motionEvent2.getAction() == MotionEvent.ACTION_DOWN) {
                        fingerOnScreen = true;

                    }

                    if (motionEvent2.getAction() == MotionEvent.ACTION_UP) {
                        fingerOnScreen = false;
                    }

                    if (motionEvent2.getAction() == MotionEvent.ACTION_MOVE && fingerOnScreen) {
                        xCoord = motionEvent2.getX() - screenWidth / 10;


                        paddle.setX(xCoord);
                    }
                    return true;
                }
            });


            frameUpdate = new Runnable() {
                @Override
                public void run() {


                    ball.setY((float) bally);
                    ball.setX((float) ballx);
                    ball2.setY((float) ball2y);
                    ball2.setX((float) ball2x);
                    PreviousSysTime = currentSysTime;


                }
            };

            frameUpdate2 = new Runnable() {
                @Override
                public void run() {
                   // ScoreBottom.setText(P1);
                    //ScoreTop.setText(P2);

                    if (P1Score == 1){
                        RedScore.setImageResource(R.drawable.red1);


                    }
                    if (P1Score == 2){
                        RedScore.setImageResource(R.drawable.red2);


                    }
                    if (P1Score == 3){
                        RedScore.setImageResource(R.drawable.red3);

                    }if (P1Score == 4){
                        RedScore.setImageResource(R.drawable.red4);


                    }if (P1Score == 5){
                        RedScore.setImageResource(R.drawable.red5);


                    }if (P1Score == 6){
                        RedScore.setImageResource(R.drawable.red6);


                    }if (P1Score == 7){
                        RedScore.setImageResource(R.drawable.red7);


                    }
                    if (P1Score == 8){
                        RedScore.setImageResource(R.drawable.red8);

                    }
                    if (P1Score == 9){
                        RedScore.setImageResource(R.drawable.red9);


                    }

                    if (P2Score == 1){
                        GreenScore.setImageResource(R.drawable.green1);


                    }
                    if (P2Score == 2){
                        GreenScore.setImageResource(R.drawable.green2);

                    }
                    if (P2Score == 3){
                        GreenScore.setImageResource(R.drawable.green3);


                    }if (P2Score == 4){
                        GreenScore.setImageResource(R.drawable.green4);


                    }if (P2Score == 5){
                        GreenScore.setImageResource(R.drawable.green5);

                    }if (P2Score == 6){
                        GreenScore.setImageResource(R.drawable.green6);


                    }if (P2Score == 7){
                        GreenScore.setImageResource(R.drawable.green7);


                    }
                    if (P2Score == 8){
                        GreenScore.setImageResource(R.drawable.green8);

                    }
                    if (P2Score == 9){
                        GreenScore.setImageResource(R.drawable.green9);

                    }






                    if (P1Score == 7) {
                        timer.cancel();
                        sound4.stop();

                        intent.setAction(Intent.ACTION_VIEW);
                        startActivity(intent);

                        ScoreBottom.setText("RED WINS!");
                        ScoreTop.setText("RED WINS!");
                        layout.removeView(ball);
                        layout.removeView(ball2);
                    }

                    if (P2Score == 7) {
                        timer.cancel();
                        sound4.stop();

                        intent2.setAction(Intent.ACTION_VIEW);
                        startActivity(intent2);

                        ScoreBottom.setText("GREEN WINS!");
                        ScoreTop.setText("GREEN WINS!");
                        layout.removeView(ball);
                        layout.removeView(ball2);
                    }

                    ball.setY((float) bally);
                    ball.setX((float) ballx);
                    ball2.setY((float) ball2y);
                    ball2.setX((float) ball2x);


                    if (ChangeTimer > ChangeInterval){

                        ResBlock();

                        String msg = String.format("delBlock Size = %d", delBlock.size());

                        System.out.println(msg);



                        ChangeTimer = 0;
                        ChangeInterval = ChangeInterval-1;

                        if (ChangeInterval < 2){
                            ChangeInterval = 2;
                           // ResBlock();
                        }
                    }


                    updateBlock();

                    if (removeBlock) {

                        for (int i =0; i<DeadBlock.size();i++) {

                            delBlock.add(new Coords(DeadBlock.get(i).x, DeadBlock.get(i).y));

                            layout.removeView(DeadBlock.get(i).imageView);

                            block.remove(DeadBlock.get(i));

                          //  Blocks thing = DeadBlock.get(i);

                           // DeadBlock.remove(thing);

                            removeBlock = false;


                        }
                        DeadBlock.clear();

                    }

                  /*  if (ListCom){
                        delBlock.remove(Resblock);
                        ListCom = false;
                    }*/


                    PreviousSysTime = currentSysTime;


                }
            };


            frameTask = new TimerTask() {
                @Override
                public void run() {

                    currentSysTime = System.nanoTime();
                    float t = (currentSysTime - PreviousSysTime) / 1000000000.0f;

                   // ChangeInterval=10;
                    ChangeTimer+=t;

                    paddlex = paddle.getX();
                    paddley = paddle.getY();

                    paddle2x = paddle2.getX();
                    paddle2y = paddle2.getY();


                    ballx = ballx + ballvx * t;
                    bally = bally + ballvy * t;

                    ball2x = ball2x + ball2vx * t;
                    ball2y = ball2y + ball2vy * t;

                    if (bally < 0) {

                        P1Score = P1Score + 1;
                        point = true;



                       // System.out.println(P1Score);

                        P1 = String.valueOf(P1Score);

                        //ballvy = -1 * ballvy;

                        if (P1Score == 7) {

                            ballvy = 0;
                            ballvx = 0;
                            ballx = (screenWidth / 2);
                            bally = (screenHeight / 3);

                            ballvy = 0;
                            ballvx = 0;
                            ballx = (screenWidth / 2);
                            bally = (2 * screenHeight / 3);


                        } else {
                            ballvy = -(screenHeight * .5);
                            ballvx = 0;
                            ballx = (screenWidth / 2);
                            bally = (screenHeight / 3);
                        }


                    }

                    if (bally > screenHeight - (screenHeight / 30)) {

                        P2Score = P2Score + 1;
                      //  System.out.println(P2Score);

                        P2 = String.format("%3.1f", P2Score);
                        point = true;

                        if (P2Score == 7) {

                            ballvy = 0;
                            ballvx = 0;
                            ballx = (screenWidth / 2);
                            bally = (screenHeight / 3);

                            ballvy = 0;
                            ballvx = 0;
                            ballx = (screenWidth / 2);
                            bally = (2 * screenHeight / 3);

                        } else {

                            // ballvy = -1 * ballvy;
                            //ballx = (screenWidth / 2);
                            // bally = (screenWidth / 2);

                            ballvy = (screenHeight * .5);
                            ballvx = 0;
                            ballx = (screenWidth / 2);
                            bally = (2 * screenHeight / 3);
                        }


                    }

                    if (ball2y < 0) {

                        P1Score = P1Score + 1;
                        point = true;
                       // System.out.println(P1Score);

                        P1 = String.valueOf(P1Score);

                        if (P1Score == 10) {
                            ballvy = 0;
                            ballvx = 0;
                            ballx = (screenWidth / 2);
                            bally = (screenHeight / 3);

                            ball2vy = 0;
                            ball2vx = 0;
                            ball2x = (screenWidth / 2);
                            ball2y = (2 * screenHeight / 3);


                        } else {

                            //ballvy = -1 * ballvy;

                            ball2vy = -(screenHeight * .5);
                            ball2vx = 0;
                            ball2x = (screenWidth / 2);
                            ball2y = (screenHeight / 3);
                        }


                    }

                    if (ball2y > screenHeight - (screenHeight / 30)) {

                        P2Score = P2Score + 1;
                        point = true;
                       // System.out.println(P2Score);

                        P2 = String.format("%3.1f", P2Score);

                        if (P2Score == 10) {
                            ballvy = 0;
                            ballvx = 0;
                            ballx = (screenWidth / 2);
                            bally = (screenHeight / 3);

                            ball2vy = 0;
                            ball2vx = 0;
                            ball2x = (screenWidth / 2);
                            ball2y = (2 * screenHeight / 3);
                        } else {

                            // ballvy = -1 * ballvy;
                            //ballx = (screenWidth / 2);
                            // bally = (screenWidth / 2);

                            ball2vy = (screenHeight * .5);
                            ball2x = (screenWidth / 2);
                            ball2vx = 0;
                            ball2y = (2 * screenHeight / 3);
                        }


                    }

                    if (point) {

                        sound3.seekTo(0);
                        sound3.start();
                        point = false;


                    }

                    if (hit) {

                        sound2.seekTo(0);
                        sound2.start();
                        hit = false;


                    }

                    if (smack) {

                        sound.seekTo(0);
                        sound.start();
                        smack = false;


                    }

                    if (ballx < 0) {

                        ballvx = -1 * ballvx;
                        ballx = ballx + (screenWidth / 100);
                        //ballvy = -1 * ballvy;
                    }

                    if (ballx > screenWidth - (screenWidth / 20)) {
                        ballvx = -1 * ballvx;
                        ballx = ballx - (screenWidth / 100);
                    }

                    if (ball2x < 0) {

                        ball2vx = -1 * ball2vx;
                        //ballvy = -1 * ballvy;
                        ball2x = ball2x + (screenWidth / 100);
                    }

                    if (ball2x > screenWidth - (screenWidth / 20)) {
                        ball2vx = -1 * ball2vx;
                        ball2x = ball2x - (screenWidth / 100);
                    }

                    if (paddlex > screenWidth - screenWidth / 10) {
                        paddlex = (screenWidth - screenWidth / 10);
                    }
                    if (paddle2x < screenWidth / 10) {
                        paddle2x = (screenWidth / 10);
                    }
                    if (paddle2x > screenWidth - screenWidth / 10) {
                        paddle2x = (screenWidth - screenWidth / 10);
                    }
                    if (paddle2x < screenWidth / 10) {
                        paddle2x = (screenWidth / 10);
                    }


                    //player
                    double box1Right = paddle.getX() + screenWidth / 5;
                    double box1Left = paddle.getX();
                    double box1Top = paddle.getY();
                    double box1Bottom = paddle.getY() + screenHeight / 30;

                    double box1centerX = (box1Right+box1Left)/2;
                    double box1centery = (box1Top+box1Bottom)/2;
                    //ball
                    double box2Left = ballx;
                    double box2Right = ballx + screenWidth / 20;
                    double box2Top = bally;
                    double box2Bottom = bally + screenHeight / 30;

                    double box2centerX = (box2Right+box2Left)/2;
                    double box2centery = (box2Top+box2Bottom)/2;


                    double xOverlap = 0;
                    double biggerLeftBound = Math.max(box1Left, box2Left);
                    double smallerRightBound = Math.min(box1Right, box2Right);
                    xOverlap = smallerRightBound - biggerLeftBound;
                    xOverlap = Math.max(xOverlap,0);

                    double yOverlap = 0;
                    double biggerTopBound = Math.max(box1Top, box2Top);
                    double smallerBottomBound = Math.min(box1Bottom, box2Bottom);
                    yOverlap = smallerBottomBound - biggerTopBound;
                    yOverlap = Math.max(yOverlap,0);




                   if (
                            (
                                    ((box1Left >= box2Left) && (box1Left <= box2Right))
                                            || ((box1Right >= box2Left) && (box1Right <= box2Right))
                                            || ((box2Left >= box1Left) && (box2Left <= box1Right))
                                            || ((box2Right >= box1Left) && (box2Right <= box1Right))
                            )
                                    &&
                                    (
                                            (box1Top >= box2Top) && (box1Top <= box2Bottom)
                                                    || ((box1Bottom >= box2Top) && (box1Bottom <= box2Bottom))
                                                    || ((box2Top >= box1Top) && (box2Top <= box1Bottom))
                                                    || ((box2Bottom >= box1Top) && (box2Bottom <= box1Bottom))
                                    )
                            ) {

                       //ballx = paddlex + (screenWidth / 10);
                       bally = paddley - (screenHeight /30);
                       smack = true;



                        ballx = ballx - ballvx * LastTimeStep;
                        bally = bally - ballvy * LastTimeStep;

                        double Centerx = ballx + (screenWidth / 40);
                        double Centery = bally + (screenHeight / 60);

                        double boxCx = paddle.getX() + screenWidth / 10;
                        double boxCy = paddle.getY() + screenHeight / 60;


                        double distanceX = Math.abs(Centerx - boxCx);
                        double distanceY = Math.abs(Centery - boxCy);

                        double Tx = distanceX / Math.abs(ballvx);
                        double Ty = distanceY / Math.abs(ballvy);

                        double radius = (paddle.getX() + screenWidth / 5) - (ballx + screenWidth / 20);
                        double Bv = Math.pow(Math.pow(ballvx, 2) + Math.pow(ballvy, 2), .5);
                        double ballspeed = Math.abs(Bv);
                        double angle = ((2 * radius * Math.PI) / (22));

                        if (angle > 45) {angle=45;}

                        if (angle < -45) {angle=-45;}

                        double VxNew = ballspeed * Math.cos(angle);
                        double VyNew = ballspeed * Math.sin(angle);





                        ballvx = VxNew;
                        ballvy = VyNew;

                        ballvx = ballvx + (ballvx/8);
                        ballvy = ballvy + (ballvy/8);

                        if (ballvx>1100)
                        {
                            ballvx=1100;
                        }
                        if (ballvy>1100)
                        {
                            ballvy=1100;
                        }

                        if (ballvx<-1100)
                        {
                            ballvx=-1100;
                        }
                        if (ballvy<-1100)
                        {
                            ballvy=-1100;
                        }

                       // System.out.println(ballvy);
                        //System.out.println(ballvx);


                        if (Tx > Ty) {
                            ballx = ballx + ballvx * t;
                            bally = bally + ballvy * LastTimeStep;


                        } else if (Tx < Ty) {
                            ballx = ballx + ballvx * LastTimeStep;
                            bally = bally + ballvy * t;

                        } else {
                            ballx = ballx + ballvx * t;
                            bally = bally + ballvy * t;

                        }

                    } else {
                        ballx += ballvx * LastTimeStep * -.1;
                        bally += ballvy * LastTimeStep * -.1;



                    }


                    //player
                    //ball
                    double box5Left = ball2x;
                    double box5Right = ball2x + screenWidth / 20;
                    double box5Top = ball2y;
                    double box5Bottom = ball2y + screenHeight / 30;

                    if (
                            (
                                    ((box1Left >= box5Left) && (box1Left <= box5Right))
                                            || ((box1Right >= box5Left) && (box1Right <= box5Right))
                                            || ((box5Left >= box1Left) && (box5Left <= box1Right))
                                            || ((box5Right >= box1Left) && (box5Right <= box1Right))
                            )
                                    &&
                                    (
                                            (box1Top >= box5Top) && (box1Top <= box5Bottom)
                                                    || ((box1Bottom >= box5Top) && (box1Bottom <= box5Bottom))
                                                    || ((box5Top >= box1Top) && (box5Top <= box1Bottom))
                                                    || ((box5Bottom >= box1Top) && (box5Bottom <= box1Bottom))
                                    )
                            ) {

                        ball2y = paddley - (screenHeight /30);
                        smack = true;


                        ball2x = ball2x - ball2vx * LastTimeStep;
                        ball2y = ball2y - ball2vy * LastTimeStep;

                        double Centerx = ball2x + (screenWidth / 40);
                        double Centery = ball2y + (screenHeight / 60);

                        double boxCx = paddle.getX() + screenWidth / 10;
                        double boxCy = paddle.getY() + screenHeight / 60;


                        double distanceX = Math.abs(Centerx - boxCx);
                        double distanceY = Math.abs(Centery - boxCy);

                        double Tx = distanceX / Math.abs(ballvx);
                        double Ty = distanceY / Math.abs(ballvy);

                        double radius = (paddle.getX() + screenWidth / 5) - (ball2x + screenWidth / 20);
                        double Bv = Math.pow(Math.pow(ball2vx, 2) + Math.pow(ball2vy, 2), .5);
                        double ballspeed = Math.abs(Bv);
                        double angle = ((2 * radius * Math.PI) / (22));

                        if (angle > 45) {angle=45;}

                        if (angle < -45) {angle=-45;}

                        double VxNew = ballspeed * Math.cos(angle);
                        double VyNew = ballspeed * Math.sin(angle);

                        ball2vx = VxNew;
                        ball2vy = VyNew;

                        ball2vx = ball2vx + (ball2vx/8);
                        ball2vy = ball2vy + (ball2vy/8);

                        if (ball2vx>1100)
                        {
                            ball2vx=1100;
                        }
                        if (ball2vy>1100)
                        {
                            ball2vy=1100;
                        }

                        if (ball2vx<-1100)
                        {
                            ball2vx=-1100;
                        }
                        if (ball2vy<-1100)
                        {
                            ball2vy=-1100;
                        }

                        if (Tx > Ty) {
                            ball2x = ball2x + ball2vx * t;
                            ball2y = ball2y + ball2vy * LastTimeStep;


                        } else if (Tx < Ty) {
                            ball2x = ball2x + ball2vx * LastTimeStep;
                            ball2y = ball2y + ball2vy * t;

                        } else {
                            ball2x = ball2x + ball2vx * t;
                            ball2y = ball2y + ball2vy * t;

                        }

                    } else {
                        ball2x += ball2vx * LastTimeStep * -.1;
                        ball2y += ball2vy * LastTimeStep * -.1;


                    }


                    if (
                            (
                                    ((box5Left >= box2Left) && (box5Left <= box2Right))
                                            || ((box5Right >= box2Left) && (box5Right <= box2Right))
                                            || ((box2Left >= box5Left) && (box2Left <= box5Right))
                                            || ((box2Right >= box5Left) && (box2Right <= box5Right))
                            )
                                    &&
                                    (
                                            (box5Top >= box2Top) && (box5Top <= box2Bottom)
                                                    || ((box5Bottom >= box2Top) && (box5Bottom <= box2Bottom))
                                                    || ((box2Top >= box5Top) && (box2Top <= box5Bottom))
                                                    || ((box2Bottom >= box5Top) && (box2Bottom <= box5Bottom))
                                    )
                            ) {


                        ball2x = ball2x - ball2vx * LastTimeStep;
                        ball2y = ball2y - ball2vy * LastTimeStep;

                        ballx = ballx - ballvx * LastTimeStep;
                        bally = bally - ballvy * LastTimeStep;

                        double Centerx = ball2x + (screenWidth / 40);
                        double Centery = ball2y + (screenHeight / 60);

                        double Center2x = ballx + (screenWidth / 40);
                        double Center2y = bally + (screenHeight / 60);


                        double distanceX = Math.abs(Centerx - Center2x);
                        double distanceY = Math.abs(Centery - Center2y);

                        double Tx = distanceX / Math.abs(ball2vx);
                        double Ty = distanceY / Math.abs(ball2vy);

                        double Tx2 = distanceX / Math.abs(ballvx);
                        double Ty2 = distanceY / Math.abs(ballvy);

                        double radius = (ball.getX() + screenWidth / 20) - (ball2x + screenWidth / 20);
                        double Bv = Math.pow(Math.pow(ball2vx, 2) + Math.pow(ball2vy, 2), .5);
                        double ballspeed = Math.abs(Bv);
                        double angle = ((2 * radius * Math.PI) / (22));
                        if (angle > 45) {angle=45;}

                        if (angle < -45) {angle=-45;}
                        double VxNew = ballspeed * Math.cos(angle);
                        double VyNew = ballspeed * Math.sin(angle);

                        double radius2 = (ball2.getX() + screenWidth / 20) - (ballx + screenWidth / 20);
                        double Bv2 = Math.pow(Math.pow(ballvx, 2) + Math.pow(ballvy, 2), .5);
                        double ballspeed2 = Math.abs(Bv2);
                        double angle2 = ((2 * radius2 * Math.PI) / (22.5));
                        double VxNew2 = ballspeed2 * Math.cos(angle2);
                        double VyNew2 = ballspeed2 * Math.sin(angle2);

                        ball2vx = VxNew;
                        ball2vy = VyNew;
                        ballvx = -VxNew2;
                        ballvy = -VyNew2;


                        if (Tx > Ty) {
                            ball2x = ball2x + ball2vx * t;
                            ball2y = ball2y + ball2vy * LastTimeStep;


                        } else if (Tx < Ty) {
                            ball2x = ball2x + ball2vx * LastTimeStep;
                            ball2y = ball2y + ball2vy * t;

                        } else {
                            ball2x = ball2x + ball2vx * t;
                            ball2y = ball2y + ball2vy * t;

                        }

                        if (Tx2 > Ty2) {
                            ballx = ballx + ballvx * t;
                            bally = bally + ballvy * LastTimeStep;


                        } else if (Tx2 < Ty2) {
                            ballx = ballx + ballvx * LastTimeStep;
                            bally = bally + ballvy * t;

                        } else {
                            ballx = ballx + ballvx * t;
                            bally = bally + ballvy * t;

                        }

                    } else {
                        ball2x += ball2vx * LastTimeStep * -.1;
                        ball2y += ball2vy * LastTimeStep * -.1;
                        ballx += ballvx * LastTimeStep * -.1;
                        bally += ballvy * LastTimeStep * -.1;
                    }


                    double box4Right = paddle2.getX() + screenWidth / 5;
                    double box4Left = paddle2.getX();
                    double box4Top = paddle2.getY();
                    double box4Bottom = paddle2.getY() + screenHeight / 30;

                    if (
                            (
                                    ((box4Left >= box2Left) && (box4Left <= box2Right))
                                            || ((box4Right >= box2Left) && (box4Right <= box2Right))
                                            || ((box2Left >= box4Left) && (box2Left <= box4Right))
                                            || ((box2Right >= box4Left) && (box2Right <= box4Right))
                            )
                                    &&
                                    (
                                            (box4Top >= box2Top) && (box4Top <= box2Bottom)
                                                    || ((box4Bottom >= box2Top) && (box4Bottom <= box2Bottom))
                                                    || ((box2Top >= box4Top) && (box2Top <= box4Bottom))
                                                    || ((box2Bottom >= box4Top) && (box2Bottom <= box4Bottom))
                                    )
                            ) {
                        bally = paddle2y + (screenHeight /30);
                        smack = true;



                        ballx = ballx - ballvx * LastTimeStep;
                        bally = bally - ballvy * LastTimeStep;

                        double Centerx = ballx + (screenWidth / 40);
                        double Centery = bally + (screenHeight / 60);

                        double boxCx = paddle2.getX() + screenWidth / 10;
                        double boxCy = paddle2.getY() + screenHeight / 60;


                        double distanceX = Math.abs(Centerx - boxCx);
                        double distanceY = Math.abs(Centery - boxCy);

                        double Tx = distanceX / Math.abs(ballvx);
                        double Ty = distanceY / Math.abs(ballvy);

                        double radius = (paddle2.getX() + screenWidth / 5) - (ballx + screenWidth / 20);
                        double Bv = Math.pow(Math.pow(ballvx, 2) + Math.pow(ballvy, 2), .5);
                        double ballspeed = Math.abs(Bv);
                        double angle = (-(2 * radius * Math.PI) / (22));
                        if (angle > 45) {angle=45;}

                        if (angle < -45) {angle=-45;}
                        double VxNew = ballspeed * Math.cos(angle);
                        double VyNew = ballspeed * Math.sin(angle);

                        ballvx = VxNew;
                        ballvy = VyNew;
                        ballvx = ballvx + (ballvx/8);
                        ballvy = ballvy + (ballvy/8);

                        if (ballvx>1100)
                        {
                            ballvx=1100;
                        }
                        if (ballvy>1100)
                        {
                            ballvy=1100;
                        }

                        if (ballvx<-1100)
                        {
                            ballvx=-1100;
                        }
                        if (ballvy<-1100)
                        {
                            ballvy=-1100;
                        }


                        //ballx = ballx + ballvx * t;
                        //bally = bally + ballvy * t;


                        if (Tx > Ty) {
                            ballx = ballx + ballvx * t;
                            bally = bally + ballvy * LastTimeStep;


                        } else if (Tx < Ty) {
                            ballx = ballx + ballvx * LastTimeStep;
                            bally = bally + ballvy * t;

                        } else {
                            ballx = ballx + ballvx * t;
                            bally = bally + ballvy * t;

                        }

                    } else {
                        ballx += ballvx * LastTimeStep * -.1;
                        bally += ballvy * LastTimeStep * -.1;


                    }


                    if (
                            (
                                    ((box4Left >= box5Left) && (box4Left <= box5Right))
                                            || ((box4Right >= box5Left) && (box4Right <= box5Right))
                                            || ((box5Left >= box4Left) && (box5Left <= box4Right))
                                            || ((box5Right >= box4Left) && (box5Right <= box4Right))
                            )
                                    &&
                                    (
                                            (box4Top >= box5Top) && (box4Top <= box5Bottom)
                                                    || ((box4Bottom >= box5Top) && (box4Bottom <= box5Bottom))
                                                    || ((box5Top >= box4Top) && (box5Top <= box4Bottom))
                                                    || ((box5Bottom >= box4Top) && (box5Bottom <= box4Bottom))
                                    )
                            ) {
                        ball2y = paddle2y + (screenHeight /30);
                        smack = true;


                        ball2x = ball2x - ball2vx * LastTimeStep;
                        ball2y = ball2y - ball2vy * LastTimeStep;

                        double Centerx = ball2x + (screenWidth / 40);
                        double Centery = ball2y + (screenHeight / 60);

                        double boxCx = paddle2.getX() + screenWidth / 10;
                        double boxCy = paddle2.getY() + screenHeight / 60;


                        double distanceX = Math.abs(Centerx - boxCx);
                        double distanceY = Math.abs(Centery - boxCy);

                        double Tx = distanceX / Math.abs(ball2vx);
                        double Ty = distanceY / Math.abs(ball2vy);

                        double radius = (paddle2.getX() + screenWidth / 5) - (ball2x + screenWidth / 20);
                        double Bv = Math.pow(Math.pow(ball2vx, 2) + Math.pow(ball2vy, 2), .5);
                        double ballspeed = Math.abs(Bv);
                        double angle = ((2 * radius * Math.PI) / (22));
                        if (angle > 45) {angle=45;}

                        if (angle < -45) {angle=-45;}
                        double VxNew = ballspeed * Math.cos(angle);
                        double VyNew = ballspeed * Math.sin(angle);

                        ball2vx = VxNew;
                        ball2vy = VyNew;

                        //ball2x = ball2x + ball2vx * t;
                        //ball2y = ball2y + ball2vy * t;

                        ball2vx = ball2vx + (ball2vx/8);
                        ball2vy = ball2vy + (ball2vy/8);

                        if (ball2vx>1100)
                        {
                            ball2vx=1100;
                        }
                        if (ball2vy>1100)
                        {
                            ball2vy=1100;
                        }

                        if (ball2vx<-1100)
                        {
                            ball2vx=-1100;
                        }
                        if (ball2vy<-1100)

                        {
                            ball2vy=-1100;
                        }



                        if (Tx > Ty) {
                            ball2x = ball2x + ball2vx * t;
                            ball2y = ball2y + ball2vy * LastTimeStep;


                        } else if (Tx < Ty) {
                            ball2x = ball2x + ball2vx * LastTimeStep;
                            ball2y = ball2y + ball2vy * t;

                        } else {
                            ball2x = ball2x + ball2vx * t;
                            ball2y = ball2y + ball2vy * t;

                        }

                    } else {
                        ball2x += ball2vx * LastTimeStep * -.1;
                        ball2y += ball2vy * LastTimeStep * -.1;


                    }


                    //blocks
                    for (int i = 0; i < block.size(); i++) {

                        Blocks currentblocks = block.get(i);

                        double box3Left = block.get(i).x;
                        double box3Right = block.get(i).x + screenWidth / 10;
                        double box3Top = block.get(i).y;
                        double box3Bottom = block.get(i).y + screenHeight / 30;


                        if (
                                (
                                        ((box3Left >= box2Left) && (box3Left <= box2Right))
                                                || ((box3Right >= box2Left) && (box3Right <= box2Right))
                                                || ((box2Left >= box3Left) && (box2Left <= box3Right))
                                                || ((box2Right >= box3Left) && (box2Right <= box3Right))
                                )
                                        &&
                                        (
                                                (box3Top >= box2Top) && (box3Top <= box2Bottom)
                                                        || ((box3Bottom >= box2Top) && (box3Bottom <= box2Bottom))
                                                        || ((box2Top >= box3Top) && (box2Top <= box3Bottom))
                                                        || ((box2Bottom >= box3Top) && (box2Bottom <= box3Bottom))
                                        )
                                ) {

                            //System.out.println("TRUE");

                            ballx = ballx - ballvx * LastTimeStep;
                            bally = bally - ballvy * LastTimeStep;

                            double Centerx = ballx + (screenWidth / 40);
                            double Centery = bally + (screenHeight / 60);

                            double boxCx = block.get(i).x + screenWidth / 20;
                            double boxCy = block.get(i).y + screenHeight / 60;


                            double distanceX = Math.abs(Centerx - boxCx);
                            double distanceY = Math.abs(Centery - boxCy);

                            double Tx = distanceX / Math.abs(ballvx);
                            double Ty = distanceY / Math.abs(ballvy);


                            if (Tx > Ty) {
                                ballx = ballx + ballvx * t;
                                bally = bally + ballvy * LastTimeStep;


                            } else if (Tx < Ty) {
                                ballx = ballx + ballvx * LastTimeStep;
                                bally = bally + ballvy * t;

                            } else {
                                ballx = ballx + ballvx * t;
                                bally = bally + ballvy * t;

                            }

                            double radius = (block.get(i).x + screenWidth / 5) - (ballx + screenWidth / 20);
                            double Bv = Math.pow(Math.pow(ballvx, 2) + Math.pow(ballvy, 2), .5);
                            double ballspeed = Math.abs(Bv);
                            double angle = (-(2 * radius * Math.PI) / 22.5);
                            if (angle > 45) {angle=45;}

                            if (angle < -45) {angle=-45;}
                            double VxNew = ballspeed * Math.cos(angle);
                            double VyNew = ballspeed * Math.sin(angle);

                            ballvx = VxNew;
                            ballvy = VyNew;

                            ballx = ballx + ballvx * t;
                            bally = bally + ballvy * t;

                            //think of more majestic way of getting rid of node

                            //currentBlock(layout);

                            removeBlock = true;
                            if (!DeadBlock.contains(currentblocks)) {
                                DeadBlock.add(currentblocks);

                            }
                            hit = true;

                        }

                        if (
                                (
                                        ((box3Left >= box5Left) && (box3Left <= box5Right))
                                                || ((box3Right >= box5Left) && (box3Right <= box5Right))
                                                || ((box5Left >= box3Left) && (box5Left <= box3Right))
                                                || ((box5Right >= box3Left) && (box5Right <= box3Right))
                                )
                                        &&
                                        (
                                                (box3Top >= box5Top) && (box3Top <= box5Bottom)
                                                        || ((box3Bottom >= box5Top) && (box3Bottom <= box5Bottom))
                                                        || ((box5Top >= box3Top) && (box5Top <= box3Bottom))
                                                        || ((box5Bottom >= box3Top) && (box5Bottom <= box3Bottom))
                                        )
                                ) {

                          //  System.out.println("TRUE");



                            ball2x = ball2x - ball2vx * LastTimeStep;
                            ball2y = ball2y - ball2vy * LastTimeStep;

                            double Centerx = ball2x + (screenWidth / 40);
                            double Centery = ball2y + (screenHeight / 60);

                            double boxCx = block.get(i).x + screenWidth / 20;
                            double boxCy = block.get(i).y + screenHeight / 60;


                            double distanceX = Math.abs(Centerx - boxCx);
                            double distanceY = Math.abs(Centery - boxCy);

                            double Tx = distanceX / Math.abs(ball2vx);
                            double Ty = distanceY / Math.abs(ball2vy);


                            if (Tx > Ty) {
                                ball2x = ball2x + ball2vx * t;
                                ball2y = ball2y + ball2vy * LastTimeStep;


                            } else if (Tx < Ty) {
                                ball2x = ball2x + ball2vx * LastTimeStep;
                                ball2y = ball2y + ball2vy * t;

                            } else {
                                ball2x = ball2x + ball2vx * t;
                                ball2y = ball2y + ball2vy * t;

                            }

                            double radius = (block.get(i).x + screenWidth / 5) - (ballx + screenWidth / 20);
                            double Bv = Math.pow(Math.pow(ball2vx, 2) + Math.pow(ball2vy, 2), .5);
                            double ballspeed = Math.abs(Bv);
                            double angle = -((2 * radius * Math.PI) / 22.5);
                            if (angle > 45) {angle=45;}

                            if (angle < -45) {angle=-45;}
                            double VxNew = ballspeed * Math.cos(angle);
                            double VyNew = ballspeed * Math.sin(angle);

                            ball2vx = VxNew;
                            ball2vy = VyNew;

                            ball2x = ball2x + ball2vx * t;
                            ball2y = ball2y + ball2vy * t;

                            //think of more majestic way of getting rid of node

                            //currentBlock(layout);

                            removeBlock = true;

                            if (!DeadBlock.contains(currentblocks)) {
                                DeadBlock.add(currentblocks);

                            }
                            hit = true;

                        }
                    }
                    PreviousSysTime = currentSysTime;

                    LastTimeStep = t;

                    runOnUiThread(frameUpdate2);
                }
            };

            PreviousSysTime = System.nanoTime();
            timer = new Timer();
            timer.schedule(frameTask, 100, 16);


    }

    /*public void method(View v){

        startGame = !startGame;


        }*/


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

    void makeBlock(float x, float y) {

        Blocks newwall = new Blocks(this, x, y);
        block.add(newwall);

    }

    void updateBlock(){

        for (int i = 0 ; i < block.size(); i++){

            block.get(i).imageView.setY(block.get(i).y);
            block.get(i).imageView.setX(block.get(i).x);

        }
    }

    void ResBlock(){

if (delBlock.size() > 0) {
    long r = Math.round(Math.random() * (delBlock.size() - 1));

    r = Math.max(r, 0);

    makeBlock(delBlock.get((int) r).x, delBlock.get((int) r).y);

    delBlock.remove((int) r);
}
    }

}
