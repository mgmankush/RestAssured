package com.formula1;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
public class FormulaF1StepDef {
	Response resp;
	
	@Given("^user start the rest assured test$")
	public void user_start_the_rest_asured_test() {
		System.out.println("Started the test !");
	}
	
	@When("^user hit the get request$")
	public void user_hit_the_get_request(){
		
		//Response resp = 
		resp = RestAssured.given().relaxedHTTPSValidation().
					when().get("https://ergast.com/api/f1/2017/circuits.json");
		
		//print response in string
		String respString = resp.asString();
		System.out.println(respString);
		
		//get all headers
		Headers heds = resp.getHeaders();
		System.out.println(heds);
		
	}
	
	@Then("^user checks the count or size$")
	public void user_checks_the_count_or_size(){
		
		resp.then().assertThat().body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
		
		String limitValue = resp.getBody().jsonPath().getString("MRData.limit");
		Assert.assertEquals("30", limitValue);
		
		
		//verify the size
		resp.then().assertThat().body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
	}
	
	@Then("^user checks at path \\\"([^\\\"]*)\\\" with the \\\"([^\\\"]*)\\\" value$")
	public void user_checks_at_path_with_the_value(String path, String expValue){
		String actualCircuitValue = resp.getBody().jsonPath().getString(path);
		Assert.assertEquals(expValue, actualCircuitValue);
	}
	

}
