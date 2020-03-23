package com.e.hw1.db;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private int imgPath;

    public Contact(@NonNull String name, @NonNull int imgPath, String surname) {
        this.name = name;
        this.imgPath = imgPath;
        this.surname = surname;
    }


    @NonNull
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgPath() {
        return imgPath;
    }

    public void setImgPath(int imgPath) {
        this.imgPath = imgPath;
    }

    @NonNull
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NonNull String surname) {
        this.surname = surname;
    }
}
