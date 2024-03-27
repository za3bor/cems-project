package logic;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
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
 * Represents the details of a started exam.
 */
public class StartExam implements Serializable{
	/**
	 * The start number of the exam.
	 */
	private Integer Startnum;

	/**
	 * The ID of the exam.
	 */
	private String ExamID;

	/**
	 * The name of the lecturer conducting the exam.
	 */
	private String LecturerName;

	/**
	 * The code of the exam.
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
	 * The lock status of the exam.
	 * 0 - Not locked, 1 - Locked
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
	 * The date of the exam.
	 */
	private Date date;

	/**
	 * The additional duration of the exam in minutes.
	 */
	private int AddDuration;
	
	
	   /**
     * Constructs a new StartExam object with the specified parameters.
     *
     * @param startnum       The start number of the exam.
     * @param examID         The ID of the exam.
     * @param lecturerName   The name of the lecturer.
     * @param examCode       The code of the exam.
     * @param startTime      The start time of the exam.
     * @param duration       The duration of the exam.
     * @param isLocked       The lock status of the exam.
     * @param subject        The subject of the exam.
     * @param course         The course of the exam.
     * @param date           The date of the exam.
     * @param addDuration    The additional duration of the exam.
     */
	public StartExam(Integer startnum, String examID, String lecturerName, String examCode, Time startTime,
			int duration, int isLocked, String subject, String course, Date date, int addDuration) {
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
		this.date = date;
		AddDuration = addDuration;
	}

	
    /**
     * Retrieves the start number of the exam.
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
     * Retrieves the ID of the exam.
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
     * Retrieves the name of the lecturer.
     *
     * @return The name of the lecturer.
     */
	public String getLecturerName() {
		return LecturerName;
	}


	  /**
     * Sets the name of the lecturer.
     *
     * @param lecturerName The name of the lecturer.
     */
	public void setLecturerName(String lecturerName) {
		LecturerName = lecturerName;
	}


    /**
     * Retrieves the code of the exam.
     *
     * @return The code of the exam.
     */
	public String getExamCode() {
		return ExamCode;
	}


    /**
     * Sets the code of the exam.
     *
     * @param examCode The code of the exam.
     */
	public void setExamCode(String examCode) {
		ExamCode = examCode;
	}


	   /**
     * Retrieves the start time of the exam.
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
     * Retrieves the duration of the exam.
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
     * Retrieves the lock status of the exam.
     *
     * @return The lock status of the exam.
     */
	public int getIsLocked() {
		return IsLocked;
	}


    /**
     * Sets the lock status of the exam.
     *
     * @param isLocked The lock status of the exam.
     */
	public void setIsLocked(int isLocked) {
		IsLocked = isLocked;
	}


    /**
     * Retrieves the subject of the exam.
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
     * Retrieves the course of the exam.
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
     * Retrieves the date of the exam.
     *
     * @return The date of the exam.
     */
	public Date getDate() {
		return date;
	}


    /**
     * Sets the date of the exam.
     *
     * @param date The date of the exam.
     */
	public void setDate(Date date) {
		this.date = date;
	}


    /**
     * Retrieves the additional duration of the exam.
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
     * Returns a string representation of the StartExam object.
     *
     * @return A string representation of the StartExam object.
     */
	@Override
	public String toString() {
		return "StartExam [Startnum=" + Startnum + ", ExamID=" + ExamID + ", LecturerName=" + LecturerName
				+ ", ExamCode=" + ExamCode + ", StartTime=" + StartTime + ", Duration=" + Duration + ", IsLocked="
				+ IsLocked + ", Subject=" + Subject + ", Course=" + Course + ", date=" + date + ", AddDuration="
				+ AddDuration + "]";
	}



    /**
     * Converts a row from a database result set into a StartExam object.
     *
     * @param row The row from the database result set.
     * @return The converted StartExam object.
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
	        Date date = (Date) row.get("Date");
	        int addduration = row.get("AddDuration") != null ? ((Integer) row.get("AddDuration")).intValue() : 0;
	        return new StartExam(id,examID, lecturerName, examCode, startTime, duration, isLocked, subject, course, date,addduration);
	}
}
