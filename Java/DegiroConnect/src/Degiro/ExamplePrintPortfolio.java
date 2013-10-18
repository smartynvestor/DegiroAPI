package Degiro;

import java.io.IOException;

/**
 * Print portfolio at Degiro
 */
public class ExamplePrintPortfolio {

    public static void main(String [ ] args)
    {
        try {
            String current = new java.io.File( "." ).getCanonicalPath();
            System.out.println("Current dir:"+current);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        PropertiesReader credentials = new PropertiesReader();
        credentials.read(DegiroConnect.USER_CREDENTIALS_FILENAME_DEFAULT);
        DegiroConnect degiro = new DegiroConnect(credentials);
        degiro.login();
        Portfolio p = degiro.getPortfolio();
        System.out.println(p.toString());
    }
}
