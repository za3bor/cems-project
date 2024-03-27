package logic;

import java.util.Date;
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
 * The `LecDurationRequest` class represents a lecture duration request.
 * It contains information such as the request number, exam code, date of the request,
 * additional duration requested, request made by the lecturer, and the status of the request.
 * This class follows the Singleton design pattern to ensure only one instance is created.
 * Use the `getInstance` method to retrieve the singleton instance.
 * The class also provides a method to convert a row of data from a map into a `LecDurationRequest` object.
 */
public class LecDurationRequest {
    /**
     * The request number.
     */
    private int requestNum;

    /**
     * The exam code.
     */
    private String examCode;

    /**
     * The date of the request.
     */
    private Date date;

    /**
     * The additional duration requested.
     */
    private int additionalDuration;

    /**
     * The request made by the lecturer.
     */
    private String lecDurationRequest;

    /**
     * The status of the request.
     */
    private int status;

    /**
     * The singleton instance of the LecDurationRequest class.
     */
    private static LecDurationRequest instance;
    /**
     * Returns the singleton instance of the LecDurationRequest class.
     * If the instance doesn't exist, it creates a new one.
     *
     * @param requestNum           The request number.
     * @param examCode             The exam code.
     * @param date                 The date of the request.
     * @param additionalDuration   The additional duration requested.
     * @param lecDurationRequestCol   The request made by the lecturer.
     * @param status               The status of the request.
     * @return The singleton instance of the LecDurationRequest class.
     */
    public static synchronized LecDurationRequest getInstance(int requestNum, String examCode, Date date, int additionalDuration, String lecDurationRequestCol, int status) {
        instance = new LecDurationRequest(requestNum, examCode, date, additionalDuration, lecDurationRequestCol, status);
        return instance;
    }
    /**
     * Constructs a new LecDurationRequest object.
     *
     * @param requestNum           The request number.
     * @param examCode             The exam code.
     * @param date                 The date of the request.
     * @param additionalDuration   The additional duration requested.
     * @param lecDurationRequest   The request made by the lecturer.
     * @param status               The status of the request.
     */
    private LecDurationRequest(int requestNum, String examCode, Date date, int additionalDuration,
                               String lecDurationRequest, int status) {
        this.requestNum = requestNum;
        this.examCode = examCode;
        this.date = date;
        this.additionalDuration = additionalDuration;
        this.lecDurationRequest = lecDurationRequest;
        this.status = status;
    }
    /**
     * Converts a row of data from a map into a LecDurationRequest object.
     *
     * @param row  A map containing the data for a LecDurationRequest.
     * @return A LecDurationRequest object converted from the row data.
     */
    public static LecDurationRequest convertToLecDurationRequest(Map<String, Object> row) {
        int requestNum = (int) row.get("requestNum");
        String examCode = (String) row.get("examCode");
        Date date = (Date) row.get("date");
        int additionalDuration = (int) row.get("additionalDuration");
        String lecDurationRequest = (String) row.get("lecDurationRequestCol");
        int status = (int) row.get("status");

        instance.setRequestNum(requestNum);
        instance.setExamCode(examCode);
        instance.setDate(date);
        instance.setAdditionalDuration(additionalDuration);
        instance.setLecDurationRequest(lecDurationRequest);
        instance.setStatus(status);

        return instance;
    }

    /**
     * Returns the request number.
     *
     * @return The request number.
     */
    public int getRequestNum() {
        return requestNum;
    }
    /**
     * Sets the request number.
     *
     * @param requestNum The request number to set.
     */
    public void setRequestNum(int requestNum) {
        this.requestNum = requestNum;
    }
    /**
     * Returns the exam code.
     *
     * @return The exam code.
     */
    public String getExamCode() {
        return examCode;
    }
    /**
     * Sets the exam code.
     *
     * @param examCode The exam code to set.
     */
    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }
    /**
     * Returns the date of the request.
     *
     * @return The date of the request.
     */
    public Date getDate() {
        return date;
    }
    /**
     * Sets the date of the request.
     *
     * @param date The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * Returns the additional duration requested.
     *
     * @return The additional duration requested.
     */
    public int getAdditionalDuration() {
        return additionalDuration;
    }
    /**
     * Sets the additional duration requested.
     *
     * @param additionalDuration The additional duration to set.
     */
    public void setAdditionalDuration(int additionalDuration) {
        this.additionalDuration = additionalDuration;
    }
    /**
     * Returns the request made by the lecturer.
     *
     * @return The request made by the lecturer.
     */
    public String getLecDurationRequest() {
        return lecDurationRequest;
    }
    /**
     * Sets the request made by the lecturer.
     *
     * @param lecDurationRequest The request made by the lecturer to set.
     */
    public void setLecDurationRequest(String lecDurationRequest) {
        this.lecDurationRequest = lecDurationRequest;
    }
    /**
     * Returns the status of the request.
     *
     * @return The status of the request.
     */
    public int getStatus() {
        return status;
    }
    /**
     * Sets the status of the request.
     *
     * @param status The status to set.
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
    /**
     * Returns a string representation of the LecDurationRequest object.
     *
     * @return A string representation of the LecDurationRequest object.
     */
    @Override
    public String toString() {
        return "LecDurationRequest [requestNum=" + requestNum + ", examCode=" + examCode + ", date=" + date
                + ", additionalDuration=" + additionalDuration + ", lecDurationRequest=" + lecDurationRequest
                + ", status=" + status + "]";
    }
}
