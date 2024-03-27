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
 * Represents a manual exam.
 */
public class manual_exam {
	  /**
     * The instance of the manual_exam class.
     */
    private static manual_exam instance;

    /**
     * The ID of the exam.
     */
    private String exam_id;

    /**
     * The subject of the exam.
     */
    private String sbuject;

    /**
     * Constructs a new instance of the manual_exam class.
     *
     * @param exam_id  The ID of the exam.
     * @param sbuject  The subject of the exam.
     */
    private manual_exam(String exam_id, String sbuject) {
        this.exam_id = exam_id;
        this.sbuject = sbuject;
    }
    /**
     * Returns the instance of the manual_exam class.
     *
     * @param exam_id  The ID of the exam.
     * @param sbuject  The subject of the exam.
     * @return The instance of the manual_exam class.
     */
    public static synchronized manual_exam getInstance(String exam_id, String sbuject) {
        instance = new manual_exam(exam_id, sbuject);
        return instance;
    }
    /**
     * Converts a row from a map into a manual_exam object.
     *
     * @param row  The map representing a row of data.
     * @return The manual_exam object.
     */
    public static manual_exam convertToManualExam(Map<String, Object> row) {
        String exam_id = (String) row.get("exam_id");
        String sbuject = (String) row.get("subject");
        return getInstance(exam_id, sbuject);
    }
    /**
     * Returns the ID of the exam.
     *
     * @return The ID of the exam.
     */
	public String getExam_id() {
		return exam_id;
	}

    /**
     * Sets the ID of the exam.
     *
     * @param exam_id  The ID of the exam to set.
     */
	public void setExam_id(String exam_id) {
		this.exam_id = exam_id;
	}
    /**
     * Returns the subject of the exam.
     *
     * @return The subject of the exam.
     */
	public String getSbuject() {
		return sbuject;
	}
    /**
     * Sets the subject of the exam.
     *
     * @param sbuject  The subject of the exam to set.
     */
	public void setSbuject(String sbuject) {
		this.sbuject = sbuject;
	}

}
