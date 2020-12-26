package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test Sortedlist class
 * 
 * @author Tianxin Jia
 *
 */
public class SortedListTest {

	/**
	 * test create a sorted list and adding elements until over default capacity.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));

		// TODO Test that the list grows by adding at least 11 elements
		// Remember the list's initial capacity is 10
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		list.add("h");
		list.add("i");
		list.add("j");
		list.add("k");
		assertEquals(11, list.size());

	}

	/**
	 * test add method
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();

		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));

		// TODO Test adding to the front, middle and back of the list
		list.add("apple");
		assertEquals("apple", list.get(0));
		list.add("aztec");
		assertEquals("aztec", list.get(1));
		list.add("zebra");
		assertEquals("zebra", list.get(3));
		// TODO Test adding a null element
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
		// TODO Test adding a duplicate element
		try {
			list.add("apple");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
		}
	}

	/**
	 * test get method
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();

		// Since get() is used throughout the tests to check the
		// contents of the list, we don't need to test main flow functionality
		// here. Instead this test method should focus on the error
		// and boundary cases.

		// TODO Test getting an element from an empty list
		try {
			list.get(1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		// TODO Add some elements to the list
		list.add("apple");
		assertEquals(1, list.size());
		// TODO Test getting an element at an index < 0
		try {
			list.get(-5145323);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
		// TODO Test getting an element at size
		assertEquals("apple", list.get(0));
	}

	/**
	 * test remove method
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		// TODO Test removing from an empty list
		try {
			list.remove(1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		// TODO Add some elements to the list - at least 4
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		// TODO Test removing an element at an index < 0
		try {
			list.remove(-5145323);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		// TODO Test removing an element at size
		list.remove(0);
		assertEquals(3, list.size());
		assertFalse(list.contains("a"));
		// TODO Test removing a middle element
		list.remove(1);
		assertEquals(2, list.size());
		assertFalse(list.contains("c"));
		// TODO Test removing the last element

		list.remove(1);
		assertEquals(1, list.size());
		assertFalse(list.contains("d"));
		// TODO Test removing the first element

		list.add("a");
		list.remove(0);
		assertEquals(1, list.size());
		assertFalse(list.contains("a"));

		// TODO Test removing the last element
		list.remove(0);
		assertEquals(0, list.size());
		assertFalse(list.contains("b"));

	}

	/**
	 * test indexOf method
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();

		// TODO Test indexOf on an empty list
		assertEquals(-1, list.indexOf("a"));
		// TODO Add some elements
		list.add("apple");
		list.add("banana");
		list.add("cherry");
		// TODO Test various calls to indexOf for elements in the list
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("banana"));
		assertEquals(2, list.indexOf("cherry"));
		// and not in the list
		assertEquals(-1, list.indexOf("a"));
		// TODO Test checking the index of null
		int a = 0;
		try {
			a = list.indexOf(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(0, a);
		}
	}

	/**
	 * test clear method
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// TODO Add some elements
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		// TODO Clear the list
		assertEquals(4, list.size());
		list.clear();
		// TODO Test that the list is empty
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}

	/**
	 * test isEmpty method
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();

		// TODO Test that the list starts empty
		assertTrue(list.isEmpty());
		// TODO Add at least one element
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		// TODO Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * test contains method
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		// TODO Test the empty list case
		assertFalse(list.contains("apple"));
		// TODO Add some elements
		list.add("apple");
		list.add("banana");
		list.add("cherry");
		// TODO Test some true and false cases
		assertFalse(list.contains("b"));
		assertTrue(list.contains("apple"));
		assertFalse(list.contains("a"));
		assertTrue(list.contains("banana"));
	}

	/**
	 * test equals method
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// TODO Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list1.add("cherry");
		list2.add("apple");
		list2.add("banana");
		list2.add("cherry");
		list3.add("a");
		list3.add("b");
		list3.add("c");
		// TODO Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		assertFalse(list3.equals(list1));
		assertFalse(list3.equals(list2));
	}

	/**
	 * test hashCode method
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// TODO Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list1.add("cherry");
		list2.add("apple");
		list2.add("banana");
		list2.add("cherry");
		list3.add("a");
		list3.add("b");
		list3.add("c");
		// TODO Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertFalse(list2.hashCode() == (list3.hashCode()));
		assertFalse(list1.hashCode() == (list3.hashCode()));
	}

}
