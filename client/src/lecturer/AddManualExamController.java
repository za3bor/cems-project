package lecturer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Exam;
import logic.myfile;
import logic.sqlmessage;
import static client.ChatClient.isexsit;
import static client.ChatClient.resultList;
import static client.ClientUI.chat;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * Controller class for the Add Manual Exam screen.
 */
public class AddManualExamController {
    /**
     * The root pane of the Add Manual Exam screen.
     */
    @FXML
    private AnchorPane rootPane;
    /**
     * The text field to display the exam status.
     */
    @FXML
    private Text exam_status;
    /**
     * The text field to enter the exam ID.
     */
    @FXML
    private TextField exam_id_field;
    /**
     * The text field to display the selected file path.
     */
  
    @FXML
    private TextField fileField;
    /**
     * The button to open the file chooser dialog.
     */
    @FXML
    private Button select_butt;
    /**
     * The button to add the manual exam.
     */
    @FXML
    private Button add_butt;

    /**
     * The file chooser for selecting a file.
     */
    private FileChooser fileChooser;
    /**
     * Initializes the controller.
     * This method is automatically called after the FXML file has been loaded.
     */
    public void initialize() {
        fileChooser = new FileChooser();
    }

 

    /**
     * Handles the action when the select button is clicked.
     * Opens a file chooser dialog to select a file.
     */
    @FXML
    private void handleSelectButtonAction() {
        File file = fileChooser.showOpenDialog(rootPane.getScene().getWindow());
        if (file != null) {
            fileField.setText(file.getAbsolutePath());
        }
    }
    /**
     * Handles the action when the add button is clicked.
     * Validates the input fields and adds the manual exam.
     */
    @FXML
    private void handleAddButtonAction() {
        String examId = exam_id_field.getText();
        String filePath = fileField.getText();

        if (examId.isEmpty()) {
            exam_status.setText("ID field is empty. Please enter a valid ID.");
            return;
        }
        if (filePath.isEmpty()) {
            exam_status.setText("Please select a file.");
            return;
        }
        
        String query = "SELECT exam_id FROM manual_exam WHERE exam_id = ?";
        Object[] parm= {examId};
        sqlmessage message=new sqlmessage("check", query, parm);
        chat.accept(message);
        
        if (isexsit) {
            // Exam already exists, show a dialog to confirm changing it
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.initModality(Modality.APPLICATION_MODAL);
            confirmation.initStyle(StageStyle.UNDECORATED);
            confirmation.setHeaderText("Exam already exists");
            confirmation.setContentText("The exam with ID " + examId + " already exists. Do you want to change it?");
            
            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");
            
            confirmation.getButtonTypes().setAll(yesButton, noButton);
            
            confirmation.showAndWait().ifPresent(buttonType -> {
                if (buttonType == yesButton) {
                	   // Read the file data as bytes
                    byte[] fileData = null;
                    try {
                        File file = new File(filePath);
                        FileInputStream fileInputStream = new FileInputStream(file);
                        fileData = new byte[(int) file.length()];
                        fileInputStream.read(fileData);
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        exam_status.setText("Failed to read file data.");
                        return;
                    }
                    
                    // Create the file object to send to the server
                    myfile fileObject = new myfile("save",examId, filePath, "lecturer", fileData);
                    
                    // Send the file object to the server
                    chat.accept(fileObject);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Exam with ID " + examId + " has been successfully modified.");
                    successAlert.showAndWait();
                    
                } else if (buttonType == noButton) {
                    return;
                }
            });
        } else {

            String query2 = "SELECT * FROM exam WHERE ID = ?";
            Object[] pram = {examId};
            sqlmessage message2 = new sqlmessage("get", query2, pram);
            chat.accept(message2);
            List<Exam> examList = new ArrayList();

            for (Map<String, Object> row : resultList) {
                Exam exam = Exam.convertToExam(row);
                examList.add(exam);
            }
            String subject=examList.get(0).getSubject();
            String insertQuery = "INSERT INTO manual_exam (exam_id,subject) VALUES (?,?)";
            Object[] insertParams = {examId,subject};
            sqlmessage insertMessage = new sqlmessage("save", insertQuery, insertParams);
            chat.accept(insertMessage);
            // Read the file data as bytes
            byte[] fileData = null;
            try {
                File file = new File(filePath);
                FileInputStream fileInputStream = new FileInputStream(file);
                fileData = new byte[(int) file.length()];
                fileInputStream.read(fileData);
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                exam_status.setText("Failed to read file data.");
                return;
            }
            
            // Create the file object to send to the server
            myfile fileObject = new myfile("save",examId, filePath, "lecturer", fileData);
            
            // Send the file object to the server
            chat.accept(fileObject);;
            
            // Display a success message or perform any other actions
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setHeaderText(null);
            successAlert.setContentText("Exam with ID " + examId + " has been successfully added.");
            successAlert.showAndWait();
        }
    }
    /**
     * Handles the action when the back button is clicked.
     * Returns to the Manual Exam screen.
     *
     * @param event The action event.
     * @throws IOException if an I/O error occurs while loading the FXML file.
     */
    public void back(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/Manual Exam.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    
}
