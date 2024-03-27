package logic;

import java.util.Map;
/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * Represents a student.
 */
public class Student {
    /**
     * The ID of the student.
     */
    private String ID;
    
    /**
     * The username of the student.
     */
    private String Username;
    
    /**
     * The password of the student.
     */
    private String Password;
    
    /**
     * The email address of the student.
     */
    private String Email;
    
    /**
     * The phone number of the student.
     */
    private String PhoneNumber;
    
    /**
     * The first name of the student.
     */
    private String FirstName;
    
    /**
     * The last name of the student.
     */
    private String LastName;
    
    /**
     * The course of the student.
     */
    private String Course;
    
    /**
     * The department of the student.
     */
    private String Department;

    /**
     * Constructs a new Student object with the specified parameters.
     *
     * @param iD            The ID of the student.
     * @param username      The username of the student.
     * @param password      The password of the student.
     * @param email         The email address of the student.
     * @param phoneNumber   The phone number of the student.
     * @param firstName     The first name of the student.
     * @param lastName      The last name of the student.
     * @param course        The course of the student.
     * @param department    The department of the student.
     */
	public Student(String iD, String username, String password, String email, String phoneNumber, String firstName,
			String lastName, String course, String department) {
		super();
		ID = iD;
		Username = username;
		Password = password;
		Email = email;
		PhoneNumber = phoneNumber;
		FirstName = firstName;
		LastName = lastName;
		Course = course;
		Department = department;
	}
    /**
     * Retrieves the ID of the student.
     *
     * @return The ID of the student.
     */
	public String getID() {
		return ID;
	}

    /**
     * Sets the ID of the student.
     *
     * @param iD The ID of the student.
     */
	public void setID(String iD) {
		ID = iD;
	}
    /**
     * Retrieves the username of the student.
     *
     * @return The username of the student.
     */
	public String getUsername() {
		return Username;
	}

    /**
     * Sets the username of the student.
     *
     * @param username The username of the student.
     */
	public void setUsername(String username) {
		Username = username;
	}
    /**
     * Retrieves the password of the student.
     *
     * @return The password of the student.
     */
	public String getPassword() {
		return Password;
	}
    /**
     * Sets the password of the student.
     *
     * @param password The password of the student.
     */
	public void setPassword(String password) {
		Password = password;
	}
    /**
     * Retrieves the email address of the student.
     *
     * @return The email address of the student.
     */
	public String getEmail() {
		return Email;
	}
    /**
     * Sets the email address of the student.
     *
     * @param email The email address of the student.
     */
	public void setEmail(String email) {
		Email = email;
	}
    /**
     * Retrieves the phone number of the student.
     *
     * @return The phone number of the student.
     */
	public String getPhoneNumber() {
		return PhoneNumber;
	}
    /**
     * Sets the phone number of the student.
     *
     * @param phoneNumber The phone number of the student.
     */
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
    /**
     * Retrieves the first name of the student.
     *
     * @return The first name of the student.
     */
	public String getFirstName() {
		return FirstName;
	}
	   /**
     * Sets the first name of the student.
     *
     * @param firstName The first name of the student.
     */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
    /**
     * Retrieves the last name of the student.
     *
     * @return The last name of the student.
     */
	public String getLastName() {
		return LastName;
	}
    /**
     * Sets the last name of the student.
     *
     * @param lastName The last name of the student.
     */
	public void setLastName(String lastName) {
		LastName = lastName;
	}
    /**
     * Retrieves the course of the student.
     *
     * @return The course of the student.
     */
	public String getCourse() {
		return Course;
	}
    /**
     * Sets the course of the student.
     *
     * @param course The course of the student.
     */
	public void setCourse(String course) {
		Course = course;
	}
    /**
     * Retrieves the department of the student.
     *
     * @return The department of the student.
     */
	public String getDepartment() {
		return Department;
	}
    /**
     * Sets the department of the student.
     *
     * @param department The department of the student.
     */
	public void setDepartment(String department) {
		Department = department;
	}
    /**
     * Converts a row from a database result set into a Student object.
     *
     * @param row The row from the database result set.
     * @return The converted Student object.
     */
	public static Student convertToStudent(Map<String, Object> row) {
        String id = (String) row.get("ID");
        String username = (String) row.get("Username");
        String password = (String) row.get("Password");
        String email = (String) row.get("Email");
        String phoneNumber = (String) row.get("PhoneNumber");
        String firstName = (String) row.get("FirstName");
        String lastName = (String) row.get("LastName");
        String course = (String) row.get("Course");
        String Department = (String) row.get("Department");
        return new Student(id, username, password, email, phoneNumber, firstName, lastName, course, Department);
    }
}
