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
 * Represents an SMS message.
 */
public class sms {
    /**
     * The singleton instance of the sms class.
     */
    private static sms instance;
    /**
     * The department associated with the SMS message.
     */
    private String department;
    /**
     * The lecturer associated with the SMS message.
     */
    private String lecturer;
    /**
     * The ID of the exam associated with the SMS message.
     */
    private String examId;
    
    /**
     * The new duration of the exam associated with the SMS message.
     */
    private int newDuration;
    /**
     * The reason provided in the SMS message.
     */
    private String reason;
    /**
     * The response received for the SMS message.
     */
    private String response;
    /**
     * Constructs a new sms object with the specified parameters.
     *
     * @param department    The department associated with the SMS message.
     * @param lecturer      The lecturer associated with the SMS message.
     * @param examId        The ID of the exam associated with the SMS message.
     * @param newDuration   The new duration of the exam associated with the SMS message.
     * @param reason        The reason provided in the SMS message.
     * @param response      The response received for the SMS message.
     */
    private sms(String department, String lecturer, String examId, int newDuration, String reason, String response) {
        this.department = department;
        this.lecturer = lecturer;
        this.examId = examId;
        this.newDuration = newDuration;
        this.reason = reason;
        this.response = response;
    }
    /**
     * Retrieves the singleton instance of the sms class.
     *
     * @param department    The department associated with the SMS message.
     * @param lecturer      The lecturer associated with the SMS message.
     * @param examId        The ID of the exam associated with the SMS message.
     * @param newDuration   The new duration of the exam associated with the SMS message.
     * @param reason        The reason provided in the SMS message.
     * @param response      The response received for the SMS message.
     * @return The singleton instance of the sms class.
     */
    public static synchronized sms getInstance(String department, String lecturer, String examId, int newDuration,
            String reason, String response) {
        instance = new sms(department, lecturer, examId, newDuration, reason, response);
        return instance;
    }
    /**
     * Retrieves the department associated with the SMS message.
     *
     * @return The department associated with the SMS message.
     */
    public String getDepartment() {
        return department;
    }
    /**
     * Retrieves the lecturer associated with the SMS message.
     *
     * @return The lecturer associated with the SMS message.
     */
    public String getLecturer() {
        return lecturer;
    }
    /**
     * Retrieves the ID of the exam associated with the SMS message.
     *
     * @return The ID of the exam associated with the SMS message.
     */
    public String getExamId() {
        return examId;
    }
    /**
     * Retrieves the new duration of the exam associated with the SMS message.
     *
     * @return The new duration of the exam associated with the SMS message.
     */
    public int getNewDuration() {
        return newDuration;
    }
    /**
     * Retrieves the reason provided in the SMS message.
     *
     * @return The reason provided in the SMS message.
     */
    public String getReason() {
        return reason;
    }
    /**
     * Retrieves the response received for the SMS message.
     *
     * @return The response received for the SMS message.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Sets the response received for the SMS message.
     *
     * @param response The response received for the SMS message.
     */
    public void setResponse(String response) {
        this.response = response;
    }
    /**
     * Converts a row from a database result set into an sms object.
     *
     * @param row The row from the database result set.
     * @return The converted sms object.
     */
    public static sms convertToSMS(Map<String, Object> row) {
        String department = (String) row.get("Department");
        String lecturer = (String) row.get("lecturer");
        String examId = (String) row.get("exam_id");
        Integer newDurationValue = (Integer) row.get("new duration");
        int newDuration = newDurationValue != null ? newDurationValue.intValue() : 0;
        String reason = (String) row.get("reason");
        String response = (String) row.get("response");
        return getInstance(department, lecturer, examId, newDuration, reason, response);
    }
}
