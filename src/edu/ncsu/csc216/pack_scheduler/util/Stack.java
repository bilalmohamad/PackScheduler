package edu.ncsu.csc216.pack_scheduler.util;
/**
 * Stack interface that has 5 methods
 * @author Qihao Lu
 *
 * @param <E> uncertain type
 */
public interface Stack<E> {
	/**
	 * adds the element to top of the stack
	 * @param element uncertain element
	 * @throws IllegalArgumentException is no room
	 */
	void push(E element);
	
	/**
	 * Removes and returns the element at the top of stack
	 * @return top element
	 */
	E pop();
	
	/**
	 * If the stack is empty
	 * @return true if empty
	 */
	boolean isEmpty();
	
	/**
	 * return size of stack
	 * @return the size
	 */
	int size();
	
	/**
	 * Set the capacity of stack
	 * @param capacity given capacity
	 * @throws IllegalArgumentException if parameter if less than 0 or less than the element in stack
	 */
	void setCapacity(int capacity);
}
