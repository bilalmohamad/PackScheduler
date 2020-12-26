/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests CourseNameValidatorFSM.
 * @author romir seth, kyle 
 *
 */
public class CourseNameValidatorFSMTest { 
	
	/**
	 * Tests the inital state
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void testStateInitial() throws InvalidTransitionException { 
		CourseNameValidatorFSM c = new CourseNameValidatorFSM(); 
		assertFalse(c.isValid(""));
	}
	
	/**
	 * Tests the first letter state
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void stateL() throws InvalidTransitionException {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM(); 
		assertTrue(c.isValid("C116"));
	}
	
	/**
	 * Tests the second letter state
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void stateLL() throws InvalidTransitionException {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM(); 
		assertTrue(c.isValid("CS116"));
	}
	
	/**
	 * Tests the third letter state
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void stateLLL() throws InvalidTransitionException {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM(); 
		assertTrue(c.isValid("CSC116"));
	}
	
	/**
	 * Tests the fourth letter state
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void stateLLLL() throws InvalidTransitionException {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM(); 
		assertTrue(c.isValid("CSCA116"));
	}
	
	/**
	 * Tests the first digit state
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void stateD() throws InvalidTransitionException {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM(); 
		assertFalse(c.isValid("CSCA1"));
	}
	
	/**
	 * Tests the second digit state
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void stateDD() throws InvalidTransitionException {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM(); 
		assertFalse(c.isValid("CSCA11"));
	}
	
	/**
	 * Tests the third digit state
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void stateDDD() throws InvalidTransitionException {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM(); 
		assertTrue(c.isValid("CSCA116"));
	}
	
	/**
	 * Tests the suffix state
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void stateSuffix() throws InvalidTransitionException {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM(); 
		assertTrue(c.isValid("CSCA116y"));  
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidatorFSM#isValid(java.lang.String)}.
	 * @throws InvalidTransitionException  if the class attempts to make an invalid transition
	 */
	@Test
	public void testIsValid() throws InvalidTransitionException {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM();
		// Tests 1 letter prefix path, no suffix
	    assertTrue(c.isValid("A003"));
	    
		// Tests 2 letter prefix path, no suffix
	    assertTrue(c.isValid("AB003"));
	    
		// Tests 3 letter prefix path, no suffix
	    assertTrue(c.isValid("ABC003"));
	    
		// Tests 4 letter prefix path, no suffix
	    assertTrue(c.isValid("ABCD003"));
	    
		// Tests 1 letter prefix path, with suffix
	    assertTrue(c.isValid("A003a"));
	    
		// Tests 2 letter prefix path, with suffix
	    assertTrue(c.isValid("AB003a"));
	    
		// Tests 3 letter prefix path, with suffix
	    assertTrue(c.isValid("ABC003a"));
	    
		// Tests 4 letter prefix path, with suffix
	    assertTrue(c.isValid("ABCD003a"));
	    
	    // Tests starting with digit
	    try {
	    	c.isValid("0AB003");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name must start with a letter.", e.getMessage());
	    }
	    
	    // Tests only 1 number
	    try {
	    	c.isValid("AB0A");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name must have 3 digits.", e.getMessage());
	    }
	    
	    // Tests only 2 numbers
	    try {
	    	c.isValid("AB00A");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name must have 3 digits.", e.getMessage());
	    }
	    
	    // Tests more than 4 letter prefix
	    try {
	    	c.isValid("ABCDE003A");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
	    }
	    
	    // Tests more than more than 3 numbers
	    try {
	    	c.isValid("ABCD0003A");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name can only have 3 digits.", e.getMessage());
	    }
	    
	    // Tests number after suffix
	    try {
	    	c.isValid("ABCD003A1");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
	    }
	    
	    // Tests more than 1 letter suffix
	    try {
	    	c.isValid("ABCD003AA");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
	    }
	    
	    // Tests invalid character
	    try {
	    	c.isValid("ABC-003A");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name can only contain letters and digits.", e.getMessage());
	    }
	}
	
	/**
	 * Tests isValid
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void testIsValid2() throws InvalidTransitionException {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM(); 
		assertTrue(c.isValid("CSCA116"));
		assertTrue(c.isValid("C116"));
		assertFalse(c.isValid("CSC3")); 
		try {
			c.isValid("CSC3");
		} catch (InvalidTransitionException e) {
			assertTrue(e instanceof Exception); 
		}
	}
	
}