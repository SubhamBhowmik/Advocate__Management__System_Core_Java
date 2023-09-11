package com.advocate.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

import com.advocate.dao.AdvocateDao;
import com.advocate.dao.AppointmentDao;
import com.advocate.dao.CustomerDao;
import com.advocate.dao.Impl.AdvocateDaoImpl;
import com.advocate.dao.Impl.AppointmentDaoDbImpl;
import com.advocate.dao.Impl.CustomerDaoDbImpl;
import com.advocate.dao.Impl.advocateDaoDbImpl;
import com.advocate.entity.Advocate;
import com.advocate.entity.Appointment;
import com.advocate.entity.Customer;

public class AppointmentMenu {

	private static Scanner sc = new Scanner(System.in);
	private static AppointmentDao appointmentDao = new AppointmentDaoDbImpl();

	public static void showAppointmentMenu() {

		while (true) {
			System.out.println("\n Welcome to Appointment Menu:\n");
			System.out.println("1: Book an appointment ");
			System.out.println("2. Modify appointment details");
			System.out.println("3. Delete an appointment");
			System.out.println("4. View Single appointment record");
			System.out.println("5. View All Appointment history");
			System.out.println("0. Exit");
			System.out.print("\nEnter your choice :");
			int option = Integer.parseInt(sc.nextLine());

			switch (option) {
			case 0:
				return;
			case 1:
				try {
					bookAppointment();
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			case 2:
				modifyAppointment();
				break;
			case 3:
				deleteAppointment();
				break;
			case 4:
				findSingleDetails();
				break;
			case 5:
				viewALlAppointments();
				break;
			default:
				System.out.println("Try again");

			}

		}

	}

	private static boolean isAppointmentsEmpty = false;

	private static void viewALlAppointments() {

		List<Appointment> appointments = appointmentDao.getAllAppointments();
		System.out.println("\n>>>> All Appointments history :");
		if (appointments.size() == 0) {
			System.out.println("No customer data exist");
			isAppointmentsEmpty = true;
			return;
		}
		for (Appointment appointment : appointments) {

			int customerId = appointment.getCustomerId();
			int advocateId = appointment.getAdvocateId();

			CustomerDao customerDao = new CustomerDaoDbImpl();
			Customer customer = customerDao.getCustomerById(customerId);

			AdvocateDao advocateDao = new advocateDaoDbImpl();
			Advocate advo = advocateDao.getAdvocateById(advocateId);

			System.out.println("Appointment No:[" + appointment.getApointmentId() + "] customer: ["
					+ customer.getCustomerName() + "] have an appointment with Advocate: ["

					+ advo.getName() + "] Date: [" + appointment.getAppointmentDate() + "] at Time: ["
					+ appointment.getAppointmentTime() + "]");
		}

	}

	private static boolean validateNewCustomerId(List<Customer> customers, int newCustomerId) {
		boolean found = false;
		for (Customer customer : customers) {
			if (customer.getId() == newCustomerId) {

				found = true;
			}
		}

		return found;
	}
	private static boolean validateNewAdvocateId(List<Advocate> advocates,int newAdvocateId){
		boolean found = false;
		for (Advocate advocate: advocates) {
			if (advocate.getId() == newAdvocateId) {

				found = true;
			}
		}

		return found;
	}
	
	

	private static void modifyAppointment() {
		while (true) {
			List<Appointment> appointments = appointmentDao.getAllAppointments();
			System.out.println("\n>>>> All Appointments Records :");
			if (appointments.size() == 0) {
				System.out.println("No Appointment data exist");
				isAppointmentsEmpty = true;
				return;
			}
			for (Appointment appointment : appointments) {
				System.out.println(appointment.toString());
			}

			System.out.println("\n >>>>> Enter the appointment id which you want to modify or Press 0 for exit:");

			int selectedId = Integer.parseInt(sc.nextLine());
			boolean isAppointmentId = false;
			for (Appointment appointment : appointments) {
				if (appointment.getApointmentId() == selectedId) {
					isAppointmentId = true;
					System.out.println("Current Appointment Information:");

					System.out.println(appointment);

					
					
					CustomerDao customerDao = new CustomerDaoDbImpl();
					List<Customer> customers = customerDao.getAllCustomers();
					System.out.println("\n\n Customers Details");
					for (Customer customer : customers) {
						System.out.println(customer);
					}
					System.out.print("Choose new customerId from here: ");
					int newCustomerId = Integer.parseInt(sc.nextLine());

					if (!validateNewCustomerId(customers, newCustomerId)) {
						System.out.println("Customer Id not exist");
						return;
					} else {
						appointment.setCustomerId(newCustomerId);
					}

					
					
					
					AdvocateDao advocateDao = new advocateDaoDbImpl();
					List<Advocate> advocates = advocateDao.getAllAdvocates();
					System.out.println("\n\n Advocates Details");

					if (advocates.size() == 0) {
						System.out.println("No Advocates exist");
						return;
					} else {
						for (Advocate advocate : advocates) {
							System.out.println(advocate);
						}
					}

					System.out.print("Enter new advocateId: ");
					int newAdvocateId = Integer.parseInt(sc.nextLine());

					if (!validateNewAdvocateId(advocates, newAdvocateId)) {
						System.out.println("Advocate Id not exist");
						return;
					} else {
						appointment.setAdvocateId(newAdvocateId);
					}

					
					
					
					System.out.print("Enter the New Appointment Date Format-> YYYY-MM-DD ::");
					String appointmentDate = sc.nextLine();

					if (isValidDateFormat(appointmentDate)) {
						appointment.setAppointmentDate(appointmentDate);
					} else {
						System.out.println("Error: Invalid date format. Please use YYYY-MM-DD format.");
						return;
					}

					System.out.print("Enter the  new Appointment Time Format -> HH:MM:ss ::");
					String appointmentTime = sc.nextLine();

					if (isValidTimeFormat(appointmentTime)) {

						appointment.setAppointmentTime(appointmentTime);
					} else {

						System.out.println(
								"Error: Invalid time format or out of bounds. Please use HH:MM:ss format within 00:00:00 to 23:59:59 range.");
						return;
					}

					appointmentDao.editAppointment(selectedId, appointment);
					return;

				}
			}

			if (selectedId == 0) {
				return;
			}

			if (!isAppointmentsEmpty) {
				System.out.println("Appointment ID not exist ,plase enter a valid Id");
				return;
			}
		}

	}

	public static boolean isValidDateFormat(String date) {

		String regex = "^(?:\\d{4}-(?:0[1-9]|1[0-2])-(?:0[1-9]|[1-2][0-9]|3[0-1]))$";
		return Pattern.matches(regex, date);
	}

	public static boolean isValidTimeFormat(String time) {
		String regex = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";
		if (Pattern.matches(regex, time)) {
			String[] parts = time.split(":");
			int hours = Integer.parseInt(parts[0]);
			int minutes = Integer.parseInt(parts[1]);
			int seconds = Integer.parseInt(parts[2]);

			if (hours <= 23 && minutes <= 59 && seconds <= 59) {
				return true;
			}
		}
		return false;
	}

	private static void bookAppointment() throws java.text.ParseException {

		Appointment appointment = new Appointment();


		
		CustomerDao customerDao = new CustomerDaoDbImpl();
		List<Customer> customers = customerDao.getAllCustomers();
		System.out.println("\n\n Customers Details");
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		System.out.print("Choose customerId from here: ");
		int newCustomerId = Integer.parseInt(sc.nextLine());

		if (!validateNewCustomerId(customers, newCustomerId)) {
			System.out.println("Customer Id not exist");
			return;
		} else {
			appointment.setCustomerId(newCustomerId);
		}
		

		AdvocateDao advocateDao = new advocateDaoDbImpl();
		List<Advocate> advocates = advocateDao.getAllAdvocates();
		System.out.println("\n\n Advocates Details");

		if (advocates.size() == 0) {
			System.out.println("No Advocates exist");
			return;
		} else {
			for (Advocate advocate : advocates) {
				System.out.println(advocate);
			}
		}

		System.out.print("Enter new advocateId: ");
		int newAdvocateId = Integer.parseInt(sc.nextLine());

		if (!validateNewAdvocateId(advocates, newAdvocateId)) {
			System.out.println("Advocate Id not exist");
			return;
		} else {
			appointment.setAdvocateId(newAdvocateId);
		}

		
		
		

		System.out.print("\nEnter the Appointment Date Format-> YYYY-MM-DD ::");
		String appointmentDate = sc.nextLine();

		if (isValidDateFormat(appointmentDate)) {
			appointment.setAppointmentDate(appointmentDate);
		} else {
			System.out.println("Error: Invalid date format.");
			return;
		}

		System.out.print("\nEnter the Appointment Time Format -> HH:MM:ss ::");
		String appointmentTime = sc.nextLine();

		if (isValidTimeFormat(appointmentTime)) {

			appointment.setAppointmentTime(appointmentTime);
		} else {

			System.out.println(
					"Error: Invalid time format or out of bounds. Please use HH:MM:ss format within 00:00:00 to 23:59:59 range.");
			return;
		}

		try {
			appointmentDao.addAppointment(appointment);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	
	
	
	
	
	
	
	private static void findSingleDetails() {
		
		System.out.print("Enter the particular Id :");
		Appointment appointment = null;
		int id = Integer.parseInt(sc.nextLine());
		
		try {
			 appointment = appointmentDao.getAppointmentById(id);
			 if(appointment==null) {
				 System.out.println("Appointment not exist on this id");
				 return;
			 }
		} catch (Exception e) {
			System.out.println(e);
			return;
		}
	

		int customerId = appointment.getCustomerId();
		int advocateId = appointment.getAdvocateId();

		CustomerDao customerDao = new CustomerDaoDbImpl();
		Customer customer = customerDao.getCustomerById(customerId);

		AdvocateDao advocateDao = new advocateDaoDbImpl();
		Advocate advo = advocateDao.getAdvocateById(advocateId);

		System.out.println("Appointment No:[" + appointment.getApointmentId() + "] customer: ["
				+ customer.getCustomerName() + "] have an appointment with Advocate: ["

				+ advo.getName() + "] Date: [" + appointment.getAppointmentDate() + "] at Time: ["
				+ appointment.getAppointmentTime() + "]");
	}

	private static void deleteAppointment() {
		viewALlAppointments();
		if (isAppointmentsEmpty) {
			return;
		} else {
			System.out.print("\nEnter the ID you want to delete :");
			int id = Integer.parseInt(sc.nextLine());

			appointmentDao.deleteAppointmentById(id);
		}

	}
}
