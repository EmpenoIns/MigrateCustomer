package com.example.demo.service;

import java.util.List;

public interface AceMigService {
	
	void pushCustomerToDatabase(List<Long> list);
	
	void processEligibleCustomers();

}
