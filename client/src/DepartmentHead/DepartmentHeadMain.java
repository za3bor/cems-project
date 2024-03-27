package DepartmentHead;

import static client.ChatClient.isexsit;

import static client.ClientUI.chat;

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
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.sqlmessage;
import static gui.LogIn.DepartmentHeadinfo;

/**
 * Controller class for the Department Head Main screen.
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class DepartmentHeadMain {
	
    /**
     * Button for accessing the Statistics screen.
     */
    @FXML
    private Button Statistic_button;
    
    /**
     * Button for accessing the Question Bank screen.
     */
    @FXML
    private Button Q_B_button;
    
    /**
     * Button for accessing the Exam Statistics screen.
     */
    @FXML
    private Button examstat_button;
    
    /**
     * Text displaying a welcome message.
     */
    @FXML
    private Text welcome_text;
    
    /**
     * Flag indicating whether the SMS thread should stop.
     */
    private boolean stop = false;
    
    /**
     * Flag indicating whether there is a new SMS.
     */
    public static int flag = 0;
    /**
     * Initializes the Department Head Main screen.
     */
    public void initialize() {
        if (DepartmentHeadinfo != null && !DepartmentHeadinfo.isEmpty()) {
            String name = "Welcome back " +DepartmentHeadinfo.get(0).getFirstName() + " " + DepartmentHeadinfo.get(0).getLastName();
            welcome_text.setText(name);
        }
    	SMSThread smsThread = new SMSThread();
        Thread thread = new Thread(smsThread);
        thread.setDaemon(true); 
        thread.start();
    }
    /**
     * Switches to the SMS screen.
     * 
     * @param event The action event triggered by the button.
     * @throws IOException If an I/O error occurs while loading the SMS screen.
     */
	  public void goTosms(ActionEvent event) throws IOException {
		    stop=true;
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/sms.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
	    }
	  /**
	     * Logs out the Department Head and switches to the login screen.
	     * 
	     * @param event The action event triggered by the button.
	     * @throws IOException If an I/O error occurs while loading the login screen.
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
		     * Switches to the Student Grade screen.
		     * 
		     * @param event The action event triggered by the button.
		     * @throws IOException If an I/O error occurs while loading the Student Grade screen.
		     */
		 public void ss(ActionEvent event) throws IOException{
			    stop=true;
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/Student grade.fxml"));
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
		     * Switches to the Statistic screen.
		     * 
		     * @param event The action event triggered by the button.
		     * @throws IOException If an I/O error occurs while loading the Statistic screen.
		     */
		  public void Statistic(ActionEvent event) throws IOException {
			    stop=true;
		    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/Show Report.fxml"));
		        Parent root = loader.load();
		        Scene scene = new Scene(root);
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.setScene(scene);
		        stage.show();
		   }
		  /**
		     * Switches to the Question Bank screen.
		     * 
		     * @param event The action event triggered by the button.
		     * @throws IOException If an I/O error occurs while loading the Question Bank screen.
		     */
		  public void showquestion(ActionEvent event) throws IOException {
			    stop=true;
		    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/head Question Bank.fxml"));
		        Parent root = loader.load();
		        Scene scene = new Scene(root);
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.setScene(scene);
		        stage.show();
		   }
		  /**
		     * Switches to the Exam Statistics screen.
		     * 
		     * @param event The action event triggered by the button.
		     * @throws IOException If an I/O error occurs while loading the Exam Statistics screen.
		     */
		  public void gotoExamtatistic(ActionEvent event) throws IOException {
			  stop=true;
		    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/head exam stat.fxml"));
		        Parent root = loader.load();
		        Scene scene = new Scene(root);
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.setScene(scene);
		        stage.show();
		   }
		  /**
		     * Switches to the Exam Bank screen.
		     * 
		     * @param event The action event triggered by the button.
		     * @throws IOException If an I/O error occurs while loading the Exam Bank screen.
		     */
		  public void goToExamBank(ActionEvent event) throws IOException {
			    stop=true;
		    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/Exam Bank.fxml"));
		        Parent root = loader.load();
		        Scene scene = new Scene(root);
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.setScene(scene);
		        stage.show();
		    }
		  
		   /**
		     * Internal thread class for checking SMS notifications.
		     */
		  public class SMSThread extends Task<Void> {
			    @Override
			    protected Void call() throws Exception {
			        while (true) {
			            if (stop) {
			                break;
			            }
			            if(flag==0) {
			            boolean hasSMS = haveSMS();

			            if (hasSMS) {
			                Platform.runLater(() -> {
			                    Alert alert = new Alert(AlertType.WARNING);
			                    alert.setTitle("SMS");
			                    alert.setHeaderText(null);
			                    alert.setContentText("You have a SMS");
			                    alert.showAndWait();
			                });
			            }
			            }
			            Thread.sleep(5000);
			        }

			        return null;
			    }
			    /**
		         * Checks if the department head has any new SMS messages.
		         *
		         * @return true if there are new SMS messages, false otherwise.
		         */

			    private boolean haveSMS() {
			    	String query = "SELECT * FROM sms WHERE Department = ? AND response IS NULL";
			        Object[] params = { DepartmentHeadinfo.get(0).getDepartment() };
			        sqlmessage message = new sqlmessage("check", query, params);
			        chat.accept(message);
			        if (isexsit) {
			        	flag=1;
			            return true;
			        }
			        return false;
			    }
			}
		  /**
		     * Switches to the Static Report for 2 Exams screen.
		     * 
		     * @param event The action event triggered by the button.
		     * @throws IOException If an I/O error occurs while loading the Static Report for 2 Exams screen.
		     */
		  public void mani(ActionEvent event) throws IOException {
			  stop=true;
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/static report for 2 exam.fxml"));
		      Parent root = loader.load();
		      Scene scene = new Scene(root);
		      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		      stage.setScene(scene);
		      stage.show();
		  }
}
