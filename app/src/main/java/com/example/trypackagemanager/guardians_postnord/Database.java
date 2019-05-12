package com.example.trypackagemanager.guardians_postnord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

public class Database {

    private static Database db = null;
    private Connection connect = null;
    String customer = null;
    String address = null;

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
            System.out.println("Success");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed");
        }

    }


    public String getCustomer() {
        return customer;
    }

    public String getAddress() {
        return address;
    }

    public void CustomerQuery(){

        String query = "SELECT name, address FROM guardiansdb.reciever";
        try(PreparedStatement preparedStmt = connect.prepareStatement(query)) {
            ResultSet rs = preparedStmt.executeQuery();
            rs.next();
             customer = rs.getString("name");
             address = rs.getString("address");

        }catch (Exception e){
            e.printStackTrace();
        }

    }




    String  name1;
    String name2;
    String name3;
    String name4;
    String address1;
    String address2;
    String address3;
    String address4;



    public void GuardianQuery(){

        String query1 = "SELECT name, address FROM guardiansdb.guardians WHERE name = 'johan'";
        String query2 = "SELECT name, address FROM guardiansdb.guardians WHERE name = 'rasmus'";
        String query3 = "SELECT name, address FROM guardiansdb.guardians WHERE name = 'henrik'";
        String query4 = "SELECT name, address FROM guardiansdb.guardians WHERE name = 'oskar'";

        try(PreparedStatement preparedStatement = connect.prepareStatement(query1)){
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            name1 = resultSet.getString("name");
            address1 = resultSet.getString("address");


        }catch(Exception e){
            e.printStackTrace();
        }
        try(PreparedStatement preparedStatement = connect.prepareStatement(query2)){
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            name2 = resultSet.getString("name");
            address2 = resultSet.getString("address");

        }catch(Exception e){
            e.printStackTrace();
        }
        try(PreparedStatement preparedStatement = connect.prepareStatement(query3)){
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            name3 = resultSet.getString("name");
            address3 = resultSet.getString("address");


        }catch(Exception e){
            e.printStackTrace();
        }
        try(PreparedStatement preparedStatement = connect.prepareStatement(query4)){
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            name4 = resultSet.getString("name");
            address4 = resultSet.getString("address");


        }catch(Exception e){
            e.printStackTrace();
        }
    }





}
