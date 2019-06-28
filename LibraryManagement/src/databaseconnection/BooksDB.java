package databaseconnection;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import librarymanagement.BrowserJFrame;
import librarymanagement.classes.Book;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author Joseph Lumpkin & Chad Jones
 *
 * This is a class to store most of the methods for managing the books in
 * the database.
*/

public class BooksDB {
    
    public static ArrayList<Book> books;
    
    public BooksDB() throws SQLException, IOException, ClassNotFoundException{
        updateBooksTable();
    }
    
    //A method to add a book to the databse. (Blob and String data)
    public static void addBook(Book a) 
            throws FileNotFoundException, IOException, SQLException{
        File file = new File("src/databaseconnection/Book.txt");
        PrintWriter write = new PrintWriter(file);
        
        //Clearing the Book.txt file
        write.print("");
        write.close();
        
        //Block of code below to write a Book object to a file in binary.
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(a);
        oos.flush();
        oos.close();
        
        //Establishing SQL write statements
        String sqlStrings = "INSERT INTO Books VALUES ("
                + "'" + a.getProductCode()+"', "
                + "'" + a.getTitle()+"', "
                + "'" + a.getGenre()+"', "
                + "'" + a.getAuthor()+"', "
                + "'null')";
        
        String sqlBook = "update Books set Book=? "
                +"where Product_Code='"+a.getProductCode()+"'";
        
        PreparedStatement bookStmt = 
                DBConnection.db.connection.prepareStatement(sqlBook);
        
        Statement st = DBConnection.db.connection.createStatement();
        st.execute(sqlStrings);
        
        //Input the binary data to database
        FileInputStream input = new FileInputStream(file);
        bookStmt.setBinaryStream(1, input);
        bookStmt.executeUpdate();
        
        //Clear the Book.txt file of all data. Initialized in the beginning.
        write.print("");
        write.close();
    }
    
    //A method to update books in the database. (Blob and String data)
    public static void updateBook(Book a) 
            throws FileNotFoundException, IOException, SQLException{
        
        File file = new File("src/databaseconnection/Book.txt");
        PrintWriter write = new PrintWriter(file);
        
        //Clearing the Book.txt file
        write.print("");
        write.close();
        
        /*
        Block of code below to write a Book object to a file.
        This will prepare the Book object to be streamed to the database
        as a binary object.
        */
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(a);
        oos.flush();
        oos.close();
        
        //Establishing SQL write statements.
        String sqlStrings = "UPDATE Books set Title = '"+a.getTitle()+
                "', Genre = '"+a.getGenre()+
                "', Author = '"+a.getAuthor()+
                "' where Product_Code = "+a.getProductCode()+";";
                
        String sqlBook = "update Books set Book=? "
                +"where Product_Code='"+a.getProductCode()+"'";
        
        
        PreparedStatement bookStmt = 
                DBConnection.db.connection.prepareStatement(sqlBook);
        
        Statement st = DBConnection.db.connection.createStatement();
        st.execute(sqlStrings);
        
        //Input the binary data to database.
        FileInputStream input = new FileInputStream(file);
        bookStmt.setBinaryStream(1, input);
        bookStmt.executeUpdate();
        
        //Clear the Book.txt file of all data. Initialized in the beginning.
        write.print("");
        write.close();
    }
    
    //A method for setting and updating the Books table on the BrowserJFrame.
    public static void updateBooksTable() 
            throws SQLException, IOException, ClassNotFoundException {
        
        try{
            String query = "SELECT * FROM Books";
            PreparedStatement myStmt =
                    DBConnection.db.connection.prepareStatement(query);
            ResultSet rs = myStmt.executeQuery(query);
            
            //Utilizing the amazing rs2xml lib down below.
            BrowserJFrame.bookTable
                    .setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            System.out.println(e+"\n");
        }
    }
    
    /*
    A method to check out books from the database.
    This verifies the input data as being a match for something that is indeed
    in the database, then attaches the book object to the array of
    checkedOutBooks stored in the user's class.
    */
    public static void checkOutBook(String productID) 
            throws SQLException, IOException, ClassNotFoundException{
        //Establishing SQL query for verification of book's existence.
        String query = "SELECT Book FROM Books"
                +" where Product_Code = '"+ productID+"'";
        PreparedStatement myStmt = 
                DBConnection.db.connection.prepareStatement(query);
        //Querying the database...
        ResultSet rs = myStmt.executeQuery(query);
        
        /*
        A loop through the result set to make sure the book exists.
        Once it finds the book, it will import the book object for attachment to
        the User class that is currently logged in.
        It will also check for the genre in the book object and increment that
        user's preference score.
        */
        while (rs.next()){
            byte[] sto = (byte[]) rs.getObject("Book");
            ByteArrayInputStream baip = new ByteArrayInputStream(sto);
            ObjectInputStream ois = new ObjectInputStream(baip);
            Book book = (Book) ois.readObject();
            BrowserJFrame.user.addBook(book);
            if(book.getGenre().equals("Sci-Fi") 
                    && (BrowserJFrame.user.ga.getScienceFiction()<10)){
                BrowserJFrame.user.ga.incSciFi();
            }
            if(book.getGenre().equals("Fantasy") 
                    && (BrowserJFrame.user.ga.getFantasy()<10)){
                BrowserJFrame.user.ga.incFantasy();
            }
            if(book.getGenre().equals("Thriller") 
                    && (BrowserJFrame.user.ga.getThriller()<10)){
                BrowserJFrame.user.ga.incThriller();
            }
            if(book.getGenre().equals("Mystery") 
                    && (BrowserJFrame.user.ga.getMystery()<10)){
                BrowserJFrame.user.ga.incMystery();
            }
        }
        rs.close();
    }
    
    //A method to delete a book from the database when given the product code.
    public static void deleteBook(String pid) throws SQLException{
        String delBook = "DELETE FROM `LibraryDB`.`Books` WHERE "
                + "(`Product_Code` = '"+pid+"');";
        PreparedStatement myStmt = 
                DBConnection.db.connection.prepareStatement(delBook);
        myStmt.execute(delBook);
        myStmt.close();
    }
}