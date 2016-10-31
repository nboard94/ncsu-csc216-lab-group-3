package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * customized ArrayList for Schedule class
 * @author xrao
 * @param <E> generic representation of data type to be used
 */
public class ArrayList<E> extends AbstractList<E> {
	/** Initial size of array list */
	private static final int INIT_SIZE = 10;
	/** List of elements */
	private E[] list;
	/** Number of elements in list */
	private int size;
	
	/**
	 * constructor
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(){
		//E item = (E)(new Object());
		list = (E[])(new Object[INIT_SIZE]);
		size = 0;
	}
	
	/**
	 * Adds new element to specified index
	 * @param index Index of list
	 * @param element Element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if duplicate element found
	 * @throws IndexOutOfBoundsException if index is less than
	 * 0 or greater than the size of the list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		E[] tempArray = (E[]) (new Object[list.length]);
		
		if(size == 0){
			list[0] = element;
			size++;
			return;
		}
		// grow list if necessary
		if (size == list.length) {
			tempArray = (E[]) (new Object[list.length * 2]);
			for (int i = 0; i < list.length + 1; i++) {
				if (i < index) {
					tempArray[i] = list[i];
				} else if (i == index) {
					tempArray[i] = element;
				} else {

					tempArray[i] = list[i - 1];

				}
			}
		} else {

			for (int i = 0; i < list.length; i++) {
				if (i < index) {
					tempArray[i] = list[i];
				} else if (i == index) {
					tempArray[i] = element;
				} else {

					tempArray[i] = list[i - 1];

				}
			}
		}
		
		list = tempArray;
		size++;
	}
	/**
	 * Removes element at specified index
	 * @param index Index of list
	 * @throws IndexOutOfBoundsException if removing from
	 * empty list, index of list with null element, or outside bounds
	 */
	@Override
	public E remove(int index) {
		if (size <= -1) {
			throw new IndexOutOfBoundsException();
		}
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (list.length == 0) {
			throw new IndexOutOfBoundsException();
		}
		
		E element = list[index];
		
		for(int i = index; i < list.length - 1; i++) {
			list[i] = list[i + 1];
			list[list.length - 1] = null;
		}
		size--;
		return element;
		
	}
	/**
	 * Sets the element at the specified index
	 * @param index Index of list
	 * @param element Element to set
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if duplicate element found
	 * @throws IndexOutOfBoundsException if index is less than
	 * 0 or greater than the size of the list, or size is 0
	 */
	@Override
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (size == 0){
			throw new IndexOutOfBoundsException();
		}
		
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}

		
		E temp = list[index];
		list[index] = element;
		
		return temp;
	}
	/**
	 * Gets the element at the specified index
	 * @param index Index of list
	 * @throws IndexOutOfBoundsException if index is outside
	 * the bounds of the list
	 */
	@Override
	public E get(int index) {
		if (size <= -1) {
			throw new IndexOutOfBoundsException();
		}
		if (index < 0 || index > size() - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		return list[index];
	}

	/**
	 * Gets the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	
}
