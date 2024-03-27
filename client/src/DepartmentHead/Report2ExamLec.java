package DepartmentHead;

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
import logic.ExamStatistics;
import logic.Lecturer;
import logic.sqlmessage;

import static client.ChatClient.resultList;
import static client.ClientUI.chat;
import static gui.LogIn.DepartmentHeadinfo;

import java.io.IOException;
import java.util.Map;
/**
 * Controller class for the Report2ExamLec.fxml file.
 * Handles the logic and event handling for generating reports based on selected exams and lecturers.
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class Report2ExamLec {
	/**
	 * JavaFX controller class for the graphical user interface.
	 * Handles the logic and event handling for generating reports based on selected exams and lecturers.
	 */
	@FXML
	private AnchorPane anchorPane;

	/**
	 * Text element displaying the average value for the first exam.
	 */
	@FXML
	private Text average1;

	/**
	 * Text element displaying the median value for the first exam.
	 */
	@FXML
	private Text medin1;

	/**
	 * Text element displaying the average value for the second exam.
	 */
	@FXML
	private Text average2;

	/**
	 * Text element displaying the median value for the second exam.
	 */
	@FXML
	private Text medin2;

	/**
	 * ComboBox for selecting the lecturer ID.
	 */
	@FXML
	private ComboBox<String> lecId;

	/**
	 * ComboBox for selecting the first exam ID.
	 */
	@FXML
	private ComboBox<String> exam_id1;

	/**
	 * ComboBox for selecting the second exam ID.
	 */
	@FXML
	private ComboBox<String> exam_id2;

	/**
	 * BarChart representing the graph for the first exam.
	 */
	@FXML
	private BarChart<String, Number> graph1;

	/**
	 * CategoryAxis representing the X-axis of the graph for the first exam.
	 */
	@FXML
	private CategoryAxis xAxis1;

	/**
	 * NumberAxis representing the Y-axis of the graph for the first exam.
	 */
	@FXML
	private NumberAxis yAxis1;

	/**
	 * BarChart representing the graph for the second exam.
	 */
	@FXML
	private BarChart<String, Number> graph2;

	/**
	 * CategoryAxis representing the X-axis of the graph for the second exam.
	 */
	@FXML
	private CategoryAxis xAxis2;

	/**
	 * NumberAxis representing the Y-axis of the graph for the second exam.
	 */
	@FXML
	private NumberAxis yAxis2;


    /**
     * Initializes the controller.
     * Sets up the event handlers and populates the lecturer ComboBox.
     */
    public void initialize() {
        String query = "SELECT * FROM lecturer WHERE Department = ? ";
        Object[] pram = { DepartmentHeadinfo.get(0).getDepartment() };
        sqlmessage message = new sqlmessage("get", query, pram);
        chat.accept(message);
        for (Map<String, Object> row : resultList) {
            Lecturer lec = Lecturer.convertToLecturer(row);
            lecId.getItems().add(lec.getID());
        }

        // Add event handler for lecId ComboBox
        lecId.setOnAction(event -> {
            String selectedLecId = lecId.getValue();
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
     * Populates the exam ComboBoxes based on the selected lecturer ID.
     *
     * @param selectedLecId The selected lecturer ID.
     */
    private void populateExamComboBoxes(String selectedLecId) {
    

        // Retrieve the exams for the selected lecturer ID
        String query = "SELECT * FROM examstatistic WHERE LecturerID = ?";
        Object[] pram = { selectedLecId };
        sqlmessage message = new sqlmessage("get", query, pram);
        chat.accept(message);

        ObservableList<ExamStatistics> exams = FXCollections.observableArrayList();
        for (Map<String, Object> row : resultList) {
        	ExamStatistics exam = ExamStatistics.convertToExamStatistics(row);
            exams.add(exam);
        }

        // Populate the exam_id1 and exam_id2 ComboBoxes with exam IDs
        for (ExamStatistics exam : exams) {
            exam_id1.getItems().add(exam.getExamId());
            exam_id2.getItems().add(exam.getExamId());
        }
    }
    
    /**
     * Updates the graph based on the selected exam.
     *
     * @param selectedExamId The selected exam ID.
     * @param graph          The BarChart for the graph.
     * @param av             The Text element to display the average.
     * @param me             The Text element to display the median.
     */
    private void updateGraph(String selectedExamId, BarChart<String, Number> graph,Text av,Text me) {
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
     * Handles the back button click event.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException if an I/O error occurs.
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
