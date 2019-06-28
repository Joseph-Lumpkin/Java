package librarymanagement.classes.users;

import databaseconnection.DBConnection;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;
import librarymanagement.classes.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Chad Jones & Joseph Lumpkin
 */

public class User implements Serializable{
    public ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
    public GenreAlgorithm ga = new GenreAlgorithm();
    private String username;
    private String password;
    private String email;
    private String fName;
    private String lName;
    private String recommendedBook = null;
    //This constructor does not initialize a checkOutBooks ArrayList as these 
    //should be added after account creation.
    public User(String username, 
            String password, 
            String email, 
            String fName, 
            String lName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
    }


    //A method to print the list of books in neatly formatted String.
    public String booksToString(){
        StringBuilder a = new StringBuilder();
        for (Book index : getCheckedOutBooks()){
            int counter = 1;
            a.append(index.getTitle() + " - " + index.getAuthor());
            if(counter > getCheckedOutBooks().size()){
                a.append(", ");
            }
        }
        a.append(".");
        return a.toString();
    }
    
    //A method to add a book to the list of checked out books.
    public void addBook(Book a){
        checkedOutBooks.add(a);
    }
    
    //A method to remove a book from the list of checked out books.
    public void removeBook(String a){
        for (Book index : this.checkedOutBooks){
            String pid = ""+index.getProductCode();
            if (pid.equals(a)){
                this.checkedOutBooks.remove(index);
                break;
            }
        }
    }
    //A method for the recommend book button.
    public String recommendBook() 
            throws SQLException, IOException, ClassNotFoundException{
        String recommendation = null;
        /*
        if statements to test what the user's most preferred genre is based
        of of their check out history.
        */
        if((ga.sciFi >= ga.thriller) 
                && (ga.sciFi >= ga.mystery) 
                && (ga.sciFi >= ga.fantasy)){
            recommendation = "Sci-Fi";
        }
        if((ga.thriller >= ga.sciFi) 
                && (ga.thriller >= ga.mystery) 
                && (ga.thriller >= ga.fantasy)){
            recommendation = "Thriller";
        }
        if((ga.mystery >= ga.thriller) 
                && (ga.mystery >= ga.sciFi) 
                && (ga.mystery >= ga.fantasy)){
            recommendation = "Mystery";
        }
        if((ga.fantasy >= ga.thriller) 
                && (ga.fantasy >= ga.mystery) 
                && (ga.fantasy >= ga.sciFi)){
            recommendation = "Fantasy";
        }
        
        /*
        Pulling books from the database as an array list. Adding those books 
        that match the preferred genre to an ArrayList<Book> for randomizing a
        result.
        */
        ArrayList<Book> books = new ArrayList<Book>();
        String query = "SELECT Book FROM Books"
                +" where Genre = '"+ recommendation+"'";
        PreparedStatement myStmt = 
                DBConnection.db.connection.prepareStatement(query);
        ResultSet rs = myStmt.executeQuery(query);
        while (rs.next()){
            byte[] sto = (byte[]) rs.getObject("Book");
            ByteArrayInputStream baip = new ByteArrayInputStream(sto);
            ObjectInputStream ois = new ObjectInputStream(baip);
            Book book = (Book) ois.readObject();
            books.add(book);
        }
        /*
        Making a random index number for the ArrayList<Book> of matching genres.
        */
        int a = new Random().nextInt(books.size());
        //Grabbing the book from the array.
        Book recoBook = books.get(a);
        recommendation = recoBook.getTitle();
        /*
        Setting the product code of the recommendedBook
        variable stored in this class.
        */
        recommendedBook = ""+recoBook.getProductCode();
        //Returning the recommended title.
        return recommendation;
    }
    
    //A method to update the user class and Strings stored in the database.
    public static void updateUser(User a) 
            throws FileNotFoundException, IOException, SQLException{
        File file = new File("src/librarymanagement/classes/users/user.txt");
        PrintWriter write = new PrintWriter(file);
        
        //Clearing the user.txt file
        write.print("");
        
        //Block of code below to write a User object to a file.
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(a);
        oos.flush();
        oos.close();
        fos.close();
        
        //Establish SQL write statements
        String sqlStrings = "UPDATE Users set Email = '"+a.getEmail()+
                "', Username = '"+a.getUsername()+
                "', FirstName = '"+a.getfName()+
                "', LastName = '"+a.getlName()+
                "', Passphrase = '"+a.getPassword()+
                "' where Username = '"+a.getUsername()+"';";
                
        String sqlUser = "update Users set UserObject=? "
                +"where Username='"+a.getUsername()+"'";
        
        
        PreparedStatement userStmt = 
                DBConnection.db.connection.prepareStatement(sqlUser);
        Statement st = DBConnection.db.connection.createStatement();
        st.execute(sqlStrings);
        
        //Input the binary data to database
        FileInputStream input = new FileInputStream(file);
        userStmt.setBinaryStream(1, input);
        userStmt.executeUpdate();
        
        //Clear the user.txt file of all data. Initialized in the beginning.
        //Also closing all DB statements.
        write.print("");
        write.close();
        userStmt.close();
        input.close();
        st.close();
    }
    //------------------------------------------------------------------------//
    //Methods to set preferences
    //Methods to prefer a genre
    public void preferSciFi(){
        for(int index = 1; index <=5; index++){
            ga.incSciFi();
        }
    }
    
    public void preferThriller(){
        for(int index = 1; index <=5; index++){
            ga.incThriller();
        }
    }
    
    public void preferMystery(){
        for(int index = 1; index <=5; index++){
            ga.incMystery();
        }
    }
    
    public void preferFantasy(){
        for(int index = 1; index <=5; index++){
            ga.incFantasy();
        }
    }
    //------------------------------------------------------------------------//
    //Methods to dislike a genre
    public void dislikeSciFi(){
        for(int index = 1; index <=5; index++){
            ga.decSciFi();
        }
    }
    
    public void dislikeThriller(){
        for(int index = 1; index <=5; index++){
            ga.decThriller();
        }
    }
    
    public void dislikeMystery(){
        for(int index = 1; index <=5; index++){
            ga.decMystery();
        }
    }
    
    public void dislikeFantasy(){
        for(int index = 1; index <=5; index++){
            ga.decFantasy();
        }
    }
    
    //------------------------------------------------------------------------//
    //Basic getters and setters.
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public ArrayList<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public void setCheckedOutBooks(ArrayList<Book> checkedOutBooks) {
        this.checkedOutBooks = checkedOutBooks;
    }

    public String getRecommendedBook() {
        return recommendedBook;
    }

    public void setRecommendedBook(String recommendedBook) {
        this.recommendedBook = recommendedBook;
    }
}