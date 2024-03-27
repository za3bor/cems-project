package student;

import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import static client.ChatClient.resultList;
import static client.ClientUI.chat;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import logic.Exam;
import logic.examresult;
import logic.sqlmessage;
import static gui.LogIn.Studentinfo;

/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * Controller class for displaying exam results.
 */
public class ExamResultController implements Initializable {
    /**
     * The TableView that displays the exam results.
     */
	@FXML
    private TableView<examresult> id_TableView;
    /**
     * The TableColumn that displays the exam number.
     */
    @FXML
    private TableColumn<examresult, Integer> id_examnumCol;
    /**
     * The TableColumn that displays the exam ID.
     */
    @FXML
    private TableColumn<examresult, String> id_examIDcol;
    /**
     * The TableColumn that displays the exam result.
     */
    @FXML
    private TableColumn<examresult, String> id_examResultcol;
    /**
     * The TableColumn that displays the subject of the exam.
     */
    @FXML
    private TableColumn<examresult, String> id_subjectcol;
    /**
     * The TableColumn that displays the course of the exam.
     */
    @FXML
    private TableColumn<examresult, String> id_coursecol;
    /**
     * The TableColumn that displays the lecturer of the exam.
     */
    @FXML
    private TableColumn<examresult, String> id_lectcol;
    /**
     * The TableColumn that displays the date of the exam.
     */
    @FXML
    private TableColumn<examresult, String> id_datecol;
    /**
     * The TableColumn that displays the time of the exam.
     */
    @FXML
    private TableColumn<examresult, String> id_timecol;
    /**
     * The TableColumn that contains buttons for each row in the TableView.
     */
    @FXML
    private TableColumn<examresult, Void> id_Button;
    /**
     * The Text field that displays the total number of exams.
     */
    @FXML
    private Text id_totalexam;
    /**
     * The list of Exam objects representing detailed exam information.
     */
    public static List<Exam> examqeus = new ArrayList<>();
    /**
     * The exam result object to be shown in the detailed exam view.
     */
    public static examresult ShowExaminfo;
    /**
     * The list of exam results.
     */
    public static List<examresult> Examlist = new ArrayList<>();
    /**
     * The index of the selected row in the TableView.
     */
    public static int rowIndex;
    /**
     * Initializes the controller class.
     *
     * @param arg0     The URL location of the FXML file.
     * @param arg1     The ResourceBundle for the FXML file.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), id_TableView);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    	Examlist.clear();
    	examqeus.clear();
        id_examnumCol.setCellValueFactory(new PropertyValueFactory<>("iD"));
        id_examIDcol.setCellValueFactory(new PropertyValueFactory<>("examID"));
        id_examResultcol.setCellValueFactory(new PropertyValueFactory<>("examResult"));
        id_subjectcol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        id_coursecol.setCellValueFactory(new PropertyValueFactory<>("course"));
        id_lectcol.setCellValueFactory(new PropertyValueFactory<>("lecturerID"));
        id_datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        id_timecol.setCellValueFactory(new PropertyValueFactory<>("DurationTake"));
        id_examResultcol.setCellValueFactory(cellData -> {
            examresult result = cellData.getValue();
            String examResult = result.getExamResult();
            String note = result.getNote();
            if(note.isEmpty())
            	return new SimpleStringProperty(examResult);
            else {
            	String displayValue = "The grade changed\n" + examResult + " - " + note;
                return new SimpleStringProperty(displayValue);
            }
        });
        id_timecol.setCellValueFactory(cellData -> {
            examresult result = cellData.getValue();
            String duration = result.getDurationTake();
            String form = result.getFormSubmission();
            String displayValue = duration + " - " + form;
            return new SimpleStringProperty(displayValue);
        });
        id_examnumCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        id_examIDcol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_examResultcol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_subjectcol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_coursecol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_lectcol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_datecol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_timecol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_Button.setCellFactory(createButtonCellFactory());
        retrieveExam();
        id_TableView.setItems(FXCollections.observableArrayList(Examlist));
    }
    /**
     * Retrieves the exam results from the database and populates the Examlist.
     */
    private void retrieveExam() {
        String checkquery = "SELECT * FROM examresult WHERE StudentID = ? AND status = ?";
        Object[] checkparams = { Studentinfo.get(0).getID(),"1" };
        sqlmessage checkmessage = new sqlmessage("get", checkquery, checkparams);
        chat.accept(checkmessage);
        for (Map<String, Object> row : resultList) {
            examresult exam = examresult.convertToExamResult(row);
            Examlist.add(exam);
        }
    }
    /**
     * Creates a custom cell factory for the button column in the TableView.
     *
     * @return The TableCell representing the button column.
     */
    private Callback<TableColumn<examresult, Void>, TableCell<examresult, Void>> createButtonCellFactory() {
        return new Callback<TableColumn<examresult, Void>, TableCell<examresult, Void>>() {
            @Override
            public TableCell<examresult, Void> call(TableColumn<examresult, Void> param) {
                final TableCell<examresult, Void> cell = new TableCell<examresult, Void>() {
                    private final Button showButton = new Button("Show");
                    private final VBox container = new VBox();

                    {
                        container.getChildren().addAll(showButton);
                        container.setSpacing(5);
                        showButton.getStyleClass().add("table-button");
                        showButton.setOnAction(event -> {
                            examresult examResult = (examresult) getTableRow().getItem();
                            if (examResult != null) {
                            	rowIndex = getTableRow().getIndex(); // Get the row index    
                                ShowExaminfo = examResult;
                                preapreExamShow();
                                try {
                                    Show(event);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            examresult examResult = (examresult) getTableRow().getItem();
                            if (examResult != null) {
                                // Set the initial visibility for all buttons and elements
                                showButton.getStyleClass().add("table-button-add");
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
     * Opens a new window to display detailed exam information.
     *
     * @param event    The ActionEvent triggered by clicking the button.
     * @throws IOException   If an I/O error occurs when loading the new window.
     */
    public void Show(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/student/ExamResult2.fxml"));
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
     * Returns to the previous window.
     *
     * @param event    The ActionEvent triggered by clicking the button.
     * @throws IOException   If an I/O error occurs when loading the previous window.
     */
    public void Back(ActionEvent event) throws IOException {
    	Examlist.clear();
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
     * Prepares the necessary data for displaying detailed exam information.
     */
    public void preapreExamShow() {
    	String checkquery1 = "SELECT * FROM exam WHERE ID = ?";
        Object[] checkparams1 = { ShowExaminfo.getExamID() };
        sqlmessage checkmessage1 = new sqlmessage("get", checkquery1, checkparams1);
        chat.accept(checkmessage1);
        for (Map<String, Object> row : resultList) {
            Exam exm = Exam.convertToExam(row);
            examqeus.add(exm);
        } 
    }
}
