package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.ListIterator;

import org.junit.Test;

/**
 * Tests LinkedList
 * @author dndereef
 *
 */
public class LinkedListTest {
	/**
	 * test for  constructor
	 */
	@Test
	public void testConstructor() {
		LinkedList<String> a = new LinkedList<String>();
		assertEquals(0, a.size());
	}
	/**
	 * test method for the set method of LinkedList
	 */
	@Test
	public void testSet() {
		LinkedList<String> a = new LinkedList<String>();
		a.listIterator().add("one");
		try{
			a.listIterator().set("test");
			fail();
		} catch(IllegalStateException E) {
			assertEquals(1, a.size());
		}
		a.listIterator().next();
		try{
			a.listIterator().set(null);
			fail();
		} catch(NullPointerException E) {
			assertEquals(1, a.size());
		}
		
		a.add(0, "test");
		assertEquals(2, a.size());
		a.listIterator().next();
		try{
			a.set(1, "test");
			fail();
		} catch(IllegalArgumentException E) {
			assertEquals(2, a.size());
		}
		
		a.add(0, "string");
		a.add(0, "words");
		assertEquals(a.get(0), "words");
		assertEquals(a.get(1), "string");
		assertEquals(a.get(2), "test");
		assertEquals(a.get(3), "one");
		assertEquals(-1, a.listIterator().previousIndex());
		assertEquals(0, a.listIterator().nextIndex());
		
		ListIterator<String> b = a.listIterator();
		assertEquals("words", b.next());
		
		assertEquals(0, b.previousIndex());
		assertEquals(1, b.nextIndex());
		
		b.set("other");
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
		LinkedList<String> list = new LinkedList<String>();
		
		list.add("a");	// add to empty array
		assertEquals("a", list.get(0));
		assertEquals(1, list.size());
		
		list.add("b");	// add to beginning of array
		assertEquals("b", list.get(1));
		assertEquals("a", list.get(0));
		assertEquals(2, list.size());
		
		list.add("c");	// add to end of array
		assertEquals("b", list.get(1));
		assertEquals("a", list.get(0));
		assertEquals("c", list.get(2));
		assertEquals(3, list.size());
		
		list.add("d");	// add to middle of array
		assertEquals("b", list.get(1));
		assertEquals("d", list.get(3));
		assertEquals("a", list.get(0));
		assertEquals("c", list.get(2));
		assertEquals(4, list.size());
		
		//try to add 11 elements
		list.add(0, "e");
		list.add(0, "f");
		list.add(0, "g");
		list.add(0, "h");
		list.add(0, "i");
		list.add(0, "j");
		list.add(0, "k");
		assertEquals(11, list.size());
		assertEquals("k", list.get(0));
		assertEquals("j", list.get(1));
		assertEquals("i", list.get(2));
		assertEquals("h", list.get(3));
		assertEquals("g", list.get(4));
		assertEquals("f", list.get(5));
		assertEquals("e", list.get(6));
		assertEquals("a", list.get(7));
		assertEquals("b", list.get(8));
		assertEquals("c", list.get(9));
		assertEquals("d", list.get(10));

		
		// test invalid add cases
		
		try {
			list.add(0, "a");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("k", list.get(0));
			assertEquals("j", list.get(1));
			assertEquals("i", list.get(2));
			assertEquals("h", list.get(3));
			assertEquals(11, list.size());
		}
		
		try {	// test add null element
			list.add(4, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(11, list.size());
		}
		
		try {	// test add illegal index
			list.add(-1, "abc");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(11, list.size());
		}
		
		try {	// test add illegal index
			list.add(13, "abc");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(11, list.size());
		}
		
		
	}

	/**
	 * test for remove() method
	 */
	@Test
	public void testRemove() {
		//remove the element at index 0
		LinkedList<String> a = new LinkedList<String>();
		assertEquals(0, a.size());
		a.add(0, "Apple");
		a.add(1, "Grape");
		a.remove(0);
		assertEquals(1, a.size());
		assertEquals("Grape", a.get(0));
		
		//remove the element in middle
		a = new LinkedList<String>();
		assertEquals(0, a.size());
		a.add(0, "Apple");
		a.add(1, "Grape");
		a.add(2, "Orange");
		a.remove(1);
		assertEquals(2, a.size());
		assertEquals("Apple", a.get(0));
		assertEquals("Orange", a.get(1));
		
		//remove the element in the end
		a = new LinkedList<String>();
		assertEquals(0, a.size());
		a.add(0, "Apple");
		a.add(1, "Grape");
		a.add(2, "Orange");
		a.remove(2);
		assertEquals(2, a.size());
		assertEquals("Apple", a.get(0));
		assertEquals("Grape", a.get(1));
		
		//remove the element at invalid index
		a = new LinkedList<String>();
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
		a = new LinkedList<String>();
		assertEquals(0, a.size());
		try{
			a.remove(0);
		} catch (IndexOutOfBoundsException e){
			assertEquals(0, a.size());
		}
	}
	/**
	 * test method for the get method of LinkedList
	 */
	@Test
	public void testGet() {
		LinkedList<String> a = new LinkedList<String>();
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
	 * Tests custom iterator 
	 */
	@Test
	public void testIterator() {
		LinkedList<String> a = new LinkedList<String>();
		a.listIterator().add("one");
		a.listIterator().add("two");
		a.listIterator().add("three");
		a.listIterator().add("four");
		assertEquals(4, a.size());
		ListIterator<String> b = a.listIterator();
		assertEquals(-1, b.previousIndex());
		assertEquals(0, b.nextIndex());
		assertFalse(b.hasPrevious());
		assertTrue(b.hasNext());
		
		b.next();
		assertEquals(0, b.previousIndex());
		assertEquals(1, b.nextIndex());
	}
}
