package lecturer;
import static client.ClientUI.chat;
import static gui.LogIn.Lecturerinfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static client.ChatClient.sqldone;
import static client.ChatClient.isexsit;
import static client.ChatClient.resultList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Lecturer;
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
 * Represents a controller class for managing the exam duration change view.
 * This class handles the user interface and logic for changing the duration
 * of a specific exam. It provides methods for initializing the controller,
 * loading and displaying the exams, navigating back to the main lecturer view,
 * and sending a request to change the exam duration.
 */
public class ExamDurationController {
    /**
     * The combo box for selecting the exam ID.
     */
    @FXML
    private ComboBox<String> exam_id;
    /**
     *  The text field for entering the new duration.
     */
    @FXML
    private TextField new_duration_field;
    /**
     *  The text area for entering the reason for the duration change.
     */
    @FXML
    private TextArea reason_field;
    /**
     * The text field for displaying the status of the duration change request.
     */
    @FXML
    private Text status;
    /**
     * The text field for displaying the course name.
     */
    @FXML
    private Text course_ftext;
    /**
     * he text field for displaying the old duration.
     */
    @FXML
    private Text old_du_text;
    /**
     * Initializes the controller and sets up the exam ID combo box.
     * This method is automatically called after the FXML file has been loaded.
     * It retrieves the exams from the server and populates the exam ID combo box.
     */
    public void initialize() {
        Lecturer lecturer = Lecturerinfo.get(0);
        String checkquery = "SELECT * FROM startexam WHERE LecturerName = ?";
        Object[] checkparams = { lecturer.getID() };
        sqlmessage checkmessage = new sqlmessage("get", checkquery, checkparams);
        chat.accept(checkmessage);
        List<StartExam> examList = new ArrayList<>();
        for (Map<String, Object> row : resultList) {
            StartExam exam = StartExam.convertToStartExam(row);
            examList.add(exam);
            exam_id.getItems().add(exam.getExamID());
        }
        exam_id.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String selectedExamID = (String) newValue;
                for (StartExam exam : examList) {
                    if (exam.getExamID().equals(selectedExamID)) {
                        course_ftext.setText(exam.getCourse());
                        old_du_text.setText(String.valueOf(exam.getDuration()));
                        break;
                    }
                }
            }
        });
    }
    /**
     * Navigates back to the main lecturer view.
     * This method loads the main lecturer view and displays it in the current window.
     *
     * @param event The event triggering the navigation.
     * @throws IOException if an I/O error occurs while loading the view.
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
     * Sends a request to change the duration of the selected exam.
     * This method retrieves the selected exam ID, new duration, and reason
     * from the input fields and sends a request to the server to change
     * the duration of the exam. It displays the status of the request.
     *
     * @param event The event triggering the request.
     * @throws IOException if an I/O error occurs while sending the request.
     */
    public void send(ActionEvent event) throws IOException {
    	String examId = (String) exam_id.getValue();
        String newDuration = new_duration_field.getText();
        String reason = reason_field.getText();

        if (examId.isEmpty() || newDuration.isEmpty() || reason.isEmpty()) {
            status.setText("Fields cannot be empty");
            return;
        }
        String query = "SELECT * FROM exam WHERE ID = ?";
    	Object[] params= {examId};
    	sqlmessage message=new sqlmessage("check",query,params);
	    chat.accept(message);
	    if(!isexsit) {
	    	status.setText("The exam not exsist");
	    	return;
	    }
	    
	    String query3 = "SELECT * FROM sms WHERE exam_id = ?";
    	Object[] params3= {examId};
    	sqlmessage message3=new sqlmessage("check",query3,params3);
	    chat.accept(message3);
	    if(isexsit) {
	    	status.setText("This test request has already been sent");
	    	return;
	    }
	    
	    
	    String query2 = "INSERT INTO sms (department, lecturer, exam_id, `new duration`, reason) VALUES (?, ?, ?, ?, ?)";
    	Object[] params2= {Lecturerinfo.get(0).getDepartment(),Lecturerinfo.get(0).getFirstName(),examId,newDuration,reason};
    	sqlmessage message2=new sqlmessage("save",query2,params2);
	    chat.accept(message2);
	    if(sqldone) {
	    	status.setText("sent succesfully");

	    }
	    else {
	    	status.setText("Failed to send");
	    }
    }
}

