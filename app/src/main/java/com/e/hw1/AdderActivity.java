package com.e.hw1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.e.hw1.db.Contact;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AdderActivity extends AppCompatActivity implements DatePicker.OnDateChosenListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adder);
        final ContactViewModel contactViewModel;
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        EditText editText3 = findViewById(R.id.textBirthday);
        editText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePicker();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        Button button = findViewById(R.id.button_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.textName);
                EditText editText1 = findViewById(R.id.textSurname);
                String textName = editText.getText().toString();
                String textSurname = editText1.getText().toString();
                if(textName.isEmpty() || textSurname.isEmpty())
                    return;
                contactViewModel.insert(new Contact(textName,randomPic(),textSurname));
                Log.i("userrr", "cos");
                finish();
            }
        });
    }

    public int randomPic(){
        ArrayList<Integer> pics = new ArrayList<>();
        pics.add(R.drawable.avatar_1);
        pics.add(R.drawable.avatar_2);
        pics.add(R.drawable.avatar_3);
        pics.add(R.drawable.avatar_4);
        pics.add(R.drawable.avatar_5);
        pics.add(R.drawable.avatar_6);
        pics.add(R.drawable.avatar_7);
        pics.add(R.drawable.avatar_8);
        pics.add(R.drawable.avatar_9);
        pics.add(R.drawable.avatar_10);
        pics.add(R.drawable.avatar_11);
        pics.add(R.drawable.avatar_12);
        pics.add(R.drawable.avatar_13);
        pics.add(R.drawable.avatar_14);
        pics.add(R.drawable.avatar_15);
        pics.add(R.drawable.avatar_16);

        int max = 16;
        int min = 0;
        int range = max - min + 1;
        return pics.get((int) ((Math.random() * range) + min));
    }

    @Override
    public void onDateChosen(int year, int month, int dayOfMonth) {
        EditText textBirth = findViewById(R.id.textBirthday);
        month++;
        String text = dayOfMonth + "/" + month + "/" + year;
        textBirth.setText(text);
        Log.i("userrr","działaaaa");

    }
}

