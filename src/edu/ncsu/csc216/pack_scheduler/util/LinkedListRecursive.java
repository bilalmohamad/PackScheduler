package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Creates a Linked list that is also recursive when adding, removing, setting, getting and
 * getting the size of lists and objects
 * @author TJ, Bilal, Qihao
 * @param <E> the object that is being passed in
 */
public class LinkedListRecursive<E> {

	/** the size of the list */
	private int size;
	
	/** the first node in the list */
	private ListNode front;
	
	/**
	 * creates the front and size variables and assigns them values
	 */
	public LinkedListRecursive()
	{
		this.front = null;
		this.size = 0;
	}
	
	/**
	 * checks to see if the list is empty
	 * @return true if the list is empty, false if the list is not empty
	 */
	public boolean isEmpty()
	{
		if(size == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the size of the list
	 * @return the size of the list
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * adds an element to the list and rearranges the list and increments the size
	 * @param element object that is getting added to the list
	 * @return true if the element can be added to the list
	 */
	public boolean add(E element){
		if(element == null) {
			return false;
		}
		if(contains(element)) {
			throw new IllegalArgumentException();
		}
		add(size(), element);
		return true;
	}
	
	/**
	 * adds the element to the list
	 * @param idx the desired index that the element is being added at
	 * @param element the object being added to the list
	 */
	public void add(int idx, E element)
	{
		if(element == null) {
			throw new NullPointerException();
		}
		
		if(contains(element)) {
			throw new IllegalArgumentException();
		}
		
		if(idx > size() || idx < 0 ) {
			throw new IndexOutOfBoundsException();
		}
		
		if(idx == 0) {
			front = new ListNode(element, front);
			size++;
		} else {
			front.add(idx - 1, element);
		}
	}
	
	/**
	 * gets the object from the list
	 * @param idx the index the object is getting retrieved at
	 * @return the element at the index
	 */
	public E get(int idx)
	{
		if(idx >= size() || idx < 0 ) {
			throw new IndexOutOfBoundsException();
		}
		if(idx == 0) {
			E i = front.data;
			return i;
		}
		E i = front.get(idx);
		return i;
	}
	
	/**
	 * removes the element from the list
	 * @param element the element we are looking for
	 * @return true if the element can be removed and false if it cannot be removed
	 */
	public boolean remove(E element)
	{
		if(element == null || isEmpty()) {
			return false;
		}
		
		if(front.data == element) {
			front = front.next;
			size--;
			return true;
		} else {
			return front.remove(element);
		}
	}
	
	/**
	 * removes the object from the list
	 * @param idx the index where the object is being removed at
	 * @return the removed object
	 */
	public E remove(int idx)
	{
		if(idx >= size() || idx < 0 ) {
			throw new IndexOutOfBoundsException();
		}
		if(idx == 0) {
			E r = front.data;
			front = front.next;
			size--;
			return r;
		} else {
			return front.remove(idx - 1);
		}
		
	}
	
	/**
	 * sets the element at the desired index in the list
	 * @param idx the index where the object will be inserted at
	 * @param element the object being added to the list
	 * @return the new element at that location
	 */
	public E set(int idx, E element)
	{
		if(idx >= size() || idx < 0 ) {
			throw new IndexOutOfBoundsException();
		}
		if(element == null) {
			throw new NullPointerException();
		}
		if(contains(element)) {
			throw new IllegalArgumentException();
		}
		if(idx == 0) {
			E r = front.data;
			front.data = element;
			return r;
		}
		return front.set(idx - 1, element);
		
	}
	
	/**
	 * checks to see if the object is already in the list
	 * @param element the element being checked
	 * @return true if the object is in the list and false if the object is not in the list
	 */
	public boolean contains(E element)
	{
		if(element == null) {
			return false;
		}
		if(front == null) {
			return false;
		}
		if(front.data == element) {
			return true;
		}
		if(!isEmpty()) {
			return front.contains(element);
		}
		
		return false;
	}
	

	/**
	 * Is one node in the list, it has the data of that node and the next node
	 * @author Qihao, Bilal, TJ 
	 *
	 */
	private class ListNode {
		/** the data of the node */
		public E data;
		
		/** the next node in the list */
		private ListNode next;
		
		/**
		 * adds the element to the list if the size is greater than 1.
		 * @param idx the index where the object is being added at
		 * @param element the element that is being added to the list
		 */
		private void add(int idx, E element)
		{
			int i = 0;
			if(i == idx) {
				next = new ListNode(element, next);
				size++;
			} else {
				idx--;
				next.add(idx, element);
			}
			
		}
		/**
		 * gets the desired object 
		 * @param idx the index where the element is wanted
		 * @return the object at that index
		 */
		private E get(int idx) {
			if(idx == 1) {
				return next.data;
			}
			else {
				idx--;
				return next.get(idx);
			}
		}
		
		/**
		 * removes the object at the given index
		 * @param idx the index where the object is located
		 * @return the element that is removed
		 */
		private E remove(int idx) {
			if (next != null) {
				if (idx == 0) {
					E i = next.data;
					next = next.next;
					size--;
					return i;
				} else {
					idx--;
					next.remove(idx);
				}
			}
			return null;
			
		}
		
		/**
		 * checks to see if the element can be removed
		 * @param element the element that is being removed
		 * @return true if the element can be removed and false if the element cannot be removed
		 */
		private boolean remove(E element) {
			if(next != null) {
				if(next.data.equals(element)) {
					next = next.next;
					size--;
					return true;
				} else {
					next.remove(element);
				}
			}
			return false;
		}
		
		/**
		 * sets the element at the given index
		 * @param idx the index that the object is being inserted
		 * @param element the element that is being inserted
		 * @return the element at that location
		 */
		private E set(int idx, E element) {
			if(0 == idx) {
				E o = next.data;
				next.data = element;
				return o;
			} else {
				idx--;
				return next.set(idx, element);
			}
		}
		
		/**
		 * checks to see if the element is in the list
		 * @param element the element that is being checked
		 * @return true if the element is in the list and false if the element is not in the list
		 */
		private boolean contains(E element) {
			if(next == null) {
				return false;
			}
			else if(next.data == element) {
				return true;
			}
			return next.contains(element);
			
		}
		
		/**
		 * constructs the objects data and next
		 * @param data the data in the element
		 * @param next the next node in the list
		 */
		private ListNode(E data, ListNode next)
		{
			this.data = data;
			this.next = next;
			
		}
	}
}
