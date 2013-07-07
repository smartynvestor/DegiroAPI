
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
    "title",
    "name",
    "id",
    "value",
    "isAdded"
})
public class Value {

    @JsonProperty("title")
    private String title;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("value")
    private List<Value_> value = new ArrayList<Value_>();
    @JsonProperty("isAdded")
    private Boolean isAdded;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("value")
    public List<Value_> getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(List<Value_> value) {
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
