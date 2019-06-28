package config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph Lumpkin and Chad Jones
 */

/*
Use this class to load the config.txt file for database 
configuration after the java program has already been compiled.
*/
public class ConfigLoader {
        
        //To read the files
        FileReader fr;
        BufferedReader br;
        
        //Initializing the configuration variables outside the try catch.
        String str;
        private String dbURL, dbUser, dbPass;
        
        //To hold the information read from the while loop.
        StringBuilder sb = new StringBuilder();
        {
        try {
            fr = new FileReader(
                    "src/config/config.txt");
            br = new BufferedReader(fr);
            if(br != null){
                try {
                    /*
                    To read the values of config.txt and import them to the 
                    Stringbuilder.
                    */
                    while((str = br.readLine()) != null){
                        /*
                        If the line starts with a "//", the loop will skip to 
                        the next line. So we can comment the config.txt and 
                        say where all the info needs to go. 
                        (It will be in a specific order.)
                        */
                        if(str.startsWith("//")){
                            continue;
                        }
                        else{
                            sb.append(str+",");
                        }
                    }
                }
                catch (IOException ex) {
                        Logger.getLogger(ConfigLoader.class.getName())
                                .log(Level.SEVERE, null, ex);
                        }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigLoader.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        //Converting the config StringBuilder to a String.
        String sbString = sb.toString();
        //Splitting the config String to a String[] with a comma delimiter.
        String[] configSplit = sbString.split(",");
        
        //Assigning variables
        dbURL = configSplit[0];
        dbUser = configSplit[1];
        dbPass = configSplit[2];
    }
        
    public String getDbURL() {
    return dbURL;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPass() {
        return dbPass;
    }
}