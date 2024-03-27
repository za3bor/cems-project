package lecturer;

import javafx.scene.control.CheckBox;

import javafx.scene.layout.VBox;
import static client.ClientUI.chat;
import static client.ChatClient.resultList;
import static client.ChatClient.sqldone;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static gui.LogIn.Lecturerinfo;
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
import logic.Course;
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
 * Controller class for creating a new question.
 * Handles the initialization and actions of the view components.
 */
public class CreateNewQuestionController {
	   /**
     * Text element for displaying notes.
     */
    @FXML
    private Text id_note;
    /**
     * Text field for entering a question.
     */
    @FXML
    private TextField id_question;
    /**
     * Text field for entering the first answer.
     */
    @FXML
    private TextField id_answer1;
    /**
     * Text field for entering the second answer.
     */
    @FXML
    private TextField id_answer2;
    /**
     * Text field for entering the third answer.
     */
    @FXML
    private TextField id_answer3;
    /**
     * Text field for entering the correct answer.
     */
    @FXML
    private TextField id_rightanswer;
    /**
     * The subject of the course.
     */
   
    @FXML
    private Text id_Subject;
    
    /**
     * VBox element for containing course-related elements.
     */
    @FXML
    private VBox id_Course;
    
    /**
     * Button for saving changes.
     */
    @FXML
    private Button id_Save;
    
    /**
     * Button for saving changes and creating a new course.
     */
    @FXML
    private Button id_SaveCreate;
    
    /**
     * A list of Course objects.
     */
    private List<Course> CourseList = new ArrayList<>();
    
    /**
     * Initializes the controller.
     */
    public void initialize() {
    	String checkquery2 = "SELECT * FROM course WHERE ID_Subject = ?";
	    Object[] checkparams2 = {Lecturerinfo.get(0).getSubjectID()};
	    sqlmessage checkmessage2 = new sqlmessage("get", checkquery2, checkparams2);
	    chat.accept(checkmessage2);
	    for (Map<String, Object> row : resultList) {
	    	Course course = Course.convertCourse(row);
	    	CourseList.add(course);
	    }
	    id_Subject.setText(CourseList.get(0).getDepartment());
	    System.out.println(CourseList.size());
	    for (int i = 0; i < CourseList.size(); i++) {
	        CheckBox checkBox = new CheckBox(CourseList.get(i).getCourseName() + " " +CourseList.get(i).getID());
	        id_Course.getChildren().add(checkBox);
	    }
    }
    /**
     * Saves the question and performs necessary actions.
     * 
     * @param event The action event.
     */
    public void Save(ActionEvent event) {
        if (id_question.getText().isEmpty() ||
                id_answer1.getText().isEmpty() ||
                id_answer2.getText().isEmpty() ||
                id_answer3.getText().isEmpty() ||
                !isAnySelected() ||
                id_rightanswer.getText().isEmpty()&&isAnySelected()) {
            id_note.setText("Please fill in all fields.");
            return;
        } else {
        	for (Node node : id_Course.getChildren()) {
                if (node instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) node;
                    // Check if the CheckBox is selected
                    if (checkBox.isSelected()) {
                        // Get the selected value
                        String selectedValue = checkBox.getText();
                        String[] str = selectedValue.split(" ");
                        String checkquery3 = "INSERT INTO question (QuestionNumber,Subject,CourseName,QuestionText,LecturerID,Answer1,Answer2,Answer3,AnswerCorrect) VALUES (?,?,?,?,?,?,?,?,?)";
                        Object[] checkparams3 = {Lecturerinfo.get(0).getSubjectID(),Lecturerinfo.get(0).getDepartment(),str[0],id_question.getText()
                        		,Lecturerinfo.get(0).getID(),id_answer1.getText(),id_answer2.getText(),id_answer3.getText()
                        		,id_rightanswer.getText()};
                        sqlmessage checkmessage3 = new sqlmessage("save", checkquery3, checkparams3);
                        chat.accept(checkmessage3); 
                        if (sqldone) {
                        	id_note.setText("Question saved");
                        }
                        else
                        	id_note.setText("Save Question Failure");
                        
                    }
                }
            }
        	try {
        		if (event.getSource() instanceof Button) {
        		    Button clickedButton = (Button) event.getSource();
        		    String buttonId = clickedButton.getId();
        		    if(id_Save.equals(buttonId))
        		    	MainLecturer(event);
        		    	
        		    // Use the buttonId as needed
        		}  
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        	// Iterate over the children of the VBox
        	for (Node node : id_Course.getChildren()) {
        	    if (node instanceof CheckBox) {
        	        CheckBox checkBox = (CheckBox) node;
        	        checkBox.setSelected(false);
        	    }
        	}
    		id_question.setText("");
            id_answer1.setText("");
            id_answer2.setText("");
            id_answer3.setText("");
            id_rightanswer.setText("");
            id_note.setText("");
        	//String[] subject = id_Subject.getValue().split(" ");
        	//String[] course = id_Course.getValue().split(" ");
        	
            // All fields are filled, proceed with saving
            
            
            
            
            
        }
    }
    /**
     * Checks if any checkbox is selected.
     * 
     * @return True if any checkbox is selected, false otherwise.
     */
    public boolean isAnySelected() {
        // Iterate over the children of the VBox
        for (Node node : id_Course.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;

                // Check if the CheckBox is selected
                if (checkBox.isSelected()) {
                    return true; // Found a selected CheckBox, return true
                }
            }
        }

        return false; // No selected CheckBox found
    }

    /**
     * Navigates back to the main lecturer view.
     * 
     * @param event The action event.
     * @throws IOException if an I/O error occurs.
     */
    public void MainLecturer(ActionEvent event) throws IOException {
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
