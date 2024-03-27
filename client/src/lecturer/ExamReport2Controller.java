package lecturer;

import static client.ChatClient.resultList;


import static client.ClientUI.chat;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import logic.Question;
import logic.sqlmessage;
import static lecturer.ExamReportController.examqeusLecturer;
import static lecturer.ExamReportController.Examresult;
import static lecturer.ExamReportController.rowIndexLecturer;;
/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * Represents a controller class for managing the exam report view.
 * This class handles the user interface and logic for displaying the exam report,
 * including the questions, answers, and grades.
 */
public class ExamReport2Controller implements Initializable {
	/**
	 * The TableView component for displaying the questions.
	 */
	@FXML
    private TableView<Question> id_TableView;
	/**
	 * The TableColumn for displaying the question IDs.
	 */
	@FXML
    private TableColumn<Question, String> id_IDCol;
	/**
	 * The TableColumn for displaying the question text.
	 */
	@FXML
    private TableColumn<Question, String> id_QuestionCol;
	/**
	 * The TableColumn for displaying the answer options.
	 */
	@FXML
    private TableColumn<Question, Void> id_AnswerCol;
	/**
	 * The Text component for displaying the exam grade.
	 */

    @FXML
    private Text idExamGrade;
    /**
     * The Text component for displaying the number of correct answers.
     */
   
    @FXML
    private Text idRightAnswer;
    /**
     * The Text component for displaying the number of wrong answers.
     */
    @FXML
    private Text idWrongAnwer;
    /**
     * A list to store the points assigned to each question.
     */
    private List<String> questionpoint = new ArrayList<>();
    /**
     * A list to store the retrieved questions.
     */
    private List<Question> questionList = new ArrayList<>();
    /**
     * An array to store the student's answers for the exam.
     */
    private String[] ExamAnswer;
    /**
     * An array to store the correct answers for the exam questions.
     */
    private String[] rightanswer;
    /**
     * The total number of correctly answered questions.
     */
    private int Totalrightquestion = 0;
    
