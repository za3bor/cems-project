package logic;

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
 * Represents the current exam.
 * 
 * @author fadis
 */
public class CurrentExam {
    /**
     * The singleton instance of the CurrentExam class.
     */
    private static CurrentExam instance;
    /**
     * The ID of the current exam.
     */
    private String examid;
    /**
     * The count of the current exam.
     */
    private int count;
    /**
     * Constructs a new CurrentExam object with the specified exam ID and count.
     *
     * @param examid The ID of the current exam.
     * @param count  The count of the current exam.
     */
    private CurrentExam(String examid, int count) {
        this.examid = examid;
        this.count = count;
    }
    /**
     * Returns the singleton instance of the CurrentExam class.
     *
     * @param examid The ID of the current exam.
     * @param count  The count of the current exam.
     * @return The singleton instance of the CurrentExam class.
     */
    public static CurrentExam getInstance(String examid, int count) {
        instance = new CurrentExam(examid, count);
        return instance;
    }
    /**
     * Returns the ID of the current exam.
     *
     * @return The ID of the current exam.
     */
    public String getExamid() {
        return examid;
    }
    /**
     * Sets the ID of the current exam.
     *
     * @param examid The ID of the current exam.
     */
    public void setExamid(String examid) {
        this.examid = examid;
    }
    /**
     * Returns the count of the current exam.
     *
     * @return The count of the current exam.
     */
    public int getCount() {
        return count;
    }
    /**
     * Sets the count of the current exam.
     *
     * @param count The count of the current exam.
     */
    public void setCount(int count) {
        this.count = count;
    }
    /**
     * Converts a row from a data source into a CurrentExam instance.
     *
     * @param row The row containing the data for CurrentExam.
     * @return The CurrentExam instance.
     */
    public static CurrentExam convertToCurrentExam(Map<String, Object> row) {
        String examid = (String) row.get("examid");
        int count = row.get("count") != null ? (int) row.get("count") : 0;
        return getInstance(examid, count);
    }
}
