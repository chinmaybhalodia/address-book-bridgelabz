import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");

        // creating address book
        AddressBook book = new AddressBook();

        // taking Console inputs
        takeConsoleInputs(book);

        AddressBook.printAddressBook(book);
    }

    public static void takeConsoleInputs(AddressBook book) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Which function would you like to execute?");
            System.out.println("[1] Add New Contact");
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

                default:
                    System.out.println("Invalid Input.");
                    break;
            }
        }
    }
}