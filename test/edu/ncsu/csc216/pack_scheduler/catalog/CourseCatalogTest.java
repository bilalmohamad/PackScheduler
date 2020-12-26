package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * class used to test CourseCatalog
 * 
 * @author yueyu
 *
 */
public class CourseCatalogTest {
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";

	/** The Constant ACTUAL_COURSE_RECORDS_STRING. */
	public static final String[][] ACTUAL_COURSE_RECORDS_STRING = new String[][] {
			new String[] { "CSC116", "003", "Intro to Programming - Java", "MW 12:50PM-2:40PM", "10" },
			new String[] { "CSC216", "001", "Programming Concepts - Java", "MW 1:30PM-2:45PM", "10" },
			new String[] { "CSC216", "601", "Programming Concepts - Java", "Arranged", "10" } };

	/** The Constant ACTUAL_COURSE_RECORDS_PATH. */
	public static final String ACTUAL_COURSE_RECORDS_PATH = "test-files/actual_course_records.txt";

	/** The Constant FAKE_COURSE_RECORDS_PATH. */
	public static final String FAKE_COURSE_RECORDS_PATH = "test-files/no-file-here.lol";

	/** The Constant COURSE_ADD_NAME. */
	public static final String COURSE_ADD_NAME = "CSC216";

	/** The Constant COURSE_ADD_TITLE. */
	public static final String COURSE_ADD_TITLE = "Programming Concepts - Java";

	/** The Constant COURSE_ADD_SECTION. */
	public static final String COURSE_ADD_SECTION = "001";

	/** The Constant COURSE_ADD_CREDITS. */
	public static final int COURSE_ADD_CREDITS = 3;

	/** The Constant COURSE_ADD_INSTRUCTOR_ID. */
	public static final String COURSE_ADD_INSTRUCTOR_ID = "Professor Namesworth";

	/** The Constant COURSE_ADD_MEETING_DAYS. */
	public static final String COURSE_ADD_MEETING_DAYS = "MW";

	/** The Constant COURSE_ADD_TIME_START. */
	public static final int COURSE_ADD_TIME_START = 1330;

	/** The Constant COURSE_ADD_TIME_END. */
	public static final int COURSE_ADD_TIME_END = 1445;

