package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigReaderManagerInputStream;
import com.api.utils.SpecUtils;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	
	@Test
	public void verifyCountAPIResponse()
	{
		RestAssured
		.given()
			.spec(SpecUtils.requestSpecificationWithAuthorizationHeader(Roles.FRONTDESK))
		.when()
			.get("/dashboard/count")
		.then()
			.spec(SpecUtils.responseSpecification())
			.body("data", Matchers.notNullValue())
			.body("data.size()", Matchers.equalTo(3))
			.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
			.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/countAPIResponseJsonSchema_FD.json"))
			.body("data.key", Matchers.containsInAnyOrder("pending_fst_assignment","created_today","pending_for_delivery"));	
	}
	
	
	@Test
	public void verifyResponseWithNoAuthenticationHeader()
	{
		RestAssured
			.given()
				.spec(SpecUtils.requestSpecification())			//no header being attached to the request..
			.when()
				.get("/dashboard/count")
			.then()
				.spec(SpecUtils.responseSpecification_HTML(401));		//since no auth token passed , hence unauthorized..expect type is HTML
		
	}
}


/*
 * 
 * 
 * @Test
	public void verifyCountAPIResponse()
	{
		RestAssured
		.given()
			.baseUri(ConfigReaderManagerInputStream.getProperties("BASE_URI"))
			.header("Authorization",AuthTokenProvider.getLoginToken(Roles.FRONTDESK))
			.log().uri()
			.log().method()
			.log().headers()
		.when()
			.get("/dashboard/count")
		.then()
			.statusCode(200)
			.body("message", Matchers.equalTo("Success"))
			.time(Matchers.lessThan(2000L))
			.body("data", Matchers.notNullValue())
			.body("data.size()", Matchers.equalTo(3))
			.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
			.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/countAPIResponseJsonSchema_FD.json"))
			.body("data.key", Matchers.containsInAnyOrder("pending_fst_assignment","created_today","pending_for_delivery"))
			.log().all();		
	}
	
	
	@Test
	public void verifyResponseWithNoAuthenticationHeader()
	{
		RestAssured
			.given()
				.baseUri(ConfigReaderManagerInputStream.getProperties("BASE_URI"))
			.when()
				.get("/dashboard/count")
			.then()
				.statusCode(401)
				.log().body();
		
	}
	
	*/
