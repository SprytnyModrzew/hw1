package com.e.hw1.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert
    void insert(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("select * from contacts order by name desc")
    LiveData<List<Contact>> getContacts();
}
