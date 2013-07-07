package Degiro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesReader implements Credentials {

    public PropertiesReader() {
    }

    // properties file needs to contain username and password in form
    // username=<username>
    // password=<password>
    public void read(String filename) {
        try {
            File file = new File(filename);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            Enumeration enuKeys = properties.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = properties.getProperty(key);
                if(key.startsWith("password")) password = value;
                if(key.startsWith("username")) username = value;
            }
            //System.out.println("user=<"+username+"> pw=<"+password+">");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private String username;
    private String password;

}