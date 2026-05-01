package ir.aminahmadi24.exceptions;

public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException(){
        super("Email is duplicated");
    }

}
