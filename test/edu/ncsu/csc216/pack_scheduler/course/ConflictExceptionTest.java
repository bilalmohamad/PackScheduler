/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * To test ConflictException
 * @author Tianxin Jia
 *
 */
public class ConflictExceptionTest {

    /**
     * Test method for a ConflictException containing a String.
     */
    @Test
    public void testConflictExceptionString() {
        ConflictException ce = new ConflictException("Custom exception message");
        assertEquals("Custom exception message", ce.getMessage());
    }

    /**
     * Test method for a ConflictException not containing a custom message.
     */
    @Test
    public void testConflictException() {
        ConflictException ce = new ConflictException();
        assertEquals("Schedule conflict.", ce.getMessage());
    }

}
