package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;
/**
 * Creates the Linked stack between the classes and makes the operators
 * @author TJ
 *
 * @param <E> the object read in
 */

public class LinkedStack<E> implements Stack<E> {

	/** creating a varible of the LinkedAbstractList */
	private LinkedAbstractList<E> stack;
	
	/**
	 * constructs the stack variable
	 * @param capacity capacity of the course
	 */
	public LinkedStack(int capacity) {
		stack = new LinkedAbstractList<E>(capacity);
	} 
		
	/**
	 * pushes the element to the stack and to the front
	 */
	@Override
	public void push(E element) {
		stack.add(0, element);
	}

	/**
	 * pops the element off the stack and removes it
	 * at the first position
	 */
	@Override
	public E pop() {
		if(stack.isEmpty()) {
			throw new EmptyStackException();
		}
		E value = stack.remove(0);
		return value;
	}

	/**
	 * checks to see if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	/**
	 * returns the size to of the list
	 */
	@Override
	public int size() {
		return stack.size();
	}

	/**
	 * sets the capacity of the course 
	 */
	@Override
	public void setCapacity(int capacity) {
		if(stack.size() < capacity || stack.size() > capacity) {
			throw new IllegalArgumentException();
		}
		stack.setCapacity(capacity);
	}

}
