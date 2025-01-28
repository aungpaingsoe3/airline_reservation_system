package airlinereservations;

import java.util.*;

/**
 * Programming Assignment 2 Employee Program File
 * 
 * @author Aung Paing Soe
 * @version 1.0 3/4/2023
 */

/**
 * A Java class that manages all the employees that uses the airline reservation system
 */
public class Employee {

	private ArrayList<Integer> employees;
	
	/**
	 * Constructor that initializes the ArrayList containing all employee ids
	 */
	public Employee() {
		employees = addIDs();
	}
	
	/**
	 * Initializes the ArrayList with the employee ids
	 * 
	 * @return the arraylist with added employee ids
	 */
	private ArrayList<Integer> addIDs() {
		employees = new ArrayList<Integer>();
		employees.add(15356);
		employees.add(19854);
		employees.add(26674);
		employees.add(23477);
		employees.add(98533);
		employees.add(12345);
		employees.add(54162);
		employees.add(67890);
		return employees;
	}
	
	/**
	 * Checks whether the provided employee id is in the ArrayList 
	 * 
	 * @param id the employee id to check for validity
	 * @return a boolean value: true if it is valid, false otherwise
	 */
	public boolean checkEmployee(String id) {
		for (int i = 0; i < employees.size(); i++) {
			int toCheck = employees.get(i);
			if (toCheck == Integer.parseInt(id)) {
				return true;
			}
		}
		return false;
	}
	
}
