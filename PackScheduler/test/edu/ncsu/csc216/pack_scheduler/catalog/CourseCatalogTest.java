package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;


/**
 * CourseCatalog
 * @author Xin Rao
 * @author Ketan Gohel
 *
 */
public class CourseCatalogTest {

	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	private static final int ENROLLMENT_CAP = 10;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	

	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception any possible exception for setup
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Tests WolfScheduler().
	 */
	@Test
	public void testCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		catalog.loadCoursesFromFile(validTestFile);
		assertEquals(8, catalog.getCourseCatalog().length);	
		
		catalog.loadCoursesFromFile(invalidTestFile);
		assertEquals(0, catalog.getCourseCatalog().length);	
	}

	/**
	 * Test getCourseFromCatalog()
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog ws = new CourseCatalog();
		ws.loadCoursesFromFile(validTestFile);
		
		//Attempt to get a course that doesn't exist
		assertNull(ws.getCourseFromCatalog("CSC492", "001"));
		
		//Attempt to get a course that does exist
		Activity c = new Course(NAME, TITLE, SECTION, CREDITS, null, 12, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(c, ws.getCourseFromCatalog("CSC216", "001"));
	}
	
	/**
	 * Test addCourseToCatalog()
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog ws = new CourseCatalog();
		ws.loadCoursesFromFile(validTestFile);
		
		//Attempt to add a course that doesn't exist
		assertTrue(ws.addCourseToCatalog("CSC492", "C#", "001", 4, "xrao", 10, "MT", 1440, 1550));
		assertEquals(9, ws.getCourseCatalog().length);
		String [] course = ws.getCourseCatalog()[8];
		assertEquals("CSC492", course[0]);
		assertEquals("001", course[1]);
		assertEquals("C#", course[2]);
		assertEquals(ws.getCourseFromCatalog("CSC492", "001").getMeetingString(), course[3]);
		
		
		//Attempt to add a course that does exist
		assertFalse(ws.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(9, ws.getCourseCatalog().length);
		
		//Attempt to add a course that invalid
		try{
			assertFalse(ws.addCourseToCatalog("CSC491", "C#", "012", 4, "xrao", 10, "HV", 1440, 1550));
		} catch (IllegalArgumentException e){
			assertEquals(9, ws.getCourseCatalog().length);
		}
	}
	
	/**
	 * Test removeCourseFromCatalog()
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog ws = new CourseCatalog();
		
		//Attempt to remove from empty schedule
		assertFalse(ws.removeCourseFromCatalog(NAME, SECTION));
		
		//Attempt to remove a Course exist
		ws.loadCoursesFromFile(validTestFile);
		ws.removeCourseFromCatalog(NAME, SECTION);
		assertEquals(7, ws.getCourseCatalog().length);
	}
	
	/**
	 * Test WolfScheduler.resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
		CourseCatalog ws = new CourseCatalog();
		ws.loadCoursesFromFile(validTestFile);
		assertEquals(8, ws.getCourseCatalog().length);
		ws.newCourseCatalog();
		assertEquals(0, ws.getCourseCatalog().length);
	}
	
	/**
	 * Test WolfScheduler.getCourseCatalog().
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog ws = new CourseCatalog();
		ws.loadCoursesFromFile(validTestFile);
		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalog = ws.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		assertEquals("MW 9:10AM-11:00AM", catalog[0][3]);
		//Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		assertEquals("MW 11:20AM-1:10PM", catalog[1][3]);
		//Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		assertEquals("TH 11:20AM-1:10PM", catalog[2][3]);
		//Row 3
		assertEquals("CSC216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Programming Concepts - Java", catalog[3][2]);
		assertEquals("TH 1:30PM-2:45PM", catalog[3][3]);
		//Row 4
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Programming Concepts - Java", catalog[4][2]);
		assertEquals("MW 1:30PM-2:45PM", catalog[4][3]);
		//Row 5
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Programming Concepts - Java", catalog[5][2]);
		assertEquals("Arranged", catalog[5][3]);
		//Row 6
		assertEquals("CSC226", catalog[6][0]);
		assertEquals("001", catalog[6][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[6][2]);
		assertEquals("MWF 9:35AM-10:25AM", catalog[6][3]);
		//Row 7
		assertEquals("CSC230", catalog[7][0]);
		assertEquals("001", catalog[7][1]);
		assertEquals("C and Software Tools", catalog[7][2]);
		assertEquals("MW 11:45AM-1:00PM", catalog[7][3]);
	}
	
	/**
	 * Test WolfScheduler.getCourseCatalog().
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog ws = new CourseCatalog();
		ws.loadCoursesFromFile(invalidTestFile);
		assertEquals(0, ws.getCourseCatalog().length);
		ws.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");
		
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File (expFile));
			Scanner actScanner = new Scanner(new File(actFile));
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
