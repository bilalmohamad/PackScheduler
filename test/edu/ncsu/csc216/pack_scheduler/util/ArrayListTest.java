package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests ArrayList
 * @author Kyle Kleinke
 *
 */
public class ArrayListTest {
	
	/**
	 * Tests the constructor for ArrayList
	 */
	@Test
	public void testArrayList() {
		ArrayList<String> al = new ArrayList<String>();
		assertEquals(0, al.size());
	}
	
	/**
	 * Tests the add method for ArrayList
	 */
	@Test
	public void testAdd() {
		ArrayList<String> al = new ArrayList<String>();
		al.add(0, "hi");
		assertEquals("hi", al.get(0));
		al.add(1, "yes");
		assertEquals("yes", al.get(1));
		al.add(1, "no");
		assertEquals("hi", al.get(0));
		assertEquals("no", al.get(1));
		assertEquals("yes", al.get(2));
		// Add greater than size
		try {
			al.add(5, "Error");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("hi", al.get(0));
			assertEquals("no", al.get(1));
			assertEquals("yes", al.get(2));
		}
		// Add less than 0
		try {
			al.add(-1, "Error");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("hi", al.get(0));
			assertEquals("no", al.get(1));
			assertEquals("yes", al.get(2));
		}
		// Add null element
		try {
			al.add(3, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals("hi", al.get(0));
			assertEquals("no", al.get(1));
			assertEquals("yes", al.get(2));
		}
		// Add duplicate object
		try {
			al.add(3, "hi");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("hi", al.get(0));
			assertEquals("no", al.get(1));
			assertEquals("yes", al.get(2));
		}
		// Fill the list and try growing the list size
		ArrayList<String> al2 = new ArrayList<String>();
		al2.add(0, "1");
		al2.add(1, "2");
		al2.add(2, "3");
		al2.add(3, "4");
		al2.add(4, "5");
		al2.add(5, "6");
		al2.add(6, "7");
		al2.add(7, "8");
		al2.add(8, "9");
		assertEquals(9, al2.size());
		al2.add(9, "10");
		assertEquals(10, al2.size());
		al2.add(10, "11");
		assertEquals(11, al2.size());
		al2.add(11, "12");
		assertEquals(12, al2.size());
	}

	/**
	 * Tests the remove method for ArrayList
	 */
	@Test
	public void testRemove() {
		ArrayList<String> al = new ArrayList<String>();
		al.add(0, "hi");
		assertEquals("hi", al.get(0));
		al.add(1, "yes");
		assertEquals("yes", al.get(1));
		al.add(1, "no");
		// Remove from front
		al.remove(0);
		assertEquals("no", al.get(0));
		assertEquals("yes", al.get(1));
		al.add(0, "hi");
		assertEquals("hi", al.get(0));
		assertEquals("no", al.get(1));
		assertEquals("yes", al.get(2));
		// Remove from middle
		al.remove(1);
		assertEquals("hi", al.get(0));
		assertEquals("yes", al.get(1));
		al.add(1, "no");
		assertEquals("hi", al.get(0));
		assertEquals("no", al.get(1));
		assertEquals("yes", al.get(2));
		// Remove from end
		al.remove(2);
		assertEquals("hi", al.get(0));
		assertEquals("no", al.get(1));
		al.add(2, "yes");
		// Remove negative index
		try {
			al.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("hi", al.get(0));
			assertEquals("no", al.get(1));
			assertEquals("yes", al.get(2));
		}
		// Remove index equal to the size
		try {
			al.remove(6);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("hi", al.get(0));
			assertEquals("no", al.get(1));
			assertEquals("yes", al.get(2));
		}
		// Remove index greater than the size
		try {
			al.remove(7);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("hi", al.get(0));
			assertEquals("no", al.get(1));
			assertEquals("yes", al.get(2));
		}
	}
	
	/**
	 * Tests the set method for ArrayList
	 */
	@Test
	public void testSet() {
		ArrayList<String> al = new ArrayList<String>();
		al.add(0, "hi");
		assertEquals("hi", al.get(0));
		al.add(1, "yes");
		assertEquals("yes", al.get(1));
		al.add(1, "no");
		// Set in front
		al.set(0, "WHY");
		assertEquals("WHY", al.get(0));
		// Set in middle
		al.set(1, "HELLO");
		assertEquals("HELLO", al.get(1));
		// Set at end
		al.set(2, "THERE");
		assertEquals("THERE", al.get(2));
		// Make a new array list to test if the set method returns the right thing
//		ArrayList<String> al2 = new ArrayList<String>();
		al.add(0, "hi");
		assertEquals("hi", al.get(0));
		al.add(1, "yes");
		assertEquals("yes", al.get(1));
		al.add(1, "no");
		//assertEquals("hi", al.set(0, "WHY"));
		// Set in middle
		//assertEquals("no", al.set(1, "HELLO"));
		// Set at end
		//assertEquals("yes", al.set(2, "THERE"));
		
		try {
			al.set(-1, "yes");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("hi", al.get(0));
			assertEquals("no", al.get(1));
			assertEquals("yes", al.get(2));
		}
		
		try {
			al.set(8, "yes");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("hi", al.get(0));
			assertEquals("no", al.get(1));
			assertEquals("yes", al.get(2));
		}
		ArrayList<String> al3 = new ArrayList<String>();
		try {
			al3.set(0, "hi");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, al3.size());
		}
	}
	
	/**
	 * Tests the get method for ArrayList
	 */
	@Test
	public void testGet() {
		ArrayList<String> al = new ArrayList<String>();
		al.add(0, "hi");
		assertEquals("hi", al.get(0));
		al.add(1, "yes");
		assertEquals("yes", al.get(1));
		al.add(1, "no");
		al.get(0);
		try {
			al.get(7);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, al.size());
		}
	}
}
