package com.e.hw1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.e.hw1.db.Contact;

public class DetailViewModel extends AndroidViewModel {
    private MutableLiveData<Contact> current;
    public DetailViewModel(@NonNull Application application) {
        super(application);
        current = new MutableLiveData<>();
        current.setValue(new Contact("nope", R.drawable.avatar_1, "noope", 200));
    }


    LiveData<Contact> getCurrent() {return current;}

    public void setCurrent(Contact contact){
        current.setValue(contact);
    }
}
