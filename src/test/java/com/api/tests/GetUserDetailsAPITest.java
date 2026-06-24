package com.api.tests;

import static com.api.utils.ConfigReaderManagerInputStream.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.SpecUtils;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class GetUserDetailsAPITest {
	
	@Test
	public void getUserDetails()
	{
		given()
		.spec(SpecUtils.requestSpecificationWithAuthorizationHeader(Roles.FRONTDESK))
		.when()
			.get("/userdetails")
		.then()
			.spec(SpecUtils.responseSpecification())
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/getUserDetailsJsonSchema.json"));	
	}

}

/*
 * 
 * 
 * 
 * @Test
	public void getUserDetails()
	{
		Header loginToken = new Header("Authorization",AuthTokenProvider.getLoginToken(Roles.FRONTDESK));
	
		given()
			.baseUri(getProperties("BASE_URI"))
			.header(loginToken)
			.accept(ContentType.JSON)
			.log().headers()
			.log().all()
		.when()
			.get("/userdetails")
		.then()
			.statusCode(200)
			.time(lessThan(2000L))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/getUserDetailsJsonSchema.json"))
			.log().body();		
	}
	
	*/
