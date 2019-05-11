package com.example.trypackagemanager.guardians_postnord;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    private static Database db = null;
    private Connection connect = null;

    public static Database getInstance(){
        if (db == null){
            db = new Database();
        }
        return db;
    }

    private Database() {

        String url = "jdbc:mysql://den1.mysql3.gear.host/guardiansdb?user=guardiansdb&password=!postnord";
        try {
            connect = DriverManager.getConnection(url);
            System.out.println("Connected to database");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
