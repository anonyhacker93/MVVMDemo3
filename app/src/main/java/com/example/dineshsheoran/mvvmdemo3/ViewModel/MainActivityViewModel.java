package com.example.dineshsheoran.mvvmdemo3.ViewModel;

import android.app.Application;
import android.content.Context;

import com.example.dineshsheoran.mvvmdemo3.Model.Person;
import com.example.dineshsheoran.mvvmdemo3.Repositories.PersonRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private LiveData<List<Person>> _personLiveData;
    private PersonRepository _personRepository;


    public void init(Context application) {
        if (_personLiveData != null) {
            return;
        }
        _personRepository = PersonRepository.getInstance(application);

        _personLiveData = _personRepository.getPersonDetails();
    }

    public LiveData<List<Person>> getPersonDetails() {
        return _personLiveData;
    }

    public void addNewData() {//Invoked from Button
        _personRepository.addPerson(new Person("AAA", "BBBB"));
    }

}
