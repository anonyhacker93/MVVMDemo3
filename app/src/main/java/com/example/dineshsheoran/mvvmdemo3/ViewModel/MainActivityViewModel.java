package com.example.dineshsheoran.mvvmdemo3.ViewModel;

import android.app.Application;

import com.example.dineshsheoran.mvvmdemo3.Model.Person;
import com.example.dineshsheoran.mvvmdemo3.Repositories.PersonRepository;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
private MutableLiveData<ArrayList<Person>> _personDetails;
private PersonRepository _personRepository;
private MutableLiveData<String> _mssg;

    public MutableLiveData<ArrayList<Person>> getPersonDetails() {
        return _personDetails;
    }

    public void init(Application application){
        if(_personDetails!=null){
            return;
        }
        _mssg = new MutableLiveData<>();
        _personDetails = new MutableLiveData<>();
        _personRepository = PersonRepository.getInstance(application);
        _personDetails = (MutableLiveData)_personRepository.getPersonDetails();

    }

    public void addNewData(){



        _mssg.postValue("Heyya");
    }

    public MutableLiveData<String> getMessage(){
        return _mssg;
    }
}
