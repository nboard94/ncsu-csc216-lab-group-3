package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * Collection class for faculty implementation
 * @author dndereef, ajstrapp
 *
 * @param <E> Element type of list
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	/** Beginning of list and end of list */
	private ListNode front, back;
	/** Number of elements in list */
	private int size;
	
	/**
	 * Constructor for LinkedList
	 */
	public LinkedList(){
		front = new ListNode(null,null,null);
		back = new ListNode(null,front,null);
		front.next = back;
		back.prev = front;
		size = 0;
		
	}
	
	/**
	 * Iterator constructor
	 */
	@Override
	public ListIterator<E> listIterator(int arg0) {
		LinkedListIterator listIter = new LinkedListIterator(arg0);
		return listIter;
	}

	/** 
	 * Adds the element to specified index
	 * @param index Index of list
	 * @param element Element to add
	 */
	@Override
	public void add(int index, E element) {
		// Should throw IllegalArgumentException for duplicate element
		for (int i = 0; i < size(); i++) {
			if (element.equals(get(i))) {
				throw new IllegalArgumentException();
			}
		}
		super.add(index, element);
	}
	
	/**
	 * Sets the element to the specified index
	 * @param index Index of list
	 * @param element Element to set
	 */
	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		for (int i = 0; i < size(); i++) {
			if (element.equals(get(i))) {
				throw new IllegalArgumentException();
			}
		}
		return super.set(index, element);
	}

	/**
	 * Gets the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Iterator class
	 * @author dndereef
	 *
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** The previous element, the next element, and the last obtained element */
		public ListNode previous, next, lastRetrieved;
		/** Indexes of previous and next elements */
		public int previousIndex, nextIndex;
		
		/**
		 * Positions the iterator at desired node in list
		 * @param index
		 */
		public LinkedListIterator(int index) { 
			if(index < 0 || index > size )
				throw new IndexOutOfBoundsException();
			ListNode current = front.next;
			for(int i = 0; i < index; i++){
				current = current.next;
			}
			next = current;
			previous = current.prev;
			previousIndex = index - 1;
			nextIndex = index;
			lastRetrieved = null;
		}

		/**
		 * Iterator adds desired element
		 */
		@Override
		public void add(E arg0) {
			if(arg0 == null)
				throw new NullPointerException();
		
			if(size == 0){
				ListNode lN = new ListNode(arg0, previous, next);
				front.next = lN;
				back.prev = lN;
					
			} else{
				ListNode lN = new ListNode(arg0, previous, next);
				previous.next = lN;
				next.prev = lN;
			}
			lastRetrieved = null;
			size++;
		}

		/**
		 * Checks if the next element is null
		 * @returns true if the element is not null
		 */
		@Override
		public boolean hasNext() {
			return next.data != null;
		}

		/**
		 * Checks if the previous element is null
		 * @return True if the element is not null
		 */
		@Override
		public boolean hasPrevious() {
			return previous.data != null;
		}

		/**
		 * Gets the next element
		 * @return The next element in the list
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			lastRetrieved = next;
			next = next.next;
			previous = next.prev;
			nextIndex++;
			previousIndex++;
			return lastRetrieved.data;
		}

		/**
		 * Gets the next element's index
		 * @return The index of the next element
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * Gets the previous element
		 * @return The previous element
		 */
		@Override
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			lastRetrieved = previous;
			previous = previous.prev;
			next = previous.next;
			previousIndex--;
			nextIndex--;
			return lastRetrieved.data;
		}

		/**
		 * Gets the index of the previous element
		 * @return The previous element's index
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/**
		 * Removes the last retrieved element
		 */
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.prev = lastRetrieved.next;
			lastRetrieved = null;
			size--;
		}

		/**
		 * Replaces the last retrieved element
		 * @param element The new element
		 */
		@Override
		public void set(E element) {
			if (element == null) {
				throw new NullPointerException();
			}
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			ListNode lR = new ListNode(element, previous, next);
			lastRetrieved = null;
			previous = lR;
			next.prev = lR;
		} 
		
	}
	
	/**
	 * Individual node in the LinkedList
	 * @author dndereef
	 *
	 */
	private class ListNode {
		/** The information being stored in the node */
		public E data;
		/** Reference to the next node in the list */
		public ListNode next;
		/** Reference to the previous node in the list */
		public ListNode prev;
		/**
		 * Constructs a new ListNode
		 * 
		 * @param data data to store in the node
		 * @param previous reference to previous node
		 * @param next1 reference to the next node
		 */
		public ListNode(E data, ListNode previous, ListNode next1) {
			this.data = data;
			this.next = next1;
			this.prev = previous;
		}
		
	}

	
}
