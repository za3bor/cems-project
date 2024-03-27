package logic;
/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * Represents a question in an exam.
 */
public class ExamQuestion {
	   /**
     * The singleton instance of the `ExamQuestion` class.
     */
	private static ExamQuestion instance;
	 /**
     * The question object.
     */
	private Question question;
    /**
     * The point value for the question.
     */
	private String point;
    /**
     * Constructs a new `ExamQuestion` instance.
     *
     * @param question The question object.
     * @param point    The point value for the question.
     */
    private ExamQuestion(Question question, String point) {
        this.question = question;
        this.point = point;
    }
    /**
     * Gets the singleton instance of `ExamQuestion`.
     *
     * @param question The question object.
     * @param point    The point value for the question.
     * @return The singleton instance of `ExamQuestion`.
     */
    public static ExamQuestion getInstance(Question question, String point) {
        instance = new ExamQuestion(question, point);
        return instance;
    }
    /**
     * Gets the question object.
     *
     * @return The question object.
     */
    public Question getQuestion() {
        return question;
    }
    /**
     * Sets the question object.
     *
     * @param question The question object to set.
     */
    public void setQuestion(Question question) {
        this.question = question;
    }
    /**
     * Gets the point value for the question.
     *
     * @return The point value for the question.
     */
    public String getPoint() {
        return point;
    }
    /**
     * Sets the point value for the question.
     *
     * @param point The point value for the question to set.
     */
    public void setPoint(String point) {
        this.point = point;
    }
    /**
     * Gets the question number.
     *
     * @return The question number.
     */
    public String getQuestionNum() {
        return question.getQuestionNum();
    }
    /**
     * Gets the question information.
     *
     * @return The question information.
     */
    public String getInfo() {
        return question.getInfo();
    }
    /**
     * Gets the subject of the question.
     *
     * @return The subject of the question.
     */
    public String getSubject() {
        return question.getSubject();
    }
    /**
     * Gets the course name associated with the question.
     *
     * @return The course name associated with the question.
     */
    public String getCourseName() {
        return question.getCourseName();
    }
    /**
     * Gets the text of the question.
     *
     * @return The text of the question.
     */
    public String getQuestionText() {
        return question.getQuestionText();
    }
    /**
     * Gets the ID of the lecturer who created the question.
     *
     * @return The ID of the lecturer who created the question.
     */
    public String getLecturerID() {
        return question.getLecturerID();
    }
    /**
     * Gets the first answer option of the question.
     *
     * @return The first answer option of the question.
     */
    public String getAnswer1() {
        return question.getAnswer1();
    }
    /**
     * Gets the second answer option of the question.
     *
     * @return The second answer option of the question.
     */
    public String getAnswer2() {
        return question.getAnswer2();
    }
    /**
     * Gets the third answer option of the question.
     *
     * @return The third answer option of the question.
     */
    public String getAnswer3() {
        return question.getAnswer3();
    }
    /**
     * Gets the correct answer option of the question.
     *
     * @return The correct answer option of the question.
     */
    public String getAnswerCorrect() {
        return question.getAnswerCorrect();
    }
}
