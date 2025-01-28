package airlinereservations;

import java.util.*;
import java.io.*;

/**
 * Programming Assignment 2 ReservationSystem Tester File
 * 
 * @author Aung Paing Soe
 * @version 1.0 3/4/2023
 */

/**
 * A Java class that runs the airline reservation system application.
 */
public class ReservationSystem {
	
	/**
	 * The main method that runs the airline reservation system application.
	 * 
	 * @param args Command line arguments
	 * @throws IOException if there is invalid input in the file
	 */
	public static void main(String[] args) throws IOException {
		Scanner inputs = new Scanner(System.in);
		User u = new User();
		Employee e = new Employee();
		Reservations r = new Reservations();
		
		String airplane = args[0];
		String users = args[1];
		
		File reservationFile = new File(airplane + ".txt"); // CL34 File
		File userFile = new File(users + ".txt"); // Users File
		if (!reservationFile.exists() && !userFile.exists()) {
			reservationFile = new File(airplane + ".txt");
			userFile = new File(users + ".txt");
			System.out.printf("%s and %s files are now created.\n", airplane, users);
		}
		else {
			u.readFile(userFile);
			r.readFile(reservationFile);
			System.out.println("Existing Reservations and Users are loaded.");
		}
		initialMenu(inputs, u, e, r);
		r.writeFile(reservationFile);
		u.writeFile(userFile);
	}
	
	/**
	 * Private method that runs the initial menu screen of the application
	 * 
	 * @param s Scanner that takes in the input options
	 * @param u User object to be used throughout the application
	 * @param e Employee object to be used throughout the application
	 * @param r Reservations object to be used throughout the application
	 */
	private static void initialMenu(Scanner s, User u, Employee e, Reservations r) {
		System.out.println("Are you a first-time user? (Yes or No)");
		if (s.nextLine().toLowerCase().equals("yes")) {
			System.out.println("You must create an account before proceeding.");
			System.out.println("Enter your name:");
			String newName = s.nextLine();
			System.out.println("Enter a user id:");
			String newID = s.nextLine();
			System.out.println("Enter a password:");
			String newPassword = s.nextLine();
			u.addUser(newName, newID, newPassword);
			System.out.println("You have created a new account successfully.");
		}
		System.out.println("Please enter your user type ([1] for Public User, [2] for Administrator):");
		String userType = s.nextLine();
		application(s, u, e, userType, r);
	}
	
	/**
	 * Private method that runs the main application procedures
	 * 
	 * @param s Scanner that takes in the input options
	 * @param u User object to be used throughout the application
	 * @param e Employee object to be used throughout the application
	 * @param type The user type of the application (1 for public user, 2 for administrator)
	 * @param r Reservations object to be used throughout the application
	 */
	private static void application (Scanner s, User u, Employee e, String type, Reservations r) {
		if (type.equals("1")) {
			System.out.println("Please sign in to the reservation system before proceeding.");
			boolean check = false;
			String checkID = null;
			while (check == false) {
				System.out.println("Enter your user id:");
				checkID = s.nextLine();
				System.out.println("Enter your user password:");
				String checkPassword = s.nextLine();
				check = u.checkUserLogin(checkID, checkPassword);
				if (check == false) {
					System.out.println("The user id or password you entered is incorrect.");				
				}
			}
			User retrieved = u.getUserbyID(checkID);
			System.out.println(publicMenu());
			String choice = s.nextLine().toLowerCase();
			publicActions(choice, s, r, u, retrieved);
			initialMenu(s, u, e, r);
		}
		else if (type.equals("2")){
			boolean employeeCheck = false;
			while (employeeCheck == false) {
				System.out.println("Enter your employee id:");
				String checkID = s.nextLine();
				employeeCheck = e.checkEmployee(checkID);
				if (employeeCheck == false) {
					System.out.println("The employee id you entered is incorrect.");				
				}
			}
			System.out.println(adminMenu());
			String adminChoice = s.nextLine().toLowerCase();
			adminActions(s, adminChoice, r);
			System.out.println("The Airline Reservation System is closed now");
		}
	}
	
