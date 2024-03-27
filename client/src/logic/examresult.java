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
 * Represents an exam result.
 * This class provides methods to get and set various properties of an exam result.
 */
public class examresult implements Serializable {
	  /**
     * The singleton instance of the examresult class.
     */
	private static examresult instance;
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
     * The answers provided in the exam.
     */
    private String ExamAnswer;

    /**
     * The points allocated per question.
     */
    private String pointperquestion;

    /**
     * The correct answers for the exam.
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
     * The status of the exam result.
     */
    private String status;

    /**
     * A note regarding the exam result.
     */
    private String note;

    /**
     * A note for the student regarding the exam result.
     */
    private String noteforstudent;

    /**
     * The ID of the student.
     */
    private String StudentID;

    /**
     * The number of times the exam was started.
     */
    private Integer startexamNum;

    /**
     * The duration taken to complete the exam.
     */
    private String DurationTake;

    /**
     * The form submission status of the exam result.
     */
    private String FormSubmission;
    /**
     * Constructs an instance of the examresult class.
     * @param iD The ID of the exam result.
     * @param examID The ID of the exam.
     * @param examResult The result of the exam.
     * @param subject The subject of the exam.
     * @param course The course of the exam.
     * @param examAnswer The answers provided in the exam.
     * @param pointperquestion The points allocated per question.
     * @param rightanswer The correct answers for the exam.
     * @param lecturerID The ID of the lecturer.
     * @param date The date of the exam.
     * @param time The time of the exam.
     * @param status The status of the exam result.
     * @param note A note regarding the exam result.
     * @param noteforstudent A note for the student regarding the exam result.
     * @param studentID The ID of the student.
     * @param startexamNum The number of times the exam was started.
     * @param durationTake The duration taken to complete the exam.
     * @param formSubmission The form submission status of the exam result.
     */
    private examresult(Integer iD, String examID, String examResult, String subject, String course, String examAnswer,
                      String pointperquestion, String rightanswer, String lecturerID, String date, String time,
                      String status, String note, String noteforstudent, String studentID, Integer startexamNum, String durationTake,
                      String formSubmission) {
        super();
        ID = iD;
        ExamID = examID;
        ExamResult = examResult;
        Subject = subject;
        Course = course;
        ExamAnswer = examAnswer;
        this.pointperquestion = pointperquestion;
        this.rightanswer = rightanswer;
        this.lecturerID = lecturerID;
        this.date = date;
        this.time = time;
        this.status = status;
        this.note = note;
        this.noteforstudent = noteforstudent;
        StudentID = studentID;
        this.startexamNum = startexamNum;
        DurationTake = durationTake;
        FormSubmission = formSubmission;
    }
    /**
     * Returns a singleton instance of the examresult class.
     *
     * @param iD             The ID of the exam result.
     * @param examID         The ID of the exam.
     * @param examResult     The result of the exam.
     * @param subject        The subject of the exam.
     * @param course         The course of the exam.
     * @param examAnswer     The answers provided in the exam.
     * @param pointperquestion The points allocated per question.
     * @param rightanswer    The correct answers for the exam.
     * @param lecturerID     The ID of the lecturer.
     * @param date           The date of the exam.
     * @param time           The time of the exam.
     * @param status         The status of the exam result.
     * @param note           A note regarding the exam result.
     * @param noteforstudent A note for the student regarding the exam result.
     * @param studentID      The ID of the student.
     * @param startexamNum   The number of times the exam was started.
     * @param durationTake   The duration taken to complete the exam.
     * @param formSubmission The form submission status of the exam result.
     * @return The singleton instance of the examresult class.
     */
    public static examresult getInstance(Integer iD, String examID, String examResult, String subject, String course,
                                         String examAnswer, String pointperquestion, String rightanswer,
                                         String lecturerID, String date, String time, String status, String note,String noteforstudent,
                                         String studentID, Integer startexamNum, String durationTake,
                                         String formSubmission) {
        synchronized (examresult.class) {
                    instance = new examresult(iD, examID, examResult, subject, course, examAnswer, pointperquestion,
                            rightanswer, lecturerID, date, time, status, note,noteforstudent, studentID, startexamNum, durationTake,
                            formSubmission);
        }
        return instance;
    }
    /**
     * Converts a row of data to an exam result instance.
     *
     * @param row The row of data containing exam result information.
     * @return An instance of the examresult class representing the exam result.
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
        String Noteforstudent = (String) row.get("noteforstudent");
        Integer startexamNum = (Integer) row.get("startexamNum");
        String durationTake = (String) row.get("DurationTake");
        String formSubmission = (String) row.get("FormSubmission");
        return getInstance(ID, ExamID, ExamResult, Subject, Course, ExamAnswer, PointPerQuestion, RightAnswer, Lecturer,
                date, time, Status, Note,Noteforstudent, StudentID, startexamNum, durationTake, formSubmission);
    }

    /**
     * Returns the singleton instance of the examresult class.
     *
     * @return The singleton instance of the examresult class.
     */
	public static examresult getInstance() {
		return instance;
	}

