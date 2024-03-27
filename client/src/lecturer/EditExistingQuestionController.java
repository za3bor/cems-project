package lecturer;
import static client.ChatClient.isexsit;
import java.io.IOException;
import static client.ClientUI.chat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.sqlmessage;
import static client.ChatClient.sqldone;

/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * Represents a controller class for editing an existing question.
 * This class handles the user interface and logic for editing a question,
 * as well as deleting a question. It provides methods for initializing
 * the controller, editing a question, deleting a question, navigating back,
 * and checking the existence of a question.
 */
public class EditExistingQuestionController {
	/**
	 * The text field for entering the question ID.
	 */
	@FXML
    private TextField id_question;
	/**
	 * The button for navigating back to the previous screen.
	 */
    @FXML
    private Button id_Back;
	/**
	 * Stores the ID of the question being edited.
	 */
    public static String id_QuestionExist;
	/**
	 * Stores the ID of the question entered in the text field.
	 */
    private String QuestionId;
    
    
    /**
     * Handles the editing of a question.
     * This method checks if the question ID is provided,
     * verifies the existence of the question, and proceeds
     * to the question editor screen.
     * 
     * @throws IOException if an I/O error occurs when loading the editor screen
     */
    public void Edit() throws IOException {
    	if(!checkField()) {
    		System.out.println("Put Question ID please\n");
    		id_question.setText("");
    		return;
    	}
    	checkdataExist();
	    if(!isexsit) {
	    	System.out.println("Question not Exist");
	    	id_question.setText("");
	    	return;
	    }
	    isexsit=false;
	    id_QuestionExist=QuestionId;
	    EditExistingQuestion2();
    }
    /**
     * Handles the deletion of a question.
     * This method checks if the question ID is provided,
     * verifies the existence of the question, and deletes
     * the question from the database.
     * 
     * @throws IOException if an I/O error occurs when deleting the question
     */
    public void Delete() throws IOException {
    	if(!checkField()) {
    		System.out.println("Put Question ID please\n");
    		id_question.setText("");
    		return;
    	}
    	checkdataExist();
    	if(!isexsit) {
	    	System.out.println("Question not Exist");
	    	id_question.setText("");
	    	return;
	    }
    	String checkquery ="DELETE FROM question WHERE QuestionNumber = ?";
	    Object[] checkparams= {QuestionId};
	    sqlmessage checkmessage=new sqlmessage("delete",checkquery,checkparams);
	    chat.accept(checkmessage);
	    if(!sqldone) {
	    	System.out.println("No records found matching the criteria.");
	    	id_question.setText("");
	    	return;
	    }
	    System.out.println("Deletion successful");
	    id_question.setText("");
    }
    /**
     * Navigates to the question editor screen.
     * This method loads the editor FXML file and displays
     * the editor screen in a new stage.
     * 
     * @throws IOException if an I/O error occurs when loading the editor screen
     */
    public void EditExistingQuestion2() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/EditExistingQuestion2.fxml"));
    	Parent root = loader.load();
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
    	stage.setScene(scene);
    	stage.show();
    }
    /**
     * Checks if the question ID field is empty.
     * 
     * @return true if the question ID field is not empty, false otherwise
     */
    public boolean checkField() {
    	QuestionId = id_question.getText();
    	if(QuestionId.isEmpty())
    		return false;
    	return true;
    }
    /**
     * Checks if a question with the specified ID exists in the database.
     * This method sends a SQL query to the server to check the existence
     * of the question.
     */
    public void checkdataExist() {
    	String checkquery ="SELECT QuestionNumber FROM question WHERE QuestionNumber = ?";
	    Object[] checkparams= {QuestionId};
	    sqlmessage checkmessage=new sqlmessage("check",checkquery,checkparams);
	    chat.accept(checkmessage);
    }
    /**
     * Navigates back to the main lecturer screen.
     * This method loads the main lecturer FXML file and displays
     * the main lecturer screen in the current stage.
     * 
     * @param event the action event that triggered the navigation
     * @throws IOException if an I/O error occurs when loading the main lecturer screen
     */
    public void back(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/LecturerMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
