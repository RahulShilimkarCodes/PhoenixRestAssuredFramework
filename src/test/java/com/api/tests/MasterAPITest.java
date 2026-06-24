package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.ConfigReaderManagerInputStream;
import com.api.utils.SpecUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {
	
	@Test
	public void masterAPITest()
	{
		RestAssured
		.given()
			.spec(SpecUtils.requestSpecificationWithAuthorizationHeader(Roles.FRONTDESK))
		.when()
			.post("/master")
		.then()
			.spec(SpecUtils.responseSpecification())
			.body("message", Matchers.equalTo("Success"))
			.body("data", Matchers.notNullValue())
			.body("data", Matchers.hasKey("mst_oem"))
			.body("data.mst_oem[0]", Matchers.hasKey("name"))
			.body("data.mst_oem[0]", Matchers.hasEntry("name", "Google"))
			.body("$", Matchers.hasKey("message"))		//$ - selects entire JSON from top to bottom
			.body("$",Matchers.hasKey("data"))
			.body("data.mst_oem.size()",Matchers.greaterThan(0))
			.body("data.mst_oem.size()",Matchers.equalTo(2))
			.body("data.mst_model.size()", Matchers.greaterThan(0))
			.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
			.body("data.mst_model.mst_product_id", Matchers.everyItem(Matchers.notNullValue()))
			.body("data.mst_oem.id", Matchers.everyItem(Matchers.greaterThanOrEqualTo(1)))
			.body("data.mst_model.mst_product_id", Matchers.everyItem(Matchers.greaterThanOrEqualTo(1)))
			.body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))
			.body("data.mst_model.name", Matchers.everyItem(Matchers.notNullValue()))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/masterAPIResponseJsonSchema_FD.json"));
		
	}
	
	
	@Test
	public void invalidMasterAPITest()
	{
		RestAssured
		.given()
			.spec(SpecUtils.requestSpecification())				//Deliberately not passing any auth token to fail test...
		.when()
			.post("/master")
		.then()
			.spec(SpecUtils.responseSpecification_HTML(401));			//since, no login token is being passed...unauthorized 
		//above we are passing user defined status code...
	}

}

/*
 * 
 * @Test
	public void masterAPITest()
	{
		RestAssured
		.given()
			.baseUri(ConfigReaderManagerInputStream.getProperties("BASE_URI"))
			.header("Authorization",AuthTokenProvider.getLoginToken(Roles.FRONTDESK))
			.contentType(ContentType.JSON)					//use this always in case of post request to avoid 415 error..
			//.contentType("")
			.log().all()
		.when()
			.post("/master")
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(2000L))
			.log().body()
			.body("message", Matchers.equalTo("Success"))
			.body("data", Matchers.notNullValue())
			.body("data", Matchers.hasKey("mst_oem"))
			.body("data.mst_oem[0]", Matchers.hasKey("name"))
			.body("data.mst_oem[0]", Matchers.hasEntry("name", "Google"))
			.body("$", Matchers.hasKey("message"))		//$ - selects entire JSON from top to bottom
			.body("$",Matchers.hasKey("data"))
			.body("data.mst_oem.size()",Matchers.greaterThan(0))
			.body("data.mst_oem.size()",Matchers.equalTo(2))
			.body("data.mst_model.size()", Matchers.greaterThan(0))
			.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
			.body("data.mst_model.mst_product_id", Matchers.everyItem(Matchers.notNullValue()))
			.body("data.mst_oem.id", Matchers.everyItem(Matchers.greaterThanOrEqualTo(1)))
			.body("data.mst_model.mst_product_id", Matchers.everyItem(Matchers.greaterThanOrEqualTo(1)))
			.body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))
			.body("data.mst_model.name", Matchers.everyItem(Matchers.notNullValue()))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/masterAPIResponseJsonSchema_FD.json"));
		
	}
	
	
	@Test
	public void invalidMasterAPITest()
	{
		RestAssured
		.given()
			.baseUri(ConfigReaderManagerInputStream.getProperties("BASE_URI"))
			.contentType(ContentType.JSON)					//use this always in case of post request to avoid 415 error..
			//.contentType("")
			.log().all()
		.when()
			.post("/master")
		.then()
			.statusCode(401);		//since, no login token is being passed...unauthorized 
	}

*/
