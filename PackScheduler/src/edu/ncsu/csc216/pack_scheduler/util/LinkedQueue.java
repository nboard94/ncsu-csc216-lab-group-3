package edu.ncsu.csc216.pack_scheduler.util;

public class LinkedQueue<E> implements Queue<E> {
	LinkedAbstractList<E> linkedList;

	@Override
	public void enqueue(E element) {
		linkedList.add(0,element);		
	}

	@Override
	public E dequeue() {
		return linkedList.remove(linkedList.size());
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

}
