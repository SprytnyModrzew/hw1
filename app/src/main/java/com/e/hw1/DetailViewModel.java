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
        current.setValue(new Contact(getApplication().getString(R.string.detail_not_selected), R.drawable.avatar_1, "", 200, "777777777"));
    }


    LiveData<Contact> getCurrent() {return current;}

    public void setCurrent(Contact contact){
        current.setValue(contact);
    }
}
