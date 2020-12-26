/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *  Tests the LinkedAbstractList class
 * @author Kyle Kleinke
 *
 */
public class LinkedAbstractListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#size()}.
	 */
	@Test
	public void testSize() {
		LinkedAbstractList<String> a = new LinkedAbstractList<String>(5);
		assertEquals(0, a.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#LinkedAbstractList(int)}.
	 */
	@Test
	public void testLinkedAbstractList() {
		LinkedAbstractList<String> a = new LinkedAbstractList<String>(2);
		assertEquals(0, a.size());
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#setCapacity(int)}.
	 */
	@Test
	public void testSetCapacity() {
		LinkedAbstractList<String> a = new LinkedAbstractList<String>(2);
		a.setCapacity(5);
		assertEquals(0, a.size());
		a.add(0, "Hi");
		try {
			a.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, a.size());
		}
		try {
			a.setCapacity(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, a.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#get(int)}.
	 */
	@Test
	public void testGet() {
		LinkedAbstractList<String> a = new LinkedAbstractList<String>(2);
		a.add(0, "Hi");
		assertEquals("Hi", a.get(0));
		a.add(1, "Hello");
		assertEquals("Hello", a.get(1));
		try {
			a.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, a.size());
		}
		
		try {
			a.get(99);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, a.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAdd() {
		LinkedAbstractList<String> a = new LinkedAbstractList<String>(5);
		try {
			a.add(0, null);
			fail();
		} catch(NullPointerException e) {
			assertEquals(0, a.size());
		}
		a.add(0, "1");
		assertEquals("1", a.get(0));
		a.add(1, "2");
		assertEquals("1", a.get(0));
		assertEquals("2", a.get(1));
		a.add(2, "3");
		assertEquals("1", a.get(0));
		assertEquals("2", a.get(1));
		assertEquals("3", a.get(2));
		a.add(3, "4");
		assertEquals("1", a.get(0));
		assertEquals("2", a.get(1));
		assertEquals("3", a.get(2));
		assertEquals("4", a.get(3));
		a.add(4, "5");
		assertEquals("1", a.get(0));
		assertEquals("2", a.get(1));
		assertEquals("3", a.get(2));
		assertEquals("4", a.get(3));
		assertEquals("5", a.get(4));
		try {
			a.add(5, "6");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("1", a.get(0));
			assertEquals("2", a.get(1));
			assertEquals("3", a.get(2));
			assertEquals("4", a.get(3));
			assertEquals("5", a.get(4));
			assertEquals(5, a.size());
		}
		a.setCapacity(6);
		try {
			a.add(-10, "6");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("1", a.get(0));
			assertEquals("2", a.get(1));
			assertEquals("3", a.get(2));
			assertEquals("4", a.get(3));
			assertEquals("5", a.get(4));
			assertEquals(5, a.size());
		}
		try {
			a.add(6, "1");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("1", a.get(0));
			assertEquals("2", a.get(1));
			assertEquals("3", a.get(2));
			assertEquals("4", a.get(3));
			assertEquals("5", a.get(4));
			assertEquals(5, a.size());
		}
		try {
			a.add(5, "1");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("1", a.get(0));
			assertEquals("2", a.get(1));
			assertEquals("3", a.get(2));
			assertEquals("4", a.get(3));
			assertEquals("5", a.get(4));
			assertEquals(5, a.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#remove(int)}.
	 */
	@Test
	public void testRemove() {
		LinkedAbstractList<String> a = new LinkedAbstractList<String>(5);
		a.add(0, "1");
		a.add(1, "2");
		a.add(2, "3");
		assertEquals("1", a.remove(0));
		assertEquals("2", a.get(0));
		assertEquals("3", a.get(1));
		a.add(0, "1");
		assertEquals("1", a.get(0));
		assertEquals("2", a.get(1));
		assertEquals("3", a.get(2));
		assertEquals("2", a.remove(1));
		assertEquals("1", a.get(0));
		assertEquals("3", a.get(1));
		a.add(1, "2");
		assertEquals("3", a.remove(2));
		assertEquals("1", a.get(0));
		assertEquals("2", a.get(1));
		a.add(2, "3");
		
		try {
			a.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("1", a.get(0));
			assertEquals("2", a.get(1));
			assertEquals("3", a.get(2));
		}
		
		try {
			a.remove(5);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("1", a.get(0));
			assertEquals("2", a.get(1));
			assertEquals("3", a.get(2));
		}
	}
	
	/**
	 * Tests the set method
	 */
	@Test
	public void testSet() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(5);
		list.add("orange");
        list.add("banana");
        list.add("apple");
        list.add("kiwi");
        try {
            list.set(0, "apple");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
		try {
			list.set(0, null);
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
		
		try {
			list.set(-1, "strawberry");
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
//		try {
//			list.set(0, "something");
//		} catch (Exception e) {
//			fail();
//		}
		
		try {
			list.set(0, "apple");
			list.set(1, "apple");
			fail();
		} catch (Exception e) {
			//assertTrue(e instanceof IllegalArgumentException);
		}
		
		//list.set(1, "strawberry");
        //assertEquals(list.get(1), "strawberry");
	}
}
