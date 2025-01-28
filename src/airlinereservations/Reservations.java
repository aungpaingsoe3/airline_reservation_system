package airlinereservations;

import java.util.*;
import java.io.*;

/**
 * Programming Assignment 2 Reservations Program File
 * 
 * @author Aung Paing Soe
 * @version 1.0 3/4/2023
 */

/**
 * A Java class that manages airline reservations.
 */
public class Reservations {

	private AirplaneSeats seats;
	private User user;
	private TreeMap<String, User> reservations;
	
	/**
	 * Constructs Reservations object by initializing AirplaneSeats and User objects and 
	 * the TreeMap that stores all reservations
	 */
	public Reservations() {
		seats = new AirplaneSeats();
		user = new User();
		reservations = new TreeMap<>();
	}

	/**
	 * Makes reservations based on the the user and the seat number user desires to reserve,
	 * and removes the seat from the seats object
	 * 
	 * @param seat The seat number the user desires to reserve
	 * @param u The user that reserves that particular seat
	 */
	public void makeReservation(String seat, User u) {
		int num = 0;
		String alphabet = null;
		if (seat.length() == 2) {
			num = Integer.parseInt(Character.toString(seat.charAt(0)));
			alphabet = Character.toString(seat.charAt(1));
		}
		else if (seat.length() == 3) {
			num = Integer.parseInt(Character.toString(seat.charAt(0)) + Character.toString(seat.charAt(1)));
			alphabet = Character.toString(seat.charAt(2));
		}
		reservations.put(seat, u);
		seats.removeSeats(num, alphabet);
	}
	
	/**
	 * Adds reservations to the TreeMap that is used in reading reservations file
	 * 
	 * @param seat The seat number from the reservations file to be added to TreeMap
	 * @param name The name of the user that reserves the seat previously
	 */
	public void addReservation(String seat, String name) {  // Used when reading File
		User toAdd = user.getUserbyName(name);
		toAdd.addPrice(getTicketPrice(seat));
		makeReservation(seat, toAdd);
	}
	
	/**
	 * Shows the seat availability list, which is categorized by type of service along with the price
	 */
	public void showAvailability() {
		seats.showAvailability();
	}
	
	/**
	 * Checks whether the seat the user desires to reserve is available or not
	 * 
	 * @param seat the seat the user wants to reserve 
	 * @return a boolean value: true if it is available, false otherwise
	 */
	public boolean checkAvailability(String seat) {
		return seats.checkAvailability(seat);
	}
	
	/**
	 * Returns the price of the ticket associated with the seat
	 * 
	 * @param seat The seat to get the ticket price
	 * @return the price of that seat
	 */
	public int getTicketPrice(String seat) {
		return seats.determinePrice(seat);
	}
	
	/**
	 * Returns the service type of the seat
	 * 
	 * @param seat The seat to get the service type
	 * @return the service type of that seat (First, Economy Plus, or Economy)
	 */
	public String getType(String seat) {
		return seats.determineService(seat);
	}
	
	/**
	 * Cancels the seat number the user provides from the reservations list
	 * 
	 * @param seatNumber the seat number the user wishes to cancel
	 * @param u the user that cancels the seat
	 */
	public void cancelReservation(String seatNumber, User u) {
		if (reservations.containsKey(seatNumber)) {
			reservations.remove(seatNumber);
		}
		String type = seats.determineService(seatNumber);
		int priceToSubtract = seats.determinePrice(seatNumber);
		u.subtractPrice(priceToSubtract);
		seats.reAdd(seatNumber, type);
	}
	
	/**
	 * Shows all the seats the user reserved along side the price of each seat
	 * 
	 * @param u The user to show the reservations list
	 */
	public void showReservations(User u) {
		String toPrint = null;
		Set<Map.Entry<String, User>> entries = reservations.entrySet();
		for (Map.Entry<String, User> entry: entries) {
			if (entry.getValue().getName().equals(u.getName()))	{
				toPrint += entry.getKey() + " $" + seats.determinePrice(entry.getKey()) + ", ";
			}
		}
		System.out.println(toPrint.substring(4, toPrint.length()-2));
	}
	
	/**
	 * Checks whether the seat the user provides to cancel is valid or not 
	 * 
	 * @param u the user that provides the seat number
	 * @param seatToCancel the seat number to cancel
	 * @return a boolean value: true if it is correct, false otherwise
	 */
	public boolean checkCorrectSeat(User u, String seatToCancel) {
		boolean correct = false;
		Set<Map.Entry<String, User>> entries = reservations.entrySet();
		for (Map.Entry<String, User> entry: entries) {
			if (entry.getValue().getName().equals(u.getName()) && entry.getKey().equals(seatToCancel)) {
				correct = true;
			}
		}
		return correct;
	}
	
	/**
	 * Shows the manifest list to the administrator
	 */
	public void showManifestList() {
		Set<Map.Entry<String, User>> entries = reservations.entrySet();
		for (Map.Entry<String, User> entry: entries) {
			System.out.print(entry.getKey() + ": ");
			User toPrint = entry.getValue();
			System.out.println(toPrint.getName());
		}
	}
	
	/**
	 * Reads the Reservations File and populate them into the TreeMap
	 * 
	 * @param f the file to be read
	 * @throws IOException if the file is not found
	 * @throws FileNotFoundException if the file is not found
	 */
	public void readFile(File f) throws IOException {
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			boolean done = false;
			String nameInfo;
			while (!done) {
				nameInfo = br.readLine();
				if (nameInfo == null) {
					done = true;
				}
				else {
					String[] splitInfo = nameInfo.split("  ");
					addReservation(splitInfo[0], splitInfo[1]);
				}
			}
			br.close();
			fr.close();
		}
		catch (FileNotFoundException x) {
			System.out.println("File is not found: " + f);
		}
	}
	
	/**
	 * Writes all the reservations made, with the exception of cancelled
	 * reservations to the Reservations File
	 * 
	 * @param f the file to write the reservations to
	 * @throws IOException if the file is not found
	 */
	public void writeFile(File f) throws IOException {
		FileWriter fw = new FileWriter(f);
		PrintWriter pw = new PrintWriter(fw);
		
		Set<Map.Entry<String, User>> entries = reservations.entrySet();
		for (Map.Entry<String, User> entry: entries) {
			pw.printf("%s  %s\n", entry.getKey(), entry.getValue().getName());
		}
		
		pw.close();
		fw.close();
	}
}
