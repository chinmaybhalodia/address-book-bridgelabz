package Exceptions;

public class InvalidLastNameException extends Exception {
    public InvalidLastNameException() {
        super("Last name is invalid");
    }
}
