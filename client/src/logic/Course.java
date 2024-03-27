package logic;

import java.io.Serializable;

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
 * The `Course` class represents a course.
 * It contains information such as the course ID, name, subject ID, and department.
 */
public class Course implements Serializable {
    /**
     * The singleton instance of the Course class.
     */
    private static Course instance;

    /**
     * The ID of the course.
     */
    private String ID;

    /**
     * The name of the course.
     */
    private String courseName;

    /**
     * The ID of the subject to which the course belongs.
     */
    private String ID_Subject;

    /**
     * The department associated with the course.
     */
    private String department;

    /**
     * Private constructor for creating a Course instance.
     * Use the getInstance method to obtain an instance of Course.
     *
     * @param ID           The ID of the course.
     * @param courseName   The name of the course.
     * @param ID_Subject   The ID of the subject to which the course belongs.
     * @param department   The department associated with the course.
     */
    private Course(String ID, String courseName, String ID_Subject, String department) {
        this.ID = ID;
        this.courseName = courseName;
        this.ID_Subject = ID_Subject;
        this.department = department;
    }
    /**
     * Returns an instance of Course.
     *
     * @param ID           The ID of the course.
     * @param courseName   The name of the course.
     * @param ID_Subject   The ID of the subject to which the course belongs.
     * @param department   The department associated with the course.
     * @return An instance of Course.
     */
    public static Course getInstance(String ID, String courseName, String ID_Subject, String department) {
        instance = new Course(ID, courseName, ID_Subject, department);
        return instance;
    }
    /**
     * Converts a row from a database result set into a Course object.
     *
     * @param row The row from the database result set containing course information.
     * @return A Course object.
     */
    public static Course convertCourse(Map<String, Object> row) {
        String ID = (String) row.get("ID");
        String courseName = (String) row.get("CourseName");
        String ID_Subject = (String) row.get("ID_Subject");
        String department = (String) row.get("Department");
        return getInstance(ID, courseName, ID_Subject, department);
    }
    /**
     * Returns the ID of the course.
     *
     * @return The ID of the course.
     */
    public String getID() {
        return ID;
    }
    /**
     * Sets the ID of the course.
     *
     * @param ID The ID of the course.
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    /**
     * Returns the name of the course.
     *
     * @return The name of the course.
     */
    public String getCourseName() {
        return courseName;
    }
    /**
     * Sets the name of the course.
     *
     * @param courseName The name of the course.
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    /**
     * Returns the ID of the subject to which the course belongs.
     *
     * @return The ID of the subject to which the course belongs.
     */
    public String getID_Subject() {
        return ID_Subject;
    }
    /**
     * Sets the ID of the subject to which the course belongs.
     *
     * @param ID_Subject The ID of the subject to which the course belongs.
     */
    public void setID_Subject(String ID_Subject) {
        this.ID_Subject = ID_Subject;
    }
    /**
     * Returns the department associated with the course.
     *
     * @return The department associated with the course.
     */
    public String getDepartment() {
        return department;
    }
    /**
     * Sets the department associated with the course.
     *
     * @param department The department associated with the course.
     */
    public void setDepartment(String department) {
        this.department = department;
    }
}