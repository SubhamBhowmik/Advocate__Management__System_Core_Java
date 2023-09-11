package com.advocate.dao;

import java.sql.SQLException;
import java.util.List;

import com.advocate.entity.Customer;

public interface CustomerDao {

	boolean addCustomer(Customer customer) throws SQLException;

	List<Customer> getAllCustomers();

	void editCustomer(int id, Customer customer);

	Customer getCustomerById(int id); 
	
	void deleteCustomerById(int id);
}
