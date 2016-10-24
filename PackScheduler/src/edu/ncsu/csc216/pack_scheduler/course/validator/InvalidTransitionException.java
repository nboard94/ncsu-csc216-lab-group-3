package edu.ncsu.csc216.pack_scheduler.course.validator;
/**
 * Invalid transition exception
 * @author Jtirene
 * @author Ajstrapp
 * @author xrao
 *
 */
public class InvalidTransitionException extends Exception {
	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * constructor with default message
	 */
	public InvalidTransitionException(){
		super("Invalid FSM Transition.");
	}
	
	/**
	 * constructor with other message
	 * @param e message as parameter
	 */
	public InvalidTransitionException(String e){
		super(e);
	}
}
