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
 * The `ExamStatistics` class represents the statistics of an exam.
 * It contains information such as exam ID, lecturer ID, average grade,
 * median grade, lowest grade, highest grade, and counts for each grade range.
 * Use the `getInstance` method to create an instance of the `ExamStatistics` class.
 * The class also provides a method to convert a row of data from a map into an `ExamStatistics` object.
 */
public class ExamStatistics {
	   /**
     * The singleton instance of the `ExamStatistics` class.
     */
	private static ExamStatistics instance;
	/**
	 * The ID of the exam.
	 */
	private String examId;

	/**
	 * The ID of the lecturer.
	 */
	private String lecturerID;

	/**
	 * The average grade.
	 */
	private double average;

	/**
	 * The median grade.
	 */
	private double median;

	/**
	 * The lowest grade.
	 */
	private int lowestGrade;

	/**
	 * The highest grade.
	 */
	private int highestGrade;

	/**
	 * The count of grades in range 1.
	 */
	private int range1Count;

	/**
	 * The count of grades in range 2.
	 */
	private int range2Count;

	/**
	 * The count of grades in range 3.
	 */
	private int range3Count;

	/**
	 * The count of grades in range 4.
	 */
	private int range4Count;

	/**
	 * The count of grades in range 5.
	 */
	private int range5Count;

	/**
	 * The count of grades in range 6.
	 */
	private int range6Count;

	/**
	 * The count of grades in range 7.
	 */
	private int range7Count;

	/**
	 * The count of grades in range 8.
	 */
	private int range8Count;

	/**
	 * The count of grades in range 9.
	 */
	private int range9Count;

