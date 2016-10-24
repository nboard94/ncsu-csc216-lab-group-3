package edu.ncsu.csc216.pack_scheduler.course.validator;


/**
 * CourseNameValidator using inner class instead of switch cases
 * 
 * @author xrao
 * @author ajstrapp
 * @author jtirene
 */
public class CourseNameValidator  {
	private boolean validEndState;
	private int letterCount;
	private int digitCount;
	private State currentState;
	private final State stateInitial = new InitialState();
	private final State stateLetter = new LetterState();
	private final State stateNumber = new NumberState();
	private final State stateSuffix = new SuffixState();
	/**
	 * constructor
	 */
	public CourseNameValidator(){
		this.validEndState = false;
		this.letterCount = 0;
		this.digitCount = 0;
	}
	
	/**
	 * determine if a course ID is valid
	 * @param courseID course ID
	 * @return whether courseID is valid
	 * @throws InvalidTransitionException courseID is illegal
	 */
	public boolean isValid(String courseID) throws InvalidTransitionException {
		new CourseNameValidator();
		validEndState = false;
		letterCount = 0;
		digitCount = 0;
		currentState = stateInitial;
		if(courseID == null || courseID.equals(""))
			throw new IllegalArgumentException("Invalid name");
		for(int i = 0; i < courseID.length(); i++){
			if(Character.isLetter(courseID.charAt(i))){
				letterCount++;
				currentState.onLetter();
			} else if (Character.isDigit(courseID.charAt(i))){
				digitCount++;
				currentState.onDigit();
			} else {
				currentState.onOther();
			}
		}
		
		return validEndState;
	}
	/**
	 * State class helps validator transfer from different states
	 * @author Xin Rao
	 *
	 */
	private abstract class State {
		/**
		 * empty constructor
		 */
		public State(){
			//empty constructor
		}
		/**
		 * abstract method onLetter
		 * @throws InvalidTransitionException when ID is invalid
		 */
		public abstract void  onLetter() throws InvalidTransitionException;
		/**
		 * abstract method onNumber
		 * @throws InvalidTransitionException when ID is invalid
		 */
		public  abstract void  onDigit() throws InvalidTransitionException;
		/**
		 * abstract method onOther
		 * @throws InvalidTransitionException when ID is invalid
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
		
	}
	/**
	 * Initial state
	 * @author Xin Rao
	 *
	 */
	public class InitialState extends State {
		
		private InitialState(){
			
		}
		/**
		 * on letter 
		 */
		public void onLetter(){
			currentState = stateLetter;
		}
		/**
		 * on digit
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
		
	
	}
	/**
	 * letter state
	 * @author Xin Rao
	 *
	 */
	public class LetterState extends State {
		
		private LetterState(){

		}
		/**
		 * on letter
		 * course ID can have 4 or less letters
		 * otherwise throw exceptions
		 */
		public void onLetter() throws InvalidTransitionException {
			if(letterCount <= 4){
				currentState = stateLetter;
			} else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}
		/**
		 * on digit
		 * transfer to number state
		 */
		public void onDigit(){
			currentState = stateNumber;
			letterCount = 0;
		}
		
	}
	
	/**
	 * number state
	 * @author Xin Rao
	 *
	 */
	public class NumberState extends State {
		
		private NumberState(){
			
		}
		/**
		 * on letter 
		 * if course has 3 digits, transfer to suffix state, otherwise throw excpetion
		 */
		public void onLetter() throws InvalidTransitionException {
			if(digitCount != 3) {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			} else {
				validEndState = true;
				currentState = stateSuffix;
			}
		}
		/**
		 * on digit
		 * course can only have 3 digits, otherwise throw exceptions
		 */
		public void onDigit() throws InvalidTransitionException {
			if(digitCount < 3){
				currentState = stateNumber;
			} else if (digitCount == 3) {
				validEndState = true;
			} else {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}
	}
	/**
	 * suffix state
	 * @author Xin Rao
	 *
	 */
	public class SuffixState extends State {
		
		private SuffixState(){
			
		}
		/**
		 * on letter
		 * if letter is bigger than 1, throw exception
		 * otherwise the course ID is valid
		 */
		public void onLetter() throws InvalidTransitionException {
			if(letterCount > 1){
				throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
			} 
		}
		/**
		 * on digit
		 * throw exception because course ID can only have letter suffix
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
		
	}


}
