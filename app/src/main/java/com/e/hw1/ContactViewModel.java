package com.e.hw1;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.e.hw1.db.Contact;
import com.e.hw1.db.ContactRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository contactRepository;
    private LiveData<List<Contact>> contacts;


    public ContactViewModel(Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
        contacts = contactRepository.getContacts();
    }

    public void insert(Contact contact){
        contactRepository.insert(contact);
    }

    public void delete(Contact contact){
        contactRepository.delete(contact);
    }

    public LiveData<List<Contact>> getContacts(){
        return contacts;
    }
}
