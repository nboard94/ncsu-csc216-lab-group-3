package edu.ncsu.csc216.pack_scheduler.util;
/**
 * ArrayList Stack
 * @author dndereef
 *
 * @param <E> Element type of stack
 */
public class ArrayStack<E> implements Stack<E> {

	/** ArrayList to store the data*/
	ArrayList<E> list = new ArrayList<E>();
	
	/** The capacity of the ArrayStack */
	int capacityList;
	
	/**
	 * Constructor for the ArrayStack;
	 */
	public ArrayStack(int cap) {
		this.setCapacity(cap);
	}
	
	/**
	 * Adds element to stack
	 * @throws IllegalArgumentException if size 
	 * reaches capacity
	 */
	@Override
	public void push(E element) {
		if (list.size() < capacityList) {
			list.add(0, element);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Removes element from stack
	 * @return Element removed from stack
	 */
	@Override
	public E pop() {
		return list.remove(0);
	}

	/**
	 * Checks if stack is empty
	 * @return true if stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Gets the size of the stack
	 * @return Size of the stack
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the new capacity of the stack
	 * @param capacity New capacity of stack
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < list.size()) {
			throw new IllegalArgumentException("Capacity too small for number of elements");
		}
		capacityList = capacity;
	}

}
