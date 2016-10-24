/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * conflict exception handle conflict exists in Activity
 * @author Xin Rao
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * parameterized constructor
	 * @param str string given that should be passed into superclass
	 */
	public ConflictException(String str){
		super(str);
	}
	
	/**
	 * parameterless constructor
	 */
	public ConflictException(){
		super("Schedule conflict.");
	}
}
