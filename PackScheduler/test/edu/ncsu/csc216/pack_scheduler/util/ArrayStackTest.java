package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayStackTest {
	
	@Test
	public void pushTest() {
		ArrayStack<String> stack = new ArrayStack<String>(10);
		assertEquals(0, stack.size());
		
		stack.push("Obj 1");
		assertEquals("Obj 1", stack.list.get(0));
		assertEquals(1, stack.size());
		
		stack.push("Obj 2");
		assertEquals("Obj 2", stack.list.get(0));
		assertEquals(2, stack.size());
	}
	
	@Test
	public void popTest() {
		ArrayStack<String> stack = new ArrayStack<String>(10);
		stack.push("Obj 1");
		stack.push("Obj 2");

		assertEquals("Obj 2", stack.pop());
		assertEquals(1, stack.size());
		
		assertEquals("Obj 1", stack.pop());
		assertEquals(0, stack.size());
	}
	
	@Test
	public void emptyTest() {
		ArrayStack<String> stack = new ArrayStack<String>(10);
		assertEquals(0, stack.size());

		stack.push("Obj 1");
		assertFalse(stack.isEmpty());
		
		stack.pop();
		assertTrue(stack.isEmpty());
	}
	
	@Test
	public void setCapacityTest() {
		ArrayStack<String> stack = new ArrayStack<String>(10);
		stack.push("Obj 1");
		
		try {
			stack.setCapacity(5);
			stack.push("Obj 2");
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			stack.push("Obj 3");
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			stack.setCapacity(1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(3, stack.size());
		}
		try {
			stack.setCapacity(3);
			stack.push("Obj 4");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(3, stack.size());
		}
		
	}
}
