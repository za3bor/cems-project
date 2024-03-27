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
 * Represents a checking copy of an exam result, which contains information about the comparison
 * between two students' answers.
 * 
 * The class provides methods to convert a row from a database query result into a CheckingCopies object.
 */
public class CheckingCopies {
	/**
	 * The auto-increment ID of the checking copy.
	 */
	private Integer autoincrement;
	/**
	 * The ID of the exam result associated with this checking copy.
	 */
	private Integer ExamResultID;
	/**
	 * The start number of the exam.
	 */
	private Integer startnum;
	/**
	 * The ID of the exam.
	 */
	private String ExamID;
	/**
	 * The ID of the first student.
	 */
	private String Student1ID;
	/**
	 * The ID of the second student.
	 */
	private String Student2ID;
	/**
	 * The percentage of similarity between the answers of the two students.
	 */
	private String SimilarPrecent;
	
	/**
	 * Creates a new CheckingCopies object with the specified parameters.
	 * 
	 * @param autoincrement The auto-increment ID of the checking copy.
	 * @param examResultID The ID of the exam result associated with this checking copy.
	 * @param startnum The start number of the exam.
	 * @param examID The ID of the exam.
	 * @param student1id The ID of the first student.
	 * @param student2id The ID of the second student.
	 * @param similarPrecent The percentage of similarity between the answers of the two students.
	 */
	public CheckingCopies(Integer autoincrement, Integer examResultID, Integer startnum, String examID,
			String student1id, String student2id, String similarPrecent) {
		super();
		this.autoincrement = autoincrement;
		ExamResultID = examResultID;
		this.startnum = startnum;
		ExamID = examID;
		Student1ID = student1id;
		Student2ID = student2id;
		SimilarPrecent = similarPrecent;
	}

	/**
	 * Converts a row from a database query result into a CheckingCopies object.
	 * 
	 * @param row The row containing the checking copy data.
	 * @return A CheckingCopies object representing the row.
	 */
	public static CheckingCopies convertToCheckingCopies(Map<String, Object> row) {
        Integer autoincrement = (Integer) row.get("autoincrement");
        Integer examresultID = (Integer) row.get("ExamResultID");
        Integer startnum = (Integer) row.get("startnum");
        String examID = (String) row.get("ExamID");
        String student1ID = (String) row.get("Student1ID");
        String student2ID = (String) row.get("Student2ID");
        String similarPercent = (String) row.get("SimilarPrecent");
        return new CheckingCopies(autoincrement, examresultID, startnum, examID, student1ID, student2ID, similarPercent);
    }

	/**
	 * Returns the auto-increment ID of the checking copy.
	 * 
	 * @return The auto-increment ID of the checking copy.
	 */
	public Integer getAutoincrement() {
		return autoincrement;
	}

	/**
	 * Sets the auto-increment ID of the checking copy.
	 * 
	 * @param autoincrement The auto-increment ID of the checking copy.
	 */
	public void setAutoincrement(Integer autoincrement) {
		this.autoincrement = autoincrement;
	}

	/**
	 * Returns the ID of the exam result associated with this checking copy.
	 * 
	 * @return The ID of the exam result associated with this checking copy.
	 */
	public Integer getExamResultID() {
		return ExamResultID;
	}

	/**
	 * Sets the ID of the exam result associated with this checking copy.
	 * 
	 * @param examResultID The ID of the exam result associated with this checking copy.
	 */
	public void setExamResultID(Integer examResultID) {
		ExamResultID = examResultID;
	}

	/**
	 * Returns the start number of the exam.
	 * 
	 * @return The start number of the exam.
	 */
	public Integer getStartnum() {
		return startnum;
	}

	/**
	 * Sets the start number of the exam.
	 * 
	 * @param startnum The start number of the exam.
	 */
	public void setStartnum(Integer startnum) {
		this.startnum = startnum;
	}

	/**
	 * Returns the ID of the exam.
	 * 
	 * @return The ID of the exam.
	 */
	public String getExamID() {
		return ExamID;
	}

	/**
	 * Sets the ID of the exam.
	 * 
	 * @param examID The ID of the exam.
	 */
	public void setExamID(String examID) {
		ExamID = examID;
	}

	/**
	 * Returns the ID of the first student.
	 * 
	 * @return The ID of the first student.
	 */
	public String getStudent1ID() {
		return Student1ID;
	}

	/**
	 * Sets the ID of the first student in the `CheckingCopies` object.
	 * 
	 * @param student1id The ID of the first student to set.
	 */
	public void setStudent1ID(String student1id) {
		Student1ID = student1id;
	}

	/**
	 * Returns the ID of the second student in the `CheckingCopies` object.
	 * 
	 * @return The ID of the second student.
	 */
	public String getStudent2ID() {
		return Student2ID;
	}

	/**
	 * Sets the ID of the second student in the `CheckingCopies` object.
	 * 
	 * @param student2id The ID of the second student to set.
	 */
	public void setStudent2ID(String student2id) {
		Student2ID = student2id;
	}

	/**
	 * Returns the similarity percentage between the copies in the `CheckingCopies` object.
	 * 
	 * @return The similarity percentage.
	 */
	public String getSimilarPrecent() {
		return SimilarPrecent;
	}

	/**
	 * Sets the similarity percentage between the copies in the `CheckingCopies` object.
	 * 
	 * @param similarPrecent The similarity percentage to set.
	 */
	public void setSimilarPrecent(String similarPrecent) {
		SimilarPrecent = similarPrecent;
	}
	/**
	 * Returns a string representation of the `CheckingCopies` object.
	 * 
	 * @return A string representation of the object, containing the values of its properties.
	 */
	@Override
	public String toString() {
		return "CheckingCopies [autoincrement=" + autoincrement + ", ExamResultID=" + ExamResultID + ", startnum="
				+ startnum + ", ExamID=" + ExamID + ", Student1ID=" + Student1ID + ", Student2ID=" + Student2ID
				+ ", SimilarPrecent=" + SimilarPrecent + "]";
	}
}
