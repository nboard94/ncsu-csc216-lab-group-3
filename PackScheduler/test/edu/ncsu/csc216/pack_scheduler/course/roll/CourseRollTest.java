package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
	/**
	 * Tests courseRoll()
	 */
	@Test
	public void testCourseRoll() {
		CourseRoll roll = new CourseRoll(10);
		assertEquals(10, roll.getEnrollmentCap());
	}
	/**
	 * Tests setEnrollmentCap()
	 */
	@Test
	public void testSetEnrollmentCap(){
		CourseRoll roll = new CourseRoll(10);
		roll.setEnrollmentCap(20);
		assertEquals(20, roll.getEnrollmentCap());
	}
	/**
	 * Tests enroll()
	 */
	@Test
	public void testEnroll(){
		CourseRoll roll = new CourseRoll(3);
		//test enroll null student
		try {
			roll.enroll(null);
		} catch (IllegalArgumentException e) {
			assertEquals(3, roll.getOpenSeats());
		}
		
		//test enroll same student
		roll.enroll(s1);
		try {
			roll.enroll(s1);
		} catch (IllegalArgumentException e) {
			assertEquals(2, roll.getOpenSeats());
		}
		
		//test no space enrollment
		roll.enroll(s2);
		roll.enroll(s3);
		try {
			roll.enroll(s4);
		} catch (IllegalArgumentException e) {
			assertEquals(0, roll.getOpenSeats());
		}
	}
	/**
	 * Tests drop()
	 */
	@Test
	public void  testDrop(){
		CourseRoll roll = new CourseRoll(3);
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		//test drop valid student
		roll.drop(s1);
		assertEquals(1, roll.getOpenSeats());
		
		//test drop student does not exist in roll
		try {
			roll.drop(s4);
		} catch (IllegalArgumentException e){
			assertEquals(1, roll.getOpenSeats());
		}
		
		//test drop null
		try {
			roll.drop(null);
		} catch (IllegalArgumentException e){
			assertEquals(1, roll.getOpenSeats());
		}
	}
	/**
	 * Tests canEnroll()
	 */
	@Test
	public void testCanEnroll(){
		CourseRoll roll = new CourseRoll(3);
		assertTrue(roll.canEnroll(s1));
		roll.enroll(s1);
		assertFalse(roll.canEnroll(s1));
		assertTrue(roll.canEnroll(s2));
		roll.enroll(s2);
		assertTrue(roll.canEnroll(s3));
		roll.enroll(s3);
		assertFalse(roll.canEnroll(s4));
	}
}