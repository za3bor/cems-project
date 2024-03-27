package DepartmentHead;

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
 * The StaticReport2ExamCONTROLLER class is a JavaFX controller responsible for handling the UI events and actions
 * related to the static report for exams.
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class StaticReport2ExamCONTROLLER {
	  /**
     * The ComboBox for selecting the information report head.
     */
    @FXML
    private ComboBox<String> info_report_head;

    /**
     * The button for displaying the report.
     */
    @FXML
    private Button show_button;

    /**
     * The button for going back to the previous screen.
     */
    @FXML
    private Button back_button;

    /**
     * The text field for displaying the status or error messages.
     */
    @FXML
    private Text status;
	    
	    /**
	     * Initializes the controller.
	     * Adds options to the infoStationComboBox.
	     */
	    public void initialize() {
	        // Add options to the infoStationComboBox
	    	info_report_head.getItems().addAll("Two exams for the same lecturer", "Two exams in the same lecturer");
	    }
	    /**
	     * Handles the action when the "Show" button is clicked.
	     * Loads the appropriate FXML file based on the selected role.
	     * @param event The action event.
	     * @throws IOException If an I/O error occurs while loading the FXML file.
	     */
	    public void show(ActionEvent event) throws IOException {
	        String selectedRole = info_report_head.getValue();
	        if (selectedRole == null) {
	            status.setText("Please select a role.");
	            return;
	        }
	        if (selectedRole.equals("Two exams for the same lecturer") ) {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/static report 2 exam lec.fxml"));
	            Parent root = loader.load();
	            Scene scene = new Scene(root);
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            stage.setScene(scene);
	            stage.show();
	        }
	        
	        else if (selectedRole.equals("Two exams in the same lecturer") ) {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/static report 2 exam course.fxml"));
	            Parent root = loader.load();
	            Scene scene = new Scene(root);
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            stage.setScene(scene);
	            stage.show();
	        }
	    }
	    
	    /**
	     * Handles the action when the "Back" button is clicked.
	     * Loads the DepartmentHeadMain.fxml file.
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
