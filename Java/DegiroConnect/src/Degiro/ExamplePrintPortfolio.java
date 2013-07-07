package Degiro;

/**
 * Print portfolio at Degiro
 */
public class ExamplePrintPortfolio {

    public static void main(String [ ] args)
    {
        PropertiesReader credentials = new PropertiesReader();
        credentials.read(DegiroConnect.USER_CREDENTIALS_FILENAME_DEFAULT);
        DegiroConnect degiro = new DegiroConnect(credentials);
        degiro.login();
        Portfolio p = degiro.getPortfolio();
        System.out.println(p.toString());
    }
}
