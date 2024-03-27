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
import java.util.HashSet;
import java.util.Set;
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
import logic.Student;
import logic.examresult;
import logic.sqlmessage;
/**
 * Controller class for the "Show Report 3" page in the DepartmentHead package.
 * Handles the logic and user interactions for displaying a report of exam results for a selected student.
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class ShowReport3Controller {
	/**
     * ComboBox for selecting a student.
     */
    @FXML
    private ComboBox<String> choose_std_combo;

    /**
     * Button for initiating the display of the report.
     */
    @FXML
    private Button show_button;

    /**
     * Button for navigating back to the previous page.
     */
    @FXML
    private Button back_button;

    /**
     * Text field for displaying the status or error messages.
     */
    @FXML
    private Text status;

    /**
     * Text field for displaying the average exam result.
     */
    @FXML
    private Text AVG_text;

    /**
     * Text field for displaying the median exam result.
     */
    @FXML
    private Text Median_text;

    /**
     * BarChart for visualizing the distribution of exam results.
     */
    @FXML
    private BarChart<String, Number> graph;

    /**
     * CategoryAxis for labeling the x-axis of the bar chart.
     */
    @FXML
    private CategoryAxis xAxis;

    /**
     * NumberAxis for labeling the y-axis of the bar chart.
     */
    @FXML
    private NumberAxis yAxis;

    /**
     * List to store Student objects.
     */
    private List<Student> student_List = new ArrayList<>();

    /**
     * List to store examresult objects.
     */
    private List<examresult> ex_List = new ArrayList<>();

    /**
     * List to store student IDs.
     */
    private List<String> studentID_List = new ArrayList<>();

    /**
     * List to temporarily store exam results.
     */
	private List<String> temp = new ArrayList<>();
	 /**
     * Initializes the controller by populating the choose_std_combo ComboBox with student IDs from the department.
     */
    public void initialize() {
	  	String department = null;
    	for (DepartmentHead departmentHead : DepartmentHeadinfo) {
    	    if (departmentHead.getUsername().equals(Username)) {
    	        department = departmentHead.getDepartment();
    	        break; // Exit the loop once a match is found
    	    }
    	}
		String query = "SELECT * FROM student WHERE Department= ?";
		Object[] params = {department};
		sqlmessage message = new sqlmessage("get", query, params);
		chat.accept(message);
		
		for (Map<String, Object> row : resultList) {
			Student student = Student.convertToStudent(row);
			student_List.add(student);
		}
		for(Student student : student_List) {
			studentID_List.add(student.getID());
		}
        Set<String> setWithoutDuplicates = new HashSet<>(studentID_List);
        studentID_List = new ArrayList<>(setWithoutDuplicates);
        
		for(String s : studentID_List) {
			choose_std_combo.getItems().addAll(s);
		}
    }
    /**
     * Event handler for the show_button. Displays the exam results for the selected student.
     *
     * @param event The ActionEvent triggered by the show_button.
     * @throws IOException If an error occurs during the navigation or data retrieval.
     */
	 public void show(ActionEvent event) throws IOException {
		 	ex_List.clear();
		    graph.getData().clear(); // Clear previous data from the BarChart

			int counter=0;
			double sum=0;
	
	        String selectedRole = choose_std_combo.getValue();
	        if (selectedRole == null) {
	            status.setText("Please select a role.");
	            return;
	        }
	        else {

				String query = "SELECT * FROM examresult WHERE StudentID= ?";
				Object[] params = {selectedRole};
				sqlmessage message = new sqlmessage("get", query, params);
				chat.accept(message);
				
				for (Map<String, Object> row : resultList) {
					examresult ex = examresult.convertToExamResult(row);
					ex_List.add(ex);
				}
				if(ex_List.isEmpty()) {
					System.out.println("hhh");
				}
				else {
		            List<XYChart.Series<String, Number>> seriesList = new ArrayList<>();
		            String[] grades = {"0-9", "10-19", "20-29", "30-39", "40-49", "50-59", "60-69", "70-79", "80-89", "90-100"};
		            for (int i = 0; i < 10; i++) {
		                XYChart.Series<String, Number> series = new XYChart.Series<>();
		                series.setName(grades[i]); // Custom column names as fruits
		                seriesList.add(series);
		            }
				     int columns1=0,columns2=0,columns3=0,columns4=0,columns5=0,columns6=0,columns7=0,
					            columns8=0,columns9=0,columns10=0;
					for(examresult ex : ex_List) {
						sum+=Double.parseDouble(ex.getExamResult());
						counter++;
						if(Double.parseDouble(ex.getExamResult())>=0.0 && Double.parseDouble(ex.getExamResult())<=9.0){
							columns1++;
						}
						else if(Double.parseDouble(ex.getExamResult())>=10.0 && Double.parseDouble(ex.getExamResult())<=19.0){
							columns2++;
	
						}
						else if(Double.parseDouble(ex.getExamResult())>=20.0 && Double.parseDouble(ex.getExamResult())<=29.0){
							columns3++;
	
						}
						else if(Double.parseDouble(ex.getExamResult())>=30.0 && Double.parseDouble(ex.getExamResult())<=39.0){
							columns4++;
	
						}
						else if(Double.parseDouble(ex.getExamResult())>=40.0 && Double.parseDouble(ex.getExamResult())<=49.0){
							columns5++;
	
						}
						else if(Double.parseDouble(ex.getExamResult())>=50.0 && Double.parseDouble(ex.getExamResult())<=59.0){
							columns6++;
	
						}
						else if(Double.parseDouble(ex.getExamResult())>=60.0 && Double.parseDouble(ex.getExamResult())<=69.0){
							columns7++;
	
						}
						else if(Double.parseDouble(ex.getExamResult())>=70.0 && Double.parseDouble(ex.getExamResult())<=79.0){
							columns8++;
	
						}
						else if(Double.parseDouble(ex.getExamResult())>=80.0 && Double.parseDouble(ex.getExamResult())<=89.0){
							columns9++;
	
						}
						else if(Double.parseDouble(ex.getExamResult())>=90.0 && Double.parseDouble(ex.getExamResult())<=100.0){
							columns10++;
	
						}
						
					}
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
	
		    		if(counter!=0)
		    			sum=sum/counter;
		    		AVG_text.setText(String.valueOf(sum));
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
		 for(examresult ex : ex_List) {
	 		temp.add(ex.getExamResult());
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
	     * Event handler for the back_button. Navigates back to the "Show Report" page.
	     *
	     * @param event The ActionEvent triggered by the back_button.
	     * @throws IOException If an error occurs during the navigation.
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
