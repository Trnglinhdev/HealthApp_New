package com.example.proj1_etr65856;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Jokes extends AppCompatActivity {
    private ImageView jokeImageView;
    private int[] imageArray = {
            R.drawable.figjoke_1,
            R.drawable.figjoke_3,
            R.drawable.figjoke_6,
            R.drawable.figjoke_7,
            R.drawable.figjoke_8
    };

    private Handler handler = new Handler();
    private Runnable jokeRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jokes);

        // Reference ImageView
        jokeImageView = findViewById(R.id.jokeImageView);

        // Set up Home button to navigate back
        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(view -> startActivity(new Intent(Jokes.this, MainActivity2.class)));

        startJokeUpdates();
    }

    private void startJokeUpdates() {
        jokeRunnable = new Runnable() {
            @Override
            public void run() {
                showJokeImage();
                handler.postDelayed(this, 4500);
            }
        };
        handler.post(jokeRunnable);
    }

    private void showJokeImage() {
        Random random = new Random();
        int randomIndex = random.nextInt(imageArray.length);
        jokeImageView.setImageResource(imageArray[randomIndex]);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the handler to prevent memory leaks
        if (handler != null && jokeRunnable != null) {
            handler.removeCallbacks(jokeRunnable);
        }
    }
}
