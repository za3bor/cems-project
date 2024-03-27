package lecturer;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.Exam;
import logic.sqlmessage;

import static client.ChatClient.resultList;
import static client.ClientUI.chat;
import static gui.LogIn.Lecturerinfo;

import java.io.IOException;
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
 * Represents a controller class for managing the exam bank view.
 * This class handles the user interface and logic for displaying the
 * list of exams available for a specific lecturer. It provides methods
 * for initializing the controller, loading and displaying the exams,
 * navigating to the exam details view, and navigating back to the
 * exam question bank view.
 */
public class ExamBankController {
    /**
     * TableView for displaying the list of exams.
     */
	@FXML
    private TableView<Exam> examTable;

    /**
     * TableColumn for displaying the ID of the exam.
     */
    @FXML
    private TableColumn<Exam, String> idColumn;

    /**
     * TableColumn for displaying the lecturer of the exam.
     */
    @FXML
    private TableColumn<Exam, String> LecturerColumn;

    /**
     * TableColumn for displaying the course of the exam.
     */
    @FXML
    private TableColumn<Exam, String> courseColumn;

    /**
     * TableColumn for displaying the duration of the exam.
     */
    @FXML
    private TableColumn<Exam, String> durationColumn;

    /**
     * TableColumn for displaying the "Show" button.
     */
    @FXML
    private TableColumn<Exam, Void> show_column;
    
    /**
     * The currently selected exam.
     */
    public static Exam exam;
    
    /**
     * Initializes the controller and sets up the table columns.
     * This method is automatically called after the FXML file has been loaded.
     * It sets up the table columns with appropriate cell value factories
     * and retrieves the exams from the server to populate the table view.
     */
    public void initialize() {
        // Set up the table columns
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), examTable);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        LecturerColumn.setCellValueFactory(new PropertyValueFactory<>("Lecturer"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("Course"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("Duration"));

        // Define the cell factory for the showColumn to display a button
        show_column.setCellFactory(column -> new ButtonCell());

        String query = "SELECT * FROM exam WHERE Subject = ? AND Lecturer = ?";
        Object[] pram = {Lecturerinfo.get(0).getDepartment(),Lecturerinfo.get(0).getFirstName()};
        sqlmessage message = new sqlmessage("get", query, pram);
        chat.accept(message);
        ObservableList<Exam> examList = FXCollections.observableArrayList();

        for (Map<String, Object> row : resultList) {
            Exam exam = Exam.convertToExam(row);
            examList.add(exam);
        }

        examTable.setItems(examList);
    }
    /**
     * Navigates back to the exam question bank view.
     * This method loads the exam question bank FXML file and displays
     * the exam question bank screen in the current stage.
     * 
     * @param event the action event that triggered the navigation
     * @throws IOException if an I/O error occurs when loading the exam question bank screen
     */
	 public void back(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/Exam Question Bank.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
	 }
	    /**
	     * Represents a custom table cell that displays a "Show" button for each row in the exam table.
	     * This button allows navigating to the exam details view when clicked.
	     */
    private class ButtonCell extends TableCell<Exam, Void> {
        /**
         * Button used to display exam details.
         */
        private final Button showButton;
        /**
         * Constructs a new instance of the button cell.
         * It initializes the button with the label "Show" and
         * sets the event handler to navigate to the exam details view.
         */
        public ButtonCell() {
            showButton = new Button("Show");
            showButton.setOnAction(event -> {
                Exam exam1 = (Exam) getTableRow().getItem();
                if (exam1 != null) {
                	exam=exam1;
                	FXMLLoader loader = new FXMLLoader(getClass().getResource("Exam Bank 2.fxml"));
                    Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();                    stage.setScene(scene);
                    stage.show();
                }
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setGraphic(showButton);
            } else {
                setGraphic(null);
            }
        }
    }
}
