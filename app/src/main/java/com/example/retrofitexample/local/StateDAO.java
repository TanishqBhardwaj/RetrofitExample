package com.example.retrofitexample.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface StateDAO {

    @Insert
    void insert(StateEntity stateEntity);

    @Update
    void update(StateEntity stateEntity);

    @Query("SELECT * FROM state_table")
    List<StateEntity> getAllStates();
}
