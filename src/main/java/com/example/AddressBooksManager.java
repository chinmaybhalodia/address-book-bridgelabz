package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.exceptions.*;
import com.validator.*;

// UC6: Address books manager to handle multiple address books
public class AddressBooksManager {
    HashMap<String, AddressBook> addressBooks;

    // UC9: store and save contacts by city and state
    private HashMap<String, ArrayList<Contact>> cityContacts;
    private HashMap<String, ArrayList<Contact>> stateContacts;

    // UC13: directory address to store files
    private String directoryPath;

    public AddressBooksManager(String dirPath) {
        this.addressBooks = new HashMap<String, AddressBook>();
        this.cityContacts = new HashMap<>();
        this.stateContacts = new HashMap<>();
        this.directoryPath = dirPath;

        // creating new directory to store addressbooks
        CSVOperations.createDirectory(this.directoryPath);
    }

    public String getDirectory() {
        return this.directoryPath;
    }

    // UC10: get number of contacts from city
    public int countInCity(String city) {
        ArrayList<Contact> contacts;
        if (cityContacts.containsKey(city)) {
            contacts = cityContacts.get(city);
        } else {
            contacts = new ArrayList<>();
        }
        return contacts.size();
    }

    // UC10: get number of contacts from state
    public int countInState(String state) {
        ArrayList<Contact> contacts;
        if (stateContacts.containsKey(state)) {
            contacts = stateContacts.get(state);
        } else {
            contacts = new ArrayList<>();
        }
        return contacts.size();
    }

    // method to update city contacts
    public void updateCityContacts(String city, Contact contact) {
        ArrayList<Contact> contacts;
        if (cityContacts.containsKey(city)) {
            contacts = cityContacts.get(city);
        } else {
            contacts = new ArrayList<>();
        }
        contacts.add(contact);
        cityContacts.put(city, contacts);
    }

    // method to update state contacts
    public void updateStateContacts(String state, Contact contact) {
        ArrayList<Contact> contacts;
        if (stateContacts.containsKey(state)) {
            contacts = stateContacts.get(state);
        } else {
            contacts = new ArrayList<>();
        }
        contacts.add(contact);
        stateContacts.put(state, contacts);
    }

    // UC8: method to search across all addressbooks with same city
    public ArrayList<Contact> findByCity(String city) {
        return this.cityContacts.containsKey(city) ? this.cityContacts.get(city) : new ArrayList<Contact>();
    }

    // UC8: method to search across all addressbooks with same state
    public ArrayList<Contact> findByState(String state) {
        return this.stateContacts.containsKey(state) ? this.stateContacts.get(state) : new ArrayList<Contact>();
    }

    public AddressBook getBookbyName(String name) {
        return addressBooks.containsKey(name) ? addressBooks.get(name) : null;
    }

    public void getAllBooks() {
        CSVOperations.listFiles(this.directoryPath);
    }

    public void createBook(String name) {
        addressBooks.put(name, new AddressBook(name, this.directoryPath));
    }

