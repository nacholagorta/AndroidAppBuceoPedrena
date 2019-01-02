package com.example.pccom.androidappbuceopedrena;

public class DataHolder {
    public static DataHolder instance = new DataHolder();

    public FireBaseAdmin fireBaseAdmin;
    public DataHolder(){
        fireBaseAdmin = new FireBaseAdmin();
    }
}
