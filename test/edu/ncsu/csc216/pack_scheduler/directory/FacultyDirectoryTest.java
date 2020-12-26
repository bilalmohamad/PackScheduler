package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the FacultyDirectory class
 * @author Bilal Mohamad, Qihao Lu, Tj Tutka
 *
 */
public class FacultyDirectoryTest {

	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";

	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Fac";
	/** Test last name */
	private static final String LAST_NAME = "Ulty";
	/** Test id */
	private static final String ID = "fulty";
	/** Test email */
	private static final String EMAIL = "fulty@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 2;

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception
	 *             if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests StudentDirectory().
	 */
	@Test
	public void testStudentDirectory() {
		// Test that the StudentDirectory is initialized to an empty list
		FacultyDirectory fd = new FacultyDirectory();
		assertFalse(fd.removeFaculty("sesmith5"));
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests StudentDirectory.testNewStudentDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		// Test that if there are students in the directory, they
		// are removed after calling newStudentDirectory().
		FacultyDirectory sd = new FacultyDirectory();

		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);

		sd.newFacultyDirectory();
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile().
	 */
	@Test
	public void testLoadStudentsFromFile() {
		FacultyDirectory sd = new FacultyDirectory();

		// Test valid file
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);

		try {
			sd.loadFacultyFromFile(invalidTestFile);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests StudentDirectory.addStudent().
	 */
	@Test
	public void testAddStudent() {
		FacultyDirectory sd = new FacultyDirectory();

		// Test valid Student
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		assertFalse(sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		String[][] studentDirectory = sd.getFacultyDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
	}

	/**
	 * Tests StudentDirectory.removeStudent().
	 */
	@Test
	public void testRemoveStudent() {
		FacultyDirectory sd = new FacultyDirectory();

		// Add students and remove
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		assertTrue(sd.removeFaculty("awitt"));
		String[][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(7, facultyDirectory.length);
		assertEquals("Brent", facultyDirectory[1][0]);
		assertEquals("Brewer", facultyDirectory[1][1]);
		assertEquals("bbrewer", facultyDirectory[1][2]);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory().
	 */
	@Test
	public void testSaveStudentDirectory() {
		FacultyDirectory sd = new FacultyDirectory();

		// Add a student
		sd.addFaculty("Ashely", "King", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
		assertEquals(1, sd.getFacultyDirectory().length);
		sd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		checkFiles("test-files/expected_faculty_records.txt", "test-files/faculty_records.txt");
	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile
	 *            expected output
	 * @param actFile
	 *            actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
