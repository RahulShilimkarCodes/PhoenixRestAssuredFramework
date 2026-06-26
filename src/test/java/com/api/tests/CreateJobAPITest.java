package com.api.tests;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtils;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest()
	{
		
		Customer customer = new Customer();
		customer.setFirst_name("Vaibhav");
		customer.setLast_name("Suryavanshi");
		customer.setMobile_number("843-865-3213");
		customer.setMobile_number_alt("843-865-3534");
		customer.setEmail_id("Vaibhav_Aufderhar@gmail.com");
		customer.setEmail_id_alt("");
		
		CustomerAddress address = new CustomerAddress();
        address.setFlat_number("Chinnaswamy");
        address.setApartment_name("playground");
        address.setStreet_name("Whitefield");
        address.setLandmark("RCB Camp");
        address.setArea("RCB Area");
        address.setPincode("653434");
        address.setCountry("India");
        address.setState("Maharashtra");
        
        CustomerProduct product = new CustomerProduct();
        product.setDop("2026-04-01T18:30:00.000Z");
        product.setSerial_number("33783432783786");
        product.setImei1("33783432783786");
        product.setImei2("33783432783786");
        product.setPopurl("2026-04-01T18:30:00.000Z");
        product.setProduct_id(1);
        product.setMst_model_id(2);
        
        Problems problem = new Problems();
        problem.setId(3);
        problem.setRemark("app crash");
	
		CreateJobPayload jobPayload = new CreateJobPayload();		
		jobPayload.setMst_service_location_id(0);
		jobPayload.setMst_platform_id(2);
		jobPayload.setMst_warrenty_status_id(1);
		jobPayload.setMst_oem_id(1);
		jobPayload.setCustomer(customer);
		jobPayload.setCustomer_address(address);
		jobPayload.setCustomer_product(product);
		jobPayload.setProblems(Arrays.asList(problem));
		
		RestAssured
			.given()
				.spec(SpecUtils.requestSpecificationWithAuthorizationHeaderAndPayload(Roles.FRONTDESK, jobPayload))
			.when()
				.post("/job/create")
			.then()
				.spec(SpecUtils.responseSpecification())
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.job_number", Matchers.startsWith("JOB_"))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.tr_customer_product_id", Matchers.notNullValue())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/createJobAPIResponseJsonSchema_FD.json"));
	}
	/*
	Request Body-----
	 * {
	    "mst_service_location_id": 0,
	    "mst_platform_id": 2,
	    "mst_warrenty_status_id": 1,
	    "mst_oem_id": 1,,
	    "customer_address": {
	        "flat_number": "Chinnaswamy",
	        "apartment_name": "playground",
	        "street_name": "Whitefield",
	        "landmark": "RCB Camp",
	        "area": "RCB Area",
	        "pincode": "653434",
	        "country": "India",
	        "state": "Maharashtra"
	    },
	    "customer_product": {
	        "dop": "2026-04-01T18:30:00.000Z",
	        "serial_number": "33783432438756",       
	        "imei1": "33783432438756",              
	        "imei2": "33783432438756",               
	        "popurl": "2026-04-01T18:30:00.000Z",
	        "product_id": 1,
	        "mst_model_id": 2
	    },
	    "problems": [
	        {
	            "id": 3,
	            "remark": "app crash"
	        }
	    ]
	}
*/
}
