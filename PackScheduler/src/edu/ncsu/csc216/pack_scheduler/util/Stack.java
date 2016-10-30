package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface for the stack methods.
 * @author NBoar
 *
 * @param <E>
 */
public interface Stack<E> {
	
	/**
	 * Adds an element to the stack at the top.
	 * @param E element The element to add to the stack.
	 */
	void push(E element);
	
	/**
	 * Removes an element from the stack at the top.
	 * @return E The element at the top of the stack.
	 */
	E pop();
	
	/**
	 * Indicates if the stack is empty or not.
	 * @return True or False if the stack is empty or not.
	 */
	boolean isEmpty();
	
	/**
	 * Indicates the number of elements in the stack.
	 * @return The size of the stack.
	 */
	int size();
	
	/**
	 * Method to set the stack's capacity.
	 * @param capacity The capacity to set the stack to.
	 */
	void setCapacity(int capacity);
}
