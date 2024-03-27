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
 * Represents a question.
 */
public class Question implements Serializable {
	  /**
     * The instance of the Question class.
     */
    private static Question instance;
    /**
     * The information about the question.
     */
    private String Info;

    /**
     * The question number.
     */
    private String QuestionNum;

    /**
     * The subject of the question.
     */
    private String Subject;

    /**
     * The course name of the question.
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
     * Constructs a new Question object with the provided parameters.
     *
     * @param info          the information about the question
     * @param questionNum   the question number
     * @param subject       the subject of the question
     * @param courseName    the course name of the question
     * @param questionText  the text of the question
     * @param lecturerID    the ID of the lecturer who created the question
     * @param answer1       the first answer option
     * @param answer2       the second answer option
     * @param answer3       the third answer option
     * @param answerCorrect the correct answer option
     */
    private Question(String info, String questionNum, String subject, String courseName, String questionText,
            String lecturerID, String answer1, String answer2, String answer3, String answerCorrect) {
        this.Info = info;
        this.QuestionNum = questionNum;
        this.Subject = subject;
        this.CourseName = courseName;
        this.QuestionText = questionText;
        this.LecturerID = lecturerID;
        this.Answer1 = answer1;
        this.Answer2 = answer2;
        this.Answer3 = answer3;
        this.AnswerCorrect = answerCorrect;
    }
    /**
     * Returns an instance of the Question class with the provided parameters. If an instance already exists, it returns the existing instance.
     *
     * @param info          the information about the question
     * @param questionNum   the question number
     * @param subject       the subject of the question
     * @param courseName    the course name of the question
     * @param questionText  the text of the question
     * @param lecturerID    the ID of the lecturer who created the question
     * @param answer1       the first answer option
     * @param answer2       the second answer option
     * @param answer3       the third answer option
     * @param answerCorrect the correct answer option
     * @return the Question instance
     */
    public static synchronized Question getInstance(String info, String questionNum, String subject, String courseName,
            String questionText, String lecturerID, String answer1, String answer2, String answer3,
            String answerCorrect) {
        instance = new Question(info, questionNum, subject, courseName, questionText, lecturerID, answer1, answer2,
                    answer3, answerCorrect);
        return instance;
    }
    /**
     * Converts a row of data (Map) into a Question object.
     *
     * @param row the row of data containing question details
     * @return the converted Question object
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
        String answerCorrect = (String) row.get("AnswerCorrect");
        return getInstance(info, questionNum, subject, courseName, questionText, lecturerID, answer1, answer2, answer3,
                answerCorrect);
    }
	
    /**
     * Retrieves the information about the question.
     *
     * @return the information about the question
     */
	public String getInfo() {
		return Info;
	}

	  /**
     * Sets the information about the question.
     *
     * @param info the information about the question
     */
	public void setInfo(String info) {
		Info = info;
	}

    /**
     * Retrieves the question number.
     *
     * @return the question number
     */
	public String getQuestionNum() {
		return QuestionNum;
	}

	   /**
     * Sets the question number.
     *
     * @param questionNum the question number
     */
	public void setQuestionNum(String questionNum) {
		QuestionNum = questionNum;
	}

    /**
     * Retrieves the subject of the question.
     *
     * @return the subject of the question
     */
	public String getSubject() {
		return Subject;
	}

    /**
     * Sets the subject of the question.
     *
     * @param subject the subject of the question
     */
	public void setSubject(String subject) {
		Subject = subject;
	}

    /**
     * Retrieves the course name of the question.
     *
     * @return the course name of the question
     */
	public String getCourseName() {
		return CourseName;
	}

    /**
     * Sets the course name of the question.
     *
     * @param courseName the course name of the question
     */
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

    /**
     * Retrieves the text of the question.
     *
     * @return the text of the question
     */
	public String getQuestionText() {
		return QuestionText;
	}

    /**
     * Sets the text of the question.
     *
     * @param questionText the text of the question
     */
	public void setQuestionText(String questionText) {
		QuestionText = questionText;
	}

    /**
     * Retrieves the ID of the lecturer who created the question.
     *
     * @return the ID of the lecturer who created the question
     */
	public String getLecturerID() {
		return LecturerID;
	}

    /**
     * Sets the ID of the lecturer who created the question.
     *
     * @param lecturerID the ID of the lecturer who created the question
     */
	public void setLecturerID(String lecturerID) {
		LecturerID = lecturerID;
	}

    /**
     * Retrieves the first answer option.
     *
     * @return the first answer option
     */
	public String getAnswer1() {
		return Answer1;
	}

    /**
     * Sets the first answer option.
     *
     * @param answer1 the first answer option
     */
	public void setAnswer1(String answer1) {
		Answer1 = answer1;
	}

    /**
     * Retrieves the second answer option.
     *
     * @return the second answer option
     */
	public String getAnswer2() {
		return Answer2;
	}

    /**
     * Sets the second answer option.
     *
     * @param answer2 the second answer option
     */
	public void setAnswer2(String answer2) {
		Answer2 = answer2;
	}

    /**
     * Retrieves the third answer option.
     *
     * @return the third answer option
     */
	public String getAnswer3() {
		return Answer3;
	}

    /**
     * Sets the third answer option.
     *
     * @param answer3 the third answer option
     */
	public void setAnswer3(String answer3) {
		Answer3 = answer3;
	}

    /**
     * Retrieves the correct answer option.
     *
     * @return the correct answer option
     */
	public String getAnswerCorrect() {
		return AnswerCorrect;
	}

    /**
     * Sets the correct answer option.
     *
     * @param answerCorrect the correct answer option
     */
	public void setAnswerCorrect(String answerCorrect) {
		AnswerCorrect = answerCorrect;
	}


	

    /**
     * Returns a string representation of the Question object.
     *
     * @return a string representation of the Question object
     */
	@Override
	public String toString() {
		return "Question [Info=" + Info + ", QuestionNum=" + QuestionNum + ", Subject=" + Subject + ", CourseName="
				+ CourseName + ", QuestionText=" + QuestionText + ", LecturerID=" + LecturerID + ", Answer1=" + Answer1
				+ ", Answer2=" + Answer2 + ", Answer3=" + Answer3 + ", AnswerCorrect=" + AnswerCorrect + "]";
	}

}
