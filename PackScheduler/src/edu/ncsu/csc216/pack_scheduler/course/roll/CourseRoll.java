package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
/**
 * course roll to check student's enrollment status
 * @author Xin Rao
 *
 */
public class CourseRoll {
	/**a list of student enrolled in this class*/
	public LinkedAbstractList<Student> roll;
	/**maximum seat of the class*/
	private int enrollmentCap;
	/**minimum enrollment number*/
	private static final int MIN_ENROLLMENT = 10;
	/**maximum enrollment number*/
	private static final int MAX_ENROLLMENT = 250;
	/**
	 * Constructor for the course roll
	 * @param i the new enrollment cap that want to be set
	 */
	public CourseRoll(int i){
		enrollmentCap = i;
		roll = new LinkedAbstractList<Student>(enrollmentCap);
	}
	/**
	 * get the enrollment cap
	 * @return enrollment cap
	 */
	public int getEnrollmentCap(){
		return enrollmentCap;
	}
	/**
	 * set the enrollment cap
	 * @param i new enrollment cap that wants to be set
	 */
	public void setEnrollmentCap(int i){
		if(roll == null && i <= MAX_ENROLLMENT && i >= MIN_ENROLLMENT)
			enrollmentCap = i;
		if(i <= MAX_ENROLLMENT && i >= MIN_ENROLLMENT)
			if(roll != null && roll.size() <= i)
				enrollmentCap = i;
			else
				throw new IllegalArgumentException();
		else 
			throw new IllegalArgumentException();
	}
	/**
	 * enroll a new student
	 * @param s Student to be enrolled
	 */
	public void enroll(Student s){
		if(s == null)
			throw new IllegalArgumentException();
		if(enrollmentCap == roll.size())
			throw new IllegalArgumentException();
		for(int i = 0; i < roll.size(); i++)
			if(roll.get(i).equals(s))
				throw new IllegalArgumentException();
		try{
			roll.add(s);
		} catch(Exception E) {
			throw new IllegalArgumentException();
		}
	}
	/**
	 * drop student from course
	 * @param s student to be dropped
	 */
	public void drop(Student s){
		if(s == null)
			throw new IllegalArgumentException();
		for(int i = 0; i < roll.size(); i++){
			if(roll.get(i).equals(s))
				try{
					roll.remove(i);
				} catch(IndexOutOfBoundsException E) {
					throw new IllegalArgumentException();
				}
		}
	}
	/**
	 * get the open seats left
	 * @return remaining open seats
	 */
	public int getOpenSeats(){
		return  enrollmentCap -  roll.size();
	}
	/**
	 * test a student can be enrolled
	 * @param s student to be enrolled
	 * @return whether the student can be enrolled
	 */
	public boolean canEnroll(Student s){
		for(int i = 0; i < roll.size(); i++) {
			if(roll.get(i).equals(s))
				return false;
		}
		return enrollmentCap -  roll.size() > 0;
//		if(enrollmentCap -  roll.size() > 0) 
//			return true;
//		else 
//			return false;
		
	}
	
}
