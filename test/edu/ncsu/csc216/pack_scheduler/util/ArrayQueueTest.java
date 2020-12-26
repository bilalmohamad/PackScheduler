package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * This class will test the ArrayQueue class
 * 
 * @author Bilal Mohamad, Qihao Lu, Tj Tutka
 *
 */
public class ArrayQueueTest {

	/** making a test string used for testing */
	public static final String[] TEST_STRINGS = {"ab", "bc", "cd", "de"};
	
	
	/**
	 * tests the queue
	 */
	@Test
	public void testQueue() {
		ArrayQueue<String> aq = new ArrayQueue<String>(10);
		
		assertTrue(aq.isEmpty());
		
		aq.enqueue("ab");
		aq.enqueue("bc");
		aq.enqueue("cd");
		aq.enqueue("de");
		
		assertFalse(aq.isEmpty());
		assertEquals(4, aq.size());
		
		for (int i = 0; i < aq.size(); i++) {
			assertEquals(TEST_STRINGS[i], aq.dequeue());
		}
	}
	
	/**
	 * tests for invalid queues
	 */
	@Test
	public void testInvalidQueue() {
		
		//Tests an invalid use of the enqueue method
		try {
			ArrayQueue<String> aq = new ArrayQueue<String>(-1);
			aq.enqueue("ab");
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		//Tests an invalid use of the dequeue method
		try {
			ArrayQueue<String> aq = new ArrayQueue<String>(10);
			aq.dequeue();
			fail();
		}
		catch (NoSuchElementException e) {
			assertNull(e.getMessage());
		}
		

		
	}
}
