package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Reads students' records from a file and writes the records to another file 
 * 
 * @author Ketan Gohel
 * @author Xin Rao
 */
public class StudentRecordIO {

	/**
	 * Reads a file line by line by calling another method and stores the students' records into an array of Student objects
	 * 
	 * @param fileName name of file with students' records
	 * @return an array of Student objects
	 * @throws FileNotFoundException if the file does not exist on the file system
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> studentRecord = new SortedList<Student>();
		while(fileReader.hasNextLine()){
			try{
				Student student = proceedStudent(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < studentRecord.size(); i++) {
	                Student s = studentRecord.get(i);
	                if (student.equals(s)) {
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	                studentRecord.add(student);
	            }				
			} catch (IllegalArgumentException e) {
				//do nothing
			}
		}
		fileReader.close();
		return studentRecord;
	}

	/**
	 * Reads a line and stores all the information from the line into one Student object
	 * 
	 * @param str the line that is being read
	 * @return a Student object with the information from param, str
	 */
	private static Student proceedStudent(String str) {
		Scanner input = new Scanner(str);
		input.useDelimiter(",");
		String firstName;
		String lastName;
		String id;
		String email;
		String hashPW;
		int maxCredits = 0; 
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
			maxCredits = input.nextInt();
		} catch(NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException("Invalid max credits");
		}
		if(maxCredits != 0){
			Student temp = new Student(firstName, lastName, id, email, hashPW, maxCredits);
			input.close();
			return temp;
		} else {
			Student temp = new Student(firstName, lastName, id, email, hashPW);
			input.close();
			return temp;
		}
	}

	/**
	 * Prints the students' records onto a new file one Student object at time
	 * 
	 * @param fileName name of the new file
	 * @param studentDirectory an array of Student objects
	 * @throws IOException if unable to write to the file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
		    fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();	
	}

}
