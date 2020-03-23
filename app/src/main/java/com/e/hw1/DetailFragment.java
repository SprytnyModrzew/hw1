package com.e.hw1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.e.hw1.db.Contact;

public class DetailFragment extends Fragment{

    private DetailViewModel mViewModel;

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
        Log.i("userrr", " wopppp");
        mViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        mViewModel.getCurrent().observe(getViewLifecycleOwner(), new Observer<Contact>() {
            @Override
            public void onChanged(Contact contact) {
                textName.setText(String.format("%s %s", contact.getName(), contact.getSurname()));
                image.setImageResource(contact.getImgPath());
                Log.e("userrr","onChanged");
            }
        });
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.setCurrent(new Contact("woop",R.drawable.avatar_3,"ee"));
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
