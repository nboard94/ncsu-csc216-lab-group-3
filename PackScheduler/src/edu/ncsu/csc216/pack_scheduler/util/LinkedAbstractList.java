package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * LinkedList implementation
 * 
 * @author xrao
 * @author ajstrapp
 * @author jtirene
 * 
 * @param <E> generic representation of data type to be used
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** reference to the front of the list */
	private ListNode<E> front;
	private ListNode<E> back;
	/** size of the list */
	private int size;
	/** capacity of the list */
	private int capacity;

	/**
	 * Constructs a LinkedAbstractList with initial capacity i
	 * 
	 * @param i initial capacity
	 */
	public LinkedAbstractList(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("Capacity must be greater than 0.");
		}
		this.front = null;
		this.back = null;
		this.size = 0;
		this.capacity = i;
	}

	/**
	 * Adds an element to the list
	 * 
	 * @param i index to add to
	 * @param element data to add at index i
	 */
	@Override
	public void add(int i, E element) {
		ListNode<E> currentNode = front;
		if (size >= capacity) {
			throw new IllegalArgumentException("Capacity reached.");
		}
		if (element == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		for (int j = 0; j < size; j++) {
			if (element.equals(get(j))) {
				throw new IllegalArgumentException("Duplicate element found.");
			}
		}
		if (i > size || i < 0) {
			throw new IndexOutOfBoundsException("Index outside of list size.");
		}
		
		if (i == 0) {
			if (front == null) {
				this.front = new ListNode<E>(element);
				this.back = this.front;
			} else if (front.next == null) {
				this.front = new ListNode<E>(element, front);
				front.next = back;
				back.next = front;
			} else {
				this.front = new ListNode<E>(element, front);
			}
		} else if(i == size){
			this.back.next = new ListNode<E>(element, null);
			this.back = this.back.next;
		} else {
			for (int k = 0; k < i - 1; k++) {
				currentNode = currentNode.next;
			}
			if (currentNode == null) {
				this.front = new ListNode<E>(element);
			} else if (currentNode.next == null) {
				currentNode.next = new ListNode<E>(element, currentNode);
			} else {
				currentNode.next = new ListNode<E>(element, currentNode.next);
			}
		}
		size++;
	}

	/**
	 * Gets the data at a specified index
	 * 
	 * @param index index to get data at
	 * @return the data at the specified index
	 */
	@Override
	public E get(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index outside of list size.");
		}
		ListNode<E> currentNode = front;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		return (E) currentNode.data;
	}

	/**
	 * Returns the size of the LinkedAbstractList
	 * 
	 * @return the size of the LinkedAbstractList
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Sets the data at a specified index
	 * 
	 * @param index the specified index
	 * @param element the data to set
	 * @return the data that was previously at the specified index
	 */
	@Override
	public E set(int index, E element) {
		ListNode<E> currentNode = front;
		if (element == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		for (int j = 0; j < size; j++) {
			if (element.equals(get(j))) {
				throw new IllegalArgumentException("Duplicate element.");
			}
		}
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index outside of list size.");
		}
		for (int k = 0; k < index; k++) {
			currentNode = currentNode.next;
		}
		E temp = (E) currentNode.data;
		currentNode.data = element;
		return temp;
	}

	/**
	 * Removes the node at a specified index
	 * 
	 * @param index index to remove at
	 * @return the data that was in the index
	 */
	@Override
	public E remove(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Can not remove from empty list.");
		}
		ListNode<E> currentNode = front;
		for (int k = 0; k < index - 1; k++) {
			currentNode = currentNode.next;
		}
		if (index == 0) {
			E temp = front.data;
			front = currentNode.next;
			size--;
			return temp;
		} else if(index == size() - 1) {
			E temp = (E) back.data;
			back = currentNode;
			size--;
			return temp;
			
		}
		else {
			for (int k = 0; k < index - 1; k++) {
				currentNode = currentNode.next;
			}
			E temp = (E) currentNode.next.data;
			currentNode.next = currentNode.next.next;
			size--;
			return temp;
		}
	}
	/**
	 * Sets the new capacity
	 * @param capacity Maximum capacity of list
	 * @throws IllegalArgumentException if new capacity is 
	 * less than the size of list or negative
	 */
	public void setCapacity(int capacity) {
		if (capacity < size || capacity < 0) {
			throw new IllegalArgumentException("Capacity must have room for elements.");
		}
		this.capacity = capacity;
	}

	/**
	 * Individual node in the LinkedAbstractList
	 * 
	 * @author ajstrapp
	 * @author xrao
	 * @author jtirene
	 *
	 * @param <T> generic representation of data type
	 */
	private class ListNode<T> {
		/** The information being stored in the node */
		public T data;
		/** Reference to the next node in the list */
		public ListNode<T> next;
		/**
		 * Constructs a new ListNode
		 * 
		 * @param data data to store in the node
		 */
		public ListNode(T data) {
			this.data = data;
			this.next = null;
		}
		/**
		 * Constructs a new ListNode
		 * 
		 * @param data data to store in the node
		 * @param next reference to the next node
		 */
		public ListNode(T data, ListNode<T> next) {
			this.data = data;
			this.next = next;
		}
	}
}