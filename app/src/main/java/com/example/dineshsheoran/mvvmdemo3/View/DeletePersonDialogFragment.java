package com.example.dineshsheoran.mvvmdemo3.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dineshsheoran.mvvmdemo3.Model.Person;
import com.example.dineshsheoran.mvvmdemo3.R;
import com.example.dineshsheoran.mvvmdemo3.Repositories.PersonRepository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

// Created by Dinesh Kumar on 4/29/2019
public class DeletePersonDialogFragment extends DialogFragment {

    private PersonRepository _personRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _personRepository = PersonRepository.getInstance(getActivity().getApplication());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_add_person, container, false);
        Button actionBtn = view.findViewById(R.id.btnAction);
        EditText editName = view.findViewById(R.id.editName);
        EditText editAddress = view.findViewById(R.id.editAddress);
        actionBtn.setText("Delete");
        actionBtn.setOnClickListener((v) -> {

            _personRepository.delete(new Person(editName.getText().toString(), editAddress.getText().toString()));

            editName.setText("");
            editAddress.setText("");
            dismiss();
        });
        return view;
    }


}
