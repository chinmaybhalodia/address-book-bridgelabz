public class Contact {
    String first_name;
    String last_name;
    String address;
    String city;
    String state;
    int zip;
    String phone_number;
    String email;

    public Contact(String first_name, String last_name, String address, String city, String state, int zip,
            String phone_number, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone_number = phone_number;
        this.email = email;
    }

    public void printContact() {
        System.out.println("First Name: " + this.first_name);
        System.out.println("Last Name: " + this.last_name);
        System.out.println("Phone Number: " + this.phone_number);
        System.out.println("Address: " + this.address);
        System.out.println("City: " + this.city);
        System.out.println("State: " + this.state);
        System.out.println("Zip code: " + this.zip);
        System.out.println("Email: " + this.email);
    }
}
