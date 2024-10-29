/**
* This class test the functionality of the queue class.
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

import main.Queue;

class StudentQueueTest {

	@Test
    public void testEnqueueAndToString() {
        Queue<Integer> queue = new Queue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals("[1, 2, 3]", queue.toString());
    }

    @Test
    public void testDequeue() {
        Queue<Integer> queue = new Queue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        assertEquals("[2, 3]", queue.toString());
    }

    @Test
    public void testDequeueEmptyQueue() {
        Queue<Integer> queue = new Queue<>(5);
        queue.dequeue(); // Throws NoSuchElementException which is correct
		
    }

    @Test
    public void testFront() {
        Queue<Integer> queue = new Queue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(1, queue.front());
    }

    @Test
    public void testFrontEmptyQueue() {
        Queue<Integer> queue = new Queue<>(5);
        queue.front(); // Throws NoSuchElementException which is correct
    }

    @Test
    public void testSize() {
        Queue<Integer> queue = new Queue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(3, queue.size());
    }

    @Test
    public void testEnqueueFullQueue() {
        Queue<Integer> queue = new Queue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        assertEquals("[1, 2, 3]", queue.toString());
    }

    @Test
    public void testCircularBehavior() {
        Queue<Integer> queue = new Queue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        queue.enqueue(4);
        assertEquals("[2, 3, 4]", queue.toString());
    }
}
