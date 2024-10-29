/**
* This class provides the implementation of a generic circular first-in-first-out queue. 
* Known Bugs: None
*
* @author Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* October, 2024
* COSI 21A PA1
*/

package main;

import java.util.NoSuchElementException;

public class Queue<T> {
	// Initialize array, indexes for the head and tail, and numEntries
	public T[] q;
	public int head;
	public int tail;
	public int numEntries;
	
	/**
	 * This is the constructor method which creates a generic array and intializes the head, tail, and numEntries variables to 0.
	 * 
	 * @param capacity is the number of elements the queue can hold
	 */
	@SuppressWarnings("unchecked")
	public Queue(int capacity) {
		this.q = (T[]) new Object[capacity];
		this.head = 0;
		this.tail = 0;
		this.numEntries = 0;
	}
	
	/**
	 * This method adds an element at the tail of the queue.
	 * 
	 * @param element is the element being added
	 */
	public void enqueue(T element) {
		if (numEntries == this.q.length) {
			System.out.println("Queue is full!");
		}
		else {
			// Sets the element of the tail to the element passed through the method
			this.q[tail] = element;
			// Moves the tail index to the next element and uses mod to wrap around when necessary
			tail = (tail + 1) % this.q.length;
			// Increments the number of entries
			this.numEntries++;
		}
	}
	
	/**
	 * This method removes the element at the head of the queue but if there isn't any element, it throws
	 * a NoSuchElementException.
	 */
	public void dequeue() { 
		if (this.q[head] == null) {
			throw new NoSuchElementException();
		}
		else {
			// Sets the element of the head to null
			this.q[head] = null;
			// Moves the head index to the next element and uses mod to wrap around when necessary
			head = (head + 1) % this.q.length;
			// Decrements the number of entries
			this.numEntries--;
		}
	} 
	
	/**
	 * This method returns the element at the head of the queue. If the number of entries is zero, it throws a
	 * NoSuchElementException.
	 *  
	 * @return the element at the head of the queue or throw an exception if none found
	 */
	public T front() {
		if (numEntries == 0) {
			throw new NoSuchElementException();
		}
		return this.q[head];
	}
	
	/**
	 * This method returns the number of elements in the queue.
	 *  
	 * @return the number of elements in the queue
	 */
	public int size() {
		return this.numEntries;
	}
	
	/**
	 * This method returns a String representation of the queue's elements using a for loop.
	 */
	@Override
	public String toString() {
		// Returns an empty string representation of elements
		if (numEntries == 0) {
			return "[]";
		}
		else {
			String elements = "[";
			int index = head;
			// For loop which adds each element in the queue to the string
			for (int i = 0; i < numEntries; i++) {
				elements += this.q[index];
				// Adds a comma and a space if it isn't the last element
				if (i < numEntries - 1) {
					elements += ", ";
				}
				// Moves the index to the next element and uses mod to wrap around when necessary
				index = (index + 1) % this.q.length;
			}
			elements += "]";
			return elements;
		}
	}
}