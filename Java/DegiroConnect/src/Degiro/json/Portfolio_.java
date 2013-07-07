
package Degiro.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "lastUpdated",
    "name",
    "value",
    "isAdded"
})
public class Portfolio_ {

    @JsonProperty("lastUpdated")
    private Integer lastUpdated;
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private List<Value> value = new ArrayList<Value>();
    @JsonProperty("isAdded")
    private Boolean isAdded;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("lastUpdated")
    public Integer getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("lastUpdated")
    public void setLastUpdated(Integer lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("value")
    public List<Value> getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(List<Value> value) {
        this.value = value;
    }

    @JsonProperty("isAdded")
    public Boolean getIsAdded() {
        return isAdded;
    }

    @JsonProperty("isAdded")
    public void setIsAdded(Boolean isAdded) {
        this.isAdded = isAdded;
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
