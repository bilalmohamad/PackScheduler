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
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * class used to read the students list in given file
 * 
 * @author yueyu
 * @author Tianxin Jia
 * @author Qihao Lu
 */
public class StudentRecordIO {
	/**
	 * Reads student records from a file and generates a list of valid Students. Any
	 * invalid students are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName
	 *            file to read Student records from
	 * @return a SortedList of valid Students
	 * @throws FileNotFoundException
	 *             if the file cannot be found or read
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> students = new SortedList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < students.size(); i++) {
					User s = students.get(i);
					if (student.getFirstName().equals(s.getFirstName())
							&& student.getLastName().equals(s.getLastName())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					students.add(student);
				}
			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		return students;
	}

	/**
	 * read the Student in the given line
	 * 
	 * @param nextLine
	 *            the String Line need to be read
	 * @return Student student read from the given String line
	 */
	private static Student processStudent(String nextLine) {

		String firstName = "";
		String lastName = "";
		String id = "";
		int maxCredits = 0;
		String email = "";
		String password = "";
		@SuppressWarnings("resource")
		Scanner readScan = new Scanner(nextLine).useDelimiter(",");
		Student student = null;
		try {
			firstName = readScan.next();
			lastName = readScan.next();
			id = readScan.next();
			email = readScan.next();
			password = readScan.next();
			maxCredits = readScan.nextInt();
			student = new Student(firstName, lastName, id, email, password, maxCredits);
		} catch (NoSuchElementException e) {
			readScan.close();
			throw new IllegalArgumentException();
		}
		readScan.close();
		return student;
	}

	/**
	 * Writes the given list of Students to a file
	 * 
	 * @param fileName
	 *            filename of the file need to print the Students on
	 * @param studentDirectory
	 *           SortedList of the Students needed to be save
	 * @throws IOException
	 *             if the filename cannot be found
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {

		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();

	}

}
