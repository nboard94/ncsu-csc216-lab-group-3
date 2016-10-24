package edu.ncsu.csc216.pack_scheduler.util;


public class LinkedStack<E> implements Stack<E>{
	LinkedAbstractList<E> linkedList;
	public LinkedStack(){
		linkedList = new LinkedAbstractList<E>(10);
	}
	
	@Override
	public E pop() {
		return linkedList.remove(linkedList.size()-1);
	}

	@Override
	public boolean isEmpty() {
		if(linkedList.size() == 0)
			return true;
		return false;
	}

	@Override
	public int size() {
		return linkedList.size();
	}

	@Override
	public void setCapacity(int capacity) {
		linkedList.setCapacity(capacity);
		
	}

	@Override
	public void push(E element) {
		linkedList.add(linkedList.size(), element);
		
	}
}
