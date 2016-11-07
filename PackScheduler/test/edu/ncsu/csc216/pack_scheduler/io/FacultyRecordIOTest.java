package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

public class FacultyRecordIOTest {

	/** Valid faculty records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Invalid faculty records */
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";

	/** Expected results for valid students */
	private final String validFaculty1 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
	private final String validFaculty2 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	private final String validFaculty3 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	private final String validFaculty4 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
	private final String validFaculty5 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
	private final String validFaculty6 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
	private final String validFaculty7 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
	private final String validFaculty8 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";

	/** Array to hold expected results of students */
	private final String[] validFacultyMems = { validFaculty1, validFaculty2, validFaculty3, validFaculty4,
			validFaculty5, validFaculty6, validFaculty7, validFaculty8 };

	/** Hashed password */
	private String hashPW;
	/** Hashed password algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Resets faculty_records.txt for use in other tests.
	 */
	@Before
	public void setUp() {
		try {
			new FacultyRecordIO();
		} catch (IllegalArgumentException e) {
			fail("Cannot create Object");
		}
		try {
			String password = "pw";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			hashPW = new String(digest.digest());

			for (int i = 0; i < validFacultyMems.length; i++) {
				validFacultyMems[i] = validFacultyMems[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Tests readFacultyRecords
	 */
	@Test
	public void testReadFacultyRecords() {
		
		LinkedList<Faculty> f;
		try {
			f = FacultyRecordIO.readFacultyRecords(invalidTestFile);
			assertEquals(0, f.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
		
		try {
			f = FacultyRecordIO.readFacultyRecords("test-files/invalid_course_records.txt");
			assertEquals(0, f.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
		
		try {
			f = FacultyRecordIO.readFacultyRecords("test-files/numbers.txt");
			assertEquals(0, f.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
		
		try {
			f = FacultyRecordIO.readFacultyRecords("test-files/duplicate_faculty.txt");
			assertEquals(1, f.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
		try {
			f = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, f.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
		
			
		try {
			LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, faculty.size());

			for (int i = 0; i < validFacultyMems.length; i++) {
				assertEquals(validFacultyMems[i], faculty.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}

		
	}
	
	/**
	 * Tests writeFacultyRecords
	 */
	@Test
	public void testWriteFacultyRecords() {
		LinkedList<Faculty> f = new LinkedList<Faculty>();
		try {
			f = FacultyRecordIO.readFacultyRecords(validTestFile);
		} catch (FileNotFoundException e1) {
			fail();
		}
		// Assumption that you are using a hash of "pw" stored in hashPW

		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", f);
		} catch (IOException e) {
			fail();
		}

		checkFiles("test-files/expected_full_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}

	/**
	 * Helper method to compare two files for the same contents.
	 * 
	 * @param expFile
	 *            expected file output
	 * @param actFile
	 *            actual file output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
