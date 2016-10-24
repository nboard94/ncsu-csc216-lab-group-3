package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/***
 * Unit test for Student class
 * @author Xin Rao
 * @author Ketan Gohel
 *
 */
public class StudentTest {

	/** Student's first name for testing */
	private static final String FIRST_NAME = "Tom";
	
	/** Student's last name for testing */
	private static final String LAST_NAME = "Jenkins";
	
	/** Student's id for testing */
	private static final String ID = "tjenkin3";
	
	/** Student's email for testing */
	private static final String EMAIL = "tjenkin3@ncsu.edu";
	
	/** Student's password for testing */
	private static final String PASSWORD = "wolfpack";
	
	/** Student's max credit for testing */
	private static final int MAX_CREDITS = 15;
	
	/**
	 * Test for hash code method
	 */
	@Test 
	public void testHashCode() {
		//Testing for hashCode working correctness
		Student s1 = new Student (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s2 = new Student ("Jerry", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s3 = new Student (FIRST_NAME, "Github", ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s4 = new Student (FIRST_NAME, LAST_NAME, "someVaildID", EMAIL, PASSWORD, MAX_CREDITS);
		Student s5 = new Student (FIRST_NAME, LAST_NAME, ID, "nobody@ncsu.edu", PASSWORD, MAX_CREDITS);
		Student s6 = new Student (FIRST_NAME, LAST_NAME, ID, EMAIL, "GoPack", MAX_CREDITS);
		Student s7 = new Student (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 17);
		Student s8 = new Student (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		
		//Test for the same hash code for same values
		assertEquals(s1.hashCode(), s8.hashCode());
		
		//Test for each of different fields
		assertNotEquals(s1.hashCode(), s2.hashCode());
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
	}
	
	/**
	 * Test for constructor that without max credits given
	 * default max credit should be 18
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		//Testing for null string first name
		Student s = null;
		try {
		    s = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		//Testing for empty string first name
		s = null;
		try {
			s = new Student("", LAST_NAME, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for null string last name
		s = null;
		try {
		s = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}

		//Testing for empty string last name
		s = null;
		try {
			s = new Student(FIRST_NAME, "", ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for null string id
		s = null;
		try {
		    s = new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}

		//Testing for empty string id
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for null string email
		s = null;
		try {
		    s = new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}

		//Testing for empty string email
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "", PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for string email that does not contain '@'
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "tjenkin3ncsu.edu", PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for string email that does not contain '.'
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "tjenkin3@ncsuedu", PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for string email in which the last '.' character is earlier than the first '@'
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "tjenkin3.@ncsuedu", PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for null string password
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for empty string password
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Test for constructor that with max credits given
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		//Testing for null string first name
		Student s = null;
		try {
		    s = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		//Testing for empty string first name
		s = null;
		try {
			s = new Student("", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for null string last name
		s = null;
		try {
		s = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD, MAX_CREDITS);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}

		//Testing for empty string last name
		s = null;
		try {
			s = new Student(FIRST_NAME, "", ID, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for null string id
		s = null;
		try {
		    s = new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, MAX_CREDITS);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}

		//Testing for empty string id
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for null string email
		s = null;
		try {
		    s = new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, MAX_CREDITS);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}

		//Testing for empty string email
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "", PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for string email that does not contain '@'
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "tjenkin3ncsu.edu", PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for string email that does not contain '.'
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "tjenkin3@ncsuedu", PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for string email in which the last '.' character is earlier than the first '@'
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "tjenkin3.@ncsuedu", PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for null string password
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, null, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for empty string password
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "", MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for maximum credits below 0
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for maximum credits above 18
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 20);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Test for set first name
	 * first name should not be null, or empty 
	 */
	@Test
	public void testSetFirstName() {
		//Null first name string
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		try {
		    s.setFirstName(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(FIRST_NAME, s.getFirstName());
		}
		
		//Empty first name string
		s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		try {
		    s.setFirstName("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(FIRST_NAME, s.getFirstName());
		}
	}

	/**
	 * Test for set last name
	 * last name should not be null, or empty 
	 */
	@Test
	public void testSetLastName() {
		//Null last name string
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		try {
		    s.setLastName(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(LAST_NAME, s.getLastName());
		}
		
		//Empty last name string
		s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		try {
		    s.setLastName("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(LAST_NAME, s.getLastName());
		}
	}

	/**
	 * Test for set email
	 * email should not be null, or empty 
	 * email should contains at least one '@' and '.'
	 * first '@' should be in front of last '.'
	 */
	@Test
	public void testSetEmail() {
		//Null email string
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		try {
		    s.setEmail(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(EMAIL, s.getEmail());
		}
		
		//Empty email string
		s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		try {
		    s.setEmail("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(EMAIL, s.getEmail());
		}
		
		//Email string that does not contain '@'
		s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		try {
		    s.setEmail("tjenkin3ncsu.edu");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(EMAIL, s.getEmail());
		}
		
		//Email string that does not contain '.'
		s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		try {
		    s.setEmail("tjenkin3@ncsuedu");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(EMAIL, s.getEmail());
		}
		
		//Email string in which last '.' character comes before the first '@' character
		s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		try {
		    s.setEmail("tjenkin3.@ncsuedu");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(EMAIL, s.getEmail());
		}
	}

	/**
	 * Test for set password
	 * password should not be null, or empty 
	 */
	@Test
	public void testSetPassword() {
		//Null password string
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		try {
		    s.setPassword(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(PASSWORD, s.getPassword());
		}
		
		//Empty password string
		s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		try {
		    s.setPassword("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(PASSWORD, s.getPassword());
		}
	}

	/**
	 * Test for set max credit
	 * max credit should not below 3 or above 18 
	 */
	@Test
	public void testSetMaxCredits() {
		//Max credits int less than 3
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		try {
		    s.setMaxCredits(2);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(MAX_CREDITS, s.getMaxCredits());
		}
		
		//Max credits int greater than 18
		s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		try {
		    s.setMaxCredits(22);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(MAX_CREDITS, s.getMaxCredits());
		}
	}

	/**
	 * Test for object's equality
	 */
	@Test
	public void testEqualsObject() {
		//Testing for equal method of all student fields
		Student s1 = new Student (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s2 = new Student (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s3 = new Student ("Jerry", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s4 = new Student (FIRST_NAME, "Github", ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s5 = new Student (FIRST_NAME, LAST_NAME, "someVaildID", EMAIL, PASSWORD, MAX_CREDITS);
		Student s6 = new Student (FIRST_NAME, LAST_NAME, ID, "nobody@ncsu.edu", PASSWORD, MAX_CREDITS);
		Student s7 = new Student (FIRST_NAME, LAST_NAME, ID, EMAIL, "GoPack", MAX_CREDITS);
		Student s8 = new Student (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 17);	
		Student s9 = null;
		String s10 = "";
		
		//Test in double way equal
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		assertTrue(s1.equals(s1));
		
		//Test for each of different fields
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		assertFalse(s1.equals(s9));
		assertFalse(s10.equals(s1));
		assertFalse(s1.equals(s10));
	}

	/**
	 * Test for toString method
	 */
	@Test
	public void testToString() {
		//Test for constructor 1(with max credit)
		Student s1 = new Student (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		String str1 = "Tom,Jenkins,tjenkin3,tjenkin3@ncsu.edu,wolfpack,15";
		assertEquals(str1, s1.toString());
		
		//Test for constructor 2(without max credit and the default max credit is 18)
		Student s2 = new Student (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		String str2 = "Tom,Jenkins,tjenkin3,tjenkin3@ncsu.edu,wolfpack,18";
		assertEquals(str2, s2.toString());
	}
	
	/**
	 * Test for Id
	 * first name should not be null, or empty 
	 */
	@Test
	public void testId(){
		//test for getId method
		Student s = new Student (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertEquals(ID, s.getId());
	}

	/**
	 * Test for compareTo
	 * return 1,0 or -1
	 */
	@Test
	public void testCompareTo(){
		Student s1 = new Student("Ezio", "Auditore", "Eauditore", "Eauditore@ncsu.edu", "pw", 15);
		Student s2 = new Student("Federico", "Auditore", "Eauditore", "Eauditore@ncsu.edu", "pw", 15);
		Student s3 = new Student("Ezio", "Auditore", "Edaauditore", "Edaauditore@ncsu.edu", "pw", 15);
		Student s4 = new Student("Jacob", "Frye", "Jfrye", "ajenkens@ncsu.edu", "pw", 15);
		Student s5 = new Student("Connor", "Kenway", "Ckenway", "ckenway@ncsu.edu", "pw", 15);
		Student s6 = new Student("A", "B", "ab", "ab@ncsu.edu", "pw", 15);
		Student s7 = new Student("Aa", "B", "aab", "aab@ncsu.edu", "pw", 15);
		Student s8 = new Student("Aa", "Bb", "aabb", "aabb@ncsu.edu", "pw", 15);
		
		assertEquals(-1, s1.compareTo(s4));
		assertEquals(1, s4.compareTo(s1));
		
		assertEquals(-1, s1.compareTo(s2));
		assertEquals(1, s2.compareTo(s1));
		
		assertEquals(-1, s1.compareTo(s3));
		assertEquals(1, s3.compareTo(s1));
		
		assertEquals(-1, s4.compareTo(s5));
		assertEquals(1, s5.compareTo(s4));
		
		assertEquals(-1, s6.compareTo(s7));
		assertEquals(1, s7.compareTo(s6));
		
		assertEquals(-1, s7.compareTo(s8));
		assertEquals(1, s8.compareTo(s7));
		
		assertEquals(0, s5.compareTo(s5));
	}
	/**
	 * Tests canAdd
	 */
	@Test
	public void testCanAdd() {
		Student s1 = new Student("Ezio", "Auditore", "Eauditore", "Eauditore@ncsu.edu", "pw", 10);
		SortedList<Course> courses = null;
		
		try {
			courses = CourseRecordIO.readCourseRecords("test-files/course_records.txt");
		} catch (FileNotFoundException e) {
			fail();
		}
		
		for (int i = 0; i < courses.size(); i++) {
			try {
				s1.getSchedule().addCourseToSchedule(courses.get(i));
			} catch (IllegalArgumentException e) {
				// do nothing
			}
		}
		
		assertFalse(s1.canAdd(null));
		assertFalse(s1.canAdd(courses.get(0)));
		assertFalse(s1.canAdd(courses.get(1)));
		assertFalse(s1.canAdd(courses.get(7)));
		
		s1.getSchedule().removeCourseFromSchedule(courses.get(0));
		
		assertTrue(s1.canAdd(courses.get(0)));
		
		s1.getSchedule().removeCourseFromSchedule(courses.get(0));
		
		s1.setMaxCredits(9);
		
		assertFalse(s1.canAdd(courses.get(0)));
	}
}