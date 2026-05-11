package ir.aminahmadi24.exceptions;

public class ProductNotFoundException extends RuntimeException
{
    public ProductNotFoundException(){
        super("Product not found");
    }
}
