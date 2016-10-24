package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * 
 * @author Xin Rao
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new File(fileName));
	    SortedList<Course> courses = new SortedList<Course>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Course course = readCourse(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course c = courses.get(i);
	                if (course.getName().equals(c.getName()) &&
	                        course.getSection().equals(c.getSection())) {
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	                courses.add(course);
	            }
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
	    fileReader.close();
	    return courses;
	}

    private static Course readCourse(String str) {
		Scanner input = new Scanner(str);
		input.useDelimiter(",");
		String name;
		String title;
		String section;
		int credits;
		String instructorId;
		int enrollmentCap;
		String meetingDays;
		int startTime = 0;
		int endTime = 0;
		try{
			name = input.next();
		} catch(NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException();
		}
		try{
			title = input.next();
		} catch(NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException();
		}
		try{
			section = input.next();
		} catch(NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException();
		}
		try{
			credits = input.nextInt();
		} catch( NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException();
		}
		try{
			instructorId = input.next();
		} catch( NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException();
		}
		try{
			enrollmentCap = input.nextInt();
		} catch( NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException();
		}
		try{
			meetingDays = input.next();
		} catch( NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException();
		}
		if(meetingDays.equals("A")){
//			if(startTime != 0 && endTime != 0){
//				input.close();
//				throw new IllegalArgumentException();
//			}
			if(input.hasNext()){
				input.close();
				throw new IllegalArgumentException();
			}
			Course temp = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays);
			input.close();
			return temp;
		} else {
			try{
				startTime = input.nextInt();
			} catch( NoSuchElementException e) {
				input.close();
				throw new IllegalArgumentException();
			}
			try{
				endTime = input.nextInt();
			} catch( NoSuchElementException e) {
				input.close();
				throw new IllegalArgumentException();
			}
			Course temp = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
			input.close();
			return temp;
		}
	}
    /**
     * Output method for activities IO
     * @param fileName fileName for output
     * @param courses course Sortedlist for output
     * @throws IOException exceptions that file cannot be write
     */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
	
		for (int i = 0; i < courses.size(); i++) {
		    fileWriter.println(courses.get(i).toString());
		}
	
		fileWriter.close();
	}

}
