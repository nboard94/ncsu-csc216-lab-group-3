package edu.ncsu.csc216.pack_scheduler.util;

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
	
	
	@Override
	public void push(E element) {
		if (list.size() < capacityList) {
			list.add(0, element);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public E pop() {
		return list.remove(0);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void setCapacity(int capacity) {
		capacityList = capacity;
	}

}
