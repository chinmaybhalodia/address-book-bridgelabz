import java.util.HashMap;
import java.util.Map;

public class AddressBook {
    // UC3: used HashMap for efficient searching by name
    HashMap<String, Contact> addressbook;

    public AddressBook() {
        this.addressbook = new HashMap<String, Contact>();
    }

    // UC1: add new contact function
    public void addContact(String first_name, String last_name, String address, String city, String state, int zip,
            String phone_number, String email) {
        addressbook.put(first_name, new Contact(first_name, last_name, address, city, state, zip, phone_number, email));
    }

    public void printAddressBook() {
        System.out.println("\nContacts in this address book are: ");
        int i = 1;
        for (Map.Entry<String, Contact> entry : this.addressbook.entrySet()) {
            System.out.println(i);
            entry.getValue().printContact();
            i++;
            System.out.println();
        }
    }
}