	/**
	 * The count of grades in range 10.
	 */
	private int range10Count;

 
    /**
     * Constructs a new `ExamStatistics` instance with the specified parameters.
     *
     * @param examId       The exam ID.
     * @param lecturerID   The lecturer ID.
     * @param average      The average grade.
     * @param median       The median grade.
     * @param lowestGrade  The lowest grade.
     * @param highestGrade The highest grade.
     * @param range1Count  The count for range 0-9.
     * @param range2Count  The count for range 10-19.
     * @param range3Count  The count for range 20-29.
     * @param range4Count  The count for range 30-39.
     * @param range5Count  The count for range 40-49.
     * @param range6Count  The count for range 50-59.
     * @param range7Count  The count for range 60-69.
     * @param range8Count  The count for range 70-79.
     * @param range9Count  The count for range 80-89.
     * @param range10Count The count for range 90-100.
     */
    private ExamStatistics(String examId, String lecturerID, double average, double median, int lowestGrade, int highestGrade,
                           int range1Count, int range2Count, int range3Count, int range4Count, int range5Count,
                           int range6Count, int range7Count, int range8Count, int range9Count, int range10Count) {
        this.examId = examId;
        this.lecturerID = lecturerID;
        this.average = average;
        this.median = median;
        this.lowestGrade = lowestGrade;
        this.highestGrade = highestGrade;
        this.range1Count = range1Count;
        this.range2Count = range2Count;
        this.range3Count = range3Count;
        this.range4Count = range4Count;
        this.range5Count = range5Count;
        this.range6Count = range6Count;
        this.range7Count = range7Count;
        this.range8Count = range8Count;
        this.range9Count = range9Count;
        this.range10Count = range10Count;
    }

  
    /**
     * Returns the singleton instance of the `ExamStatistics` class.
     *
     * @param examId       The exam ID.
     * @param lecturerID   The lecturer ID.
     * @param average      The average grade.
     * @param median       The median grade.
     * @param lowestGrade  The lowest grade.
     * @param highestGrade The highest grade.
     * @param range1Count  The count for range 0-9.
     * @param range2Count  The count for range 10-19.
     * @param range3Count  The count for range 20-29.
     * @param range4Count  The count for range 30-39.
     * @param range5Count  The count for range 40-49.
     * @param range6Count  The count for range 50-59.
     * @param range7Count  The count for range 60-69.
     * @param range8Count  The count for range 70-79.
     * @param range9Count  The count for range 80-89.
     * @param range10Count The count for range 90-100.
     * @return The singleton instance of the `ExamStatistics` class.
     */
    public static ExamStatistics getInstance(String examId, String lecturerID, double average, double median, int lowestGrade,
                                            int highestGrade, int range1Count, int range2Count, int range3Count,
                                            int range4Count, int range5Count, int range6Count, int range7Count,
                                            int range8Count, int range9Count, int range10Count) {
        instance = new ExamStatistics(examId, lecturerID, average, median, lowestGrade, highestGrade, range1Count,
                range2Count, range3Count, range4Count, range5Count, range6Count, range7Count, range8Count,
                range9Count, range10Count);
        return instance;
    }
    /**
     * Converts a row of data from a map into an `ExamStatistics` object.
     *
     * @param row The row of data as a map.
     * @return The `ExamStatistics` object converted from the row of data.
     */
    public static ExamStatistics convertToExamStatistics(Map<String, Object> row) {
        String examId = (String) row.get("ExamID");
        String lecturerID = (String) row.get("LecturerID");
        double average = (row.get("Average") != null) ? (Double) row.get("Average") : 0.0;
        double median = (row.get("Median") != null) ? (Double) row.get("Median") : 0.0;
        int lowestGrade = (row.get("Lowest Grade") != null) ? (Integer) row.get("Lowest Grade") : 0;
        int highestGrade = (row.get("Highest Grade") != null) ? (Integer) row.get("Highest Grade") : 0;
        int range1Count = (row.get("0-9") != null) ? (int) row.get("0-9") : 0;
        int range2Count = (row.get("10-19") != null) ? (int) row.get("10-19") : 0;
        int range3Count = (row.get("20-29") != null) ? (int) row.get("20-29") : 0;
        int range4Count = (row.get("30-39") != null) ? (int) row.get("30-39") : 0;
        int range5Count = (row.get("40-49") != null) ? (int) row.get("40-49") : 0;
        int range6Count = (row.get("50-59") != null) ? (int) row.get("50-59") : 0;
        int range7Count = (row.get("60-69") != null) ? (int) row.get("60-69") : 0;
        int range8Count = (row.get("70-79") != null) ? (int) row.get("70-79") : 0;
        int range9Count = (row.get("80-89") != null) ? (int) row.get("80-89") : 0;
        int range10Count = (row.get("90-100") != null) ? (int) row.get("90-100") : 0;

        return new ExamStatistics(examId, lecturerID, average, median, lowestGrade, highestGrade, range1Count,
                range2Count, range3Count, range4Count, range5Count, range6Count, range7Count, range8Count,
                range9Count, range10Count);
    }

