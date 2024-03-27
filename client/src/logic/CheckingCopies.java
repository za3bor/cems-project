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
 * The CheckingCopies class represents the information about copies checking for exam results.
 * It implements the Serializable interface to support serialization and deserialization.
 */
public class CheckingCopies implements Serializable {
    /**
     * The auto-increment value of the CheckingCopies.
     */
    private Integer autoincrement;
    
    /**
     * The ExamResult ID of the CheckingCopies.
     */
    private Integer ExamResultID;
    
    /**
     * The start number of the CheckingCopies.
     */
    private Integer startnum;
    
    /**
     * The Exam ID of the CheckingCopies.
     */
    private String ExamID;
    
    /**
     * The ID of the first student in the CheckingCopies.
     */
    private String Student1ID;
    
    /**
     * The ID of the second student in the CheckingCopies.
     */
    private String Student2ID;
    
    /**
     * The similarity percentage between the students' answers in the CheckingCopies.
     */
    private String SimilarPrecent;
    /**
     * Constructs a new CheckingCopies object with the specified parameters.
     *
     * @param autoincrement  The autoincrement value.
     * @param examResultID   The ID of the exam result.
     * @param startnum       The starting number.
     * @param examID         The ID of the exam.
     * @param student1id     The ID of the first student.
     * @param student2id     The ID of the second student.
     * @param similarPrecent The similarity percentage.
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
     * Converts a row from a database result set to a CheckingCopies object.
     *
     * @param row The row from a database result set.
     * @return The CheckingCopies object.
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
     * Returns the value of the autoincrement.
     *
     * @return The value of the autoincrement.
     */
	public Integer getAutoincrement() {
		return autoincrement;
	}
    /**
     * Sets the value of the autoincrement.
     *
     * @param autoincrement The new value of the autoincrement.
     */
	public void setAutoincrement(Integer autoincrement) {
		this.autoincrement = autoincrement;
	}
    /**
     * Returns the ID of the exam result.
     *
     * @return The ID of the exam result.
     */
	public Integer getExamResultID() {
		return ExamResultID;
	}
    /**
     * Sets the ID of the exam result.
     *
     * @param examResultID The new ID of the exam result.
     */
	public void setExamResultID(Integer examResultID) {
		ExamResultID = examResultID;
	}
    /**
     * Returns the starting number.
     *
     * @return The starting number.
     */
	public Integer getStartnum() {
		return startnum;
	}
	   /**
     * Sets the starting number.
     *
     * @param startnum The new starting number.
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
     * @param examID The new ID of the exam.
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
     * Sets the ID of the first student.
     *
     * @param student1id The new ID of the first student.
     */
	public void setStudent1ID(String student1id) {
		Student1ID = student1id;
	}
    /**
     * Returns the ID of the second student.
     *
     * @return The ID of the second student.
     */
	public String getStudent2ID() {
		return Student2ID;
	}
    /**
     * Sets the ID of the second student.
     *
     * @param student2id The new ID of the second student.
     */
	public void setStudent2ID(String student2id) {
		Student2ID = student2id;
	}
    /**
     * Returns the similarity percentage.
     *
     * @return The similarity percentage.
     */
	public String getSimilarPrecent() {
		return SimilarPrecent;
	}
    /**
     * Sets the similarity percentage.
     *
     * @param similarPrecent The new similarity percentage.
     */
	public void setSimilarPrecent(String similarPrecent) {
		SimilarPrecent = similarPrecent;
	}
    /**
     * Returns a string representation of the CheckingCopies object.
     *
     * @return A string representation of the CheckingCopies object.
     */
	@Override
	public String toString() {
		return "CheckingCopies [autoincrement=" + autoincrement + ", ExamResultID=" + ExamResultID + ", startnum="
				+ startnum + ", ExamID=" + ExamID + ", Student1ID=" + Student1ID + ", Student2ID=" + Student2ID
				+ ", SimilarPrecent=" + SimilarPrecent + "]";
	}
}
