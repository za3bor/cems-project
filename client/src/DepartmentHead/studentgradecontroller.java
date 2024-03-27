package DepartmentHead;

import static client.ChatClient.resultList;
import static client.ClientUI.chat;
import static gui.LogIn.DepartmentHeadinfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.examresult;
import logic.Course;
import logic.sqlmessage;

/**
 * The studentgradecontroller class is a JavaFX controller responsible for handling the UI events and actions
 * related to displaying student grades.
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class studentgradecontroller {
	
	/**
	 * The show_button is a JavaFX button used to trigger the display of student grades.
	 */
	@FXML
	private Button show_button;

	/**
	 * The back_button is a JavaFX button used to navigate back to the previous screen.
	 */
	@FXML
	private Button back_button;

	/**
	 * The error_text is a JavaFX text component used to display error messages or status information.
	 */
	@FXML
	private Text error_text;

	/**
	 * The S_ID_textfield is a JavaFX text field used for inputting a student ID.
	 */
	@FXML
	private TextField S_ID_textfield;

	/**
	 * The examTable is a JavaFX table view used to display the student's exam results.
	 */
	@FXML
	private TableView<examresult> examTable;

	/**
	 * The exID is a table column in the examTable that displays the exam IDs.
	 */
	@FXML
	private TableColumn<examresult, String> exID;

	/**
	 * The Grade is a table column in the examTable that displays the exam grades.
	 */
	@FXML
	private TableColumn<examresult, String> Grade;

	/**
	 * The courseName is a table column in the examTable that displays the course names.
	 */
	@FXML
	private TableColumn<examresult, String> courseName;

	/**
	 * The course_list is a list that stores Course objects retrieved from the database.
	 */
	private List<Course> course_list = new ArrayList<>();

	/**
	 * The examres_list is a list that stores examresult objects retrieved from the database.
	 */
	private List<examresult> examres_list = new ArrayList<>();

	/**
	 * The examres2_list is a list that stores filtered examresult objects based on selected criteria.
	 */
	private List<examresult> examres2_list = new ArrayList<>();

	/**
	 * The stID_list is a list that stores the student IDs associated with the exam results.
	 */
	private List<String> stID_list = new ArrayList<>();

	/**
	 * The gradetable is an ObservableList used to populate the examTable with examresult objects.
	 */
	private ObservableList<examresult> gradetable = FXCollections.observableArrayList();



    /**
     * Initializes the controller by setting up table columns and retrieving necessary data from the database.
     * The fade-in animation is also applied to the examTable.
     */
    public void initialize() {
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), examTable);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    	exID.setCellValueFactory(new PropertyValueFactory<>("ExamID"));
    	Grade.setCellValueFactory(new PropertyValueFactory<>("ExamResult"));
    	courseName.setCellValueFactory(new PropertyValueFactory<>("Course"));
        String query = "SELECT * FROM course WHERE Department= ?";
        Object[] pram = {DepartmentHeadinfo.get(0).getDepartment()};
        sqlmessage message = new sqlmessage("get", query, pram);
        chat.accept(message);
        
        for (Map<String, Object> row : resultList) {
            Course course = Course.convertCourse(row);
            course_list.add(course);
        }
        
        String query1 = "SELECT * FROM examresult";
        Object[] pram1 = {};
        sqlmessage message1 = new sqlmessage("get", query1, pram1);
        chat.accept(message1);

        
        for (Map<String, Object> row : resultList) {
            examresult ex = examresult.convertToExamResult(row);
            examres_list.add(ex);
        }
        
        for(examresult ex : examres_list) {
        	for(Course course : course_list) {
        		if(ex.getCourse().equals(course.getCourseName())) {
        			examres2_list.add(ex);
        			stID_list.add(ex.getStudentID());
        		}
        	}
        }
    }
    /**
     * Handles the action when the "Show" button is clicked.
     * Retrieves the student's grades and displays them in the examTable.
     *
     * @param event The action event.
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    public void show(ActionEvent event) throws IOException {
    	gradetable.clear();
    	String sID=S_ID_textfield.getText();
		error_text.setText("");
		if (S_ID_textfield.getText().isEmpty()) {
    		error_text.setText("Please fill textfield.");
    	}
		else if(!stID_list.contains(S_ID_textfield.getText())) {
    		error_text.setText("This student doesn't have grades.");
    	}
    	else {            
            for (examresult ex :examres2_list) {
            	if(ex.getStudentID().equals(sID))
            		gradetable.add(ex);
            }
            examTable.setItems(gradetable);
    	}
    }
    /**
     * Handles the action when the "Back" button is clicked.
     * Loads the DepartmentHeadMain.fxml file.
     *
     * @param event The action event.
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    public void back(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/DepartmentHeadMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
