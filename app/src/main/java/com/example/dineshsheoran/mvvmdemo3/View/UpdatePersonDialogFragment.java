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
public class UpdatePersonDialogFragment extends DialogFragment {

    private PersonRepository _personRepository;
    private Person _person;
    private EditText _editName;
    private EditText _editAddress;

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
        _editName = view.findViewById(R.id.editName);
        _editAddress = view.findViewById(R.id.editAddress);
        actionBtn.setText("Update");
        fillFieldsWithDetails();

        actionBtn.setOnClickListener((v) -> {
            updateData();
            clearFields();
            dismiss();
        });
        return view;
    }

    private void updateData() {
        Person updatePerson = new Person(_editName.getText().toString(), _editAddress.getText().toString());
        updatePerson.setId(_person.getId());
        _personRepository.update(updatePerson);
    }

    private void clearFields() {
        _editName.setText("");
        _editAddress.setText("");
    }

    private void fillFieldsWithDetails() {
        _editName.setText(_person.getName());
        _editAddress.setText(_person.getAddress());
    }

    public void setPerson(Person person) {
        this._person = person;
    }

}
