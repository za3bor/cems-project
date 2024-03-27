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
 * Represents a question entity.
 *
 * This class stores information about a question, including the question details, options, and correct answer.
 *
 * The class provides methods to get and set the properties.
 *
 * Note: This class assumes a single correct answer. If you have multiple correct answers or more complex answer options,
 * you might need to modify the class structure accordingly.
 *
 * Note: It's important to validate and sanitize the input when constructing a `Question` object to prevent security vulnerabilities
 * such as cross-site scripting (XSS) attacks.
 *
 * Note: The `convertToQuestion` method assumes a specific mapping of column names to corresponding object properties.
 * Modify the method if your database column names or object properties differ.
 *
 * @author fadis
 *
 */
public class Question implements Serializable {
    /**
     * Additional information about the question.
     */
    private String Info;

    /**
     * The question number or identifier.
     */
    private String QuestionNum;

    /**
     * The subject or topic of the question.
     */
    private String Subject;

    /**
     * The name of the course related to the question.
     */
    private String CourseName;

    /**
     * The text of the question.
     */
    private String QuestionText;

    /**
     * The ID of the lecturer who created the question.
     */
    private String LecturerID;

    /**
     * The first answer option.
     */
    private String Answer1;

    /**
     * The second answer option.
     */
    private String Answer2;

    /**
     * The third answer option.
     */
    private String Answer3;

    /**
     * The correct answer option.
     */
    private String AnswerCorrect;

    /**
     * Constructs a `Question` object with the specified parameters.
     *
     * @param info The additional information about the question.
     * @param questionNum The question number or identifier.
     * @param subject The subject or topic of the question.
     * @param courseName The name of the course related to the question.
     * @param questionText The text of the question.
     * @param lecturerID The ID of the lecturer who created the question.
     * @param answer1 The first answer option.
     * @param answer2 The second answer option.
     * @param answer3 The third answer option.
     * @param answerCorrect The correct answer option.
     */
	public Question(String info, String questionNum, String subject, String courseName, String questionText,
			String lecturerID, String answer1, String answer2, String answer3, String answerCorrect) {
		super();
		Info = info;
		QuestionNum = questionNum;
		Subject = subject;
		CourseName = courseName;
		QuestionText = questionText;
		LecturerID = lecturerID;
		Answer1 = answer1;
		Answer2 = answer2;
		Answer3 = answer3;
		AnswerCorrect = answerCorrect;
	}
	
	   /**
     * Returns the additional information about the question.
     *
     * @return The additional information about the question.
     */
	public String getInfo() {
		return Info;
	}

	   /**
     * Sets the additional information about the question.
     *
     * @param info The additional information about the question.
     */
	public void setInfo(String info) {
		Info = info;
	}

    /**
     * Returns the question number or identifier.
     *
     * @return The question number or identifier.
     */
	public String getQuestionNum() {
		return QuestionNum;
	}

    /**
     * Sets the question number or identifier.
     *
     * @param questionNum The question number or identifier.
     */
	public void setQuestionNum(String questionNum) {
		QuestionNum = questionNum;
	}

	   /**
     * Returns the subject or topic of the question.
     *
     * @return The subject or topic of the question.
     */
	public String getSubject() {
		return Subject;
	}

    /**
     * Sets the subject or topic of the question.
     *
     * @param subject The subject or topic of the question.
     */
	public void setSubject(String subject) {
		Subject = subject;
	}

    /**
     * Returns the name of the course related to the question.
     *
     * @return The name of the course related to the question.
     */
	public String getCourseName() {
		return CourseName;
	}

    /**
     * Sets the name of the course related to the question.
     *
     * @param courseName The name of the course related to the question.
     */
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

    /**
     * Returns the text of the question.
     *
     * @return The text of the question.
     */

	public String getQuestionText() {
		return QuestionText;
	}

    /**
     * Sets the text of the question.
     *
     * @param questionText The text of the question.
     */
	public void setQuestionText(String questionText) {
		QuestionText = questionText;
	}

	 /**
     * Returns the ID of the lecturer who created the question.
     *
     * @return The ID of the lecturer who created the question.
     */
	public String getLecturerID() {
		return LecturerID;
	}

    /**
     * Sets the ID of the lecturer who created the question.
     *
     * @param lecturerID The ID of the lecturer who created the question.
     */
	public void setLecturerID(String lecturerID) {
		LecturerID = lecturerID;
	}

    /**
     * Returns the first answer option.
     *
     * @return The first answer option.
     */
	public String getAnswer1() {
		return Answer1;
	}

    /**
     * Sets the first answer option.
     *
     * @param answer1 The first answer option.
     */
	public void setAnswer1(String answer1) {
		Answer1 = answer1;
	}

    /**
     * Returns the second answer option.
     *
     * @return The second answer option.
     */
	public String getAnswer2() {
		return Answer2;
	}

    /**
     * Sets the second answer option.
     *
     * @param answer2 The second answer option.
     */
	public void setAnswer2(String answer2) {
		Answer2 = answer2;
	}

    /**
     * Returns the third answer option.
     *
     * @return The third answer option.
     */
	public String getAnswer3() {
		return Answer3;
	}

    /**
     * Sets the third answer option.
     *
     * @param answer3 The third answer option.
     */
	public void setAnswer3(String answer3) {
		Answer3 = answer3;
	}

    /**
     * Returns the correct answer option.
     *
     * @return The correct answer option.
     */
	public String getAnswerCorrect() {
		return AnswerCorrect;
	}

    /**
     * Sets the correct answer option.
     *
     * @param answerCorrect The correct answer option.
     */
	public void setAnswerCorrect(String answerCorrect) {
		AnswerCorrect = answerCorrect;
	}

    /**
     * Converts a database row represented as a map into a `Question` object.
     *
     * @param row The map representing a database row.
     * @return The `Question` object converted from the database row.
     */
	public static Question convertToQuestion(Map<String, Object> row) {
        String info = (String) row.get("Info");
        String questionNum = (String) row.get("QuestionNumber");
        String subject = (String) row.get("Subject");
        String courseName = (String) row.get("CourseName");
        String questionText = (String) row.get("QuestionText");
        String lecturerID = (String) row.get("LecturerID");
        String answer1 = (String) row.get("Answer1");
        String answer2 = (String) row.get("Answer2");
        String answer3 = (String) row.get("Answer3");
        String answercorrect = (String) row.get("AnswerCorrect");
        return new Question(info, questionNum, subject, courseName, questionText, lecturerID, answer1,answer2,answer3,answercorrect);
	}
		
}
