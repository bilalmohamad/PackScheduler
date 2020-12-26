/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;
import java.util.Arrays;

/**
 * A custom ArrayList of elements
 * @author Kyle Kleinke
 * @param <E> A generic object that is the placeholder for most other objects
 */
public class ArrayList<E> extends AbstractList<E> {
	
	/** a constant value for initializing the list size. */
	private static final int INIT_SIZE = 10;
	
	/** an array of type E */
	private E[] list;
	
	/** the size of the list */
	private int size;
	
	/**
	 * Constructs a new ArrayList of capacity 10 and size 0
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		this.list = (E[]) new Object[INIT_SIZE];
		this.size = 0;
	}
	
	/**
	 * Adds an object to the specified index
	 * @param index the index in which to add the object
	 * @param object The object to be added at the specific index
	 */
	@Override
	public void add(int index, E object) {
		growArray();
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < this.size; i++) {
			if (object.equals(this.list[i])) {
				throw new IllegalArgumentException();
			}
		}
		if (object == null) {
			throw new NullPointerException();
		}
		E next;
		for (int i = this.size; i > index; i--) {
			next = this.list[i - 1];
			this.list[i] = next;
		}
		this.list[index] = object;
		size++;
	}
	
	/**
	 * Grows the array if the capacity has been reached
	 */
	private void growArray() {
		if (this.size == this.list.length - 1) {
			int newCapacity = this.list.length * 2 + 1;
			if (this.size > newCapacity) {
				newCapacity = this.size();
			}
			this.list = Arrays.copyOf(this.list, newCapacity);
		}
	}
	
	/**
	 * Removes the element at the specified index
	 * @param index the index of the element to be removed
	 * @return the element that was removed
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		E obj = this.list[index];
		E next;
		for (int i = index; i < this.size; i++) {
			next = this.list[i + 1];
			this.list[i] = next;
		}
		this.list[size - 1] = null;
		size--;
		return obj;
	}
	
	/**
	 * Sets an element at the specified index, replacing the element at the specified index
	 * @param idx The index to replace
	 * @param element The object that will be replacing
	 * @return The object that was replaced
	 */
	@Override
	public E set(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		}

		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}

		for (int i = 0; i < size(); i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}

		E returnE = list[idx];
		list[idx] = element;

		return returnE;
	}
	
	/**
	 * Returns the object at the specified index
	 * @param index The index of the object to be returned
	 * @return The object at the specified index
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		} else if (this.list[index] == null) {
			throw new IndexOutOfBoundsException();
		}
		return this.list[index];
	}
	
	/**
	 * Returns the lists size field
	 * @return the size of the array
	 */
	@Override
	public int size() {
		return this.size;
	}

}
