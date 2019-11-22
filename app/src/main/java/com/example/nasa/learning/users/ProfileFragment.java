package com.example.nasa.learning.users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nasa.R;
import com.example.nasa.learning.flashcard.Database;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    RecyclerView recyclerView;
    TextView name;
    TextView userName;
    TextView highScore;

    public void Profile(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);
        // Inflating layout for this fragment

        recyclerView = view.findViewById(R.id.profile_friends);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        final FriendsRvAdapter friendsRvAdapter = new FriendsRvAdapter();
        recyclerView.setAdapter(friendsRvAdapter);

        populateUserDB();
        friendsRvAdapter.notifyDataSetChanged();
        Database db = Database.getInstance(getContext());
        List<User> users = db.usersDao().getAll();

        User thisUser = db.usersDao().findUserByUserName("leotmnt");

        db.usersDao().delete(thisUser);
        //deleting this user from friend list as this is their profile
        users = db.usersDao().getAll();
        friendsRvAdapter.setData(users);
        //add him back into the database
        db.usersDao().insertAll(users);

        name = view.findViewById(R.id.full_name);
        userName = view.findViewById(R.id.username);
        highScore = view.findViewById(R.id.high_score_profile);
        name.setText(thisUser.getFullName());
        userName.setText("@" + thisUser.getUsername());
        highScore.setText("Highscore: " + thisUser.getHighscore());

        return view;
    }
    public void populateUserDB() {
        //adding dummy data to user database for exampleuse , account creation to add users implemented in the future
        List<User> users = new ArrayList<>();
        User user = new User("Leonardo", "leotmnt", 5);
        User user1 = new User("Donatello","dontmnt", 4);
        User user2 = new User("Michelangelo","miketmnt", 3);
        User user3 = new User("Raphael","raphtmnt", 2);
        User user5 = new User("Test","hi", 1);
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
