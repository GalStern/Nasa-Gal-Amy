package com.example.nasa.learning.quiz;

import android.provider.BaseColumns;

//Method for recording quiz data and connects to the database help file
public final class QuizRecord {

    private QuizRecord() {
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TBL_NAME = "quiz_questions";
        public static final String COL_QUESTION = "question";
        public static final String COL_OPTION1 = "option1";
        public static final String COL_OPTION2 = "option2";
        public static final String COL_OPTION3 = "option3";
        public static final String COL_ANSWER = "answer_nr";
        public static final String COL_DIFFICULTY = "difficulty";
    }
}
