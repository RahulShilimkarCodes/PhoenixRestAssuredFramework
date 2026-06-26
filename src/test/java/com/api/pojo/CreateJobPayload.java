package com.api.pojo;

import java.util.List;

public class CreateJobPayload {

	private int mst_service_location_id;
	private int mst_platform_id;
	private int mst_warrenty_status_id;
	private int mst_oem_id;

	private Customer customer;
	private CustomerAddress customer_address;
	private CustomerProduct customer_product;
	private List<Problems> problems;
	
	public CreateJobPayload()	{}

	public CreateJobPayload(int mst_service_location_id, int mst_platform_id, int mst_warrenty_status_id,
			int mst_oem_id, Customer customer, CustomerAddress customer_address, CustomerProduct customer_product,
			List<Problems> problems) {
		
		super();
		this.mst_service_location_id = mst_service_location_id;
		this.mst_platform_id = mst_platform_id;
		this.mst_warrenty_status_id = mst_warrenty_status_id;
		this.mst_oem_id = mst_oem_id;
		this.customer = customer;
		this.customer_address = customer_address;
		this.customer_product = customer_product;
		this.problems = problems;
	}

	public int getMst_service_location_id() {
		return mst_service_location_id;
	}

	public void setMst_service_location_id(int mst_service_location_id) {
		this.mst_service_location_id = mst_service_location_id;
	}

	public int getMst_platform_id() {
		return mst_platform_id;
	}

	public void setMst_platform_id(int mst_platform_id) {
		this.mst_platform_id = mst_platform_id;
	}

	public int getMst_warrenty_status_id() {
		return mst_warrenty_status_id;
	}

	public void setMst_warrenty_status_id(int mst_warrenty_status_id) {
		this.mst_warrenty_status_id = mst_warrenty_status_id;
	}

	public int getMst_oem_id() {
		return mst_oem_id;
	}

	public void setMst_oem_id(int mst_oem_id) {
		this.mst_oem_id = mst_oem_id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerAddress getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(CustomerAddress customer_address) {
		this.customer_address = customer_address;
	}

	public CustomerProduct getCustomer_product() {
		return customer_product;
	}

	public void setCustomer_product(CustomerProduct customer_product) {
		this.customer_product = customer_product;
	}

	public List<Problems> getProblems() {
		return problems;
	}

	public void setProblems(List<Problems> problems) {
		this.problems = problems;
	}

	@Override
	public String toString() {
		return "CreateJobPayload [mst_service_location_id=" + mst_service_location_id + ", mst_platform_id="
				+ mst_platform_id + ", mst_warrenty_status_id=" + mst_warrenty_status_id + ", mst_oem_id=" + mst_oem_id
				+ ", customer=" + customer + ", customer_address=" + customer_address + ", customer_product="
				+ customer_product + ", problems=" + problems + "]";
	}

}

/*
 * 
 * Request Body-----
 * {
    "mst_service_location_id": 0,
    "mst_platform_id": 2,
    "mst_warrenty_status_id": 1,
    "mst_oem_id": 1,
    "customer": {
        "first_name": "Jules",       
        "last_name": "Mraz",       
        "mobile_number": "843-865-3213",       
        "mobile_number_alt": "",
        "email_id": "Bria_Aufderhar@gmail.com",       
        "email_id_alt": ""
    },
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
