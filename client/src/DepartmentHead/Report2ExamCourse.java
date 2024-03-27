package DepartmentHead;

import static client.ChatClient.resultList;
import static client.ClientUI.chat;
import static gui.LogIn.DepartmentHeadinfo;

import java.io.IOException;
import java.util.Map;
import static client.ChatClient.isexsit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Course;
import logic.Exam;
import logic.ExamStatistics;
import logic.sqlmessage;
/**
 * Controller class for the Report2ExamCourse.fxml file.
 * Handles the logic and event handling for generating reports based on selected exams and courses.
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class Report2ExamCourse {
	/**
     * The main anchor pane of the UI.
     */
    @FXML
    private AnchorPane anchorPane;

    /**
     * Text element to display average 1.
     */
    @FXML
    private Text average1;

    /**
     * Text element to display average 2.
     */
    @FXML
    private Text average2;

    /**
     * Text element to display median 1.
     */
    @FXML
    private Text medin1;

    /**
     * Text element to display median 2.
     */
    @FXML
    private Text medin2;

    /**
     * ComboBox to select a course.
     */
    @FXML
    private ComboBox<String> course;

    /**
     * ComboBox to select exam ID 1.
     */
    @FXML
    private ComboBox<String> exam_id1;

    /**
     * ComboBox to select exam ID 2.
     */
    @FXML
    private ComboBox<String> exam_id2;

    /**
     * Bar chart for graph 1.
     */
    @FXML
    private BarChart<String, Number> graph1;

    /**
     * X-axis for graph 1.
     */
    @FXML
    private CategoryAxis graph1XAxis;

    /**
     * Y-axis for graph 1.
     */
    @FXML
    private NumberAxis graph1YAxis;

    /**
     * Bar chart for graph 2.
     */
    @FXML
    private BarChart<String, Number> graph2;

    /**
     * X-axis for graph 2.
     */
    @FXML
    private CategoryAxis graph2XAxis;

    /**
     * Y-axis for graph 2.
     */
    @FXML
    private NumberAxis graph2YAxis;
    /**
     * Initializes the controller.
     * Sets up the event handlers and populates the course and exam ComboBoxes.
     */
    public void initialize() {
    	  String query = "SELECT * FROM course WHERE Department = ? ";
          Object[] pram = { DepartmentHeadinfo.get(0).getDepartment() };
          sqlmessage message = new sqlmessage("get", query, pram);
          chat.accept(message);
          for (Map<String, Object> row : resultList) {
              Course co = Course.convertCourse(row);
              course.getItems().add(co.getCourseName());
          }
          
       // Add event handler for lecId ComboBox
          course.setOnAction(event -> {
              String selectedLecId = course.getValue();
              if (selectedLecId != null) {
                  populateExamComboBoxes(selectedLecId);
              }
          });
          
          exam_id1.setOnAction(event -> {
          	   graph1.getData().clear();
         	   average1.setText("");
         	   medin1.setText("");
              String selectedExamId1 = exam_id1.getValue();
              if (selectedExamId1 != null) {
                  updateGraph(selectedExamId1, graph1 ,average1,medin1);
              }
          });

          exam_id2.setOnAction(event -> {
          	 graph2.getData().clear();
          	 average2.setText("");
          	 medin2.setText("");
              String selectedExamId2 = exam_id2.getValue();
              if (selectedExamId2 != null) {
                  updateGraph(selectedExamId2, graph2,average2,medin2);
              }
          });
    }
    
    /**
     * Populates the exam ComboBoxes based on the selected course.
     * @param selectedLecId The ID of the selected lecturer.
     *
     * 
     */
    private void populateExamComboBoxes(String selectedLecId) {
    	
        exam_id1.getItems().clear();
        exam_id2.getItems().clear();

        // Retrieve the exams for the selected lecturer ID
        String query = "SELECT * FROM exam WHERE Course = ?";
        Object[] pram = { selectedLecId };
        sqlmessage message = new sqlmessage("get", query, pram);
        chat.accept(message);

        ObservableList<Exam> exams = FXCollections.observableArrayList();
        for (Map<String, Object> row : resultList) {
        	 Exam exam = Exam.convertToExam(row);
        	 String query2 = "SELECT * FROM examstatistic WHERE ExamID = ?";
             Object[] pram2 = { exam.getID() };
             sqlmessage message2 = new sqlmessage("check", query2, pram2);
             chat.accept(message2);
             if (isexsit) {
            	 exams.add(exam);
             }
            
        }
        // Populate the exam_id1 and exam_id2 ComboBoxes with exam IDs
        for (Exam exam : exams) {
            exam_id1.getItems().add(exam.getID());
            exam_id2.getItems().add(exam.getID());
        }
        }
    /**
     * Updates the graph based on the selected exam.
     *
     * @param selectedExamId The selected exam ID.
     * @param graph          The BarChart to update.
     * @param av    The Text component to display the average.
     * @param me     The Text component to display the median.
     */
        private void updateGraph(String selectedExamId, BarChart<String, Number> graph,Text av,Text me) {
            // Clear previous data in the graph
            graph.getData().clear();
            av.setText("");
            me.setText("");
            String query = "SELECT * FROM examstatistic WHERE ExamID = ?";
            Object[] pram = { selectedExamId };
            sqlmessage message = new sqlmessage("get", query, pram);
            chat.accept(message);
            ExamStatistics examStats = ExamStatistics.convertToExamStatistics(resultList.get(0));
            av.setText(String.valueOf(examStats.getAverage()));
            me.setText(String.valueOf(examStats.getMedian()));
            ObservableList<XYChart.Series<String, Number>> seriesList = graph.getData();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Exam Statistics"); // Set a name for the series

            // Add data points for the different grade ranges
            series.getData().add(new XYChart.Data<>("0-9", examStats.getRange1Count()));
            series.getData().add(new XYChart.Data<>("10-19", examStats.getRange2Count()));
            series.getData().add(new XYChart.Data<>("20-29", examStats.getRange3Count()));
            series.getData().add(new XYChart.Data<>("30-39", examStats.getRange4Count()));
            series.getData().add(new XYChart.Data<>("40-49", examStats.getRange5Count()));
            series.getData().add(new XYChart.Data<>("50-59", examStats.getRange6Count()));
            series.getData().add(new XYChart.Data<>("60-69", examStats.getRange7Count()));
            series.getData().add(new XYChart.Data<>("70-79", examStats.getRange8Count()));
            series.getData().add(new XYChart.Data<>("80-89", examStats.getRange9Count()));
            series.getData().add(new XYChart.Data<>("90-100", examStats.getRange10Count()));

            // Add the series to the graph
            seriesList.add(series);
        }

        /**
         * Handles the action event for the back button.
         *
         * @param event The action event triggered by the button.
         * @throws IOException if an I/O error occurs during the transition to the previous scene.
         */
   	 public void back(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/static report for 2 exam.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
	 }
   

}
