/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the CourseNameValidatorFSM class
 * 
 * @author xrao
 * @author ajstrapp
 * @author jtirene
 *
 */
public class CourseNameValidatorFSMTest {

	/**
	 * test course ID validator
	 */
	@Test
	public void testFSM()  {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		//test valid ID
		try{
			assertTrue(fsm.isValid("A123d"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		try{
			assertTrue(fsm.isValid("AB123d"));
		} catch (InvalidTransitionException e){
			fail();
		}
	
		try{
			assertTrue(fsm.isValid("ABC123d"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		try{
			assertTrue(fsm.isValid("ABCD123d"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		try{
			assertTrue(fsm.isValid("ABCD123"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		
		//test invalid ID
		try{
			assertFalse(fsm.isValid("ABCDE123d"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		
		try{
			assertFalse(fsm.isValid("ABCD1234d"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		
		try{
			assertFalse(fsm.isValid(" "));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		try{
			assertFalse(fsm.isValid("1ABC123"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		
		try{
			assertFalse(fsm.isValid("ABC"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		try{
			assertFalse(fsm.isValid("ABC1"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		try{
			assertFalse(fsm.isValid("ABC12"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		try{
			assertFalse(fsm.isValid("ABC123dd"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		
		try{
			assertFalse(fsm.isValid("ABC123d1"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
		
		try{
			assertFalse(fsm.isValid("ABC1A"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		try{
			assertFalse(fsm.isValid("ABC12A"));
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
	}

}
