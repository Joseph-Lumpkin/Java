package librarymanagement.datav;
/**
 *
 * @author Chad Jones and Joseph Lumpkin
 */

public class NewPasswordCheck {
    
    //A method to run all the checks involved with the password
    //A password must be between 6 and 16 characters
    //The password must also contain at least one letter and one digit.
    //This will return true if the new password fits the criteria.
    public static boolean checkNewPassword(String passOne, String passTwo){
        if((checkMatch(passOne, passTwo) == true) 
                && (checkLength(passOne) == true) 
                && (checkForDigit(passOne) == true) 
                && (checkForLetter(passOne) == true)){
            return true;
        }
        return false;
    }
    
    //A method to make sure the new password Strings match.
    public static boolean checkMatch(String passOne, String passTwo){
        if(passOne.equals(passTwo)){
            return true;
        }
        return false;
    }
    
    //A method to make sure the password length is at least 6 characters,
    //but no more than 16 characters.
    public static boolean checkLength(String password){
        if(password.length() >= 6 && password.length() <=16){
            return true;
        }
        return false;
    }
    
    //A method to verify there is a digit in the password.
    public static boolean checkForDigit(String password){
        for(char index : password.toCharArray()){
            if (Character.isDigit(index)){
                return true;
            }
        }
        return false;
    }
    
    //A method to verify there is a letter in the password.
    public static boolean checkForLetter(String password){
        char[] a = password.toCharArray();
        for (char index : a){
            if (Character.isLetter(index)){
                return true;
            }
        }
        return false;
    }
}