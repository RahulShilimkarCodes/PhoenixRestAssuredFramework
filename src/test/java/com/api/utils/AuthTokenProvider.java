package com.api.utils;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigReaderManagerInputStream.*;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private AuthTokenProvider() {
		//private constructor to prevent object creation..
	}
	
	@Test
	public static String getLoginToken(Roles userRole)
	{
		UserCredentials credentials = null;		//since local variable, need to initialize...
		
		switch(userRole)
		{
		case FRONTDESK -> credentials = new UserCredentials("iamfd","password");
		
		case SUPERVISOR -> credentials = new UserCredentials("iamsup","password");
		
		case ENGINEER -> credentials = new UserCredentials("iameng","password");
		
		case QUALITY_CONTROL -> credentials = new UserCredentials("iamqc","password");
		
		default -> throw new RuntimeException("Invalid user details passed, used either frontdesk,supervisor,engineer,qualitycontrol.....");	
		
		}
		
		String loginToken = 
		given()
			.baseUri(getProperties("BASE_URI"))
			.contentType(ContentType.JSON)
			.body(credentials)
		.when()
			.post("/login")
		.then()
			.statusCode(200)
			.body("message",equalTo("Success"))
			.log().ifValidationFails()
			.extract().body().jsonPath().getString("data.token");
		
		return loginToken;
	}

}
