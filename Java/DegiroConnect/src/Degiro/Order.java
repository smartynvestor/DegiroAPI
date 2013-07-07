package Degiro;

/**
 * Created
 * Date: 7/7/13
 * Time: 6:26 PM
 */
public class Order {

    static final int BUY = 0;
    static final int SELL = 1;
    static final int ACTION_DEFAULT = 0;
    static final int ORDERTYPE_DEFAULT = 0;
    static final int PRICETYPE_DEFAULT = 1;
    static final int TIMETYPE_GTD = 1;

    public Order(int _pid,
                 int _action,
                 int _buysell,
                 int _size,
                 double _price,
                 int _ordertype,
                 int _pricetype,
                 int _timetype,
                 int _disclosedsize)
    {
        pid=_pid;
        action=_action;
        buysell=_buysell;
        size=_size;
        price=_price;
        ordertype=_ordertype;
        pricetype=_pricetype;
        timetype=_timetype;
        disclosedsize=_disclosedsize;
    }

    static Order createOrderLimitGTD(int productId, int size, double price)
    {
        Order order = new Order(
                productId,
                DegiroConnect.ACTION_DEFAULT,
                DegiroConnect.SELL,
                size,
                price,
                DegiroConnect.ORDERTYPE_DEFAULT,
                DegiroConnect.PRICETYPE_DEFAULT,
                DegiroConnect.TIMETYPE_GTD,
                size);
        return order;

    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public void setBuysell(int buysell) {
        this.buysell = buysell;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }

    public void setPricetype(int pricetype) {
        this.pricetype = pricetype;
    }

    public void setTimetype(int timetype) {
        this.timetype = timetype;
    }

    public void setDisclosedsize(int disclosedsize) {
        this.disclosedsize = disclosedsize;
    }

    public int getPid() {
        return pid;
    }

    public int getAction() {
        return action;
    }

    public int getBuysell() {
        return buysell;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public int getOrdertype() {
        return ordertype;
    }

    public int getPricetype() {
        return pricetype;
    }

    public int getTimetype() {
        return timetype;
    }

    public int getDisclosedsize() {
        return disclosedsize;
    }

    int pid;
    int action;
    int buysell;
    int size;
    double price;
    int ordertype;
    int pricetype;
    int timetype;
    int disclosedsize;

}
