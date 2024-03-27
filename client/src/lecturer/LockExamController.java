package lecturer;
import static client.ClientUI.chat;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
 * The controller class for the LockExam view.
 */
public class LockExamController {
	/**
	 * The TextField for entering the exam ID.
	 */
	@FXML
    private TextField examIDTextField;
	/**
	 * The Button for locking the exam.
	 */
    @FXML
    private Button lockButton;
    /**
     * The Button for exiting the application.
     */
    @FXML
    private Button exitButton;
    /**
     * The Button for navigating back to the previous view.
     */
    @FXML
    private Button backButton;
    /**
     * The Text component for displaying the status or error messages.
     */
    @FXML
    private Text status;
    
    /**
     * Handles the back button action.
     * Redirects the user to the LecturerMain view.
     *
     * @param event The action event.
     * @throws IOException If an I/O error occurs during the redirection.
     */
    public void back(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/LecturerMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Handles the lock button action.
     * Locks the specified exam.
     *
     * @param event The action event.
     * @throws IOException If an I/O error occurs during the locking process.
     */
    public void LockExam(ActionEvent event) throws IOException{
    	String examid= examIDTextField.getText();
    	if (examid.isEmpty()) {
    		status.setText("Exam Code is required");
    		return;
    	}
    	String query = "UPDATE startexam SET isLocked = ? WHERE ExamID = ?";
    	Object[] params= {1,examid};
    	sqlmessage message=new sqlmessage("edit",query,params);
	    chat.accept(message);

    }
    /**
     * Handles the exit button action.
     * Redirects the user to the LecturerMain view.
     *
     * @param event The action event.
     * @throws IOException If an I/O error occurs during the redirection.
     */
    public void exitapp(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/LecturerMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        // Close the current window
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
