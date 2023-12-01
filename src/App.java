import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");

        // creating address book
        AddressBook book = new AddressBook();

        // taking Console inputs
        takeConsoleInputs(book);
    }

    public static void takeConsoleInputs(AddressBook book) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Which function would you like to execute?");
            System.out.println("[1] Add New Contact");
            System.out.println("[2] Edit Existing Contact");
            System.out.println("[3] Delete Existing Contact");
            System.out.println("[4] Print Address Book");
            System.out.print("Enter your choice (Enter 0 to exit): ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 0:
                    sc.close();
                    return;

                // UC2: adding contact from console
                case 1:
                    System.out.print("Enter First Name: ");
                    String first_name = sc.nextLine();

                    System.out.print("Enter Last Name: ");
                    String last_name = sc.nextLine();

                    System.out.print("Enter Phone Number: ");
                    String phone_number = sc.nextLine();

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
                    String email = sc.nextLine();

                    // adding new contact in the address book
                    book.addContact(first_name, last_name, address, city, state, zip, phone_number, email);

                    System.out.println("Contact added successfully!\n");
                    break;

                // UC3: edit the contact details using contact's first name
                case 2:
                    System.out.print("Enter first name of contact you wish to edit: ");
                    String search_name = sc.nextLine().trim();

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
                                new_first_name = sc.nextLine();
                                book.addressbook.remove(search_name);
                                contact.first_name = new_first_name;
                                book.addressbook.put(new_first_name, contact);
                                System.out.println("Contact Edited Successfully!\n");
                                break;

                            case 2:
                                System.out.print("\n\tEnter New Last Name: ");
                                contact.last_name = sc.nextLine();
                                book.addressbook.put(search_name, contact);
                                System.out.println("Contact Edited Successfully!\n");
                                break;

                            case 3:
                                System.out.print("\n\tEnter New Phone Number: ");
                                contact.phone_number = sc.nextLine();
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
                                contact.email = sc.nextLine();
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
                    book.printAddressBook();
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}