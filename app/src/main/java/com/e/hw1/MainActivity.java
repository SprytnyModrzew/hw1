package com.e.hw1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.e.hw1.db.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements ContactFragment.OnListFragmentInteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton button = findViewById(R.id.button_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdderActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onListFragmentInteraction(Contact contact) {
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.e("userrr","portret");
        }
        else {
            //todo this line doesnt work
            DetailViewModel viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
            viewModel.setCurrent(contact);
        }
    }

    @Override
    public void onButtonClick(Contact mItem) {
        ContactViewModel contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        contactViewModel.delete(mItem);
    }
}
