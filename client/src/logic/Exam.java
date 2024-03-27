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
 * The `Exam` class represents an exam.
 * It contains information such as exam details, duration, descriptions,
 * questions, points, and the lecturer responsible for the exam.
 * This class implements the Serializable interface to support serialization.
 * Use the `getInstance` method to create an instance of the `Exam` class.
 * The class also provides a method to convert a row of data from a map into an `Exam` object.
 */
public class Exam implements Serializable {
    /**
     * The singleton instance of the `Exam` class.
     */
    private static Exam instance;

    /**
     * The exam information.
     */
    private String info;

    /**
     * The exam ID.
     */
    private String ID;

    /**
     * The subject of the exam.
     */
    private String subject;

    /**
     * The course of the exam.
     */
    private String course;

    /**
     * The duration of the exam.
     */
    private int duration;

    /**
     * The description for lecturers.
     */
    private String descriptionForLecturers;

    /**
     * The description for students.
     */
    private String descriptionForStudents;

    /**
     * The exam question.
     */
    private String question;

    /**
     * The total point of the exam.
     */
    private String totalPoint;

    /**
     * The point per question in the exam.
     */
    private String pointPerQuestion;

    /**
     * The lecturer responsible for the exam.
     */
    private String lecturer;
    /**
     * Constructs a new `Exam` instance with the specified parameters.
     *
     * @param info                     The exam information.
     * @param ID                       The exam ID.
     * @param subject                  The subject of the exam.
     * @param course                   The course of the exam.
     * @param duration                 The duration of the exam.
     * @param descriptionForLecturers  The description for lecturers.
     * @param descriptionForStudents   The description for students.
     * @param question                 The exam question.
     * @param totalPoint               The total point of the exam.
     * @param pointPerQuestion         The point per question in the exam.
     * @param lecturer                 The lecturer responsible for the exam.
     */
    private Exam(String info, String ID, String subject, String course, int duration,
                 String descriptionForLecturers, String descriptionForStudents, String question,
                 String totalPoint, String pointPerQuestion, String lecturer) {
        this.info = info;
        this.ID = ID;
        this.subject = subject;
        this.course = course;
        this.duration = duration;
        this.descriptionForLecturers = descriptionForLecturers;
        this.descriptionForStudents = descriptionForStudents;
        this.question = question;
        this.totalPoint = totalPoint;
        this.pointPerQuestion = pointPerQuestion;
        this.lecturer = lecturer;
    }
    /**
     * Returns the singleton instance of the `Exam` class.
     *
     * @param info                     The exam information.
     * @param ID                       The exam ID.
     * @param subject                  The subject of the exam.
     * @param course                   The course of the exam.
     * @param duration                 The duration of the exam.
     * @param descriptionForLecturers  The description for lecturers.
     * @param descriptionForStudents   The description for students.
     * @param question                 The exam question.
     * @param totalPoint               The total point of the exam.
     * @param pointPerQuestion         The point per question in the exam.
     * @param lecturer                 The lecturer responsible for the exam.
     * @return The singleton instance of the `Exam` class.
     */
    public static Exam getInstance(String info, String ID, String subject, String course, int duration,
                                   String descriptionForLecturers, String descriptionForStudents, String question,
                                   String totalPoint, String pointPerQuestion, String lecturer) {
            instance = new Exam(info, ID, subject, course, duration,
                                descriptionForLecturers, descriptionForStudents, question,
                                totalPoint, pointPerQuestion, lecturer);
        return instance;
    }
    /**
     * Gets the exam information.
     *
     * @return The exam information.
     */
    public String getInfo() {
        return info;
    }
    /**
     * Sets the exam information.
     *
     * @param info The exam information to set.
     */
    public void setInfo(String info) {
        this.info = info;
    }
    /**
     * Gets the exam ID.
     *
     * @return The exam ID.
     */
    public String getID() {
        return ID;
    }
    /**
     * Sets the exam ID.
     *
     * @param ID The exam ID to set.
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    /**
     * Gets the subject of the exam.
     *
     * @return The subject of the exam.
     */
    public String getSubject() {
        return subject;
    }
    /**
     * Sets the subject of the exam.
     *
     * @param subject The subject of the exam to set.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**
     * Gets the course of the exam.
     *
     * @return The course of the exam.
     */
    public String getCourse() {
        return course;
    }
    /**
     * Sets the course of the exam.
     *
     * @param course The course of the exam to set.
     */
    public void setCourse(String course) {
        this.course = course;
    }
    /**
     * Gets the duration of the exam.
     *
     * @return The duration of the exam.
     */
    public int getDuration() {
        return duration;
    }
    /**
     * Sets the duration of the exam.
     *
     * @param duration The duration of the exam to set.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
    /**
     * Gets the description for lecturers.
     *
     * @return The description for lecturers.
     */
    public String getDescriptionForLecturers() {
        return descriptionForLecturers;
    }
    /**
     * Sets the description for lecturers.
     *
     * @param descriptionForLecturers The description for lecturers to set.
     */
    public void setDescriptionForLecturers(String descriptionForLecturers) {
        this.descriptionForLecturers = descriptionForLecturers;
    }
    /**
     * Gets the description for students.
     *
     * @return The description for students.
     */
    public String getDescriptionForStudents() {
        return descriptionForStudents;
    }
    /**
     * Sets the description for students.
     *
     * @param descriptionForStudents The description for students to set.
     */
    public void setDescriptionForStudents(String descriptionForStudents) {
        this.descriptionForStudents = descriptionForStudents;
    }
    /**
     * Gets the exam question.
     *
     * @return The exam question.
     */
    public String getQuestion() {
        return question;
    }
    /**
     * Sets the exam question.
     *
     * @param question The exam question to set.
     */
    public void setQuestion(String question) {
        this.question = question;
    }
    /**
     * Gets the total point of the exam.
     *
     * @return The total point of the exam.
     */
    public String getTotalPoint() {
        return totalPoint;
    }
    /**
     * Sets the total point of the exam.
     *
     * @param totalPoint The total point of the exam to set.
     */
    public void setTotalPoint(String totalPoint) {
        this.totalPoint = totalPoint;
    }
    /**
     * Gets the point per question in the exam.
     *
     * @return The point per question in the exam.
     */
    public String getPointPerQuestion() {
        return pointPerQuestion;
    }
    /**
     * Sets the point per question in the exam.
     *
     * @param pointPerQuestion The point per question in the exam to set.
     */
    public void setPointPerQuestion(String pointPerQuestion) {
        this.pointPerQuestion = pointPerQuestion;
    }
    /**
     * Gets the lecturer responsible for the exam.
     *
     * @return The lecturer responsible for the exam.
     */
    public String getLecturer() {
        return lecturer;
    }
    /**
     * Sets the lecturer responsible for the exam.
     *
     * @param lecturer The lecturer responsible for the exam to set.
     */
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }
    /**
     * Converts a row of data from a map into an `Exam` object.
     *
     * @param row The row of data represented as a map.
     * @return The converted `Exam` object.
     */
    public static Exam convertToExam(Map<String, Object> row) {
        String info = (String) row.get("info");
        String ID = (String) row.get("ID");
        String subject = (String) row.get("Subject");
        String course = (String) row.get("Course");
        int duration = row.get("Duration") != null ? ((Integer) row.get("Duration")).intValue() : 0;
        String descriptionForLecturers = (String) row.get("DescriptionForLecturers");
        String descriptionForStudents = (String) row.get("DescriptionForStudents");
        String question = (String) row.get("Question");
        String totalPoint = (String) row.get("TotalPoint");
        String pointPerQuestion = (String) row.get("PointPerQuestion");
        String lecturer = (String) row.get("Lecturer");

        return getInstance(info, ID, subject, course, duration,
                           descriptionForLecturers, descriptionForStudents, question,
                           totalPoint, pointPerQuestion, lecturer);
    }

    @Override
    public String toString() {
        return "Exam [info=" + info + ", ID=" + ID + ", Subject=" + subject + ", Course=" + course + ", Duration="
                + duration + ", DescriptionForLecturers=" + descriptionForLecturers + ", DescriptionForStudents="
                + descriptionForStudents + ", Question=" + question + ", TotalPoint=" + totalPoint
                + ", PointPerQuestion=" + pointPerQuestion + "]";
    }
}