	   /**
     * Sets the singleton instance of the examresult class.
     *
     * @param instance The singleton instance of the examresult class.
     */
	public static void setInstance(examresult instance) {
		examresult.instance = instance;
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
	 * Gets the subject of the exam result.
	 *
	 * @return The subject of the exam result.
	 */
	public String getSubject() {
		return Subject;
	}
	/**
	 * Sets the subject of the exam result.
	 *
	 * @param subject The subject of the exam result.
	 */
	public void setSubject(String subject) {
		Subject = subject;
	}
	/**
	 * Gets the course of the exam result.
	 *
	 * @return The course of the exam result.
	 */
	public String getCourse() {
		return Course;
	}
	/**
	 * Sets the course of the exam result.
	 *
	 * @param course The course of the exam result.
	 */
	public void setCourse(String course) {
		Course = course;
	}
	/**
	 * Gets the exam answer.
	 *
	 * @return The exam answer.
	 */
	public String getExamAnswer() {
		return ExamAnswer;
	}
	/**
	 * Sets the exam answer.
	 *
	 * @param examAnswer The exam answer.
	 */
	public void setExamAnswer(String examAnswer) {
		ExamAnswer = examAnswer;
	}
	/**
	 * Gets the point per question.
	 *
	 * @return The point per question.
	 */
	public String getPointperquestion() {
		return pointperquestion;
	}
	/**
	 * Sets the point per question.
	 *
	 * @param pointperquestion The point per question.
	 */
	public void setPointperquestion(String pointperquestion) {
		this.pointperquestion = pointperquestion;
	}
	/**
	 * Gets the right answer.
	 *
	 * @return The right answer.
	 */
	public String getRightanswer() {
		return rightanswer;
	}
	/**
	 * Sets the right answer.
	 *
	 * @param rightanswer The right answer.
	 */
	public void setRightanswer(String rightanswer) {
		this.rightanswer = rightanswer;
	}
	/**
	 * Gets the lecturer ID.
	 *
	 * @return The lecturer ID.
	 */
	public String getLecturerID() {
		return lecturerID;
	}
	/**
	 * Sets the lecturer ID.
	 *
	 * @param lecturerID The lecturer ID.
	 */
	public void setLecturerID(String lecturerID) {
		this.lecturerID = lecturerID;
	}
	/**
	 * Gets the date.
	 *
	 * @return The date.
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Sets the date.
	 *
	 * @param date The date.
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * Gets the time.
	 *
	 * @return The time.
	 */
	public String getTime() {
		return time;
	}
	/**
	 * Sets the time.
	 *
	 * @param time The time.
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * Gets the status.
	 *
	 * @return The status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * Sets the status.
	 *
	 * @param status The status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * Gets the note.
	 *
	 * @return The note.
	 */
	public String getNote() {
		return note;
	}
	/**
	 * Sets the note.
	 *
	 * @param note The note.
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * Gets the note for the student.
	 *
	 * @return The note for the student.
	 */
	public String getNoteforstudent() {
		return noteforstudent;
	}
	/**
	 * Sets the note for the student.
	 *
	 * @param noteforstudent The note for the student.
	 */
	public void setNoteforstudent(String noteforstudent) {
		this.noteforstudent = noteforstudent;
	}
	/**
	 * Gets the student ID.
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
	 * Gets the start exam number.
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
	 * Gets the duration taken for the exam.
	 *
	 * @return The duration taken for the exam.
	 */
	public String getDurationTake() {
		return DurationTake;
	}

/**
 * Sets the duration taken for the exam.
 *
 * @param durationTake The duration taken for the exam.
 */
	public void setDurationTake(String durationTake) {
		DurationTake = durationTake;
	}
	/**
	 * Gets the form submission.
	 *
	 * @return The form submission.
	 */
	public String getFormSubmission() {
		return FormSubmission;
	}
	/**
	 * Sets the form submission.
	 *
	 * @param formSubmission The form submission.
	 */
	public void setFormSubmission(String formSubmission) {
		FormSubmission = formSubmission;
	}
	/**
	 * Returns a string representation of the exam result object.
	 *
	 * @return A string representation of the exam result object.
	 */
	@Override
	public String toString() {
		return "examresult [ID=" + ID + ", ExamID=" + ExamID + ", ExamResult=" + ExamResult + ", Subject=" + Subject
				+ ", Course=" + Course + ", ExamAnswer=" + ExamAnswer + ", pointperquestion=" + pointperquestion
				+ ", rightanswer=" + rightanswer + ", lecturerID=" + lecturerID + ", date=" + date + ", time=" + time
				+ ", status=" + status + ", note=" + note + ", noteforstudent=" + noteforstudent + ", StudentID="
				+ StudentID + ", startexamNum=" + startexamNum + ", DurationTake=" + DurationTake + ", FormSubmission="
				+ FormSubmission + "]";
	}
}
