package com.example.nasa.learning.dashboard;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.nasa.R;
import com.example.nasa.learning.flashcard.FlashcardActivity;
import com.example.nasa.learning.quiz.QuizStartingActivity;
import com.example.nasa.learning.video.YouTubeActivity;


public class DashboardFragment extends Fragment {

    private Button flashcardButton;
    private Button quizButton;
    private Button videoButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        flashcardButton = view.findViewById(R.id.flashcardButton);
        quizButton = view.findViewById(R.id.quizButton);
        videoButton = view.findViewById(R.id.videoButton);

        //Flashcard onclick method if it was an activity
        flashcardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFlashcard();
            }
        });

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openQuiz();

            }
        });
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVideo();
            }
        });

        return view;
    }

    //need to fill this
    //Intent to open Flashcard learning app
    //Might not need this as the flashcard is a fragment
    public void openFlashcard() {
        Intent intent = new Intent(this.getContext(), FlashcardActivity.class);
        startActivity(intent);
    }

    //Intent to open Quiz app
    public void openQuiz() {

        Intent intent = new Intent(this.getContext(), QuizStartingActivity.class);
        startActivity(intent);
    }

    //Intent to open Video app
    public void openVideo(){

        Intent intent = new Intent(this.getContext(), YouTubeActivity.class);

        startActivity(intent);
    }

}
