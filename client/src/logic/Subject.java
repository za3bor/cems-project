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
 * Represents a subject.
 */
public class Subject {
    /**
     * The singleton instance of the Subject class.
     */
    private static Subject instance;
    
    /**
     * The ID of the subject.
     */
    private String ID;
    
    /**
     * The name of the subject.
     */
    private String SubjectName;

    /**
     * Constructs a new Subject with the specified ID and subject name.
     *
     * @param iD           The ID of the subject.
     * @param subjectName  The name of the subject.
     */
	private Subject(String iD, String subjectName) {
		ID = iD;
		SubjectName = subjectName;
	}
    /**
     * Retrieves the singleton instance of the Subject class.
     *
     * @param ID           The ID of the subject.
     * @param SubjectName  The name of the subject.
     * @return The singleton instance of the Subject class.
     */
	public static Subject getInstance(String ID, String SubjectName) {
        if (instance == null) {
            instance = new Subject(ID, SubjectName);
        }
        return instance;
    }
    /**
     * Retrieves the ID of the subject.
     *
     * @return The ID of the subject.
     */
	public String getID() {
		return ID;
	}
    /**
     * Sets the ID of the subject.
     *
     * @param iD The ID of the subject.
     */
	public void setID(String iD) {
		ID = iD;
	}
    /**
     * Retrieves the name of the subject.
     *
     * @return The name of the subject.
     */
	public String getSubjectName() {
		return SubjectName;
	}
    /**
     * Sets the name of the subject.
     *
     * @param subjectName The name of the subject.
     */
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}
    /**
     * Converts a row from a database result set into a Subject object.
     *
     * @param row The row from the database result set.
     * @return The converted Subject object.
     */
	public static Subject convertSubject(Map<String, Object> row) {
		String ID = (String) row.get("ID");
        String subjectName = (String) row.get("SubjectName");
        return getInstance(ID, subjectName);
    }
}