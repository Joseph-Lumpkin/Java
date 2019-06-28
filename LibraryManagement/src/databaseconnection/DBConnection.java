package databaseconnection;

import config.ConfigLoader;
import java.sql.*;
import java.util.ArrayList;
import librarymanagement.classes.users.User;

/**
 * @author Joseph Lumpkin & Chad Jones
 * A class to hold the database's connection. This is a basic pool and the rest
 * of the code will refer to this DBConnection for interaction with the database.
 */
public class DBConnection {
    /*
    Establish the database connection using the information collected from the
    ConfigLoader.
    */
    public static DBConnection db = new DBConnection();
    ArrayList<User> users = new ArrayList();
    public Connection connection;
    {
        System.out.println("Loading database...");
        
        
        //The below statement loads the config file.
        ConfigLoader cl = new ConfigLoader();

        /*
        Try to establish a connection to the database with the settings in the 
        config.txt of the ConfigLoader class.
        */
        try{
            connection = DriverManager.getConnection(
               cl.getDbURL(), cl.getDbUser(), cl.getDbPass());
        }
        catch(SQLException e){
            for(Throwable t : e){
                t.printStackTrace();
            }
        }
        System.out.println("Database loaded!");
        System.out.println();
    }
}
