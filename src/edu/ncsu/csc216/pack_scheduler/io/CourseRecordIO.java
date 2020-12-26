package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * 
 * @author Sarah Heckman
 * @author Bilal Mohamad, Tj Tutka, Qihao Lu
 */
public class CourseRecordIO {

	/**
	 * The constructor for the CourseRecordIO class. Does nothing
	 */
	public CourseRecordIO() {
		// Does nothing
	}

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Course> courses = new SortedList<Course>();
		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
					Course c = courses.get(i);
					if (course.getName().equals(c.getName()) && course.getSection().equals(c.getSection())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch (Exception e) {
				//
			}
		}
		fileReader.close();
		return courses;
	}

	/**
	 * Receive a string and use "," as a delimiter, then create a course object for
	 * the string. NoSuchElementException is caught and IllegalArgumentException is
	 * thrown.
	 * 
	 * @param nextLine line string that is received
	 * @return course object for the line string
	 */
	public static Course readCourse(String nextLine) {
		Course course = null;
		String instructorId = null;
		Scanner s = new Scanner(nextLine);
		s.useDelimiter(",");
		try {
			String name = s.next();
			String title = s.next();
			String section = s.next();
			int credits = s.nextInt();
			instructorId = null;
			if (!s.hasNextInt()) {
				instructorId = s.next();
			}
			int enrollmentCap = s.nextInt();
			String meetingDays = s.next();
			if (meetingDays.equals("A") && s.hasNext()) {
					s.close();
					extracted();
			}
				if (!meetingDays.equals("A") && !s.hasNextInt()) {
					s.close();
					extracted();
				}

				if (!meetingDays.equals("A") && s.hasNextInt()) {
					int startTime = s.nextInt();
					if (startTime == 0) {
						s.close();
						extracted();
					}
					// Check if there's an end time//
					if (!s.hasNextInt()) {
						s.close();
						extracted();
					} else {
						int endTime = s.nextInt();
						s.close();
						course = new Course(name, title, section, credits, null, enrollmentCap, meetingDays, startTime,
								endTime);

						Faculty f = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId);
						if (f != null) {
							f.getSchedule().addCourseToSchedule(course);
						}
						return course; 
					}
				}
				s.close();
				course = new Course(name, title, section, credits, null, enrollmentCap, meetingDays);

				Faculty f = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId);
				if (f != null) {
					f.getSchedule().addCourseToSchedule(course);
				}

				return course;

			} catch (NoSuchElementException e) {
				s.close();
				throw new IllegalArgumentException();
			} catch (IllegalArgumentException e) {
				s.close();
				throw new IllegalArgumentException();
			}
	}

	/**
	 * For better management of same code
	 * 
	 * @throw new IllegalArgumentException
	 */
	private static void extracted() {
		throw new IllegalArgumentException();
	}

	/**
	 * Writes the given list of Courses to
	 * 
	 * @param fileName output file name
	 * @param courses  expected array list
	 * @throws IOException when file not found
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();

	}

}