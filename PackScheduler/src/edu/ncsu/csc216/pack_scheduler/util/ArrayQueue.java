package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * ArrayList Queue
 * @author dndereef
 *
 * @param <E> Element type of queue
 */
public class ArrayQueue<E> implements Queue<E> {
	/** ArrayList to hold queue elements */
	private ArrayList<E> list;
	/** Maximum number of elements permitted in list */
	private int capacity;
	
	/**
	 * Constructs a new arraylist Queue
	 * @param capacity Maximum capacity of list
	 */
	public ArrayQueue(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity); 
	}
	/**
	 * Adds element to back of queue
	 * @param element
	 * @throws IllegalArgumentException if size is
	 * equal to capacity
	 */
	@Override
	public void enqueue(E element) {
		if (list.size() == capacity) {
			throw new IllegalArgumentException("Capacity reached.");
		}
		list.add(size(), element);
	}

	/**
	 * Removes and returns the element at front of queue
	 * @throws NoSuchElementException if queue is empty
	 * @return E the element at front of queue
	 */
	@Override
	public E dequeue() {
		if (size() == 0) {
			throw new NoSuchElementException("Can not remove from empty list.");
		}
		E element = list.get(0);
		list.remove(0);
		return element;
	}

	/**
	 * Checks if queue is empty
	 * @return true if queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}


	/**
	 * Gets the number of elements in the list
	 * @return size of the list
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the queue's capacity
	 * @param capacity Capacity of queue
	 * @throws IllegalArgumentException if new capacity is
	 * negative or less than the current size
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException("Capacity must have room for elements.");
		}
		this.capacity = capacity;
	}
}
