package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * test two constructors of InvalidTransitionException
 * @author Xin Rao
 *
 */
public class InvalidTransitionExceptionTest {

	/**
	 * test the default constructor
	 */
	@Test
	public void testDefaultConstructor() {
		InvalidTransitionException e = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", e.getMessage());
	}
	
	/**
	 * test the constructor with message
	 */
	@Test
	public void testConstructorWithMessage() {
		InvalidTransitionException e = new InvalidTransitionException("test message.");
		assertEquals("test message.", e.getMessage());
	}

}
