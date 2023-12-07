import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

class AddressBook {
    // UC3: used HashMap for efficient searching by name
    String name; // UC6
    HashMap<String, Contact> addressbook;

    // UC13: file path for address book
    private String filePath;

    public AddressBook(String name, String dirPath) {
        this.name = name;
        this.addressbook = new HashMap<String, Contact>();
        this.filePath = dirPath + "/" + name + ".csv";
        FileOperations.createFile(this.filePath); // creating new file for address book
    }

    // UC12: method to sort entries by city
    public ArrayList<Contact> sortByCity() {
        ArrayList<Contact> contacts = FileOperations.readFromFile(this.filePath);
        contacts.sort(Comparator.comparing(contact -> contact.city));
        return contacts;
    }

    // UC12: method to sort entries by state
    public ArrayList<Contact> sortByState() {
        ArrayList<Contact> contacts = FileOperations.readFromFile(this.filePath);
        contacts.sort(Comparator.comparing(contact -> contact.state));
        return contacts;
    }

    // UC12: method to sort entries by zip
    public ArrayList<Contact> sortByZip() {
        ArrayList<Contact> contacts = FileOperations.readFromFile(this.filePath);
        contacts.sort(Comparator.comparing(contact -> contact.zip));
        return contacts;
    }

    // UC11: method to sort entries by name
    public ArrayList<Contact> sortByName() {
        ArrayList<Contact> contacts = FileOperations.readFromFile(this.filePath);
        contacts.sort(Comparator.comparing(contact -> contact.first_name));
        return contacts;
    }

    // UC8: method to get all contacts by city
    public ArrayList<Contact> getAllbyCity(String city) {
        ArrayList<Contact> contacts = new ArrayList<>();
        for (Entry<String, Contact> entry : this.addressbook.entrySet()) {
            if (entry.getValue().city.equals(city)) {
                contacts.add(entry.getValue());
            }
        }
        return contacts;
    }

    // UC8: method to get all contacts by state
    public ArrayList<Contact> getAllbyState(String state) {
        ArrayList<Contact> contacts = new ArrayList<>();
        for (Entry<String, Contact> entry : this.addressbook.entrySet()) {
            if (entry.getValue().state.equals(state)) {
                contacts.add(entry.getValue());
            }
        }
        return contacts;
    }

    // UC7: method to check for duplicate entry
    public boolean hasDuplicate(String first_name) {
        return this.addressbook.containsKey(first_name.toLowerCase().trim());
    }

    // UC1: add new contact function
    public void addContact(String first_name, String last_name, String address, String city, String state, int zip,
            String phone_number, String email) {
        Contact contact = new Contact(first_name, last_name, address, city, state, zip, phone_number, email);
        addressbook.put(first_name.toLowerCase().trim(),
                contact);

        // UC13: adding contact to address book file
        FileOperations.writeToFile(this.filePath, contact);
    }

    public ArrayList<Contact> getAddressBook() {
        return FileOperations.readFromFile(this.filePath);
    }
}
