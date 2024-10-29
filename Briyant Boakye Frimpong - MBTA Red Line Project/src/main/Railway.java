/**
* This class represents the red line railway and is made up of a list of stations.
* Known Bugs: I didn't get to debug so there probably is but not any known specifics.
*
* @author Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* October, 2024
* COSI 21A PA1
*/

package main;

public class Railway {

	public DoubleLinkedList<Station> railway;
	public String[] stationNames;
	
	/**
	 * This is the constructor method which constructs an emepty railway.
	 */
	public Railway() {
		this.railway = new DoubleLinkedList<>();
		this.stationNames = new String[18];
	}
	
	/**
	 * This method adds a station to the railway.
	 * 
	 * @param s is the station being added to the railway
	 */
	public void addStation(Station s) {
		// Adds the station to the linked list and array as long as there's space
		if (this.railway.size() < 18 && s != null) {
			this.railway.insert(s);
			for (int i = 0; i < this.stationNames.length; i++) {
				if (this.stationNames[i] == null) {
					this.stationNames[i] = s.stationName();
					return;
				}
			}
		}
		// Case where railway is full
		else if (this.railway.size() >= 18) {
			System.out.println("Railway is currently full.");
		}
		// Case where a null station is being added
		else {
			System.out.println("Can't add null station.");
		}
	}
	
	/**
	 * This method sets a rider’s direction based on the order of the stations in the railway and
	 * adds the rider to the correct station in the railway.
	 * 
	 * @param r is the rider who's direction is being set
	 */
	public void addRider(Rider r) {
		setRiderDirection(r);
        Node<Station> currentNode = this.railway.getFirst();
        while (currentNode != null) {
            Station station = currentNode.getData();
            if (station.stationName().equals(r.getStarting())) {
                station.addRider(r);
                return;
            }
            currentNode = currentNode.getNext();
        }
	}
	
	/**
	 * This method adds a train to the appropriate station in this railway.
	 * 
	 * @param t is the train being added to the station in the railway
	 */
	public void addTrain(Train t) {
		Node<Station> currentNode = this.railway.getFirst();
		while (currentNode != null) {
			Station station = currentNode.getData();
			if (station.stationName().equals(t.currentStation)) {
				station.addTrain(t);
				return;
			}
			currentNode = currentNode.getNext();
		}
	}
	
	/**
	 * This method sets a rider’s direction based on the rider’s starting and ending stations.
	 * 
	 * @param r is the rider who's direction is being set
	 */
	public void setRiderDirection(Rider r) {
		int startingIndex = -1;
        int destinationIndex = -1;

        for (int i = 0; i < this.stationNames.length; i++) {
            if (this.stationNames[i] != null) {
                if (this.stationNames[i].equals(r.getStarting())) {
                    startingIndex = i;
                }
                if (stationNames[i].equals(r.getDestination())) {
                    destinationIndex = i;
                }
            }
        }

        if (startingIndex != -1 && destinationIndex != -1) {
            if (startingIndex < destinationIndex && r.goingNorth()) {
                r.swapDirection();
            } else if (destinationIndex < startingIndex && !r.goingNorth()) {
                r.swapDirection();
            }
        }
	}
	
	/**
	 * This method executes one simulation of the railway.
	 * 
	 * @return a log of events that occured during the simulation
	 */
	public String simulate() {
		String log = "";

        Node<Station> currentNode = this.railway.getFirst();
        while (currentNode != null) {
            Station station = currentNode.getData();

            Train northTrain = station.northBoardTrain();
            if (northTrain != null) {
                log += northTrain.toString() + "\n";
            }

            Train southTrain = station.southBoardTrain();
            if (southTrain != null) {
                log += southTrain.toString() + "\n";
            }

            currentNode = currentNode.getNext();
        }

        return log;
	}
	
	/**
	 * This method returns the stations list’s string representation.
	 */
	@Override
	public String toString() {
		String result = "";
        Node<Station> currentNode = this.railway.getFirst();
        while (currentNode != null) {
            Station station = currentNode.getData();
            result += station.toString() + "\n";
            currentNode = currentNode.getNext();
        }
        return result;
	}
}
