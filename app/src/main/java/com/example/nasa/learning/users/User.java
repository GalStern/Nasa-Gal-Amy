package com.example.nasa.learning.users;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    private String fullName;
    @NonNull
    @PrimaryKey
    private String username;
    private int highscore;

    public User(String fullName, String username, int highscore) {
        this.fullName = fullName;
        this.username = username;
        this.highscore = highscore;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
