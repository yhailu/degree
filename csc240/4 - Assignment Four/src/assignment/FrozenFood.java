/**
 * CSC240
 * Dr. Richard Epstein
 * Assignment #4
 * 
 * @author Marshall Bowers
 */

package assignment;

import java.util.Scanner;
import java.util.StringTokenizer;

public class FrozenFood extends Element {
	private String name;
	private String manufacturer;
	private String nutrients;

	private static Scanner input = new Scanner(System.in);

	/**
	 * Creates a new FrozenFood object with all blank fields.
	 */

	public FrozenFood() {
		name = "";
		manufacturer = "";
		nutrients = "";
	}

	/**
	 * Creates a new FrozenFood object with just a name.
	 * 
	 * @param name
	 */

	public FrozenFood(String name) {
		this.name = name;
		manufacturer = "";
		nutrients = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getNutrients() {
		return nutrients;
	}

	public void setNutrients(String nutrients) {
		this.nutrients = nutrients;
	}

	public void readIn() {
		System.out.print("\nEnter the name of the Frozen Food: ");
		name = input.nextLine().toUpperCase();

		System.out.print("Enter the name of the manufacturer: ");
		manufacturer = input.nextLine().toUpperCase();

		System.out.print("Enter the number of nutrients for this food: ");
		
		try {
			int numNu = Integer.parseInt(input.nextLine());
			
			System.out.print("Enter the nutrients when prompted: \n");
			nutrients = "";
			
			for (int i = 0; i < numNu; i++) {
				System.out.print((i + 1) + ") ");
				nutrients += input.nextLine().toUpperCase();
				if (i != numNu - 1)
					nutrients += ", ";
			}
		} catch (Exception e) {
			System.out.println("That is not a valid number.");
			readIn();
		}
	}

	public void display() {
		System.out.println("Name: " + name);
		System.out.println("Manufacturer: " + manufacturer);
		System.out.println("Nutrients: ");
		StringTokenizer token = new StringTokenizer(nutrients.replace(',', ' '));
		while (token.hasMoreTokens()) {
			System.out.println("  " + token.nextToken());
		}
	}

	public boolean equals(Object dobj) {
		if (dobj.getClass().getName().equals("assignment.FrozenFood"))
			if (name.equals(((FrozenFood) dobj).name))
				return true;

		return false;
	}

	public Element clone() {
		FrozenFood clone = new FrozenFood();

		clone.setName(getName());
		clone.setManufacturer(getManufacturer());
		clone.setNutrients(getNutrients());

		return clone;
	}

	public String toString() {
		return "\nName: " + name + "\nManufacturer: " + manufacturer + "\nNutrients: " + nutrients;
	}
}
