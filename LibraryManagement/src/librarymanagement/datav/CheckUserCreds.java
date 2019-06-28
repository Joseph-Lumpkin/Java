package librarymanagement.datav;

import static databaseconnection.DBConnection.db;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import librarymanagement.classes.users.User;
/**
 *
 * @author Joseph Lumpkin & Chad Jones
 */

public class CheckUserCreds {
    public static User user;
    //A method to check for the correct email and password in the database.
    public static boolean checkCreds(String user, String pass) 
            throws SQLException{
        
        String query = "SELECT * FROM Users";
        Statement st = db.connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        boolean match = false;
        while (rs.next()){
            if(user.equals(rs.getString("Username")) && 
                    pass.equals(rs.getString("Passphrase"))){
                
                match = true;
            }
        }
        rs.close();
        st.close();
        return match;
    }
    
    //A method to initialize the user class object from the database.
    public static void loadUser(String username) 
            throws SQLException, IOException, ClassNotFoundException{
        String sql = "select UserObject from Users"
                + " where Username = '"+username+"'";
        PreparedStatement myStmt = db.connection.prepareStatement(sql);
        ResultSet rs = myStmt.executeQuery(sql);
        while (rs.next()) {
            byte[] sto = (byte[]) rs.getObject("UserObject");
            ByteArrayInputStream baip = new ByteArrayInputStream(sto);
            ObjectInputStream ois = new ObjectInputStream(baip);
            user = (User) ois.readObject();
        }
        rs.close();
    }
}