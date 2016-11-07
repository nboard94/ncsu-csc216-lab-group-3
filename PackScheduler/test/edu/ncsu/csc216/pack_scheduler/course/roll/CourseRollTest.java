package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
/**
 * Tests the CourseRoll class
 * 
 * @author xrao
 * @author ajstrapp
 * @author jtirene
 */
public class CourseRollTest {
	Student s1 = new Student("f1", "l1", "id1", "id1@ncsu.edu", "pw", 16);
	Student s2 = new Student("f2", "l2", "id2", "id2@ncsu.edu", "pw", 16);
	Student s3 = new Student("f3", "l3", "id3", "id3@ncsu.edu", "pw", 16);
	Student s4 = new Student("f4", "l4", "id4", "id4@ncsu.edu", "pw", 16);
	Student s5 = new Student("f5", "l5", "id5", "id5@ncsu.edu", "pw", 16);
	Student s6 = new Student("f6", "l6", "id6", "id6@ncsu.edu", "pw", 16);
	Student s7 = new Student("f7", "l7", "id7", "id7@ncsu.edu", "pw", 16);
	Student s8 = new Student("f8", "l9", "id8", "id8@ncsu.edu", "pw", 16);
	Student s9 = new Student("f9", "l9", "id9", "id9@ncsu.edu", "pw", 16);
	Student s10 = new Student("f10", "l10", "id10", "id10@ncsu.edu", "pw", 16);
	Student s11 = new Student("f11", "l11", "id11", "id11@ncsu.edu", "pw", 16);
	/**
	 * Tests courseRoll()
	 */
	@Test
	public void testCourseRoll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		Course c1 = null;
		CourseRoll roll;
		try {
			roll = new CourseRoll(c1, 50);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Course cannot be null.");
		}
		roll = c.getCourseRoll();
		assertEquals(10, roll.getEnrollmentCap());
		assertEquals(0, roll.getNumberOnWaitlist());
	}
	/**
	 * Tests setEnrollmentCap()
	 */
	@Test
	public void testSetEnrollmentCap(){
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll roll = c.getCourseRoll();
		roll.setEnrollmentCap(20);
		assertEquals(20, roll.getEnrollmentCap());
		
		roll.setEnrollmentCap(20);
	}
	/**
	 * Tests enroll()
	 */
	@Test
	public void testEnroll(){
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll roll = c.getCourseRoll();
		//test enroll null student
		try {
			roll.enroll(null);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Student cannot be null.");
			assertEquals(10, roll.getOpenSeats());
		}
		
		//test enroll same student
		roll.enroll(s1);
		try {
			roll.enroll(s1);
		} catch (IllegalArgumentException e) {
			assertEquals(9, roll.getOpenSeats());
		}
		
		//test no space enrollment
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		assertEquals(0, roll.getOpenSeats());
		try {
			roll.enroll(s11);
			assertEquals(1, roll.getNumberOnWaitlist());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
	/**
	 * Tests drop()
	 */
	@Test
	public void  testDrop(){
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll roll = c.getCourseRoll();
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		roll.enroll(s11);
		//test drop valid student
		roll.drop(s1);
		assertEquals(0, roll.getOpenSeats());
		
		//test drop student does not exist in roll
		try {
			roll.drop(s1);
		} catch (IllegalArgumentException e){
			assertEquals(0, roll.getOpenSeats());
		}
		
		//test drop null
		try {
			roll.drop(null);
		} catch (IllegalArgumentException e){
			assertEquals(e.getMessage(), "Student cannot be null.");
			assertEquals(0, roll.getOpenSeats());
		}
		
		roll.enroll(s1);
		assertEquals(1, roll.getNumberOnWaitlist());
		roll.drop(s1);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(0, roll.getNumberOnWaitlist());
	}
	/**
	 * Tests canEnroll()
	 */
	@Test
	public void testCanEnroll(){
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll roll = c.getCourseRoll();
		assertTrue(roll.canEnroll(s1));
		roll.enroll(s1);
		assertFalse(roll.canEnroll(s1));
		assertTrue(roll.canEnroll(s2));
		roll.enroll(s2);
		assertTrue(roll.canEnroll(s3));
		roll.enroll(s3);
		assertFalse(roll.canEnroll(s3));
	}
	
	/**
	 * Tests getNumberOnWaitlist
	 */
	@Test
	public void testGetNumberOnWaitlist() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll roll = c.getCourseRoll();
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		assertEquals(0, roll.getNumberOnWaitlist());
		assertFalse(roll.canEnroll(s11));
		roll.enroll(s11);
		assertEquals(1, roll.getNumberOnWaitlist());
	}
}