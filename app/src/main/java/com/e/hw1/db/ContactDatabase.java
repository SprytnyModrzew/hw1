package com.e.hw1.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.e.hw1.R;

@Database(entities = Contact.class, version = 7, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {
    private static ContactDatabase instance;

    public abstract ContactDao contactDao();

    public static synchronized ContactDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ContactDatabase.class, "contactDatabase")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDB(instance).insertDummy();
        }
    };

    private static class PopulateDB {
        private ContactDao contactDao;
        private PopulateDB(ContactDatabase db){
            contactDao = db.contactDao();
        }
        protected void insertDummy(){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    contactDao.insert(new Contact("2020", R.drawable.avatar_2, "Mama"));
                    contactDao.insert(new Contact("3030", R.drawable.avatar_1, "mama"));
                }
            });
            thread.start();
        }
    }
}
