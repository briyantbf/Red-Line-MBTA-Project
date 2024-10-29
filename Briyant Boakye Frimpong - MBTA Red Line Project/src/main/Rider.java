/**
* This class represents a rider on the red line. The ride has an ID, starting station, destination,
* and a direction.
* Known Bugs: None
*
* @author Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* October, 2024
* COSI 21A PA1
*/

package main;

public class Rider {
	// Initalizes String information about the rider
	public String riderID;
	public String startingStation;
	public String destinationStation;
	public String direction;

	/**
	 * This method construct a rider with an ID, a starting station, ending station, and as
	 * travelling south.
	 * 
	 * @param riderID is the rider's ID
	 * @param startingStation is the rider's starting station
	 * @param destinationStation is the rider's destination station
	 */
	public Rider(String riderID, String startingStation, String destinationStation) {
		this.riderID = riderID;
		this.startingStation = startingStation;
		this.destinationStation = destinationStation;
		this.direction = "South";
	}
	
	/**
	 * This method returns the name of this Rider's starting station
	 * 
	 * @return the rider's starting station
	 */
	public String getStarting() {
		return this.startingStation;
	}
	
	/**
	 * This method returns the rider's ending station
	 * 
	 * @return the rider's ending station
	 */
	public String getDestination() {
		return this.destinationStation;
	}
	
	/**
	 * This method returns the rider's ID
	 * 
	 * @return the rider's ID
	 */
	public String getRiderID() {
		return this.riderID;
	}
	
	/**
	 * This method returns a boolean representing if the rider is northbound, otherwise false.
	 * 
	 * @return a boolean representation of if the rider is going north
	 */
	public boolean goingNorth() {
		return this.direction.equals("North");
	}
	
	/**
	 * This methd swaps the rider's current direction to either north or south
	 */
	public void swapDirection() {
		if (this.direction.equals("North")) {
			this.direction = "South";
		}
		else {
			this.direction = "North";
		}
	}
	
	/**
	 * This method returns a string representation of the rider.
	 */
	@Override
	public String toString() {
		return this.riderID + " from " + this.startingStation + " to " + this.destinationStation + " going " + this.direction;
	}
	
	/**
	 * This method checks if this rider is equal to another object based on ID.
	 * 
	 * @param s is the object being compared to the rider
	 * @return a boolean represeting is the rider is equal to the object
	 */
	@Override
	public boolean equals(Object o) {
		// Check if the object is an instance of Rider
		if (o instanceof Rider) {
			// Casts the object to a Rider
			Rider rider = (Rider) o;
			// Compares rider IDs and returns a boolean
			return this.getRiderID().equals(rider.getRiderID());
		}
		// Returns false if the object isn't an instance of Rider
		return false;
	}
}
