package lecturer;
import logic.ExamPoint;
import java.util.HashMap;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import logic.Question;
import logic.sqlmessage;
import static client.ClientUI.chat;
import static client.ChatClient.resultList;
import static lecturer.CreatNewExamController.Examinfo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * Controller class for creating a new exam (part 2).
 */
public class CreatNewExam2Controller implements Initializable {
	/**
	 * Represents the Text control for displaying the total point of the exam.
	 */
	@FXML
    private Text id_TotalPoint;
	/**
	 * Represents the TableView control for displaying the list of available questions.
	 */
    @FXML
    private TableView<Question> id_TableView;
    /**
     * Represents the TableColumn control for displaying the ID of each question in the TableView.
     */
    @FXML
    private TableColumn<Question, String> id_IDCol;
    /**
     * Represents the TableColumn control for displaying the subject of each question in the TableView.
     */
    @FXML
    private TableColumn<Question, String> id_SubCol;
    /**
     * Represents the TableColumn control for displaying the course of each question in the TableView.
     */
    @FXML
    private TableColumn<Question, String> id_CourCol;
    /**
     * Represents the TableColumn control for displaying the text of each question in the TableView.
     */
    @FXML
    private TableColumn<Question, String> id_QuesCol;
    /**
     * Represents the TableColumn control for displaying the lecturer ID of each question in the TableView.
     */
    @FXML
    private TableColumn<Question, String> id_LecCol;
    /**
     * Represents the TableColumn control for displaying action buttons (Add, Delete, Edit) for each question in the TableView.
     */

    @FXML
    private TableColumn<Question, Void> id_ActionCol;
    /**
     * Represents the TableView control for displaying the list of selected questions with their points.
     */
    @FXML
    private TableView<ExamPoint> id_tb2;
    /**
     * Represents the TableColumn control for displaying the question number of each selected question in the TableView.
     */
    @FXML
    private TableColumn<ExamPoint, String> id_tb2questionnum;
    /**
     * Represents the TableColumn control for displaying the point assigned to each selected question in the TableView.
     */

