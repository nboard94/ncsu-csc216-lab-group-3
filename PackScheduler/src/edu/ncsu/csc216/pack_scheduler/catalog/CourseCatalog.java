package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;
import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * CourseCatalog use to manage courses
 * @author Ketan Gohel
 * @author Xin Rao
 */
public class CourseCatalog {
	
	/** List of Courses */
	private SortedList<Course> catalog;
	
	/**
	 * Constructs an empty catalog.
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}
	
	/**
	 * Constructs an empty catalog.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/**
	 * Loads course records into the catalog. Any FileNotFoundExceptions are caught and an
	 * IllegalArgumentException is thrown to the client.
	 * @param fileName file containing list of students
	 * @throws IllegalArgumentException to the client if a FileNotFoundException is caught.
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * add a course to the catalog using given imformation
	 * @param name name
	 * @param title title
	 * @param section section
	 * @param credits credits
	 * @param instructorId instructor ID
	 * @param enrollmentCap course's enrollment cap
	 * @param meetingDays meetinf days
	 * @param startTime start time of course
	 * @param endTime end time of course
	 * @return whether the course can be added
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, 
			String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return false;
			}
		}
		try{
			Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
			catalog.add(c);
			return true;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage()); 
		}
	}
	
	/**
	 * remove course using course's name and section
	 * @param name name 
	 * @param section section
	 * @return whether the course can be removed
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * get Course from catalog
	 * @param name name
	 * @param section section
	 * @return the target Course
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}
	
	/**
	 * get the information about all the course in the catalog
	 * @return 2D String of course information
	 */
	public String[][] getCourseCatalog() {
		String[][] cata = new String[catalog.size()][3];
		for (int i = 0; i < catalog.size(); i++) {
			cata[i] = catalog.get(i).getShortDisplayArray();
		}
		return cata;
	}
	
    /**
     * Output method for activities IO
     * @param fileName fileName for output
     */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}

	}
}
