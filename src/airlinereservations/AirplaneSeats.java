package airlinereservations;

import java.util.*;

/**
 * Programming Assignment 2 AirplaneSeats Program File
 * 
 * @author Aung Paing Soe
 * @version 1.0 3/4/2023
 */

/**
 * A Java class that manages all airplane seats in the airline reservation system.
 */
public class AirplaneSeats {
	
	private static final int FIRST_PRICE = 1000;
	private static final int ECON_PLUS_PRICE = 500;
	private static final int ECON_PRICE = 250;
	
	private static final String FIRST_TYPE = "First";
	private static final String ECON_PLUS_TYPE = "Economy Plus";
	private static final String ECON_TYPE = "Economy";
	
	private static final TreeMap<Integer, ArrayList<String>> ORIGINAL_FIRST = initialFirstClass(new TreeMap<>());
	private static final TreeMap<Integer, ArrayList<String>> ORIGINAL_ECON_PLUS = initialEconPlusClass(new TreeMap<>());
	private static final TreeMap<Integer, ArrayList<String>> ORIGINAL_ECON = initialEconClass(new TreeMap<>());
	
	private TreeMap<Integer, ArrayList<String>> first;
	private TreeMap<Integer, ArrayList<String>> econPlus;
	private TreeMap<Integer, ArrayList<String>> econ;
	
	/**
	 * Constructor that initializes first, economy plus, and economy seats
	 */
	public AirplaneSeats() {
		first = new TreeMap<>();
		initialFirstClass(first);
		econPlus = new TreeMap<>();
		initialEconPlusClass(econPlus);
		econ = new TreeMap<>();
		initialEconClass(econ);
	}

