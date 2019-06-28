package librarymanagement.datav;

import static databaseconnection.DBConnection.db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Joseph Lumpkin & Chad Jones
 */
public class EmailCheck {
    
    //Method to make sure the new emails fit the criteria
    public static boolean checkNewEmail(String email) throws SQLException{
        /*
        Check to make sure the email is a real email and that is doesn't exist.
        This returns yes if you're good to use that email.
        */
        if(checkDomain(email) && !checkEmailExists(email)){
            return true;
        }
        //If email exists, tell the user.
        if(checkEmailExists(email)){
            JOptionPane.showMessageDialog(null, 
                    "Sorry, that email already exists.");
        }
        return false;
    }
    
    //A method to make sure the email contains a domain.
    public static boolean checkDomain(String email){
        boolean atchar = false, domain = false;
        char[] c = email.toCharArray();
        //Make sure it has an '@' character.
        for(char i : c){
            if(i == '@'){
                atchar = true;
            }
        }
        //Make sure it ends with a domain name.
        if(email.contains(".com") 
                || email.contains(".net") 
                || email.contains(".org")){
            domain = true;
        }
        //If both checks are good, return true.
        if(atchar && domain){
            return true;
        }
        /*
        If the email doesn't meet the criteria, tell the user and return false.
        */
        JOptionPane.showMessageDialog(null, 
                    "Sorry you've entered an invalid entry.");
        return false;
    }
    
    //A method to check if the email exists
    public static boolean checkEmailExists(String email) throws SQLException{
        //Setting and executing the SQL statement to pull all users.
        String query = "SELECT * FROM Users";
        Statement st = db.connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            /*
            If the email entered is an email in the system,
            this method will return true for yes it does exist.
            */
            if(email.equals(rs.getString("Email"))){
                rs.close();
                st.close();
                return true;
            }
        }
        rs.close();
        st.close();
        return false;
    }
}