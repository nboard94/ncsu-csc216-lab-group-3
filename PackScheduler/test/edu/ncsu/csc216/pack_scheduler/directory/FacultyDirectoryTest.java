package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class FacultyDirectoryTest {

	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_COURSES = 3;
	
	/**
	 * Resets faculty_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		//Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory fd = new FacultyDirectory();
		assertFalse(fd.removeFaculty("sesmith5"));
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests newFacultyDirectory
	 */
	@Test
	public void testNewFacultyDirectory() {
		//Test that if there are faculty in the directory, they are removed 
		FacultyDirectory fd = new FacultyDirectory();
		
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(10, fd.getFacultyDirectory().length);
		
		fd.newFacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests loadFacultyFromFile
	 */
	@Test
	public void testLoadStudentsFromFile() {
		FacultyDirectory fd = new FacultyDirectory();
				
		//Test valid file
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(10, fd.getFacultyDirectory().length);
		
		//Test invalid file
		String invalidFile = "invalidstudents.txt";
		try {
			fd.loadFacultyFromFile(invalidFile);
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file " + invalidFile, e.getMessage());
		}
		
		//Test non-existing file
		String notFile = "ojawefpojcspoje.txt";
		try {
			fd.loadFacultyFromFile(notFile);
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file " + notFile, e.getMessage());
		}
	}

	/**
	 * Tests addFaculty
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
		
		//Test valid Faculty
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		assertFalse(sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		String [][] studentDirectory = sd.getFacultyDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
		assertTrue(sd.addFaculty(FIRST_NAME, LAST_NAME, "other_id", EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		
		//Test invalid student with invalid password
		try {
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_COURSES);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "pw", "pww", MAX_COURSES);
		} catch (IllegalArgumentException e) {
			assertEquals("Passwords do not match", e.getMessage());
		}
		try {
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "pw", null, MAX_COURSES);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", "pw", MAX_COURSES);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "pw", "", MAX_COURSES);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
	}

	/**
	 * Tests removeFaculty
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
				
		//Add students and remove
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(10, sd.getFacultyDirectory().length);
		assertTrue(sd.removeFaculty("efrost"));
		String [][] studentDirectory = sd.getFacultyDirectory();
		assertEquals(9, studentDirectory.length);
		assertEquals("Zahir", studentDirectory[5][0]);
		assertEquals("King", studentDirectory[5][1]);
		assertEquals("zking", studentDirectory[5][2]);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory().
	 */
	@Test
	public void testSaveFacultyDirectory() {
		FacultyDirectory sd = new FacultyDirectory();
		
		//Add a student
		sd.addFaculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getFacultyDirectory().length);
		sd.saveFacultyDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
		
		//Writing to invalid file
		String invalidPath = "test-invalid/invalidtest.txt";
		try {
			sd.saveFacultyDirectory(invalidPath);
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to write to file " + invalidPath, e.getMessage());
		}
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
