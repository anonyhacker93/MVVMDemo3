package com.example.dineshsheoran.mvvmdemo3.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.dineshsheoran.mvvmdemo3.Model.Person;
import com.example.dineshsheoran.mvvmdemo3.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonRecyclerAdapter.MyViewHolder> {
    private ArrayList<Person> _personArrayList;

    public PersonRecyclerAdapter(ArrayList<Person> personArrayList){
        this._personArrayList = personArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_person_recycler,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Person person = _personArrayList.get(position);
    holder.txtViewId.setText(""+person.getId());
    holder.txtViewName.setText(person.getName());
    }

    @Override
    public int getItemCount() {
        return _personArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtViewId;
        TextView txtViewName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewId = itemView.findViewById(R.id.txtViewPersonId);
            txtViewName = itemView.findViewById(R.id.txtViewPersonName);
        }
    }
}

