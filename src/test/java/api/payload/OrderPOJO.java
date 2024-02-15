package api.payload;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"bookId",
"customerName"
})

public class OrderPOJO {

@JsonProperty("bookId")
private Integer bookId;
@JsonProperty("customerName")
private String customerName;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("bookId")
public Integer getBookId() {
return bookId;
}

@JsonProperty("bookId")
public void setBookId(Integer bookId) {
this.bookId = bookId;
}

@JsonProperty("customerName")
public String getCustomerName() {
return customerName;
}

@JsonProperty("customerName")
public void setCustomerName(String customerName) {
this.customerName = customerName;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}