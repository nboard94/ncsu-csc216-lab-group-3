package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;
/**
 * Tests LinkedStack 
 * @author dndereef
 *
 */
public class LinkedStackTest {

	/** Capacity test value */
	private static final int MAX_CAP = 5;
	
	/**
	 * Tests push
	 */
	@Test 
	public void testPush(){
		LinkedStack<String> stack = new LinkedStack<String>(MAX_CAP);
		
		assertEquals(0, stack.size());
		stack.push("word");
		assertEquals(1, stack.size());
		stack.push("more");
		assertEquals(2, stack.size());
		stack.push("other");
		assertEquals(3, stack.size());
		stack.push("four");
		stack.push("five");
		try {
			stack.push("six");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Capacity reached.");
		}
	}
	
	/**
	 * Tests pop
	 */
	@Test
	public void testPop(){
		LinkedStack<String> stack = new LinkedStack<String>(MAX_CAP);
		
		assertEquals(0, stack.size());
		stack.push("word");
		assertEquals(1, stack.size());
		stack.push("more");
		assertEquals(2, stack.size());
		stack.push("other");
		assertEquals(3, stack.size());
		stack.push("four");
		stack.push("five");
		assertEquals("five", stack.pop());
		assertEquals("four", stack.pop());
		assertEquals("other", stack.pop());
		assertEquals("more", stack.pop());
		assertEquals("word", stack.pop());
		try {
			stack.pop();
			fail();
		} catch (EmptyStackException e) {
			assertEquals(0, stack.size());
		}
	}
	/**
	 * Tests isEmpty
	 */
	@Test
	public void testIsEmpty(){
		LinkedStack<String> stack = new LinkedStack<String>(MAX_CAP);

		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
		
		stack.push("word");
		
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());		
	}
	
	/**
	 * Tests setCapacity
	 */
	@Test
	public void testSetCapacity(){
		LinkedStack<String> stack = new LinkedStack<String>(MAX_CAP);
		
		try{
			stack.setCapacity(-1);
			fail();
		} catch(IllegalArgumentException E){
			assertEquals(0, stack.size());
		}
		
		stack.push("word");
		stack.push("more");
		stack.push("other");
		
		try{
			stack.setCapacity(2);
			fail();
		} catch(IllegalArgumentException E){
			assertEquals(3, stack.size());
		}
		
		stack.setCapacity(3);
		
		
		try{
			stack.push("another");
			fail();
		} catch(IllegalArgumentException E){
			assertEquals(3, stack.size());
		}
		
		stack.setCapacity(4);
		
		try{
			stack.push("another");
			assertEquals(4, stack.size());
		} catch(IllegalArgumentException E){
			fail();
		}
	}
}
