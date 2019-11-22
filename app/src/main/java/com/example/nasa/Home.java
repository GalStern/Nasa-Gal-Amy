package com.example.nasa;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nasa.learning.dashboard.DashboardFragment;
import com.example.nasa.learning.users.LeaderboardFragment;
import com.example.nasa.learning.users.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        // sets slot to dashboard to begin with
        Fragment fragment = new DashboardFragment();
        swapFragment(fragment);
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //setting up bottom navigation bar, each tab changing
                if (menuItem.getItemId() == R.id.home) {
                    Fragment fragment = new DashboardFragment();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.leaderBoard) {
                    Fragment fragment = new LeaderboardFragment();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.profile) {
                    Fragment fragment = new ProfileFragment();
                    swapFragment(fragment);
                    return true;
                }
                return false;
            }
        });
    }

    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }
}
