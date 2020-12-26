package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;
/**
 * Test for ArrayStack class
 * @author Qihao Lu
 *
 */
public class ArrayStackTest {

	/**
	 * test for ArrayStack methods
	 */
	@Test
	public void testPush() {
		ArrayStack<String> a = new ArrayStack<String>(10);
		assertTrue(a.isEmpty());
		
		a.push("a");
		assertEquals(1, a.size());
		a.pop();
		assertEquals(0, a.size());
		
		a.push("b");
		a.push("c");
		assertEquals(2, a.size());
		a.pop();
		a.pop();
		assertEquals(0, a.size());
		
		a.push("a");
		a.push("b");
		a.push("c");
		a.push("d");
		assertEquals(4, a.size());
		a.pop();
		a.pop();
		a.pop();
		a.pop();
		assertEquals(0, a.size());
		
		try {
			a.pop();
			fail();
		} catch(EmptyStackException e) {
			//should pass
		}
		
		try {
			a.push("a");
			a.push("b");
			a.push("c");
			a.push("d");
			a.setCapacity(3);
			fail();
		} catch(IllegalArgumentException e) {
			//should pass
		}	
	}
}
