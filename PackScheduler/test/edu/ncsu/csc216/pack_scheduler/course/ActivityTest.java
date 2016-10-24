/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * test for Activity
 * especially for checkConflict method.
 * @author Xin Rao
 *
 */
public class ActivityTest {

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.Activity#checkConflict(edu.ncsu.csc216.pack_scheduler.course.Activity)}.
	 */
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "TH", 1330, 1445);
	    Activity a5 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "M", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	    try {
	        a1.checkConflict(a5);
	        fail();
	        
	    } catch (ConflictException e) {
	    	assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 1:30PM-2:45PM", a5.getMeetingString());	    
	    }
	    
	  //Update a1 with the same meeting days and a start time that overlaps the end time of a2
	    a1.setMeetingDays("TH");
	    a1.setActivityTime(1445, 1530);
	    try {
	        a1.checkConflict(a2);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    Activity a3 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
	    Activity a4 = new Course("CSC226", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
	    try {
	        a3.checkConflict(a4);
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.Activity#checkConflict(edu.ncsu.csc216.pack_scheduler.course.Activity)}.
	 */
	@Test
	public void testGetMeetingString() {
	    Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1509, 1645);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 330, 445);
	    Activity a3 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
	    Activity a4 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 901, 1100);
	    
	    assertEquals("MW 3:09PM-4:45PM", a1.getMeetingString());
	    assertEquals("MW 3:30AM-4:45AM", a2.getMeetingString());
	    assertEquals("Arranged", a3.getMeetingString());
	    assertEquals("MW 9:01AM-11:00AM", a4.getMeetingString());
	}

	/**
	 * Test equals()
	 */
	@Test
	public void testEqualsObject() {
		Activity c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME);
		Activity c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME);
		Activity c3 = new Course(NAME, "Different", SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME);
		Activity c4 = new Course(NAME, TITLE, "002", CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME);
		Activity c5 = new Course(NAME, TITLE, SECTION, 5, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME);
		Activity c6 = new Course(NAME, TITLE, SECTION, CREDITS, "Different", 10, MEETING_DAYS, START_TIME, END_TIME);
		Activity c7 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, "TH", START_TIME, END_TIME);
		Activity c8 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, 830, END_TIME);
		Activity c9 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, 1400);
		
		assertTrue(c1.equals(c1));
		//Test for equality in both directions
		assertTrue(c1.equals(c2));
		assertTrue(c2.equals(c1));
		
		//Test for each of the fields
		assertFalse(c1.equals(c3));
		assertFalse(c1.equals(c4));
		assertFalse(c1.equals(c5));
		assertFalse(c1.equals(c6));
		assertFalse(c1.equals(c7));
		assertFalse(c1.equals(c8));
		assertFalse(c1.equals(c9));
	}
	/**
	 * test setActivityTime
	 */
	@Test
	public void testSetActivityTime() {
		Activity c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME);
		try{
			c1.setActivityTime(-1, -1);
		} catch (IllegalArgumentException e){
			assertEquals("Invalid meeting times", e.getMessage());
		}
		try{
			c1.setActivityTime(60000, 6000);
		} catch (IllegalArgumentException e){
			assertEquals("Invalid meeting times", e.getMessage());
		}
		try{
			c1.setActivityTime(1440, -1);
		} catch (IllegalArgumentException e){
			assertEquals("Invalid meeting times", e.getMessage());
		}
		try{
			c1.setActivityTime(1478, 1670);
		} catch (IllegalArgumentException e){
			assertEquals("Invalid meeting times", e.getMessage());
		}
		try{
			c1.setActivityTime(1440, 1330);
		} catch (IllegalArgumentException e){
			assertEquals("Invalid meeting times", e.getMessage());
		}
	}
}
