package lecturer;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * Represents a controller class for managing the exam and question bank view.
 * This class handles the user interface and logic for navigating between the exam bank
 * and question bank views. It provides methods for initializing the controller,
 * navigating to the exam bank or question bank based on the selected option,
 * and navigating back to the main lecturer view.
 */
public class ExamQuestionBankController {
	
    /**
     * The combo box for selecting the exam or question bank option.
     */
    @FXML
    private ComboBox<String> ExamQuestionComboBox;
    /**
     * The button for navigating to the selected option.
     */
    @FXML
    private Button next_button;
    /**
     * The button for navigating back to the main lecturer view.
     */
    @FXML
    private Button back_button;
    /**
     * The text field for displaying the status or error messages.
     */
    @FXML
    private Text status;
    /**
     * Initializes the controller by adding options to the combo box.
     */
    public void initialize() {
        // Add options to the infoStationComboBox
    	ExamQuestionComboBox.getItems().addAll("Exams", "Questions");
    }
    /**
     * Handles the action event when the next button is clicked.
     * Navigates to the selected option (exam bank or question bank).
     *
     * @param event The action event triggered by the next button.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    public void next(ActionEvent event) throws IOException {
        String selectedRole = ExamQuestionComboBox.getValue();
        if (selectedRole == null) {
            status.setText("Please select a role.");
            return;
        }
        if (selectedRole.equals("Exams") ) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/Exam Bank.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else if (selectedRole.equals("Questions") ) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/Question Bank.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    /**
     * Handles the action event when the back button is clicked.
     * Navigates back to the main lecturer view.
     *
     * @param event The action event triggered by the back button.
     * @throws IOException If an error occurs while loading the FXML file.
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
