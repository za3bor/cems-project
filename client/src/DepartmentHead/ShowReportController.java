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
 * JavaFX controller class for the "ShowReport.fxml" view.
 * Handles the user interactions and logic for displaying different types of reports.
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class ShowReportController {

    /**
     * ComboBox for selecting the type of report to display.
     */
    @FXML
    private ComboBox<String> info_report_head;

    /**
     * Button for initiating the display of the selected report.
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
     * Initializes the controller.
     * Adds options to the info_report_head ComboBox.
     */
    public void initialize() {
        // Add options to the infoStationComboBox
    	info_report_head.getItems().addAll("various tests of a particular lecturer", "various tests of course","various tests of student");
    }
    /**
     * Displays the selected report based on the chosen option from the info_report_head ComboBox.
     *
     * @param event the ActionEvent triggered by clicking the show_button
     * @throws IOException if there is an error loading the FXML file or creating the scene
     */
    public void show(ActionEvent event) throws IOException {
        String selectedRole = info_report_head.getValue();
        if (selectedRole == null) {
            status.setText("Please select a role.");
            return;
        }
        if (selectedRole.equals("various tests of a particular lecturer") ) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/Show Report 1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        
        else if (selectedRole.equals("various tests of course") ) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/Show Report 2.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else if (selectedRole.equals("various tests of student") ) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/Show Report 3.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    
    /**
     * Navigates back to the DepartmentHeadMain.fxml page.
     *
     * @param event the ActionEvent triggered by clicking the back_button
     * @throws IOException if there is an error loading the FXML file or creating the scene
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