    @FXML
    private TableColumn<ExamPoint, Integer> id_tb2point;
    /**
     * Represents the list of available questions.
     */
    private List<Question> questionList = new ArrayList<>();
    /**
     * Represents a mapping of selected questions by their unique identifier.
     */
    public static HashMap<String, Question> SelectedQuestion = new HashMap<>();
    /**
     * Represents a mapping of points assigned to each selected question by their unique identifier.
     */
    public static HashMap<String, Integer> SelectedQuestionPoint = new HashMap<>();
    /**
     * Represents the total point of the exam.
     */
    public static int point = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), id_TableView);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
		FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(1000), id_tb2);
        fadeTransition1.setFromValue(0.0);
        fadeTransition1.setToValue(1.0);
        fadeTransition1.play();
    	retrievepoint();
    	id_TotalPoint.setText(Integer.toString(point));
    	questionList.clear();
        retrieveQuestions();
        id_IDCol.setCellValueFactory(new PropertyValueFactory<>("questionNum"));
        id_SubCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        id_CourCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        id_QuesCol.setCellValueFactory(new PropertyValueFactory<>("questionText"));
        id_LecCol.setCellValueFactory(new PropertyValueFactory<>("lecturerID"));
        id_IDCol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_SubCol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_CourCol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_QuesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_LecCol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_ActionCol.setCellFactory(createButtonCellFactory());
        id_TableView.setItems(FXCollections.observableArrayList(questionList));
    }
    /**
     * Retrieves the list of questions.
     */
    private void retrieveQuestions() {
        String checkquery = "SELECT * FROM question WHERE Subject = ?";
        Object[] checkparams = { Examinfo.getSubject() };
        sqlmessage checkmessage = new sqlmessage("get", checkquery, checkparams);
        chat.accept(checkmessage);
        for (Map<String, Object> row : resultList) {
            Question question = Question.convertToQuestion(row);
            questionList.add(question);
        }	
    }
    /**
     * Creates a cell factory for the action buttons in the table.
     *
     * @return The cell factory for the action buttons.
     */
    private Callback<TableColumn<Question, Void>, TableCell<Question, Void>> createButtonCellFactory() {
        return new Callback<TableColumn<Question, Void>, TableCell<Question, Void>>() {
            @Override
            public TableCell<Question, Void> call(TableColumn<Question, Void> param) {
                final TableCell<Question, Void> cell = new TableCell<Question, Void>() {
                    private final Button add = new Button("Add");
                    private final Button delete = new Button("Delete");
                    private final Button edit = new Button("Edit");
                    private final VBox container = new VBox();
                    private final Label pointLabel = new Label("Point");
                    private final TextField textField = new TextField();
                    private final Text note = new Text();
                    //private final Text Pointforthisquestion = new Text();
                    {
                        container.getChildren().addAll(add, delete, edit, pointLabel, textField, note);
                        container.setSpacing(5);
                        add.getStyleClass().add("table-button");
                        delete.getStyleClass().add("table-button");
                        edit.getStyleClass().add("table-button");
                        add.setOnAction(event -> {
                            Question question = (Question) getTableRow().getItem();
                            if (question != null) {
                                if (textField.getText().isEmpty()) {
                                    note.setText("Put the point");
                                } else {
                                	
                                    String textFieldValue = textField.getText();
                                    if (textFieldValue != null && !textFieldValue.isEmpty() && (point + Integer.parseInt(textFieldValue)) > 0&&
                                    		point + Integer.parseInt(textFieldValue) <= 100) {
                                        point += Integer.parseInt(textFieldValue);
                                        SelectedQuestionPoint.put(question.getQuestionNum(), Integer.parseInt(textField.getText()));
                                        SelectedQuestion.put(question.getQuestionNum(), question);
                                        id_TotalPoint.setText(Integer.toString(point));
                                        note.setText("");
                                        retrievepoint();
                                        // Update the visibility of buttons     
                                        add.setVisible(false);
                                        delete.setVisible(true);
                                        edit.setVisible(true);
                                    } else {
                                        note.setText("Uncorrect Point");
                                    }
                                }
                            }
                            textField.clear();
                        });
                        delete.setOnAction(event -> {
                            Question question = (Question) getTableRow().getItem();
                            if (question != null) {
                                point -= SelectedQuestionPoint.get(question.getQuestionNum());
                                SelectedQuestion.remove(question.getQuestionNum());
                                SelectedQuestionPoint.remove(question.getQuestionNum());
                                id_TotalPoint.setText(Integer.toString(point));
                                add.setVisible(true);
                               // Pointforthisquestion.setText("");
                                //Pointforthisquestion.setVisible(false);
                                delete.setVisible(false);
                                edit.setVisible(false);
                                pointLabel.setVisible(true);
                                textField.setVisible(true);
                                note.setVisible(true);
                                // Perform the desired delete action with the selected question
                            }
                            textField.clear();
                            note.setText("");
                            // Update the table view
                            retrievepoint();
                           // retrieveQuestions();
                        });
                        edit.setOnAction(event -> {
                            Question question = (Question) getTableRow().getItem();
                            if (question != null) {
                                if (textField.getText().isEmpty()||Integer.parseInt(textField.getText())<=0) {
                                    note.setText("Uncorrect Point");
                                    return;
                                }
                                else if((point - SelectedQuestionPoint.get(question.getQuestionNum())) + Integer.parseInt(textField.getText()) >0&&
                                		(point - SelectedQuestionPoint.get(question.getQuestionNum())) + Integer.parseInt(textField.getText()) <=100) {
                                    point -= SelectedQuestionPoint.get(question.getQuestionNum());
                                    point += Integer.parseInt(textField.getText());
                                    SelectedQuestionPoint.replace(question.getQuestionNum(), Integer.parseInt(textField.getText()));
                                    note.setText("");
                                    id_TotalPoint.setText(Integer.toString(point));
                                    retrievepoint();
                                    //Pointforthisquestion.setText("Your have set\n"+Integer.toString(SelectedQuestionPoint.get(question.getQuestionNum()))+" point");
                                }
                                else {
                                	note.setText("Point is invalid");
                                	return;
                                }
                                
                            }
                            textField.clear();
                            // Update the table view
                           // retrieveQuestions();
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Question question = (Question) getTableRow().getItem();
                            if (question != null) {
                                // Set the initial visibility for all buttons and elements
                                add.setVisible(true);
                                delete.setVisible(false);
                                edit.setVisible(false);
                                pointLabel.setVisible(true);
                                textField.setVisible(true);
                                note.setVisible(true);
                                add.getStyleClass().add("table-button-add");
                                delete.getStyleClass().add("table-button-delete");
                                edit.getStyleClass().add("table-button-edit");

                                if (SelectedQuestion.containsKey(question.getQuestionNum())) {
                                    // SelectedQuestion contains the question number, so hide the add button
                                    add.setVisible(false);
                                    delete.setVisible(true);
                                    edit.setVisible(true);
                                }
                            }
                            setGraphic(container);
                        }
                    }
                };
                return cell;
            }
        };
    }
    /**
     * Creates a new exam.
     *
     * @param event The event that triggered the create action.
     * @throws IOException if an I/O error occurs.
     */
    public void Create(ActionEvent event) throws IOException {
    	if(SelectedQuestion.size()==0){
    		showAlert("Select Question");
       	 	return;
    	}
    	else if(point<100 || point > 100) {
    		showAlert("Total marks must be 100");
       	 	return;
    	}
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/CreateNewExam3.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        // Close the current stage
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
     * Navigates back to the previous page.
     *
     * @param event The event that triggered the back action.
     * @throws IOException if an I/O error occurs.
     */
    public void back(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/Create New Exam.fxml"));
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
     * Retrieves and displays the selected point for each question.
     */
    public void retrievepoint() {
        id_tb2.getItems().clear(); // Clear any existing items
        // Iterate over the entries in the HashMap
        for (HashMap.Entry<String, Integer> entry : SelectedQuestionPoint.entrySet()) {
            String questionNum = entry.getKey();
            Integer point = entry.getValue();

            // Create a new DataEntry instance with the values from the HashMap entry
            ExamPoint dataEntry = new ExamPoint(questionNum, point);

            // Add the DataEntry instance to the TableView
            id_tb2.getItems().add(dataEntry);
        }

        // Set the cell value factories for each column
        id_tb2questionnum.setCellValueFactory(cellData -> cellData.getValue().questionNumProperty());
        id_tb2point.setCellValueFactory(cellData -> cellData.getValue().pointProperty());
    }
}
