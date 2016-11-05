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
			return next != null;
		}

		/**
		 * Checks if the previous element is null
		 * @return True if the element is not null
		 */
		@Override
		public boolean hasPrevious() {
			return previous != null;
		}

		/**
		 * Gets the next element
		 * @return The next element in the list
		 */
		@Override
		public E next() {
			if (next == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = next;
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
			if (previous == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = previous;
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
		 * 
		 */
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		/**
		 * 
		 */
		@Override
		public void set(E arg0) {
			// TODO Auto-generated method stub
			
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
		public ListNode prev;
		/**
		 * Constructs a new ListNode
		 * 
		 * @param data data to store in the node
		 * @param next reference to the next node
		 */
		public ListNode(E data, ListNode previous, ListNode next1) {
			this.data = data;
			this.next = next1;
			this.prev = previous;
		}
		
	}

	
}
