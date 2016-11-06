package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
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
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a new faculty to the directory
	 * @param firstName First name of faculty
	 * @param lastName Last name of faculty
	 * @param id Id of faculty
	 * @param email Email of faculty
	 * @param password Faculty password
	 * @param repeatPassword Faculty repeated password
	 * @param maxCourses Number of courses teaching
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
			String repeatPassword, int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || password.equals("") || repeatPassword.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(password.getBytes());
			hashPW = new String(digest1.digest());
			
			MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest2.update(repeatPassword.getBytes());
			repeatHashPW = new String(digest2.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
		
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		//If an IllegalArgumentException is throw, it's passed up from Faculty
		//to the GUI
		Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty s = facultyDirectory.get(i);
			if (s.getId().equals(faculty.getId())) {
				return false;
			}
		}
		return facultyDirectory.add(faculty);
		
	}
	
	/**
	 * Removes a faculty from the list
	 * @param id Faculty id
	 * @return true if the Faculty could be removed from the list
	 */
	public boolean removeFaculty(String id) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty s = facultyDirectory.get(i);
			if (s.getId().equals(id)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets a 2D array of information on faculty in directory
	 * @return directory 2D array of faculty first name, last name, and id
	 */
	public String[][] getFacultyDirectory() {
		String[][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty f = facultyDirectory.get(i);
			directory[i][0] = f.getFirstName();
			directory[i][1] = f.getLastName();
			directory[i][2] = f.getId();
		}
		
		return directory;
	}
	
	/**
	 * Saves the current directory to a file
	 * @param fileName Name of file
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
		
	}
	
	/**
	 * Gets a faculty member from the directory by id
	 * @param id Faculty id
	 * @return Faculty with specified id
	 */
	public Faculty getFacultyById(String id) {
		for(int i = 0; i < facultyDirectory.size(); i++){
			if(facultyDirectory.get(i).getId().equals(id)){
				if(facultyDirectory.get(i) == null){
					throw new IllegalArgumentException();
				} else {
					return facultyDirectory.get(i);
				}
			}
		}
		return null;
		
	}

}
