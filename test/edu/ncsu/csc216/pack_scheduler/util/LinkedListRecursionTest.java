package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * test for LinkedListRecursion
 * @author Qihao Lu
 *
 */
public class LinkedListRecursionTest {

	/**
	 * testing the constructor, isEmpty, and size methods
	 */
	@Test
	public void testLinkedListRecursive() {
		LinkedListRecursive<Integer> l = new LinkedListRecursive<Integer>();
		assertTrue(l.isEmpty());
		l.add(1);
		assertEquals(l.size(), 1);
		assertFalse(l.isEmpty());
	}
		
	/**
	 * testing the add boolean method
	 */
	@Test
	public void testAddElement() {
		LinkedListRecursive<Integer> l = new LinkedListRecursive<Integer>();
		assertFalse(l.add(null));
		l.add(1);
		try {
			l.add(1);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	/**
	 * testing the add method
	 */
	@Test
	public void testAddIdxElement() {
		LinkedListRecursive<Integer> l = new LinkedListRecursive<Integer>();
		l.add(0, 1);
		l.add(1, 2);
		assertEquals(l.size(), 2);
		try {
			l.add(1, null);
			fail();
		} catch (NullPointerException e) {
			assertTrue(e instanceof NullPointerException);
		}
		try {
			l.add(1, 1);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			l.add(3, 3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		try {
			l.add(-3, 4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
	}
	
	/**
	 * testing the add method
	 */
	@Test
	public void testGet() {
		LinkedListRecursive<Integer> l = new LinkedListRecursive<Integer>();
		l.add(1);
		l.add(1, 2);
		assertEquals(l.get(0).intValue(), 1);
		assertEquals(l.get(1).intValue(), 2);
		try {
			l.get(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		try {
			l.get(-3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}
	
	/**
	 * testing the add method
	 */
	@Test
	public void testRemoveElement() {
		LinkedListRecursive<String> l = new LinkedListRecursive<String>();
		assertFalse(l.remove(null));
		assertFalse(l.remove("a"));
		assertFalse(l.contains("z"));
		l.add(0, "a");
		l.add(1, "b");
		l.add(2, "c");
		l.remove("a");
		l.remove("c");
		assertFalse(l.contains(null));
		assertTrue(l.contains("b"));
		assertFalse(l.contains("d"));
		
	}
	
	/**
	 * testing the add method
	 */
	@Test
	public void testRemoveIndex() {
		LinkedListRecursive<String> l = new LinkedListRecursive<String>();
		try {
			l.remove(-1);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		try {
			l.remove(100);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		l.add(0, "a");
		l.add(1, "b");
		l.add(2, "c");
		l.add(3, "d");
		l.remove(3);
		l.remove(0);
		l.remove(1);
	}
	
	/**
	 * testing the add method
	 */
	@Test
	public void testSet() {
		LinkedListRecursive<Integer> l = new LinkedListRecursive<Integer>();
		try {
			l.set(-1, 10);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		try {
			l.set(100, 100);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		l.add(0, 1);
		l.add(1, 2);
		l.set(0, 0);
		l.set(1, 1);
		try {
			l.set(0, null);
			fail();
		} catch(NullPointerException e) {
			assertTrue(e instanceof NullPointerException);
		}
		try {
			l.set(1, 0);
			fail();
		} catch(IllegalArgumentException e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
}
