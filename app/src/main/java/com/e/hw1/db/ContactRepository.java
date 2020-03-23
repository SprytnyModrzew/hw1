package com.e.hw1.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.logging.Handler;

public class ContactRepository {
    private ContactDao contactDao;
    private LiveData<List<Contact>> contacts;

    public ContactRepository(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        contactDao = contactDatabase.contactDao();
        contacts = contactDao.getContacts();
    }

    public LiveData<List<Contact>> getContacts(){
        return contacts;
    }

    public void insert(Contact contact){
        final Contact contact1 = contact;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                contactDao.insert(contact1);
            }
        });
        thread.start();
    }

    public void delete(final Contact contact){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                contactDao.delete(contact);
            }
        });
        thread.start();
    }
}
