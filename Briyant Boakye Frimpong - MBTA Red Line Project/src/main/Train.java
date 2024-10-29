/**
* This class represents a train on the red line. All trains hold a specified number of passengers
* in an array, the current number of passengers, the current station where the train is, and the
* train’s current direction.
* Known Bugs: None
*
* @author Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* October, 2024
* COSI 21A PA1
*/

package main;

public class Train {

	public static final int TOTAL_PASSENGERS = 10;
	public Rider[] passengers;
	public int passengerIndex;
	public String currentStation;
	public int direction;
	
	/**
	 * This is the constructor method which constructs an emepty train at a given station going
	 * either north or south.
	 * 
	 * @param currentStation is the station the train is at on the red line
	 * @param direction is the way the train is going which can either be south (1) or north (0)
	 */
	public Train(String currentStation, int direction) {
		this.passengers = new Rider[TOTAL_PASSENGERS];
		this.passengerIndex = 0;
		this.currentStation = currentStation;
		this.direction = direction;
	}
	
	/**
	 * This method returns a boolean representing if the train is northbound, otherwise false.
	 * 
	 * @return a boolean representation of if the train is going north
	 */
	public boolean goingNorth() {
		return this.direction == 0;
	}
	
	/**
	 * This method swaps the train's direction.
	 */
	public void swapDirection() {
		if (this.direction == 0) {
			this.direction = 1;
		}
		else {
			this.direction = 0;
		}
	}
	
	/**
	 * This method returns a string representation of the current passengers on the train.
	 * 
	 * @return the current passengers on the train
	 */
	public String currentPassengers() {
		String listOfPassengers = "";
		// Adds the toString method of the passenger to the string if their value 
		for (int i = 0; i < passengerIndex; i++) {
			if (passengers[i] != null) {
				listOfPassengers += passengers[i].toString();
			}
			// Only adds an extra line if it isn't the last passenger
			if (i < passengerIndex - 1) {
				listOfPassengers += "\n";
			}
		}
		return listOfPassengers;
	}
	
	/**
	 * This method adds a passenger to the train as long as the rider is at the correct station to
	 * enter the train, the train is going in the appropriate direction, and there is space on the
	 * train.
	 * 
	 * @param r is the passenger being added to the  train
	 * @return a boolean representation if the addition of the rider was completed
	 */
	public boolean addPassenger(Rider r) {
		if (r.getStarting().equals(this.currentStation) && r.goingNorth() == this.goingNorth()) {
			if (hasSpaceForPassengers()) {
				// Adds passenger to the next available spot and passenger index is incremented
				passengers[passengerIndex] = r;
				passengerIndex++;
				return true;
			}
		}
		// Returns false if passenger can't be added
		return false;
	}
	
	/**
	 * This method returns true if the train has space for additional passengers, otherwise false.
	 * 
	 * @return a boolean representation if the train has space for additional passengers
	 */
	public boolean hasSpaceForPassengers() {
		return passengerIndex < TOTAL_PASSENGERS;
	}
	
	/**
	 * This method removes all of the passengers who should be exiting at the train's current
	 * station and returns a string of the removed passengers.
	 * 
	 * @return a string of removed passengers
	 */
	public String disembarkPassengers() {
		String removedPassengers = "";
		int removedCount = 0;
		// Iterates through the train and removes passengers accordingly
		for (int i = 0; i < passengerIndex; i++) {
			if (this.currentStation.equals(passengers[i].destinationStation)) {
				//
				removedPassengers += passengers[i].toString() + " has been disembarked.";
				// Only adds an extra line if it isn't the last passenger
				if (i < passengerIndex - 1) {
					removedPassengers += "\n";
				}
				removedCount++;
			}
			// Shifts the passengers foward to get rid of null values in between passengers
			else if (removedCount > 0) {
				passengers[i -  removedCount] = passengers[i];
				passengers[i] = null;
			}
		}
		// Updates passenger index
		passengerIndex -= removedCount;
		return removedPassengers;
	}
	
	/**
	 * This method updates the name of this train’s current station to be the name of another
	 * station.
	 * 
	 * @param s is the name of the new station the train is updating to
	 */
	public void updateStation(String s) {
		this.currentStation = s;
	}
	
	/**
	 * This method returns the name of the train’s current station.
	 * 
	 * @return the train's current station
	 */
	public String getStation() {
		return this.currentStation;
	}
	
	/**
	 * This method returns a string representation of the rider.
	 */
	@Override
	public String toString() {
		String stringDirection;
		if (goingNorth()) {
			stringDirection = "North";
		}
		else {
			stringDirection = "South";
		}
		return "Currently at " + this.currentStation + " going " + stringDirection + " with " +
		this.passengerIndex + " passengers.";
	}
}
