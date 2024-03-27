package DepartmentHead;

import static client.ChatClient.resultList;
import static client.ClientUI.chat;
import static gui.LogIn.DepartmentHeadinfo;
import static gui.LogIn.Username;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.DepartmentHead;
import logic.Exam;
import logic.ExamStatistics;
import logic.examresult;
import logic.Course;
import logic.sqlmessage;
/**
 * Controller class for the Show Report 2 page.
 * Handles the logic and actions related to displaying reports for a specific course.
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class ShowReport2Controller {
   /**
    * The ComboBox for selecting a course.
    */
   @FXML
   private ComboBox<String> choose_cor_combo;

   /**
    * The Button for initiating the report display.
    */
   @FXML
   private Button show_button;

   /**
    * The Button for navigating back to the previous page.
    */
   @FXML
   private Button back_button;

   /**
    * The Text field for displaying the status or error messages.
    */
   @FXML
   private Text status;

   /**
    * The Text field for displaying the average value.
    */
   @FXML
   private Text AVG_text;

   /**
    * The Text field for displaying the median value.
    */
   @FXML
   private Text Median_text;

   /**
    * The BarChart for displaying the graph.
    */
   @FXML
   private BarChart<String, Number> graph;

   /**
    * The CategoryAxis for the x-axis of the graph.
    */
   @FXML
   private CategoryAxis xAxis;

   /**
    * The NumberAxis for the y-axis of the graph.
    */
   @FXML
   private NumberAxis yAxis;

   /**
    * The list of Course objects.
    */
   private List<Course> course_List = new ArrayList<>();

   /**
    * The list of Exam objects.
    */
   private List<Exam> exam_List = new ArrayList<>();

   /**
    * The list of ExamStatistics objects.
    */
   private List<ExamStatistics> examStatistics_List = new ArrayList<>();

   /**
    * The list of exam IDs.
    */
   private List<String> examID_List = new ArrayList<>();

   /**
    * The list of examresult objects.
    */
   private List<examresult> exresult_List = new ArrayList<>();

   /**
    * The temporary list of strings.
    */
   private List<String> temp = new ArrayList<>();

   /**
    * Initializes the controller.
    * Retrieves the department and populates the ComboBox with course IDs.
    */
    public void initialize() {
    	String department = null;
    	for (DepartmentHead departmentHead : DepartmentHeadinfo) {
    	    if (departmentHead.getUsername().equals(Username)) {
    	        department = departmentHead.getDepartment();
    	        break; // Exit the loop once a match is found
    	    }
    	}

		String query = "SELECT * FROM course WHERE Department= ?";
		Object[] params = { department };
		sqlmessage message = new sqlmessage("get", query, params);
		chat.accept(message);

		for (Map<String, Object> row : resultList) {
			Course course = Course.convertCourse(row);
			course_List.add(course);
		}
		for (Course course : course_List) {
			String id = course.getID();
			choose_cor_combo.getItems().addAll(id);
		}
    	
    }
    /**
     * Displays the report based on the selected course.
     *
     * @param event The ActionEvent triggered by the show_button.
     * @throws IOException If an error occurs during the navigation to the next page.
     */
	 public void show(ActionEvent event) throws IOException {
		 	course_List.clear();
		 	exam_List.clear();
		 	examStatistics_List.clear();
		 	examID_List.clear();
		    graph.getData().clear(); // Clear previous data from the BarChart
		 	double sum=0;
		 	int counter=0;
	        String selectedRole = choose_cor_combo.getValue();
	        if (selectedRole == null) {
	            status.setText("Please select a role.");
	            return;
	        }
	        else {
	            List<XYChart.Series<String, Number>> seriesList = new ArrayList<>();
	            String[] grades = {"0-9", "10-19", "20-29", "30-39", "40-49", "50-59", "60-69", "70-79", "80-89", "90-100"};
	            for (int i = 0; i < 10; i++) {
	                XYChart.Series<String, Number> series = new XYChart.Series<>();
	                series.setName(grades[i]); // Custom column names as fruits
	                seriesList.add(series);
	            }
	            
	    		String query = "SELECT * FROM course WHERE ID= ?";
	    		Object[] params = { selectedRole };
	    		sqlmessage message = new sqlmessage("get", query, params);
	    		chat.accept(message);
	    		
	    		for (Map<String, Object> row : resultList) {
	    			Course course = Course.convertCourse(row);
	    			course_List.add(course);
	    		}
	    		
	    		String query1 = "SELECT * FROM exam";
	    		Object[] params1 = {};
	    		sqlmessage message1 = new sqlmessage("get", query1, params1);
	    		chat.accept(message1);
	    		
	    		for (Map<String, Object> row : resultList) {
	    			Exam exam = Exam.convertToExam(row);
	    			exam_List.add(exam);
	    		}
	    		
	    		for(Course course : course_List) {
	    			for(Exam exam : exam_List) {
	    				if(exam.getCourse().equals(course.getCourseName())) {
	    					examID_List.add(exam.getID());
	    				}
	    			}
	    		}
	    		String query2 = "SELECT * FROM examstatistic";
	    		Object[] params2 = {};
	    		sqlmessage message2 = new sqlmessage("get", query2, params2);
	    		chat.accept(message2);
	    		
	    		for (Map<String, Object> row : resultList) {
	    			ExamStatistics ex = ExamStatistics.convertToExamStatistics(row);
	    			examStatistics_List.add(ex);
	    		}
	            int columns1=0,columns2=0,columns3=0,columns4=0,columns5=0,columns6=0,columns7=0,
	            columns8=0,columns9=0,columns10=0;
	    		for(ExamStatistics ex : examStatistics_List) {
	    			for(String s : examID_List) {
	    				if(ex.getExamId().equals(s)) {
	    					columns1 += ex.getRange1Count();
	    					columns2 += ex.getRange2Count();
	    					columns3 += ex.getRange3Count();
	    					columns4 += ex.getRange4Count();
	    					columns5 += ex.getRange5Count();
	    					columns6 += ex.getRange6Count();
	    					columns7 += ex.getRange7Count();
	    					columns8 += ex.getRange8Count();
	    					columns9 += ex.getRange9Count();
	    					columns10 += ex.getRange10Count();
	    					sum+=ex.getAverage();
	    					counter++;
	    				}
	    			}
	    		}
	    		
	    	      // Add data to the series
	               seriesList.get(0).getData().add(new XYChart.Data<>("", columns1));
	               seriesList.get(1).getData().add(new XYChart.Data<>("", columns2));
	               seriesList.get(2).getData().add(new XYChart.Data<>("", columns3));
	               seriesList.get(3).getData().add(new XYChart.Data<>("", columns4));
	               seriesList.get(4).getData().add(new XYChart.Data<>("", columns5));
	               seriesList.get(5).getData().add(new XYChart.Data<>("", columns6));
	               seriesList.get(6).getData().add(new XYChart.Data<>("", columns7));
	               seriesList.get(7).getData().add(new XYChart.Data<>("", columns8));
	               seriesList.get(8).getData().add(new XYChart.Data<>("", columns9));
	               seriesList.get(9).getData().add(new XYChart.Data<>("", columns10));
	            graph.getData().addAll(seriesList);

	    		if(counter==0) {
		    		AVG_text.setText("There is no exams for this course");
	    		}
	    		else {
			    	sum=sum/counter;
			    	AVG_text.setText(String.format("%.2f", sum));
			    	Median_text.setText(String.format("%.2f", cal_med()));
	    		}
	        }

	 }

	    /**
	     * Calculates the median of the exam results for the selected course.
	     *
	     * @return The median of the exam results.
	     */
	 private double cal_med() {
		 temp.clear();
		 exresult_List.clear();
		 String query = "SELECT * FROM examresult";
		 Object[] params = {};
	     sqlmessage message = new sqlmessage("get", query, params);
		 chat.accept(message);
		 
	 	for (Map<String, Object> row : resultList) {
	 		examresult ex = examresult.convertToExamResult(row);
	 		exresult_List.add(ex);
		}
	 	
	 	for(Course course : course_List) {
	 		for(examresult ex : exresult_List) {
	 			if(ex.getCourse().equals(course.getCourseName())) {
	 	 			temp.add(ex.getExamResult());
	 			}
	 		}
	 	}
		String[] scores=new String[temp.size()];
 		scores = temp.toArray(scores);
        int[] intArray = new int[scores.length];
        for (int i = 0; i < scores.length; i++) {
            intArray[i] = Integer.parseInt(scores[i]);
        }
        Arrays.sort(intArray);
        double median;
        int n = scores.length;
        if (n % 2 == 0) {
            int middleIndex1 = n / 2 - 1;
            int middleIndex2 = n / 2;
            median = (intArray[middleIndex1] + intArray[middleIndex2]) / 2.0;
        } else {
            int middleIndex = n / 2;
            median = intArray[middleIndex];
        }
        return median;
	 }

	    /**
	     * Navigates back to the previous page.
	     *
	     * @param event The ActionEvent triggered by the back_button.
	     * @throws IOException If an error occurs during the navigation to the previous page.
	     */
	 public void back(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/Show Report.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
	 }
}
