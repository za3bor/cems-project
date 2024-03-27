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
 * 
 * Represents an exam result.
 * 
 * This class stores information about a student's result in an exam, including the exam ID, subject, course,
 * exam answer, point per question, right answer, lecturer ID, date, time, status, note, student ID, and start exam number.
 * 
 * The class provides methods to get and set the properties, as well as a method to convert a database row into an `examresult` object.
 * 
 *
 */

public class examresult implements Serializable{
    /**
     * The ID of the exam result.
     */
	private Integer ID;
	  /**
     * The ID of the exam.
     */
	private String ExamID;
	   /**
     * The result of the exam.
     */
	private String ExamResult;
    /**
     * The subject of the exam.
     */
	private String Subject;
     /**
      * The course of the exam.
      */
	private String Course;
      /**
       * The student's answer in the exam.
       */
	private String ExamAnswer;
       /**
        * The point per question in the exam.
        */
	private String pointperquestion;
        /**
         * The correct answer in the exam.
         */
	private String rightanswer;
         
         /**
          * The ID of the lecturer.
          */
	private String lecturerID;
          /**
           * The date of the exam.
           */
	private String date; 
           /**
            * The time of the exam.
            */
	private String time;
            /**
             * The status of the exam.
             */	
	private String status;
             /**
              * A note associated with the exam.
              */
	private String note;
              /**
               * The student ID.
               */
	private String StudentID;
               /**
                * The start exam number.
                */
	private Integer startexamNum;
	
