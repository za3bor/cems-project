package lecturer;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
 * Controller class for the ManualExam view.
 */
public class ManualExamController {
	/**
	 * Handles button click events for navigating to other views.
	 * 
	 * @param event The ActionEvent triggered by the button click.
	 * @throws IOException if an I/O error occurs during the navigation.
	 */ 
	public void move(ActionEvent event) throws IOException{
		 Button button = (Button) event.getSource();
	     String buttonName = button.getText();
	     String fxmlPath = buttonName + ".fxml";
	     try {
	        	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		        Parent root = loader.load();
		        Scene scene = new Scene(root);
		        Stage stage = new Stage();
		        stage.setScene(scene);
		        stage.show();
		        // Close the current window
		        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        currentStage.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	/**
     * Handles the back button click event.
     * Navigates back to the LecturerMain view.
     * 
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException if an I/O error occurs during the navigation.
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
