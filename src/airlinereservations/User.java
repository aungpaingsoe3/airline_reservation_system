package airlinereservations;

import java.util.*;
import java.io.*;

/**
 * Programming Assignment 2 User Program File
 * 
 * @author Aung Paing Soe
 * @version 1.0 3/4/2023
 */

/**
 * A Java class that manages all the users of this airline reservation system
 */
public class User {
	
	private String name;
	private String id;
	private String password;
	private int price;
	private static HashMap<String, User> userList;
	
	/**
	 * Constructor that initializes the HashMap of all registered users
	 */
	public User() {
		userList = new HashMap<>();
	}
	
	/**
	 * Constructor that initializes the user's name, id, password, and set price to 0
	 * 
	 * @param name the name of this user
	 * @param id the user id of this user
	 * @param password the password of this user
	 */
	public User(String name, String id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.price = 0;
	}
	
	/**
	 * Returns the name of this user
	 * 
	 * @return the name of this user
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the price this user reserved seats
	 * 
	 * @return the price of this user
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Returns the user id of this user
	 * 
	 * @return the user id of this user
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Private method that returns the password of this user used to check during sign in
	 * 
	 * @return the password of this user
	 */
	private String getPassword() {
		return password;
	}
	
	/**
	 * Adds this user's price to a particular price
	 * 
	 * @param price the price after addition
	 */
	public void addPrice(int price) {
		this.price += price;
	}

	/**
	 * Subtracts this user's price to a particular price
	 * 
	 * @param price the price after subtraction
	 */
	public void subtractPrice(int price) {
		this.price -= price;
	}
	
	/**
	 * Adds the user to the HashMap of userList
	 * 
	 * @param name the name of the user to be added
	 * @param id the user id of the user to be added
	 * @param password the password of the user to be added
	 */
	public void addUser(String name, String id, String password) {
		User toAdd = new User(name, id, password);
		userList.put(id, toAdd);
	}
	
	/**
	 * Returns the user using the user id
	 * 
	 * @param id the user id to search the user
	 * @return the user object that corresponds to the user id
	 */
	public User getUserbyID(String id) {
		User toGet = null;
		if (userList.containsKey(id)) {
			 toGet = userList.get(id);
		}
		return toGet;
	}
	
	/**
	 * Returns the user using the name of the user
	 * 
	 * @param name the name of the user to search the user object
	 * @return the user object that corresponds to the name of the user
	 */
	public User getUserbyName(String name) {
		User toGet = null;
		Set<Map.Entry<String, User>> entries = userList.entrySet();
		for (Map.Entry<String, User> entry: entries) {
			User toCheck = entry.getValue();
			if (toCheck.name.equals(name)) {
				toGet = toCheck;
			}
		}
		return toGet;
	}
	
	/**
	 * Checks whether the user provided correct information during login
	 * 
	 * @param id the user id the user entered
	 * @param password the password the user entered
	 * @return a boolean value: true if it is correct, false otherwise
	 */
	public boolean checkUserLogin(String id, String password) {
		if (userList.containsKey(id)) {
			User toCheck = userList.get(id);
			if (toCheck.password.equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Reads the Users File and populate them into the HashMap
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
					addUser(splitInfo[0], splitInfo[1], splitInfo[2]);
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
	 * Writes all the registered users' information to the User File 
	 * 
	 * @param f the file to write the registered users' information to
	 * @throws IOException if the file is not found
	 */
	public void writeFile(File f) throws IOException {
		FileWriter fw = new FileWriter(f);
		PrintWriter pw = new PrintWriter(fw);

		Set<Map.Entry<String, User>> entries = userList.entrySet();
		for (Map.Entry<String, User> entry: entries) {
			pw.printf("%s  %s  %s\n", entry.getValue().getName(), entry.getKey(), entry.getValue().getPassword());
		}
		
		pw.close();
		fw.close();
	}
}
