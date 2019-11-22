package com.example.nasa.learning.flashcard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nasa.R;
import com.example.nasa.learning.flashcard.Database;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FlashcardActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcard_collection);
        recyclerView = findViewById(R.id.flashcard_rv);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final FlashcardCollectionAdapter flashcardCollectionAdapter = new FlashcardCollectionAdapter();
        recyclerView.setAdapter(flashcardCollectionAdapter);

        final RequestQueue requestQueue =  Volley.newRequestQueue(this);
        //URL used to get data from the api
        String url = "https://api.le-systeme-solaire.net/rest/bodies?data=englishName,isPlanet,density,gravity,discoveredBy,discoveryDate";


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                SolarSystem solarSystem = gson.fromJson(response, SolarSystem.class);
                List<Bodies> solarSBodies = solarSystem.getBodies();
                //saving data into the database
                Database db = Database.getInstance(getApplicationContext());
                db.ssBodiesDao().insertAll(solarSBodies);

                List<Bodies> listOfBodiesFromDatabase = db.ssBodiesDao().getAll();

                ArrayList<Bodies> bodiesFromDatabase = new ArrayList<Bodies>(listOfBodiesFromDatabase);

                flashcardCollectionAdapter.setData(bodiesFromDatabase);
                flashcardCollectionAdapter.notifyDataSetChanged();
                requestQueue.stop();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //if tasks completed for response are unsuccessful
                System.out.println("Failed");
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);
        requestQueue.add(stringRequest);
    }
}
