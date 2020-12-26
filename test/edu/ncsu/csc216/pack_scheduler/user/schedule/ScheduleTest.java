package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
/**
 * test for Schedule
 * @author Qihao Lu
 *
 */
public class ScheduleTest {
	
	/**
	 * test constructor
	 */
	@Test
	public void testSchedule() {
		Schedule s = new Schedule();
		assertEquals(s.getTitle(), "My Schedule");
	}
	
	
	
	
	/**
	 * test addCourseToSchedule
	 */
	@Test
	public void testAdd() {
		Schedule s = new Schedule();
		Course c = new Course("name", "title", "001", 1, "id", 10, "A");
		assertTrue(s.addCourseToSchedule(c));
		try {
			s.addCourseToSchedule(c);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "You are already enrolled in name");
		}
	}
	
	/**
	 * test removeCourseFromSchedule
	 */
	@Test
	public void testRemove() {
		Schedule s = new Schedule();
		Course c = new Course("name", "title", "001", 1, "id", 10, "A");
		assertFalse(s.removeCourseFromSchedule(c));
		assertTrue(s.addCourseToSchedule(c));
		assertTrue(s.removeCourseFromSchedule(c));
	}
	
	/**
	 * test resetSchedule
	 */
	@Test
	public void testReset() {
		Schedule s = new Schedule();
		Course c = new Course("name", "title", "001", 1, "id", 10, "A");
		assertFalse(s.removeCourseFromSchedule(c));
		assertTrue(s.addCourseToSchedule(c));
		s.resetSchedule();
		assertTrue(s.addCourseToSchedule(c));
	}
	
	/**
	 * test getScheduledCourse
	 */
	@Test
	public void testGetScheduledCourse() {
		Schedule s = new Schedule();
		Course c = new Course("name", "title", "001", 1, "id", 10, "A");
		assertFalse(s.removeCourseFromSchedule(c));
		assertTrue(s.addCourseToSchedule(c));
		String[][] str = s.getScheduledCourses();
		String ccc = str[0][0].toString(); 
		assertEquals(ccc, "name");
		ccc = str[0][1].toString(); 
		assertEquals(ccc, "001");
		ccc = str[0][2].toString(); 
		assertEquals(ccc, "title");
		ccc = str[0][3].toString(); 
		assertEquals(ccc, "Arranged");
		ccc = str[0][4].toString();
		assertEquals("10", ccc);
	}
	
	/**
	 * test getScheduledCourse
	 */
	@Test
	public void testSetTitle() {
		Schedule s = new Schedule();
		assertEquals(s.getTitle(), "My Schedule");
		s.setTitle("1");
		assertEquals(s.getTitle(), "1");
	}
	
	/**
	 * Tests the canAdd method
	 */
	@Test
	public void testCanAdd() {
		Schedule s = new Schedule();
		Course c = new Course("name", "title", "001", 1, "id", 10, "A");
		Course c2 = new Course("name1", "title", "002", 1, "id", 10, "A");
		assertTrue(s.addCourseToSchedule(c));
		assertFalse(s.canAdd(c));
		assertTrue(s.canAdd(c2));
		assertTrue(s.addCourseToSchedule(c2));
		assertEquals(2, s.getScheduleCredits());
	}
}
