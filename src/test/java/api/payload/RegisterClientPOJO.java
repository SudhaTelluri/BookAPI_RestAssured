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
"clientName",
"clientEmail"
})

public class RegisterClientPOJO {

@JsonProperty("clientName")
private String clientName;
@JsonProperty("clientEmail")
private String clientEmail;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("clientName")
public String getClientName() {
return clientName;
}

@JsonProperty("clientName")
public void setClientName(String clientName) {
this.clientName = clientName;
}

@JsonProperty("clientEmail")
public String getClientEmail() {
return clientEmail;
}

@JsonProperty("clientEmail")
public void setClientEmail(String clientEmail) {
this.clientEmail = clientEmail;
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