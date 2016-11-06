package edu.ncsu.csc216.pack_scheduler.directory;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Maintains a directory for faculty instructors
 * @author dndereef
 *
 */
public class FacultyDirectory {
	
	/** List of students in the directory */
	private LinkedList<Faculty> facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Constructs a new Faculty directory
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}
	
	/**
	 * Creates an empty faculty directory list
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
		
	}
	
	/**
	 * Reads in faculty from a file
	 * @param fileName Name of file
	 */
	public void loadFacultyFromFile(String fileName) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Adds a new faculty to the directory
	 * @param firstName First name of faculty
	 * @param lastName Last name of faculty
	 * @param id Id of faculty
	 * @param email Email of faculty
	 * @param pw Faculty password
	 * @param pw2 Faculty repeated password
	 * @param maxCourses Number of courses teaching
	 */
	public void addFaculty(String firstName, String lastName, String id, String email, String pw,
			String pw2, int maxCourses) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Removes a faculty from the list
	 * @param id Faculty id
	 * @return true if the Faculty could be removed from the list
	 */
	public boolean removeFaculty(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Gets a 2D array of information on faculty in directory
	 * @return
	 */
	public String[][] getFacultyDirectory() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Saves the current directory to a file
	 * @param fileName
	 */
	public void saveFacultyDirectory(String fileName) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Gets a faculty member from the directory by id
	 * @param id Faculty id
	 * @return
	 */
	public Faculty getFacultyById(String id) {
		return null;
		
	}

}
