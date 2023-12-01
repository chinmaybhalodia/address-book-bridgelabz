import java.util.ArrayList;

public class AddressBook {
    private ArrayList<Contact> addressbook = new ArrayList<Contact>();

    public AddressBook() {
        this.addressbook = new ArrayList<Contact>();
    }

    // UC1: add new contact function
    public void addContact(String first_name, String last_name, String address, String city, String state, int zip,
            String phone_number, String email) {
        addressbook.add(new Contact(first_name, last_name, address, city, state, zip, phone_number, email));
    }

    public static void printAddressBook(AddressBook book) {
        System.out.println("Contacts in this address book are: ");
        for (int i = 0; i < book.addressbook.size(); i++) {
            System.out.println("(" + (i + 1) + ")");
            book.addressbook.get(i).printContact();
            System.out.println();
        }
    }
}
