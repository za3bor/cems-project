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
 * Represents a selected question for a current exam from the perspective of a student.
 */
public class CurrentExamSelectedQuestionStudent {
    /**
     * The ID of the question.
     */
    private String QuestionID;
    
    /**
     * The text of the question.
     */
    private String QuestionText;
    
    /**
     * The student's answer to the question.
     */
    private String StudentAnswer;
    
    /**
     * The correct answer to the question.
     */
    private String rightAnswer;
    
    /**
     * The point value of the question.
     */
    private String Point;
    /**
     * Constructs a new `CurrentExamSelectedQuestionStudent` object with the specified question ID, question text,
     * student's answer, right answer, and point.
     *
     * @param questionID    The ID of the question.
     * @param questionText  The text of the question.
     * @param studentAnswer The student's answer to the question.
     * @param rightAnswer   The correct answer to the question.
     * @param point         The point value of the question.
     */
	public CurrentExamSelectedQuestionStudent(String questionID, String questionText, String studentAnswer,
			String rightAnswer, String point) {
		super();
		QuestionID = questionID;
		QuestionText = questionText;
		StudentAnswer = studentAnswer;
		this.rightAnswer = rightAnswer;
		Point = point;
	}
    /**
     * Returns the ID of the question.
     *
     * @return The question ID.
     */
	public String getQuestionID() {
		return QuestionID;
	}
    /**
     * Sets the ID of the question.
     *
     * @param questionID The question ID to set.
     */
	public void setQuestionID(String questionID) {
		QuestionID = questionID;
	}
    /**
     * Returns the text of the question.
     *
     * @return The question text.
     */
	public String getQuestionText() {
		return QuestionText;
	}
    /**
     * Sets the text of the question.
     *
     * @param questionText The question text to set.
     */
	public void setQuestionText(String questionText) {
		QuestionText = questionText;
	}
    /**
     * Returns the student's answer to the question.
     *
     * @return The student's answer.
     */
	public String getStudentAnswer() {
		return StudentAnswer;
	}
    /**
     * Sets the student's answer to the question.
     *
     * @param studentAnswer The student's answer to set.
     */
	public void setStudentAnswer(String studentAnswer) {
		StudentAnswer = studentAnswer;
	}
    /**
     * Returns the correct answer to the question.
     *
     * @return The correct answer.
     */
	public String getRightAnswer() {
		return rightAnswer;
	}

    /**
     * Sets the correct answer to the question.
     *
     * @param rightAnswer The correct answer to set.
     */
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
    /**
     * Returns the point value of the question.
     *
     * @return The point value.
     */
	public String getPoint() {
		return Point;
	}
    /**
     * Sets the point value of the question.
     *
     * @param point The point value to set.
     */
	public void setPoint(String point) {
		Point = point;
	}
    /**
     * Returns a string representation of the `CurrentExamSelectedQuestionStudent` object.
     *
     * @return A string representation of the object.
     */
	@Override
	public String toString() {
		return "CurrentExamSelectedQuestionStudent [QuestionID=" + QuestionID + ", QuestionText=" + QuestionText
				+ ", StudentAnswer=" + StudentAnswer + ", rightAnswer=" + rightAnswer + ", Point=" + Point + "]";
	}
}
