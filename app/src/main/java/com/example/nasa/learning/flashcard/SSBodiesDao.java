package com.example.nasa.learning.flashcard;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SSBodiesDao {

    // queries that can be sent to database for a result

    @Query("SELECT * FROM bodies")
    List<Bodies> getAll();

    @Query("SELECT * FROM bodies WHERE name = :name")
    Bodies findBodyByName(String name);

    // ensures doubles are not inserted into the database which would cause issues
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Bodies> bodies);

    @Delete
    void delete(Bodies bodies);
}
