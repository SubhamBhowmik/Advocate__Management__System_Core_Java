package com.advocate.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.advocate.dao.CustomerDao;
import com.advocate.entity.Customer;
import com.advocate.util.DataBaseConfig;


public class CustomerDaoDbImpl implements CustomerDao {
	
	private static Connection connection = DataBaseConfig.getConnection();

	private static final String INSERT_CUSTOMER = "insert into customers (customer_name, password, email,address,phone,age) values(?,?,?,?,?,?)";

	@Override
	public boolean addCustomer(Customer customer) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(INSERT_CUSTOMER);
		// Set The value
		ps.setString(1, customer.getCustomerName());
		ps.setString(2, customer.getPassword());
		ps.setString(3, customer.getEmail());
		ps.setString(4, customer.getAddress());
		ps.setString(5, customer.getPhone());
		ps.setInt(6, customer.getAge());

		// Execute Statement
		int executeUpdate = ps.executeUpdate();
		System.out.println("\n >>>>Customer Registration Successful.Please login\n");
		ps.close();
		if (executeUpdate > 0) {
			return true;
		}
		return false;

	}

//Getting all Customers info from ADVOCATEDB--> table-> customers
	private static final String ALL_CUSTOMERS = "select * from customers";

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customerCollection = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(ALL_CUSTOMERS);
			ResultSet result = ps.executeQuery();
//			  System.out.println(result);

			while (result.next()) {
				Customer customer = new Customer();
				customer.setId(result.getInt("id"));
				customer.setCustomerName(result.getString("customer_name"));
				customer.setPassword(result.getString("password"));
				customer.setEmail(result.getString("email"));
				customer.setAddress(result.getString("address"));
				customer.setPhone(result.getString("phone"));
				customer.setAge(result.getInt("age"));

				customerCollection.add(customer);
			}

			result.close();
			ps.close();

		} catch (SQLException e) {

			System.out.println("Error:" + e);
		}
		return customerCollection;

	}

//for getting info by id
	private static final String FIND_BY_ID = "select * from customers where id=?";

	@Override
	public Customer getCustomerById(int id) {
		Customer customer = new Customer();
		try {
		
			PreparedStatement ps = connection.prepareStatement(FIND_BY_ID);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();

			if (result.next()) {
				customer.setId(result.getInt("id"));
				customer.setCustomerName(result.getString("customer_name"));
				customer.setPassword(result.getString("password"));
				customer.setEmail(result.getString("email"));
				customer.setAddress(result.getString("address"));
				customer.setPhone(result.getString("phone"));
				customer.setAge(result.getInt("age"));
			}else {
				System.out.println("No result found on this id.");
				return null;
			}
			
		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}

//for editing
	private static final String UPDATE_QUERY = "UPDATE customers SET customer_name=?,password=?, email=?, address=?, phone=?, age=? WHERE id=?";

	@Override
	public void editCustomer(int id, Customer customer) {

		try {
			// Update the customer record in the database

			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.setString(2, customer.getPassword());
			preparedStatement.setString(3, customer.getEmail());
			preparedStatement.setString(4, customer.getAddress());
			preparedStatement.setString(5, customer.getPhone());
			preparedStatement.setInt(6, customer.getAge());
			preparedStatement.setInt(7, id);

			int rowsUpdated = preparedStatement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Customer information updated successfully in the database.");
			} else {
				System.out.println("Failed to update customer information in the database.");
			}

			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//deleting the customer record by id 
	 private static final String DELETE_BY_ID="delete from customers where id=?";
	
	
		@Override
		public void deleteCustomerById(int id) {

			try {
				PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID);
				ps.setInt(1, id);
				 int affectedRows = ps.executeUpdate();
			        
			        if (affectedRows > 0) {
			            System.out.println("Customer with ID " + id + " deleted successfully.");
			        } else {
			            System.out.println("Customer with ID " + id + " not found or not deleted.");
			        }
			        
			        ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

}
