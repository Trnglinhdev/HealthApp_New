package com.example.proj1_etr65856;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Jokes extends AppCompatActivity {
    //declare variables
    private ImageView jokeImageView;
    private int[] imageArray = {
            R.drawable.figjoke_1,
            R.drawable.figjoke_3,
            R.drawable.figjoke_6,
            R.drawable.figjoke_7,
            R.drawable.figjoke_8
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jokes);
        //reference imageview
        jokeImageView = findViewById(R.id.jokeImageView);

        ImageButton btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Jokes.this, MainActivity2.class));
            }
        });
    }
    //method to show random jokes
    private void showJokeImage(){
        Random random = new Random();
        int randomIndex = random.nextInt(imageArray.length);
        jokeImageView.setImageResource(imageArray[randomIndex]);
    }


}