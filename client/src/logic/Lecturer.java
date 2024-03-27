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
 * Represents a lecturer.
 */
public class Lecturer {
    /**
     * The instance of the Lecturer class.
     */
    private static Lecturer instance;

    /**
     * The ID of the lecturer.
     */
    private String ID;

    /**
     * The username of the lecturer.
     */
    private String Username;

    /**
     * The password of the lecturer.
     */
    private String Password;

    /**
     * The email of the lecturer.
     */
    private String Email;

    /**
     * The phone number of the lecturer.
     */
    private String PhoneNumber;

    /**
     * The first name of the lecturer.
     */
    private String FirstName;

    /**
     * The last name of the lecturer.
     */
    private String LastName;

    /**
     * The ID of the subject taught by the lecturer.
     */
    private String SubjectID;

    /**
     * The department of the lecturer.
     */
    private String Department;

    /**
     * Constructs a new instance of the Lecturer class.
     *
     * @param iD           The ID of the lecturer.
     * @param username     The username of the lecturer.
     * @param password     The password of the lecturer.
     * @param email        The email of the lecturer.
     * @param phoneNumber  The phone number of the lecturer.
     * @param firstName    The first name of the lecturer.
     * @param lastName     The last name of the lecturer.
     * @param subjectID    The ID of the subject taught by the lecturer.
     * @param department   The department of the lecturer.
     */

    private Lecturer(String iD, String username, String password, String email, String phoneNumber, String firstName,
                     String lastName, String subjectID, String department) {
        ID = iD;
        Username = username;
        Password = password;
        Email = email;
        PhoneNumber = phoneNumber;
        FirstName = firstName;
        LastName = lastName;
        SubjectID = subjectID;
        Department = department;
    }
    /**
     * Returns the singleton instance of the Lecturer class.
     * If the instance doesn't exist, it creates a new one.
     * 
     * @param iD           The ID of the lecturer.
     * @param username     The username of the lecturer.
     * @param password     The password of the lecturer.
     * @param email        The email of the lecturer.
     * @param phoneNumber  The phone number of the lecturer.
     * @param firstName    The first name of the lecturer.
     * @param lastName     The last name of the lecturer.
     * @param subjectID    The ID of the subject taught by the lecturer.
     * @param department   The department of the lecturer.
     * @return The instance of the Lecturer class.
     */
    public static synchronized Lecturer getInstance(String iD, String username, String password, String email,
                                                    String phoneNumber, String firstName, String lastName,
                                                    String subjectID, String department) {
        instance = new Lecturer(iD, username, password, email, phoneNumber, firstName, lastName, subjectID,
                                    department);
        return instance;
    }
    /**
     * Converts a row from a map into a Lecturer object.
     *
     * @param row  The map representing a row of data.
     * @return The Lecturer object.
     */
    public static Lecturer convertToLecturer(Map<String, Object> row) {
        String id = (String) row.get("ID");
        String username = (String) row.get("Username");
        String password = (String) row.get("Password");
        String email = (String) row.get("Email");
        String phoneNumber = (String) row.get("PhoneNumber");
        String firstName = (String) row.get("FirstName");
        String lastName = (String) row.get("LastName");
        String subjectID = (String) row.get("SubjectID");
        String department = (String) row.get("Department");
        return getInstance(id, username, password, email, phoneNumber, firstName, lastName, subjectID, department);
    }
    /**
     * Returns the ID of the lecturer.
    *
    * @return The ID of the lecturer.
    */
	public String getID() {
		return ID;
	}
    /**
     * Sets the ID of the lecturer.
     *
     * @param iD  The ID of the lecturer to set.
     */
	public void setID(String iD) {
		ID = iD;
	}
    /**
     * Returns the username of the lecturer.
     *
     * @return The username of the lecturer.
     */
	public String getUsername() {
		return Username;
	}
    /**
     * Sets the username of the lecturer.
     *
     * @param username  The username of the lecturer to set.
     */
	public void setUsername(String username) {
		Username = username;
	}
	   /**
     * Returns the password of the lecturer.
     *
     * @return The password of the lecturer.
     */
	public String getPassword() {
		return Password;
	}
    /**
     * Sets the password of the lecturer.
     *
     * @param password  The password of the lecturer to set.
     */
	public void setPassword(String password) {
		Password = password;
	}
    /**
     * Returns the email of the lecturer.
     *
     * @return The email of the lecturer.
     */
	public String getEmail() {
		return Email;
	}
    /**
     * Sets the email of the lecturer.
     *
     * @param email  The email of the lecturer to set.
     */
	public void setEmail(String email) {
		Email = email;
	}
    /**
     * Returns the phone number of the lecturer.
     *
     * @return The phone number of the lecturer.
     */
	public String getPhoneNumber() {
		return PhoneNumber;
	}
    /**
     * Sets the phone number of the lecturer.
     *
     * @param phoneNumber  The phone number of the lecturer to set.
     */
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

    /**
     * Returns the first name of the lecturer.
     *
     * @return The first name of the lecturer.
     */
	public String getFirstName() {
		return FirstName;
	}
    /**
     * Sets the first name of the lecturer.
     *
     * @param firstName  The first name of the lecturer to set.
     */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
    /**
     * Returns the last name of the lecturer.
     *
     * @return The last name of the lecturer.
     */
	public String getLastName() {
		return LastName;
	}
    /**
     * Sets the last name of the lecturer.
     *
     * @param lastName  The last name of the lecturer to set.
     */
	public void setLastName(String lastName) {
		LastName = lastName;
	}
    /**
     * Returns the ID of the subject taught by the lecturer.
     *
     * @return The ID of the subject taught by the lecturer.
     */
	public String getSubjectID() {
		return SubjectID;
	}
    /**
     * Sets the ID of the subject taught by the lecturer.
     *
     * @param subjectID  The ID of the subject to set.
     */
	public void setSubjectID(String subjectID) {
		SubjectID = subjectID;
	}
	   /**
     * Sets the department of the lecturer.
     *
     * @param department  The department of the lecturer to set.
     */
	public void setDepartment(String department) {
		Department = department;
	}
    /**
     * Returns the department of the lecturer.
     *
     * @return The department of the lecturer.
     */
	public String getDepartment() {
		return Department;
	}
	
}
