package com.example.nasa.learning.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nasa.R;
import com.example.nasa.learning.flashcard.Database;

public class FlashcardDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_card_side2);

        Intent intent = getIntent();
        final String intentID = intent.getStringExtra("name");
        Database db = Database.getInstance(this);
        Bodies bodies = db.ssBodiesDao().findBodyByName(intentID);

        TextView name = findViewById(R.id.flashcard_name_detailed);
        TextView desc = findViewById(R.id.flashcard_desc_detailed);
        TextView density = findViewById(R.id.flashcard_density);
        TextView gravity = findViewById(R.id.flashcard_gravity);
        TextView disBy = findViewById(R.id.flashcard_discovered_by);
        TextView disDate = findViewById(R.id.flashcard_discovery_date);

        name.setText(intentID);
        if(bodies.isPlanet() == true){
            desc.setText("Type of body in our solar system: Planet");
        }
        else{
            desc.setText("Type of body in our solar system: Moon");
        }
        density.setText("Density in g.cm3: " + bodies.getDensity());
        gravity.setText("Gravity in m.s-2: " + bodies.getGravity());
        disBy.setText("Discovered by: " + bodies.getDiscoveredBy());
        disDate.setText("Discovery dated: " + bodies.getDiscoveryDate());
    }

}
