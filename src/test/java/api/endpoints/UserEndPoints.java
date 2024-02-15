package api.endpoints;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import api.globals.GlobalVars;
import api.payload.OrderPOJO;
import api.payload.RegisterClientPOJO;
import api.payload.UpdatePOJO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//To perform crud operations
//Created for perform Create,Read,Update and Delete requests the user API
public class UserEndPoints {

	public static final ObjectMapper MAPPER = new ObjectMapper();

	public static Response generateApiKey() throws JsonProcessingException {
		Faker faker=new Faker();
		RegisterClientPOJO registerapi = new RegisterClientPOJO();
		//registerapi.setClientName("ammamma11");
		//registerapi.setClientEmail("ammamma11@email.com");
		registerapi.setClientName(faker.name().username());
		registerapi.setClientEmail(faker.internet().emailAddress());

		String url = "https://simple-books-api.glitch.me/api-clients/";
		String json = MAPPER.writeValueAsString(registerapi);
		Response response = RestAssured.given().header("Authorization", "Bearer " + GlobalVars.token)
				.contentType("application/json").log().all(true).body(json).post(url).andReturn();
		return response;

	}

	public static Response submitOrder() throws JsonProcessingException {

		OrderPOJO o = new OrderPOJO();
		o.setBookId(1);
		o.setCustomerName("Kennedy");
		String Url = "https://simple-books-api.glitch.me/orders";

		// coverting pojo to json
		String json = MAPPER.writeValueAsString(o);
		Response response = RestAssured
				.given()
				.header("Authorization", "Bearer " + GlobalVars.token)
				.contentType("application/json")
				.log()
				.all(true)
				.body(json)
				.post(Url)
				.andReturn();
		return response;

	}

	public static Response getOrder(String orderid) {
		Response response = given().header("Authorization", "Bearer " + GlobalVars.token).pathParam("orderId", orderid)
				.when().get(Routes.get_url);
		return response;
	}

	public static Response updateOrder(String orderid) throws JsonProcessingException {

		UpdatePOJO up = new UpdatePOJO();
		up.setCustomerName("KennedyUpdate");
		String url = "https://simple-books-api.glitch.me/orders/{orderId}";
		String json = MAPPER.writeValueAsString(up);
		Response response = RestAssured.given().header("Authorization", "Bearer " + GlobalVars.token)
				.contentType("application/json").accept(ContentType.JSON).pathParam("orderId", orderid).log().all(true)
				.body(json).patch(url).andReturn();
		return response;
	}

	public static Response deleteOrder(int orderid) {
		Response response = given().pathParam("orderId", orderid).when().delete(Routes.delete_url);
		return response;
	}
}
