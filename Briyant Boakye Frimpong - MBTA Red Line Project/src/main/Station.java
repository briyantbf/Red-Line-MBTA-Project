/**
* This class represents a station on the red line. The station tracks which trains and riders are
* waiting to go north or south.
* Known Bugs: Nothing is actually inside the queues when things are enqueued.
*
* @author Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* October, 2024
* COSI 21A PA1
*/

package main;

public class Station {

	public Queue<Rider> northBoundRiders;
	public Queue<Rider> southBoundRiders;
	public Queue<Train> northBoundTrains;
	public Queue<Train> southBoundTrains;
	public String stationName;
	
	/**
	 * This method constructs an empty station with a given name.
	 * 
	 * @param name is the name of the station
	 */
	public Station(String name) {
		this.stationName = name;
		this.northBoundRiders = new Queue<>(20);
		this.northBoundTrains = new Queue<>(20);
		this.southBoundRiders = new Queue<>(20);
		this.southBoundTrains = new Queue<>(20); 
	}
	
	/**
	 * This method adds a rider to the correct queue depending on which direction the rider is
	 * going and as long as they are at the right station. It returns true if rider is added and
	 * false otherwise.
	 * 
	 * @param r is the rider being added
	 * @return a boolean representation if the addition of the rider was completed
	 */
	public boolean addRider(Rider r) {
		if (r.getStarting().equals(this.stationName)) {
			if (r.goingNorth()) {
				northBoundRiders.enqueue(r);
				return true;
			}
			else {
				southBoundRiders.enqueue(r);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method moves a train into this Station, removes all of the passengers who are meant to
	 * disembark at the station, and places the train in the appropriate queue depending on its
	 * direction.
	 * 
	 * @param t is the train being moved into this station
	 * @return a string that includes the passengers that were removed at the station
	 */
	public String addTrain(Train t) {
		// Updates the train's current station name
		t.updateStation(this.stationName());
		// String representation of disembarked passengers
		String disembarkedPassengers = t.disembarkPassengers();
		// Adds the train to correct queue based on its direction
		if (t.goingNorth()) {
			this.northBoundTrains.enqueue(t);
		}
		else {
			this.southBoundTrains.enqueue(t);
		}
		return this.stationName + " Disembarking Passengers:\n" + disembarkedPassengers;
	}
	
	/**
	 * This method prepares a southbound train of passengers by dequeuing a train from the
	 * southbound train queue, adding as many southbound riders to the train as possible, and
	 * return the train that has been filled or null if there are no waiting southbound trains.
	 * 
	 * @return the train that has been filled or null if there are no southbound trains waiting
	 */
	public Train southBoardTrain() {
		// No southbound trains waiting so it returns null
		if (this.southBoundTrains.size() == 0) {
			return null;
		}
		// Adds as many southbound passengers to the next available train as possible
		Train train = this.southBoundTrains.front();
		this.southBoundTrains.dequeue();
		while (train.hasSpaceForPassengers() && this.southBoundRiders.size() > 0) {
			train.addPassenger(this.southBoundRiders.front());
			this.southBoundRiders.dequeue();
		}
		return train;
	}
	
	/**
	 * This method prepares a northbound train of passengers by dequeuing a train from the
	 * northbound train queue, adding as many northbound riders to the train as possible, and
	 * return the train that has been filled or null if there are no waiting northbound trains.
	 * 
	 * @return the train that has been filled or null if there are no southbound trains waiting
	 */
	public Train northBoardTrain() {
		// No northbound trains waiting so it returns null
		if (this.northBoundTrains.size() == 0) {
			return null;
		}
		// Adds as many northbound passengers to the next available train as possible
		Train train = this.northBoundTrains.front();
		this.northBoundTrains.dequeue();
		while (train.hasSpaceForPassengers() && this.northBoundRiders.size() > 0) {
			train.addPassenger(this.northBoundRiders.front());
			this.northBoundRiders.dequeue();
		}
		return train;
	}
	
	/**
	 * This method changes the direction of the first waiting norhbound train and moves it to the
	 * southbound queue.
	 */
	public void moveTrainNorthToSouth() {
		// Enqueues the train to the southbound queue and swaps the direction it's going
		if (northBoundTrains.size() > 0) {
			Train train = this.northBoundTrains.front();
			this.southBoundTrains.enqueue(train);
			train.swapDirection();
			this.northBoundTrains.dequeue();
		}
	}
	
	/**
	 * This method changes the direction of the first waiting southbound train and moves it to the
	 * northbound queue.
	 */
	public void moveTrainSouthToNorth() {
		// Enqueues the train to the northbound queue and swaps the direction it's going
		if (southBoundTrains.size() > 0) {
			Train train = southBoundTrains.front();
			this.northBoundTrains.enqueue(train);
			train.swapDirection();
			this.southBoundTrains.dequeue();
		}
	}
	
	/**
	 * This method returns the name and status of the station.
	 */
	@Override
	public String toString() {
		return "Station: " + this.stationName() + "\n" +
		this.northBoundTrains.size() + " north-bound trains waiting\n" +
		this.southBoundTrains.size() + " south-bound trains waiting\n" +
		this.northBoundRiders.size() + " north-bound passengers waiting\n" +
		this.southBoundRiders.size() + " south-bound passengers waiting";
	}
	
	/**
	 * This method returns the name of the station.
	 * 
	 * @return the name of the station
	 */
	public String stationName() {
		return this.stationName;
	}
	
	/**
	 * This method checks if a Station is equal to some object based on name.
	 */
	@Override
	public boolean equals(Object o) {
		// Checks if the object is a station, rider, or train and compares the name
		if (o instanceof Station) {
			Station station = (Station) o;
			return this.stationName().equals(station.stationName());
		}
		else if (o instanceof Rider) {
			Rider rider = (Rider) o;
			return this.stationName().equals(rider.getStarting()) || this.stationName().equals(rider.getDestination());
		}
		else if (o instanceof Train) {
			Train train = (Train) o;
			return this.stationName().equals(train.getStation());
		}
		return false;
	}
}
