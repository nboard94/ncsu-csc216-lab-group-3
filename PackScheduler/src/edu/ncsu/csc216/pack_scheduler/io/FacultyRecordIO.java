package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Handles working with files for faculty information
 * @author dndereef
 *
 */
public class FacultyRecordIO {

	/**
	 * Reads a file for faculty information
	 * @param fileName Name of file being read
	 * @return facultyRecord list of faculty
	 * @throws FileNotFoundException If the file could not be found or is not
	 * properly configured
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultyRecord = new LinkedList<Faculty>();
		while(fileReader.hasNextLine()){
			try{
				Faculty faculty = proceedFaculty(fileReader.nextLine());
				if (faculty != null) {
					boolean duplicate = false;
					for (int i = 0; i < facultyRecord.size(); i++) {
						Faculty s = facultyRecord.get(i);
						if (faculty.equals(s)) {
							//it's a duplicate
							duplicate = true;
						}
					}
					if (!duplicate) {
						facultyRecord.add(faculty);
					}				
				}
			} catch (IllegalArgumentException e) {
				fileReader.close();
				throw new IllegalArgumentException(e);
			}
		}
		fileReader.close();
		return facultyRecord;
	}

	/**
	 * Prints the students' records onto a new file one Student object at time
	 * 
	 * @param fileName name of the new file
	 * @param studentDirectory an array of Student objects
	 * @throws IOException if unable to write to the file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < facultyDirectory.size(); i++) {
		    fileWriter.println(facultyDirectory.get(i).toString());
		}

		fileWriter.close();	
	}
	/**
	 * Reads a line and stores all the information from the line into one Student object
	 * 
	 * @param str the line that is being read
	 * @return a Student object with the information from param, str
	 */
	private static Faculty proceedFaculty(String str) {
		Scanner input = new Scanner(str);
		input.useDelimiter(",");
		String firstName;
		String lastName;
		String id;
		String email;
		String hashPW;
		int maxCourses = 0; 
		try{
			firstName = input.next();
		} catch(NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException("Invalid first name");
		}
		try{
			lastName = input.next();
		} catch(NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException("Invalid last name");
		}
		try{
			id = input.next();
		} catch(NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException("Invalid id");
		}
		try{
			email = input.next();
		} catch(NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException("Invalid email");
		}
		try{
			hashPW = input.next();
		} catch(NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException("Invalid password");
		}
		try{
			maxCourses = input.nextInt();
		} catch(NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException("Invalid max credits");
		}
		Faculty temp = null;
		try {
			temp = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
			input.close();
			return temp;
		} catch (Exception e) {
			return temp;
		}
	}
}
