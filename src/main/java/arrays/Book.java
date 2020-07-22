package main.java.arrays;

public class Book {
    private int ISBN;
    Book(int i) {
        ISBN = i;
    }

    @Override
    public int hashCode(){
        return ISBN;
    }

}
