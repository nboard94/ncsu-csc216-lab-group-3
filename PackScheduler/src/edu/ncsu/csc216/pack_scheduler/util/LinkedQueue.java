package edu.ncsu.csc216.pack_scheduler.util;

/**
 * LinkedList Queue
 * @author dndereef
 *
 * @param <E> Element type of queue
 */
public class LinkedQueue<E> implements Queue<E> {
	/** Linked List to hold queue elements */
	LinkedAbstractList<E> linkedList;

	/**
	 * Constructs a LinkedQueue
	 * @param c Capacity of queue
	 */
	public LinkedQueue(int c) {
		linkedList = new LinkedAbstractList<E>(c);
	}
	
	/**
	 * Adds element to queue (end)
	 * @param element Element to add
	 */
	@Override
	public void enqueue(E element) {
		linkedList.add(linkedList.size(), element);		
	}

	/**
	 * Removes element from queue (front)
	 * @return the removed element
	 */
	@Override
	public E dequeue() {
		return linkedList.remove(0);
	}

	/**
	 * Checks if list is empty
	 * @return true if list is empty
	 */
	@Override
	public boolean isEmpty() {
		if (linkedList.size() == 0)
			return true;
		return false;
	}

	/**
	 * Gets the size of the list
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return linkedList.size();
	}

	/**
	 * Sets the new capacity of the list
	 * @param c Capacity of list
	 */
	@Override
	public void setCapacity(int c) {
		linkedList.setCapacity(c);
	}

}
