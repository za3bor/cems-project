package logic;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

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
 * Represents an Exam Point.
 * 
 */
public class ExamPoint {
    /**
     * The question number.
     */
    private String questionNum;
    /**
     * The point value.
     */
    private Integer point;
    /**
     * Constructs a new ExamPoint object with the specified question number and point.
     *
     * @param questionNum the question number
     * @param point the point value
     */
    public ExamPoint(String questionNum, Integer point) {
        this.questionNum = questionNum;
        this.point = point;
    }
    /**
     * Returns the question number.
     *
     * @return the question number
     */
    public String getQuestionNum() {
        return questionNum;
    }
    /**
     * Sets the question number.
     *
     * @param questionNum the question number to set
     */
    public void setQuestionNum(String questionNum) {
        this.questionNum = questionNum;
    }
    /**
     * Returns the point value.
     *
     * @return the point value
     */
    public Integer getPoint() {
        return point;
    }
    /**
     * Sets the point value.
     *
     * @param point the point value to set
     */
    public void setPoint(Integer point) {
        this.point = point;
    }

    /**
     * Returns an observable value representing the question number.
     *
     * @return an observable value of the question number
     */    public ObservableValue<String> questionNumProperty() {
        return new SimpleStringProperty(questionNum);
    }
     /**
      * Returns an observable value representing the point value.
      *
      * @return an observable value of the point value
      */
    public ObservableValue<Integer> pointProperty() {
        return new SimpleIntegerProperty(point).asObject();
    }
}
