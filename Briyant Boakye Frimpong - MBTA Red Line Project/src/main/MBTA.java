/**
* This class contains my program’s main method and runs a simulation of a railway.
* Known Bugs: None
*
* @author Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* October, 2024
* COSI 21A PA1
*/

package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MBTA {

	public static final int SOUTHBOUND = 1;
	public static final int NORTHBOUND = 0;
	
	static final int TIMES = 6;
	static Railway r;
	
	/**
	 * This is the main method which constructs the railway with the stations, riders, and trains
	 * loaded from the text files and then runs the simulation.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Initalize a new railway object
		r = new Railway();
		
		// Call methods 
		initTrains("redLine.txt");
		initRiders("riders.txt");
		initStations("trains.txt");
		
		runSimulation();
	}
	
	/**
	 * This method runs the simulation using TIMES and railway’s simulate().
	 */
	public static void runSimulation() {
		for (int i = 0; i < TIMES; i++) {
			r.simulate();
		}
	}
	
	/**
	 * This method constructs trains from an input file and adds them to the railway.
	 * 
	 * @param trainsFile is the trains input file
	 * @throws FileNotFoundException 
	 */
	public static void initTrains(String trainsFile) {
		try {
			Scanner scn = new Scanner(new File(trainsFile));
			while (scn.hasNextLine()) {
				String stationName = scn.nextLine(); // Read the station name
				if (scn.hasNextLine()) {
					String directionString = scn.nextLine(); // Read the direction

					// Determine the direction based on string comparison
					int direction = -1; // Initialize to an invalid value
					if (directionString.equals("1")) {
						direction = SOUTHBOUND;
					} else if (directionString.equals("0")) {
						direction = NORTHBOUND;
					}

					// Create a new train object
					Train t = new Train(stationName, direction);
					r.addTrain(t);
				}
			}
			scn.close();
		}
		catch (FileNotFoundException e) {
				System.out.println("The file " + trainsFile + " was not found.");
		}
	}
	
	/**
	 * This method constructs riders from an input file and adds them to the railway.
	 * 
	 * @param ridersFile is the riders input file
	 */
	public static void initRiders(String ridersFile) {
		try {
			Scanner scn = new Scanner(new File(ridersFile));
			while (scn.hasNextLine()) {
				// Read the rider ID, starting station, and destination station
				String riderID = scn.nextLine(); // Rider ID
				String startingStation = scn.nextLine(); // Starting station
				String destinationStation = scn.nextLine(); // Destination station
				
				// Create a new rider object
				Rider rider = new Rider(riderID, startingStation, destinationStation);
				
				// Set the rider's direction based on their starting and destination stations
				r.setRiderDirection(rider);
				// Adds rider
				r.addRider(rider);
			}
			scn.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("The file " + ridersFile + " was not found.");
		}
	}
	
	/**
	 * This method constructs stations from an input file and adds them to the railway.
	 * 
	 * @param stationsFile is the stations input file
	 * @throws FileNotFoundException 
	 */
	public static void initStations(String stationsFile) {
		try {
			Scanner scn = new Scanner(new File(stationsFile));
			while (scn.hasNextLine()) {
				String stationName = scn.nextLine(); // Read the station name

				// Create a new station object
				Station s = new Station(stationName);

				// Add the station to the railway
				r.addStation(s);
			}
			scn.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("The file " + stationsFile + " was not found.");
		}
	}
}
