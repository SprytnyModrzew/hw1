package com.e.hw1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.e.hw1.db.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements ContactFragment.OnListFragmentInteractionListener{
    boolean detail = false;
    Contact current;

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
    public void onBackPressed() {
        if(detail) {
            getSupportFragmentManager().popBackStack();
            detail = false;
        }
        else
        super.onBackPressed();
    }

    @Override
    public void onListFragmentInteraction(Contact contact) {
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.e("userrr","portret");
            FragmentManager fm = getSupportFragmentManager();
            DetailFragment fragmentById = new DetailFragment(new ViewModelProvider(this).get(DetailViewModel.class));
            //todo below
            fm.beginTransaction().replace(R.id.fragmentcore, fragmentById,"eo").addToBackStack(null).commit();
            //fragmentById.getViewModel().setCurrent(contact);
            fragmentById.setContactInfo(contact);
            current = contact;
            detail = true;
        }
        else {
            FragmentManager fm = getSupportFragmentManager();
            DetailFragment fragmentById = (DetailFragment) fm.findFragmentById(R.id.fragment2);
            fragmentById.getViewModel().setCurrent(contact);
            current = contact;
            /*DetailViewModel viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
            viewModel.setCurrent(contact);*/

        }
    }

    @Override
    public void onListFragmentLongClick(Contact item) {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("");
        alert.setMessage(getString(R.string.hint_call)+" "+item.getName()+"?")
        .setCancelable(false)
        .setNegativeButton(getString(R.string.hint_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        })
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Calling...", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    @Override
    public void onButtonClick(final Contact item) {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        final ContactViewModel contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        alert.setTitle("");
        alert.setMessage(getString(R.string.hint_erase)+" "+item.getName()+"?")
                .setCancelable(false)
                .setNegativeButton(getString(R.string.hint_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        contactViewModel.delete(item);
                    }
                });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

}