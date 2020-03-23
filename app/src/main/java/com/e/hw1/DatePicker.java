package com.e.hw1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    OnDateChosenListener callback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DatePickerDialog.OnDateSetListener callback;
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                android.R.style.Theme_Light_Panel,
                this, year, month, day);
        return datePickerDialog;
                //todo pobawić się tu
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        callback.onDateChosen(year, month, dayOfMonth);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnDateChosenListener){
            callback = (OnDateChosenListener) context;
        }
        else throw new RuntimeException(context.toString()
                + " must implement OnDateFragmentInteractionListener");
    }


    public interface OnDateChosenListener{
        void onDateChosen(int year, int month, int dayOfMonth);
    }
}
