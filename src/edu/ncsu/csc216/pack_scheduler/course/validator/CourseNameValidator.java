package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Checks the course name to see if it follows the guidelines
 * for a course name
 * @author TJ
 *
 */
public class CourseNameValidator {

	/** Creating a letter State object */
	private final State letterState = new LetterState();
	/** Creating a digit State object */
	private final State digitState = new DigitState();
	/** Creating a suffix State object */
	private final State suffixState = new SuffixState();
	/** Creating a initial State object */
	private final State initialState = new InitialState();
	/** Creating a currentState and setting it equal to initialState */
	private State currentState = initialState;

	/** making a variable to count the number of letters */
	private int letterCount;
	/** making a variable to count the number of digits */
	private int digitCount;
	/** checks to see if the course name is valid */
	private boolean isDone = false;
	/** making sure the last object is a letter */
	private int suffix;

	/** gets the letter counter and updates from within the methods 
	 * @return letterCount how many letters in the course name
	 */
	public int getLetterCount() {
		return letterCount;
	}

	/** gets the digit counter and updates from within the methods 
	 *  @return digitCount how many digits in the course name
	 */
	public int getDigitCount() {
		return digitCount;
	}

	/**
	 * The State class that calls the other private classes
	 * @author TJ
	 *
	 */
	abstract class State {

		/** calls the onLetter method and gets the result */
		abstract void onLetter() throws InvalidTransitionException;

		/** calls the onDigit method and gets the result */
		abstract void onDigit() throws InvalidTransitionException;

		/**
		 * Sets the currentState to initial
		 * @throws InvalidTransitionException when anything other than a number or letter is inputed
		 */
		void onOther() throws InvalidTransitionException {
		    currentState = initialState;
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}

	}

	/**
	 * contains the letterState that extents the state class
	 * @author TJ
	 *
	 */
	private class LetterState extends State {

		/** sets the max letter count to 4 */
		private static final int MAX_LETTER_COUNT = 4;

		/**
		 * checks to see if the letters stay within the bounds and returns the letter 
		 * state if it is looping until there are no more letters
		 * or throws and InvalidTransitionException;
		 */
		@Override
		void onLetter() throws InvalidTransitionException {

			letterCount += 1;
			if (getLetterCount() > MAX_LETTER_COUNT) {
			    currentState = initialState;
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
			currentState = letterState;
		}

		/**
		 * increments the digit counter and sets currentState to digitState
		 */
		@Override
		void onDigit() throws InvalidTransitionException {
		    System.out.println("l2");
			digitCount += 1;
			currentState = digitState;
		}
	}

	/**
	 * contains the digitState that extents the state class
	 * @author TJ
	 *
	 */
	private class DigitState extends State {

		/** sets the maximum digit in a name to 3 */
		private static final int MAX_DIGIT_COUNT = 3;

		/** sets the currentState to initialState and throws a exception if the course does not have 3 digits */
		@Override
		void onLetter() throws InvalidTransitionException {
		    currentState = initialState;
			throw new InvalidTransitionException("Course name must have 3 digits.");
		}

		/** 
		 * Increments the digit counter and checks to see if the course digit equals the max digit
		 * and sets the current state to suffixState
		 */
		@Override
		void onDigit() throws InvalidTransitionException {
		    System.out.println("d2");
			digitCount += 1;
			if(getDigitCount() == MAX_DIGIT_COUNT) {
			    currentState = suffixState;
			    isDone = true;
			}
		}
	}
	
	/**
	 * The final state if there is letter at the end of the course name
	 * @author TJ
	 *
	 */
	private class SuffixState extends State {

		/**  
		 * adds 1 to suffix and checks to see its within bounds
		 * and sets currentState to intitialState if failed
		 */
		@Override
		void onLetter() throws InvalidTransitionException {
		    suffix++;
			
			if(suffix > 1) {
			    currentState = initialState;
                throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
			}
		}

		/**  
		 * a suffix can never be a number so it throws an error and 
		 * sets currentState to initialState
		 */
		@Override
		void onDigit() throws InvalidTransitionException {
		    currentState = initialState;
		    if(suffix >= 1) {
		        throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		    }
			throw new InvalidTransitionException("Course name can only have 3 digits.");
		}
	}
	
	/**  
	 * Makes the currentState to letterState and sets letterCount to 1
	 * @author TJ
	 */
	private class InitialState extends State {

		/**
		 * the letterState should be first so it sets currentState to letterState
		 * and letterCount to 1
		 */
		@Override
		void onLetter() throws InvalidTransitionException {
			currentState = letterState;
			letterCount = 1;
			
		}

		/** 
		 * first one shouldn't be a digit and sets currentState to initialState
		 * and throws and exception
		 */
		@Override
		void onDigit() throws InvalidTransitionException {
		    currentState = initialState;
			throw new InvalidTransitionException("Course name must start with a letter.");
			
		}
		
	}
	
	/**
	 * Checks to see if the course name is valid
	 * @param s the course name
	 * @return true if the name is correct
	 * @throws InvalidTransitionException if any of the of the course are not correct
	 */
	public boolean isValid(String s) throws InvalidTransitionException {
		letterCount = 0;
		digitCount = 0;
		char c;
		int charIndex = 0;
		isDone = false;
		suffix = 0;
		while(charIndex < s.length()) {
		    
			c = s.charAt(charIndex);
			
			if(Character.isLetter(c)) {
				currentState.onLetter();
			} else if(Character.isDigit(c)) {
				currentState.onDigit();
			} else {
				currentState.onOther();
			}
			charIndex++;
		}
		currentState = initialState;
		return isDone;
	}
}