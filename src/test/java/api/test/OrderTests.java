package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;

import api.endpoints.Routes;
import api.endpoints.UserEndPoints;
import api.globals.GlobalVars;
import api.payload.OrderPOJO;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OrderTests {

	// can we make this to cucumber bdd
	//Faker faker;
	String orderId;

	@BeforeClass
	public void setUpData() {
		//faker = new Faker();
	}

	@Test(priority = 1)
	public void apiKey01() throws JsonProcessingException {
		Response response = UserEndPoints.generateApiKey();
		GlobalVars.extractResponse = response.then().log().all().extract().response().asString();
		JsonPath path = new JsonPath(GlobalVars.extractResponse);
		GlobalVars.token = path.getString("accessToken");
		System.out.println("Access Token :" + GlobalVars.token);
	}

	@Test(priority = 2)
	public void testPostOrder02() throws JsonProcessingException {

		Response response = UserEndPoints.submitOrder();
		response.then().log().all();
		orderId = response.jsonPath().getString("orderId");

		// Print the captured orderId
		System.out.println("Captured Order ID: " + orderId);
		// System.out.println("Request Headers: " + given().header());
		// System.out.println("Request Payload: " + order);
		System.out.println("Request URL: " + Routes.post_url);

		Assert.assertEquals(response.getStatusCode(), 201);
	}

	@Test(priority = 3)
	public void testGetOrderByOrderid03() {

		Response response = UserEndPoints.getOrder(orderId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public void testUpdateOrderByOrderId04() throws JsonProcessingException {
		Response response = UserEndPoints.updateOrder(orderId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 204);
	}

	@Test(priority = 5)
	public void testDeleteOrderByOrderid05() throws JsonProcessingException {
		Response response = UserEndPoints.updateOrder(orderId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 204);

	}
}
