package com.example.nasa.learning.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class QuizDBHelp extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SpaceQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDBHelp(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //onCreate used to create table for the SpaceQuiz.db database, includes difficulty
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                //Column for table name
                QuizRecord.QuestionsTable.TBL_NAME + " ( " +
                //Column for ID
                QuizRecord.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                //Column for Question data entry
                QuizRecord.QuestionsTable.COL_QUESTION + " TEXT, " +
                //Column for First option
                QuizRecord.QuestionsTable.COL_OPTION1 + " TEXT, " +
                //Column for Second option
                QuizRecord.QuestionsTable.COL_OPTION2 + " TEXT, " +
                //Column for Third option
                QuizRecord.QuestionsTable.COL_OPTION3 + " TEXT, " +
                //Column for Actual answer
                QuizRecord.QuestionsTable.COL_ANSWER + " INTEGER, " +
                //Column for Difficulty Level
                QuizRecord.QuestionsTable.COL_DIFFICULTY + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizRecord.QuestionsTable.TBL_NAME);
        onCreate(db);
    }

    private void fillQuestionTable() {

        //EASY QUESTIONS LIST
        QuizQuestion quizQuestion1 = new QuizQuestion("What is the closest planet to the Sun?",
                "Neptune", "Mercury", "Venus", 2, QuizQuestion.DIFFICULTY_EASY);
        addQuestion(quizQuestion1);

        QuizQuestion quizQuestion2 = new QuizQuestion("What is the hottest planet in our solar system?",
                "Neptune", "Mercury", "Venus", 3, QuizQuestion.DIFFICULTY_EASY);
        addQuestion(quizQuestion2);

        QuizQuestion quizQuestion3 = new QuizQuestion("Earth is located in which galaxy?",
                "Segue 2", "The Milky Way", "Segue 1", 2, QuizQuestion.DIFFICULTY_EASY);
        addQuestion(quizQuestion3);

        QuizQuestion quizQuestion4 = new QuizQuestion("Who was the first person to walk on the moon?",
                "Neil Armstrong", "Buzz Aldrin", "Michael Collins", 1, QuizQuestion.DIFFICULTY_EASY);
        addQuestion(quizQuestion4);

        QuizQuestion quizQuestion5 = new QuizQuestion("What is the name of the 2nd biggest planet in our solar system?",
                "Uranus", "Jupiter", "Saturn", 3, QuizQuestion.DIFFICULTY_EASY);
        addQuestion(quizQuestion5);

        //HARD QUESTIONS LIST
        QuizQuestion quizQuestion6 = new QuizQuestion("What planet is known as the red planet?",
                "Neptune","Mars", "Saturn", 2, QuizQuestion.DIFFICULTY_HARD);
        addQuestion(quizQuestion6);

        QuizQuestion quizQuestion7 = new QuizQuestion("What is the name of the force holding us to the Earth?", "Dark Matter","Gravity", "Rock", 2, QuizQuestion.DIFFICULTY_HARD);
        addQuestion(quizQuestion7);

        QuizQuestion quizQuestion8 = new QuizQuestion("What planet is famous for its big red spot on it?",
                "Jupiter", "Saturn", "Uranus", 1, QuizQuestion.DIFFICULTY_HARD);
        addQuestion(quizQuestion8);

        QuizQuestion quizQuestion9 = new QuizQuestion("Olympus Mons is a large volcanic mountain on which planet?",
                "Mars", "Saturn", "Uranus", 1, QuizQuestion.DIFFICULTY_HARD);
        addQuestion(quizQuestion9);

        QuizQuestion quizQuestion10 = new QuizQuestion("What is the name of Saturn’s largest moon?",
                "Enceladus", "Titan", "Tethys", 2, QuizQuestion.DIFFICULTY_HARD);
        addQuestion(quizQuestion10);

    }

    private void addQuestion(QuizQuestion quizQuestion) {
        ContentValues cv = new ContentValues();
        cv.put(QuizRecord.QuestionsTable.COL_QUESTION, quizQuestion.getQuestion());
        cv.put(QuizRecord.QuestionsTable.COL_OPTION1, quizQuestion.getOption1());
        cv.put(QuizRecord.QuestionsTable.COL_OPTION2, quizQuestion.getOption2());
        cv.put(QuizRecord.QuestionsTable.COL_OPTION3, quizQuestion.getOption3());
        cv.put(QuizRecord.QuestionsTable.COL_ANSWER, quizQuestion.getAnswerNr());
        cv.put(QuizRecord.QuestionsTable.COL_DIFFICULTY, quizQuestion.getDifficulty());
        db.insert(QuizRecord.QuestionsTable.TBL_NAME, null, cv);
    }

    public ArrayList<QuizQuestion> getQuestions(String difficulty) {
        ArrayList<QuizQuestion> quizQuestionList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuizRecord.QuestionsTable.TBL_NAME +
                " WHERE " + QuizRecord.QuestionsTable.COL_DIFFICULTY + " = ?", selectionArgs);

        if (c.moveToFirst()) {
            do {
                QuizQuestion quizQuestion = new QuizQuestion();
                quizQuestion.setQuestion(c.getString(c.getColumnIndex(QuizRecord.QuestionsTable.COL_QUESTION)));
                quizQuestion.setOption1(c.getString(c.getColumnIndex(QuizRecord.QuestionsTable.COL_OPTION1)));
                quizQuestion.setOption2(c.getString(c.getColumnIndex(QuizRecord.QuestionsTable.COL_OPTION2)));
                quizQuestion.setOption3(c.getString(c.getColumnIndex(QuizRecord.QuestionsTable.COL_OPTION3)));
                quizQuestion.setAnswerNr(c.getInt(c.getColumnIndex(QuizRecord.QuestionsTable.COL_ANSWER)));
                quizQuestion.setDifficulty(c.getString(c.getColumnIndex(QuizRecord.QuestionsTable.COL_DIFFICULTY)));
                quizQuestionList.add(quizQuestion);
            } while (c.moveToNext());
        }

        c.close();
        return quizQuestionList;
    }
}