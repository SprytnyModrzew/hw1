package com.e.hw1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.e.hw1.db.Contact;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AdderActivity extends AppCompatActivity implements DatePicker.OnDateChosenListener {
    long contactDate;
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
                EditText editText2 = findViewById(R.id.textBirthday);
                EditText editText3 = findViewById(R.id.textNumber);

                String textName = editText.getText().toString();
                String textSurname = editText1.getText().toString();
                String textNumber = editText3.getText().toString();
                if(textName.isEmpty() || textSurname.isEmpty() || editText2.toString().isEmpty() || faultyText(textNumber)){
                    Toast.makeText(getApplicationContext(), "Something went wrong... did you put your data properly?", Toast.LENGTH_LONG).show();
                    return;}
                //todo contact class -> birthday
                contactViewModel.insert(new Contact(textName,randomPic(),textSurname, contactDate,textNumber));
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

        int max = pics.size()-1;
        int min = 0;
        int range = max - min + 1;
        return pics.get((int) ((Math.random() * range) + min));
    }

    @Override
    public void onDateChosen(int year, int month, int dayOfMonth) {
        EditText textBirth = findViewById(R.id.textBirthday);
        Log.i("userrr","działaaaa");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        contactDate = cal.getTimeInMillis();
        Date date = new Date(contactDate);
        Log.i("userrr","działaaaa "+simpleDateFormat.format(date));
        String text = simpleDateFormat.format(date);
        textBirth.setText(text);
    }
    private boolean faultyText(String number){
        if(number.length() == 9){
            for(int i = 0; i < number.length(); i++){
                switch(number.charAt(i)){
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        break;
                    default:
                        return true;
                }

            }
            return false;
        }
        return true;
    }
}

