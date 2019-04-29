package com.example.dineshsheoran.mvvmdemo3.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_table")
public class Person {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}