	/**
	 * Helper method that returns the menu options for public users
	 * 
	 * @return Menu options string for public users
	 */
	private static String publicMenu() {
		String menu = "Check [A]vailability  Make [R]eservation  [C]ancel Reservation  [V]iew Reservations  [D]one";
		return menu;
	}
	
	/**
	 * Helper method that returns the menu options for administrators
	 * 
	 * @return Menu options string for administrators
	 */
	private static String adminMenu() {
		String menu = "Show [M]anifest list  E[X]it ";
		return menu;
	}
	
	/**
	 * Private method that runs each option of the public menu
	 * 
	 * @param options the menu option the public user inputs
	 * @param s Scanner that takes in the input options
	 * @param r Reservations object to be used throughout the application 
	 * @param u User object to be used throughout the application
	 * @param retrievedUser the current user that is using the application
	 */
	private static void publicActions(String options, Scanner s, Reservations r, User u, User retrievedUser) {
		User reserved = retrievedUser;
		while (!options.equals("d")) {
			if (options.equals("a")) {
				r.showAvailability();
				System.out.println(publicMenu());
				options = s.nextLine().toLowerCase();
			}
			else if (options.equals("r")) {
				String again = "yes";
				while (!again.equals("no")) {
					System.out.println("Enter a desired seat number: ");
					String seatPos = s.nextLine();
					while (!r.checkAvailability(seatPos)) {
						System.out.println("Enter a desired seat number: ");
						seatPos = s.nextLine();
					}
					System.out.printf("Seat number: %s, Service type: %s, Price: $%d\n", seatPos, r.getType(seatPos), r.getTicketPrice(seatPos));
					System.out.println("Please enter your user id to confirm your reservation:");
					String idConfirm = s.nextLine();
					if (u.getUserbyID(idConfirm) != null) {
						reserved = u.getUserbyID(idConfirm);
						reserved.addPrice(r.getTicketPrice(seatPos));
						r.makeReservation(seatPos, reserved);
					}
					System.out.println("Do you want to make another reservation? (Yes or No):");
					again = s.nextLine().toLowerCase();
				}
				System.out.println(publicMenu());
				options = s.nextLine().toLowerCase();
			}
			else if (options.equals("c")) {
				String again = "yes";
				while (!again.equals("no")) {
					System.out.print("Seats You Reserved: ");
					r.showReservations(reserved);
					boolean correctSeat = false;
					String toCheckSeat = null;
					while (correctSeat == false) {
						System.out.println("Please enter a seat number to cancel:");
						toCheckSeat = s.nextLine();
						correctSeat = r.checkCorrectSeat(reserved, toCheckSeat);
					}
					r.cancelReservation(toCheckSeat, reserved);
					System.out.println("The seat has been canceled successfully.");
					System.out.println("Do you want to cancel another reservation? (Yes or No):");
					again = s.nextLine().toLowerCase();
				}
				System.out.println(publicMenu());
				options = s.nextLine().toLowerCase();
			}
			else if (options.equals("v")) {
				System.out.printf("Name: %s\n", reserved.getName());
				System.out.print("Seats: ");
				r.showReservations(reserved);
				System.out.printf("Total Balance Due: $%d\n", reserved.getPrice());
				System.out.println(publicMenu());
				options = s.nextLine().toLowerCase();
			}
		}
	}
	
	/**
	 * Private method that runs each option of the administrator menu
	 * 
	 * @param s Scanner that takes in the input options
	 * @param adminOptions the menu option the administrator inputs
	 * @param r Reservations object to be used throughout the application 
	 */
	private static void adminActions(Scanner s, String adminOptions, Reservations r) {
		while (!adminOptions.equals("x")) {
			if (adminOptions.equals("m")) {
				r.showManifestList();
				System.out.println(adminMenu());
				adminOptions = s.nextLine().toLowerCase();
			}
		}
	}
}
