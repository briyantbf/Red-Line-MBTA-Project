/**
* This class test the functionality of the station class.
* Known Bugs: None
*
* @author Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* October, 2024
* COSI 21A PA1
*/

package test;

import main.Station;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Rider;
import main.Train;

class StudentStationTest {
	Station s;
	
	@Test
	void initTest() {
		s = new Station("s");
		assertEquals(null, s.northBoardTrain());
		assertEquals(null, s.southBoardTrain());
		assertEquals("s", s.stationName());
		assertEquals(0, s.northBoundRiders.size());
		assertEquals(0, s.southBoundRiders.size());
	}
	
	@Test
	void addWaitingRiders() {
		s = new Station("s");
		
		Rider r = new Rider("abc", "s", "d");
		
		s.addRider(r);
		
		r = new Rider("abc", "s", "d");
		r.swapDirection();
		s.addRider(r);
		
		assertEquals(1, s.northBoundRiders.size());
		assertEquals(1, s.southBoundRiders.size());
		
		s.addRider(r);
		s.addRider(r);
		s.addRider(r);
		
		assertEquals(4, s.northBoundRiders.size());
	}
	
	@Test
    void addRiderNotAtStation() {
        s = new Station("s");

        Rider r = new Rider("abc", "a", "d"); // Rider's starting station does not match

        assertFalse(s.addRider(r)); // Should return false
        assertEquals(0, s.northBoundRiders.size());
        assertEquals(0, s.southBoundRiders.size());
    }

    @Test
    void addRiderStationFull() {
        s = new Station("s");

        // We assume that capacity is 20 and fill the northbound queue
        for (int i = 0; i < 20; i++) {
            Rider r = new Rider("r" + i, "s", "d");
            s.addRider(r);
        }

        // Adding one more should not increase the size
        Rider overflow = new Rider("overflow", "s", "d");
        s.addRider(overflow);
        assertEquals(20, s.northBoundRiders.size());
    }

	@Test
	void addTrains() {
		s = new Station("s");
		
		Train t = new Train("s", 1);
		s.addTrain(t);
		
		t = new Train("s", 0);
		s.addTrain(t);
		
		assertEquals(1, s.northBoundTrains.size());
		assertEquals(1, s.southBoundTrains.size());
		assertEquals(0, s.northBoundRiders.size());
		assertEquals(0, s.southBoundRiders.size());
		
		s.addTrain(t);
		s.addTrain(t);
		s.addTrain(t);
		
		assertEquals(4, s.northBoundTrains.size());
		assertEquals(1, s.southBoundTrains.size());
	}
	
	@Test
	void testEquals() {
		s = new Station("s");
		
		Station t = new Station("t");
		assertFalse(t.equals(s));
		
		t = new Station("s");
		assertEquals(t, s);
	}
	
	@Test
	void moveTrains() {
		s = new Station("s");
		
		//1 = south-bound trains
		Train t = new Train("s", 1);
		s.addTrain(t);
		s.addTrain(t);
		//0 = north-bound trains
		t = new Train("s", 0);
		s.addTrain(t);
		s.addTrain(t);
		
		assertEquals(2, s.northBoundTrains.size());
		assertEquals(2, s.southBoundTrains.size());
		
		s.moveTrainNorthToSouth();
		
		assertEquals(1, s.northBoundTrains.size());
		assertEquals(3, s.southBoundTrains.size());
		
		s.moveTrainSouthToNorth();
		s.moveTrainSouthToNorth();
		s.moveTrainSouthToNorth();
		
		assertEquals(4, s.northBoundTrains.size());
		assertEquals(0, s.southBoundTrains.size());		
	}

	@Test
    void testToString() {
        s = new Station("s");

        assertEquals("Station: s\n0 north-bound trains waiting\n0 south-bound trains waiting\n0 north-bound passengers waiting\n0 south-bound passengers waiting", s.toString());

        Train t = new Train("s", 1);
        s.addTrain(t);

        assertEquals("Station: s\n0 north-bound trains waiting\n1 south-bound trains waiting\n0 north-bound passengers waiting\n0 south-bound passengers waiting", s.toString());
    }
}
