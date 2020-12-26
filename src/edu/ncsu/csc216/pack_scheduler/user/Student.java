package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * class represent a individual student record
 * 
 * @author yueyu 
 * @author Tianxin Jia  
 * @author Qihao Lu
 *
 */
public class Student extends User implements Comparable<Student> {
	/** Student schedule*/
	private Schedule schedule;
	/** student max credits87 */
	private int maxCredits;
	/** constant of the max credits a student can have */
	public static final int MAX_CREDITS = 18;

	/**
	 * Constructor used to create a student with maxCredits
	 * 
	 * @param firstName
	 *            firstName of the student
	 * @param lastName
	 *            lastName of the student
	 * @param id
	 *            id of the student
	 * @param email
	 *            email of the student
	 * @param password
	 *            password of the student
	 * @param maxCredits
	 *            max credits of the student
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits)  {
		super(firstName, lastName, id, email, password);
		try {
			setFirstName(firstName);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid first name");
		}
		try {
			setLastName(lastName);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid last name");
		}
		try {
			setId(id);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid id");
		}
		try {
			setEmail(email);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid email");
		}
		try {
			setPassword(password);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid password");
		}
		try {
			setMaxCredits(maxCredits);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.schedule = new Schedule();

	}

	/**
	 * create a student by given firstName, lastName, id, email, password and
	 * constant MaxCredits
	 * 
	 * @param firstName
	 *            firstName of the student
	 * @param lastName
	 *            lastName of the student
	 * @param id
	 *            id of the student
	 * @param email
	 *            email of the student
	 * @param password
	 *            password of the student
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, 18); 
		this.schedule = new Schedule();
	}

	/**
	 * get the student maxCredits
	 * 
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * set the student max Credits of Courses
	 * 
	 * @param maxCredits
	 *            the maxCredits to set
	 * @throws IllegalArgumentException
	 *             if the maxCredits is less than 3 or greater than 18
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > 18) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Returns a comma separated value String of all Student fields.
	 * 
	 * @return String representation of Student
	 */
	@Override
	public String toString() {
		String studentRecord;
		studentRecord = getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + this.maxCredits;
		return studentRecord;  
	}
	
	/**
	 * compare two object with order in last name, first name, and unity id return
	 * an integer that is negative, 0, or positive, which means less than, equal to,
	 * or greater than
	 * 
	 * @param s
	 *            object that compare with
	 * @return an integer represents less than, equal to, or greater than
	 */
	public int compareTo(Student s) {
		SortedList<String> first = new SortedList<String>();
		SortedList<String> last = new SortedList<String>();
		SortedList<String> unity = new SortedList<String>();
		if (s == null) {
			throw new NullPointerException("the specified object is null");
		}

		try {
			if (!this.getLastName().equals(s.getLastName())) {
				last.add(this.getLastName());
				last.add(s.getLastName());
				if (last.indexOf(this.getLastName()) < last.indexOf(s.getLastName())) {
					return -1;
				} else if (last.indexOf(this.getLastName()) > last.indexOf(s.getLastName())) {
					return 1;
				}
			} else if (this.getLastName().equals(s.getLastName())) {
				if (!this.getFirstName().equals(s.getFirstName())) {
					first.add(this.getFirstName());
					first.add(s.getFirstName());
					if (first.indexOf(this.getFirstName()) < first.indexOf(s.getFirstName())) {
						return -1;
					} else if (first.indexOf(this.getFirstName()) > first.indexOf(s.getFirstName())) {
						return 1;
					}
				} else {
					if (!this.getId().equals(s.getId())) {
						unity.add(this.getId());
						unity.add(s.getId());
						if (unity.indexOf(this.getId()) < unity.indexOf(s.getId())) {
							return -1;
						} else if (unity.indexOf(this.getId()) > unity.indexOf(s.getId())) {
							return 1;
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			throw new ClassCastException(
					"the specified object's type prevents it from being " + "compared to this object.");
		}
		return 0;
	}

	
	/**
	 * Hashes the faculty member's information
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
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
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}
	
	/**
	 * get student's schedule
	 * @return student's schedule
	 */
	public Schedule getSchedule() {
		return this.schedule;
	}
	
	/**
	 * Checks to see if a course can be added to a student schedule
	 * @param c the course in question
	 * @return true if it can
	 */
	public boolean canAdd(Course c) {
		if(!this.schedule.canAdd(c)) {
			return false;
		}
		if(this.schedule.getScheduleCredits() + c.getCredits() > maxCredits) {
			return false;
		}
		return true;
	}
	
}
