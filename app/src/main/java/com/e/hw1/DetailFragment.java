package com.e.hw1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.e.hw1.db.Contact;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetailFragment extends Fragment{

    public DetailViewModel mViewModel;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_detail, container, false);
        final TextView textName = root.findViewById(R.id.detailName);
        final ImageView image = root.findViewById(R.id.detailImage);
        final TextView textNumber = root.findViewById(R.id.detailNumber);
        final TextView textBirth = root.findViewById(R.id.detailBirthday);
        Log.i("userrr", " wopppp");
        mViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        mViewModel.getCurrent().observe(getViewLifecycleOwner(), new Observer<Contact>() {
            @Override
            public void onChanged(Contact contact) {
                textName.setText(String.format("%s %s", contact.getName(), contact.getSurname()));
                image.setImageResource(contact.getImgPath());

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                Date date = new Date(contact.getDate());
                String text = simpleDateFormat.format(date);
                textBirth.setText(text);

                textNumber.setText(contact.getNumber());

                Log.e("userrr","onChanged");
            }
        });
        return root;
    }
    public DetailViewModel getViewModel()
    {
        return mViewModel;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setContactInfo(Contact contact){
        mViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        mViewModel.setCurrent(contact);
    }
}