package com.example.nasa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.nasa.intro.IntroActivity;
import com.example.nasa.learning.flashcard.Database;
import com.example.nasa.learning.users.User;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetRawData getRawData = new GetRawData();
        getRawData.execute("https://images-api.nasa.gov/search?q=apollo%2011 &description=moon%20landing&media_type=image");

        Log.d(TAG, "onCreate: ends");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        Log.d(TAG, "onCreateOptionsMenu() returned: " + true);
        return true;
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button,
//        // must specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        Log.d(TAG, "onOptionsItemSelected() returned: returned");
//        return super.onOptionsItemSelected(item);
//    }

    public void changeView(View view) {
        Intent intent = new Intent(MainActivity.this, IntroActivity.class);
        startActivity(intent);
    }
}