package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * A Student object represents a grouping of the student's information, including the student's first
 * and last name, id, email, password, and maximum number of credits.
 * 
 * @author Ketan Gohel
 * @author Xin Rao
 */

public class Student extends User implements Comparable<Student> {
	
	/**
	 * Integer that represents the student's maximum number of credits 
	 */
	private int maxCredits;
	/**
	 * Integer that represents the student's maximum number of credits
	 */
	public static final int MAX_CREDITS = 18;
	/** Schedule that represents the classes the Student is enrolled in */
	private Schedule schedule = new Schedule();
	
	/**
	 * Constructs and initializes a Student object
	 * 
	 * @param firstName first name of student
	 * @param lastName last name of student
	 * @param id id of student
	 * @param email email address of student
	 * @param hashPW password of student
	 * @param maxCredits maximum credits of student
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
	}
	
	/**
	 * Constructs and initializes a Student object
	 * 
	 * @param firstName first name of student
	 * @param lastName last name of student
	 * @param id id of student
	 * @param email email address of student
	 * @param hashPW password of student
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
	}





	/**
	 * Returns the maximum number of credits for the student
	 * 
	 * @return the maximum number of credits for the student
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the maximum number of credits for the student equal to the parameter, maxCredits
	 * 
	 * @param maxCredits maximum number of credits for the student
	 */
	public void setMaxCredits(int maxCredits) {
		if(maxCredits < 3 || maxCredits > 18){
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}



	@Override
	/**
	 * Returns a string of the information of the student
	 * 
	 * @return a string of the information of the student
	 */
	public String toString() {
		String temp = super.getFirstName() + "," + super.getLastName() + "," +  super.getId() + "," + super.getEmail() + "," + super.getPassword() + "," + maxCredits;
		return temp;
	}

	/**
	 * compare student in order according to their Last name,
	 * then first name, then UnityID
	 * @return the result of comparing
	 */
	@Override
	public int compareTo(Student o) {
		char[] lln; //longer last name
		char[] sln; //shorter last name
		char[] lfn; //longer first name
		char[] sfn; //shorter first name
		char[] lu;  //longer unityID
		char[] su;  //shorter unityID
		boolean tsl; //this student have short last name
		boolean tsf; //this student have short first name
		boolean tsu; //this student have short unityID
		if(super.getLastName().length() >= o.getLastName().length()){
			lln = super.getLastName().toCharArray();
			sln = o.getLastName().toCharArray();
			tsl = false;
		} else {
			sln = super.getLastName().toCharArray();
			lln = o.getLastName().toCharArray();
			tsl = true;
		}
		if(super.getFirstName().length() >= o.getFirstName().length()){
			lfn = super.getFirstName().toCharArray();
			sfn = o.getFirstName().toCharArray();
			tsf = false;
		} else {
			sfn = super.getFirstName().toCharArray();
			lfn = o.getFirstName().toCharArray();
			tsf = true;
		}
		if(super.getId().length() >= o.getId().length()){
			lu = super.getId().toCharArray();
			su = o.getId().toCharArray();
			tsu = false;
		} else {
			su = super.getId().toCharArray();
			lu = o.getId().toCharArray();
			tsu = true;
		}
		//compare last name
		for(int i = 0; i < sln.length; i++){
			if(sln[i] < lln[i] && tsl){
				return -1;
			} else if (sln[i] < lln[i] && !tsl){
				return 1;
			} else if (sln[i] > lln[i] && tsl){
				return 1;
			} else if (sln[i] > lln[i] && !tsl){
				return -1;
			}
		}
		if(!(sln.length == lln.length)){
			if(tsl){
				return -1;
			} else if (!tsl){
				return 1;
			}
		}
		//compare first name
		for(int i = 0; i < sfn.length; i++){
			if(sfn[i] < lfn[i] && tsf){
				return -1;
			} else if (sfn[i] < lfn[i] && !tsf){
				return 1;
			} else if (sfn[i] > lfn[i] && tsf){
				return 1;
			} else if (sfn[i] > lfn[i] && !tsf){
				return -1;
			}
		}
		if(!(sfn.length == lfn.length)){
			if(tsf){
				return -1;
			} else if (!tsf){
				return 1;
			}
		}
		//compare UnityID
		for(int i = 0; i < su.length; i++){
			if(su[i] < lu[i] && tsu){
				return -1;
			} else if (su[i] < lu[i] && !tsu){
				return 1;
			} else if (su[i] > lu[i] && tsu){
				return 1;
			} else if (su[i] > lu[i] && !tsu){
				return -1;
			}
		}
		if(!(su.length == lu.length)){
			if(tsu){
				return -1;
			} else if (!tsu){
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}
	/**
	 * return a schedule object
	 * @return student's schedule
	 */
	public Schedule getSchedule(){
		return schedule;
	}
	
	/**
	 * checks whether the course can be added to the student's schedule
	 * 
	 * @param course course to check against
	 * @return whether or not the course can be added
	 */
	public boolean canAdd(Course course) {
		if (course == null) {
			return false;
		}
		if (!schedule.canAdd(course)) {
			return false;
		}
		if (schedule.getScheduleCredits() + course.getCredits() > maxCredits) {
			return false;
		}
		return true;
	}
}