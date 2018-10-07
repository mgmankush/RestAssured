package com.javacodegeeks;

import static io.restassured.RestAssured.given;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import junit.framework.Assert;

public class Timepass {

	final static String ROOT_URI = "http://localhost:7000/employees";

	/*
	 * Response response = get(ROOT_URI + "/list");
	 * System.out.println(response.asString());
	 * 
	 * response.then().body("id", hasItems(1, 2)); response.then().body("name",
	 * hasItems("Pankaj"));
	 */

	Response resp;

	@Given("^user first hit the url$")
	public void user_first_hit_the_url() {
		resp = given().when().get("http://www.google.com");
	}

	@Then("user checks (\\d+) the response code")
	public void user_checks_the_response_code(int resCode) {
		int i = resp.getStatusCode();
		Assert.assertTrue(i == resCode);
	}

}
