package com.api.tests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void loginAPITest()
	{
		
		UserCredentials credentials = new UserCredentials("iamfd","password");
		
		given()
			.baseUri("http://64.227.160.186:9000/v1")
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

}
