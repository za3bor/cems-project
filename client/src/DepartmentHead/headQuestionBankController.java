package DepartmentHead;

import static client.ChatClient.resultList;
import static client.ClientUI.chat;
import static gui.LogIn.DepartmentHeadinfo;
import static gui.LogIn.Username;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.DepartmentHead;
import logic.Lecturer;
import logic.Question;
import logic.sqlmessage;
/**
 * Controller class for the headQuestionBank.fxml file.
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class headQuestionBankController implements Initializable{
	/**
	 * TableView component used for displaying a collection of Question objects.
	 * Each row in the table represents a single Question object.
	 */
	@FXML
	private TableView<Question> questionTable;

	/**
	 * Table column for the question ID.
	 */
	@FXML
	private TableColumn<Question, String> idColumn;

	/**
	 * Table column for the question subject.
	 */
	@FXML
	private TableColumn<Question, String> subjectColumn;

	/**
	 * Table column for the question number.
	 */
	@FXML
	private TableColumn<Question, String> numberColumn;

	/**
	 * Table column for the lecturer associated with the question.
	 */
	@FXML
	private TableColumn<Question, String> lecturerColumn;

	/**
	 * Table column for the course associated with the question.
	 */
	@FXML
	private TableColumn<Question, String> courseColumn;

	/**
	 * Table column for the question text.
	 */
	@FXML
	private TableColumn<Question, String> textColumn;

	/**
	 * Button used to navigate back to the previous screen or view.
	 */
	@FXML
	private Button back_butt;

	/**
	 * List of questions in the question bank.
	 */
	private List<Question> questionList = new ArrayList<>();

	/**
	 * List of lecturers associated with the questions.
	 */
	private List<Lecturer> lecturer_List = new ArrayList<>();


    /**
     * Initializes the controller.
     * 
     * @param location  The location used to resolve relative paths for the root object or null if the location is not known.
     * @param resources The resources used to localize the root object or null if the root object was not localized.
     */
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), questionTable);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
		idColumn.setCellValueFactory(new PropertyValueFactory<>("QuestionNum"));
		subjectColumn.setCellValueFactory(new PropertyValueFactory<>("Subject"));
	    lecturerColumn.setCellValueFactory(new PropertyValueFactory<>("LecturerID"));
	    courseColumn.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
	    textColumn.setCellValueFactory(new PropertyValueFactory<>("QuestionText"));
    	String department = null;
    	for (DepartmentHead departmentHead : DepartmentHeadinfo) {
    	    if (departmentHead.getUsername().equals(Username)) {
    	        department = departmentHead.getDepartment();
    	        break; // Exit the loop once a match is found
    	    }
    	}
         ObservableList<Question> showquestionList = FXCollections.observableArrayList();
	     String query = "SELECT * FROM lecturer WHERE Department= ?";
	     Object[] param= {department};
	     sqlmessage message=new sqlmessage("get",query,param);
	     chat.accept(message);
	     
	     for (Map<String, Object> row : resultList) {
	    	 Lecturer lecturer = Lecturer.convertToLecturer(row);
             lecturer_List.add(lecturer);
	     }
	     
	     String query1 = "SELECT * FROM question";
	     Object[] param1= {};
	     sqlmessage message1=new sqlmessage("get",query1,param1);
	     chat.accept(message1);

	     for (Map<String, Object> row : resultList) {
	    	 Question question = Question.convertToQuestion(row);
             questionList.add(question);
	     }
	     
	     for(Question question : questionList) {
	    	 for(Lecturer lecturer : lecturer_List) {
	    		 if(question.getLecturerID().equals(lecturer.getID())) {
	    			 showquestionList.add(question);
	    		 }
	    	 }
	     }
	     questionTable.setItems(showquestionList);
 
	}
    
    /**
     * Handles the back button action.
     * 
     * @param event The ActionEvent triggered by clicking the button.
     * @throws IOException if an I/O error occurs when loading the FXML file.
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
