package lecturer;

import static client.ChatClient.resultList;
import static client.ClientUI.chat;
import static lecturer.StatisticReportController.exam_ID;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Exam;
import logic.ExamStatistics;
import logic.sqlmessage;
/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * This class is responsible for controlling the exam statistics view.
 * It displays various statistics about an exam, including the average,
 * median, highest grade, lowest grade, and grade distribution in a bar chart.
 */
public class ExamStatisticsController2 {
		/**
	 	* Text field displaying the course name.
	 	*/
		@FXML
	    private Text course_text;
		/**
		 * Text field displaying the median grade.
		 */

	    @FXML
	    private Text median_test;
	    /**
	     * Text field displaying the average grade.
	     */
	    @FXML
	    private Text average_text;
	    /**
	     * Text field displaying the highest grade.
	     */
	    @FXML
	    private Text heghest_grade;
	    /**
	     * Text field displaying the lowest grade.
	     */
	    @FXML
	    private Text Lowest_Grade_test;
	    /**
	     * Text field displaying the failing percentage.
	     */
	    @FXML
	    private Text failing_percentage_text;
	    /**
	     * Text field displaying the exam ID.
	     */
	    @FXML
	    private Text exam_id_text;
	    /**
	     * Bar chart displaying the grade distribution.
	     */
	    @FXML
	    private BarChart<String, Number> graph;
	    /**
	     * X-axis of the grade distribution bar chart.
	     */

	    @FXML
	    private CategoryAxis xAxis;
	    /**
	     * Y-axis of the grade distribution bar chart.
	     */
	    @FXML
	    private NumberAxis yAxis;
	    /**
	     * Initializes the exam statistics view.
	     * Fetches the exam statistics and exam details from the database and sets the text and chart data accordingly.
	     */
	    public void initialize() {
	    	String query="SELECT * FROM examstatistic WHERE ExamID = ?";
	        Object[] params = {exam_ID};
	        sqlmessage message = new sqlmessage("get", query, params);
	        chat.accept(message);
	        Map<String, Object> row = resultList.get(0);
	        ExamStatistics exam_statistic= ExamStatistics.convertToExamStatistics(row);
	        String query2 = "SELECT * FROM exam WHERE ID = ?";
	        Object[] params2 = {exam_ID};
	        sqlmessage message2 = new sqlmessage("get", query2, params2);
	        chat.accept(message2);
	        Map<String, Object> row2 = resultList.get(0);
	        Exam exam=Exam.convertToExam(row2);
	        setText(exam_statistic,exam);

	        // Prepare the data
	        Map<String, Integer> gradeData = getGradeData(exam_statistic); // Replace with your own data structure

	        // Create a series and add data points
	        XYChart.Series<String, Number> series = new XYChart.Series<>();
	        for (String range : gradeData.keySet()) {
	            int value = gradeData.get(range);
	            series.getData().add(new XYChart.Data<>(range, value));
	        }

	        // Add the series to the chart
	        graph.getData().add(series);

	    }
	    /**
	     * Retrieves the grade data from the exam statistics.
	     *
	     * @param e_s The exam statistics object.
	     * @return A map containing the grade range as the key and the count as the value.
	     */
	    private Map<String, Integer> getGradeData(ExamStatistics e_s) {
	        Map<String, Integer> gradeData = new LinkedHashMap<>();
	        gradeData.put("0-9", e_s.getRange1Count() );
	        gradeData.put("10-19", e_s.getRange2Count() );
	        gradeData.put("20-29", e_s.getRange3Count() );
	        gradeData.put("30-39", e_s.getRange4Count() );
	        gradeData.put("40-49", e_s.getRange5Count() );
	        gradeData.put("50-59", e_s.getRange6Count() );
	        gradeData.put("60-69", e_s.getRange7Count() );
	        gradeData.put("70-79", e_s.getRange8Count() );
	        gradeData.put("80-89", e_s.getRange9Count() );
	        gradeData.put("90-100", e_s.getRange10Count() );


	        return gradeData;
	    }
	    /**
	     * Sets the text fields with the exam statistics and exam details.
	     *
	     * @param exam_statistic The exam statistics object.
	     * @param exam           The exam object.
	     */
	    private void setText(ExamStatistics exam_statistic,Exam exam) {
	        int student_num=exam_statistic.getRange1Count()+exam_statistic.getRange2Count()+exam_statistic.getRange3Count()+exam_statistic.getRange4Count()+exam_statistic.getRange5Count()+exam_statistic.getRange6Count();
	    	exam_id_text.setText(exam_ID);
	    	course_text.setText(exam.getCourse());
	    	median_test.setText(String.format("%.2f", exam_statistic.getMedian()));
	    	average_text.setText(String.format("%.2f", exam_statistic.getAverage()));
	    	Lowest_Grade_test.setText( String.valueOf( exam_statistic.getLowestGrade()));
	    	heghest_grade.setText(String.valueOf( exam_statistic.getHighestGrade()));
	        if(student_num>0) {
	        	 double range1Percentage = (double) exam_statistic.getRange1Count() / student_num * 100;
	 	        failing_percentage_text.setText(String.format("%.2f",range1Percentage));
	        }
	    }
	    /**
	     * Handles the back button action.
	     *
	     * @param event The action event.
	     * @throws IOException If an I/O error occurs.
	     */
	    @FXML
	    public void back(ActionEvent event) throws IOException {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/Statistic Report.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
	    }
	    /**
	     * Handles the main menu button action.
	     *
	     * @param event The mouse event.
	     * @throws IOException If an I/O error occurs.
	     */
	    @FXML
	    public void move_to_main(MouseEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/LecturerMain.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
	    }
	    

}
