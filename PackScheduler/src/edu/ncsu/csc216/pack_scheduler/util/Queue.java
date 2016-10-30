package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Queue Interface for linked list
 * @author dndereef
 *
 * @param <E> Type of elements used
 */
public interface Queue<E> {
	/**
	 * Adds element to back of queue
	 * @param element Element to add
	 * @throws IllegalArgumentException if size is
	 * equal to capacity
	 */
	void enqueue(E element);
	
	/**
	 * Removes and returns the element at front of queue
	 * @throws NoSuchElementException if queue is empty
	 * @return E the element at front of queue
	 */
	E dequeue();
	
	/**
	 * Checks if queue is empty
	 * @return true if queue is empty
	 */
	boolean isEmpty();
	
	/**
	 * Gets the number of elements in the list
	 * @return size of the list
	 */
	int size();
	
	/**
	 * Sets the queue's capacity
	 * @param capacity Maximum capacity of list
	 * @throws IllegalArgumentException if new capacity is
	 * negative or less than the current size
	 */
	void setCapacity(int capacity);
}
