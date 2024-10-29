/**
* This class test the functionality of the rider class.
* Known Bugs: None
*
* @author Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* October, 2024
* COSI 21A PA1
*/

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Rider;

class StudentRiderTest {

	Rider r;
	
	@Test
	void initTest() {
		r = new Rider("abc123", "Alewife", "Braintree");
		
		assertEquals("abc123", r.getRiderID());
		assertEquals("Alewife", r.getStarting());
		assertEquals("Braintree", r.getDestination());
		assertFalse(r.goingNorth());

	}
	
	@Test
	void testEquals() {
		r = new Rider("abc123", "Alewife", "Braintree");
		Rider r1 = new Rider("abc123", "a", "b");
		assertEquals(r1, r);
	}

	@Test
    public void testSwapDirection() {
        r = new Rider("abc123", "Alewife", "Braintree");
        assertEquals("South", r.direction); // Initially going south
        r.swapDirection();
        assertEquals("North", r.direction); // Should be going north now
        r.swapDirection();
        assertEquals("South", r.direction); // Should be going south again
    }

    @Test
    public void testToString() {
        r = new Rider("abc123", "Alewife", "Braintree");
        assertEquals("abc123 from Alewife to Braintree going South", r.toString());
    }
}
