package edu.ncsu.csc216.pack_scheduler.util;

import java.util.LinkedList;


public class LinkedQueue<E> implements Queue<E> {
	LinkedList<E> linkedList;
	int capacity;

	public LinkedQueue(int c) {
		linkedList = new LinkedList<E>();
		capacity = c;
	}
	
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
		capacity = c;
		
	}

}
