package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Course is an object that has name, title, section, credit hours, instructorId
 * meetingDays, start and ending time as its fields
 * @author Xin Rao
 */
public class Course extends Activity implements Comparable<Course> {
    /**Course's name.*/
    private String name;
    /** Course's section. */
    private String section;
    /** Course's credit hours */
    private int credits;
    /** Course's instructor */
    private String instructorId;
    private CourseRoll roll;
    /**
	 * get class's name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * set class's name
	 * if the name is null, has a length less than 4 or greater than 6
	 * an IllegalArgumentException is throw
	 * @param name the name to set
	 * @throws IllegalArgumentExcetion if the name is null or length is less than 4 
	 * or more than 6 
	 */
	private void setName(String name) {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			if (cnv.isValid(name))
				this.name = name;
			else
				throw new IllegalArgumentException("Invalid name");
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid name");
		}

	}
	/**
	 * get class's section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * set class's section
	 * throws IllegalArguementException if the section is not exactly 3 digits
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section is not exactly 3 digits
	 */
	public void setSection(String section) {
		if(section == null){
			throw new IllegalArgumentException("Invalid section");
		}
		for(int i = 0; i < section.length(); i++){
			if(!Character.isDigit(section.charAt(i))){
				throw new IllegalArgumentException("Invalid section");
			}
		}
		if(section.length() != 3){
			throw new IllegalArgumentException("Invalid section");
		}
		this.section = section;
	}
	/**
	 * get class's credits
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * set class's credit
	 * throws IllegalArgumentException when credits is not a number or
	 * credits is less than 1 or greater than 5 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException is credits are not digits or are less than 0
	 * or greater than 5
	 */
	public void setCredits(int credits) {
		if(credits < 1 || credits > 5){
			throw new IllegalArgumentException("Invalid credits");
		}
		this.credits = credits;
	}
	/**
	 * get class's instructor
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	/**
	 * set class's instructor
	 * throws IllegalArgumentException when instructorId is null or empty String
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException when instructorId is null or empty String
	 */
	public void setInstructorId(String instructorId) {
		if(instructorId == null || instructorId.length() == 0){
			throw new IllegalArgumentException("Invalid instructor id");
		}
		this.instructorId = instructorId;
	}
	/**
	 * Create a Course with the given name, title, section, credits, instructorId, meetingDays, 
	 * start and end Time for course that are arranged
	 * @param name course's name
	 * @param title course's title
	 * @param section course's section
	 * @param credits course's credits
	 * @param instructorId course's Id
	 * @param enrollmentCap course's enrollment cap
	 * @param meetingDays course's meeting days
	 * @param startTime course's start time
	 * @param endTime course's end time
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(enrollmentCap);
	}
	/**
	 * Create a Course with the given name, title, section, credits, instructorId, and meetingDays for
	 * course that are arranged
	 * @param name course's name
	 * @param title course's title
	 * @param section course's section
	 * @param credits course's credits
	 * @param instructorId course's Id
	 * @param enrollmentCap course's enrollment cap
	 * @param meetingDays course's meeting days
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/*
	 * Generates a hashCode for Course using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credits;
		result = prime * result + getEndTime();
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((getMeetingDays() == null) ? 0 : getMeetingDays().hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + getStartTime();
		result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
		return result;
	}
	/* Compares a given object to this object for equality on all fields.
	 * @param obj the Object to compare 
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (getEndTime() != other.getEndTime())
			return false;
		if (!instructorId.equals(other.instructorId))
			return false;
		if (!getMeetingDays().equals(other.getMeetingDays()))
			return false;
		if (!name.equals(other.name))
			return false;
		if (!section.equals(other.section))
			return false;
		if (getStartTime() != other.getStartTime())
			return false;
		if (!getTitle().equals(other.getTitle()))
			return false;
		return true;
	}
	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (getMeetingDays().equals("A")) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
				+ roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}
	@Override
	public String[] getShortDisplayArray() {
		String[] str = new String[5];
		str[0] = name;
		str[1] = section;
		str[2] = getTitle();
		str[3] = getMeetingString();
		str[4] = "" + roll.getOpenSeats();
		return str;
	}
	@Override
	public String[] getLongDisplayArray() {
		String[] str = new String[7];
		str[0] = name;
		str[1] = section;
		str[2] = getTitle();
		str[3] = "" + credits;
		str[4] = instructorId;
		str[5] = getMeetingString();
		str[6] = "";
		return str;
	}
	/**
	 * override setMeetingDays() method
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		if (!meetingDays.matches("[MTWHFA]+")) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			if (meetingDays.charAt(i) == 'A' && meetingDays.length() != 1) {
				throw new IllegalArgumentException("Invalid meeting days");
			}
		}
		super.setMeetingDays(meetingDays);
	}
	@Override
	public boolean isDuplicate(Activity act) {
		if (this == act)
			return true;
		if (act == null)
			return false;
		if (getClass() != act.getClass())
			return false;
		Course other = (Course) act;
		if (credits != other.credits)
			return false;
		if (getEndTime() != other.getEndTime())
			return false;
		if (!instructorId.equals(other.instructorId))
			return false;
		if (!getMeetingDays().equals(other.getMeetingDays()))
			return false;
		if (!name.equals(other.name))
			return false;
		if (!section.equals(other.section))
			return false;
		if (getStartTime() != other.getStartTime())
			return false;
		if (!getTitle().equals(other.getTitle()))
			return false;
		return true;
	}
	
	/**
	 * Compare Course object to another Course to determine which comes first alphabetically
	 * @param o Course object
	 * @return -1 if first Course object comes before the next Course alphabetically, 1 if the first
	 * Course object comes after the next, and 0 if the Courses are exactly the same based on name and
	 * section
	 */
	@Override
	public int compareTo(Course o) {
		char[] name1;
		char[] section1;
		char[] name2;
		char[] section2;
		name1 = this.name.toCharArray();
		name2 = o.getName().toCharArray();
		section1 = this.section.toCharArray();
		section2 = o.getSection().toCharArray();
		for (int i = 0; i < name1.length; i++) {
			if (name1[i] < name2[i]) {
				return -1;
			} else if (name1[i] > name2[i]) {
				return 1;
			}
		}
		for (int j = 0; j < section1.length; j++) {
			if (section1[j] < section2[j]) {
				return -1;
			} else if (section1[j] > section2[j]) {
				return 1;
			}
		}
		return 0;
	}
	/**
	 * Gets the course roll
	 * @return course roll
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}
}
