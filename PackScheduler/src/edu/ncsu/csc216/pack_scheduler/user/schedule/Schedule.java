package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * schedule used to manage courses
 * @author xrao
 *
 */
public class Schedule {
	
	private ArrayList<Course> schedule;
	
	private String title;
	/**
	 * constructor of the schedule
	 */
	public Schedule(){
		this.title = "My Schedule";
		resetSchedule();
	}
	/**
	 * add course into the schedule only if the course is not duplicated and has no time conlflict
	 * @param course course to be added
	 * @return whether the course has been added to the schedule
	 */
	public boolean addCourseToSchedule(Course course) {
		for(int i = 0; i < schedule.size(); i++){
			try{
				course.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
			if(course.getName().equals(schedule.get(i).getName())){
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
			}
		} 
		schedule.add(schedule.size(), course);
		return true;
	}
	/**
	 * remove a course from schedule
	 * @param course course to be removed
	 * @return whether the course can be removed
	 */
	public boolean removeCourseFromSchedule(Course course){
		if(course != null)
		for(int i = 0; i < schedule.size(); i++){
			if(course.isDuplicate(schedule.get(i))){
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}
	/**
	 * reset the schedule
	 */
	public void resetSchedule(){
		this.schedule = new ArrayList<Course>();
	}
	/**
	 * set title for the schedule
	 * @param title title
	 */
	public void setTitle(String title){
		if(title == null){
			throw new IllegalArgumentException();
		}
		this.title = title;
	}
	/**
	 * get schedule be to shown
	 * @return 2D array contains the schedule information
	 */
	public String[][] getScheduledCourses(){
		String[][] temp = new String[schedule.size()][4];
		for(int i = 0; i < schedule.size(); i++){
			temp[i] = schedule.get(i).getShortDisplayArray();
		}
		return temp;
	}
	/**
	 * get schedule's title
	 * @return title of the schedule
	 */
	public String getTitle(){
		return this.title;
	}
	/**
	 * Gets the total number of credit hours in the schedule
	 * 
	 * @return total credits
	 */
	public int getScheduleCredits() {
		int credits = 0;
		for (int i = 0; i < schedule.size(); i++) {
			credits += schedule.get(i).getCredits();
		}
		return credits;
	}
	/**
	 * Checks whether or not a course can be added to the schedule
	 * 
	 * @param course course to be added
	 * @return whether or not the course can be added
	 */
	public boolean canAdd(Course course) {
		if (course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (course.getName().equals(schedule.get(i).getName())) {
				return false;
			}
			try {
				course.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				return false;
			}
		}
		return true;
	}
}