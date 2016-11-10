package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListRecursiveTest {

	/**
	 * test for constructor
	 */
	@Test
	public void testConstructor() {
		LinkedListRecursive<String> a = new LinkedListRecursive<String>();
		assertEquals(0, a.size());
	}
	/**
	 * test method for the set method of arrayList
	 */
	@Test
	public void testSet(){
		LinkedListRecursive<String> a = new LinkedListRecursive<String>();
		try{
			a.set(0, "test");
			fail();
		} catch(IndexOutOfBoundsException E) {
			assertEquals(a.size(), 0);
		}
		try{
			a.set(5, "test");
			fail();
		} catch(IndexOutOfBoundsException E) {
			assertEquals(a.size(), 0);
		}
		
		
		a.add(0, "test");
		assertEquals(a.size(), 1);
		try{
			a.set(0, null);
			fail();
		} catch(NullPointerException E) {
			assertEquals(a.size(), 1);
		}
		
		a.add(0, "string");
		a.add(0, "words");
		assertEquals(a.get(0), "words");
		assertEquals(a.get(1), "string");
		assertEquals(a.get(2), "test");
		a.set(0, "other");
		assertEquals("other", a.get(0));
		a.set(1, "letters");
		assertEquals("letters", a.get(1));
	}
	/**
	 * test for add method
	 */
	@Test
	public void testAdd() {
		// test valid add cases
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		
		list.add("a");	// add to empty array
		assertEquals("a", list.get(0));
		assertEquals(1, list.size());
		
		list.add("b");	// add to beginning of array
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals(2, list.size());
		
		list.add("c");	// add to end of array
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("c", list.get(2));
		assertEquals(3, list.size());
		
		list.add("d");	// add to middle of array
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("c", list.get(2));
		assertEquals("d", list.get(3));
		assertEquals(4, list.size());
		
		
	}
	
	@Test
	public void testAddIntE() {
		//try to add 11 elements
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		list.add(0, "e");
		list.add(0, "f");
		list.add(0, "g");
		list.add(0, "h");
		list.add(0, "i");
		list.add(0, "j");
		list.add(0, "k");
		assertEquals(7, list.size());
		assertEquals("k", list.get(0));
		assertEquals("j", list.get(1));
		assertEquals("i", list.get(2));
		assertEquals("h", list.get(3));
		assertEquals("g", list.get(4));
		assertEquals("f", list.get(5));
		assertEquals("e", list.get(6));
				
		// test invalid add cases
				
				try {
					list.add(0, "e");
					fail();
				} catch (IllegalArgumentException e) {
					assertEquals("k", list.get(0));
					assertEquals("j", list.get(1));
					assertEquals("i", list.get(2));
					assertEquals("h", list.get(3));
					assertEquals(7, list.size());
				}
				
				try {	// test add null element
					list.add(4, null);
					fail();
				} catch (NullPointerException e) {
					assertEquals(7, list.size());
				}
				
				try {	// test add illegal index
					list.add(-1, "abc");
					fail();
				} catch (IndexOutOfBoundsException e) {
					assertEquals(7, list.size());
				}
				
				try {	// test add illegal index
					list.add(13, "abc");
					fail();
				} catch (IndexOutOfBoundsException e) {
					assertEquals(7, list.size());
				}
				
				list = new LinkedListRecursive<String>();
				list.add(0, "a");
				list.add(1, "b");
				list.add(0, "c");
				assertEquals(3, list.size());
				assertEquals("c", list.get(0));
				assertEquals("a", list.get(1));
				assertEquals("b", list.get(2));
	}

	/**
	 * test for remove() method
	 */
	@Test
	public void testRemove() {
		//remove the element at index 0
		LinkedListRecursive<String> a = new LinkedListRecursive<String>();
		assertEquals(0, a.size());
		a.add(0, "Apple");
		a.add(1, "Grape");
		a.remove(0);
		assertEquals(1, a.size());
		assertEquals("Grape", a.get(0));
		
		//remove the element in middle
		a = new LinkedListRecursive<String>();
		assertEquals(0, a.size());
		a.add(0, "Apple");
		a.add(1, "Grape");
		a.add(2, "Orange");
		a.remove(1);
		assertEquals(2, a.size());
		assertEquals("Apple", a.get(0));
		assertEquals("Orange", a.get(1));
		
		//remove the element in the end
		a = new LinkedListRecursive<String>();
		assertEquals(0, a.size());
		a.add(0, "Apple");
		a.add(1, "Grape");
		a.add(2, "Orange");
		a.remove(2);
		assertEquals(2, a.size());
		assertEquals("Apple", a.get(0));
		assertEquals("Grape", a.get(1));
		
		//remove the element at invalid index
		a = new LinkedListRecursive<String>();
		assertEquals(0, a.size());
		a.add(0, "Apple");
		a.add(1, "Grape");
		a.add(2, "Orange");
		try{
			a.remove(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, a.size());
		}
		
		try{
			a.remove(12);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, a.size());
		}
		
		//remove element from empty list
		a = new LinkedListRecursive<String>();
		assertEquals(0, a.size());
		try{
			a.remove(0);
		} catch (IndexOutOfBoundsException e){
			assertEquals(0, a.size());
		}
	}
	
	@Test
	public void testRemoveE() {
		LinkedListRecursive<String> a = new LinkedListRecursive<String>();
		assertEquals(0, a.size());
		a.add(0, "Apple");
		a.add(1, "Grape");
		assertTrue(a.remove("Apple"));
		assertEquals(1, a.size());
		assertEquals("Grape", a.get(0));
	}
	/**
	 * test method for the get method of arrayList
	 */
	@Test
	public void testGet(){
		LinkedListRecursive<String> a = new LinkedListRecursive<String>();
		a.add(0, "test");
		a.add(0, "string");
		a.add(0, "words");
		
		assertEquals(a.get(0), "words");
		assertEquals(a.get(1), "string");
		assertEquals(a.get(2), "test");
		try{
			a.get(5);
			fail();
		} catch(IndexOutOfBoundsException E) {
			assertEquals(a.size(), 3);
		}

	}
	/**
	 * Tests setCapacity
	 */
	@Test
	public void testsetCapacity(){
		LinkedAbstractList<String> a = new LinkedAbstractList<String>(10);
		assertEquals(a.size(), 0);
		a.add(0, "test");
		a.add(0, "string");
		a.add(0, "words");
		a.setCapacity(3);
		try{
			a.add(0, "tests");
			fail();
		} catch(IllegalArgumentException E){
			assertEquals(a.size(), 3);
		}
		a.setCapacity(4);
		try{
			a.add(0, "tests");
			assertEquals(a.size(), 4);
		} catch(IllegalArgumentException E){
			fail();
		}
		try{
			a.setCapacity(-1);
			fail();
		} catch(IllegalArgumentException E){
			assertEquals(a.size(), 4);
		}
		try{
			a.setCapacity(2);
			fail();
		} catch(IllegalArgumentException E){
			assertEquals(a.size(), 4);
		}
		
		
	}

}
