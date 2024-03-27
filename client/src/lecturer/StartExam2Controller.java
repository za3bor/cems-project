package lecturer;

import static client.ChatClient.sqldone;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.Exam;
import logic.Question;
import logic.sqlmessage;
import static lecturer.StartExamController.ExamIdSelected;
import static client.ChatClient.isexsit;
import static client.ChatClient.resultList;
import static client.ClientUI.chat;
import static lecturer.StartExamController.examinfo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import static gui.LogIn.Lecturerinfo;


/**
 * Controller class for starting an exam.
 * This class implements the Initializable interface from JavaFX.
 * It handles the initialization and functionality of the start exam view.
 *
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class StartExam2Controller implements Initializable {
   
    /**
     * Table view for displaying questions.
     */
	@FXML
    private TableView<Question> id_TableView;
	   /**
     * Table column for the question text.
     */
    @FXML
    private TableColumn<Question, String> id_QuesCol;
    /**
     * Text field for entering the exam code.
     */
    @FXML
    private TextField id_ExamCode;
    /**
     * Label for displaying the exam duration.
     */
    @FXML
    private Label id_Duration;
    /**
     * Label for displaying the total points of the exam.
     */
    @FXML
    private Label id_TotalPoint;
    /**
     * Label for displaying the total number of questions in the exam.
     */
    @FXML
    private Label id_TotalQuesation;
    /**
     * Label for displaying the subject of the exam.
     */
    @FXML
    private Label id_Subject;
    /**
     * Label for displaying the course of the exam.
     */
    @FXML
    private Label id_ExamCourse;
    /**
     * Text element for displaying the exam code note.
     */
    @FXML
    private Text note_examcode;
    /**
     * List of exams.
     */
    private List<Exam> examList = new ArrayList<>();
    /**
     * List of questions.
     */
    private List<Question> questionList = new ArrayList<>();
    
    /**
     * 
     */
    private int TotalQuestion=0;
   
    /**
     * Initializes the controller after its root element has been completely processed.
     * 
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), id_TableView);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        String checkquery = "SELECT * FROM exam WHERE ID = ?";
        Object[] checkparams = { ExamIdSelected };
        sqlmessage checkmessage = new sqlmessage("get", checkquery, checkparams);
        chat.accept(checkmessage);
        for (Map<String, Object> row : resultList) {
            Exam exam = Exam.convertToExam(row);
            examList.add(exam);
        }     
        
        String input = examList.get(0).getQuestion();
        String[] questionIds = input.split(";");
        for (String string : questionIds) {
        	System.out.println(string);
		}
        String checkquery2 = "SELECT * FROM question WHERE QuestionNumber = ?";
        for (String questionId : questionIds) {
            Object[] checkparams2 = { questionId };
            sqlmessage checkmessage2 = new sqlmessage("get", checkquery2, checkparams2);
            chat.accept(checkmessage2);
            for (Map<String, Object> row : resultList) {
                Question question = Question.convertToQuestion(row);
                questionList.add(question);
            }
            TotalQuestion++;
        }
        // Set up the cell factory for the question column
        id_QuesCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuestionText()));
        // Set the question list as the items of the TableView
        id_TableView.getItems().addAll(questionList);
       // id_ExamCode.setText(examinfo.getID());
        id_Duration.setText(Integer.toString(examinfo.getDuration()));
        id_TotalPoint.setText(examinfo.getTotalPoint());
        id_TotalQuesation.setText(Integer.toString(TotalQuestion));
        id_Subject.setText(examinfo.getSubject());
        id_ExamCourse.setText(examinfo.getCourse());
        
    }
    /**
     * Starts the exam if the entered exam code is valid.
     * 
     * @param event The event representing the user action.
     * @throws IOException If an I/O error occurs.
     */
    public void Start(ActionEvent event) throws IOException {
    	if(id_ExamCode.getText().isEmpty()) {
    		note_examcode.setText("Enter Exam Code");
    		return;
    	}
    	if(id_ExamCode.getText().length()!=4) {
    		note_examcode.setText("Exam Code should be 4 digit");
    		return;
    	}
    	note_examcode.setText("");
    	String query = "SELECT * FROM startexam WHERE ExamCode = ?";
        Object[] params = {id_ExamCode.getText() };
        sqlmessage message = new sqlmessage("check", query, params);
        chat.accept(message);
        if (!isexsit) {
        	String checkquery3 = "INSERT INTO startexam (ExamID,LecturerName,ExamCode,StartTime,Duration,IsLocked,Subject,Course,Date) VALUES (?,?,?,?,?,?,?,?,?)";
            Date currentDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            Time currentTime = new Time(calendar.getTimeInMillis());
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
            Object[] checkparams3 = { examinfo.getID(),Lecturerinfo.get(0).getID(),id_ExamCode.getText(),currentTime
            		,id_Duration.getText(),"0",examinfo.getSubject(),examinfo.getCourse(),sqlDate };
            sqlmessage checkmessage3 = new sqlmessage("save", checkquery3, checkparams3);
            chat.accept(checkmessage3); 
            String addquery = "INSERT INTO currentexam (examid) VALUES (?)";
            Object[] addparams = {examinfo.getID()};
            sqlmessage addmessage = new sqlmessage("save", addquery, addparams);
            chat.accept(addmessage);
            

            Back(event);
            if(sqldone) {
            	System.out.println("Exam Start");
            	//chat.accept("CheckExams;" + examIDTextField.getText());
            }
            else
            	System.out.println("operation Failure Try again");
        }else
        	note_examcode.setText("Enter Another Code");
    }
    /**
     * Navigates back to the previous screen.
     * 
     * @param event The event representing the user action.
     * @throws IOException If an I/O error occurs.
     */
    public void Back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/Start Exam.fxml"));
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