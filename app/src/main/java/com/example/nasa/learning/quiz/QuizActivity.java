package com.example.nasa.learning.quiz;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nasa.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";

    //Instantiating Countdown time
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    //Instantiating object components of the quiz
    private TextView Question;
    private TextView Score;
    private TextView Difficulty;
    private TextView QuestionCount;
    private TextView CountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private ArrayList<QuizQuestion> quizQuestionList;
    private int questionCounter;
    private int questionCountTotal;
    private QuizQuestion currentQuizQuestion;

    private int score;
    private boolean answered;

    //Instantiating variable for pressing back to starting_activity_quiz
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Question = findViewById(R.id.tv_question);
        Score = findViewById(R.id.tv_score);
        QuestionCount = findViewById(R.id.tv_question_count);
        Difficulty = findViewById(R.id.tv_difficulty);
        CountDown = findViewById(R.id.tv_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = CountDown.getTextColors();

        Intent intent = getIntent();

        //Allowing the user to select a difficulty
        String difficulty = intent.getStringExtra(QuizStartingActivity.EXTRA_DIFFICULTY);

        Difficulty.setText("Difficulty: " + difficulty);

        //Whether or not there is a saved instance from previously played games
        if (savedInstanceState == null) {
            QuizDBHelp dbHelper = new QuizDBHelp(this);
            quizQuestionList = dbHelper.getQuestions(difficulty);
            questionCountTotal = quizQuestionList.size();
            Collections.shuffle(quizQuestionList);

            showNextQuestion();
        } else {
            quizQuestionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = quizQuestionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuizQuestion = quizQuestionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if (!answered) {
                startCountDown();
            } else {
                updateCountDownText();
                showSolution();
            }
        }

        //onClickListener to check whether or not the user selected an option
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    //Method for what to do when a new question is shown (e.g. if current question < total questions, increment title, resent timer, and go to next question)
    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuizQuestion = quizQuestionList.get(questionCounter);

            Question.setText(currentQuizQuestion.getQuestion());
            rb1.setText(currentQuizQuestion.getOption1());
            rb2.setText(currentQuizQuestion.getOption2());
            rb3.setText(currentQuizQuestion.getOption3());

            questionCounter++;
            QuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    //Method for starting the countdown
    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    //Method for updating the countdown text
    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        CountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            CountDown.setTextColor(Color.RED);
        } else {
            CountDown.setTextColor(textColorDefaultCd);
        }
    }

    private void checkAnswer() {
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuizQuestion.getAnswerNr()) {
            score++;
            Score.setText("Score: " + score);
        }

        showSolution();
    }

    //What to do to show a solution - set incorrect answers to red and correct answer to green
    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuizQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                Question.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                Question.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                Question.setText("Answer 3 is correct");
                break;
        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }

    //Method to indicate that the quiz is complete and uses an an intent to return to starting page
    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, quizQuestionList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    //Rule implemented in order to exit the game - double click back button
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}