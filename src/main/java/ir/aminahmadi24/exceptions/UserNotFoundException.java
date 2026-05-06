package ir.aminahmadi24.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("User not found.");
    }
}
