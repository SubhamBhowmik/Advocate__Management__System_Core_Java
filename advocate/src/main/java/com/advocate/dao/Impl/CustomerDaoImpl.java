package com.advocate.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import com.advocate.dao.CustomerDao;
import com.advocate.entity.Customer;

public class CustomerDaoImpl implements CustomerDao {

	private List<Customer> customers = new ArrayList<>();

	@Override
	public boolean addCustomer(Customer customer) {

		return customers.add(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		
		return customers;
	}

	@Override
	public void editCustomer(int id, Customer customer) {
	
		
	}

	@Override
	public Customer getCustomerById(int id) {
	
		return new Customer();
	}

	@Override
	public void deleteCustomerById(int id) {
	
	}





}
