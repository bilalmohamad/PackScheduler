/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Creates a LinkedAbstractList of the generic object E
 * @author Kyle Kleinke
 * @param <E> THe genereic type of object that this class implements
 *
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	
	/** The size of the LinkedAbstractList */
	private int size;
	
	/** The capacity of the LinkedAbstractList */
	private int capacity;
	
	/** The current ListNode at the front of the list */
	private ListNode front;
	
	/** The current ListNode at the back of the list */
	private ListNode back;
	
	/**
	 * Constructor for LinkedAbstractList. Creates a new one
	 * @param capacity The capacity of the LinkedAbstractList
	 */
	public LinkedAbstractList(int capacity) {
		setCapacity(capacity);
		this.front = null;
		this.back = front;
		back = front;
		this.size = 0;
	}
	
	/**
	 * Sets the current lists capacity to the given int. If the capcity is less than 0, or
	 * if the capacity is less than the current lists size, then an IllegalArgumentException
	 * will be thrown.
	 * @param capacity The capacity to set the list to
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity can not be less than 0!");
		}
		if (capacity < this.size) {
			throw new IllegalArgumentException("The capacity can not be less than the current lists size!");
		}
		this.capacity = capacity;
	}
	
	/**
	 * Returns the element at the specified index
	 * @param index The index of the element to return
	 */
	@Override
	public E get(int index) {
		ListNode answer = null;
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		// Returns front
		if (index == 0) {
			return front.data;
		} else {
			ListNode current = front;
			for (int i = 0; i <= index; i++) {
				if (i == index) {
					answer = current; 
				}
				current = current.next;
			}
		}
		return answer.data;
	}
	
	/**
	 * Returns the size of the current LinkedAbstractList (How many elements there are)
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * Adds an object to the specified index
	 * @param index the index in which to add the object
	 * @param object The object to be added at the specific index
	 */
	@Override
	public void add(int index, E object) {
		if (object == null) {
			throw new NullPointerException();
		}
		
		if (this.size == this.capacity) {
			throw new IllegalArgumentException();
		}
		
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (index > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		if (front == null) {
			front = new ListNode(object);
			back = front;
		}
		ListNode now = front;
		for (int i = 0; i < size; i++) {
			if (object.equals(now.data)) {
				throw new IllegalArgumentException(object + " is already in the list!");
			}
			now = now.next;
		}
		
		if (index == 0) {
			this.front = new ListNode(object, this.front);
		} else {
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = new ListNode(object, current.next);
		}
		back = front;
		while (back.next != null) {
			back = back.next;
		}
		size++;
	}
	
	/**
	 * Removes the element at the specified index, returning the value that was removed
	 * @param index The index of the element to remove
	 * @return the number at that position
	 */
	public E remove(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode cur;
		if (index == 0) {
			ListNode current = front;
			front = front.next;
			size--;
			return current.data;
		} else {
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			cur = current.next;
			current.next = current.next.next;
		}
		size--;
		return cur.data;
	}
	
	/**
	 * Replaces the element at the specified index with the given object
	 * @param idx The index to replace something
	 * @param obj The new object to set at the index
	 * @return The object that was replaced
	 */
	public E set(int idx, E obj) {
		if(front == null) {
			throw new IndexOutOfBoundsException();
		}
		if (obj == null) {
			throw new NullPointerException();
		}
		if (this.contains(obj)) {
			throw new IllegalArgumentException();
		}
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode dupeScan = front;
		ListNode storage = null;
		E retObj = null; 
		for(int i = 0; dupeScan != null; i++) {
			if(i == idx) {
				storage = dupeScan;
				retObj = dupeScan.data;
			}
			if(dupeScan.data.equals(obj)) {
				throw new IllegalArgumentException();
			}
			dupeScan = dupeScan.next;
		}
		
		storage.data = obj;
		return retObj;
	}
	
	/**
	 * Creates the node system for the LinkedList
	 * @author Kyle Kleinke
	 *
	 */
	private class ListNode {
		
		/** The data inside the node */
		private E data;
		
		/** The next node in the list */
		private ListNode next;
		
		/**
		 * Constructs a new ListNode with the given data
		 * @param data the data to set for the current node
		 */
		@SuppressWarnings("unused")
		public ListNode(E data) {
			this.data = data;
		}
		
		/**
		 * Constructs a new ListNode with the given data, then sets the next ListNode that is referenced
		 * @param data the data to be set
		 * @param next The next listnode that the current listnode will point to
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
