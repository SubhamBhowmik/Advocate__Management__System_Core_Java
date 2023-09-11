package com.advocate.menu;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.advocate.dao.AdvocateDao;
import com.advocate.dao.Impl.AdvocateDaoImpl;
import com.advocate.dao.Impl.advocateDaoDbImpl;
import com.advocate.entity.Advocate;
import com.advocate.entity.Customer;

public class AdvocateMenu {

	private static Scanner sc = new Scanner(System.in);

	public static void advocateMenu() {

		while (true) {
			System.out.println("\n Welcome to advocate Menu:\n");
			System.out.println("1: Add Advocate ");
			System.out.println("2. view All advocates");
			System.out.println("3. view single advocate");
			System.out.println("4. Modify advocate");
			System.out.println("5. Delete advoacte");
			System.out.println("0. Exit");
			System.out.print("\nEnter your choice :");
			int option = Integer.parseInt(sc.nextLine());

			switch (option) {
			case 0:
				return;
			case 1:
				try {
					addAdvocate();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			case 2:
				viewAllAdvocates();
				break;
			case 3:
				viewSingleAdvocate();
				break;
			case 4:
				modifyAdvocateDetails();
				break;
			case 5:
				deleteAdvocate();
				break;
			default:
				System.out.println("Try again");

			}

		}

	}

	private static Advocate createAdvocate() {

		Advocate advocate = new Advocate();
		System.out.print("\n 1: Enter Advocate Name :");
		String name = sc.nextLine();
		advocate.setName(name);
	

		return advocate;
	}

	private static AdvocateDao advocateDao = new advocateDaoDbImpl();

	private static void addAdvocate() {
		Advocate createdAdvocate;

		try {
			createdAdvocate = createAdvocate();
		
			advocateDao.addAdvocate(createdAdvocate);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	private static boolean isAdvocatesEmpty = false;

	public static void viewAllAdvocates() {

		List<Advocate> advocates = advocateDao.getAllAdvocates();
		System.out.println("\n>>>> All  Advocate Records :");
		if (advocates.size() == 0) {
			System.out.println("No Advocate data exist");
			isAdvocatesEmpty = true;
			return;
		}
		for (Advocate advocate : advocates) {
			System.out.println(advocate.toString());
		}

	}

	private static void viewSingleAdvocate() {
		System.out.print("Enter the particular Id :");
		int id = Integer.parseInt(sc.nextLine());
		Advocate advocate = advocateDao.getAdvocateById(id);
		System.out.println("Advocate information:" + advocate);

	}

	private static void modifyAdvocateDetails() {
		while (true) {

			List<Advocate> advocates = advocateDao.getAllAdvocates();
			System.out.println("\n>>>> All  Advocate Records :");
			if (advocates.size() == 0) {
				System.out.println("No Advocate data exist");
				isAdvocatesEmpty = true;
				return;
			}
			for (Advocate advocate : advocates) {
				System.out.println(advocate.toString());
			}

			System.out.println("\n >>>>> Enter the advocate id which you want to modify or Press 0 for exit:");

			int selectedId = Integer.parseInt(sc.nextLine());
			boolean isAdvocateId = false;
			for (Advocate advocate : advocates) {
				if (advocate.getId() == selectedId) {
					isAdvocateId = true;
					System.out.println("Current Advocate Information:");
					System.out.println(advocate);
					System.out.print("Enter new advocateName: ");
					String newAdvocate = sc.nextLine();
					advocate.setName(newAdvocate);

					

					advocateDao.editAdvocate(selectedId, advocate);
					return;

				}
			}

			if (selectedId == 0) {
				return;
			}

			if (!isAdvocateId) {
				System.out.println("Advocate ID not exist ,plase enter a valid Id");

			}
		}

	}

	private static void deleteAdvocate() {

		viewAllAdvocates();

		if (isAdvocatesEmpty) {
			return;
		} else {
			System.out.print("\nEnter the ID you want to delete :");
			int id = Integer.parseInt(sc.nextLine());

			advocateDao.deleteAdvocateById(id);
		}

	}

}
