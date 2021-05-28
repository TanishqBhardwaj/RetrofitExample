package com.example.retrofitexample.local;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {StateEntity.class}, version = 1)
public abstract class StateDatabase extends RoomDatabase {

    private static StateDatabase instance;

    public abstract StateDAO stateDAO();

    public static synchronized StateDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context, StateDatabase.class, "state_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
