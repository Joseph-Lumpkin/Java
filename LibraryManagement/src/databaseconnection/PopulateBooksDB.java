package databaseconnection;

import librarymanagement.classes.Book;

/**
 *
 * @author Joseph Lumpkin & Chad Jones
 * A class to populate the database with some basic books.
 * This is not used in the program at all and can be deleted.
 */
public class PopulateBooksDB {
    public static void main(String[] args) throws Exception {
        
        Book bookOne = new Book("Italian Without Words", "Don Cangelosi", "Mystery", 32, 17, 12345);
        Book bookTwo = new Book("How to Raise Your I.Q. by Eating Gifted Children", "Lewis Frumkes", "Sci-Fi", 100, 80, 98765);
        Book bookThree = new Book("The Lord of the Rings", "J. R. R. Tolkien", "Fantasy", 8, 3, 25367);
        Book bookFour = new Book("Lusty Argonian Maid", "Crassius Curio", "Thriller", 45, 15, 16661);
        Book bookFive = new Book("Beowulf", "Seamus Heaney", "Thriller", 67, 49, 94503);
        Book bookSix = new Book("The Canterbury Tales", "Geoffrey Chaucer", "Fantasy", 45, 30, 75482);
        Book bookSeven = new Book("Ender''s Game", "Orson Scott Card", "Fantasy", 50, 25, 90645);
        Book bookEight = new Book("Starting Out With Java", "Godfrey Muganda", "Sci-Fi", 175, 174, 87530);
        Book bookNine = new Book("Empress Theresa", "Norman Boutin", "Mystery", 2, 1, 80085);
        Book bookTen = new Book("Another Hope", "Lori Jareo", "Sci-Fi", 14, 7, 50723); 
       
        BooksDB bdb = new BooksDB();
        
        bdb.addBook(bookOne);
        bdb.addBook(bookTwo);
        bdb.addBook(bookThree);
        bdb.addBook(bookFour);
        bdb.addBook(bookFive);
        bdb.addBook(bookSix);
        bdb.addBook(bookSeven);
        bdb.addBook(bookEight);
        bdb.addBook(bookNine);
        bdb.addBook(bookTen);
        
        DBConnection.db.connection.close();
        bdb = null;
    }
}