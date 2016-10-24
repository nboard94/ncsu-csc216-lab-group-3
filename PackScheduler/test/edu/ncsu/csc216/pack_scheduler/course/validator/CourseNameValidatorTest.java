package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the CourseNameValidator class
 * 
 * @author xrao
 * @author ajstrapp
 * @author jtirene
 */
public class CourseNameValidatorTest {

	/**
	 * test course id validator
	 */
	@Test
	public void test() {
		CourseNameValidator cnv = new CourseNameValidator();
		//test valid ID
		try{
			assertTrue(cnv.isValid("A123d"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		try{
			assertTrue(cnv.isValid("AB123d"));
		} catch (InvalidTransitionException e){
			fail();
		}
	
		try{
			assertTrue(cnv.isValid("ABC123d"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		try{
			assertTrue(cnv.isValid("ABCD123d"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		try{
			assertTrue(cnv.isValid("ABCD123"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		
		//test invalid ID
		try{
			assertFalse(cnv.isValid("ABCDE123d"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		
		try{
			assertFalse(cnv.isValid("ABCD1234d"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		
		try{
			assertFalse(cnv.isValid(" "));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		try{
			assertFalse(cnv.isValid("1ABC123"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		
		try{
			assertFalse(cnv.isValid("ABC"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		try{
			assertFalse(cnv.isValid("ABC1"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		try{
			assertFalse(cnv.isValid("ABC12"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		try{
			assertFalse(cnv.isValid("ABC123dd"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		
		try{
			assertFalse(cnv.isValid("ABC123d1"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
		
		try{
			assertFalse(cnv.isValid("ABC1A"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		try{
			assertFalse(cnv.isValid("ABC12A"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		try{
			assertFalse(cnv.isValid("ABC123CDE"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
	}

}
