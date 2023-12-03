import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");

        // UC6: creating address book manager to handle multiple address books
        AddressBooksManager manager = new AddressBooksManager();

        takeConsoleInputs(manager);
    }

    public static void takeConsoleInputs(AddressBooksManager manager) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nFollowing functions can be executed:");
            System.out.println("[1] Create New Address Book");
            System.out.println("[2] Access Address Book");
            System.out.println("[3] View all Address Books");
            System.out.print("Enter your choice (Enter 0 to exit): ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 0:
                    sc.close();
                    return;

                case 1:
                    System.out.print("Enter Address Book Name: ");
                    String name = sc.nextLine();
                    manager.createBook(name);
                    System.out.println("Address Book created successfully.");
                    break;

                case 2:
                    System.out.print("Enter Address Book's Name: ");
                    String search_name = sc.nextLine();
                    if (manager.getBookbyName(search_name) != null) {
                        System.out.println("\nAccessing Address Book " + search_name);
                        AddressBooksManager.accessBook(manager.getBookbyName(search_name));
                    } else {
                        System.out.println("No Address Book found with this name.");
                    }
                    break;

                case 3:
                    System.out.println("Following are all the books in this manager:");
                    manager.getAllBooks();
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}