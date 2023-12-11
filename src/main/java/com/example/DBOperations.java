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

    // check if a contact with first_name and last_name is present in DB or not
    public static boolean contactExists(String first_name, String last_name) {
        String sqlQuery = "select count(ab.person_id) as count from address_book_2 ab inner join person_details pd on ab.person_id = pd.person_id where first_name = ? and last_name = ?;";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlQuery);) {
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            ResultSet resultSet = statement.executeQuery();
            int count = -1;
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
            return count == 1;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return false;
        }
    }

    // getting person_id for any person
    public static int getPersonID(String first_name, String last_name) {
        String sqlQuery = "select ab.person_id as person_id from address_book_2 ab inner join person_details pd on ab.person_id = pd.person_id where first_name = ? and last_name = ?;";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlQuery);) {
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            ResultSet resultSet = statement.executeQuery();
            int person_id = -1;
            while (resultSet.next()) {
                person_id = resultSet.getInt("person_id");
            }
            return person_id;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return -1;
        }
    }

    // getting address_id for person
    public static int getAddressID(String first_name, String last_name) {
        int person_id = getPersonID(first_name, last_name);
        if (person_id == -1) {
            return -1;
        } else {
            String sqlQuery = "select address_id from address_book_2 where person_id = ?;";
            try (
                    Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement(sqlQuery);) {
                statement.setInt(1, person_id);
                ResultSet resultSet = statement.executeQuery();
                int address_id = -1;
                while (resultSet.next()) {
                    address_id = resultSet.getInt("address_id");
                }
                return address_id;
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
                return -1;
            }
        }
    }

    // method to edit particular column for a contact's personal details
    public static void updatePersonDetail(String column, String newval, int person_id) {
        String sqlQuery = "update person_details set " + column + " = ? where person_id = ?;";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlQuery);) {
            statement.setString(1, newval);
            statement.setInt(2, person_id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    // method to edit particular column for a contact's address details
    public static void updateAddressDetail(String column, String newval, int address_id) {
        String sqlQuery = "update address_details set " + column + " = ? where address_id = ?;";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlQuery);) {
            statement.setString(1, newval);
            statement.setInt(2, address_id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
