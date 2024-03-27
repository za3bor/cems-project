package logic;

import java.io.Serializable;
import java.sql.Time;
import java.util.Map;
/**
 * 
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * Represents a start exam entity.
 * 
 * This class stores information about a start exam, including the start number, exam ID, lecturer name,
 * exam code, start time, duration, locked status, subject, course, and additional duration.
 * 
 * The class provides methods to get and set the properties, as well as a method to convert a database row into a `StartExam` object.
 * 
 *
 */
public class StartExam implements Serializable{
    /**
     * The start number of the exam.
     */
	private Integer Startnum;
     /**
      * The exam ID.
      */
    private String ExamID;
      /**
       * The name of the lecturer.
       */
    private String LecturerName;
       /**
        * The exam code.
        */
     private String ExamCode;
        /**
         * The start time of the exam.
         */
     private Time StartTime;
         /**
          * The duration of the exam in minutes.
          */
     private int Duration;
          /**
           * The locked status of the exam.
           * 0 - Not locked
           * 1 - Locked
           */
      private int IsLocked;
           /**
            * The subject of the exam.
            */
      private String Subject;
            /**
             * The course of the exam.
             */
      private String Course;
            /**
             * The additional duration of the exam in minutes.
             */
      private int AddDuration;
	
	
    /**
     * Constructs a `StartExam` object with the specified parameters.
     *
     * @param startnum The start number of the exam.
     * @param examID The exam ID.
     * @param lecturerName The name of the lecturer.
     * @param examCode The exam code.
     * @param startTime The start time of the exam.
     * @param duration The duration of the exam.
     * @param isLocked The locked status of the exam.
     * @param subject The subject of the exam.
     * @param course The course of the exam.
     * @param addDuration The additional duration of the exam.
     */
	public StartExam(Integer startnum, String examID, String lecturerName, String examCode, Time startTime,
			int duration, int isLocked, String subject, String course, int addDuration) {
		super();
		Startnum = startnum;
		ExamID = examID;
		LecturerName = lecturerName;
		ExamCode = examCode;
		StartTime = startTime;
		Duration = duration;
		IsLocked = isLocked;
		Subject = subject;
		Course = course;
		AddDuration = addDuration;
	}
	
    /**
     * Returns the start number of the exam.
     * 
     * @return The start number of the exam.
     */
	public Integer getStartnum() {
		return Startnum;
	}



	 /**
     * Sets the start number of the exam.
     * 
     * @param startnum The start number of the exam.
     */
	public void setStartnum(Integer startnum) {
		Startnum = startnum;
	}


	   /**
     * Returns the exam ID.
     * 
     * @return The exam ID.
     */
	public String getExamID() {
		return ExamID;
	}


    /**
     * Sets the exam ID.
     * 
     * @param examID The exam ID.
     */
	public void setExamID(String examID) {
		ExamID = examID;
	}


    /**
     * Returns the lecturer name.
     * 
     * @return The lecturer name.
     */
	public String getLecturerName() {
		return LecturerName;
	}


    /**
     * Sets the lecturer name.
     * 
     * @param lecturerName The lecturer name.
     */
	public void setLecturerName(String lecturerName) {
		LecturerName = lecturerName;
	}


    /**
     * Returns the exam code.
     * 
     * @return The exam code.
     */
	public String getExamCode() {
		return ExamCode;
	}


    /**
     * Sets the exam code.
     * 
     * @param examCode The exam code.
     */
	public void setExamCode(String examCode) {
		ExamCode = examCode;
	}


    /**
     * Returns the start time of the exam.
     * 
     * @return The start time of the exam.
     */
	public Time getStartTime() {
		return StartTime;
	}


    /**
     * Sets the start time of the exam.
     * 
     * @param startTime The start time of the exam.
     */
	public void setStartTime(Time startTime) {
		StartTime = startTime;
	}


    /**
     * Returns the duration of the exam.
     * 
     * @return The duration of the exam.
     */
	public int getDuration() {
		return Duration;
	}


    /**
     * Sets the duration of the exam.
     * 
     * @param duration The duration of the exam.
     */
	public void setDuration(int duration) {
		Duration = duration;
	}


    /**
     * Returns the locked status of the exam.
     * 
     * @return The locked status of the exam.
     */
	public int getIsLocked() {
		return IsLocked;
	}


    /**
     * Sets the locked status of the exam.
     * 
     * @param isLocked The locked status of the exam.
     */
	public void setIsLocked(int isLocked) {
		IsLocked = isLocked;
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
     * Returns the additional duration of the exam.
     * 
     * @return The additional duration of the exam.
     */
	public int getAddDuration() {
		return AddDuration;
	}


    /**
     * Sets the additional duration of the exam.
     * 
     * @param addDuration The additional duration of the exam.
     */
	public void setAddDuration(int addDuration) {
		AddDuration = addDuration;
	}

	
    /**
     * Returns a string representation of the `StartExam` object.
     * 
     * @return A string representation of the `StartExam` object.
     */
	@Override
	public String toString() {
		return "StartExam [Startnum=" + Startnum + ", ExamID=" + ExamID + ", LecturerName=" + LecturerName
				+ ", ExamCode=" + ExamCode + ", StartTime=" + StartTime + ", Duration=" + Duration + ", IsLocked="
				+ IsLocked + ", Subject=" + Subject + ", Course=" + Course + ", AddDuration=" + AddDuration + "]";
	}
    /**
     * Converts a database row into a `StartExam` object.
     *
     * @param row The database row containing the start exam information.
     * @return The converted `StartExam` object.
     */
	public static StartExam convertToStartExam(Map<String, Object> row) {
			Integer  id = (Integer) row.get("Startnum");
	        String examID = (String) row.get("ExamID");
	        String lecturerName = (String) row.get("LecturerName");
	        String examCode = (String) row.get("ExamCode");
	        Time startTime = (Time) row.get("StartTime");
	        int duration = row.get("Duration") != null ? ((Integer) row.get("Duration")).intValue() : 0;
	        int isLocked = row.get("IsLocked") != null ? ((Integer) row.get("IsLocked")).intValue() : 0;
	        String subject = (String) row.get("Subject");
	        String course = (String) row.get("Course");
	        int addduration = row.get("AddDuration") != null ? ((Integer) row.get("AddDuration")).intValue() : 0;
	        return new StartExam(id,examID, lecturerName, examCode, startTime, duration, isLocked, subject, course,addduration);
	}
}
