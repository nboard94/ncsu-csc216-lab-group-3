package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;
/**
 * Tests LinkedQueue
 * @author dndereef
 *
 */
public class LinkedQueueTest {
	/** Capacity test value */
	private static final int MAX_CAP = 5;
	
	/**
	 * Tests enqueue
	 */
	@Test
	public void testEnqueue() {
		LinkedQueue<String> q = new LinkedQueue<String>(MAX_CAP);
		q.enqueue("one");
		assertEquals(1, q.size());
		
		q.enqueue("two");
		q.enqueue("three");
		q.enqueue("four");
		assertEquals(4, q.size());
		
		q.enqueue("five");
		try {
			q.enqueue("six");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Capacity reached.");
		}
	}

	/**
	 * Tests dequeue
	 */
	@Test
	public void testDequeue() {
		LinkedQueue<String> q = new LinkedQueue<String>(MAX_CAP);
		q.enqueue("one");
		assertEquals("one", q.linkedList.get(0));
		assertEquals("one", q.dequeue());
		assertEquals(0, q.size());
		
		try {
			q.dequeue();
		} catch (NoSuchElementException e) {
			assertEquals(e.getMessage(), "Can not remove from empty list.");
		}
		
		q.enqueue("two");
		q.enqueue("three");
		q.enqueue("four");
		q.enqueue("five");
		assertEquals(4, q.size());
		assertEquals("two", q.dequeue());
		assertEquals("three", q.linkedList.get(0));
		assertEquals(3, q.size());
		
		assertEquals("three", q.dequeue());
		assertEquals("four", q.linkedList.get(0));
		assertEquals(2, q.size());
		
		assertEquals("four", q.dequeue());
		assertEquals(1, q.size());
		assertEquals("five", q.dequeue());
		
		q.enqueue("two");
		q.enqueue("three");
		assertEquals("two", q.dequeue());
		q.enqueue("four");
		assertEquals("three", q.dequeue());
	}

	/**
	 * Tests isEmpty
	 */
	@Test
	public void testIsEmpty() {
		LinkedQueue<String> q = new LinkedQueue<String>(MAX_CAP);
		assertTrue(q.isEmpty());
		q.enqueue("one");
		assertFalse(q.isEmpty());
	}

	/**
	 * Tests size
	 */
	@Test
	public void testSize() {
		LinkedQueue<String> q = new LinkedQueue<String>(MAX_CAP);
		assertEquals(0, q.size());
		q.enqueue("one");
		assertEquals(1, q.size());
	}

	/**
	 * Tests setCapacity
	 */
	@Test
	public void testSetCapacity() {
		LinkedQueue<String> q = new LinkedQueue<String>(MAX_CAP);
		q.enqueue("one");
		assertEquals(1, q.size());
		
		q.enqueue("two");
		q.enqueue("three");
		q.enqueue("four");
		assertEquals(4, q.size());
		
		q.enqueue("five");
		
		try {
			q.setCapacity(MAX_CAP);
			assertEquals(5, q.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			q.setCapacity(3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Capacity must have room for elements.");
		}
		
		ArrayQueue<String> q2 = new ArrayQueue<String>(MAX_CAP);
		try {
			q2.setCapacity(-3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Capacity must have room for elements.");
		}
	}

}
