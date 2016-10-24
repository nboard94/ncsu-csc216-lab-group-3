package edu.ncsu.csc216.pack_scheduler.util;

/**
 * LinkedList Queue
 * @author dndereef
 *
 * @param <E> Element type of queue
 */
public class LinkedQueue<E> implements Queue<E> {
	
	public LinkedQueue() {
		
	}
	/**
	 * Adds element to back of queue
	 * @param element
	 * @throws IllegalArgumentException if size is
	 * equal to capacity
	 */
	@Override
	public void enqueue(E element) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Removes and returns the element at front of queue
	 * @throws NoSuchElementException if queue is empty
	 * @return E the element at front of queue
	 */
	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Checks if queue is empty
	 * @return true if queue is empty
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 * Gets the number of elements in the list
	 * @return size of the list
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Sets the queue's capacity
	 * @param capacity
	 * @throws IllegalArgumentException if new capacity is
	 * negative or less than the current size
	 */
	@Override
	public void setCapacity(int capacity) {
		// TODO Auto-generated method stub
		
	}

}
