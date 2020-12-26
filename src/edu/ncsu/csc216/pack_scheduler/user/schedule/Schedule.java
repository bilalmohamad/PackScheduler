package edu.ncsu.csc216.pack_scheduler.user.schedule;



import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Schedule class for users
 * @author Qihao Lu
 *
 */
public class Schedule {
	
	/**
	 * a custom ArrayList of courses
	 */
	private ArrayList<Course> schedule;
	/**
	 * title of schedule
	 */
	private String title;
	
	/**
	 * constructor for Schedule 
	 * The constructor of Schedule should create an empty ArrayList of Courses. 
	 * The title should be initialized to my Schedule.
	 */
	public Schedule() {
		this.schedule = new ArrayList<Course>();
		this.title = "My Schedule";
		
	}
	
	/**
	 * add add the Course to the end of the schedule and return true if the Course was added.
	 * @param c course that is going to be added
	 * @return true if the course is successfully added
	 * @throws IllegalArgumentException if conflict or already same course exists
	 */
	public boolean addCourseToSchedule(Course c) {
		Course o;
		for(int i = 0; i < schedule.size(); i++) {
			o = schedule.get(i);
			if(o.isDuplicate(c)) {
				throw new IllegalArgumentException("You are already enrolled in " + c.getName());
			}
			try {
				o.checkConflict(c);
			} catch (ConflictException e) {
				throw new IllegalArgumentException();
			}
		}
		this.schedule.add(c);
		return true;
	}
	
	/**
	 * remove course from schedule
	 * @param c course that is going to be removed
	 * @return true if removed
	 */
	public boolean removeCourseFromSchedule(Course c) {
		if(schedule == null) {
			throw new IllegalArgumentException();
		}
		if(!schedule.contains(c)) {
			return false;
		} else {
			schedule.remove(c);
			return true;
		}
	}
	
	/**
	 * create a new schedule ArrayList and reset the title to the default value.
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<Course>();
		this.title =  "My Schedule";
	}
	
	/**
	 * should return a 2D array of Course information.
	 *  Each row should be a Course and the columns are name, section, title, and the meeting string
	 * @return a 2D array of Course information
	 */
	public String[][] getScheduledCourses(){
		String[][] s = new String[schedule.size()][5];
		String[] sda = new String[4];
		for(int i = 0; i < schedule.size(); i++) {
			
			sda = schedule.get(i).getShortDisplayArray();
			for(int j = 0; j < 4; j++) {
				s[i][0] = sda[0].toString();
				s[i][1] = sda[1].toString();
				s[i][2] = sda[2].toString();
				s[i][3] = sda[3].toString();
				s[i][4] = sda[4].toString();
			}
		}
		
		return s;
	}
	
	/**
	 * set the schedule's title 
	 * @param title willing title
	 */
	public void setTitle(String title) {
		if(title == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		this.title = title;
	}
	
	/**
	 * return the schedule's title
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Calculates the total number of credits a schedule is worth
	 * @return the number of creds a schedule is worth
	 */
	public int getScheduleCredits() {
		int total = 0;
		for (int i = 0; i < schedule.size(); i++) {
			total = total + schedule.get(i).getCredits();
		}
		return total;
	}
	
	/**
	 * Checks to see if the student can be added to a class
	 * @param c gets the course that a student wants to get into
	 * @return if they can or cannot be added to the class
	 */
	public boolean canAdd(Course c) {
		boolean canAdd = true;
		if (c == null) {
			canAdd = false;
		}
		Course o;
		for(int i = 0; i < schedule.size(); i++) {
			o = schedule.get(i);
			if(o.isDuplicate(c)) {
				canAdd = false;
			}
			try {
				o.checkConflict(c);
			} catch (ConflictException e) {
				canAdd = false;
			}
		}
		return canAdd;
	}
}
