package ir.aminahmadi24.exceptions;

public class DuplicatedCategoryException extends RuntimeException{
    public DuplicatedCategoryException(){
        super("Category already exists.");
    }
}
