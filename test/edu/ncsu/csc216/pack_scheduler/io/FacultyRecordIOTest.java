package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Tests the FacultyRecordIO class
 * 
 * @author Bilal Mohamad, Tj Tutka, Qihao Lu
 *
 */
public class FacultyRecordIOTest {

	/** Invalid test file */
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";
	
	/** Expected results for valid Faculty members */
	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
	private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
	private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
	private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
	private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";
	
	/** Array to hold expected results */
	private String[] validFaculty = { validFaculty0, validFaculty1, validFaculty2, validFaculty3, validFaculty4,
			validFaculty5, validFaculty6, validFaculty7};
	
	/** hash code of password */
	private String hashPW;
	/** Default hash code */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Tests the construction of a FacultyRecordIO object */
	FacultyRecordIO fr = new FacultyRecordIO();
	
	
	/**
	 * Resets faculty_records.txt for use in other tests.
	 * 
	 * @throws Exception if catch a NoSuchAlgorithmException
	 */
	@Before
	public void setUp() throws Exception {
		try {
			String password = "pw";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			hashPW = new String(digest.digest());

			for (int i = 0; i < validFaculty.length; i++) {
				validFaculty[i] = validFaculty[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}
	
	
	/**
	 * Tests the readFacultyRecords method with an invalid parameter
	 * @throws InvalidTransitionException if the transition was not valid
	 */
	@Test
	public void testReadInvalidFacultyRecords() throws InvalidTransitionException {
		SortedList<Course> courses;
		try {
			courses = CourseRecordIO.readCourseRecords(invalidTestFile);
			assertEquals(0, courses.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}
}
