package edu.ncsu.csc216.pack_scheduler.util;

public class ArrayStack<E> implements Stack {

	ArrayList<E> list = new ArrayList<E>();
	int capacityList = 10;
	
	public ArrayStack() {
		
	}
	
	
	@Override
	public void push(Object element) {
		if (list.size() < capacityList) {
			list.add(0, (E) element);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Object pop() {
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
