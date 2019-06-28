package databaseconnection;

import java.io.IOException;
import java.sql.SQLException;
import librarymanagement.datav.RegisterUser;
/**
 * @author Joseph Lumpkin & Chad Jones
 * A class to populate the database with some basic users.
 * This is not used in the program at all and can be deleted.
 */

public class PopulateUserDB {
    public static void main(String[] args) throws IOException, SQLException {
        RegisterUser.registerUser("BartMan76254", "eat1pant", "bMan@yahoo.com", "Bart", "Simpson");
        RegisterUser.registerUser("AbrahamLinksys", "4sCore7Yrs", "aLink@gmail.com", "Abraham", "Lincoln");
        RegisterUser.registerUser("JohnDarksoul236", "timeforCrab5", "DarkJohnny@hotmail.com", "John", "Darksoul");
        RegisterUser.registerUser("JohnCena", "cantseeme", "Outof@nowhere.rko", "John", "Cena");
        RegisterUser.registerUser("rustyshakleford1", "pocketsand123", "sparkyWilson@aol.com", "Dale", "Gribble");
        RegisterUser.registerUser("Userone", "sesame1", "qwe@gmail.com", "John", "Smith");
    }
}