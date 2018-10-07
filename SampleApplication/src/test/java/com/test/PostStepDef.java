package com.test;

import java.io.IOException;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class PostStepDef {

	Response resp;

	@Given("^user hit the post request from String$")
	public void user_hit_the_post_request_with_String() throws IOException {

		resp = RestAssured.given().accept(ContentType.JSON).relaxedHTTPSValidation().when()
				.body("{    \"name\": \"Tom\",    \"job\":\"Actor\"}").post("https://reqres.in/api/users");
		resp.then().log().all();
		resp.then().log().status();
		System.out.println("=====" + resp.asString());

	}

	@Then("user validate the id is not null")
	public void user_validate_the_id_is_not_null() {

		// Check id is not null
		resp.then().assertThat().body("id", notNullValue());

		// check id is greater than zero
		String idValue = resp.getBody().jsonPath().get("id");
		int idInt = Integer.parseInt(idValue);
		Assert.assertTrue(idInt > 0);

	}

}
