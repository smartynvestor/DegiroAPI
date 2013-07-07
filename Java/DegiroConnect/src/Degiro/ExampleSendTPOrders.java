package Degiro;

/**
 * Created
 * Date: 7/7/13
 * Time: 6:09 PM
 */
public class ExampleSendTPOrders {

    public static void main(String [ ] args)
    {
        PropertiesReader credentials = new PropertiesReader();
        credentials.read(DegiroConnect.USER_CREDENTIALS_FILENAME_DEFAULT);
        DegiroConnect degiro = new DegiroConnect(credentials);
        ExampleSendTPOrders example = new ExampleSendTPOrders(degiro);
        example.sendTakeProfitOrdersForWholePortfolioWithProfitOf(1.1);
    }

    public ExampleSendTPOrders(DegiroConnect _degiro)
    {
        degiro = _degiro;
    }

    public void sendTakeProfitOrdersForWholePortfolioWithProfitOf( double _profitFactorRelativeToBuyPrice)
    {
        Portfolio p = degiro.getPortfolio();
        int stocksInPortfolio = p.size();
        for(int i=0; i<stocksInPortfolio; i++)
        {
            int productId = p.getProductId(i);
            String name = p.getStockName(i);
            double size = p.getStockSize(i);
            int sizeInt = (int)Math.round(size);
            double price = p.getStockPrice(i);
            double pl = p.getStockPL(i);
            double profitPerShare = pl / size;
            double buyPrice = price-profitPerShare;
            double takeProfitRaw = _profitFactorRelativeToBuyPrice * buyPrice;
            int TPInCent = (int)(takeProfitRaw / 0.01);
            double newTP = ((double)TPInCent) * 0.01;

//            System.out.println("pid="+productId+" name="+name+" size="+size+" price="+price+" pl="+pl+ " tp="+newTP);

            Order order = Order.createOrderLimitGTD(productId, sizeInt, newTP);
//            Order order = new Order(
//                    productId,
//                    DegiroConnect.ACTION_DEFAULT,
//                    DegiroConnect.SELL,
//                    sizeInt,
//                    newTP,
//                    DegiroConnect.ORDERTYPE_DEFAULT,
//                    DegiroConnect.PRICETYPE_DEFAULT,
//                    DegiroConnect.TIMETYPE_GTD,
//                    sizeInt
//            );

            degiro.sendOrder(order);
        }

    }

    private DegiroConnect degiro;
}