    public void accessBook(AddressBook book, Scanner sc) {
        while (true) {
            System.out.println("Which function would you like to execute?");
            System.out.println("[1] Add New Contact");
            System.out.println("[2] Edit Existing Contact"); // TODO: use file I/O
            System.out.println("[3] Delete Existing Contact"); // TODO: use file I/O
            System.out.println("[4] Print Address Book");
            System.out.println("[5] Print Address Book sorted by Name");
            System.out.println("[6] Print Address Book sorted by City");
            System.out.println("[7] Print Address Book sorted by State");
            System.out.println("[8] Print Address Book sorted by Zip");
            System.out.print("Enter your choice (Enter 0 to exit): ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 0:
                    return;

                // UC2: adding contact from console
                case 1:
                    System.out.print("Enter First Name: ");
                    String first_name;
                    while (true) {
                        try {
                            first_name = sc.nextLine();
                            Validator.validateFirstName(first_name);
                            break;
                        } catch (InvalidFirstNameException exception) {
                            System.out.println("\n" + exception.getMessage());
                            System.out.print("Enter First Name: ");
                        }
                    }

                    if (book.hasDuplicate(first_name)) {
                        System.out.println("This contact already exists. You can choose to edit.\n");
                        break;
                    }

                    System.out.print("Enter Last Name: ");
                    String last_name;
                    while (true) {
                        try {
                            last_name = sc.nextLine();
                            Validator.validateLastName(last_name);
                            break;
                        } catch (InvalidLastNameException exception) {
                            System.out.println("\n" + exception.getMessage());
                            System.out.print("Enter Last Name: ");
                        }
                    }

                    System.out.print("Enter Phone Number: ");
                    String phone_number;
                    while (true) {
                        try {
                            phone_number = sc.nextLine();
                            Validator.validatePhoneNumber(phone_number);
                            break;
                        } catch (InvalidPhoneNumberException exception) {
                            System.out.println("\n" + exception.getMessage());
                            System.out.print("Enter Phone Number: ");
                        }
                    }

                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();

                    System.out.print("Enter City: ");
                    String city = sc.nextLine();

                    System.out.print("Enter State: ");
                    String state = sc.nextLine();

                    System.out.print("Enter Zip Code: ");
                    int zip = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email;
                    while (true) {
                        try {
                            email = sc.nextLine();
                            Validator.validateEmail(email);
                            break;
                        } catch (InvalidEmailException exception) {
                            System.out.println("\n" + exception.getMessage());
                            System.out.print("Enter Email: ");
                        }
                    }

                    // adding new contact in the address book
                    book.addContact(first_name, last_name, address, city, state, zip, phone_number, email);

                    // UC9: update city and state contacts
                    this.updateCityContacts(city,
                            new Contact(first_name, last_name, address, city, state, zip, phone_number, email));
                    this.updateStateContacts(state,
                            new Contact(first_name, last_name, address, city, state, zip, phone_number, email));

                    System.out.println("Contact added successfully!\n");
                    break;

                // UC3: edit the contact details using contact's first name
                case 2:
                    System.out.print("Enter first name of contact you wish to edit: ");
                    String search_name = sc.nextLine().toLowerCase().trim();

                    if (book.addressbook.containsKey(search_name)) {
                        Contact contact = book.addressbook.get(search_name);
                        String new_first_name;
                        System.out.println("\n\tFollowing fields can be edited for this contact:");
                        System.out.println("\t[1] First Name");
                        System.out.println("\t[2] Last Name");
                        System.out.println("\t[3] Phone Number");
                        System.out.println("\t[4] Address");
                        System.out.println("\t[5] City");
                        System.out.println("\t[6] State");
                        System.out.println("\t[7] Zip");
                        System.out.println("\t[8] Email");
                        System.out.print("\tEnter field you wish to edit (enter 0 to exit): ");
                        int edit_choice = sc.nextInt();
                        sc.nextLine();

                        switch (edit_choice) {
                            case 0:
                                break;

                            case 1:
                                System.out.print("\n\tEnter New First Name: ");
                                while (true) {
                                    try {
                                        new_first_name = sc.nextLine();
                                        Validator.validateFirstName(new_first_name);
                                        break;
                                    } catch (InvalidFirstNameException exception) {
                                        System.out.println("\n\t" + exception.getMessage());
                                        System.out.print("\n\tEnter New First Name: ");
                                    }
                                }
                                book.addressbook.remove(search_name);
                                contact.first_name = new_first_name;
                                book.addressbook.put(new_first_name, contact);
                                System.out.println("Contact Edited Successfully!\n");
                                break;

                            case 2:
                                System.out.print("\n\tEnter New Last Name: ");
                                while (true) {
                                    try {
                                        contact.last_name = sc.nextLine();
                                        Validator.validateLastName(contact.last_name);
                                        break;
                                    } catch (InvalidLastNameException exception) {
                                        System.out.println("\n\t" + exception.getMessage());
                                        System.out.print("\n\tEnter New Last Name: ");
                                    }
                                }
                                book.addressbook.put(search_name, contact);
                                System.out.println("Contact Edited Successfully!\n");
                                break;

                            case 3:
                                System.out.print("\n\tEnter New Phone Number: ");
                                while (true) {
                                    try {
                                        contact.phone_number = sc.nextLine();
                                        Validator.validatePhoneNumber(contact.phone_number);
                                        break;
                                    } catch (InvalidPhoneNumberException exception) {
                                        System.out.println("\n\t" + exception.getMessage());
                                        System.out.print("\n\tEnter New Phone Number: ");
                                    }
                                }
                                book.addressbook.put(search_name, contact);
                                System.out.println("Contact Edited Successfully!\n");
                                break;

                            case 4:
                                System.out.print("\n\tEnter New Address: ");
                                contact.address = sc.nextLine();
                                book.addressbook.put(search_name, contact);
                                System.out.println("Contact Edited Successfully!\n");
                                break;

                            case 5:
                                System.out.print("\n\tEnter New City: ");
                                contact.city = sc.nextLine();
                                book.addressbook.put(search_name, contact);
                                System.out.println("Contact Edited Successfully!\n");
                                break;

                            case 6:
                                System.out.print("\n\tEnter New State: ");
                                contact.state = sc.nextLine();
                                book.addressbook.put(search_name, contact);
                                System.out.println("Contact Edited Successfully!\n");
                                break;

                            case 7:
                                System.out.print("\n\tEnter New Zip Code: ");
                                contact.zip = sc.nextInt();
                                sc.nextLine();
                                book.addressbook.put(search_name, contact);
                                System.out.println("Contact Edited Successfully!\n");
                                break;

                            case 8:
                                System.out.print("\n\tEnter New Email: ");
                                while (true) {
                                    try {
                                        contact.email = sc.nextLine();
                                        Validator.validateEmail(contact.email);
                                        break;
                                    } catch (InvalidEmailException exception) {
                                        System.out.println("\n\t" + exception.getMessage());
                                        System.out.print("\n\tEnter New Email: ");
                                    }
                                }
                                book.addressbook.put(search_name, contact);
                                System.out.println("Contact Edited Successfully!\n");
                                break;

                            default:
                                System.out.println("Invalid choice.");
                                break;
                        }
                    } else {
                        System.out.println("There is no contact having this first name.\n");
                    }
                    break;

                // UC4: delete existing contact using contact's first name
                case 3:
                    System.out.print("Enter first name of contact you wish to delete: ");
                    String delete_name = sc.nextLine().trim();

                    if (book.addressbook.containsKey(delete_name)) {
                        book.addressbook.remove(delete_name);
                        System.out.println("Contact Deleted Successfully!\n");
                    } else {
                        System.out.println("There is no contact having this first name.\n");
                    }
                    break;

                case 4:
                    ArrayList<Contact> contacts = book.getAddressBook();
                    if (contacts.isEmpty()) {
                        System.out.println("\nAddress book is empty.\n");
                    } else {
                        System.out.println("\nContacts in this address book are: ");
                        int i = 1;
                        for (Contact contact : contacts) {
                            System.out.println(i + ")\n" + contact + "\n");
                            i++;
                        }
                    }
                    break;

                // UC11: sort address book by name
                case 5:
                    ArrayList<Contact> sortedByName = book.sortByName();
                    if (sortedByName.isEmpty()) {
                        System.out.println("\nAddress book is empty.\n");
                    } else {
                        System.out.println("\nContacts in this address book are: ");
                        int i = 1;
                        for (Contact contact : sortedByName) {
                            System.out.println(i + ")\n" + contact + "\n");
                            i++;
                        }
                    }
                    break;

                // UC12: sort address book by city
                case 6:
                    ArrayList<Contact> sortedByCity = book.sortByCity();
                    if (sortedByCity.isEmpty()) {
                        System.out.println("\nAddress book is empty.\n");
                    } else {
                        System.out.println("\nContacts in this address book are: ");
                        int i = 1;
                        for (Contact contact : sortedByCity) {
                            System.out.println(i + ")\n" + contact + "\n");
                            i++;
                        }
                    }
                    break;

                // UC12: sort address book by state
                case 7:
                    ArrayList<Contact> sortedByState = book.sortByState();
                    if (sortedByState.isEmpty()) {
                        System.out.println("\nAddress book is empty.\n");
                    } else {
                        System.out.println("\nContacts in this address book are: ");
                        int i = 1;
                        for (Contact contact : sortedByState) {
                            System.out.println(i + ")\n" + contact + "\n");
                            i++;
                        }
                    }
                    break;

                // UC12: sort address book by zip
                case 8:
                    ArrayList<Contact> sortedByZip = book.sortByZip();
                    if (sortedByZip.isEmpty()) {
                        System.out.println("\nAddress book is empty.\n");
                    } else {
                        System.out.println("\nContacts in this address book are: ");
                        int i = 1;
                        for (Contact contact : sortedByZip) {
                            System.out.println(i + ")\n" + contact + "\n");
                            i++;
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}