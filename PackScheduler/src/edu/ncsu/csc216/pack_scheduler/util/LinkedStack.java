package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Linked List Stack
 * @author dndereef
 *
 * @param <E> Element type of stack
 */
public class LinkedStack<E> implements Stack<E> {
	
	/** Linked List to store stack */
	LinkedAbstractList<E> linkedList;
	
	/**
	 * Constructs a LinkedStack list
	 * @param cap Capacity of list
	 */
	public LinkedStack(int cap) {
		linkedList = new LinkedAbstractList<E>(cap);
	}
	
	/**
	 * Gets and removes the element from the stack
	 * @return Element from stack
	 */
	@Override
	public E pop() {
		if (size() == 0) {
			throw new EmptyStackException();
		}
		return linkedList.remove(size() - 1);
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
		linkedList.add(size(), element);
		
	}
}
