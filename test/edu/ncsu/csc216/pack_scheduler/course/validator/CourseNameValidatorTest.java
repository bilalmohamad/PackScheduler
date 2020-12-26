package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests CourseNameValidatorFSM.
 * @author kyle qihao seth
 *
 */
public class CourseNameValidatorTest {

	/**
	 * Test method for CourseNameValidator
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void testIsValid() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();
		// Tests 1 letter prefix path, no suffix
	    assertTrue(c.isValid("A003"));
	    
		// Tests 2 letter prefix path, no suffix
	    c = new CourseNameValidator();
	    assertTrue(c.isValid("AB003"));
	    
		// Tests 3 letter prefix path, no suffix
	    c = new CourseNameValidator();
	    assertTrue(c.isValid("ABC003"));
	    
		// Tests 4 letter prefix path, no suffix
	    c = new CourseNameValidator();
	    assertTrue(c.isValid("ABCD003"));
	    
		// Tests 1 letter prefix path, with suffix
	    c = new CourseNameValidator();
	    assertTrue(c.isValid("A003a"));
	    
		// Tests 2 letter prefix path, with suffix
	    c = new CourseNameValidator();
	    assertTrue(c.isValid("AB003a"));
	    
		// Tests 3 letter prefix path, with suffix
	    c = new CourseNameValidator();
	    assertTrue(c.isValid("ABC003a"));
	    
		// Tests 4 letter prefix path, with suffix
	    c = new CourseNameValidator();
	    assertTrue(c.isValid("ABCD003a"));
	    
	    // Tests starting with digit
	    c = new CourseNameValidator();
	    try {
	    	c.isValid("0AB003");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name must start with a letter.", e.getMessage());
	    }
	    
	    // Tests only 1 number
	    c = new CourseNameValidator();
	    try {
	    	c.isValid("AB0A");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name must have 3 digits.", e.getMessage());
	    }
	    
	    // Tests only 2 numbers
	    c = new CourseNameValidator();
	    try {
	    	c.isValid("AB00A");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name must have 3 digits.", e.getMessage());
	    }
	    
	    // Tests more than 4 letter prefix
	    c = new CourseNameValidator();
	    try {
	    	c.isValid("ABCDE003A");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
	    }
	    
	    // Tests more than more than 3 numbers
	    c = new CourseNameValidator();
	    try {
	    	c.isValid("ABCD0003A");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name can only have 3 digits.", e.getMessage());
	    }
	    
	    // Tests number after suffix
	    c = new CourseNameValidator();
	    try {
	    	c.isValid("ABCD003A1");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
	    }
	    
	    // Tests more than 1 letter suffix
	    c = new CourseNameValidator();
	    try {
	    	c.isValid("ABCD003AA");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
	    }
	    
	    // Tests invalid character
	    c = new CourseNameValidator();
	    try {
	    	c.isValid("ABC-003A");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name can only contain letters and digits.", e.getMessage());
	    }
	    
	    // Tests invalid character at start
	    c = new CourseNameValidator();
	    try {
	    	c.isValid("-ABC003A");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name can only contain letters and digits.", e.getMessage());
	    }
	    
	    // Tests digit character at start
	    c = new CourseNameValidator(); 
	    try {
	    	c.isValid("1ABC003A");
	    	fail();
	    } catch (InvalidTransitionException e) {
	    	assertEquals("Course name must start with a letter.", e.getMessage());
	    }
	}

}
