package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;
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
	/** Chosen collection type for roll */
	private LinkedQueue<Student> waitlist;
	/**minimum enrollment number*/
	private static final int MIN_ENROLLMENT = 10;
	/**maximum enrollment number*/
	private static final int MAX_ENROLLMENT = 250;
	/** Maximum capacity of waitlist */
	private static final int WAITLIST_SIZE = 10;
	private Course course;
	/**
	 * Constructor for the course roll
	 * @param c Course
	 * @param i the new enrollment cap that want to be set
	 * @throws IllegalArgumentException if course is null
	 */
	public CourseRoll(Course c, int i) {
		if (c == null) {
			throw new IllegalArgumentException("Course cannot be null.");
		}
		setCourse(c);
		setEnrollmentCap(i);
		roll = new LinkedAbstractList<Student>(enrollmentCap);
		waitlist = new LinkedQueue<Student>(WAITLIST_SIZE);
	}
	/**
	 * Sets the course
	 */
	private void setCourse(Course c){
		course = c;
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
		if(roll == null)
			if(i <= MAX_ENROLLMENT && i >= MIN_ENROLLMENT)
				enrollmentCap = i;
			else 
				throw new IllegalArgumentException();
		else if(roll != null)
			if(i <= MAX_ENROLLMENT && i >= MIN_ENROLLMENT && roll.size() <= i){
				enrollmentCap = i;
				roll.setCapacity(enrollmentCap);
			}
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
			throw new IllegalArgumentException("Student cannot be null.");
		for(int i = 0; i < roll.size(); i++)
			if(roll.get(i).equals(s))
				throw new IllegalArgumentException();
		if(enrollmentCap == roll.size()) {
			try {
				waitlist.enqueue(s);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Waitlist is full.");
			}
		} else {
			s.getSchedule().addCourseToSchedule(course);
				roll.add(roll.size(), s);
		}
	}
	/**
	 * drop student from course
	 * @param s student to be dropped
	 */
	public void drop(Student s){
		if(s == null)
			throw new IllegalArgumentException("Student cannot be null.");
		for(int i = 0; i < roll.size(); i++){
			if(roll.get(i).equals(s))
				try{
					roll.remove(i);
					if (!waitlist.isEmpty())
						enroll(waitlist.dequeue());
				} catch(IndexOutOfBoundsException E) {
					throw new IllegalArgumentException();
				}
		}
		// If student drops from waitlist
		// Adds back in students not dropping from waitlist
		for(int j = 0; j < waitlist.size(); j++) {
			Student s2 = waitlist.dequeue();
			if (!s.equals(s2)) {
				waitlist.enqueue(s2);
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
	
	/**
	 * Gets the number of students on course waitlist
	 * @return Number of students on waitlist
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
	
}
