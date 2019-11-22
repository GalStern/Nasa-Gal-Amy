package com.example.nasa.learning.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nasa.R;
import com.example.nasa.learning.quiz.QuizStartingActivity;

public class DashboardActivity extends AppCompatActivity {

    private Button flashcardButton;
    private Button quizButton;
    private Button videoButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        flashcardButton = findViewById(R.id.flashcardButton);
        quizButton = findViewById(R.id.quizButton);
        videoButton = findViewById(R.id.videoButton);

        flashcardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFlashcard();
            }
        });
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Here0");
                openQuiz();
                System.out.println("Here1");
            }
        });
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVideo();
            }
        });

    }

    //need to fill this
    //Intent to open Flashcard learning app
    public void openFlashcard() {

    }

    //Intent to open Quiz app
    public void openQuiz() {
        System.out.println("Here2");
        Intent intent = new Intent(DashboardActivity.this, QuizStartingActivity.class);
        startActivity(intent);
    }

    //Intent to open Video app
    public void openVideo() {

    }
}
