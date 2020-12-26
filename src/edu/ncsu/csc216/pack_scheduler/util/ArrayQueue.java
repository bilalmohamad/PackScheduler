package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * This class will implement the Queue interface to create a queue using an ArrayList
 * 
 * @author Bilal Mohamad, Qihao Lu, Tj Tutka
 *
 * @param <E>	The object type of the ArrayQueue
 */
public class ArrayQueue<E> implements Queue<E> {

	/** The ArrayList used to create an ArrayQueue */
	private ArrayList<E> list;
	
	/** The maximum number of elements that an ArrayQueue may have */
	private int capacity;
	
	
	/**
	 * The constructor used to make ArrayQueue objects
	 * 
	 * @param capacity	the capacity of the ArrayQueue
	 */
	public ArrayQueue (int capacity){
		list = new ArrayList<E>();
		setCapacity(capacity);
		
	}

	
	/**
	 * Used to add an element to the back of the queue
	 * 
	 * @param element	the element to be added to the back of the queue
	 * @throws IllegalArgumentException if there no room in the queue (the capacity has been reached)
	 */
	@Override
	public void enqueue(E element) {
		if (list.size() < capacity) {
			list.add(list.size(), element);
		}
		else {
			throw new IllegalArgumentException(); 
		}
		
	}

	
	/**
	 * Removes and returns the element at the front of the Queue
	 * 
	 * @return the element at the front of the Queue
	 * @throws NoSuchElementException if the Queue is empty
	 */
	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return list.remove(0);
	}

	
	/**
	 * Checks if the queue is empty
	 * 
	 * @return 	true if the queue is empty
	 * 			false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	
	/**
	 * Returns the number of elements in the Queue
	 * 
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size() {
		return list.size();
	}

	
	/**
	 * Sets the capacity of the queue
	 * 
	 * @param capacity	the value to set the capacity to
	 * @throws IllegalArgumentException if the parameter is negative or less than size 
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < list.size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

}
