package com.example.dineshsheoran.mvvmdemo3.Repositories;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.example.dineshsheoran.mvvmdemo3.Dao.DaoPerson;
import com.example.dineshsheoran.mvvmdemo3.Model.Person;
import com.example.dineshsheoran.mvvmdemo3.Model.PersonDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PersonRepository {

    private static PersonRepository _instance;
    private static PersonDatabase personDatabase;
    private static DaoPerson _daoPerson;


    public static PersonRepository getInstance(Context application) {
        if (_instance == null) {
            _instance = new PersonRepository();
            init(application);
        }
        return _instance;
    }

    private static void init(Context application) {
        personDatabase = PersonDatabase.getInstance(application);
        _daoPerson = personDatabase.getPersonDao();
    }


    public LiveData<List<Person>> getPersonDetails(){
        return _daoPerson.getAllDetails();
    }

    public void addPerson(Person person){
        new AddPersonAsync(_daoPerson).execute(person);
    }

    public void update(Person person){
     new UpdatePersonAsync(_daoPerson).execute(person);
    }

    public void delete(Person person){
        new DeletePersonAsync(_daoPerson).execute(person);
    }

    private static class  AddPersonAsync extends AsyncTask<Person,Void,Void>{
        DaoPerson daoPerson;

        AddPersonAsync(DaoPerson daoPerson){
            this.daoPerson = daoPerson;
        }
        @Override
        protected Void doInBackground(Person... persons) {
            daoPerson.insert(persons[0]);
            return null;
        }
    }

    private static class  DeletePersonAsync extends AsyncTask<Person,Void,Void>{
        DaoPerson daoPerson;

        DeletePersonAsync(DaoPerson daoPerson){
            this.daoPerson = daoPerson;
        }
        @Override
        protected Void doInBackground(Person... persons) {
            daoPerson.delete(persons[0]);
            return null;
        }
    }

    private static class  UpdatePersonAsync extends AsyncTask<Person,Void,Void>{
        DaoPerson daoPerson;

        UpdatePersonAsync(DaoPerson daoPerson){
            this.daoPerson = daoPerson;
        }
        @Override
        protected Void doInBackground(Person... persons) {
            daoPerson.update(persons[0]);
            return null;
        }
    }
}

