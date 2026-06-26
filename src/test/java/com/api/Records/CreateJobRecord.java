package com.api.Records;


import java.util.List;

import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;

public record CreateJobRecord (

	 int mst_service_location_id,
	 int mst_platform_id,
	 int mst_warrenty_status_id,
	 int mst_oem_id,
	 CustomerRecord customer,
	 CustomerAddressRecord customer_address,
	 CustomerProductRecord customer_product,
	 List<ProblemsRecord> problems
	 ) {
	
}