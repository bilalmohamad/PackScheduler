
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * To test ConflictException
 * @author Qihao Lu
 *
 */
public class InvalidTransitionExceptionTest {

    /**
     * Test method for an InvalidTransitionException with a custom message
     */
    @Test
    public void testConflictExceptionString() {
        InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
        assertEquals("Custom exception message", ce.getMessage());
    }

    /**
     * Test method for an InvalidTransitionException with a default message
     */
    @Test
    public void testConflictException() {
    	InvalidTransitionException ce = new InvalidTransitionException();
        assertEquals("Invalid FSM Transition.", ce.getMessage()); 
    }

}
