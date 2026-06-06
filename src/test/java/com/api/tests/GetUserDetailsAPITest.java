package com.api.tests;

import static com.api.utils.ConfigReaderManagerInputStream.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class GetUserDetailsAPITest {
	
	@Test
	public void getUserDetails()
	{
		Header loginToken = new Header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiZmlyc3RfbmFtZSI6ImZkIiwibGFzdF9uYW1lIjoiZmQiLCJsb2dpbl9pZCI6ImlhbWZkIiwibW9iaWxlX251bWJlciI6Ijg4OTk3NzY2NTUiLCJlbWFpbF9pZCI6Im1hcmtAZ21haWwuY29tIiwicGFzc3dvcmQiOiI1ZjRkY2MzYjVhYTc2NWQ2MWQ4MzI3ZGViODgyY2Y5OSIsInJlc2V0X3Bhc3N3b3JkX2RhdGUiOm51bGwsImxvY2tfc3RhdHVzIjowLCJpc19hY3RpdmUiOjEsIm1zdF9yb2xlX2lkIjo1LCJtc3Rfc2VydmljZV9sb2NhdGlvbl9pZCI6MSwiY3JlYXRlZF9hdCI6IjIwMjEtMTEtMDNUMDg6MDY6MjMuMDAwWiIsIm1vZGlmaWVkX2F0IjoiMjAyMS0xMS0wM1QwODowNjoyMy4wMDBaIiwicm9sZV9uYW1lIjoiRnJvbnREZXNrIiwic2VydmljZV9sb2NhdGlvbiI6IlNlcnZpY2UgQ2VudGVyIEEiLCJpYXQiOjE3ODAxMjc1MzR9.ihejJ6jvhSNfOHNl5_7yHlYk7pNf1MR139E2uTKZafo");
	
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
			.time(lessThan(1500L))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/getUserDetailsJsonSchema.json"))
			.log().body();
			
		
		
	}

}
