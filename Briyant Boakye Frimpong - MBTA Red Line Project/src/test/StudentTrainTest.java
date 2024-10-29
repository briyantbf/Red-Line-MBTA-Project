package test;

import main.Train;
import main.Rider;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentTrainTest {

	Train t;
	Rider r;
	
	@Test
	void initTest() {
		t = new Train ("s", 1);
		
		assertFalse(t.goingNorth());

		assertEquals(0, t.passengerIndex);
		assertEquals(Train.TOTAL_PASSENGERS, t.passengers.length);
		assertEquals("s", t.getStation());
		
		t.updateStation("u");
		
		assertEquals("u", t.getStation());

		
	}
	
	@Test
	void passengerOperations() {
		t = new Train ("s", 1);
		assertEquals("", t.currentPassengers());
		assertTrue(t.hasSpaceForPassengers());
		assertEquals("", t.disembarkPassengers());
		
		//rider is at same station, same direction
		r = new Rider("abc", "s", "t");
		assertTrue(t.addPassenger(r));
		assertEquals(1, t.passengerIndex);
		assertTrue(t.disembarkPassengers().length()==0);

		//rider is at same station, same direction; different destination
		r = new Rider("abc", "s", "u");
		assertTrue(t.addPassenger(r));
		assertTrue(t.disembarkPassengers().length()==0);
		t.updateStation("u");
		assertTrue(t.disembarkPassengers().length()>0);

		//rider is at same station, different direction
		r = new Rider("abc", "s", "t");
		r.swapDirection();
		assertFalse(t.addPassenger(r));

		//rider is at different station, same direction
		r = new Rider("abc", "t", "u");
		assertFalse(t.addPassenger(r));
		
		//rider is at different station, different direction
		r = new Rider("abc", "t", "u");
		r.swapDirection();
		assertFalse(t.addPassenger(r));

		
	}

	@Test
    void disembarkPassengersWhenNoPassengers() {
        t = new Train("s", 1);
        assertEquals("", t.disembarkPassengers()); // Should return an empty string
    }

    @Test
    void addPassengerWhenTrainIsFull() {
        t = new Train("s", 1);

        // Fill the train
        for (int i = 0; i < Train.TOTAL_PASSENGERS; i++) {
            r = new Rider("r" + i, "s", "d");
            assertTrue(t.addPassenger(r));
        }

        // Try to add one more rider which should fail
        r = new Rider("overflow", "s", "d");
        assertFalse(t.addPassenger(r));
        assertEquals(Train.TOTAL_PASSENGERS, t.passengerIndex); // Capacity should remain the same
    }

    @Test
    void testSwapDirection() {
        t = new Train("s", 1);
        assertFalse(t.goingNorth()); // Initially southbound

        t.swapDirection(); // Change to northbound
        assertTrue(t.goingNorth());

        t.swapDirection(); // Change back to southbound
        assertFalse(t.goingNorth());
    }

    @Test
    void testCurrentPassengers() {
        t = new Train("s", 1);
        assertEquals("", t.currentPassengers()); // No passengers yet

        r = new Rider("r1", "s", "d");
        t.addPassenger(r);
        assertEquals("r1 from s to d going South", t.currentPassengers()); // Should match the Rider's toString
    }
}
