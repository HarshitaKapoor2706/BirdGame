package com.example.birdgame;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.birdgame.databinding.ActivityMainBinding;

public class GameActivity extends AppCompatActivity {

    ActivityMainBinding mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mainActivity= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainActivity.getRoot());

    }
}