	/** The Constant COURSE_ADD_STRING. */
	public static final String[] COURSE_ADD_STRING = new String[] { "CSC216", "001", "Programming Concepts - Java",
			"MW 1:30PM-2:45PM", "10"};
	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception
	 *             if catch a IOException
	 */
	@Before
	public void setUp() throws Exception {
		// Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests CourseCatalog().
	 */
	@Test
	public void testCourseCatalog() {
		// Test with invalid file. Should have an empty catalog and schedule.
		CourseCatalog sd = new CourseCatalog();
		assertFalse(sd.removeCourseFromCatalog("sesmith5", "001"));
		assertEquals(0, sd.getCourseCatalog().length);
	}

	/**
	 * Test CourseCatalog.getCourseFromCatalog().
	 * @throws InvalidTransitionException 
	 */
//	@Test
//	public void testGetCourseFromCatalog() throws InvalidTransitionException {
//		CourseCatalog ws = new CourseCatalog();
//		ws.loadCoursesFromFile(validTestFile);
//
//		// Attempt to get a course that doesn't exist
//		assertNull(ws.getCourseFromCatalog("CSC492", "001"));
//
//		// Attempt to get a course that does exist
//		Course c = new Course(COURSE_ADD_NAME, COURSE_ADD_TITLE, COURSE_ADD_SECTION, COURSE_ADD_CREDITS,
//				COURSE_ADD_INSTRUCTOR_ID, 10, COURSE_ADD_MEETING_DAYS, COURSE_ADD_TIME_START, COURSE_ADD_TIME_END);
//		assertFalse(c);
//	}

	/**
	 * Test WolfScheduler.addCourseToCatalog().
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void testAddCourseToCatalog() throws InvalidTransitionException {
		CourseCatalog cc = new CourseCatalog();
		assertTrue(cc.addCourseToCatalog(COURSE_ADD_NAME, COURSE_ADD_TITLE, COURSE_ADD_SECTION, COURSE_ADD_CREDITS,
				COURSE_ADD_INSTRUCTOR_ID, 10, COURSE_ADD_MEETING_DAYS, COURSE_ADD_TIME_START, COURSE_ADD_TIME_END));
		String[][] ccString = cc.getCourseCatalog();
		assertEquals(ccString.length, 1);
		for (int j = 0; j < ccString[0].length; j++) {
			assertEquals(ccString[0][j], COURSE_ADD_STRING[j]);
		}
		assertFalse(cc.addCourseToCatalog(COURSE_ADD_NAME, COURSE_ADD_TITLE, COURSE_ADD_SECTION, COURSE_ADD_CREDITS,
				COURSE_ADD_INSTRUCTOR_ID, 10, COURSE_ADD_MEETING_DAYS, COURSE_ADD_TIME_START, COURSE_ADD_TIME_END));
	}

	/**
	 * Test CourseCatalog.removeCourseFromCatalog().
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void testRemoveCourseFromCatalog() throws InvalidTransitionException {
		CourseCatalog cc = new CourseCatalog();
		cc.addCourseToCatalog(COURSE_ADD_NAME, COURSE_ADD_TITLE, COURSE_ADD_SECTION, COURSE_ADD_CREDITS,
				COURSE_ADD_INSTRUCTOR_ID, 100, COURSE_ADD_MEETING_DAYS, COURSE_ADD_TIME_START, COURSE_ADD_TIME_END);
		assertTrue(cc.removeCourseFromCatalog(COURSE_ADD_NAME, COURSE_ADD_SECTION));
		assertEquals(cc.getCourseCatalog().length, 0);
		assertFalse(cc.removeCourseFromCatalog(COURSE_ADD_NAME, COURSE_ADD_SECTION));

	}

	/**
	 * Test CourseCatalog.getCourseCatalog().
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void testGetCourseCatalog() throws InvalidTransitionException {
		CourseCatalog ws = new CourseCatalog();
		ws.loadCoursesFromFile(validTestFile);

		// Get the catalog and make sure contents are correct
		// Name, section, title
		String[][] catalog = ws.getCourseCatalog();
		// Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		assertEquals("MW 9:10AM-11:00AM", catalog[0][3]);
		// Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		assertEquals("MW 11:20AM-1:10PM", catalog[1][3]);
		// Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		assertEquals("TH 11:20AM-1:10PM", catalog[2][3]);
		// Row 3
		assertEquals("CSC216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Programming Concepts - Java", catalog[3][2]);
		assertEquals("TH 1:30PM-2:45PM", catalog[3][3]);
		// Row 4
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Programming Concepts - Java", catalog[4][2]);
		assertEquals("MW 1:30PM-2:45PM", catalog[4][3]);
		// Row 5
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Programming Concepts - Java", catalog[5][2]);
		assertEquals("Arranged", catalog[5][3]);
		// Row 6
		assertEquals("CSC226", catalog[6][0]);
		assertEquals("001", catalog[6][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[6][2]);
		assertEquals("MWF 9:35AM-10:25AM", catalog[6][3]);
		// Row 7
		assertEquals("CSC230", catalog[7][0]);
		assertEquals("001", catalog[7][1]);
		assertEquals("C and Software Tools", catalog[7][2]);
		assertEquals("MW 11:45AM-1:00PM", catalog[7][3]);
	}

	/**
	 * Test CourseCatalog.saveCourseCatalog().
	 * @throws InvalidTransitionException if the class attempts to make an invalid transition
	 */
	@Test
	public void testSaveCourseCatalog() throws InvalidTransitionException {
		CourseCatalog cd = new CourseCatalog();

		// Add courses
		cd.addCourseToCatalog(COURSE_ADD_NAME, COURSE_ADD_TITLE, COURSE_ADD_SECTION, COURSE_ADD_CREDITS,
				COURSE_ADD_INSTRUCTOR_ID, 10, COURSE_ADD_MEETING_DAYS, COURSE_ADD_TIME_START, COURSE_ADD_TIME_END);
		assertEquals(1, cd.getCourseCatalog().length);
		cd.saveCourseCatalog("test-files/actual_course_records.txt");

		try {
			cd.saveCourseCatalog("/home/sesmith5/actual_course_records.txt");

		} catch (IllegalArgumentException e) {
			assertEquals(1, cd.getCourseCatalog().length);
		}
	}
}
