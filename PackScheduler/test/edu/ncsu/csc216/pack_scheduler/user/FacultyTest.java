package edu.ncsu.csc216.pack_scheduler.user;
import static org.junit.Assert.*;

import org.junit.Test;

/***
 * Unit test for Faculty class
 * 
 * @author dndereef, ajstrapp
 *
 */
public class FacultyTest {

	/** Faculty's first name for testing */
	private static final String FIRST_NAME = "Tom";
	
	/** Faculty's last name for testing */
	private static final String LAST_NAME = "Jenkins";
	
	/** Faculty's id for testing */
	private static final String ID = "tjenkin3";
	
	/** Faculty's email for testing */
	private static final String EMAIL = "tjenkin3@ncsu.edu";
	
	/** Faculty's password for testing */
	private static final String PASSWORD = "wolfpack";
	
	/** Faculty's max credit for testing */
	private static final int COURSES = 3;
	
	/**
	 * Test for hash code method
	 */
	@Test 
	public void testHashCode() {
		//Testing for hashCode working correctness
		Faculty s1 = new Faculty (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		Faculty s2 = new Faculty ("Jerry", LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		Faculty s3 = new Faculty (FIRST_NAME, "Github", ID, EMAIL, PASSWORD, COURSES);
		Faculty s4 = new Faculty (FIRST_NAME, LAST_NAME, "someVaildID", EMAIL, PASSWORD, COURSES);
		Faculty s5 = new Faculty (FIRST_NAME, LAST_NAME, ID, "nobody@ncsu.edu", PASSWORD, COURSES);
		Faculty s6 = new Faculty (FIRST_NAME, LAST_NAME, ID, EMAIL, "GoPack", COURSES);
		Faculty s7 = new Faculty (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 2);
		Faculty s8 = new Faculty (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		
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
	public void testFacultyStringStringStringStringString() {
		//Testing for null string first name
		Faculty s = null;
		
		//Testing for empty string first name
		s = null;
		try {
			s = new Faculty("", LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for null string last name
		s = null;
		try {
		s = new Faculty(FIRST_NAME, null, ID, EMAIL, PASSWORD, COURSES);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}

		//Testing for empty string last name
		s = null;
		try {
			s = new Faculty(FIRST_NAME, "", ID, EMAIL, PASSWORD, COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for null string id
		s = null;
		try {
		    s = new Faculty(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, COURSES);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}

		//Testing for empty string id
		s = null;
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for null string email
		s = null;
		try {
		    s = new Faculty(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, COURSES);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}

		//Testing for empty string email
		s = null;
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, "", PASSWORD, COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for string email that does not contain '@'
		s = null;
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, "tjenkin3ncsu.edu", PASSWORD, COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for string email that does not contain '.'
		s = null;
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, "tjenkin3@ncsuedu", PASSWORD, COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for string email in which the last '.' character is earlier than the first '@'
		s = null;
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, "tjenkin3.@ncsuedu", PASSWORD, COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for null string password
		s = null;
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for empty string password
		s = null;
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for invalid maxCourses
		s = null;
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", 4);
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
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		try {
		    s.setFirstName(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(FIRST_NAME, s.getFirstName());
		}
		
		//Empty first name string
		s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
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
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		try {
		    s.setLastName(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(LAST_NAME, s.getLastName());
		}
		
		//Empty last name string
		s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
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
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		try {
		    s.setEmail(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(EMAIL, s.getEmail());
		}
		
		//Empty email string
		s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		try {
		    s.setEmail("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(EMAIL, s.getEmail());
		}
		
		//Email string that does not contain '@'
		s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		try {
		    s.setEmail("tjenkin3ncsu.edu");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(EMAIL, s.getEmail());
		}
		
		//Email string that does not contain '.'
		s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		try {
		    s.setEmail("tjenkin3@ncsuedu");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(EMAIL, s.getEmail());
		}
		
		//Email string in which last '.' character comes before the first '@' character
		s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
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
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		try {
		    s.setPassword(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(PASSWORD, s.getPassword());
		}
		
		//Empty password string
		s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
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
	public void testSetMaxCourses() {
		//Max courses less than 1
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		try {
		    s.setMaxCourses(0);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(COURSES, s.getMaxCourses());
		}
		
		//Max credits greater than 3
		s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		try {
		    s.setMaxCourses(22);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(COURSES, s.getMaxCourses());
		}
	}

	/**
	 * Test for object's equality
	 */
	@Test
	public void testEqualsObject() {
		//Testing for equal method of all Faculty fields
		Faculty s1 = new Faculty (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		Faculty s2 = new Faculty (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		Faculty s3 = new Faculty ("Jerry", LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		Faculty s4 = new Faculty (FIRST_NAME, "Github", ID, EMAIL, PASSWORD, COURSES);
		Faculty s5 = new Faculty (FIRST_NAME, LAST_NAME, "someVaildID", EMAIL, PASSWORD, COURSES);
		Faculty s6 = new Faculty (FIRST_NAME, LAST_NAME, ID, "nobody@ncsu.edu", PASSWORD, COURSES);
		Faculty s7 = new Faculty (FIRST_NAME, LAST_NAME, ID, EMAIL, "GoPack", COURSES);
		Faculty s8 = new Faculty (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 2);	
		Faculty s9 = null;
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
		Faculty s1 = new Faculty (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		String str1 = "Tom,Jenkins,tjenkin3,tjenkin3@ncsu.edu,wolfpack,3";
		assertEquals(str1, s1.toString());
		
		//Test for constructor 2(without max credit and the default max credit is 18)
		Faculty s2 = new Faculty (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 1);
		String str2 = "Tom,Jenkins,tjenkin3,tjenkin3@ncsu.edu,wolfpack,1";
		assertEquals(str2, s2.toString());
	}
	
	/**
	 * Test for Id
	 * first name should not be null, or empty 
	 */
	@Test
	public void testId(){
		//test for getId method
		Faculty s = new Faculty (FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
		assertEquals(ID, s.getId());
	}
}
