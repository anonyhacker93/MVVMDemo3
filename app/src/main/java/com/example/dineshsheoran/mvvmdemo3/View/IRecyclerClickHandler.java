package com.example.dineshsheoran.mvvmdemo3.View;

import com.example.dineshsheoran.mvvmdemo3.Model.Person;

// Created by Dinesh Kumar on 4/29/2019
public interface IRecyclerClickHandler {
void onClick(Person person);
void onLongClick(Person person);
}
