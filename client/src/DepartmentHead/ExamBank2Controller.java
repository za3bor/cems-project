package DepartmentHead;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static DepartmentHead.ExamBankController.exam;
import static client.ChatClient.resultList;
import static client.ClientUI.chat;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.Exam;
import logic.ExamQuestion;
import logic.Question;
import logic.sqlmessage;
/**
 * Controller class for the Exam Bank 2 screen.
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class ExamBank2Controller {
	/**
	 * AnchorPane that serves as the container for the UI elements.
	 */
	@FXML
	private AnchorPane anchorPane;

	/**
	 * TableView that displays the exam questions.
	 */
	@FXML
	private TableView<ExamQuestion> examTable;

	/**
	 * TableColumn for displaying the ID of the question.
	 */
	@FXML
	private TableColumn<Question, String> idColumn;

	/**
	 * TableColumn for displaying the lecturer associated with the question.
	 */
	@FXML
	private TableColumn<Question, String> LecturerColumn;

	/**
	 * TableColumn for displaying the text of the question.
	 */
	@FXML
	private TableColumn<Question, String> qu_text_co;

	/**
	 * TableColumn for displaying the first answer option.
	 */
	@FXML
	private TableColumn<Question, String> ans1_co;

	/**
	 * TableColumn for displaying the second answer option.
	 */
	@FXML
	private TableColumn<Question, String> ans2_co;

	/**
	 * TableColumn for displaying the third answer option.
	 */
	@FXML
	private TableColumn<Question, String> ans3_co;

	/**
	 * TableColumn for displaying the correct answer.
	 */
	@FXML
	private TableColumn<Question, String> corect_ans_co;

	/**
	 * TableColumn for displaying the point value of the question in the exam.
	 */
	@FXML
	private TableColumn<ExamQuestion, String> point_co;

	/**
	 * Text element displaying the ID of the exam.
	 */
	@FXML
	private Text exam_id_text;

	/**
	 * Text element displaying the course associated with the exam.
	 */
	@FXML
	private Text course_text1;

	/**
	 * List to store the retrieved exams.
	 */
	private List<Exam> examList2 = new ArrayList<>();

	/**
	 * ObservableList to store the exam questions for display in the table.
	 */
	private ObservableList<ExamQuestion> examQuestionList = FXCollections.observableArrayList();

    /**
     * Initializes the Exam Bank 2 screen.
     */
    public void initialize() {
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), examTable);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        // Set up the table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("QuestionNum"));
        LecturerColumn.setCellValueFactory(new PropertyValueFactory<>("LecturerID"));
        qu_text_co.setCellValueFactory(new PropertyValueFactory<>("QuestionText"));
        ans1_co.setCellValueFactory(new PropertyValueFactory<>("Answer1"));
        ans2_co.setCellValueFactory(new PropertyValueFactory<>("Answer2"));
        ans3_co.setCellValueFactory(new PropertyValueFactory<>("Answer3"));
        corect_ans_co.setCellValueFactory(new PropertyValueFactory<>("AnswerCorrect"));
        point_co.setCellValueFactory(new PropertyValueFactory<>("Point"));
        exam_id_text.setText(exam.getID());
        course_text1.setText(exam.getCourse());
        String query = "SELECT * FROM exam WHERE ID = ?";
        Object[] pram = {exam.getID()};
        sqlmessage message = new sqlmessage("get", query, pram);
        chat.accept(message);

        for (Map<String, Object> row : resultList) {
            Exam exam = Exam.convertToExam(row);
            examList2.add(exam);
        }
        Exam exam1=examList2.get(0);
        String[] parts=exam1.getQuestion().split(";");
        String[] parts2=exam1.getPointPerQuestion().split(";");
        int j=1;
        for (int i = 0; i < parts.length; i++) {
        	String query2 = "SELECT * FROM question WHERE QuestionNumber = ?";
            Object[] pram2 = {parts[i]};
            sqlmessage message2 = new sqlmessage("get", query2, pram2);
            chat.accept(message2);

            for (Map<String, Object> row : resultList) {
                Question question=Question.convertToQuestion(row);
                
                ExamQuestion ex_qu= ExamQuestion.getInstance(question,parts2[i+j]);
                j++;
                examQuestionList.add(ex_qu);
            }
        	
        }
        examTable.setItems(examQuestionList);
        
    }
    /**
     * Handles the action when the back button is clicked.
     *
     * @param event The action event triggered by clicking the back button.
     * @throws IOException If an I/O error occurs during the loading of the Exam Bank screen.
     */
	 public void back(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepartmentHead/Exam Bank.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
	 }

    
}