    /**
     * Initializes the controller.
     * This method is automatically called after the FXML file has been loaded.
     * It sets up the UI elements, retrieves exam answers and right answers,
     * calculates the exam grade, and retrieves the questions for display.
     *
     * @param arg0      The URL location of the FXML file.
     * @param arg1      The resources used to localize the root object.
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), id_TableView);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
		ExamAnswer = Examresult.get(rowIndexLecturer).getExamAnswer().split(";");
		for (String exam : ExamAnswer) {
			System.out.println("exam answer:- "+exam);
		}
		
		rightanswer = Examresult.get(rowIndexLecturer).getRightanswer().split(";");
		for (String exam : rightanswer) {
			System.out.println("right answer:- "+exam);

		}

		for (int i = 0; i < ExamAnswer.length; i++) {
			if(ExamAnswer[i].equals(rightanswer[i]))
				Totalrightquestion++;	
		}
		idExamGrade.setText(Examresult.get(rowIndexLecturer).getExamResult()+"%");
		idRightAnswer.setText(Integer.toString(Totalrightquestion));
		idWrongAnwer.setText(Integer.toString(ExamAnswer.length-Totalrightquestion));
		retrieveQuestions();
		id_IDCol.setCellValueFactory(new PropertyValueFactory<>("questionNum"));
        id_QuestionCol.setCellValueFactory(new PropertyValueFactory<>("questionText"));
        id_AnswerCol.setCellFactory(createButtonCellFactory());
        id_TableView.setItems(FXCollections.observableArrayList(questionList));
        id_QuestionCol.getStyleClass().add("question-text");
	}
    /**
     * Retrieves the questions for the exam report.
     * This method retrieves the questions based on their question numbers
     * stored in the examqeusLecturer list. It fetches the questions from the database
     * and adds them to the questionList.
     */
	 private void retrieveQuestions() {
		    questionpoint.clear();
		    questionList.clear();
		    String[] Questionid = examqeusLecturer.get(0).getPointPerQuestion().split(";");
		    String checkquery2 = "SELECT * FROM question WHERE QuestionNumber = ?";
		    for (int i = 0; i < Questionid.length; i += 2) {
		        Object[] checkparams2 = { Questionid[i] };
		        sqlmessage checkmessage2 = new sqlmessage("get", checkquery2, checkparams2);
		        chat.accept(checkmessage2);
		        for (Map<String, Object> row : resultList) {
		            Question question = Question.convertToQuestion(row);
		            questionList.add(question);
		        }
		        questionpoint.add(Questionid[i + 1]);
		    }
		}
	    /**
	     * Creates a custom cell factory for the answer column in the table.
	     * This method creates a custom TableCell with radio buttons for selecting answers.
	     * It also displays the question points, user's answer, and correct answer for each question.
	     *
	     * @return The created cell factory.
	     */
	private Callback<TableColumn<Question, Void>, TableCell<Question, Void>> createButtonCellFactory() {
        return new Callback<TableColumn<Question, Void>, TableCell<Question, Void>>() {
            @Override
            public TableCell<Question, Void> call(TableColumn<Question, Void> param) {
                return new TableCell<Question, Void>() {
                	 private final RadioButton radioButton1 = new RadioButton("");
                     private final RadioButton radioButton2 = new RadioButton("");
                     private final RadioButton radioButton3 = new RadioButton("");
                     private final RadioButton radioButton4 = new RadioButton("");
                     private final ToggleGroup toggleGroup = new ToggleGroup();
                     private final VBox container = new VBox();
                     private final Text questionPointText = new Text();
                     private final Text YourAnwer = new Text();
                     private final Text RightAnswer = new Text();
                    {
                    	questionPointText.getStyleClass().add("question-point");
                    	radioButton1.getStyleClass().add("radio-button");
                        radioButton2.getStyleClass().add("radio-button");
                        radioButton3.getStyleClass().add("radio-button");
                        radioButton4.getStyleClass().add("radio-button");
                    	 container.getChildren().addAll(questionPointText, radioButton1, radioButton2, radioButton3, radioButton4,YourAnwer,RightAnswer);
                         container.setSpacing(5);
                         radioButton1.setToggleGroup(toggleGroup);
                         radioButton2.setToggleGroup(toggleGroup);
                         radioButton3.setToggleGroup(toggleGroup);
                         radioButton4.setToggleGroup(toggleGroup);
                    }
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            Question question = (Question) getTableRow().getItem();

                            if (question != null) {
                                List<String> answers = Arrays.asList(
                                        question.getAnswer1(),
                                        question.getAnswer2(),
                                        question.getAnswer3(),
                                        question.getAnswerCorrect()
                                );
                                Collections.shuffle(answers);
                                radioButton1.setText(answers.get(0));
                                radioButton2.setText(answers.get(1));
                                radioButton3.setText(answers.get(2));
                                radioButton4.setText(answers.get(3));

                                int questionIndex = questionList.indexOf(question);
                                if (questionIndex >= 0 && questionIndex < questionpoint.size()) {
                                    String point = questionpoint.get(questionIndex);
                                    questionPointText.setText("Point: " + point);
                                }

                                // Set the user's answer
                                if (questionIndex >= 0 && questionIndex < ExamAnswer.length) {
                                    String userAnswer = ExamAnswer[questionIndex];
                                    YourAnwer.setText("His Answer: " + userAnswer);
                                    if (radioButton1.getText().equals(userAnswer)) {
                                        radioButton1.setSelected(true);
                                    } else if (radioButton2.getText().equals(userAnswer)) {
                                        radioButton2.setSelected(true);
                                    } else if (radioButton3.getText().equals(userAnswer)) {
                                        radioButton3.setSelected(true);
                                    } else if (radioButton4.getText().equals(userAnswer)) {
                                        radioButton4.setSelected(true);
                                    }
                                }

                                // Set the correct answer and select the corresponding radio button
                                if (questionIndex >= 0 && questionIndex < rightanswer.length) {
                                    String correctAnswer = rightanswer[questionIndex];
                                    RightAnswer.setText("Correct Answer: " + correctAnswer); 
                                }
                            }
                            setGraphic(container);
                            // Disable or enable the radio buttons
                            radioButton1.setDisable(true);
                            radioButton2.setDisable(true);
                            radioButton3.setDisable(true);
                            radioButton4.setDisable(true);
                        }
                    }
                };
            }
        };
    }
    /**
     * Handles the action when the back button is clicked.
     * This method closes the current window and opens the Exam Reports view.
     *
     * @param event The ActionEvent triggered by clicking the back button.
     * @throws IOException if an I/O error occurs.
     */
    public void Back(ActionEvent event) throws IOException {
    	questionList.clear();
    	questionpoint.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/Exam Reports.fxml"));
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
