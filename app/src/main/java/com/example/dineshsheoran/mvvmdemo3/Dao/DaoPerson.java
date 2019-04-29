package com.example.dineshsheoran.mvvmdemo3.Dao;

import com.example.dineshsheoran.mvvmdemo3.Model.Person;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DaoPerson {

    @Insert
    void insert(Person person);

    @Delete
    void delete(Person person);

    @Update
    void update(Person person);

    @Query("Select * from person_table")
    LiveData<List<Person>> getAllDetails();
}
