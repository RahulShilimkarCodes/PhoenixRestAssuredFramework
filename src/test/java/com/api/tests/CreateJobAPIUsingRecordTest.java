package com.api.tests;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.Records.CreateJobRecord;
import com.api.Records.CustomerAddressRecord;
import com.api.Records.CustomerProductRecord;
import com.api.Records.CustomerRecord;
import com.api.Records.ProblemsRecord;
import com.api.constants.Roles;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtils;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIUsingRecordTest {
	
	@Test
	public void createJobAPITest()
	{
		
		CustomerRecord customer = new CustomerRecord("Vaibhav","Suryavanshi","843-865-3213","843-865-3534","Vaibhav_Aufderhar@gmail.com","");
	
		CustomerAddressRecord address = new CustomerAddressRecord("Chinnaswamy","playground","Whitefield","RCB Camp",
				"RCB Area","653434","India","Maharashtra");
        
        CustomerProductRecord product = new CustomerProductRecord("2026-04-01T18:30:00.000Z","33786472788186","33786472788186","33786472788186",
        		"2026-04-01T18:30:00.000Z",1,2);
       
        ProblemsRecord problem = new ProblemsRecord(3,"App Crash");
	
		CreateJobRecord jobPayload = new CreateJobRecord(0,2,1,1,customer,address,product,Arrays.asList(problem));		

		
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
