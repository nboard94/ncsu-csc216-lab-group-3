package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Linked List Stack
 * @author dndereef
 *
 * @param <E> Element type of stack
 */
public class LinkedStack<E> implements Stack<E> {
	
	/** Initial capacity of list */
	private static final int INIT_SIZE = 10;
	/** Linked List to store stack */
	LinkedAbstractList<E> linkedList = new LinkedAbstractList<E>(INIT_SIZE);
	
	/**
	 * Constructs a LinkedStack list
	 */
	public LinkedStack() {
		// Sets up linked stack
	}
	
	/**
	 * Gets and removes the element from the stack
	 * @return Element from stack
	 */
	@Override
	public E pop() {
		return linkedList.remove(0);
	}

	/**
	 * Checks if stack is empty
	 * @return true if stack is empty
	 */
	@Override
	public boolean isEmpty() {
		if(linkedList.size() == 0)
			return true;
		return false;
	}

	/**
	 * Gets the size of the stack
	 * @return Size of stack
	 */
	@Override
	public int size() {
		return linkedList.size();
	}

	/**
	 * Sets the new capacity of the stack
	 * @param capacity New capacity of stack
	 */
	@Override
	public void setCapacity(int capacity) {
		linkedList.setCapacity(capacity);
		
	}

	/**
	 * Adds the element to the stack
	 */
	@Override
	public void push(E element) {
		linkedList.add(0, element);
		
	}
}
