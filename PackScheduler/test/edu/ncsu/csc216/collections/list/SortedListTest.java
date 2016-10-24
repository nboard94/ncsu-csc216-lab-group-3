package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Test for sortedList class.
 * @author Xin Rao
 * @author Ketan Gohel
 *
 */
public class SortedListTest {

	/**
	 * test sortedList is constructed correctly
	 * the initial capacity should be 10
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		//TODO Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		list.add("element 1");
		list.add("element 2");
		list.add("element 3");
		list.add("element 4");
		list.add("element 5");
		list.add("element 6");
		list.add("element 7");
		list.add("element 8");
		list.add("element 9");
		list.add("element 10");
		list.add("element 11");
		assertEquals(11, list.size());
	}

	/**
	 * test for add method
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//TODO Test adding to the front, middle and back of the list
		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		
		list.add("apricot");
		assertEquals(3, list.size());
		assertEquals("apricot", list.get(1));
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(2));
		
		list.add("yam");
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("apricot", list.get(1));
		assertEquals("banana", list.get(2));
		assertEquals("yam", list.get(3));
		
		//TODO Test adding a null element
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("apricot", list.get(1));
			assertEquals("banana", list.get(2));
			assertEquals("yam", list.get(3));
		}
		
		//TODO Test adding a duplicate element
		try {
			list.add("banana");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("apricot", list.get(1));
			assertEquals("banana", list.get(2));
			assertEquals("yam", list.get(3));
		}
	}
	
	/**
	 * test for get method
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//TODO Test getting an element from an empty list
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		//TODO Add some elements to the list
		assertTrue(list.add("apple"));
		assertEquals(1, list.size());
		assertTrue(list.add("banana"));
		assertEquals(2, list.size());
		assertTrue(list.add("yam"));
		assertEquals(3, list.size());
		//TODO Test getting an element at an index < 0
		try {
			list.get(-100);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
		//TODO Test getting an element at size
		try {
			list.get(100);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
	}
	
	/**
	 * test for remove method
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		//TODO Add some elements to the list - at least 4
		assertTrue(list.add("apple"));
		assertEquals(1, list.size());
		assertTrue(list.add("banana"));
		assertEquals(2, list.size());
		assertTrue(list.add("orange"));
		assertEquals(3, list.size());
		assertTrue(list.add("yam"));
		assertEquals(4, list.size());
		
		//TODO Test removing an element at an index < 0
		try {
			list.remove(-100);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		//TODO Test removing an element at size
		try {
			list.remove(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		//TODO Test removing a middle element
		list.remove(2);
		assertEquals(3, list.size());
		assertEquals(list.get(0), "apple");
		assertEquals(list.get(1), "banana");
		assertEquals(list.get(2), "yam");
		
		//TODO Test removing the last element
		list.remove(2);
		assertEquals(2, list.size());
		assertEquals(list.get(0), "apple");
		assertEquals(list.get(1), "banana");
		
		//TODO Test removing the first element
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals(list.get(0), "banana");
		
		//TODO Test removing the last element
		list.remove(0);
		assertEquals(0, list.size());
	}
	
	/**
	 * test for indexOf method
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test indexOf on an empty list
		assertEquals(0, list.size());
		assertEquals(-1, list.indexOf("pineapple"));
		
		//TODO Add some elements
		assertTrue(list.add("banana"));
		assertTrue(list.add("apple"));
		assertTrue(list.add("yam"));
		assertEquals(list.size(), 3);
		
		//TODO Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(1, list.indexOf("banana"));
		assertEquals(2, list.indexOf("yam"));
		assertEquals(0, list.indexOf("apple"));
		assertEquals(-1, list.indexOf("orange"));
		assertEquals(-1, list.indexOf("grape"));
		
		//TODO Test checking the index of null
		try {
			assertEquals(-1, list.indexOf(null));
		} catch (NullPointerException e) {
			assertEquals(1, list.indexOf("banana"));
			assertEquals(2, list.indexOf("yam"));
			assertEquals(0, list.indexOf("apple"));
		}
	}
	
	/**
	 * test for clear method
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//TODO Add some elements
		assertTrue(list.add("banana"));
		assertEquals(1, list.size());
		assertTrue(list.add("apple"));
		assertEquals(2, list.size());
		assertTrue(list.add("yam"));
		assertEquals(3, list.size());
		
		//TODO Clear the list
		list.clear();
		
		//TODO Test that the list is empty
		assertEquals(0, list.size());
	}

	/**
	 * test for isEmpty method
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test that the list starts empty
		assertTrue(list.isEmpty());
		
		//TODO Add at least one element
		assertTrue(list.add("banana"));
		assertEquals(1, list.size());
		assertTrue(list.add("apple"));
		assertEquals(2, list.size());
		assertTrue(list.add("yam"));
		assertEquals(3, list.size());
		
		//TODO Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * test for contains method
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test the empty list case
		assertFalse(list.contains("apple"));
		
		//TODO Add some elements
		assertTrue(list.add("banana"));
		assertEquals(1, list.size());
		assertTrue(list.add("apple"));
		assertEquals(2, list.size());
		assertTrue(list.add("yam"));
		assertEquals(3, list.size());
		
		//TODO Test some true and false cases
		assertTrue(list.contains("banana"));
		assertFalse(list.contains("grape"));
		assertTrue(list.contains("yam"));
		assertFalse(list.contains("watermelon"));
	}
	
	/**
	 * test for equals method
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		list3.add("apple");
		//TODO Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertFalse(list1.equals(list3));
		
	}
	
	/**
	 * test for hashCode method
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		list3.add("apple");
		//TODO Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
	}
}
 