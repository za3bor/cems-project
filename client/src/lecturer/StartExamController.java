package lecturer;

import static client.ChatClient.resultList;


import static client.ClientUI.chat;
import static gui.LogIn.Lecturerinfo;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import logic.Exam;
import logic.Lecturer;
import logic.sqlmessage;
/**
 * Controller class for the StartExam view.
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class StartExamController implements Initializable {
	/**
	 * TableView that displays the list of exams.
	 */
	@FXML
    private TableView<Exam> id_TableView;
	/**
	 * TableColumn that displays the ID of the exam.
	 */
    @FXML
    private TableColumn<Exam, String> id_IDCol;
    /**
     * TableColumn that displays the subject of the exam.
     */
    @FXML
    private TableColumn<Exam, String> id_SubCol;
    /**
     * TableColumn that displays the course of the exam.
     */
    @FXML
    private TableColumn<Exam, String> id_CourseCol;
    /**
     * TableColumn that displays the description for students of the exam.
     */
    @FXML
    private TableColumn<Exam, String> id_DesStuCol;
    /**
     * TableColumn that displays the description for lecturers of the exam.
     */
    @FXML
    private TableColumn<Exam, String> id_DesLecCol;
    /**
     * TableColumn that displays the total point of the exam.
     */
    @FXML
    private TableColumn<Exam, String> id_PointCol;
    /**
     * TableColumn that displays the duration of the exam.
     */
    @FXML
    private TableColumn<Exam, Integer> id_DuraCol;
    /**
     * TableColumn that displays a button for selecting the exam.
     */
    @FXML
    private TableColumn<Exam, Void> id_ButtonCol;
    /**
     * List of exams.
     */
    private List<Exam> ExamList = new ArrayList<>();
    /**
     * The selected exam ID.
     */
    public static String ExamIdSelected;
    
    /**
     * Instance of the selected exam.
     */
    public static Exam examinfo = Exam.getInstance(ExamIdSelected, ExamIdSelected, ExamIdSelected, ExamIdSelected, 0, ExamIdSelected, ExamIdSelected, ExamIdSelected, ExamIdSelected, ExamIdSelected, ExamIdSelected);
    /**
     * Initializes the controller.
     * 
     * @param arg0 The URL to initialize.
     * @param arg1 The ResourceBundle to initialize.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), id_TableView);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        ExamList.clear();
        retrieveExam();
        id_IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        id_SubCol.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        id_CourseCol.setCellValueFactory(new PropertyValueFactory<>("Course"));
        id_DuraCol.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        id_DesLecCol.setCellValueFactory(new PropertyValueFactory<>("DescriptionForLecturers"));
        id_DesStuCol.setCellValueFactory(new PropertyValueFactory<>("DescriptionForStudents"));
        id_PointCol.setCellValueFactory(new PropertyValueFactory<>("TotalPoint"));

        id_IDCol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_SubCol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_CourseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_DesStuCol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_DesLecCol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_PointCol.setCellFactory(TextFieldTableCell.forTableColumn());
        id_DuraCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        id_ButtonCol.setCellFactory(createButtonCellFactory());
        id_TableView.setItems(FXCollections.observableArrayList(ExamList));
    }
    /**
     * Retrieves the list of exams.
     */
    private void retrieveExam() {
    	Lecturer lecturer = Lecturerinfo.get(0);
        String checkquery = "SELECT * FROM exam WHERE Subject = ?";
        Object[] checkparams = { lecturer.getDepartment()};
        sqlmessage checkmessage = new sqlmessage("get", checkquery, checkparams);
        chat.accept(checkmessage);
        for (Map<String, Object> row : resultList) {
            Exam exam = Exam.convertToExam(row);
            ExamList.add(exam);
        }
    }
    /**
     * Starts the exam by navigating to the StartExam2 view.
     * 
     * @throws IOException if an I/O error occurs during navigation.
     */
    public void StartExam2() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/StartExam2.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        // Close the current window
        Stage currentStage = (Stage) id_TableView.getScene().getWindow();
        currentStage.close();
    }
    /**
     * Creates a button cell factory for the "Select" button column.
     * 
     * @return The created button cell factory.
     */
    private Callback<TableColumn<Exam, Void>, TableCell<Exam, Void>> createButtonCellFactory() {
        return new Callback<TableColumn<Exam, Void>, TableCell<Exam, Void>>() {
            @Override
            public TableCell<Exam, Void> call(TableColumn<Exam, Void> param) {
                final TableCell<Exam, Void> cell = new TableCell<Exam, Void>() {
                    private final Button Select = new Button("Select");
                    private final VBox container = new VBox();
                    {
                        container.getChildren().addAll(Select);
                        container.setSpacing(5);
                        Select.getStyleClass().add("table-button");
                        Select.setOnAction(event -> {
                            Exam exam = (Exam) getTableRow().getItem();
                            if (exam != null) {
                            	examinfo=exam;
                            	ExamIdSelected=exam.getID();
                            	try {
									StartExam2();
								} catch (IOException e) {e.printStackTrace();}
                            }
                            // Update the table view
                            retrieveExam();
                        });
                    }
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Exam exam = (Exam) getTableRow().getItem();
                            if (exam != null) {
                            	
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
     * Handles the back button click event.
     * Navigates back to the LecturerMain view.
     * 
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException if an I/O error occurs during navigation.
     */
    @FXML
    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/LecturerMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
