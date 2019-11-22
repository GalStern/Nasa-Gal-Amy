package com.example.nasa.learning.users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nasa.R;
import com.example.nasa.learning.flashcard.Database;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardFragment extends Fragment {
    RecyclerView recyclerView;

    public void Leaderboard(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.leaderboard, container, false);
        // Inflating layout for this fragment

        recyclerView = view.findViewById(R.id.leaderboard_rv);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        final FriendsRvAdapter friendsRvAdapter = new FriendsRvAdapter();
        recyclerView.setAdapter(friendsRvAdapter);

        populateUserDB();
        Database db = Database.getInstance(getContext());
        List<User> users = db.usersDao().getAll();

        friendsRvAdapter.setData(users);
        friendsRvAdapter.notifyDataSetChanged();

        return view;
    }
    public void populateUserDB() {
        //adding dummy data to user database for example use, account creation to add users implemented in the future
        List<User> users = new ArrayList<>();
        User user = new User("Leonardo", "leotmnt", 5);
        User user1 = new User("Donatello","dontmnt", 4);
        User user2 = new User("Michelangelo","miketmnt", 3);
        User user3 = new User("Raphael","raphtmnt", 2);
        User user5 = new User("Test","hi", 2);
        User user6 = new User("Dan Devito","DD", 1);
        User user7 = new User("Shark boy","IluvSharks", 1);
        User user8 = new User("Lava Girl","LavaG1rl", 1);
        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        Database db = Database.getInstance(getContext());
        db.usersDao().insertAll(users);
    }
}
