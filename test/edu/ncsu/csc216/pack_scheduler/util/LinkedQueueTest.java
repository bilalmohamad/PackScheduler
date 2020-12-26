package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * This class will test the LinkedQueue class
 * 
 * @author Bilal Mohamad, Qihao Lu, Tj Tutka
 *
 */
public class LinkedQueueTest {

	/** making a test string used for testing */
	public static final String[] TEST_STRINGS = {"ab", "bc", "cd", "de"};
	
	
	/**
	 * tests the queue
	 */
	@Test
	public void testQueue() {
		LinkedQueue<String> lq = new LinkedQueue<String>(10);
		
		assertTrue(lq.isEmpty());
		
		lq.enqueue("ab");
		lq.enqueue("bc");
		lq.enqueue("cd");
		lq.enqueue("de");
		
		assertFalse(lq.isEmpty());
		assertEquals(4, lq.size());
		
		for (int i = 0; i < lq.size(); i++) {
			assertEquals(TEST_STRINGS[i], lq.dequeue());
		}
	}
	
	/**
	 * tests for invalid queues
	 */
	@Test
	public void testInvalidQueue() {
		
		//Tests an invalid use of the enqueue method
		try {
			LinkedQueue<String> lq = new LinkedQueue<String>(-1);
			lq.enqueue("ab");
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Capacity can not be less than 0!", e.getMessage());
		}
		
		//Tests an invalid use of the dequeue method
		try {
			LinkedQueue<String> lq = new LinkedQueue<String>(10);
			lq.dequeue();
			fail();
		}
		catch (NoSuchElementException e) {
			assertNull(e.getMessage());
		}
		
		//Tests adding an element when capacity has been reached
//		try {
//			LinkedQueue<String> lq = new LinkedQueue<String>(10);
//			lq.setCapacity(1);
//			lq.enqueue("ab");
//			fail();
//		}
//		catch (IllegalArgumentException e) {
//			assertNull(e.getMessage());
//		}		
	}
}
