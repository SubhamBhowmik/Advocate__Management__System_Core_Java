package com.advocate.auth;

import java.util.List;

import com.advocate.dao.CustomerDao;
import com.advocate.dao.Impl.CustomerDaoDbImpl;
import com.advocate.entity.Customer;

public class CustomerLoginAuth {
    public static boolean authLoginCustomer( String customerName, String password) {
    	
    	CustomerDao customerDao = new CustomerDaoDbImpl();
    	List<Customer> customers= customerDao.getAllCustomers();
        for (Customer customer : customers) {
            if (customer.getCustomerName().equals(customerName) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
