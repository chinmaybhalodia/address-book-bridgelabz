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

    // UC1: add new contact function
    public void addContact(String first_name, String last_name, String address, String city, String state, int zip,
            String phone_number, String email) {
        addressbook.put(first_name, new Contact(first_name, last_name, address, city, state, zip, phone_number, email));
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
            entry.getValue().printContact();
            i++;
            System.out.println();
        }
    }
}
