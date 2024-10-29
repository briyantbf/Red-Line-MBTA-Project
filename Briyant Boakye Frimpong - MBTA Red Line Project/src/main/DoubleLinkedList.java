/**
* This class provides the implementation of a generic non-circular doubly linked list.
* Known Bugs: None
*
* @author Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* October, 2024
* COSI 21A PA1
*/

package main;

public class DoubleLinkedList<T> {
	// Initialize pointers and size
	public Node<T> head;
	public Node<T> tail;
	public int size;

	/**
	 * This method initalizes a doubly linked list to have 0 elements.
	 */
	public DoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 * This method gets the first node in the list or null if one does not exist.
	 * 
	 * @return the first node in the list
	 */
	public Node<T> getFirst() {
		return this.head;
	}
	
	/**
	 * This method adds an element to the end of this list.
	 *
	 * @param element is the element being added to the end of the list
	 */
	public void insert(T element) {
		// Initializes the new node
		Node<T> newNode = new Node<>(element);

		// Sets pointers accordingly
		if (this.size == 0) {
			this.head = newNode;
			this.tail = newNode;
		}
		else {
			this.tail.setNext(newNode);
			newNode.setPrev(this.tail);
			this.tail = newNode;
		}
		// Increments the size of doubly linked list
		this.size++;
	}
	
	/**
	 * This method deletes the first element from this list that matches the provided key but if
	 * the provided key does not exist in the list, it returns null.
	 * 
	 * @param key is the provided key used to delete 
	 * @return the deleted element or null if the key does not exist in the list
	 */
	public T delete(T key) {
		Node<T> curr = head;
		// Iterates through the doubly linked list to find 
		while (curr != null) {
			if (curr.getData().equals(key)) {
				// Updates the head if the first element is being deleted
				if (curr.getPrev() == null) {
					this.head = curr.getNext();
				}
				else {
					// Sets the previous node's next pointer accordingly
					curr.getPrev().setNext(curr.getNext());
				}
				// Updates the tail if the last element is being deleted
				if (curr.getNext() == null) {
					this.tail = curr.getPrev();
				}
				else {
					// Sets the next node's pointer accordingly
					curr.getNext().setPrev(curr.getPrev());
				}
				// Decrements the size of the doubly linked list
				this.size--;
				// Returns the data of the deleted element 
				return curr.getData();
			}
			// Moves the curr pointer to the next node
			curr = curr.getNext();
		}
		// Returns null if the key doesn't exist in the list
		return null;
	}
	
	/**
	 * This method returns the first element in the list that matches the provided key or null if
	 * one cannot be found.
	 * 
	 * @param key is the provided key used to get the element
	 * @return the first element in the list that matches the provided key
	 */
	public T get(T key) {
		Node<T> curr = head;
		// Iterates through the doubly linked list to find the element that matches they key
		while (curr != null) {
			if (curr.data.equals(key)) {
				return curr.getData();
			}
			// Moves the curr pointer to the next node
			curr = curr.getNext();
		}
		// Returns null if key not found
		return null;
	}
	
	/**
	 * This method returns the number of elements in the list.
	 * 
	 * @return the number of elements in the list
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * This method returns a string representation of this list's elements.
	 */
	@Override
	public String toString() {
		if (this.size == 0) {
			return "[]";
		}
		else {
			Node<T> curr = head;
			String elements = "[";
			// While loop which adds each element in the doubly linked list to the string
			while (curr != null) {
				elements += curr.getData();
				// Sets the curr pointer to the next element
				curr = curr.getNext();
				// Adds a comma and a space if it isn't the last element
				if (curr != null) {
					elements += (", ");
				}
			}
			elements += "]";
			return elements;
		}
	}
}
