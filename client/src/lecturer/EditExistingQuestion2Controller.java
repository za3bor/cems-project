package lecturer;

import logic.Question;

import static client.ChatClient.sqldone;
import static client.ClientUI.chat;
import static lecturer.EditExistingQuestionController.id_QuestionExist;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Map;

import static client.ChatClient.resultList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
 * Represents a controller class for editing an existing question.
 * This class handles the user interface and logic for editing a question.
 * It provides methods for initializing the controller, editing the question,
 * navigating back, and displaying alerts.
 * 
 */
public class EditExistingQuestion2Controller {
	/**
	 * The text field for displaying notes or messages.
	 */
	@FXML
    private Text id_note;
	/**
	 * The text field for entering the question.
	 */
	@FXML
    private TextField id_question;
	/**
	 * The text field for entering the first answer option.
	 */
	@FXML
    private TextField id_answer1;
	/**
	 * The text field for entering the second answer option.
	 */
	@FXML
    private TextField id_answer2;
	/**
	 * The text field for entering the third answer option.
	 */
	@FXML
    private TextField id_answer3;
	/**
	 * The text field for entering the correct answer option.
	 */
	@FXML
    private TextField id_rightanswer;
    /**
     * Initializes the controller and sets the initial values for the text fields
     * based on the existing question.
     */
    public void initialize() {
        String checkquery = "SELECT * FROM question WHERE QuestionNumber = ?";
        Object[] checkparams = { id_QuestionExist };
        sqlmessage checkmessage = new sqlmessage("get", checkquery, checkparams);
        chat.accept(checkmessage);
        List<Question> questionList = new ArrayList<>();
        for (Map<String, Object> row : resultList) {
            Question question = Question.convertToQuestion(row);
            questionList.add(question);
        } 
        id_question.setText(questionList.get(0).getQuestionText());
        id_answer1.setText(questionList.get(0).getAnswer1());
        id_answer2.setText(questionList.get(0).getAnswer2());
        id_answer3.setText(questionList.get(0).getAnswer3());
        id_rightanswer.setText(questionList.get(0).getAnswerCorrect());
    }
    /**
     * Handles the event when the "Edit" button is clicked.
     * Retrieves the modified question data from the text fields and updates the
     * corresponding question in the database.
     * 
     * @param event The action event triggered by clicking the "Edit" button.
     * @throws IOException If an error occurs during navigation.
     */
    public void Edit(ActionEvent event) throws IOException {
    	String checkquery = "UPDATE question SET QuestionText = ? , Answer1 = ? ,Answer2 = ? , Answer3 = ?, AnswerCorrect = ?"
    			+ " WHERE QuestionNumber = ?";
        Object[] checkparams = {id_question.getText(),id_answer1.getText(),id_answer2.getText(),id_answer3.getText(),
        		id_rightanswer.getText() ,id_QuestionExist };
        System.out.println(id_QuestionExist);
        sqlmessage checkmessage = new sqlmessage("edit", checkquery, checkparams);
        chat.accept(checkmessage);
        if(sqldone==false) {
        	System.out.println("update failed");
        	return;
        }
        if(sqldone==true) {
        	System.out.println("update successfully");
        	back(event);
        	return;
        }
    }
    /**
     * Handles the event when the "Back" button is clicked.
     * Closes the current window and navigates back to the previous screen.
     * 
     * @param event The action event triggered by clicking the "Back" button.
     * @throws IOException If an error occurs during navigation.
     */
    public void back(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
