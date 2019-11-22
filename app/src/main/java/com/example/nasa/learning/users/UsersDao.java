package com.example.nasa.learning.users;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsersDao {
    @Query("SELECT * FROM user ORDER BY highscore DESC")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE username = :username")
    User findUserByUserName(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Delete
    void delete(User user);
}
