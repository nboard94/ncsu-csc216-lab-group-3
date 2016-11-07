package edu.ncsu.csc216.pack_scheduler.util;


public class LinkedListRecursive<E> {
	private int size;
	private ListNode front;
	
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean add(E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (front == null) {
			front = new ListNode(element, front);
			size++;
			return true;
		}
		front.add(size - 1, element);
		return false;
	}
	
	public void add(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		if (front == null) {
			front = new ListNode(element, front);
			size++;
		} else if (index == 0) {
			front = new ListNode(element, front);
			size++;
		} else {
			front.add(index - 1, element);
		}
	}
	
	public E get(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index outside of list size.");
		}
		return front.get(index);
	}
	
	public boolean remove(E element) {
		if (size == 0) {
			throw new IllegalArgumentException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		if (size == 1 && contains(element)) {
			front = null;
			size--;
			return true;
		} else if (!contains(element)) {
			return false;
		}
		return front.remove(element);
	}
	
	public E remove(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index outside of list size.");
		}
		if (size == 0) {
			return null;
		}
		if (index == 0) {
			front = front.next;
			size--;
			return front.data;
		} 
		return front.remove(index - 1);
	}
	
	public E set(int index, E element) {
		return null;
	}
	
	public boolean contains(E element) {
		if (size == 0) {
			return false;
		}
		ListNode current = front;
		while (current != null) {
			E found = current.data;
			if (found.equals(element)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	/**
	 * ListNode class that uses recursion for outer class
	 * @author dndereef
	 *
	 */
	private class ListNode {
		/** The information being stored in the node */
		public E data;
		/** Reference to the next node in the list */
		public ListNode next;
		
		/**
		 * 
		 * @param index
		 * @param element
		 */
		private void add(int index, E element) {
			if (index == 0) {
				next = new ListNode(element, next);
				size++;
			} else {
				this.next.add(index - 1, element);
			}
		}
		
		private E get(int index) {
			if (index == 0) {
				return data;
			} else {
				return this.next.get(index - 1); 
			}
		}
		
		private boolean remove(E element) {
			if (this.next.data.equals(element)) {
				this.next = this.next.next;
				size--;
				return true;
			} else {
				return this.next.remove(element);
			}
		}
		
		private E remove(int index) {
			if (index == 0) {
				E temp = data;
				this.next = this.next.next;
				size--;
				return temp;
			} else {
				return this.next.remove(index - 1);
			}
		}
		
		private E set(int index, E element) {
			return null;
		}
		
		private boolean contains(E element) {
			return false;
		}
		
		/**
		 * Constructs a new ListNode
		 * 
		 * @param data data to store in the node
		 * @param previous reference to previous node
		 * @param next1 reference to the next node
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
