package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * test for Student.class
 * @author qlu4
 *
 */
public class StudentTest {

	/**
	 * test hash code
	 */
	@Test
	public void testHashCode() {
		User s1 = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
		User s2 = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
		User s3 = new Student("first", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
		User s4 = new Student("Qihao", "last", "qlu4", "qlu4@ncsu.edu", "password");
		User s5 = new Student("Qihao", "Lu", "id", "qlu4@ncsu.edu", "password");
		User s6 = new Student("Qihao", "Lu", "qlu4", "qlu3@ncsu.edu", "password");
		User s7 = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "pw");
		User s8 = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password", 15);
		
		assertEquals(s1.hashCode(), s2.hashCode());
		
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
		}

	
	
	/**
	 *test constructor which has credits setter
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		Student s = null; 
		
		try {
		    s = new Student(null, "last", "id", "email@ncsu.edu", "hashedpassword", 15);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		s = null;
		
		try {
		    s = new Student("Qihao", null, "id", "email@ncsu.edu", "hashedpassword", 15);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		s = null;
		
		try {
		    s = new Student("Qihao", "Lu", null, "email@ncsu.edu", "hashedpassword", 15);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		s = null;
		try {
		    s = new Student("Qihao", "Lu", "qlu4", null, "hashedpassword", 15);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		s = null;
		try {
		    s = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", null, 15);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		

		try {
			s = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password", 15);
            assertEquals("Qihao", s.getFirstName());
            assertEquals("Lu", s.getLastName());
            assertEquals("qlu4", s.getId());
            assertEquals("qlu4@ncsu.edu", s.getEmail());
            assertEquals("password", s.getPassword());
            assertEquals( 15, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
		    fail();
		}


	}
    /**
     * test the constructor which has no credits setter
     */
	@Test
	public void testStudentStringStringStringStringString() {
		Student s = null; 
		try {
		    s = new Student(null, "last", "id", "email@ncsu.edu", "hashedpassword");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		s = null;
		try {
		    s = new Student("Qihao", null, "id", "email@ncsu.edu", "hashedpassword");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		s = null;
		try {
		    s = new Student("Qihao", "Lu", null, "email@ncsu.edu", "hashedpassword");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		s = null;
		try {
		    s = new Student("Qihao", "Lu", "qlu4", null , "hashedpassword");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		s = null;
		try {
		    s = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", null);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		try {
			s = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
            assertEquals("Qihao", s.getFirstName());
            assertEquals("Lu", s.getLastName());
            assertEquals("qlu4", s.getId());
            assertEquals("qlu4@ncsu.edu", s.getEmail());
            assertEquals("password", s.getPassword());
            assertEquals( 18, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
		    fail();
		}
		
		
	}

	/**
	 * test set email
	 */
	@Test
	public void testSetEmail() {
		Student s = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
        assertEquals("Qihao", s.getFirstName());
        assertEquals("Lu", s.getLastName());
        assertEquals("qlu4", s.getId());
        assertEquals("qlu4@ncsu.edu", s.getEmail());
        assertEquals("password", s.getPassword());
        assertEquals( 18, s.getMaxCredits());
        
        
		try {
		    s.setEmail(null);
		} catch (IllegalArgumentException e) {
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());
		}
		
		
		
		try {
		    s.setEmail("");
		} catch (IllegalArgumentException e) {
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());
		}
		
		try {
		    s.setEmail("qlu4ncsu.edu");
		} catch (IllegalArgumentException e) {
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());
		}
		
		try {
		    s.setEmail("qlu4@ncsuedu");
		} catch (IllegalArgumentException e) {
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());
		}
		
		try {
		    s.setEmail("ncsu.edu@qlu4");
		} catch (IllegalArgumentException e) {
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());
		}
		

		    s.setEmail("qlu3@ncsu.edu");
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu3@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());

		

		
	}

	/**
	 * tets set password
	 */
	@Test
	public void testSetPassword() {
		Student s = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
        assertEquals("Qihao", s.getFirstName());
        assertEquals("Lu", s.getLastName());
        assertEquals("qlu4", s.getId());
        assertEquals("qlu4@ncsu.edu", s.getEmail());
        assertEquals("password", s.getPassword());
        assertEquals( 18, s.getMaxCredits());
        
		try {
		    s.setPassword(null);
		} catch (IllegalArgumentException e) {
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());
		}
        
        
		try {
		    s.setPassword("");
		} catch (IllegalArgumentException e) {
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());
		}
		
		

		    s.setPassword("New");
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("New", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());

		
		
		
	}

	/**
	 * test set credits
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
        assertEquals("Qihao", s.getFirstName());
        assertEquals("Lu", s.getLastName());
        assertEquals("qlu4", s.getId());
        assertEquals("qlu4@ncsu.edu", s.getEmail());
        assertEquals("password", s.getPassword());
        assertEquals( 18, s.getMaxCredits());
        
		try {
		    s.setMaxCredits(2);
		} catch (IllegalArgumentException e) {
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());
		}
        
        
		try {
		    s.setMaxCredits(19);
		} catch (IllegalArgumentException e) {
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());
		}
		
		

		    s.setMaxCredits(15);
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 15, s.getMaxCredits());

	}

	/**
	 * test set first name
	 */
	@Test
	public void testSetFirstName() {
		Student s = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
        assertEquals("Qihao", s.getFirstName());
        assertEquals("Lu", s.getLastName());
        assertEquals("qlu4", s.getId());
        assertEquals("qlu4@ncsu.edu", s.getEmail());
        assertEquals("password", s.getPassword());
        assertEquals( 18, s.getMaxCredits());
        
		try {
		    s.setFirstName(null);
		} catch (IllegalArgumentException e) {
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());
		}
        
        
		try {
		    s.setFirstName("");
		} catch (IllegalArgumentException e) {
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());
		}
		
		

		    s.setFirstName("New");
	        assertEquals("New", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());
	}

	/**
	 * test set last name
	 */
	@Test
	public void testSetLastName() {
		Student s = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
        assertEquals("Qihao", s.getFirstName());
        assertEquals("Lu", s.getLastName());
        assertEquals("qlu4", s.getId());
        assertEquals("qlu4@ncsu.edu", s.getEmail());
        assertEquals("password", s.getPassword());
        assertEquals( 18, s.getMaxCredits());
        
		try {
		    s.setLastName(null);
		} catch (IllegalArgumentException e) {
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());
		}
        
        
		try {
		    s.setLastName("");
		} catch (IllegalArgumentException e) {
	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("Lu", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());
		}
		
		

		    s.setLastName("New");

	        assertEquals("Qihao", s.getFirstName());
	        assertEquals("New", s.getLastName());
	        assertEquals("qlu4", s.getId());
	        assertEquals("qlu4@ncsu.edu", s.getEmail());
	        assertEquals("password", s.getPassword());
	        assertEquals( 18, s.getMaxCredits());

	}

	/**
	 * test equals
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
		User s2 = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
		User s3 = new Student("first", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
		User s4 = new Student("Qihao", "last", "qlu4", "qlu4@ncsu.edu", "password");
		User s5 = new Student("Qihao", "Lu", "id", "qlu4@ncsu.edu", "password");
		User s6 = new Student("Qihao", "Lu", "qlu4", "qlu3@ncsu.edu", "password");
		User s7 = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "pw");
		User s8 = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password", 15);
		
		
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8)); 
		
		
	}

	/**
	 * test to string
	 */
	@Test
	public void testToString() {
		User s1 = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
		String str1 = "Qihao,Lu,qlu4,qlu4@ncsu.edu,password,18";
		assertEquals(str1, s1.toString());
		
		User s2 = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password", 15);
		String str2 = "Qihao,Lu,qlu4,qlu4@ncsu.edu,password,15";
		assertEquals(str2, s2.toString());
		
	}

	
	
	
	/**
	 * test compareTo method in Student class
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
		Student s2 = new Student("Alex", "White", "awhite1", "awhite1@ncsu.edu", "password");
		Student s3 = new Student("Bacon", "White", "bwhite1", "awhite1@ncsu.edu", "password");
		Student s4 = new Student("Bacon", "White", "bwhite2", "awhite1@ncsu.edu", "password");
		int i ;
		
		i = s1.compareTo(s2);
		assertEquals(-1, i);
		i = s2.compareTo(s1);
		assertEquals(1, i);
		
		i = s1.compareTo(s1);
		assertEquals(0, i);
		
		i = s2.compareTo(s3);
		assertEquals(-1, i);
		i = s3.compareTo(s2);
		assertEquals(1, i);
		
		i = s3.compareTo(s4);
		assertEquals(-1, i);
		i = s4.compareTo(s3);
		assertEquals(1, i);
        
		
	}
	
	/**
	 * test getSchedule method in Student class
	 */
	@Test
	public void testGetSchedule() {
		Student s = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
		assertEquals(s.getSchedule().getTitle(), "My Schedule");
	}
	
	/**
	 * Tests canAdd
	 */
	@Test
	public void testCanAdd() {
		Student s = new Student("Qihao", "Lu", "qlu4", "qlu4@ncsu.edu", "password");
		Course c = new Course("nam123", "bleh", "001", 5, "Hi", 10, "MW");
		assertTrue(s.canAdd(c));
	}
}
