package student;

import static client.ChatClient.isexsit;
import static client.ClientUI.chat;
import static gui.LogIn.Studentinfo;
import java.io.IOException;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
 * The controller class for the main student view.
 */
public class StudentMain {
	   /**
     * The JavaFX Text component for displaying the welcome message.
     */
    @FXML
    private Text welcome_text;
    
    /**
     * Flag to control the background thread.
     */
	private boolean stop=false;
	
	/**
     * Initializes the student main view.
     */
	public void initialize() {
	    if (Studentinfo != null && !Studentinfo.isEmpty()) {
	    	String name = "Welcome back " +Studentinfo.get(0).getFirstName() + " " + Studentinfo.get(0).getLastName();
	    	welcome_text.setText(name);
	    }
    	SMSThread smsThread = new SMSThread();
        Thread thread = new Thread(smsThread);
        thread.setDaemon(true); 
        thread.start();
    }
    /**
     * Moves to the specified view when a button is clicked.
     *
     * @param event the event triggering the action
     * @throws IOException if an I/O error occurs
     */
	public void move(ActionEvent event) throws IOException{
		stop=true;
		 Button button = (Button) event.getSource();
	     String buttonName = button.getText();
	     String fxmlPath = buttonName + ".fxml";
	        try {
	        	if(buttonName.equals("Exams Result")){
	        		String checkquery = "SELECT * FROM examresult WHERE status = ? AND StudentID = ?";
			        Object[] checkparams = { "1", Studentinfo.get(0).getID()  };
			        sqlmessage checkmessage = new sqlmessage("check", checkquery, checkparams);
			        chat.accept(checkmessage);
			        if(!isexsit) {
			        	showAlert("There no Result Exam");
			        	return;
			        }
	        	}
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
     * Displays an alert dialog with the specified message.
     *
     * @param message the message to be displayed in the alert dialog
     */
	private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        ButtonType okayButton = new ButtonType("Okay");
        alert.getButtonTypes().setAll(okayButton);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == okayButton) {
                stage.close();
            }
        });
    }
    /**
     * Logs out the student and returns to the login screen.
     *
     * @param event the event triggering the action
     * @throws IOException if an I/O error occurs
     */
	public void logout(ActionEvent event) throws IOException{
		stop=true;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginClient.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();
	        // Close the current window
	        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        currentStage.close();
	 }
    /**
     * A background thread for checking SMS messages periodically.
     */
	public class SMSThread extends Task<Void> {

	    @Override
	    protected Void call() throws Exception {
	        while (true) {
	            if (stop) {
	                break;
	            }
	           
	            boolean hasSMS = haveSMS();

	            if (hasSMS) {
	                Platform.runLater(() -> {
	                    Alert alert = new Alert(AlertType.WARNING);
	                    alert.setTitle("SMS");
	                    alert.setHeaderText(null);
	                    alert.setContentText("You get a new grade");
	                    alert.showAndWait();
	                });
	            }
	            
	            Thread.sleep(5000);
	        }

	        return null;
	    }

	    /**
	     * Checks if the student has received any SMS messages.
	     *
	     * @return true if the student has received SMS messages, false otherwise
	     */
	    private boolean haveSMS() {
	    	String query = "SELECT * FROM student_grad WHERE StudentId = ? AND tmp = ? ";
	        Object[] params = {Studentinfo.get(0).getID(),1  };
	        sqlmessage message = new sqlmessage("check", query, params);
	        chat.accept(message);
	        if (isexsit) {
	        	String query2 = "UPDATE student_grad SET tmp = ? WHERE StudentId = ?";
	        	Object[] params2 = {0,Studentinfo.get(0).getID()  };
		        sqlmessage message2 = new sqlmessage("edit", query2, params2);
		        chat.accept(message2);
	            return true;
	        }
	        return false;
	    }
	}

}
