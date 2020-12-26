package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface used by the ArrayQueue and LinkedQueue classes
 * 
 * @author Bilal Mohamad, Qihao Lu, Tj Tutka
 *
 * @param <E>	The object type being made into a queue list
 */
public interface Queue<E> {

	/**
	 * Used to add an element to the back of the queue
	 * 
	 * @param element	the element to be added to the back of the queue
	 * @throws IllegalArgumentException if there no room in the queue (the capacity has been reached)
	 */
	void enqueue(E element);
	
	
	/**
	 * Removes and returns the element at the front of the Queue
	 * 
	 * @return the element at the front of the Queue
	 */
	E dequeue();
	
	
	/**
	 * Checks if the queue is empty
	 * 
	 * @return 	true if the queue is empty
	 * 			false otherwise
	 */
	boolean isEmpty();
	
	
	/**
	 * Returns the number of elements in the Queue
	 * 
	 * @return the number of elements in the Queue
	 */
	int size();
	
	
	/**
	 * Sets the capacity of the queue
	 * 
	 * @param capacity	the value to set the capacity to
	 * @throws IllegalArgumentException if the parameter is negative or less than size 
	 */
	void setCapacity(int capacity);
	
	
}
