package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Faculty object 
 * @author dndereef
 *
 */
public class Faculty extends User {
	
	/** Number of courses faculty teaches */
	private int maxCourses;
	/** Minimum number of courses allowed for a faculty */
	public static final int MIN_COURSES = 1;
	/** Maximum number of courses allowed for a faculty */
	public static final int MAX_COURSES = 3;
	
	/**
	 * Constructs a faculty similar to student
	 * @param firstName First name of faculty
	 * @param lastName Last name of faculty
	 * @param id Faculty id
	 * @param email Faculty email
	 * @param password Faculty password
	 * @param maxCourses Number of courses faculty teaches
	 * @throws IllegalArgumentException if maxCourses is less than the 
	 * minimum or greater than the maximum
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
	}

	/**
	 * @return the maxCourses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * @param maxCourses the maxCourses to set
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException();
		}
		this.maxCourses = maxCourses;
	}

	/**
	 * Gets the hashedcode, or integer representation, of a faculty
	 * @return result the unique hashcode of the faculty
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Checks if the object is the same as this faculty
	 * @param obj Object being compared to this faculty
	 * @return true if both objects are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

	/**
	 * Returns a string representation of Faculty fields and maxCourse info
	 * @return String of Faculty parameters
	 */
	@Override
	public String toString() {
		String temp = super.getFirstName() + "," + super.getLastName() + "," +  super.getId() + "," + super.getEmail() + "," + super.getPassword() + "," + maxCourses;
		return temp;
	}

	
	
	

}
