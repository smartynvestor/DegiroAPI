package Degiro;

/**
 * Created
 * Date: 7/7/13
 * Time: 5:36 PM
 */

import Degiro.json.JsonPortfolio;
import Degiro.json.Value_;

import java.util.List;

public class Portfolio {

    public Portfolio(JsonPortfolio _p) {
        p=_p;
    }

    public int getProductId(int stockIndex)
    {
        int pid = Integer.parseInt(getPortfolioList().get(stockIndex).getValue().get(0).getValue());
        return pid;
    }
    public String getStockName(int stockIndex)
    {
        return getPortfolioList().get(stockIndex).getValue().get(1).getValue();
    }
    public double getStockSize(int stockIndex)
    {
        double size = Integer.parseInt(getPortfolioList().get(stockIndex).getValue().get(2).getValue());
        return size;
    }
    public double getStockPrice(int stockIndex)
    {
        double price = Double.parseDouble(getPortfolioList().get(stockIndex).getValue().get(3).getValue());
        return price;
    }
    public double getStockPL(int stockIndex)
    {
        double pl = Double.parseDouble(getPortfolioList().get(stockIndex).getValue().get(7).getValue());
        return pl;
    }

    public int size()
    {
        return getPortfolioList().size();
    }

    public String toString()
    {
        StringBuilder b = new StringBuilder();
        for(int i=0; i<size(); i++)
        {
            b       .append("name=").append(getStockName(i))
                    .append(", pid=").append(getProductId(i))
                    .append(", price=").append(getStockPrice(i))
                    .append(", size=").append(getStockSize(i))
                    .append(", profit=").append(getStockPL(i))
                    .append("\n");
        }
        return b.toString();
    }

    private List<Value_> getPortfolioList()
    {
        return p.getPortfolio().getValue().get(0).getValue();
    }


    private JsonPortfolio p;
}
