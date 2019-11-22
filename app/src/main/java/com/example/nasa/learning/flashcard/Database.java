package com.example.nasa.learning.flashcard;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.nasa.learning.users.User;
import com.example.nasa.learning.users.UsersDao;

//while merging versions of the project, the database updated, thus this is version 2, also needed to allow .fallbackToDestructiveMigration
//exportSchema is false as we didn't need to look into the database's schema
@androidx.room.Database(entities = {Bodies.class, User.class}, version = 2,exportSchema = false)
public abstract class Database extends RoomDatabase{
        public abstract SSBodiesDao ssBodiesDao();
        public abstract UsersDao usersDao();
        private static Database instance;
        public static Database getInstance(Context context) {

            if(instance == null) {
                instance = Room.databaseBuilder(context, Database.class, "ssBodiesDb")
                        .allowMainThreadQueries().fallbackToDestructiveMigration()
                        //     TODO: add async
                        .build();
            }
            return instance;
        }
}
