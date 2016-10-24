package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Test methods for the Schedule class
 * @author ajstrapp
 *
 */
public class ScheduleTest {

	/**
	 * test for constructor
	 */
	@Test
	public void testSchedule() {
		Schedule s = new Schedule();
		assertEquals("My Schedule", s.getTitle());
		assertEquals(0, s.getScheduledCourses().length);
	}
	/**
	 * test addCourseToScehdule
	 */
	@Test
	public void testAddCourseToSchedule() {
		Schedule s = new Schedule();
		Course course1 = new Course("CSC216", "Java", "001", 3, "ID", 10, "MTF", 1100, 1300);
		Course course2 = new Course("CSC116", "intro Java", "001", 3, "ID", 10, "MTF", 1300, 1400);
		//add invalid course
		try {
			s.addCourseToSchedule(course1);
		} catch (IllegalArgumentException e) {
			fail();
		}
		//add duplicate course
		try {
			s.addCourseToSchedule(course1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The course cannot be added due to a conflict.", e.getMessage());
		}
		//add time conflict course
		try {
			s.addCourseToSchedule(course2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The course cannot be added due to a conflict.", e.getMessage());
		}
	}
	/**
	 * test for removeCourseToSchedule
	 */
	@Test
	public void testRemoveCourseToSchedule() throws ConflictException {
		Schedule s = new Schedule();
		Course course1 = new Course("CSC216", "Java", "001", 3, "ID", 10, "MTF", 1100, 1300);
		Course course2 = new Course("CSC116", "intro Java", "001", 3, "ID", 10, "MTF", 1500, 1700);
		s.addCourseToSchedule(course1);
		s.addCourseToSchedule(course2);
		assertTrue(s.removeCourseFromSchedule(course1));
		String[][] str = s.getScheduledCourses();
		assertEquals(1, str.length);
		assertEquals("CSC116", str[0][0]);
		assertEquals("001", str[0][1]);
		assertEquals("intro Java", str[0][2]);
		assertEquals("MTF 3:00PM-5:00PM", str[0][3]);
	}
	/**
	 * test for resetSchedule
	 */
	@Test
	public void testResetSchedule() throws ConflictException {
		Schedule s = new Schedule();
		Course course1 = new Course("CSC216", "Java", "001", 3, "ID", 10, "MTF", 1100, 1300);
		Course course2 = new Course("CSC116", "intro Java", "001", 3, "ID", 10, "MTF", 1500, 1700);
		s.addCourseToSchedule(course1);
		s.addCourseToSchedule(course2);
		s.resetSchedule();
		assertEquals(0, s.getScheduledCourses().length);
	}
	/**
	 * test for getScheduledCourses
	 */
	@Test
	public void testGetScheduledCourses() throws ConflictException {
		Schedule s = new Schedule();
		Course course1 = new Course("CSC216", "Java", "001", 3, "ID", 10, "MTF", 1100, 1300);
		Course course2 = new Course("CSC116", "intro Java", "001", 3, "ID", 10, "MTF", 1500, 1700);
		s.addCourseToSchedule(course1);
		s.addCourseToSchedule(course2);
		String[][] str = s.getScheduledCourses();
		assertEquals(2, str.length);
		assertEquals("CSC116", str[1][0]);
		assertEquals("001", str[1][1]);
		assertEquals("intro Java", str[1][2]);
		assertEquals("MTF 3:00PM-5:00PM", str[1][3]);
		assertEquals("CSC216", str[0][0]);
		assertEquals("001", str[0][1]);
		assertEquals("Java", str[0][2]);
		assertEquals("MTF 11:00AM-1:00PM", str[0][3]);

	}
	/**
	 * test for setTitle
	 */
	@Test
	public void testSetTitle() {
		Schedule s = new Schedule();
		assertEquals("My Schedule", s.getTitle());
		s.setTitle("Testing title");
		assertEquals("Testing title", s.getTitle());
	}
	/**
	 * test for getScheduleCredits
	 */
	@Test
	public void testGetScheduleCredits() {
		Schedule s = new Schedule();
		SortedList<Course> courses = null;
		try {
			courses = CourseRecordIO.readCourseRecords("test-files/course_records.txt");
		} catch (FileNotFoundException e) {
			fail();
		}
		for (int i = 0; i < courses.size(); i++) {
			try {
				s.addCourseToSchedule(courses.get(i));
			} catch (IllegalArgumentException e) {
				// do nothing
			}
		}
		
		assertEquals(10, s.getScheduleCredits());
	}
	/**
	 * test for canAdd
	 */
	@Test
	public void testCanAdd() {
		Schedule s = new Schedule();
		SortedList<Course> courses = null;
		try {
			courses = CourseRecordIO.readCourseRecords("test-files/course_records.txt");
		} catch (FileNotFoundException e) {
			fail();
		}
		for (int i = 0; i < courses.size(); i++) {
			try {
				s.addCourseToSchedule(courses.get(i));
			} catch (IllegalArgumentException e) {
				// do nothing
			}
		}
		
		assertFalse(s.canAdd(null));
		assertFalse(s.canAdd(courses.get(0)));
		assertFalse(s.canAdd(courses.get(1)));
		assertFalse(s.canAdd(courses.get(7)));
		
		s.removeCourseFromSchedule(courses.get(0));
		
		assertTrue(s.canAdd(courses.get(0)));
	}
}