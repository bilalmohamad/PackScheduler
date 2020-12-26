package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Test;




/**
 * Test for LinkedList
 * @author Bilal Mohamad, Qihao Lu, Tj Tutka
 *
 */
public class LinkedListTest {

	/**
	 * test for LinkedList
	 */
	@Test
	public void linkedListTest() {
		
		
		
		try {
			LinkedList<Integer> l = new LinkedList<Integer>();
			assertEquals(0, l.size());
			l.add(0, 1);
			l.add(1, 2);
			l.set(1, 5);
			l.remove(0);
			assertEquals(l.size(), 1);		
			
			try {
				l.remove(null);
			} catch (IllegalStateException e) {
				assertTrue(e instanceof IllegalStateException);
			}
			
			/** testing add */
			try {
				l.add(0, 5);
			} catch (IllegalArgumentException e) {
				assertTrue(e instanceof IllegalArgumentException);
			}
			try {
				l.add(null);
			} catch (NullPointerException e) {
				assertTrue(e instanceof NullPointerException);
			}
			try {
				l.add(-1, 2);
			} catch (IndexOutOfBoundsException e) {
				assertTrue(e instanceof IndexOutOfBoundsException);
			}
			try {
				l.add(100, 2);
			} catch (IndexOutOfBoundsException e) {
				assertTrue(e instanceof IndexOutOfBoundsException);
			}
			
			/** testing set */
			
			try {
				l.set(0, 5);
			} catch (IllegalArgumentException e) {
				assertTrue(e instanceof IllegalArgumentException);
			}
			try {
				l.set(-1, 2);
			} catch (IndexOutOfBoundsException e) {
				assertTrue(e instanceof IndexOutOfBoundsException);
			}
			try {
				l.set(100, 2);
			} catch (IndexOutOfBoundsException e) {
				assertTrue(e instanceof IndexOutOfBoundsException);
			}
			
			ListIterator<Integer> i = l.listIterator(1);
			assertFalse(i.hasNext());
			assertTrue(i.hasPrevious());
			i.add(3);
			assertEquals(i.previousIndex(), 0);
			try {
				i.next().intValue();
			} catch(NoSuchElementException e) {
				assertTrue(e instanceof NoSuchElementException);
			}
			
			//assertEquals(i.previous().intValue(), 1);
			try {
				i.remove();
			} catch (IllegalStateException e) {
				assertTrue(e instanceof IllegalStateException);
			}
			try {
				i.set(2);
			} catch (IllegalStateException e) {
				assertTrue(e instanceof IllegalStateException);
			}
			try {
				i.set(null);
			} catch (Exception e) {
				assertTrue(e instanceof Exception);
			}
			
			assertEquals(i.nextIndex(), 0); 
		} catch(Exception e) {
			fail();
		}
		
		
		
		
	}

}
