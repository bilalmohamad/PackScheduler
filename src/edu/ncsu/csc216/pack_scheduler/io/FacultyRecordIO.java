package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.util.LinkedList;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * The FacultyRecordIO class provides static methods that support reading in faculty records 
 * from a file and writing faculty records to a file.
 * 
 * @author Bilal Mohamad, Tj Tutka, Qihao Lu
 *
 */
public class FacultyRecordIO {
	
	/**
	 * The constructor for the FacultyRecordIO class.
	 * Does nothing
	 */
	public FacultyRecordIO() {
		//Does nothing
	}
	
	/**
	 * Reads faculty records from a file and generates a list of valid Faculty objects. Any
	 * invalid faculty members are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Faculty records from
	 * @return a LinkedList of valid Faculty members
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException{
		int index = 0;
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		while (fileReader.hasNextLine()) {
			try {
				Faculty f = processFaculty(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < faculty.size(); i++) {
					User s = faculty.get(i);
					if (f.getId().equals(s.getId())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					faculty.add(index, f);
					index++;
				}
			} catch (Exception e) {
				// skip the line
			}
		}
		fileReader.close();
		return faculty;
		
	}
	
	
	/**
	 * Reads the Faculty member in the given line
	 * 
	 * @param nextLine the String line need to be read
	 * @return faculty member read from the given String line
	 */
	private static Faculty processFaculty(String nextLine) {
		String firstName = "";
		String lastName = "";
		String id = "";
		int maxCourses = 0;
		String email = "";
		String password = "";
		@SuppressWarnings("resource")
		Scanner readScan = new Scanner(nextLine).useDelimiter(",");
		Faculty faculty = null;
		try {
			if (!readScan.hasNextInt()) {
				firstName = readScan.next();
			}
			if (!readScan.hasNextInt()) {
				lastName = readScan.next();
			}
			if (!readScan.hasNextInt()) {
				id = readScan.next();
			}
			if (!readScan.hasNextInt()) {
				email = readScan.next();
			}
			if (!readScan.hasNextInt()) {
				password = readScan.next(); 
			}
			if (readScan.hasNextInt()) {
				maxCourses = readScan.nextInt();
			}
			readScan.close();
			faculty = new Faculty(firstName, lastName, id, email, password, maxCourses);
			return faculty;
		} catch (NoSuchElementException e) {
			readScan.close();
			throw new IllegalArgumentException();
		}		
	}
	
	
	/**
	 * Writes the given list of Faculty members to a file
	 * 
	 * @param fileName name of the file need to print the Faculty members on
	 * @param facultyDirectory LinkedList of the Faculty members needed to be save
	 * 
	 * @throws IOException if the filename cannot be found
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < facultyDirectory.size(); i++) {
			fileWriter.println(facultyDirectory.get(i).toString());
		}

		fileWriter.close();
	}
}
