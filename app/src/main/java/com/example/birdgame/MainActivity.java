
package com.example.birdgame;

import android.content.Intent;
import android.health.connect.datatypes.units.Volume;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.birdgame.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    Animation animation;
    MediaPlayer mediaPlayer;
    Boolean status=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.scaleanim);
mainBinding.bird.setAnimation(animation);
        mainBinding.enemy1.setAnimation(animation);
        mainBinding.enemy2.setAnimation(animation);
        mainBinding.enemy3.setAnimation(animation);
        mainBinding.coin.setAnimation(animation);



    }

    @Override
    protected void onResume() {
        super.onResume();

    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.song);
    mediaPlayer.start();

    mainBinding.volume.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
if(!status){
    //ie status is true the vol is muted
    mediaPlayer.setVolume(0,0);
    mainBinding.volume.setImageResource(R.drawable.mute);
    status=true;
}
else {
    mediaPlayer.setVolume(1,1);
    mainBinding.volume.setImageResource(R.drawable.unmute);
    status=false;
}
                                              }
                                          });
        mainBinding.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mainBinding.volume.setImageResource(R.drawable.unmute);
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });




}}