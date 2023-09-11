package com.advocate;

import java.util.Scanner;

import com.advocate.exceptions.SystemException;
import com.advocate.menu.AdvocateMenu;
import com.advocate.menu.AppointmentMenu;
import com.advocate.menu.CustomerMenu;

/**
 * Hello world!
 *
 */
public class App {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			while (true) {
				System.out.println("1: New Customer register here");
				System.out.println("2: Existing Customer Login here");
				System.out.println("0.  <----Exit");
				System.out.println("\n Enter your choice :");

				int opt = Integer.parseInt(sc.nextLine());

				switch (opt) {
				case 0:
					System.out.println("Exited ");
					System.exit(0);

				case 1:
					CustomerMenu.addCustomer();;
					break;
					
				case 2:

					CustomerMenu.loginCustomer();

					break;

				default:
					System.out.println("Try again");

				}
			}

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());

		}


	
	}
	

}
