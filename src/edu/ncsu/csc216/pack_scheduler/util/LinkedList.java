package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * LinkedLIst will inherit from AbstractSequentialList 
 * @author Bilal Mohamad, Qihao Lu, Tj Tutka
 *
 * @param <E> The object type parameter
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

	/** size of LinkedList*/
	private int size;
	/** front of the LinkedList*/
	private ListNode front;
	/** back of the LinkedList*/
	private ListNode back;
	
	/**
	 * constructor of LinkedList
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null, front, null);
		front.next = back;
		size = 0;
	}
	
	/**
	 * The method returns a ListIterator that is positioned such that 
	 * a call to ListIterator.next() will return the element at given index
	 * @param index given index that Iterator will be positioned
	 * @return the Iterator
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		LinkedListIterator l = new LinkedListIterator(index);
		return l;
	}
	
	/* (non-Javadoc)
	 * @see java.util.AbstractSequentialList#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();

		}
		for (int i = 0; i < size; i++) {
			if (get(i).equals(element)) {
				throw new IllegalArgumentException();
			}
		}

		LinkedListIterator i = new LinkedListIterator(index);
		i.add(element);
	}

	
	/* (non-Javadoc)
	 * @see java.util.AbstractSequentialList#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		for (int i = 0; i < size; i++) {
			if (get(i).equals(element)) {
				throw new IllegalArgumentException();
			}
		}

		LinkedListIterator i = new LinkedListIterator(index);
		
		E set = i.next(); 
		i.set(element);
		return set;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * ListNode is an inner class of LinkedList.
	 * @author Bilal Mohamad, Qihao Lu, Tj Tutka
	 *
	 */
	private class ListNode {
		/** the data in the node*/
		private E data;
		/** the next node in the list*/
		private ListNode next;
		/** the previous node in the list*/
		private ListNode prev;
		
		/**
		 * Constructor for ListNode
		 * @param data that store in the current node
		 */
		public ListNode(E data) {
			this.data = data;
			
		}
		
		/**
		 * constructor for ListNode
		 * @param data store in current ListNode
		 * @param prev previous node of current node
		 * @param next next node of current node
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		
	}
	
	/**
	 * LinkedListIterator class is an inner class of LinkedList
	 * @author Bilal Mohamad, Qihao Lu, Tj Tutka
	 *
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/**next ListNode of current*/
		public ListNode next;
		/**previous ListNode of current*/
		public ListNode prev;
		/** represents the Listnode that was returned on the 
		 * last call to previous or next, otherwise null;
		 */
		public ListNode lastRetrieved;
		/** index of prev*/
		public int previousIndex;
		/** index of next*/
		public int nextIndex;
		
		/**
		 * constructor for LinkedListIterator
		 * @param index given index that Iterator will be positioned
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size()) {
				throw new IndexOutOfBoundsException();
			}
			prev = front;
			next = prev.next;
			previousIndex = index - 1;
			nextIndex = index;
 
			if (index != 0) {
				for (int i = 0; i < index; i++) {
					prev = next;
					next = next.next;

				}  
			}
			 
			lastRetrieved = null;
			
		}
		
		@Override
		public boolean hasNext() {
			if(next.data == null) {
				return false;
			}
			return true;
		}

		@Override
		public boolean hasPrevious() {
			if(prev.data == null) {
				return false;
			}
			return true;
		}

		@Override
		public E next() {
			if(next.data == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = next;
			E current = next.data;
			previousIndex++;
			nextIndex++;
			next = next.next;
			return current;
		}

		@Override
		public int nextIndex() {
			if(next == null) {
				return size;
			}
			return nextIndex - 1 ;
		}

		@Override
		public E previous() {
			if(prev.data == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = prev;
			previousIndex--;
			nextIndex--;
			prev = prev.prev;
			return prev.data;
		}

		@Override
		public int previousIndex() {
			if (prev == null) {
                return -1;
            }
			return previousIndex;
		}

		@Override
		public void remove() {
			if(lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.prev.next = next;
			if(hasNext()) {
				next = next.next;
			}
			size--;
		}

		@Override
		public void set(E arg) {
			if(lastRetrieved == null) {
				throw new IllegalStateException();
			}
			if(arg == null) {
				throw new NullPointerException();
			}
			lastRetrieved.data = arg;
		}
		
		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			ListNode newNode = new ListNode(e);
			next.prev = newNode;
			newNode.next = next;
			newNode.prev = prev;

			prev.next = newNode;
 
			size++;
			lastRetrieved = null;
		}
	}
}
