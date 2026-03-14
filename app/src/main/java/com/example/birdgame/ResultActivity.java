package com.example.birdgame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.birdgame.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    ActivityResultBinding resultbinding;
    int score;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultbinding = ActivityResultBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(resultbinding.getRoot());
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
                builder.setTitle("Help the innocent Bird");
                builder.setMessage("Are you sure you want to quit the game?");
                builder.setCancelable(false);
                builder.setNegativeButton("Quit Game", (dialog, which) -> {
                    finishAffinity();
                });

                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }

                });
                builder.create().show();
            }
        });

                score = getIntent().getIntExtra("score", 0);
        resultbinding.score.setText("Your Score : " + score);
        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int highestScore = sharedPreferences.getInt("highest score", 0);
        if (score >= 200) {
            resultbinding.result.setText("You won the game!!");
            resultbinding.highestscore.setText("Highest Score :" + score);
            sharedPreferences.edit().putInt("highest score", score).apply();
        } else if (score >= highestScore) {
            highestScore=score;
            resultbinding.result.setText("Sorry! you lost the game");
            resultbinding.highestscore.setText("Highest Score :" + score);
            sharedPreferences.edit().putInt("highest score", highestScore).apply();


        } else {
            resultbinding.result.setText("Sorry! you lost the game");
            resultbinding.highestscore.setText("Highest Score :" + highestScore);
            sharedPreferences.edit().putInt("highest score", highestScore).apply();
        }
        resultbinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        });


    }



}