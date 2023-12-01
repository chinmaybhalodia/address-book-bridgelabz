public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Address Book Program");

        // UC1: creating address book and adding contacts
        AddressBook book = new AddressBook();
        book.addContact("Chinmay", "Bhalodia", "Serenity Garden", "Rajkot", "Gujarat", 360005, "+91 93134 02393",
                "chinmaybhalodia@gmail.com");
        AddressBook.printAddressBook(book);
    }
}