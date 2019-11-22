package com.example.nasa.intro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.nasa.Home;
import com.example.nasa.R;


public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }
        public void changeLayout(View view){
            Intent intent = new Intent(this, Intro2Activity.class);
            startActivity(intent);
        }

        public void skipLayout(View view){
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }

}