package com.example.nasa.learning.users;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nasa.R;

import java.util.List;

public class FriendsRvAdapter extends RecyclerView.Adapter<FriendsRvAdapter.FriendVH> {

    private List<User> friendsToAdapt;

    public void setData(List<User> friendsToAdapt){
        this.friendsToAdapt = friendsToAdapt;
    }

    @NonNull
    @Override
    public FriendVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.user, parent, false);

        FriendVH friendVH = new FriendVH(view);
        return friendVH;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendVH holder, int position) {
        final User friendAtPosition = friendsToAdapt.get(position);
        holder.bind(friendAtPosition);
    }

    @Override
    public int getItemCount() {
        int a ;
        if(friendsToAdapt != null && !friendsToAdapt.isEmpty()) {
            a = friendsToAdapt.size();
        }
        else {
            a = 0;
        }
        return a;
    }
    public class FriendVH extends RecyclerView.ViewHolder {
        public View view;
        public TextView fullName;
        public TextView name;
        public TextView highscore;

        public FriendVH(@NonNull View itemView) {
            super(itemView);
            this.fullName = itemView.findViewById(R.id.full_name);
            this.name = itemView.findViewById(R.id.username);
            this.highscore = itemView.findViewById(R.id.high_score);
        }

        public void bind(final User user){
            fullName.setText(user.getFullName());
            name.setText("@" +user.getUsername());
            highscore.setText("Highscore: " + user.getHighscore());
        }
    }
}
