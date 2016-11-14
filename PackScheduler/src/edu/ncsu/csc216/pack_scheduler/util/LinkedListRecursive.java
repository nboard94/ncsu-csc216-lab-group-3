package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Recursive linked list class
 * @author dndereef
 *
 * @param <E> Element type
 */
public class LinkedListRecursive<E> {
	/** Number of elements in list */
	private int size;
	/** Head of list */
	private ListNode front;
	
	/**
	 * Constructs a recursive linked list
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	/**
	 * Checks if the list is empty
	 * @return true is list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Gets the size of the list
	 * @return size Number of elements in list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Adds a new element to the list
	 * @param element Element to add
	 * @return true if the element was added successfully
	 */
	public boolean add(E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (front == null) {
			front = new ListNode(element, front);
			size++;
			return true;
		}
		// Checks if element is added to list 
		// from recursive add method
		int num = size;
		front.add(size - 1, element);
		return num != size;
	}
	
	/**
	 * Adds a new element to specific index in list
	 * @param index Index of list to add the element
	 * @param element Element to add
	 */
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
	
	/**
	 * Gets the element at a specific index
	 * @param index Index of list
	 * @return the element at the specified index
	 */
	public E get(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index outside of list size.");
		}
		return front.get(index);
	}
	
	/**
	 * Removes the element from the list
	 * @param element Element to remove
	 * @return true if the element could be removed
	 */
	public boolean remove(E element) {
		if (size == 0) {
			return false;
		}
		if (element == null) {
			return false;
		}
		if (get(0).equals(element)) {
			front = front.next;
			size--;
			return true;
		} else if (!contains(element)) {
			return false;
		}
		return front.remove(element);
	}
	
	/**
	 * Removes the element at a specific index
	 * @param index Index of list
	 * @return the element removed from the list
	 */
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
	
	/**
	 * Replaces element at the index with new element
	 * @param index Index of list
	 * @param element Element replacing with
	 * @return the element that was replaced
	 */
	public E set(int index, E element) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index outside of list size.");
		}
		if (element == null) {
			throw new NullPointerException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		return front.set(index, element);
	}
	
	/**
	 * Checks if the list already has the element
	 * @param element Element looking for
	 * @return true if the list has the element
	 */
	public boolean contains(E element) {
		if (size == 0) {
			return false;
		}
		return front.contains(element);
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
		 * Recursive add for super
		 * @param index Index of list
		 * @param element Element to add
		 */
		private void add(int index, E element) {
			if (index == 0) {
				next = new ListNode(element, next);
				size++;
			} else {
				this.next.add(index - 1, element);
			}
		}
		
		/**
		 * Recursive get for super
		 * @param index Index of list
		 * @return the element at a specific index
		 */
		private E get(int index) {
			if (index == 0) {
				return data;
			} else {
				return this.next.get(index - 1); 
			}
		}
		
		/**
		 * Recursive remove for super
		 * @param element Element to remove
		 * @return true if the element was removed
		 */
		private boolean remove(E element) {
			if (this.next.data.equals(element)) {
				this.next = this.next.next;
				size--;
				return true;
			} else {
				return this.next.remove(element);
			}
		}
		
		/**
		 * Recursive remove at an index for super
		 * @param index Index of list
		 * @return the element removed from the list
		 */
		private E remove(int index) {
			if (index == 0) {
				E temp = next.data;
				this.next = this.next.next;
				size--;
				return temp;
			} else {
				return this.next.remove(index - 1);
			}
		}
		
		/**
		 * Recursive set for super
		 * @param index Index of list
		 * @param element Element replacing with
		 * @return the element that was replaced
		 */
		private E set(int index, E element) {
			if (index == 0) {
				E temp = data;
				data = element;
				return temp;
			} else {
				return this.next.set(index - 1, element);
			}
		}
		
		/**
		 * Recursive check for super
		 * @param element Element looking for
		 * @return true if the list has the element
		 */
		private boolean contains(E element) {
			if (this.data.equals(element)) {
				return true;
			} else if (this.next == null) {
				return false;
			} else {
				return this.next.contains(element);
			}
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
