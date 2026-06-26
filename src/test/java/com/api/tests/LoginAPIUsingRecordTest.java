package com.api.tests;

import static com.api.utils.ConfigReaderManagerInputStream.*;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.Records.UserCredentialsRecord;
import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIUsingRecordTest {
	
	@Test
	public void loginAPITest() throws IOException
	{
				
		UserCredentialsRecord credentials = new UserCredentialsRecord("iamfd","password");
		
		given()
			.spec(SpecUtils.loginRequestSpecification(credentials))
		.when()
			.post("/login")
		.then()
			.assertThat()
			.spec(SpecUtils.responseSpecification())
			.body("message", Matchers.equalTo("Success"))
			.body("data.token", Matchers.notNullValue())
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/loginJsonSchema.json"));
	}
	
}


/*
 * 
 * 
 * @Test
	public void loginAPITest() throws IOException
	{
		UserCredentials credentials = new UserCredentials("iamfd","password");
		
		given()
			.baseUri(getProperties("BASE_URI"))
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(credentials)
			.log().uri()
			.log().method()
			.log().body()
			.log().headers()
		.when()
			.post("/login")
		.then()
			.assertThat()
			.statusCode(200)
			.time(Matchers.lessThan(2000L))
			.body("message", Matchers.equalTo("Success"))
			.body("data.token", Matchers.notNullValue())
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/loginJsonSchema.json"))
			.log().body();
	}

*/