	/**
	 * Constructs an `examresult` object with the specified parameters.
	 * 
	 * @param iD The result ID.
	 * @param examID The exam ID.
	 * @param examResult The exam result.
	 * @param subject The exam subject.
	 * @param course The exam course.
	 * @param examAnswer The exam answer.
	 * @param pointperquestion The point per question in the exam.
	 * @param rightanswer The right answer in the exam.
	 * @param lecturerId The ID of the lecturer.
	 * @param date The date of the exam.
	 * @param time The time of the exam.
	 * @param status The status of the exam.
	 * @param note The note associated with the exam.
	 * @param studentID The student ID.
	 * @param startexamNum The start exam number.
	 */
	public examresult(Integer iD, String examID, String examResult, String subject, String course, String examAnswer,
			String pointperquestion, String rightanswer, String lecturerId, String date, String time, String status,
			String note, String studentID, Integer startexamNum) {
		super();
		ID = iD;
		ExamID = examID;
		ExamResult = examResult;
		Subject = subject;
		Course = course;
		ExamAnswer = examAnswer;
		this.pointperquestion = pointperquestion;
		this.rightanswer = rightanswer;
		this.lecturerID = lecturerId;
		this.date = date;
		this.time = time;
		this.status = status;
		this.note = note;
		StudentID = studentID;
		this.startexamNum = startexamNum;
	}
	/**
	 * Converts a database row into an `examresult` object.
	 * The row should contain the necessary fields for creating an `examresult` object.
	 * 
	 * @param row The row from the database result set.
	 * @return The created `examresult` object.
	 */
	public static examresult convertToExamResult(Map<String, Object> row) {
	    Integer ID = (Integer) row.get("ID");
	    String ExamID = (String) row.get("ExamID");
	    String ExamResult = (String) row.get("ExamResult");
	    String Subject = (String) row.get("Subject");
	    String Course = (String) row.get("Course");
	    String ExamAnswer = (String) row.get("ExamAnswer");
	    String PointPerQuestion = (String) row.get("pointperquestion");
	    String RightAnswer = (String) row.get("rightanswer");
	    String Lecturer = (String) row.get("lecturerID");
	    String date = (String) row.get("date");
	    String time = (String) row.get("time");
	    String Status = (String) row.get("status");
	    String StudentID = (String) row.get("StudentID");
	    String Note = (String) row.get("note");
	    Integer startexamNum = (Integer) row.get("startexamNum");
	    return new examresult(ID, ExamID, ExamResult, Subject, Course, ExamAnswer, PointPerQuestion,
	        RightAnswer, Lecturer, date, time, Status, Note,StudentID,startexamNum);
	}
    /**
     * Returns the ID of the exam result.
     * 
     * @return The ID of the exam result.
     */
	public Integer getID() {
		return ID;
	}
    /**
     * Sets the ID of the exam result.
     * 
     * @param iD The ID of the exam result.
     */
	public void setID(Integer iD) {
		ID = iD;
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
     * Returns the result of the exam.
     * 
     * @return The result of the exam.
     */
	public String getExamResult() {
		return ExamResult;
	}
    /**
     * Sets the result of the exam.
     * 
     * @param examResult The result of the exam.
     */
	public void setExamResult(String examResult) {
		ExamResult = examResult;
	}
    /**
     * Returns the subject of the exam.
     * 
     * @return The subject of the exam.
     */
	public String getSubject() {
		return Subject;
	}
    /**
     * Sets the subject of the exam.
     * 
     * @param subject The subject of the exam.
     */
	public void setSubject(String subject) {
		Subject = subject;
	}
    /**
     * Returns the course of the exam.
     * 
     * @return The course of the exam.
     */
	public String getCourse() {
		return Course;
	}
    /**
     * Sets the course of the exam.
     * 
     * @param course The course of the exam.
     */
	public void setCourse(String course) {
		Course = course;
	}
    /**
     * Returns the student's answer in the exam.
     * 
     * @return The student's answer in the exam.
     */
	public String getExamAnswer() {
		return ExamAnswer;
	}
    /**
     * Sets the student's answer in the exam.
     * 
     * @param examAnswer The student's answer in the exam.
     */
	public void setExamAnswer(String examAnswer) {
		ExamAnswer = examAnswer;
	}
	  /**
     * Returns the point per question in the exam.
     * 
     * @return The point per question in the exam.
     */
	public String getPointperquestion() {
		return pointperquestion;
	}
    /**
     * Sets the point per question in the exam.
     * 
     * @param pointperquestion The point per question in the exam.
     */
	public void setPointperquestion(String pointperquestion) {
		this.pointperquestion = pointperquestion;
	}
    /**
     * Returns the right answer in the exam.
     * 
     * @return The right answer in the exam.
     */
	public String getRightanswer() {
		return rightanswer;
	}
    /**
     * Sets the right answer in the exam.
     * 
     * @param rightanswer The right answer in the exam.
     */
	public void setRightanswer(String rightanswer) {
		this.rightanswer = rightanswer;
	}
	   /**
     * Returns the ID of the lecturer.
     * 
     * @return The ID of the lecturer.
     */
	public String getLecturerID() {
		return lecturerID;
	}
    /**
     * Sets the ID of the lecturer.
     * 
     * @param lecturerID The ID of the lecturer.
     */
	public void setLecturerID(String lecturerID) {
		this.lecturerID = lecturerID;
	}
    /**
     * Returns the date of the exam.
     * 
     * @return The date of the exam.
     */
	public String getDate() {
		return date;
	}
	   /**
     * Sets the date of the exam.
     * 
     * @param date The date of the exam.
     */
	public void setDate(String date) {
		this.date = date;
	}

    /**
     * Returns the time of the exam.
     * 
     * @return The time of the exam.
     */
	public String getTime() {
		return time;
	}
	   /**
     * Sets the time of the exam.
     * 
     * @param time The time of the exam.
     */
	public void setTime(String time) {
		this.time = time;
	}
    /**
     * Returns the status of the exam.
     * 
     * @return The status of the exam.
     */
	public String getStatus() {
		return status;
	}
	   /**
     * Sets the status of the exam.
     * 
     * @param status The status of the exam.
     */
	public void setStatus(String status) {
		this.status = status;
	}
    /**
     * Returns the note associated with the exam.
     * 
     * @return The note associated with the exam.
     */
	public String getNote() {
		return note;
	}
    /**
     * Sets the note associated with the exam.
     * 
     * @param note The note associated with the exam.
     */
	public void setNote(String note) {
		this.note = note;
	}
    /**
     * Returns the student ID.
     * 
     * @return The student ID.
     */
	public String getStudentID() {
		return StudentID;
	}
	   /**
     * Sets the student ID.
     * 
     * @param studentID The student ID.
     */
	public void setStudentID(String studentID) {
		StudentID = studentID;
	}
    /**
     * Returns the start exam number.
     * 
     * @return The start exam number.
     */
	public Integer getStartexamNum() {
		return startexamNum;
	}
    /**
     * Sets the start exam number.
     * 
     * @param startexamNum The start exam number.
     */
	public void setStartexamNum(Integer startexamNum) {
		this.startexamNum = startexamNum;
	}
    /**
     * Returns a string representation of the `examresult` object.
     * 
     * @return A string representation of the `examresult` object.
     */
	@Override
	public String toString() {
		return "examresult [ID=" + ID + ", ExamID=" + ExamID + ", ExamResult=" + ExamResult + ", Subject=" + Subject
				+ ", Course=" + Course + ", ExamAnswer=" + ExamAnswer + ", pointperquestion=" + pointperquestion
				+ ", rightanswer=" + rightanswer + ", lecturer=" + lecturerID + ", date=" + date + ", time=" + time
				+ ", status=" + status + ", note=" + note + ", StudentID=" + StudentID + ", startexamNum="
				+ startexamNum + "]";
	}
}
