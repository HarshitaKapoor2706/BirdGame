package com.example.birdgame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.birdgame.databinding.ActivityGameBinding;
import com.example.birdgame.databinding.ActivityMainBinding;

public class GameActivity extends AppCompatActivity {

    ActivityGameBinding gameBinding;

    ConstraintLayout constraintLayout;
    boolean touchcontrol = false;
    boolean begincontrol = false;
    Runnable runnable,runnable2;
    Handler handler,handler2;
    //positions
    int birdX, enemy1x , enemy2x,enemy3x, coin1x, coin2x;
    int birdY,enemy1y,enemy2y,enemy3y,coin1y,coin2y;
    //dimensions of screen
    int ScreenWidth;
    int ScreenHeight;
    int right=3;
    int score = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameBinding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(gameBinding.getRoot());
        constraintLayout=gameBinding.getRoot();
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gameBinding.startinfo.setVisibility(View.INVISIBLE);



                if (!begincontrol) {
                    begincontrol = true;

                    //determine widthofscrenn
                    ScreenWidth= (int) constraintLayout.getWidth();
                    ScreenHeight=(int) constraintLayout.getHeight();

                    birdX=(int) gameBinding.imageViewbird.getX();
                    birdY=(int) gameBinding.imageViewbird.getY();


                    handler = new Handler();
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            collisioncontrol();
                            enemycontrol();
                            movetobird();
                            handler.postDelayed(runnable, 20);

                        }
                    };
                    handler.post(runnable);


                } else {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        touchcontrol = true;
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        touchcontrol = false;
                    }

                }


                return true;
            }
        });
    }
