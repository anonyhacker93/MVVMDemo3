package com.example.dineshsheoran.mvvmdemo3.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dineshsheoran.mvvmdemo3.Adapter.PersonRecyclerAdapter;
import com.example.dineshsheoran.mvvmdemo3.Model.Person;
import com.example.dineshsheoran.mvvmdemo3.R;
import com.example.dineshsheoran.mvvmdemo3.ViewModel.MainActivityViewModel;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    MainActivityViewModel mainActivityViewModel;
    private ArrayList<Person> _personArrayList=new ArrayList<>();
    PersonRecyclerAdapter adapter;
    private TextView _txtViewMsg;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        initViews();

    }

    private void initViewModel() {
    mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
    mainActivityViewModel.init(getApplication());

    mainActivityViewModel.getPersonDetails().observe(this, new Observer<ArrayList<Person>>() {
        @Override
        public void onChanged(ArrayList<Person> people) {
            _personArrayList = people;
            adapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(_personArrayList.size()-1);
        }
    });

    mainActivityViewModel.getMessage().observe(this, new Observer<String>() {
        @Override
        public void onChanged(String s) {
            _txtViewMsg.setText(s);

        }
    });

    }


    private void initViews() {
        _txtViewMsg = findViewById(R.id.txtMsg);
         recyclerView = findViewById(R.id.recyclerView);
        adapter = new PersonRecyclerAdapter(mainActivityViewModel.getPersonDetails().getValue());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityViewModel.addNewData();
            }
        });
    }
}
