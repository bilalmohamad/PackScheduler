package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Creates a registration manager to allow students to sign up for classes
 * @author Kyle,Seth,Qihao
 *
 */
public class RegistrationManager {
    
	/** creates the object for Registration */
    private static RegistrationManager instance;
    /** creates the object for CourseCatalog */
    private CourseCatalog courseCatalog;
    /** creates the object for StudentDirectory */
    private StudentDirectory studentDirectory;
    /** creates the object for User */
    private User registrar;
    /** creates the object for User */
    private User currentUser;
    /** creates the object for faculty */
    private FacultyDirectory facultyDirectory;
    /** checks to see if anyone is logged in */
    private boolean isLoggedIn = false;
    /** Hashing algorithm */
    private static final String HASH_ALGORITHM = "SHA-256";
    /** assigns the Prop_file to the registrar properties to allow the person to login */
    private static final String PROP_FILE = "registrar.properties";

    /**
     * Constructor for RegistrationManager. 
     * creates the Registrar along with StudentDirectory and CourseCatalog
     */
    private RegistrationManager() {
        createRegistrar();
        this.studentDirectory = new StudentDirectory();
        this.courseCatalog = new CourseCatalog();
        this.facultyDirectory = new FacultyDirectory();
    }
    
    /**
     * Creates a new registrar object from the indicated properties file
     * @throws IllegalArgumentException when the file, hashed pw, or registrar is incorrect it throws an error message
     */
    private void createRegistrar() {
    	Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		} 
    }
    
    /**
     * Creates a hashed string from the property file's password
     * @param pw the inputted password
     * @throws IllegalArgumentException if any of the variables are null it throws the error message
     * @return the hashed string
     */
    private String hashPW(String pw) {
        try {
            MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
            digest1.update(pw.getBytes());
            return new String(digest1.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Cannot hash password");
        }
    }
    
    /**
     * Use instance to create a manager
     * @return instance A Registration Manager
     */
    public static RegistrationManager getInstance() {
        if (instance == null) {
            instance = new RegistrationManager();
        }
        return instance;
    }
    
    /**
     * Get course catalog
     * @return courseCatalog course catalog
     */
    public CourseCatalog getCourseCatalog() {
        return courseCatalog;
    }
    
    /**
     * Get student directory
     * @return studentDirectory student directory
     */
    public StudentDirectory getStudentDirectory() {
        return studentDirectory;
    }
    

    /**
     * Login with specfic ID and password
     * @param id the ID of the user
     * @param password the password of the user
     * @throws IllegalArgumentException if the user or password don't match it throws an error message
     * @return true if login successfully
     */
    public boolean login(String id, String password) {
        if(isLoggedIn) {
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
		            isLoggedIn = true;
		            return true;
		        }
	        } catch (NoSuchAlgorithmException e) {
	            throw new IllegalArgumentException();
	        }
        }
        
        User s = studentDirectory.getStudentById(id);
        if (s == null ) {
        	s = facultyDirectory.getFacultyById(id);
        	if (s == null ) {
        		throw new IllegalArgumentException("User doesn't exist.");
        	}
        }
        
	    try {
		    MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
		    digest.update(password.getBytes());
		    String localHashPW = new String(digest.digest());
		    if (s.getPassword().equals(localHashPW)) {
		    	currentUser = s;
		        isLoggedIn = true;
		        return true;
		    }
	    } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException();
        }   
        
        return false;
    }

    /**
     * Let current user log out
     */
    public void logout() {
        currentUser = null;
        isLoggedIn = false;
    }
    
    /**
     * Get the current user
     * @return The user using the program.
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Clear course catalog and student directory
     */
    public void clearData() {
        if(courseCatalog != null && studentDirectory != null) {
            courseCatalog.newCourseCatalog();
            studentDirectory.newStudentDirectory();
            facultyDirectory.newFacultyDirectory();
            isLoggedIn = false;
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
            Schedule schedule = s.getSchedule();
            CourseRoll roll = c.getCourseRoll();
            
            if (s.canAdd(c) && roll.canEnroll(s)) {
                schedule.addCourseToSchedule(c);
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
            c.getCourseRoll().drop(s);
            return s.getSchedule().removeCourseFromSchedule(c);
        } catch (IllegalArgumentException e) {
            return false; 
        }
    }
    
    
    /**
     * Adds the entered faculty member to the entered course
     * 
     * @param course	the course to add the faculty member to
     * @param faculty	the faculty member being added to the course
     * 
     * @return 	true 	if the Faculty member was added to the course
     * 			false	otherwise
     */
    public boolean addFacultyToCourse (Course course, Faculty faculty) {
    	
    	if (currentUser != null && currentUser instanceof Registrar) {
			return faculty.getSchedule().addCourseToSchedule(course);
		} else {
			throw new IllegalArgumentException("Illegal Action");
		}
    }
    
    
    /**
     * Removes the entered faculty member from the entered course
     * 
     * @param course	the course to remove the faculty member from
     * @param faculty	the faculty member being removed
     * 
     * @return 	true 	if the Faculty member was removed from the course
     * 			false	otherwise
     */
    public boolean removeFacultyFromCourse (Course course, Faculty faculty) {
    	
        if (currentUser == null || !(currentUser instanceof Registrar)) {
            throw new IllegalArgumentException("Illegal Action");
        }
        try {
            return faculty.getSchedule().removeCourseFromSchedule(course);
        } catch (IllegalArgumentException e) {
            return false; 
        }
    }
    
    
    /**
     * Resets the entered faculty member's schedule
     * 
     * @param faculty	the faculty member that will have their schedule resetted
     */
    public void resetFacultySchedule(Faculty faculty) {
        if (currentUser == null || !(currentUser instanceof Registrar)) {
            throw new IllegalArgumentException("Illegal Action");
        }
        try {
            faculty.getSchedule().resetSchedule();
        } 
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
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
            throw new IllegalArgumentException();
        }
    }
    
    /**
     *  Create Registrar with a certain user
     * @author TJ
     *
     */
    public static class Registrar extends User {
        /**
         *
         * Create a registrar user with the user id and password 
         * in the registrar.properties file.
         * @param firstName first name of the student
         * @param lastName last name of the student
         * @param id id of the student
         * @param email email of the student
         * @param hashPW password of the student
         */
        public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
            super(firstName, lastName, id, email, hashPW);
        }
    }

    
    /**
     * Retrieves the current facultyDirectory
     * @return the current facultyDirectory
     */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}
}