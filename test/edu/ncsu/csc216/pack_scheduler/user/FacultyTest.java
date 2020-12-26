package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests the Faculty class
 * 
 * @author Bilal Mohamad, Tj Tutka, Qihao Lu
 *
 */
public class FacultyTest {

	/**
	 * Tests the construction of the Faculty object
	 */
	@Test
	public void testFaculty() {
		Faculty f = new Faculty("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password", 3);
		assertEquals(3, f.getMaxCourses());
		
		//Tests the set method
		f.setMaxCourses(2);
		assertEquals(2, f.getMaxCourses());
	}
	
	
	/**
	 * Tests the invalid construction of the Faculty object
	 */
	@Test
	public void testInvalidFaculty() {
		//Tests below valid maxCourses range
		try {
			Faculty f = new Faculty("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password", 0);
			f.getMaxCourses();
			fail();
		}
		catch(IllegalArgumentException e) {
			assertEquals("Invalid max courses", e.getMessage());
		}
		
		//Tests above valid maxCourses range
		try {
			Faculty f = new Faculty("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password", 4);
			f.getMaxCourses();
			fail();
		}
		catch(IllegalArgumentException e) {
			assertEquals("Invalid max courses", e.getMessage());
		}
	}
	
	
	/**
	 * Tests the hashCode() method
	 */
	@Test
	public void testHashCode() {
		User f1 = new Faculty("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password", 2);
		User f2 = new Faculty("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password", 2);
		User f3 = new Faculty("first", "Lu", "qlu4", "qlu4@ncsu.edu", "password", 1);
		User f4 = new Faculty("Qihao", "last", "qlu4", "qlu4@ncsu.edu", "password", 3);
		User f5 = new Faculty("Qihao", "Lu", "id", "qlu4@ncsu.edu", "password", 3);
		User f6 = new Faculty("Qihao", "Lu", "qlu4", "qlu3@ncsu.edu", "password", 2);
		User f7 = new Faculty("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "pw", 1);
		User f8 = new Faculty("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password", 3);
		
		assertEquals(f1.hashCode(), f2.hashCode());
		
		assertNotEquals(f1.hashCode(), f3.hashCode());
		assertNotEquals(f1.hashCode(), f4.hashCode());
		assertNotEquals(f1.hashCode(), f5.hashCode());
		assertNotEquals(f1.hashCode(), f6.hashCode());
		assertNotEquals(f1.hashCode(), f7.hashCode());
		assertNotEquals(f1.hashCode(), f8.hashCode());
	}
	
	
	/**
	 * Tests the equals() method
	 */
	@Test
	public void testEquals() {
		User f1 = new Faculty("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password", 2);
		User f2 = new Faculty("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password", 2);
		
		assertTrue(f1.equals(f2));
	}
	
	
	/**
	 * Tests the toString() method
	 */
	@Test
	public void testToString() {
		Faculty f = new Faculty("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password", 3);
		
		assertEquals("Qihao,Lu,qlu4,qlu4@ncsu.edu,password,3", f.toString());
	}
	
}
