package edu.ncsu.csc216.pack_scheduler.course.roll;
/**
 * Creates a course roll for a course
 * @author Qihao Lu, Romir Seth, Kyle Kleinke
 *
 */

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * CourseRoll sets the enrollment cap of the courses
 * between the set bounds. It also adds and removes
 * students from the wait list.
 * @author TJ
 *
 */
public class CourseRoll {
	
	/** The smallest class size */
	public static final int MIN_ENROLLMENT = 10;
	
	/** The biggest class size */
	public static final int MAX_ENROLLMENT = 250;
	
	/** setting the waitList size */
	public static final int WAITLIST_SIZE = 10;
	
	/** How big the class is */
	private int enrollmentCap;
	
	/** The list of students in a class */
	private LinkedAbstractList<Student> roll; 
	
	/** The Course associated with the current CourseRoll */
	private Course course;
	
	/**	The wait list for the course */
	private LinkedQueue<Student> waitList;
	
	/**
	 * Constructs the Course Roll
	 * @param c the course being read in
	 * @param enrollmentCap Number of students that can be in the class
	 */
	public CourseRoll(Course c, int enrollmentCap)
	{
		if (c == null || enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		roll = new LinkedAbstractList<Student>(enrollmentCap);
		waitList = new LinkedQueue<Student>(WAITLIST_SIZE);
		setEnrollmentCap(enrollmentCap);
		
		course = c;	
	}
	
	/**
	 * Returns the list of students in the class
	 * @return The list of students in the class
	 */
	public LinkedAbstractList<Student> getCourseRoll()
	{
		return roll;
	}
	
	/**
	 * Sets the enrollment cap on for the class
	 * @param cap the number seats in the class
	 */
	public void setEnrollmentCap(int cap)
	{
		if(cap < MIN_ENROLLMENT || cap > MAX_ENROLLMENT)
		{
			throw new IllegalArgumentException();
		}
		if(cap < roll.size()) {
			throw new IllegalArgumentException();
		}
		this.enrollmentCap = cap;
		roll.setCapacity(cap);
	}
	
	/**
	 * Enrolls the student in the class
	 * @param s the student to add into the class
	 */
	public void enroll(Student s)
	{
		if(s == null || roll.size() > enrollmentCap)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			if (roll.size() == enrollmentCap) {
				if(waitList.size() < WAITLIST_SIZE) {
					waitList.enqueue(s);
				}
				else {
					throw new IllegalArgumentException();
				}
				//roll.add(0, s);
			} else {
				roll.add(roll.size(), s);
			}
		}
	}
	
	/**
	 * Gets the number of open seats in the class
	 * @return how many seats are left in the class
	 */
	public int getOpenSeats()
	{
		return enrollmentCap - roll.size();
	}
	
	/**
	 * Drops a student from the class
	 * @param s the student to remove
	 */
	public void drop(Student s)
	{
		if(s == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).equals(s)) {
					roll.remove(i);
					if(!waitList.isEmpty()) {
						Student wait = waitList.dequeue();
						roll.add(roll.size(), wait);
						wait.getSchedule().addCourseToSchedule(course);
					}
				}
				else {
					for (int j = 0; j < waitList.size(); j++) {
						Student inLine = waitList.dequeue();
						if (!s.equals(inLine)) {
							waitList.enqueue(inLine);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Determines if a student can enroll in a specific course
	 * @param s the student to check enrollment status of
	 * @return True if the student can enroll
	 */
	public boolean canEnroll(Student s)
	{
		if (roll.size() >= enrollmentCap)
		{
			return false;
		}
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(s)) {
				return false;
			}
		}
		return true;
		/*if(waitList.size() < WAITLIST_SIZE) {
			enroll = false;
		}*/
		/*for(int i = 0; i < waitList.size(); i++) {
			if (s.equals(waitList.))
		}*/
	}
	
	/**
	 * Returns the enrollment cap for a course
	 * @return the cap
	 */
	public int getEnrollmentCap() {
		return this.enrollmentCap;
	}
	
	/**
	 * gets the number of students on the waitlist
	 * @return the size of the waitlist
	 */
	public int getNumberOnWaitlist() {
		return waitList.size();
	}

}
