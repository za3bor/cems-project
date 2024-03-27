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
 * The `Exam` class represents an exam in the system.
 * It stores information such as the exam ID, subject, course, duration, and various descriptions.
 * 
 * The class provides methods to retrieve and set the values of its properties.
 * It also includes a static method to convert a row from a database result set into an `Exam` object.
 * 
 * Note: This class implements the `Serializable` interface, indicating that its objects can be serialized.
 * 
 */
public class Exam implements Serializable {
	/**
	 * Additional information about the exam.
	 */
	private String info;
	/**
	 * The ID of the exam.
	 */
	private String ID;
	/**
	 * The subject of the exam.
	 */
	private String Subject;
	/**
	 * The course of the exam.
	 */
	private String Course;
	/**
	 * The duration of the exam in minutes.
	 */
	private int Duration;
	/**
	 * The description of the exam for lecturers.
	 */
	private String DescriptionForLecturers;
	/**
	 * The description of the exam for students.
	 */
	private String DescriptionForStudents;
	/**
	 * The question associated with the exam.
	 */
	private String question;
	/**
	 * The total point value for the exam.
	 */
	private String TotalPoint;
	/**
	 * The point value per question in the exam.
	 */
	private String PointPerQuestion;
	/**
	 * Constructs a new `Exam` object with default values.
	 */
	public Exam() {
    }
	/**
	 * Constructs a new `Exam` object with the specified values.
	 * 
	 * @param info                   Additional information about the exam.
	 * @param iD                     The ID of the exam.
	 * @param subject                The subject of the exam.
	 * @param course                 The course of the exam.
	 * @param duration               The duration of the exam in minutes.
	 * @param descriptionForLecturers The description of the exam for lecturers.
	 * @param descriptionForStudents The description of the exam for students.
	 * @param question               The question associated with the exam.
	 * @param totalPoint             The total point value for the exam.
	 * @param pointPerQuestion       The point value per question in the exam.
	 */
	public Exam(String info, String iD, String subject, String course, int duration, String descriptionForLecturers,
			String descriptionForStudents, String question, String totalPoint, String pointPerQuestion) {
		super();
		this.info = info;
		ID = iD;
		Subject = subject;
		Course = course;
		Duration = duration;
		DescriptionForLecturers = descriptionForLecturers;
		DescriptionForStudents = descriptionForStudents;
		this.question = question;
		TotalPoint = totalPoint;
		PointPerQuestion = pointPerQuestion;
	}
	/**
	 * Returns the additional information about the exam.
	 * 
	 * @return The exam info.
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * Sets the additional information about the exam.
	 * 
	 * @param info The exam info to set.
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * Returns the ID of the exam.
	 * 
	 * @return The exam ID.
	 */
	public String getID() {
		return ID;
	}
	/**
	 * Sets the ID of the exam.
	 * 
	 * @param iD The exam ID to set.
	 */
	public void setID(String iD) {
		ID = iD;
	}
	/**
	 * Returns the subject of the exam.
	 * 
	 * @return The exam subject.
	 */
	public String getSubject() {
		return Subject;
	}
	/**
	 * Sets the subject of the exam.
	 * 
	 * @param subject The exam subject to set.
	 */
	public void setSubject(String subject) {
		Subject = subject;
	}
	/**
	 * Returns the course of the exam.
	 * 
	 * @return The exam course.
	 */
	public String getCourse() {
		return Course;
	}
	/**
	 * Sets the course of the exam.
	 * 
	 * @param course The exam course to set.
	 */
	public void setCourse(String course) {
		Course = course;
	}
	/**
	 * Returns the duration of the exam in minutes.
	 * 
	 * @return The exam duration in minutes.
	 */
	public int getDuration() {
		return Duration;
	}
	/**
	 * Sets the duration of the exam in minutes.
	 * 
	 * @param duration The exam duration to set in minutes.
	 */
	public void setDuration(int duration) {
		Duration = duration;
	}
	/**
	 * Returns the description of the exam for lecturers.
	 * 
	 * @return The exam description for lecturers.
	 */
	public String getDescriptionForLecturers() {
		return DescriptionForLecturers;
	}
	/**
	 * Sets the description of the exam for lecturers.
	 * 
	 * @param descriptionForLecturers The exam description for lecturers to set.
	 */
	public void setDescriptionForLecturers(String descriptionForLecturers) {
		DescriptionForLecturers = descriptionForLecturers;
	}
	/**
	 * Returns the description of the exam for students.
	 * 
	 * @return The exam description for students.
	 */
	public String getDescriptionForStudents() {
		return DescriptionForStudents;
	}
	/**
	 * Sets the description of the exam for students.
	 * 
	 * @param descriptionForStudents The exam description for students to set.
	 */
	public void setDescriptionForStudents(String descriptionForStudents) {
		DescriptionForStudents = descriptionForStudents;
	}
	/**
	 * Returns the question associated with the exam.
	 * 
	 * @return The exam question.
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * Sets the question associated with the exam.
	 * 
	 * @param question The exam question to set.
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * Returns the total point value for the exam.
	 * 
	 * @return The exam total point value.
	 */
	public String getTotalPoint() {
		return TotalPoint;
	}
	/**
	 * Sets the total point value for the exam.
	 * 
	 * @param totalPoint The exam total point value to set.
	 */
	public void setTotalPoint(String totalPoint) {
		TotalPoint = totalPoint;
	}
	/**
	 * Returns the point value per question in the exam.
	 * 
	 * @return The exam point value per question.
	 */
	public String getPointPerQuestion() {
		return PointPerQuestion;
	}
	/**
	 * Sets the point value per question in the exam.
	 * 
	 * @param pointPerQuestion The exam point value per question to set.
	 */
	public void setPointPerQuestion(String pointPerQuestion) {
		PointPerQuestion = pointPerQuestion;
	}
	/**
	 * Converts a row from a database result set into an `Exam` object.
	 * The row should contain the necessary fields for creating an `Exam` object.
	 * 
	 * @param row The row from the database result set.
	 * @return The created `Exam` object.
	 */
	public static Exam convertToExam(Map<String, Object> row) {
        String info = (String) row.get("info");
        String subject = (String) row.get("Subject");
        String course = (String) row.get("Course");
        String examNumber = (String) row.get("ExamNumber");
        int duration = (int) row.get("Duration");
        String descriptionForLecturers = (String) row.get("DescriptionForLecturers");
        String descriptionForStudents = (String) row.get("DescriptionForStudents");
        String question = (String) row.get("question");
        String TotalPoint = (String) row.get("TotalPoint");
        String PointPerQuestion = (String) row.get("PointPerQuestion");

        return new Exam(info, subject, course, examNumber, duration, descriptionForLecturers,
                descriptionForStudents, question,TotalPoint,PointPerQuestion);
    }
}

