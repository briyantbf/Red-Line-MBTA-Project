/**
* This class provides the implementation of a doubly linked list node. The nodes have a pointer to
* the next node, a pointer to the previous node, and data.
* Known Bugs: None
*
* @author Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* October, 2024
* COSI 21A PA1
*/

package main;

public class Node<T> {
	// Initialize data field and pointers
	public T data;
	public Node<T> next;
	public Node<T> prev;

	/**
	 * This method constructs a doubly linked list node that holds a data field but does not point
	 * to any other nodes.
	 * 
	 * @param data is the data field
	 */
	public Node(T data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}
	
	/**
	 * This method sets the data field of this node.
	 * 
	 * @param data is the data field
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * This method sets the next pointer of this node.
	 * 
	 * @param next is the node's next field
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	/**
	 * This method sets the prev pointer of this node.
	 * 
	 * @param prev is the node's prev field
	 */
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
	
	/**
	 * This method returns the pointer to the next node or null if one does not exist.
	 * 
	 * @return the pointer to the next node
	 */
	public Node<T> getNext() {
		return this.next;
	}
	
	/**
	 * This method returns the pointer to the previous node or null if one does not exist.
	 * 
	 * @return the pointer to the previous node
	 */
	public Node<T> getPrev() {
		return this.prev;
	}
	
	/**
	 * This method returns the data stored in this node.
	 * 
	 * @return the data stored in this node
	 */
	public T getData() {
		return this.data;
	}
	
	/**
	 * This returns the string representation of this node's element.
	 */
	@Override
	public String toString() {
		String element = "";
		element += data;
		return element;
	}
}
