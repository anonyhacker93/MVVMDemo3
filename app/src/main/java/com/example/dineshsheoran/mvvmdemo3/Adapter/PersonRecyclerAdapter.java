package com.example.dineshsheoran.mvvmdemo3.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dineshsheoran.mvvmdemo3.Model.Person;
import com.example.dineshsheoran.mvvmdemo3.R;
import com.example.dineshsheoran.mvvmdemo3.View.IRecyclerClickHandler;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonRecyclerAdapter.MyViewHolder> {
    private ArrayList<Person> _personArrayList;
    private Context _context;
    private IRecyclerClickHandler _clickHandler;

    public PersonRecyclerAdapter(Context context, ArrayList<Person> personArrayList, IRecyclerClickHandler handler) {
        this._personArrayList = personArrayList;
        this._context = context;
        _clickHandler = handler;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_person_recycler, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Person person = _personArrayList.get(position);
        holder.txtViewAddress.setText("" + person.getAddress());
        holder.txtViewName.setText(person.getName());
        holder.layout.setOnLongClickListener((view) -> {
            _clickHandler.onLongClick(person);
            return true;
        });

        holder.layout.setOnClickListener((view) -> {
            _clickHandler.onClick(person);
        });
    }

    @Override
    public int getItemCount() {
        if (_personArrayList != null)
            return _personArrayList.size();
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewAddress;
        TextView txtViewName;
        View layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.rowLayout);
            txtViewName = itemView.findViewById(R.id.txtViewPersonName);
            txtViewAddress = itemView.findViewById(R.id.txtViewAdress);
        }
    }
}

