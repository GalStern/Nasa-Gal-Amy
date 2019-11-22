package com.example.nasa.intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nasa.R;
import com.example.nasa.learning.dashboard.DashboardActivity;

public class Intro2Activity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro2);
    }
    public void changeLayout(View view){
        Intent intent = new Intent(this, Intro3Activity.class);
        startActivity(intent);
    }

    public void skipLayout(View view){
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

}