	/**
	 * Helper methods that adds all rows to the ArrayList
	 * 
	 * @return the ArrayList with all rows in the airplane 
	 */
	private static ArrayList<String> addAllRows(){
		ArrayList<String> list = new ArrayList(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J", "K", "L"));
		return list;
	}
	
	/**
	 * Helper methods that adds rows A, B, D, E, F, G, K, and L to the ArrayList
	 * 
	 * @return the ArrayList with rows A, B, D, E, F, G, K, and L 
	 */
	private static ArrayList<String> addABDEFGKL(){
		ArrayList<String> list = new ArrayList(Arrays.asList("A", "B", "D", "E", "F", "G", "K", "L"));
		return list;
	}
	
	/**
	 * Helper methods that adds rows A, B, C, J, K, and L to the ArrayList
	 * 
	 * @return the ArrayList with rows A, B, C, J, K, and L
	 */
	private static ArrayList<String> addABCJKL(){
		ArrayList<String> list = new ArrayList(Arrays.asList("A", "B", "C", "J", "K", "L"));
		return list;
	}
	
	/**
	 * Helper methods that adds rows D, E, F, and G to the ArrayList
	 * 
	 * @return the ArrayList with rows D, E, F, and G
	 */
	private static ArrayList<String> addDEFG(){
		ArrayList<String> list = new ArrayList(Arrays.asList("D", "E", "F", "G"));
		return list;
	}
	
	/**
	 * Private method that initializes the TreeMap with all rows that are first class
	 * 
	 * @param map the TreeMap to initialize 
	 * @return the initialized TreeMap for first class seats
	 */
	private static TreeMap<Integer, ArrayList<String>> initialFirstClass(TreeMap<Integer, ArrayList<String>> map){
		
		map.put(1, addABDEFGKL());
		map.put(2, addABDEFGKL());
		map.put(3, addABDEFGKL());
		ArrayList<String> row4 = new ArrayList(Arrays.asList("A", "B", "K", "L"));
		map.put(4, row4);
		return map;
	}
	
	/**
	 * Private method that initializes the TreeMap with all rows that are economy plus class
	 * 
	 * @param map the TreeMap to initialize 
	 * @return the initialized TreeMap for economy plus class seats
	 */
	private static TreeMap<Integer, ArrayList<String>> initialEconPlusClass(TreeMap<Integer, ArrayList<String>> map){
		map.put(17, addAllRows());
		map.put(18, addAllRows());
		map.put(19, addAllRows());
		map.put(20, addAllRows());
		map.put(21, addAllRows());
		map.put(22, addAllRows());
		map.put(23, addAllRows());
		map.put(24, addABCJKL());
		map.put(25, addABCJKL());
		map.put(26, addABCJKL());
		map.put(16, addDEFG());
		map.put(40, addDEFG());
		ArrayList<String> row39 = new ArrayList(Arrays.asList("A", "B", "C", "J", "K", "L"));
		map.put(39, row39);
		return map;
	}
	
	/**
	 * Private method that initializes the TreeMap with all rows that are economy class
	 * 
	 * @param map the TreeMap to initialize 
	 * @return the initialized TreeMap for economy class seats
	 */
	private static TreeMap<Integer, ArrayList<String>> initialEconClass(TreeMap<Integer, ArrayList<String>> map){
		map.put(27, addAllRows());
		map.put(28, addAllRows());
		map.put(29, addAllRows());
		map.put(30, addAllRows());
		map.put(31, addAllRows());
		map.put(32, addAllRows());
		map.put(33, addAllRows());
		map.put(34, addAllRows());
		map.put(35, addAllRows());
		map.put(36, addAllRows());
		map.put(41, addAllRows());
		map.put(42, addAllRows());
		map.put(43, addAllRows());
		map.put(44, addAllRows());
		map.put(45, addAllRows());
		map.put(46, addAllRows());
		map.put(47, addAllRows());
		ArrayList<String> row37 = new ArrayList(Arrays.asList("A", "B", "K", "L"));
		map.put(37, row37);
		ArrayList<String> row40 = new ArrayList(Arrays.asList("A", "B", "C", "J", "K", "L"));
		map.put(40, row40);
		map.put(48, addABDEFGKL());
		map.put(49, addABDEFGKL());
		map.put(50, addABDEFGKL());
		map.put(51, addABDEFGKL());
		ArrayList<String> row52 = new ArrayList(Arrays.asList("A", "B", "D", "E", "F", "G"));
		map.put(52, row52);
		map.put(53, addDEFG());
		map.put(24, addDEFG());
		map.put(25, addDEFG());
		map.put(26, addDEFG());
		return map;
	}
	
	/**
	 * Adds the seat number back to the corresponding TreeMap after cancellation
	 * 
	 * @param seat the seat to put back into the TreeMap
	 * @param service the type of service associated with the seat
	 */
	public void reAdd(String seat, String service) {
		if (service.equals(FIRST_TYPE)) {
			ArrayList<String> list = first.get(Integer.parseInt(Character.toString(seat.charAt(0))));
			list.add(Character.toString(seat.charAt(1)));
			Collections.sort(list);
		}
		if (service.equals(ECON_PLUS_TYPE)) {
			int num = 0;
			String row = null;
			if (seat.length() == 2) {
				num = Integer.parseInt(Character.toString(seat.charAt(0)));
				row = Character.toString(seat.charAt(1));
				ArrayList<String> list = econPlus.get(num);
				list.add(row);
				Collections.sort(list);
			}
			else if (seat.length() == 3) {
				num = Integer.parseInt(Character.toString(seat.charAt(0)) + Character.toString(seat.charAt(1)));
				row = Character.toString(seat.charAt(2));
				ArrayList<String> list = econPlus.get(num);
				list.add(row);
				Collections.sort(list);
			}
		}
		if (service.equals(ECON_TYPE)) {
			int num = 0;
			String row = null;
			if (seat.length() == 2) {
				num = Integer.parseInt(Character.toString(seat.charAt(0)));
				row = Character.toString(seat.charAt(1));
				ArrayList<String> list = econ.get(num);
				list.add(row);
				Collections.sort(list);
			}
			else if (seat.length() == 3) {
				num = Integer.parseInt(Character.toString(seat.charAt(0)) + Character.toString(seat.charAt(1)));
				row = Character.toString(seat.charAt(2));
				ArrayList<String> list = econ.get(num);
				list.add(row);
				Collections.sort(list);
			}
		}
	}
	
	/**
	 * Removes seat from the TreeMap after making a reservation
	 * 
	 * @param num the seat number 
	 * @param row the alphabetical seat row
	 */
	public void removeSeats(int num, String row) {
		if (first.containsKey(num)) {
			ArrayList<String> alphas = first.get(num);
			for (int i = 0; i < alphas.size(); i++) {
				if (alphas.get(i).equals(row)) {
					alphas.remove(i);
					return;
				}
			}
		}
		if (econPlus.containsKey(num)) {
			ArrayList<String> alphas = econPlus.get(num);
			for (int i = 0; i < alphas.size(); i++) {
				if (alphas.get(i).equals(row)) {
					alphas.remove(i);
					return;
				}
			}
		}
		if (econ.containsKey(num)) {
			ArrayList<String> alphas = econ.get(num);
			for (int i = 0; i < alphas.size(); i++) {
				if (alphas.get(i).equals(row)) {
					alphas.remove(i);
					return;
				}
			}
		}
	}
	
	/**
	 * Determines the price of a particular seat
	 * 
	 * @param seat the seat number to check the price
	 * @return the price of the seat ($1,000 for first class, $500 for economy plus class, $250 for economy class)
	 */
	public int determinePrice(String seat) {
		int price = 0;
		int num = 0;
		String alpha = null;
		if (seat.length() == 2) {
			num = Integer.parseInt(Character.toString(seat.charAt(0)));
			alpha = Character.toString(seat.charAt(1));
		}
		else if (seat.length() == 3) {
			num = Integer.parseInt(Character.toString(seat.charAt(0)) + Character.toString(seat.charAt(1)));
			alpha = Character.toString(seat.charAt(2));
		}
		if (ORIGINAL_FIRST.containsKey(num)) {
			ArrayList<String> alphas = ORIGINAL_FIRST.get(num);
			for (int i = 0; i < alphas.size(); i++) {
				if (alphas.get(i).equals(alpha)) {
					price = FIRST_PRICE;
					return price;
				}
			}
		}
		if (ORIGINAL_ECON_PLUS.containsKey(num)) {
			ArrayList<String> alphas = ORIGINAL_ECON_PLUS.get(num);
			for (int i = 0; i < alphas.size(); i++) {
				if (alphas.get(i).equals(alpha)) {
					price = ECON_PLUS_PRICE;
					return price;
				}
			}
		}
		if (ORIGINAL_ECON.containsKey(num) && price == 0){
			ArrayList<String> alphas = ORIGINAL_ECON.get(num);
			for (int i = 0; i < alphas.size(); i++) {
				if (alphas.get(i).equals(alpha)) {
					price = ECON_PRICE;
					return price;
				}
			}
		}
		return price;
	}
	
	/**
	 * Determines the service of a particular seat
	 * 
	 * @param seat the seat number to check the service type
	 * @return the service type of the seat (First, Economy Plus, or Economy)
	 */
	public String determineService(String seat) {
		String service = null;
		int num = 0;
		String alpha = null;
		if (seat.length() == 2) {
			num = Integer.parseInt(Character.toString(seat.charAt(0)));
			alpha = Character.toString(seat.charAt(1));
		}
		else if (seat.length() == 3) {
			num = Integer.parseInt(Character.toString(seat.charAt(0)) + Character.toString(seat.charAt(1)));
			alpha = Character.toString(seat.charAt(2));
		}
		if (ORIGINAL_FIRST.containsKey(num)) {
			ArrayList<String> alphas = ORIGINAL_FIRST.get(num);
			for (int i = 0; i < alphas.size(); i++) {
				if (alphas.get(i).equals(alpha)) {
					service = FIRST_TYPE;
					return service;
				}
			}
		}
		if (ORIGINAL_ECON_PLUS.containsKey(num)) {
			ArrayList<String> alphas = ORIGINAL_ECON_PLUS.get(num);
			for (int i = 0; i < alphas.size(); i++) {
				if (alphas.get(i).equals(alpha)) {
					service = ECON_PLUS_TYPE;
					return service;
				}
			}
		}
		if (ORIGINAL_ECON.containsKey(num)) {
			ArrayList<String> alphas = ORIGINAL_ECON.get(num);
			for (int i = 0; i < alphas.size(); i++) {
				if (alphas.get(i).equals(alpha)) {
					service = ECON_TYPE;
					return service;
				}
			}
		}
		return service;
	}
	
	/**
	 * Shows the seat availability list in the airplane reservation system
	 */
	public void showAvailability() {
		System.out.println("Seat Availability");
		System.out.println();
		System.out.printf("First (price: $%d/seat)", FIRST_PRICE);
		System.out.println();
		printTreeMap(first);
		System.out.println();
		System.out.printf("Economy Plus Price (price: $%d/seat)", ECON_PLUS_PRICE);
		System.out.println();
		printTreeMap(econPlus);
		System.out.println();
		System.out.printf("Economy Price (price: $%d/seat)", ECON_PRICE);
		System.out.println();
		printTreeMap(econ);
		System.out.println();
	}
	
	/**
	 * Checks whether the particular seat is available to reserve
	 * 
	 * @param seat the seat to check for availability 
	 * @return a boolean value: true if it is available, false otherwise
	 */
	public boolean checkAvailability(String seat) {
		boolean check = false;
		int seatCol = 0;
		String seatRow = null;
		if (seat.length() == 2) {
			seatCol = Integer.parseInt(Character.toString(seat.charAt(0)));
			seatRow = Character.toString(seat.charAt(1));
		}
		else if (seat.length() == 3) {
			seatCol = Integer.parseInt(Character.toString(seat.charAt(0)) + Character.toString(seat.charAt(1)));
			seatRow = Character.toString(seat.charAt(2));
		}
		if (first.containsKey(seatCol)) {
			ArrayList<String> getRows = first.get(seatCol);
			for (int i = 0; i < getRows.size(); i++) {
				if (getRows.get(i).equals(seatRow)) {
					check = true;
					return check;
				}
			}
		}
		if (econPlus.containsKey(seatCol)) {
			ArrayList<String> getRows = econPlus.get(seatCol);
			for (int i = 0; i < getRows.size(); i++) {
				if (getRows.get(i).equals(seatRow)) {
					check = true;
					return check;
				}
			}
		}
		if (econ.containsKey(seatCol)) {
			ArrayList<String> getRows = econ.get(seatCol);
			for (int i = 0; i < getRows.size(); i++) {
				if (getRows.get(i).equals(seatRow)) {
					check = true;
					return check;
				}
			}
		}
		return check;
	}
	
	/**
	 * Heler method that prints the TreeMap of seats
	 * 
	 * @param toPrint the TreeMap to be printed 
	 */
	private void printTreeMap(TreeMap<Integer, ArrayList<String>> toPrint) {
		Set<Map.Entry<Integer, ArrayList<String>>> entries = toPrint.entrySet();
		for (Map.Entry<Integer, ArrayList<String>> entry: entries) {
			System.out.print(entry.getKey() + ": ");
			ArrayList<String> seatAlphas = entry.getValue();
			for (int j = 0; j < seatAlphas.size() - 1; j++) {
				System.out.print(seatAlphas.get(j) + ", ");
			}
			System.out.println(seatAlphas.get(seatAlphas.size() - 1));	
		}
	}
}
