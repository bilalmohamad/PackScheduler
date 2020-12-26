package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * The Faculty class represents an individual faculty record. This class extends the User class
 * 
 * @author Bilal Mohamad, Tj Tutka, Qihao Lu
 *
 */
public class Faculty extends User {

	/** The minimum number of courses a faculty member can teach in a given semester */
	public static final int MIN_COURSES = 1;

	/** The maximum number of courses a faculty member can teach in a given semester */
	public static final int MAX_COURSES = 3;

	/** The number of courses a faculty member can teach in a given semester */
	private int maxCourses;
	
	/** The Faculty member's schedule */
	private FacultySchedule schedule;
	
	/**
	 * The constructor for the Faculty object type. Uses the super method to retrieve every parameter but maxCourses.
	 * Uses the setMaxCourses method to set the maxCourses to the parameter value.
	 * 
	 * @param firstName		the first name of the faculty member
	 * @param lastName		the last name of the faculty member
	 * @param id			the user id of the faculty member
	 * @param email			the email of the faculty member
	 * @param password		the password of the faculty member
	 * @param maxCourses	the max courses the faculty member teaches
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
		schedule = new FacultySchedule(id);
	}
	
	
	/**
	 * Sets the maxCourses instance variable to the maxCourses parameter
	 * 
	 * @param maxCourses the max courses the faculty member teaches
	 * @throws IllegalArgumentException if the maxCourses parameter is not within the range of 1 and 3 (inclusive)
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		
		this.maxCourses = maxCourses;
	}
	
	
	/**
	 * Retrieves the maxCourses variable
	 * @return the maxCourses variable
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	
	/**
	 * Hashes the faculty member's information
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Determines if two objects are equal
	 * 
	 * @param obj	the object used for comparison
	 * 
	 * @return	true	if the objects are the same
	 * 			false	if the objects are different
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

	
	/**
	 * Converts the faculty member's information into a String
	 * 
	 * @return a String containing the faculty member's information as a String
	 */
	@Override
	public String toString() {
		String s;
		s = getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCourses;
		return s;  
	}
	
	
	/**
	 * This method will retrieve the current faculty member's schedule
	 * 
	 * @return the schedule of the Faculty member
	 */
	public FacultySchedule getSchedule() {
		return this.schedule;
	}
	
	
	/**
	 * This method will inform the user if the faculty member's schedule has been overloaded.
	 * 
	 * @return	true if the number of scheduled courses is greater than the Faculty member's max courses
	 * 			false otherwise
	 */
	public boolean isOverloaded() {
		if (schedule.getNumScheduledCourses() > this.maxCourses) {
			return true;
		}
		return false;
	}

}
