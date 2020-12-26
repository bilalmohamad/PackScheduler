package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Making the ArrayStack and the operations for it
 * @author Qihao Lu, Bilal Mohamad, TJ Tutka
 * @param <E>	Object type of the Stack
 *
 */
public class ArrayStack<E> implements Stack<E> {
	/** A ArrayList that construct as stack */
	private ArrayList<E> list = new ArrayList<E>();
	/**The capacity of arraystack*/
	private int capacity;

	/**
	 * the constructor for constructing a new list and
	 * setting the capacity
	 * @param cap the capacity for a class
	 */
	public ArrayStack (int cap) {
		list = new ArrayList<E>();
		this.setCapacity(cap);
	}
	
	/**
	 * pushes the element to the stack
	 * and decreases the capacity for that class and adds it to the
	 * beginning of the stack
	 * @param element reads in the element of an object
	 */
	@Override
	public void push(E element) {
		if(this.capacity == 0) {
			throw new IllegalArgumentException("The capacity is reached!");
		}
		if (list.size() >= this.capacity) {
			throw new IllegalArgumentException();
		}
		this.capacity--;
		list.add(0, element);
		
	}

	/**
	 * pops the element off the stack
	 * and increments the capacity
	 */
	@Override
	public E pop() {
		if(list.isEmpty()) {
			throw new EmptyStackException();
		}
		this.capacity++;
		return list.remove(0);
	}

	/**
	 * makes the list empty
	 * @return the empty list
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * the size of the list
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * sets the capacity of the list and checks for
	 * errors
	 * @param capacity reads in the number of the capacity
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity can not be less than 0!");
		}
		if (capacity < list.size()) {
			throw new IllegalArgumentException("The capacity can not be less than the current lists size!");
		}
		this.capacity = capacity;
		
	}

}
