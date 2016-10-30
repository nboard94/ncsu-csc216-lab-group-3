package edu.ncsu.csc216.pack_scheduler.util;


public class LinkedQueue<E> implements Queue<E> {
	LinkedAbstractList<E> linkedList;

	public LinkedQueue(int c) {
		linkedList = new LinkedAbstractList<E>(c);
	}
	
	@Override
	public void enqueue(E element) {
		linkedList.add(linkedList.size(), element);		
	}

	@Override
	public E dequeue() {
		return linkedList.remove(0);
	}

	@Override
	public boolean isEmpty() {
		if (linkedList.size() == 0)
			return true;
		return false;
	}

	@Override
	public int size() {
		return linkedList.size();
	}

	@Override
	public void setCapacity(int c) {
		linkedList.setCapacity(c);
	}

}