    /**
     * Returns the exam ID.
     *
     * @return The exam ID.
     */
    public String getExamId() {
        return examId;
    }
    /**
     * Sets the exam ID.
     *
     * @param examId The exam ID to set.
     */
    public void setExamId(String examId) {
        this.examId = examId;
    }
    /**
     * Returns the lecturer ID.
     *
     * @return The lecturer ID.
     */
    public String getLecturerID() {
        return lecturerID;
    }
    /**
     * Sets the lecturer ID.
     *
     * @param lecturerID The lecturer ID to set.
     */
    public void setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
    }
    /**
     * Returns the average grade.
     *
     * @return The average grade.
     */
    public double getAverage() {
        return average;
    }
    /**
     * Sets the average grade.
     *
     * @param average The average grade to set.
     */
    public void setAverage(double average) {
        this.average = average;
    }
    /**
     * Returns the median grade.
     *
     * @return The median grade.
     */
    public double getMedian() {
        return median;
    }
    /**
     * Sets the median grade.
     *
     * @param median The median grade to set.
     */
    public void setMedian(double median) {
        this.median = median;
    }
    /**
     * Returns the lowest grade.
     *
     * @return The lowest grade.
     */
    public int getLowestGrade() {
        return lowestGrade;
    }
    /**
     * Sets the lowest grade.
     *
     * @param lowestGrade The lowest grade to set.
     */
    public void setLowestGrade(int lowestGrade) {
        this.lowestGrade = lowestGrade;
    }
    /**
     * Returns the highest grade.
     *
     * @return The highest grade.
     */
    public int getHighestGrade() {
        return highestGrade;
    }
    /**
     * Sets the highest grade.
     *
     * @param highestGrade The highest grade to set.
     */
    public void setHighestGrade(int highestGrade) {
        this.highestGrade = highestGrade;
    }
    /**
     * Returns the count for range 0-9.
     *
     * @return The count for range 0-9.
     */
    public int getRange1Count() {
        return range1Count;
    }
    /**
     * Sets the count of grades in range 1.
     * 
     * @param range1Count The count of grades in range 1.
     */
    public void setRange1Count(int range1Count) {
        this.range1Count = range1Count;
    }
    /**
     * Returns the count of grades in range 2.
     * 
     * @return The count of grades in range 2.
     */
    public int getRange2Count() {
        return range2Count;
    }
    /**
     * Sets the count of grades in range 2.
     * 
     * @param range2Count The count of grades in range 2.
     */
    public void setRange2Count(int range2Count) {
        this.range2Count = range2Count;
    }
    /**
     * Returns the count of grades in range 3.
     * 
     * @return The count of grades in range 3.
     */
    public int getRange3Count() {
        return range3Count;
    }
    /**
     * Sets the count of grades in range 3.
     * 
     * @param range3Count The count of grades in range 3.
     */
    public void setRange3Count(int range3Count) {
        this.range3Count = range3Count;
    }
    /**
     * Returns the count of grades in range 4.
     * 
     * @return The count of grades in range 4.
     */
    public int getRange4Count() {
        return range4Count;
    }
    /**
     * Sets the count of grades in range 4.
     * 
     * @param range4Count The count of grades in range 4.
     */
    public void setRange4Count(int range4Count) {
        this.range4Count = range4Count;
    }
    /**
     * Returns the count of grades in range 5.
     * 
     * @return The count of grades in range 5.
     */
    public int getRange5Count() {
        return range5Count;
    }
    /**
     * Sets the count of grades in range 5.
     * 
     * @param range5Count The count of grades in range 5.
     */
    public void setRange5Count(int range5Count) {
        this.range5Count = range5Count;
    }
    /**
     * Returns the count of grades in range 6.
     * 
     * @return The count of grades in range 6.
     */
    public int getRange6Count() {
        return range6Count;
    }
    /**
     * Sets the count of grades in range 6.
     * 
     * @param range6Count The count of grades in range 6.
     */
    public void setRange6Count(int range6Count) {
        this.range6Count = range6Count;
    }
    /**
     * Returns the count of grades in range 7.
     * 
     * @return The count of grades in range 7.
     */
    public int getRange7Count() {
        return range7Count;
    }
    /**
     * Sets the count of grades in range 7.
     * 
     * @param range7Count The count of grades in range 7.
     */
    public void setRange7Count(int range7Count) {
        this.range7Count = range7Count;
    }
    /**
     * Returns the count of grades in range 8.
     * 
     * @return The count of grades in range 8.
     */
    public int getRange8Count() {
        return range8Count;
    }
    /**
     * Sets the count of grades in range 8.
     * 
     * @param range8Count The count of grades in range 8.
     */
    public void setRange8Count(int range8Count) {
        this.range8Count = range8Count;
    }
    /**
     * Returns the count of grades in range 9.
     * 
     * @return The count of grades in range 9.
     */
    public int getRange9Count() {
        return range9Count;
    }
    /**
     * Sets the count of grades in range 9.
     * 
     * @param range9Count The count of grades in range 9.
     */
    public void setRange9Count(int range9Count) {
        this.range9Count = range9Count;
    }
    /**
     * Returns the count of grades in range 10.
     * 
     * @return The count of grades in range 10.
     */
    public int getRange10Count() {
        return range10Count;
    }
    /**
     * Sets the count of grades in range 10.
     * 
     * @param range10Count The count of grades in range 10.
     */
    public void setRange10Count(int range10Count) {
        this.range10Count = range10Count;
    }
}
