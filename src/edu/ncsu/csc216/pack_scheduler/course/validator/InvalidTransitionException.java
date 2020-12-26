package edu.ncsu.csc216.pack_scheduler.course.validator;
/**
 * Invalid transition exception
 * @author Qihao Lu
 *
 */
public class InvalidTransitionException extends Exception {
	/** ID used for serialization. */
    private static final long serialVersionUID = 1L;
     /**
      * To show custom message for exception
      * @param message showed message for exception
      */
    public InvalidTransitionException(String message) {
        super(message);
    }
    /**
     * Create new exception for conflict
     */
    public InvalidTransitionException() {
        super("Invalid FSM Transition.");
    }
}
