package com.example.dineshsheoran.mvvmdemo3.Model;

import android.content.Context;
import android.os.AsyncTask;

import com.example.dineshsheoran.mvvmdemo3.Dao.DaoPerson;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Person.class}, version = 1)
public abstract class PersonDatabase extends RoomDatabase {

    private static PersonDatabase _instance;

    public abstract DaoPerson getPersonDao();

    public  static synchronized PersonDatabase getInstance(Context context){
        if(_instance == null){
            _instance = Room.databaseBuilder(context.getApplicationContext(),PersonDatabase.class,
                    "Person_Database")
                    .fallbackToDestructiveMigration()
                    .addCallback(createCallback)
                    .build();
        }
        return _instance;
    }

    private static RoomDatabase.Callback createCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new CreateCallBackAsync(_instance).execute();
            super.onCreate(db);
        }
    };

    private static class CreateCallBackAsync extends AsyncTask<Void,Void,Void>{
        DaoPerson daoPerson;
        CreateCallBackAsync(PersonDatabase personDatabase){
            daoPerson = personDatabase.getPersonDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            daoPerson.insert(new Person("Dinesh","Abc"));
            daoPerson.insert(new Person("Manish","Xyz"));
            daoPerson.insert(new Person("Yogesg","Fgj"));
            daoPerson.insert(new Person("Karmbir","Jind"));
            return null;
        }
    }
}
