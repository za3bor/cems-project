package lecturer;

import static client.ClientUI.chat;

import static lecturer.EditExistingQuestionController.id_QuestionExist;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import static client.ChatClient.resultList;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.Question;
import logic.sqlmessage;
/**
 * Controller class for the Question Bank view.
 * It handles the display and management of question data in a table view.
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class QuestionBankController implements Initializable {
	/**
	 * The table view that displays the questions.
	 */
    @FXML
    private TableView<Question> questionTable;
    /**
     * The table column for the question ID.
     */
    @FXML
    private TableColumn<Question, String> idColumn;
    /**
     * The table column for the question subject.
     */
    @FXML
    private TableColumn<Question, String> subjectColumn;
    /**
     * The table column for the question number.
     */
    @FXML
    private TableColumn<Question, String> numberColumn;
    /**
     * The table column for the lecturer ID associated with the question.
     */
    @FXML
    private TableColumn<Question, String> lecturerColumn;
    /**
     * The table column for the course name associated with the question.
     */
    @FXML
    private TableColumn<Question, String> courseColumn;
    /**
     * The table column for the question text.
     */
    @FXML
    private TableColumn<Question, String> textColumn;
    /**
     * A flag indicating whether a move action has occurred.
     */
    public static int  move=0;

    /**
     * Initializes the Question Bank view.
     * This method is called after the FXML file has been loaded and the controller object has been created.
     * It sets up the table view, loads the question data from the database, and handles user interactions with the table view.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
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
	     String query = "SELECT * FROM question";
	     Object[] param= {};
	     sqlmessage message=new sqlmessage("get",query,param);
	     chat.accept(message);

	     
	     ObservableList<Question> questionList = FXCollections.observableArrayList();
	        
	     for (Map<String, Object> row : resultList) {
	    	 Question question = Question.convertToQuestion(row);
             questionList.add(question);
             
	        }
	     
	     questionTable.setItems(questionList);
	     
	     questionTable.setOnMouseClicked(event -> {
	    	    if (event.getClickCount() == 1) { // Detect single click
	    	        Question selectedQuestion = questionTable.getSelectionModel().getSelectedItem();
	    	        if (selectedQuestion != null) {
	    	            String questionNumber = selectedQuestion.getQuestionNum();
	    	            id_QuestionExist=questionNumber;
	    	            move=1;
	    	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/EditExistingQuestion2.fxml"));
	    		        Parent root = null;
						try {
							root = loader.load();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    		        Scene scene = new Scene(root);
	    		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    		        stage.setScene(scene);
	    		        stage.show();
	    	        }
	    	    }
	    	});
	    }
    /**
     * Handles the action when the "Back" button is clicked.
     *
     * @param event The action event.
     * @throws IOException if an I/O error occurs.
     */
	 public void back(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/Exam Question Bank.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
	    }
	    /**
	     * Handles the action when the "Add New Question" button is clicked.
	     *
	     * @param event The action event.
	     * @throws IOException if an I/O error occurs.
	     */
	 public void addnewquestion(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/Create New Question.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
	    }
		
	
}
