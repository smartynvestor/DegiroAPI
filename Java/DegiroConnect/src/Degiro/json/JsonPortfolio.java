
package Degiro.json;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "portfolio",
    "orders",
    "transactions",
    "totalPortfolio"
})
public class JsonPortfolio {

    @JsonProperty("portfolio")
    private Portfolio_ portfolio;
    @JsonProperty("orders")
    private Orders orders;
    @JsonProperty("transactions")
    private Transactions transactions;
    @JsonProperty("totalPortfolio")
    private TotalPortfolio totalPortfolio;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("portfolio")
    public Portfolio_ getPortfolio() {
        return portfolio;
    }

    @JsonProperty("portfolio")
    public void setPortfolio(Portfolio_ portfolio) {
        this.portfolio = portfolio;
    }

    @JsonProperty("orders")
    public Orders getOrders() {
        return orders;
    }

    @JsonProperty("orders")
    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @JsonProperty("transactions")
    public Transactions getTransactions() {
        return transactions;
    }

    @JsonProperty("transactions")
    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    @JsonProperty("totalPortfolio")
    public TotalPortfolio getTotalPortfolio() {
        return totalPortfolio;
    }

    @JsonProperty("totalPortfolio")
    public void setTotalPortfolio(TotalPortfolio totalPortfolio) {
        this.totalPortfolio = totalPortfolio;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