//method to move the bird ten call this in run method
    public void movetobird(){
//when we touch screen bird moves up itherwise it moves down
        if(touchcontrol){
            birdY=birdY-(ScreenHeight/50);
        }
        else{
            birdY=birdY+(ScreenHeight/50);
        }
        if(birdY<=0){
            birdY=0;
        }
        if(birdY>=(ScreenHeight- gameBinding.imageViewbird.getHeight()))
        {
            birdY=(ScreenHeight-gameBinding.imageViewbird.getHeight());
        }
        gameBinding.imageViewbird.setY(birdY);


    }
    public void enemycontrol(){
        gameBinding.imageView6.setVisibility(View.VISIBLE);
        gameBinding.imageView5.setVisibility(View.VISIBLE);
        gameBinding.imageView4.setVisibility(View.VISIBLE);
        gameBinding.coin1.setVisibility(View.VISIBLE);
        gameBinding.coin2.setVisibility(View.VISIBLE);

        enemy1x=enemy1x - (ScreenWidth/150);
        if(enemy1x<0){
            enemy1x=ScreenWidth+200;
            enemy1y =(int) Math.floor(Math.random()*ScreenHeight);

            //preventring char to go off the screen
            if(enemy1y<=0){
                enemy1y=0;
            }
            if(enemy1y>=(ScreenHeight- gameBinding.imageView6.getHeight()))
            {
                enemy1y=(ScreenHeight-gameBinding.imageView6.getHeight());
            }
        }
        gameBinding.imageView6.setX(enemy1x);
        gameBinding.imageView6.setY(enemy1y);

        enemy2x=enemy2x - (ScreenWidth/140);
        if(enemy2x<0){
            enemy2x=ScreenWidth+200;
            enemy2y =(int) Math.floor(Math.random()*ScreenHeight);

            //preventring char to go off the screen
            if(enemy2y<=0){
                enemy2y=0;
            }
            if(enemy2y>=(ScreenHeight- gameBinding.imageView4.getHeight()))
            {
                enemy2y=(ScreenHeight-gameBinding.imageView4.getHeight());
            }
        }
        gameBinding.imageView4.setX(enemy2x);
        gameBinding.imageView4.setY(enemy2y);

        enemy3x=enemy3x - (ScreenWidth/130);
        if(enemy3x<0){
            enemy3x=ScreenWidth+200;
            enemy3y =(int) Math.floor(Math.random()*ScreenHeight);

            //preventring char to go off the screen
            if(enemy3y<=0){
                enemy3y=0;
            }
            if(enemy3y>=(ScreenHeight- gameBinding.imageView5.getHeight()))
            {
                enemy3y=(ScreenHeight-gameBinding.imageView5.getHeight());
            }
        }
        gameBinding.imageView5.setX(enemy3x);
        gameBinding.imageView5.setY(enemy3y);

        coin1x=coin1x - (ScreenWidth/120);
        if(coin1x<0){
            coin1x=ScreenWidth+200;
            coin1y =(int) Math.floor(Math.random()*ScreenHeight);

            //preventring char to go off the screen
            if(coin1y<=0){
                coin1y=0;
            }
            if(coin1y>=(ScreenHeight- gameBinding.coin1.getHeight()))
            {
                coin1y=(ScreenHeight-gameBinding.coin1.getHeight());
            }
        }
        gameBinding.coin1.setX(coin1x);
        gameBinding.coin1.setY(coin1y);

        coin2x=coin2x - (ScreenWidth/110);
        if(coin2x<0){
            coin2x=ScreenWidth+200;
            coin2y =(int) Math.floor(Math.random()*ScreenHeight);

            //preventring char to go off the screen
            if(coin2y<=0){
                coin2y=0;
            }
            if(coin2y>=(ScreenHeight- gameBinding.coin2.getHeight()))
            {
                coin2y=(ScreenHeight-gameBinding.coin2.getHeight());
            }
        }
        gameBinding.coin2.setX(coin2x);
        gameBinding.coin2.setY(coin2y);



    }
    public void collisioncontrol(){

        //setting centre pt of enemy one char
        int centreenemy1x = enemy1x + gameBinding.imageView6.getWidth()/2;
        int centreenemy1y = enemy1y+ gameBinding.imageView6.getHeight()/2;

//checks for collissison
        if(centreenemy1x >=birdX
        && centreenemy1x <= birdX + (gameBinding.imageViewbird.getWidth())
        && centreenemy1y >= birdY
        && centreenemy1y <= birdY +  (gameBinding.imageViewbird.getHeight())){
enemy1x= ScreenWidth+200;
right--;
        }
        int centreenemy2x = enemy2x + gameBinding.imageView4.getWidth()/2;
        int centreenemy2y = enemy2y+ gameBinding.imageView4.getHeight()/2;

//checks for collissison
        if(centreenemy2x >=birdX
                && centreenemy2x <= birdX + (gameBinding.imageViewbird.getWidth())
                && centreenemy2y >= birdY
                && centreenemy2y <= birdY +  (gameBinding.imageViewbird.getHeight())){
            enemy2x= ScreenWidth+200;
            right--;
        }
        int centreenemy3x = enemy3x + gameBinding.imageView5.getWidth()/2;
        int centreenemy3y = enemy3y+ gameBinding.imageView5.getHeight()/2;

//checks for collissison
        if(centreenemy3x >=birdX
                && centreenemy3x <= birdX + (gameBinding.imageViewbird.getWidth())
                && centreenemy3y >= birdY
                && centreenemy3y <= birdY +  (gameBinding.imageViewbird.getHeight())){
            enemy3x= ScreenWidth+200;
            right--;
        }
        int centrecoin1x = coin1x + gameBinding.coin1.getWidth()/2;
        int centrecoin1y = coin1y+ gameBinding.coin1.getHeight()/2;

//checks for collissison
        if(centrecoin1x >=birdX
                && centrecoin1x <= birdX + (gameBinding.imageViewbird
                .getWidth())
                && centrecoin1y >= birdY
                && centrecoin1y<= birdY +  (gameBinding.imageViewbird.getHeight())){
            coin1x= ScreenWidth+200;
            score = score+10;
            gameBinding.textView2.setText("" +score);
        }
        int centrecoin2x =  coin2x+ gameBinding.coin2.getWidth()/2;
        int centrecoin2y = coin2y+ gameBinding.coin2.getHeight()/2;

//checks for collissison
        if(centrecoin2x >=birdX
                && centrecoin2x <= birdX + (gameBinding.imageViewbird.getWidth())
                && centrecoin2y >= birdY
                && centrecoin2y <= birdY +  (gameBinding.imageViewbird.getHeight())){
            coin2x= ScreenWidth+200;
            score=score+10;
            gameBinding.textView2.setText("" +score);
        }
        if(right>0 && score<200) {
            if (right == 2)
                gameBinding.imageView.setImageResource(R.drawable.grey);
            if (right == 1)
                gameBinding.imageView8.setImageResource(R.drawable.grey);

        }
            else if(score>= 200){
                handler.removeCallbacks(runnable);
                constraintLayout.setEnabled(false);
                gameBinding.startinfo.setVisibility(View.VISIBLE);
            gameBinding.startinfo.setText("CONGRATULATIONS! You won the game.");
            gameBinding.imageView6.setVisibility(View.INVISIBLE);
            gameBinding.imageView5.setVisibility(View.INVISIBLE);
            gameBinding.imageView4.setVisibility(View.INVISIBLE);
            gameBinding.coin1.setVisibility(View.INVISIBLE);
            gameBinding.coin2.setVisibility(View.INVISIBLE);
            handler2=new Handler();
            runnable2 = new Runnable() {
                @Override
                public void run() {

                    birdX = birdX + (ScreenWidth / 300);
                    gameBinding.imageViewbird.setX(birdX);
                    gameBinding.imageViewbird.setY(ScreenHeight / 2f);
                    if (birdX <= ScreenWidth) {
                        handler2.postDelayed(runnable2, 20);
                    } else {
                        handler2.removeCallbacks(runnable2);
                        Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                        intent.putExtra("score", score);
                        startActivity(intent);
                    }

                }

            };
            handler2.post(runnable2);

            } else if (right == 0) {

                handler.removeCallbacks(runnable);
            gameBinding.imageView9.setImageResource(R.drawable.grey);
            Intent intent = new Intent(GameActivity.this , ResultActivity.class);
            intent.putExtra("score",score);
            startActivity(intent);

            
        }
    }



    }
