package com.advocate.menu;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.advocate.auth.CustomerLoginAuth;
import com.advocate.dao.CustomerDao;
import com.advocate.dao.Impl.CustomerDaoDbImpl;
import com.advocate.entity.Customer;
import com.advocate.exceptions.InvalidCustomerException;
import com.advocate.exceptions.SystemException;

public class CustomerMenu {
	private static Scanner sc = new Scanner(System.in);

	private static CustomerDao customerDao = new CustomerDaoDbImpl();

	public static void customerMainMenu() {


		try {
			while (true) {
				System.out.println(
						"\n>>>>>>>>>>>>>>>>Welcome to Advocate Management System >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
				System.out.println("1: Customer ");
				System.out.println("2. Advocate");
				System.out.println("3. Appointment");
				System.out.println("4. Service");
				System.out.println("0. Logout");
				System.out.print("\nEnter your choice :");
				int option = Integer.parseInt(sc.nextLine());

				switch (option) {
				case 1:
					customerMenu();
					break;
				case 0:
					System.out.println("\nYou have logged out successfully.\n");
					return;
				case 2:
					AdvocateMenu.advocateMenu();
					break;
				case 3:
					AppointmentMenu.showAppointmentMenu();
					break;
				case 4:
					System.out.println("No services implemented yet ");
					break;
				default:
					System.out.println("Try again");

				}

			}

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}

	}

	

	public static void loginCustomer() {
		try {

			while (true) {
				System.out.println("\n Welcome to the Login System");
				System.out.print("Enter customerName: ");
				String customername = sc.nextLine();
				System.out.print("Enter password: ");
				String password = sc.nextLine();

				boolean isAuthenticated = CustomerLoginAuth.authLoginCustomer(customername, password);
				if (isAuthenticated) {
					System.out.println("\n ----> You have Logged in successfully");
					customerMainMenu();
					break;
				} else {
					System.out.println("\n>>>>Login failed. Please try again.\n");
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}

	}

	private static Customer createCustomer() throws InvalidCustomerException {
		Customer customer = new Customer();
	
		
			System.out.print("\n 1: Enter customerName :");
			String customerName = sc.nextLine();
			customer.setCustomerName(customerName);
			System.out.print("\n 2. Enter Password :");
			String password = sc.nextLine();
			customer.setPassword(password);
			System.out.print("\n 3. Enter Email :");
			String email = sc.nextLine();
			customer.setEmail(email);
			System.out.print("\n 4. Enter Address :");
			String address = sc.nextLine();
			customer.setAddress(address);
			
			
			
			System.out.print("\n 5. Enter Phone :");
			String phone = sc.nextLine();
		
			customer.setPhone(phone);
			
			
			System.out.print("\n 6. Enter Age :");
			String age = sc.nextLine();
			customer.setAge(Integer.parseInt(age));

			if(!isCorrectPhoneNumber(phone) || !isCorrectEmail(email)) {
				throw new InvalidCustomerException("\nInvalid inputs email should ends with @gmail.com or phone no should 10 number of digits");
			}
			// System.out.println("\n New Customer created successfully");
			return customer;
			
	

	}

	private static boolean isCorrectPhoneNumber(String phone) {
		
		return phone.matches("\\d{10}");
	}
	
	private static boolean isCorrectEmail(String email) {
		return email.endsWith("@gmail.com");
	}



	// handling Customer dao

	public static void addCustomer() throws SystemException {

		Customer createdCustomer = null;

		try {
			try {
				createdCustomer = createCustomer();
			} catch (InvalidCustomerException e) {
			System.out.println("Please fill with valid details " + e.getMessage());
			return;
			}
			customerDao.addCustomer(createdCustomer);
		} catch (SQLException e) {
			System.out.println(e);
			return;
		}

	}

	// to print all customers
	public static void findAllCustomer() {
		List<Customer> customers = customerDao.getAllCustomers();
		System.out.println("\n>>>> All Customer Records :");
		if (customers.size() == 0) {
			System.out.println("No customer data exist");
			isEmpty = true;
			return;
		}
		for (Customer customer : customers) {
			System.out.println(customer.toString());
		}

	}

	// to modify the Customer details
	private static void modifyCustomer() throws InvalidCustomerException {

		while (true) {
			List<Customer> customers = customerDao.getAllCustomers();
			System.out.println("\n>>>> All Customer Records :");
			if (customers.size() == 0) {
				System.out.println("No customer data exist");
				isEmpty = true;
				return;
			}
			for (Customer customer : customers) {
				System.out.println(customer.toString());
			}

			System.out.println("\n >>>>> Enter the id which you want to modify or Press 0 for exit:");

			int selectedId = Integer.parseInt(sc.nextLine());
			boolean isCustomerId = false;
			for (Customer customer : customers) {
				if (customer.getId() == selectedId) {
					isCustomerId = true;
					System.out.println("Current Customer Information:");
					System.out.println(customer);
					System.out.print("Enter new customerName: ");
					String newCustomer = sc.nextLine();
					customer.setCustomerName(newCustomer);

					System.out.print("Enter new Password: ");
					String newPassword = sc.nextLine();
					customer.setEmail(newPassword);

					System.out.print("Enter new Email: ");
					String newEmail = sc.nextLine();
					customer.setEmail(newEmail);

					System.out.print("Enter new Address: ");
					String newAddress = sc.nextLine();
					customer.setAddress(newAddress);

					System.out.print("Enter new Phone: ");
					String newPhone = sc.nextLine();
					customer.setPhone(newPhone);

					System.out.print("Enter new Age: ");
					int newAge = Integer.parseInt(sc.nextLine());
					customer.setAge(newAge);

					if(!isCorrectPhoneNumber(newPhone) || !isCorrectEmail(newEmail)) {
						throw new InvalidCustomerException("\nInvalid inputs email should ends with @gmail.com or phone no should 10 number of digits");
					}else {
						customerDao.editCustomer(selectedId, customer);
					}
				
				

				}
			}

			if (selectedId == 0) {
				return;
			}

			if (!isCustomerId) {
				System.out.println("Customer ID not exist ,plase enter a valid Id");

			}
		}

	}

	private static void customerMenu() {

		try {
			while (true) {
				System.out.println(
						"\nWelcome to Customer Menu ------------------------------>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

				System.out.println("1. Modify Customer details");
				System.out.println("2. Delete Customer record");
				System.out.println("3. View single record");
				System.out.println("4. View all records");
				System.out.println("0.  Exit ::");
				System.out.println("\n\n\n Enter your choice :");

				int opt = Integer.parseInt(sc.nextLine());

				switch (opt) {
				case 0:
					return;

				case 1:
					modifyCustomer();
					break;
				case 2:

					deleteCustomer();
					break;
				case 3:
					findSingleDetails();
					break;
				case 4:
					findAllCustomer();
					break;

				default:
					System.out.println("Try again");
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());

		}

	}

	private static boolean isEmpty = false;

	private static void deleteCustomer() {
		findAllCustomer();
		if (isEmpty) {
			return;
		} else {
			System.out.print("\nEnter the ID you want to delete :");
			int id = Integer.parseInt(sc.nextLine());

			customerDao.deleteCustomerById(id);
		}

	}

	private static void findSingleDetails() {
		System.out.print("Enter the particular Customer Id :");
		int id = Integer.parseInt(sc.nextLine());
		Customer customer = customerDao.getCustomerById(id);

		System.out.println("Customer information:" + customer);

	}

}
