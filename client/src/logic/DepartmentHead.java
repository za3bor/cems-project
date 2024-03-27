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
 * The DepartmentHead class represents a department head user in the system.
 */
public class DepartmentHead {
    /**
     * The singleton instance of the DepartmentHead class.
     */
	private static DepartmentHead instance;
    /**
     * The unique ID of the department head.
     */
    private String ID;
    
    /**
     * The username of the department head.
     */
    private String username;
    
    /**
     * The password of the department head.
     */
    private String password;
    
    /**
     * The email address of the department head.
     */
    private String email;
    
    /**
     * The phone number of the department head.
     */
    private String phoneNumber;
    
    /**
     * The first name of the department head.
     */
    private String firstName;
    
    /**
     * The last name of the department head.
     */
    private String lastName;
    
    /**
     * The department associated with the department head.
     */
    private String department;

    /**
     * Constructs a new DepartmentHead object with the specified attributes.
     *
     * @param ID           the ID of the department head
     * @param username     the username of the department head
     * @param password     the password of the department head
     * @param email        the email address of the department head
     * @param phoneNumber  the phone number of the department head
     * @param firstName    the first name of the department head
     * @param lastName     the last name of the department head
     * @param department   the department associated with the department head
     */
    private DepartmentHead(String ID, String username, String password, String email, String phoneNumber,
            String firstName, String lastName, String department) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }
    /**
     * Returns a singleton instance of the DepartmentHead class with the specified attributes.
     *
     * @param ID           the ID of the department head
     * @param username     the username of the department head
     * @param password     the password of the department head
     * @param email        the email address of the department head
     * @param phoneNumber  the phone number of the department head
     * @param firstName    the first name of the department head
     * @param lastName     the last name of the department head
     * @param department   the department associated with the department head
     * @return the singleton instance of the DepartmentHead class
     */
    public static DepartmentHead getInstance(String ID, String username, String password, String email,
            String phoneNumber, String firstName, String lastName, String department) {
        instance = new DepartmentHead(ID, username, password, email, phoneNumber, firstName, lastName, department);
        return instance;
    }
    /**
     * Returns the ID of the department head.
     *
     * @return the ID of the department head
     */
    public String getID() {
        return ID;
    }
    /**
     * Sets the ID of the department head.
     *
     * @param ID the ID of the department head
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    /**
     * Returns the username of the department head.
     *
     * @return the username of the department head
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the department head.
     *
     * @param username the username of the department head
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Returns the password of the department head.
     *
     * @return the password of the department head
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the password of the department head.
     *
     * @param password the password of the department head
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Returns the email address of the department head.
     *
     * @return the email address of the department head
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the email address of the department head.
     *
     * @param email the email address of the department head
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Returns the phone number of the department head.
     *
     * @return the phone number of the department head
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Sets the phone number of the department head.
     *
     * @param phoneNumber the phone number of the department head
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Returns the first name of the department head.
     *
     * @return the first name of the department head
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets the first name of the department head.
     *
     * @param firstName the first name of the department head
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Returns the last name of the department head.
     *
     * @return the last name of the department head
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets the last name of the department head.
     *
     * @param lastName the last name of the department head
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Returns the department associated with the department head.
     *
     * @return the department associated with the department head
     */
    public String getDepartment() {
        return department;
    }
    /**
     * Sets the department associated with the department head.
     *
     * @param department the department associated with the department head
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    /**
     * Converts a row from a database query result to a DepartmentHead object.
     *
     * @param row the row containing the department head data
     * @return the DepartmentHead object created from the row data
     */
    public static DepartmentHead convertToDepartmentHead(Map<String, Object> row) {
        String ID = (String) row.get("ID");
        String username = (String) row.get("Username");
        String password = (String) row.get("Password");
        String email = (String) row.get("Email");
        String phoneNumber = (String) row.get("PhoneNumber");
        String firstName = (String) row.get("FirstName");
        String lastName = (String) row.get("LastName");
        String department = (String) row.get("department");
        return getInstance(ID, username, password, email, phoneNumber, firstName, lastName, department);
    }
}
