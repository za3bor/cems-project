package student;

import static client.ChatClient.isexsit;
import static client.ChatClient.resultList;
import static client.ClientUI.chat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.CurrentExam;
import logic.StartExam;
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
 * Controller class for starting a manual exam.
 */
public class StartManualExamController {
	
	/**
	 * The TextField used to enter the exam code.
	 */
	@FXML 
	private TextField id_ExamCode;
	/**
	 * The Text element used to display text or messages.
	 */
	@FXML
	private Text text;
	/**
	 * The ID of the exam being started.
	 */
	public static String exam_id;
	/**
	 * A List of StartExam objects that stores the information of available exams.
	 */
	public static List<StartExam> startexam = new ArrayList<>();

	
    /**
     * Handles the action when the "Back" button is clicked.
     * Closes the current window and opens the student main window.
     * 
     * @param event The action event.
     * @throws IOException If an I/O error occurs.
     */
	public void Back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/student/StudentMain.fxml"));
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
     * Handles the action when the "Start" button is clicked.
     * Retrieves the exam information based on the entered exam code.
     * Opens the manual exam window if the exam is available.
     * 
     * @param event The action event.
     * @throws IOException If an I/O error occurs.
     */
	public void Start(ActionEvent event) throws IOException{
		String checkquery = "SELECT * FROM startexam WHERE ExamCode = ? AND IsLocked = ?";
        Object[] checkparams = { id_ExamCode.getText(),0 };
        sqlmessage checkmessage = new sqlmessage("check", checkquery, checkparams);
        chat.accept(checkmessage);
        if(!isexsit) {
        	showAlert("There No Exam available");
        	return;
        }
        
        sqlmessage checkmessage2 = new sqlmessage("get", checkquery, checkparams);
        chat.accept(checkmessage2);
        for (Map<String, Object> row : resultList) {
        	StartExam exm = StartExam.convertToStartExam(row);
        	startexam.add(exm);
        }
        StartExam examid = startexam.get(0);
        exam_id=examid.getExamID();
        String checkquery3 = "SELECT * FROM manual_exam WHERE exam_id = ? ";
        Object[] checkparams3 = { exam_id };
        sqlmessage checkmessage3 = new sqlmessage("check", checkquery3, checkparams3);
        chat.accept(checkmessage3);
        if(!isexsit) {
        	showAlert("There No Exam available");
        	return;
        }
        count();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/student/Start Manual Exam2.fxml"));
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
     * Displays an alert with the given message.
     * 
     * @param message The message to be displayed in the alert.
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
     * Increments the count of the current exam.
     */
	private void count() {
		String query = "SELECT * FROM currentexam WHERE examid = ?";
		Object[] pram = {exam_id};
		 sqlmessage message = new sqlmessage("get",query , pram);
		 chat.accept(message);
		 CurrentExam ex=CurrentExam.convertToCurrentExam(resultList.get(0));
         String query2 = "UPDATE currentexam SET count = ? WHERE examid = ?";
         Object[] pram2 = {ex.getCount()+1,exam_id};
		 sqlmessage message2 = new sqlmessage("edit",query2 , pram2);
		 chat.accept(message2);
	}

}
