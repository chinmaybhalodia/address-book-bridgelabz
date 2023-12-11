package com.example;

import java.sql.*;
import java.util.ArrayList;

public class DBOperations {
    // method to connect with the database
    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(Details.URL, Details.USER, Details.PASSWORD);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            connection = null;
        }
        return connection;
    }

    // reading all the data from the database
    public static ArrayList<Contact> readContacts() {
        ArrayList<Contact> contactList = new ArrayList<>();
        String sqlQuery = "select * from address_book_2 ab inner join person_details pd on ab.person_id = pd.person_id inner join address_details ad on ab.address_id = ad.address_id;";

        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int zip = Integer.parseInt(resultSet.getString("zip"));
                String phone_number = resultSet.getString("phone");
                String email = resultSet.getString("email");

                Contact contact = new Contact(first_name, last_name, address, city, state, zip, phone_number, email);
                contactList.add(contact);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return contactList;
    }
}
