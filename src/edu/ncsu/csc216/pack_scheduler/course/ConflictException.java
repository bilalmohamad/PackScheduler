package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Exception for conflict activity
 * @author Tianxin Jia
 *
 */
public class ConflictException extends Exception {
    
    /** ID used for serialization. */
    private static final long serialVersionUID = 1L;
     /**
      * To show custom message for exception
      * @param message showed message for exception
      */
    public ConflictException(String message) {
        super(message);
    }
    /**
     * Create new exception for conflict
     */
    public ConflictException() {
        super("Schedule conflict.");
    }

}
