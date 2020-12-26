package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Create a course.
 * @author Tianxin Jia
 *
 */
public class Course extends Activity implements Comparable<Course> {
	
	/**
	 * Validator that tests course name
	 */
	private CourseNameValidator validator;
    /** Course's name. */
    private String name;
    /** Course's section. */
    private String section;
    /** Course's credit hours */
    private int credits;
    /** Course's instructor */
    private String instructorId;
    /** The roll of students */
    private CourseRoll roll;
    /**
     * Construct a course object with values for all fields.
     * 
     * @param name         name of Course
     * @param title        title of Course
     * @param section      section of Course
     * @param credits      credit hours for Course
     * @param instructorId instructor's unity id
     * @param meetingDays  meeting days for Course as series of chars
     * @param startTime    start time for course
     * @param endTime      end time for course
     * @param enrollmentCap Enrollmentcap of the course
     */
    public Course(String name, String title, String section,
            int credits, String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
        super(title, meetingDays, startTime, endTime);
        setName(name);
        setSection(section);
        setCredits(credits);
        setInstructorId(instructorId);
        roll = new CourseRoll(this, enrollmentCap);
    }

    
    /**
     * Hashes the information of the Course's name, section, credits, and instructorId
     * 
     * @return the hashed information
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + credits;
        result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((section == null) ? 0 : section.hashCode());
        return result;
    }

    
    /**
     * Compares the this object with the entered object to determine if they are equal
     * 
     * @return	true 	if the objects are the same
     * 			false 	otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        if (credits != other.credits)
            return false;
        if (instructorId == null) {
            if (other.instructorId != null)
                return false;
        } else if (!instructorId.equals(other.instructorId))
            return false; 
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (section == null) {
            if (other.section != null)
                return false;
        } else if (!section.equals(other.section))
            return false;
        return true;
    }

    /**
     * Creates a Course with the given name, title, section, credits, instructorId,
     * and meetingDays for courses that are arranged.
     * 
     * @param name         	name of Course
     * @param title        	title of Course
     * @param section      	section of Course
     * @param credits      	credit hours for Course
     * @param instructorId 	instructor's unity id
     * @param meetingDays	meeting days for Course as series of chars
     * @param enrollmentCap	enrollment cap of the course
     */
    public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
        this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
    }

    /**
     * Returns the Course's name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Course's name. If the name is null, has a length less than 4 or
     * greater than 6, an IllegalArgumentException is thrown.
     * 
     * @param name the name to set
     * @throws InvalidTransitionException 
     * @throws IllegalArgumentException if name is null or length is less than 4 or
     *                                  greater than 6
     */
    private void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        if (name.length() < 4 || name.length() > 6) {
            throw new IllegalArgumentException();
        }

        try {
            this.validator = new CourseNameValidator();
            validator.isValid(name);
            this.name = name;
        } catch (Exception e) {
        	throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the Course's section
     * 
     * @return the section
     */
    public String getSection() {
        return section;
    }

    /**
     * set the Course's section
     * 
     * @param section the section to set
     */
    public void setSection(String section) {
        if (section == null) {
            throw new IllegalArgumentException();
        }
        if (section.length() != 3) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < 2; i++) {
            if (!Character.isDigit(section.charAt(i))) {
                throw new IllegalArgumentException();
            }
        }
        this.section = section;
    }

    /**
     * Returns the Course's credits
     * 
     * @return the credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * set the Course's credits
     * 
     * @param credits the credits to set
     */
    public void setCredits(int credits) {
        if (credits < 1 || credits > 5) {
            throw new IllegalArgumentException();
        }
        this.credits = credits;
    }

    /**
     * Returns the Course's instructor's Id
     * 
     * @return the instructorId
     */
    public String getInstructorId() {
        return instructorId;
    }

    /**
     * set the Course's instructor's Id
     * 
     * @param instructorId the instructorId to set
     */
    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }



    /**
     * set the Course's meeting Days
     * 
     * @param meetingDays the meetingDays to set
     */
    @Override
    public void setMeetingDays(String meetingDays) {
        if (meetingDays == null || meetingDays.length() == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < meetingDays.length(); i++) {
            if (meetingDays.charAt(i) != 'M' && meetingDays.charAt(i) != 'T' && meetingDays.charAt(i) != 'W'
                    && meetingDays.charAt(i) != 'H' && meetingDays.charAt(i) != 'F' && meetingDays.charAt(i) != 'A') {
                throw new IllegalArgumentException();
            }
            if (meetingDays.charAt(i) == 'A' && meetingDays.length() != 1) {
                    throw new IllegalArgumentException();
            }
        }
        super.setMeetingDays(meetingDays);
    }

    /**
     * Returns a comma separated value String of all Course fields.
     * 
     * @return String representation of Course
     */
    @Override
    public String toString() {
        if (getMeetingDays().equals("A")) {
            return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + 
        roll.getOpenSeats() + "," + getMeetingDays();
        }
        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getOpenSeats() + "," + 
        	getMeetingDays() + "," + getStartTime() + "," + getEndTime();
    }

    @Override
    public String[] getShortDisplayArray() {
        String[] sda = new String[5];
        sda[0] = getName();
        sda[1] = getSection();
        sda[2] = getTitle();
        sda[3] = getMeetingString();
        sda[4] = String.valueOf(this.roll.getOpenSeats());
        return sda;
    }

    @Override
    public String[] getLongDisplayArray() {
        String[] lda = new String[7];
        lda[0] = getName();
        lda[1] = getSection();
        lda[2] = getTitle();
        lda[3] = Integer.toString(getCredits());
        lda[4] = getInstructorId();
        lda[5] = getMeetingString();
        lda[6] = "";
        return lda;
    }

    /* (non-Javadoc)
     * @see edu.ncsu.csc216.wolf_scheduler.course.Activity#isDuplicate(edu.ncsu.csc216.wolf_scheduler.course.Activity)
     */
    @Override
    public boolean isDuplicate(Activity activity) {
        if(activity instanceof Course) {
            Course course = (Course) activity;
            if(getName().equals(course.getName())) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

	@Override
	public int compareTo(Course s) {
		SortedList<String> last = new SortedList<String>();
		SortedList<String> unity = new SortedList<String>();
		if (s == null) {
			throw new NullPointerException("the specified object is null");
		}

		try {
			if (!this.getName().equals(s.getName())) {
				last.add(this.getName());
				last.add(s.getName());
				if (last.indexOf(this.getName()) < last.indexOf(s.getName())) {
					return -1;
				} else if (last.indexOf(this.getName()) > last.indexOf(s.getName())) {
					return 1;
				}
			} 
			if (!this.getSection().equals(s.getSection())) {
				unity.add(this.getSection());
				unity.add(s.getSection());
				if (unity.indexOf(this.getSection()) < unity.indexOf(s.getSection())) {
						return -1;
					} else if (unity.indexOf(this.getSection()) > unity.indexOf(s.getSection())) {
						return 1;
					}
				}
		} catch (IllegalArgumentException e) {
			throw new ClassCastException(
					"the specified object's type prevents it from being " + "compared to this object.");
		}
		return 0;
	}
	
	/**
	 * Returns the course roll
	 * @return the course roll
	 */
	public CourseRoll getCourseRoll() {
		return this.roll;
	}
}
