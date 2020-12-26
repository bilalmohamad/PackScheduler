package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests COurseRoll
 * @author Kyle Kleinke
 *
 */
public class CourseRollTest {
	
	/**
	 * Tests the constructor
	 */
	@Test
	public void testCourseRoll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 20, "A");
//		CourseRoll roll = new CourseRoll(c, 20);
		assertEquals(20, c.getCourseRoll().getEnrollmentCap());
	}
	
//	/**
//	 * Tests getting the roll
//	 */
//	@Test
//	public void testGetCourseRoll() {
//		Course c1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 20, "A");
//		Course c2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
////		CourseRoll roll = new CourseRoll(c1, 20);
////		CourseRoll real = new CourseRoll(c2, 10);
//		assertEquals(c1.getCourseRoll(), c2.getCourseRoll().get);
//	}
	
	/**
	 * Tests setting the enrollment cap
	 */
	@Test
	public void testSetEnrollmentCap() {
		CourseRoll roll = null;
		try {
			Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 5, "A");
			roll = c.getCourseRoll();
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(roll);
		}
		
		try {
			Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 5, "A");
			roll = c.getCourseRoll();
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(roll);
		}
	}
	
	/**
	 * Tests enrolling students
	 */
	@Test
	public void testEnroll() {
		Student s = new Student("Kyle", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s1 = new Student("Kylee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s2 = new Student("Kyleee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s3 = new Student("Kyleeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s4 = new Student("Kyleeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s5 = new Student("Kyleeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s6 = new Student("Kyleeeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s7 = new Student("Kyleeeeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s8 = new Student("Kyleeeeeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s9 = new Student("Kyleeeeeeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s10 = new Student("Kyleeeeeeeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student n = null;
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		try {
			cr.enroll(n);
		} catch (IllegalArgumentException e) {
			assertEquals(10, cr.getEnrollmentCap());
		}
		cr.enroll(s);
		assertTrue(cr.getCourseRoll().contains(s));
		cr.enroll(s1);
		assertTrue(cr.getCourseRoll().contains(s1));
		cr.enroll(s2);
		cr.enroll(s3);
		cr.enroll(s4);
		cr.enroll(s5);
		cr.enroll(s6);
		cr.enroll(s7);
		cr.enroll(s8);
		cr.enroll(s9);
		assertEquals(0, cr.getOpenSeats());
		cr.enroll(s10);
		assertEquals(0, cr.getOpenSeats());
		cr.drop(s9);
		/*try {
			cr.enroll(s10);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(10, cr.getEnrollmentCap());
		}*/
//		assertEquals(0, cr.getOpenSeats());
	}
	
	/**
	 * Tests dropping student
	 */
	@Test
	public void testDrop() {
		Student s = new Student("Kyle", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s1 = new Student("Kylee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s2 = new Student("Kyleee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s3 = new Student("Kyleeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s4 = new Student("Kyleeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s5 = new Student("Kyleeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s6 = new Student("Kyleeeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s7 = new Student("Kyleeeeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s8 = new Student("Kyleeeeeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s9 = new Student("Kyleeeeeeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student n = null;
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll(); 
		try {
			cr.drop(n);
		} catch (IllegalArgumentException e) {
			assertEquals(10, cr.getEnrollmentCap());
		}
		cr.enroll(s);
		assertTrue(cr.getCourseRoll().contains(s));
		cr.enroll(s1);
//		assertTrue(cr.getCourseRoll().contains(s1));
		cr.enroll(s2);
		cr.enroll(s3);
		cr.enroll(s4);
		cr.enroll(s5);
		cr.enroll(s6);
		cr.enroll(s7);
		cr.enroll(s8);
		cr.enroll(s9);
		cr.drop(s);
//		assertEquals(1, cr.getOpenSeats());
	}
	
	/**
	 * Tests if student can enroll
	 */
	@Test
	public void testCanEnroll() {
		Student s = new Student("Kyle", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s1 = new Student("Kylee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s2 = new Student("Kyleee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s3 = new Student("Kyleeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s4 = new Student("Kyleeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s5 = new Student("Kyleeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s6 = new Student("Kyleeeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s7 = new Student("Kyleeeeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s8 = new Student("Kyleeeeeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Student s9 = new Student("Kyleeeeeeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		//Student s10 = new Student("Kyleeeeeeeeeee", "Kleinke", "klkleink", "klkleink@ncsu.edu", "pw", 18);
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		cr.enroll(s);
		assertTrue(cr.getCourseRoll().contains(s));
		cr.enroll(s1);
//		assertTrue(cr.getCourseRoll().contains(s1));
		cr.enroll(s2);
		cr.enroll(s3);
		cr.enroll(s4);
		cr.enroll(s5);
		cr.enroll(s6);
		cr.enroll(s7);
		cr.enroll(s8);
		assertTrue(cr.canEnroll(s9));
		//assertFalse(cr.canEnroll(s8));
		cr.enroll(s9);
		assertEquals(cr.getNumberOnWaitlist(), 0);
//		assertFalse(cr.canEnroll(s10));
	} 


}
