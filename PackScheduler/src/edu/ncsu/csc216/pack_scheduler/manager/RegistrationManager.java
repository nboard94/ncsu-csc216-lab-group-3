package edu.ncsu.csc216.pack_scheduler.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;
/**
 * RegistrationManager that helps users login to the system
 * User can also login as registrar
 * @author Xin Rao
 * @author Justin Irene
 * @author Anthony Strapp
 *
 */
public class RegistrationManager {
	/** Instance of RegistrationManager */
	private static RegistrationManager instance;
	/** List of courses */
	private CourseCatalog courseCatalog;
	/** List of students */
	private StudentDirectory studentDirectory;
	/** List of faculty */
	private FacultyDirectory facultyDirectory;
	/** Manager of school records */
	private User registrar;
	/** The user currently logged into the system */
	private User currentUser;
	
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Password for Registrar*/
	private static final String PW = "Regi5tr@r";
	/** Hashed password */
	private static String hashPW;

	// Static code block for hashing the registrar user's password
	{
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(PW.getBytes());
			hashPW = new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Initializes fields for instance
	 */
	private RegistrationManager() {
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
		registrar = new Registrar();
	}
	
	/**
	 * create a instance of RegistrationManager
	 * @return new copy of RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * get Course catalog
	 * @return new CourseCatalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * get student directory
	 * @return new StudentDirectory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Gets faculty directory
	 * @return facultyDirectory List of faculty
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}
	/**
	 * login in a user
	 * check local hashed password and the one has already hashed in file
	 * @param id student's id
	 * @param password student's password
	 * @return whether user can login
	 */
	public boolean login(String id, String password) {
		if(id == null || password == null){
			return false;
		}
		if(studentDirectory == null){
			return false;
		}
		if(currentUser != null){
			return false;
		}
		if (registrar.getId().equals(id)) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (registrar.getPassword().equals(localHashPW)) {
					currentUser = registrar;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		} else {
			User s = studentDirectory.getStudentById(id);
			if(s == null) {
				s = facultyDirectory.getFacultyById(id);
				if (s == null)
					throw new IllegalArgumentException("User doesn't exist.");
			}
			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (s.getPassword().equals(localHashPW)) {
					currentUser = s;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}
		return false;
	}
	
	/**
	 * logout a current user
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * get the current user login the system
	 * @return current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * clear data for course catalog and student directory
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}

	/**
	 * create a Registrar that could successfully login the system
	 * 
	 * @author Xin Rao
	 * @author Justin Irene
	 * @author Anthony Strapp
	 *
	 */
	private static class Registrar extends User {

		private static final String FIRST_NAME = "Wolf";
		private static final String LAST_NAME = "Scheduler";
		private static final String ID = "registrar";
		private static final String EMAIL = "registrar@ncsu.edu";

		/**
		 * Create a registrar user with the user id of registrar and password of
		 * Regi5tr@r. Note that hard coding passwords in a project is HORRIBLY
		 * INSECURE, but it simplifies testing here. This should NEVER be done
		 * in practice!
		 */
		public Registrar() {
			super(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPW);
		}
	}
    /**
 * Returns true if the logged in student can enroll in the given course.
 * @param c Course to enroll in
 * @return true if enrolled
 */
public boolean enrollStudentInCourse(Course c) {
    if (currentUser == null || !(currentUser instanceof Student)) {
        throw new IllegalArgumentException("Illegal Action");
    }
    try {
        Student s = (Student)currentUser;
        CourseRoll roll = c.getCourseRoll();
        if (s.canAdd(c) && roll.canEnroll(s)) {
     //       schedule.addCourseToSchedule(c);
            roll.enroll(s);
            return true;
        }
        
    } catch (IllegalArgumentException e) {
        return false;
    }
    return false;
}

/**
 * Returns true if the logged in student can drop the given course.
 * @param c Course to drop
 * @return true if dropped
 */
public boolean dropStudentFromCourse(Course c) {
    if (currentUser == null || !(currentUser instanceof Student)) {
        throw new IllegalArgumentException("Illegal Action");
    }
    try {
        Student s = (Student)currentUser;
        if (!c.getCourseRoll().roll.contains(s)) {
        	return false;
        }
        c.getCourseRoll().drop(s);
        return s.canAdd(c);
        	//s.getSchedule().removeCourseFromSchedule(c);
         
    } catch (IllegalArgumentException e) {
        return false; 
    }
    
}

/**
 * Resets the logged in student's schedule by dropping them
 * from every course and then resetting the schedule.
 */
public void resetSchedule() {
    if (currentUser == null || !(currentUser instanceof Student)) {
        throw new IllegalArgumentException("Illegal Action");
    }
    try {
        Student s = (Student)currentUser;
        Schedule schedule = s.getSchedule();
        String [][] scheduleArray = schedule.getScheduledCourses();
        for (int i = 0; i < scheduleArray.length; i++) {
            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
            c.getCourseRoll().drop(s);
        }
        schedule.resetSchedule();
    } catch (IllegalArgumentException e) {
        //do nothing 
    }
}
}