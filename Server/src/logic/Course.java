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
 * 
 * The `Course` class represents a course in the system.
 * It stores information such as the course ID, course name, and subject ID.
 * 
 * The class provides methods to retrieve and set the values of its properties.
 * It also includes a static method to convert a row from a database result set into a `Course` object.
 * 
 */
public class Course {
	/**
	 * The ID of the course.
	 */
	private String ID;
	/**
	 * The name of the course.
	 */
	private String CourseName;
	/**
	 * The ID of the subject associated with the course.
	 */
	private String ID_Subject;
	
	/**
	 * Constructs a new `Course` object with the specified values.
	 * 
	 * @param iD          The ID of the course.
	 * @param courseName  The name of the course.
	 * @param iD_Subject  The ID of the subject associated with the course.
	 */
	public Course(String iD, String courseName, String iD_Subject) {
		super();
		ID = iD;
		CourseName = courseName;
		ID_Subject = iD_Subject;
	}
	/**
	 * Returns the ID of the course.
	 * 
	 * @return The course ID.
	 */
	public String getID() {
		return ID;
	}
	/**
	 * Sets the ID of the course.
	 * 
	 * @param iD The course ID to set.
	 */
	public void setID(String iD) {
		ID = iD;
	}
	/**
	 * Returns the name of the course.
	 * 
	 * @return The course name.
	 */
	public String getCourseName() {
		return CourseName;
	}
	/**
	 * Sets the name of the course.
	 * 
	 * @param courseName The course name to set.
	 */
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	/**
	 * Returns the ID of the subject associated with the course.
	 * 
	 * @return The subject ID.
	 */
	public String getID_Subject() {
		return ID_Subject;
	}
	/**
	 * Sets the ID of the subject associated with the course.
	 * 
	 * @param iD_Subject The subject ID to set.
	 */
	public void setID_Subject(String iD_Subject) {
		ID_Subject = iD_Subject;
	}
	/**
	 * Converts a row from a database result set into a `Course` object.
	 * 
	 * @param row The row from the result set.
	 * @return The `Course` object created from the row.
	 */
	public static Course convertToCourse(Map<String, Object> row) {
        String id = (String) row.get("ID");
        String courseName = (String) row.get("CourseName");
        String idSubject = (String) row.get("ID_Subject");
        return new Course(id, courseName, idSubject);
    }
	    

}
