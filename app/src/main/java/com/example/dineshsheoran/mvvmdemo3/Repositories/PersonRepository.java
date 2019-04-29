package com.example.dineshsheoran.mvvmdemo3.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.dineshsheoran.mvvmdemo3.Dao.DaoPerson;
import com.example.dineshsheoran.mvvmdemo3.Model.Person;
import com.example.dineshsheoran.mvvmdemo3.Model.PersonDatabase;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;

public class PersonRepository {
    private static MutableLiveData<ArrayList<Person>>  _personArrayData;
    private static PersonRepository _instance;
    private static PersonDatabase personDatabase;
    private static DaoPerson _daoPerson;

    public static PersonRepository getInstance(Application application) {
        if (_instance == null) {
            _instance = new PersonRepository();
            init(application);
        }
        return _instance;
    }

    private static void init(Application application) {
        personDatabase = PersonDatabase.getInstance(application);
        _daoPerson = personDatabase.getPersonDao();
        ArrayList<Person> personArrayList = (ArrayList<Person>) _daoPerson.getAllDetails();
        _personArrayData.setValue(personArrayList);
    }


    public LiveData<ArrayList<Person>> getPersonDetails(){
        return _personArrayData;
    }

    public ArrayList<Person> getPersonData() {
     return new FetchPersonAsync(_daoPerson).execute();
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
            daoPerson.delete(persons[0]);
            return null;
        }
    }

    private static class FetchPersonAsync extends AsyncTask<Void,ArrayList<Person>,ArrayList<Person>>{
        DaoPerson daoPerson;
        FetchPersonAsync(DaoPerson daoPerson){
            this.daoPerson = daoPerson;
        }

        @Override
        protected void onPostExecute(ArrayList<Person> people) {
            super.onPostExecute(people);
        }

        @Override
        protected ArrayList<Person> doInBackground(Void... voids) {

            return (ArrayList<Person>) daoPerson.getAllDetails();
        }
    }
}

