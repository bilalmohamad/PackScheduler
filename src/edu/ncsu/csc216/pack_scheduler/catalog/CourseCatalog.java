package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;
import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;

import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * maintain a catalog of Course, and students can add and remove course in the
 * catalog
 * 
 * @author yueyu
 *
 */
public class CourseCatalog {
	/** List of Course in the catalog */
	private SortedList<Course> catalog;

	/**
	 * Creates an empty student directory.
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}

	/**
	 * Creates an empty student directory. All students in the previous list are
	 * list unless saved by the user.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Constructs the student directory by reading in student information from the
	 * given file. Throws an IllegalArgumentException if the file cannot be found.
	 * 
	 * @param fileName
	 *            file containing list of students
	 */
	public void loadCoursesFromFile(String fileName) {
		//SortedList<Course> loadCourse = new SortedList<Course>();
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}

	}

	/**
	 * Adds a Course to the catalog. Returns true if the Course is added and false
	 * if the Course is unable to be added because the class has already existed
	 * 
	 * This method also hashes the student's password for internal storage.
	 * 
	 * @param name
	 *            Course name
	 * @param title
	 *            Course title
	 * @param section
	 *            Course section
	 * @param credits
	 *            Course credits
	 * @param instructorID
	 *            instructor ID for this course
	 * @param meetingDays
	 *            Course meeting days
	 * @param startTime
	 *            Course start time
	 * @param endTime
	 *            Course end time
	 * @param enrollmentCap 
	 * 			  Enrollmentcap of the class
	 * @return true if added
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorID,
			int enrollmentCap, String meetingDays, int startTime, int endTime) {
		Course c = new Course(name, title, section, credits, instructorID, enrollmentCap, meetingDays, startTime, endTime);
		if (getCourseFromCatalog(c.getName(), c.getSection()) == null) {
			catalog.add(c);
			return true;
		}
		return false;

	}

	/**
	 * Removes the Course with the given name and section. Returns true if the
	 * Course is removed and false if the Course is not in the list.
	 * 
	 * @param name
	 *            Course name
	 * @param section
	 *            Course section
	 * @return true if removed
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course s = catalog.get(i);
			if (s.getName().equals(name) && s.getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * get a course from Catalog by the name and section
	 * 
	 * @param name
	 *            name the Course need to search from Catalog
	 * @param section
	 *            section the Course need to search from Catalog
	 * @return the Course find out from the Catalog
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}

	/**
	 * Returns all Course in the directory with a column for name, section, title,
	 * meetingString
	 * 
	 * @return String array containing Course name, section, title, and
	 *         meetingString.
	 */
	public String[][] getCourseCatalog() {
		String[][] arr = new String[catalog.size()][5];
		for (int i = 0; i < catalog.size(); i++) {
			arr[i] = new String[] { catalog.get(i).getName(), catalog.get(i).getSection(), catalog.get(i).getTitle(),
					catalog.get(i).getMeetingString(), Integer.toString(catalog.get(i).getCourseRoll().getOpenSeats()) };
		}
		return arr;
	}

	/**
	 * Saves all Courses in the catalog to a file.
	 * 
	 * @param fileName
	 *            name of file to save Courses to.
	 */
	public void saveCourseCatalog(String fileName) {
		SortedList<Course> addCourse = new SortedList<Course>();
		for (int i = 0; i < catalog.size(); i++) {
			addCourse.add(catalog.get(i));
		}
		try {
			CourseRecordIO.writeCourseRecords(fileName, addCourse);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

}
