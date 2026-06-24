package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constants.Roles;
import com.api.pojo.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtils {
	
	public static RequestSpecification requestSpecification()
	{
		//use's builder design pattern hence can be used as method chaining...
		RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri(ConfigReaderManagerInputStream.getProperties("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.METHOD)	
				.log(LogDetail.BODY)				
				.build();
		
		return requestSpec;
	}
	
	//Method - overloading
	//Below code will be used in case of POST/PATCH/DELETE request where in we need to pass body() as well..	
//	public static RequestSpecification requestSpecification(UserCredentials userCreds)
//	{
//		RequestSpecification requestSpec = new RequestSpecBuilder()
//				.setBaseUri(ConfigReaderManagerInputStream.getProperties("BASE_URI"))
//				.setContentType(ContentType.JSON)
//				.setAccept(ContentType.JSON)
//				.setBody(userCreds)
//				.log(LogDetail.URI)
//				.log(LogDetail.HEADERS)
//				.log(LogDetail.METHOD)
//				.log(LogDetail.BODY)
//				.build();
//		
//		return requestSpec;
//	}
	
	//Loosely coupled variant of above requestSpecification method...
	public static RequestSpecification requestSpecification(Object userCreds)
	{
		RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri(ConfigReaderManagerInputStream.getProperties("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.setBody(userCreds)
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.METHOD)
				.log(LogDetail.BODY)
				.build();
		
		return requestSpec;
	}
	
	//below requestSpecification will get you the login token as well as part of authorization in header
	public static RequestSpecification requestSpecificationWithAuthorizationHeader(Roles role)
	{
		RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri(ConfigReaderManagerInputStream.getProperties("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getLoginToken(role))
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.METHOD)
				.log(LogDetail.BODY)
				.build();
		
		return requestSpec;
	}
	
	public static ResponseSpecification responseSpecification()
	{
		ResponseSpecification responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.expectResponseTime(Matchers.lessThan(2000L))
				.log(LogDetail.ALL)
				.build();
		
		return responseSpec;
	}
	
	//overloaded method - To get the user defined status code..
	public static ResponseSpecification responseSpecification(int StatusCode)
	{
		ResponseSpecification responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(StatusCode)
				.expectContentType(ContentType.JSON)
				.expectResponseTime(Matchers.lessThan(2000L))
				.log(LogDetail.ALL)
				.build();
		
		return responseSpec;
	}
	
	public static ResponseSpecification responseSpecification_TEXT(int StatusCode)
	{
		ResponseSpecification responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(StatusCode)
				.expectContentType(ContentType.TEXT)
				.expectResponseTime(Matchers.lessThan(2000L))
				.log(LogDetail.ALL)
				.build();
		
		return responseSpec;
	}
	
	public static ResponseSpecification responseSpecification_HTML(int StatusCode)
	{
		ResponseSpecification responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(StatusCode)
				.expectContentType(ContentType.HTML)
				.expectResponseTime(Matchers.lessThan(2000L))
				.log(LogDetail.ALL)
				.build();
		
		return responseSpec;
	}
	
	public static ResponseSpecification responseSpecification_XML(int StatusCode)
	{
		ResponseSpecification responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(StatusCode)
				.expectContentType(ContentType.XML)
				.expectResponseTime(Matchers.lessThan(2000L))
				.log(LogDetail.ALL)
				.build();
		
		return responseSpec;
	}


}
