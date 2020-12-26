package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;


/**
 * This class will create a list of Faculty members and can add, remove, and retrieve members
 * @author Bilal Mohamad, Qihao Lu, Tj Tutka
 *
 */
public class FacultyDirectory {

	
	/** Constant used for hashCode method */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/** List of all the Faculty members */
	private LinkedList<Faculty> facultyDirectory;
	
	/**
	 * The constructor for the FacultyDirectory class
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}
	
	
	/**
	 * Creates a LinkedList of Faculty members
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}
	
	
	/**
	 * Loads Faculty member information from an entered file
	 * @param fileName	the name of the file to read information from
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	
	/**
	 * Adds a Faculty member to the list of Faculty members
	 * 
	 * @param firstName			the first name of the Faculty member
	 * @param lastName			the last name of the Faculty member
	 * @param id				the id of the Faculty member
	 * @param email				the email of the Faculty member
	 * @param password			the password of the Faculty member
	 * @param repeatPassword	the password of the Faculty member but repeated
	 * @param maxCredits		the max credits of the Faculty member
	 * 
	 * @return	true if the Facult member is added otherwise false
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCredits) {
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
		
		//If an IllegalArgumentException is thrown, it's passed up from Student
		//to the GUI
		Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCredits);
		
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(faculty.getId())) {
				return false;
			}
		}
		return facultyDirectory.add(faculty);
	}
	
	/**
	 * Removes the student with the given id from the list of students with the given id.
	 * Returns true if the student is removed and false if the student is not in the list.
	 * @param facultyId the id of the Faculty member
	 * @return true if removed otherwise false
	 */
	public boolean removeFaculty(String facultyId) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(facultyId)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns all students in the directory with a column for first name, last name, and id.
	 * @return String array containing students first name, last name, and id.
	 */
	public String[][] getFacultyDirectory() {
		String [][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}
	
	/**
	 * Saves all students in the directory to a file.
	 * @param fileName name of file to save students to.
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

	/**
	 * Returns a student by their ID number
	 * @param a students ID
	 * @return The correct student
	 */
	public Faculty getFacultyById(String a) {
		for (int i = 0; i < this.facultyDirectory.size(); i++) {
			if (facultyDirectory.get(i).getId().equals(a)) {
				return facultyDirectory.get(i);
			}
		}
		return null;
	}
}
