import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

class AddressBook {
    // UC3: used HashMap for efficient searching by name
    String name; // UC6
    HashMap<String, Contact> addressbook;

    public AddressBook(String name) {
        this.name = name;
        this.addressbook = new HashMap<String, Contact>();
    }

    // UC12: method to sort entries by city
    public ArrayList<Contact> sortByCity() {
        ArrayList<Contact> contacts = new ArrayList<>(this.addressbook.values());
        contacts.sort((c1, c2) -> c1.city.compareTo(c2.city));
        return contacts;
    }

    // UC12: method to sort entries by state
    public ArrayList<Contact> sortByState() {
        ArrayList<Contact> contacts = new ArrayList<>(this.addressbook.values());
        contacts.sort((c1, c2) -> c1.state.compareTo(c2.state));
        return contacts;
    }

    // UC12: method to sort entries by zip
    public ArrayList<Contact> sortByZip() {
        ArrayList<Contact> contacts = new ArrayList<>(this.addressbook.values());
        contacts.sort((c1, c2) -> c2.zip - c1.zip);
        return contacts;
    }

    // UC11: method to sort entries by name
    public ArrayList<Contact> sortByName() {
        ArrayList<Contact> contacts = new ArrayList<>(this.addressbook.values());
        contacts.sort((c1, c2) -> c1.first_name.compareTo(c2.first_name));
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
        addressbook.put(first_name.toLowerCase().trim(),
                new Contact(first_name, last_name, address, city, state, zip, phone_number, email));
    }

    public void printAddressBook() {
        if (this.addressbook.isEmpty()) {
            System.out.println("\nAddress book is empty.");
            return;
        }
        System.out.println("\nContacts in this address book are: ");
        int i = 1;
        for (Entry<String, Contact> entry : this.addressbook.entrySet()) {
            System.out.println(i + ")");
            System.out.println(entry.getValue().toString());
            i++;
            System.out.println();
        }
    }
}
