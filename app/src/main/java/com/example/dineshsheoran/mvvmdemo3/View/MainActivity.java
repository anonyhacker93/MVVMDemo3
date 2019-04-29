package com.example.dineshsheoran.mvvmdemo3.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import com.example.dineshsheoran.mvvmdemo3.Adapter.PersonRecyclerAdapter;
import com.example.dineshsheoran.mvvmdemo3.Model.Person;
import com.example.dineshsheoran.mvvmdemo3.R;
import com.example.dineshsheoran.mvvmdemo3.Repositories.PersonRepository;
import com.example.dineshsheoran.mvvmdemo3.ViewModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    MainActivityViewModel mainActivityViewModel;
    private ArrayList<Person> _personArrayList = new ArrayList<>();
    PersonRecyclerAdapter adapter;
    RecyclerView recyclerView;

    private IRecyclerClickHandler recyclerClickHandler = new IRecyclerClickHandler() {
        @Override
        public void onClick(Person person) {
            UpdatePersonDialogFragment updatePersonDialogFragment = new UpdatePersonDialogFragment();
            updatePersonDialogFragment.setPerson(person);
            updatePersonDialogFragment.show(MainActivity.this.getSupportFragmentManager(), "");
        }

        @Override
        public void onLongClick(Person person) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setMessage("Are you sure to delete it ?");
            alertDialog.setPositiveButton("Sure", (DialogInterface dialogInterface, int i) -> {
                        PersonRepository.getInstance(MainActivity.this).delete(person);
                    }
            );
            AlertDialog ad = alertDialog.create();
            ad.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initViews();
        initViewModel();


    }

    private void initViewModel() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init(MainActivity.this);

        mainActivityViewModel.getPersonDetails().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> people) {
                if (people == null) return;
                _personArrayList.clear();
                ArrayList<Person> list = (ArrayList<Person>) people;
                _personArrayList.addAll(list);
                adapter.notifyDataSetChanged();
            }
        });
    }


    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new PersonRecyclerAdapter(MainActivity.this, _personArrayList, recyclerClickHandler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button btnAdd = findViewById(R.id.btnAction);
        btnAdd.setOnClickListener((v) -> {
                    AddPersonDialogFragment addPersonDialogFragment = new AddPersonDialogFragment();
                    addPersonDialogFragment.show(getSupportFragmentManager(), "");
                }
        );
    }
}
