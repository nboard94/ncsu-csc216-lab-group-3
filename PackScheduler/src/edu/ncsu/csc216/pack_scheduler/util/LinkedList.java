package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;

public class LinkedList<E> extends AbstractSequentialList<E> {
	ListNode front, back;
	int size;
	
	public LinkedList(){
		front = new ListNode(null,null,null);
		back = new ListNode(null,front,null);
		front.next = back;
		back.prev = front;
		size = 0;
		
	}
	@Override
	public ListIterator<E> listIterator(int arg0) {
		LinkedListIterator listIter = new LinkedListIterator(arg0);
		return listIter;
	}

	@Override
	public int size() {
		return size;
	}
	
	private class LinkedListIterator implements ListIterator<E> {

		public ListNode previous, next, lastRetrieved;
		public int previousIndex, nextIndex;
		
		public LinkedListIterator(int index) { 
			if(index < 0 || index > size )
				throw new IndexOutOfBoundsException();
			ListNode current = front;
			for(int i = 0; i < index; i++){
			current = current.next;
			}
			next = current;
			previous = current.prev;
			previousIndex = index - 1;
			nextIndex = index;
			lastRetrieved = null;
		}

		@Override
		public void add(E arg0) {
			if(arg0 == null)
				throw new NullPointerException();
		
			if(size == 0){
				ListNode lN = new ListNode(arg0, front, back);
			} else{
				ListNode lN = new ListNode(arg0, previous, next);
				previous.next = lN;
				next.prev = lN;
			}
			lastRetrieved = null;
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public boolean hasPrevious() {
			return previous != null;
		}

		@Override
		public E next() {
			return next.data;
		}

		@Override
		public int nextIndex() {
			return nextIndex;
		}

		@Override
		public E previous() {
			return previous.data;
		}

		@Override
		public int previousIndex() {
			return previousIndex;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void set(E arg0) {
			// TODO Auto-generated method stub
			
		} 
		
	}
	
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
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
	}

	
}
