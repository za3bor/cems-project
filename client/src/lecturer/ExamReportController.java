package lecturer;
import static client.ChatClient.isexsit;

import javafx.scene.control.TextArea;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import static client.ChatClient.resultList;
import static client.ClientUI.chat;
import static gui.LogIn.Lecturerinfo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import logic.CheckingCopies;
import logic.Exam;
import logic.StartExam;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import logic.examresult;
import logic.sqlmessage;


/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023 * The ExamReportController class handles the logic and functionality of the exam report view in the lecturer application.
 * It displays exam results and checking copies information in separate tables.
 * The class implements the Initializable interface to initialize the JavaFX components.
 */
public class ExamReportController implements Initializable{
    /**
     * The table view to display exam results.
     */
	@FXML
	private TableView<examresult> id_TableView1;
    /**
     * The table view to display checking copies information.
     */
	@FXML
	private TableView<CheckingCopies> id_TableView2;
    /**
     * The table column for exam result ID in the exam results table.
     */
	@FXML
	private TableColumn<examresult, Integer> tb1_ID;
    /**
     * The table column for exam ID in the exam results table.
     */
	@FXML
	private TableColumn<examresult, String> tb1_examid;
    /**
     * The table column for course name in the exam results table.
     */
	@FXML
	private TableColumn<examresult, String> tb1_course;
    /**
     * The table column for exam result in the exam results table.
     */
	@FXML
	private TableColumn<examresult, String> tb1_examresult;
    /**
     * The table column for exam date in the exam results table.
     */
	@FXML
	private TableColumn<examresult, String> tb1_date;
    /**
     * The table column for exam time in the exam results table.
     */
	@FXML
	private TableColumn<examresult, String> tb1_time;
    /**
     * The table column for student ID in the exam results table.
     */
	@FXML
	private TableColumn<examresult, String> tb1_StudentID;
    /**
     * The table column for exam result status in the exam results table.
     */
	@FXML
	private TableColumn<examresult, String> id_tb1Status;
    /**
     * The table column for buttons in the exam results table.
     */
	@FXML
	private TableColumn<examresult, Void> tb1_button;
    /**
     * The table column for checking copy ID in the checking copies table.
     */
	@FXML
	private TableColumn<CheckingCopies, Integer> tb2_id;
    /**
     * The table column for exam ID in the checking copies table.
     */
	@FXML
	private TableColumn<CheckingCopies, String> tb2_examid;
    /**
     * The table column for the first student in the checking copies table.
     */
	@FXML
	private TableColumn<CheckingCopies, String> tb2_student1;
    /**
     * The table column for the second student in the checking copies table.
     */
	@FXML
	private TableColumn<CheckingCopies, String> tb2_student2;
    /**
     * The table column for the percentage in the checking copies table.
     */
	@FXML
	private TableColumn<CheckingCopies, String> tb2_precent;
	
	
    /**
     * The text field to input exam ID.
     */
	@FXML
	private TextField id_Examid;
    /**
     * The text displaying the exam date.
     */
	@FXML
	private Text id_dateText;
    /**
     * The text displaying the execution type.
     */
	@FXML
	private Text id_ExcutionText;
    /**
     * The text displaying the exam duration.
     */
	@FXML
	private Text id_DurationText;
    /**
     * The text displaying the number of students.
     */
	@FXML
	private Text id_NumofText;
    /**
     * The text displaying the number of students in time.
     */
	@FXML
	private Text id_InTimeText;
    /**
     * The text displaying the number of late students.
     */
	@FXML
	private Text id_LateText;
   
	/**
	 * 
	 */
	public static String Examcode="";
	/**
     * The list of checking copies.
     */
	private List<CheckingCopies> checkingcopies = new ArrayList<>();
	
	
    /**
     * The list of exam results.
     */
	public static List<examresult> Examresult = new ArrayList<>();
    /**
     * The list of start exams.
     */
	private List<StartExam> Startexamlist = new ArrayList<>();
	  /**
     * The start exam number.
     */
	private String startexamNum;

    /**
     * The list of exam questions for the lecturer.
     */
	public static List<Exam> examqeusLecturer = new ArrayList<>();
	   /**
     * The exam result to show for the lecturer.
     */
	public static examresult ShowExaminfoLecturer;
    /**
     * The row index for the lecturer.
     */
    public static int rowIndexLecturer;
	
   
    /**
     * Initializes the ExamReportController. It sets the exam code in the text field,
     * applies fade-in animation to the table views, and retrieves exam data.
     *
     * @param arg0 The URL of the FXML file.
     * @param arg1 The ResourceBundle for localization.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		id_Examid.setText(Examcode);
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), id_TableView1);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(1000), id_TableView2);
        fadeTransition2.setFromValue(0.0);
        fadeTransition2.setToValue(1.0);
        fadeTransition2.play();
        retrieveExam();
		id_TableView1.setItems(FXCollections.observableArrayList(Examresult));
	}
	
	
	/**
	 * Retrieves the exam information based on the exam code and lecturer's ID.
	 * It populates the exam result table with the retrieved data and updates the
	 * exam information text fields.
	 */
	 private void retrieveExam() {
		 if(id_Examid.getText().isEmpty()) {
			 System.out.println("Enter ExamCode");
			 return;
		 }
		 Examcode=id_Examid.getText();
		 id_dateText.setText("");
		 id_ExcutionText.setText("");
		 id_DurationText.setText("");
		 id_NumofText.setText("");
		 id_InTimeText.setText("");
		 id_LateText.setText("");
		 
		 String checkquery5 = "SELECT Startnum FROM startexam where ExamCode = ? AND LecturerName = ?";
	     Object[] checkparams5 = {id_Examid.getText(),Lecturerinfo.get(0).getID()};
	     sqlmessage checkmessage5 = new sqlmessage("check", checkquery5, checkparams5);
	     chat.accept(checkmessage5);
	     if(!isexsit) {
	    	 showAlert("There no exam Availble");
	    	 return;
	     }
	    	 
		 
		 String checkquery = "SELECT Startnum FROM startexam where ExamCode = ? AND LecturerName = ?";
	     Object[] checkparams = {id_Examid.getText(),Lecturerinfo.get(0).getID()};
	     sqlmessage checkmessage = new sqlmessage("get", checkquery, checkparams);
	     List<StartExam> startExam = new ArrayList<>();
	     chat.accept(checkmessage);
	     for (Map<String, Object> row : resultList) {
	    	 StartExam exam = StartExam.convertToStartExam(row);
	    	 startExam.add(exam);
	     }
	     
	     startexamNum=Integer.toString(startExam.get(0).getStartnum());
	     System.out.println(Lecturerinfo.get(0).getFirstName()+" "+startExam.get(0).getStartnum());
	     String checkquery2 = "SELECT * FROM examresult where lecturerID = ? AND startexamNum = ?";
	     Object[] checkparams2 = {Lecturerinfo.get(0).getID(),startExam.get(0).getStartnum()};
	     sqlmessage checkmessage2 = new sqlmessage("get", checkquery2, checkparams2);
	     chat.accept(checkmessage2);
	     for (Map<String, Object> row : resultList) {
	         examresult exam = examresult.convertToExamResult(row);
	         Examresult.add(exam);
	     }
	  }
	 /**
	  * Creates a custom cell factory for the table column to display buttons in each cell.
	  * The buttons are assigned with specific actions for handling various operations related to exam results.
	  *
	  * @return The callback for creating the button cell factory.
	  */
	 private Callback<TableColumn<examresult, Void>, TableCell<examresult, Void>> createButtonCellFactory() {
		    return new Callback<TableColumn<examresult, Void>, TableCell<examresult, Void>>() {
		        @Override
		        public TableCell<examresult, Void> call(TableColumn<examresult, Void> param) {
		            final TableCell<examresult, Void> cell = new TableCell<examresult, Void>() {
		                private final Button showButton = new Button("Copies");
		                private final Button Edit = new Button("Edit grade");
		                private final Button Accept = new Button("Accept");
		                private final Button ShowExam = new Button("Show Exam");
		                private final Button WriteNote = new Button("Write Note");
		                private final VBox container = new VBox();

		                {
		                    container.getChildren().addAll(showButton, Edit, ShowExam,WriteNote,Accept);
		                    container.setSpacing(5);
		                    showButton.getStyleClass().add("table-button");
		                    Edit.getStyleClass().add("table-button");
		                    Accept.getStyleClass().add("table-button");
		                    WriteNote.getStyleClass().add("table-button");
		                    ShowExam.getStyleClass().add("table-button");
		                    showButton.setStyle("-fx-font-size: 18px;");
		                    Edit.setStyle("-fx-font-size: 14px;");
		                    Accept.setStyle("-fx-font-size: 14px;");
		                    ShowExam.setStyle("-fx-font-size: 14px;");
		                    WriteNote.setStyle("-fx-font-size: 14px;");
		                    showButton.setOnAction(event -> {
		                    	FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), showButton);
		                        fadeTransition.setFromValue(0.0);
		                        fadeTransition.setToValue(1.0);
		                        fadeTransition.play();
		                        examresult examResult = (examresult) getTableRow().getItem();
		                        if (examResult != null) {
		                        	
		                        	if(checkcopiesexist(examResult.getID())) {
			                            retrieveCopies(examResult.getID());
			                            ViewCopies();
		                        	}else {
		                        		showAlert("Acording to a copy check,there is no fear of copies");
		                        	}
		                        		
		                            
		                        }
		                    });
		                    Edit.setOnAction(event -> {
		                    	FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), Edit);
		                        fadeTransition.setFromValue(0.0);
		                        fadeTransition.setToValue(1.0);
		                        fadeTransition.play();
		                        examresult examResult = (examresult) getTableRow().getItem();
		                        if (examResult != null) {
		                            Stage currentStage = (Stage) id_TableView1.getScene().getWindow();

		                            openSmallWindow(currentStage, examResult);
		                        }
		                    });
		                    Accept.setOnAction(event -> {
		                    	FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), Accept);
		                        fadeTransition.setFromValue(0.0);
		                        fadeTransition.setToValue(1.0);
		                        fadeTransition.play();
		                        examresult examResult = (examresult) getTableRow().getItem();
		                        if (examResult != null) {
		                            setdata(examResult);
		                            Accept.setVisible(false); 
		                        }
		                    });
		                    
                            ShowExam.setOnAction(event -> {
		                    	FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), Accept);
		                        fadeTransition.setFromValue(0.0);
		                        fadeTransition.setToValue(1.0);
		                        fadeTransition.play();
		                        examresult examResult = (examresult) getTableRow().getItem();
		                        if (examResult != null) {
		                        	rowIndexLecturer = getTableRow().getIndex();
		                        	ShowExaminfoLecturer = examResult;
		                            preapreExamShow(); 
		                            try {
										Show(event);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
		                        }
		                    });
                            WriteNote.setOnAction(event -> {
		                    	FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), Edit);
		                        fadeTransition.setFromValue(0.0);
		                        fadeTransition.setToValue(1.0);
		                        fadeTransition.play();
		                        examresult examResult = (examresult) getTableRow().getItem();
		                        if (examResult != null) {
		                            Stage currentStage = (Stage) id_TableView1.getScene().getWindow();

		                            writenotewindow(currentStage, examResult);
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
		                            Edit.getStyleClass().add("table-button-add");
		                            
		                            // Check the condition and hide the "Accept" button if necessary
		                            if (examResult.getStatus().equals("1")) {
		                                Accept.setVisible(false);
		                            } else {
		                                Accept.setVisible(true);
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
	  * Checks if copies exist for the given exam result ID.
	  *
	  * @param Examid The ID of the exam result.
	  * @return True if copies exist for the exam result, false otherwise.
	  */
	 private boolean checkcopiesexist(Integer Examid) {
		 String checkquery = "SELECT * FROM checkingcopies where ExamResultID = ?";
	     Object[] checkparams = {Examid};
	     sqlmessage checkmessage = new sqlmessage("check", checkquery, checkparams);
	     chat.accept(checkmessage);
	     return isexsit;
	 }
	 /**
	  * Retrieves the copies for the given exam ID and populates the table view with the retrieved data.
	  *
	  * @param Examid The ID of the exam.
	  */
	 private void retrieveCopies(Integer Examid) {
		 id_TableView2.getItems().clear();
		 checkingcopies.clear();
		 String checkquery = "SELECT * FROM checkingcopies where ExamResultID = ?";
	        Object[] checkparams = {Examid};
	        sqlmessage checkmessage = new sqlmessage("get", checkquery, checkparams);
	        chat.accept(checkmessage);
	        for (Map<String, Object> row : resultList) {
	        	CheckingCopies exam = CheckingCopies.convertToCheckingCopies(row);
	        	if(Double.parseDouble(exam.getSimilarPrecent())>=30&&!(exam.getStudent1ID().equals(exam.getStudent2ID())))
	        		checkingcopies.add(exam);
	        }
	    }
	 /**
	  * Sets up the table columns and cell factories for viewing the copies in the table view.
	  * Binds the data of the copies to the table view.
	  */
	 public void ViewCopies() {
		tb2_id.setCellValueFactory(new PropertyValueFactory<>("autoincrement"));
		tb2_examid.setCellValueFactory(new PropertyValueFactory<>("ExamID"));
		tb2_student1.setCellValueFactory(new PropertyValueFactory<>("Student1ID"));
		tb2_student2.setCellValueFactory(new PropertyValueFactory<>("Student2ID"));
		tb2_precent.setCellValueFactory(new PropertyValueFactory<>("SimilarPrecent"));
		tb1_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tb2_examid.setCellFactory(TextFieldTableCell.forTableColumn());
		tb1_course.setCellFactory(TextFieldTableCell.forTableColumn());
		tb1_examresult.setCellFactory(TextFieldTableCell.forTableColumn());
		tb1_date.setCellFactory(TextFieldTableCell.forTableColumn());
		tb1_time.setCellFactory(TextFieldTableCell.forTableColumn());
		tb1_StudentID.setCellFactory(TextFieldTableCell.forTableColumn());
		tb2_precent.setCellFactory(column -> {
		    return new TableCell<CheckingCopies, String>() {
		        @Override
		        protected void updateItem(String item, boolean empty) {
		            super.updateItem(item, empty);
		            if (empty || item == null) {
		                setText(null);
		            } else {
		                double percentage = Double.parseDouble(item);
		                String formattedPercentage = String.format("%.2f%%", percentage);
		                setText(formattedPercentage);
		            }
		        }
		    };
		});
		id_TableView2.setItems(FXCollections.observableArrayList(checkingcopies));
	}
	 /**
	  * Performs the selection of an exam based on the provided event. Retrieves and displays the exam results
	  * and information for the selected exam code.
	  *
	  * @param event The event that triggered the selection.
	  * @throws IOException if an I/O error occurs.
	  */
	 public void Select(ActionEvent event) throws IOException {
		 Examcode= id_Examid.getText();
		 Examresult.clear();
		 id_TableView1.getItems().clear();
		 id_TableView2.getItems().clear();
		 checkingcopies.clear();
		 tb1_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		 tb1_examid.setCellValueFactory(new PropertyValueFactory<>("ExamID"));
		 tb1_course.setCellValueFactory(new PropertyValueFactory<>("Course"));
		 tb1_examresult.setCellValueFactory(new PropertyValueFactory<>("ExamResult"));
		 tb1_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		 tb1_time.setCellValueFactory(new PropertyValueFactory<>("DurationTake"));
		 tb1_StudentID.setCellValueFactory(new PropertyValueFactory<>("StudentID"));		
		 id_tb1Status.setCellValueFactory(new PropertyValueFactory<>("FormSubmission"));	
		 tb1_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		 tb1_examid.setCellFactory(TextFieldTableCell.forTableColumn());
		 tb1_course.setCellFactory(TextFieldTableCell.forTableColumn());
		 id_tb1Status.setCellFactory(TextFieldTableCell.forTableColumn());
		 tb1_examresult.setCellFactory(TextFieldTableCell.forTableColumn());
		 tb1_date.setCellFactory(TextFieldTableCell.forTableColumn());
		 tb1_time.setCellFactory(TextFieldTableCell.forTableColumn());
		 tb1_StudentID.setCellFactory(TextFieldTableCell.forTableColumn());
		 tb1_button.setCellFactory(createButtonCellFactory());
		 retrieveExam();
		 id_TableView1.setItems(FXCollections.observableArrayList(Examresult));
		 ShowInfoOfTheExam(Examcode);
	 }
	 /**
	  * Retrieves and displays information about the selected exam.
	  *
	  * @param examcode The code of the selected exam.
	  */
	 private void ShowInfoOfTheExam(String examcode) {
		 Startexamlist.clear();
		 String checkquery2 = "SELECT * FROM startexam where ExamCode = ?";
	     Object[] checkparams2 = {examcode};
	     sqlmessage checkmessage2 = new sqlmessage("get", checkquery2, checkparams2);
	     chat.accept(checkmessage2);
	     for (Map<String, Object> row : resultList) {
	    	 StartExam exam = StartExam.convertToStartExam(row);
	    	 Startexamlist.add(exam);
	     }
	     id_dateText.setText(Startexamlist.get(0).getDate().toString());
	     id_ExcutionText.setText(Startexamlist.get(0).getStartTime().toString());
	     id_DurationText.setText(Integer.toString(Startexamlist.get(0).getDuration()));
	     
	     String checkquery = "SELECT FormSubmission FROM examresult where startexamNum = ?";
	     Object[] checkparams = {startexamNum};
	     sqlmessage checkmessage = new sqlmessage("get", checkquery, checkparams);
	     List<examresult> examR = new ArrayList<>();
	     chat.accept(checkmessage);
	     for (Map<String, Object> row : resultList) {
	    	 examresult result = examresult.convertToExamResult(row);
	    	 examR.add(result);
	     }
	     
	     id_NumofText.setText(Integer.toString(examR.size()));
	     int intime=0;
	     for (int i = 0; i < examR.size(); i++) {
			if(examR.get(i).getFormSubmission().equals("in Time"))
				intime++;
		}
	     id_InTimeText.setText(Integer.toString(intime));
	     id_LateText.setText(Integer.toString(examR.size()-intime));
	}
	 /**
	  * Displays an information alert dialog with the provided message.
	  *
	  * @param message The message to be displayed in the alert dialog.
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
	 * Opens a small window to display and edit exam result information for a specific exam result.
	 *
	 * @param primaryStage The primary stage of the application.
	 * @param examresult   The exam result object containing the information to be displayed and edited.
	 */
	 private void openSmallWindow(Stage primaryStage, examresult examresult) {
		    // Create a new stage
		    Stage smallWindow = new Stage();
		    smallWindow.setTitle("Small Window");

		    // Create UI components for the small window
		    Label gradeLabel = new Label("Grade:");
		    Label studentIdLabel = new Label("StudentID:");
		    Label newgrade = new Label("New grade:");
		    Label Note = new Label("Note:");
		    Text newGradeTextField = new Text(examresult.getExamResult()); // Set the initial value using Grade
		    Text StudentIDTextField = new Text(examresult.getStudentID()); // Set the initial value using StudentID
		    TextField NewgradText = new TextField();
		    TextArea NoteText = new TextArea();
		    Button saveButton = new Button("Save");
		    Text Error = new Text();
		    gradeLabel.setStyle("-fx-text-fill: blue;");
		    studentIdLabel.setStyle("-fx-text-fill: blue;");
		    newgrade.setStyle("-fx-text-fill: blue;");
		    Note.setStyle("-fx-text-fill: blue;");
		    saveButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
		    // Set the layout for the small window
		    VBox layout = new VBox(15);
		    layout.getChildren().addAll(gradeLabel, newGradeTextField, studentIdLabel, StudentIDTextField,newgrade,
		    		NewgradText,Note,NoteText,saveButton,Error);
		    layout.setAlignment(Pos.CENTER);
		    layout.setPadding(new Insets(15));
		    // Set the scene for the small window
		    Scene scene = new Scene(layout);
		    FadeTransition fadeInTransition = new FadeTransition(Duration.millis(1000), layout);
		    fadeInTransition.setFromValue(0.0);
		    fadeInTransition.setToValue(1.0);
		    fadeInTransition.play();

		    // Show the small window
		    smallWindow.initOwner(primaryStage);
		    smallWindow.initModality(Modality.APPLICATION_MODAL);
		    smallWindow.setScene(scene);
		    smallWindow.show();
		    // Set the width and height of the stage
		   // smallWindow.setWidth(400); // Set the desired width
		    //smallWindow.setHeight(500); // Set the desired height
		    saveButton.setOnAction(event -> {
		    	if(NewgradText.getText().isEmpty()||NoteText.getText().isEmpty()) {
		    		Error.setText("Please enter a valid grade,note");
		    		return;
		    	}
		    	String checkquery3 = "UPDATE examresult SET ExamResult = ?, note = ? WHERE ID = ?";
			    Object[] checkparams3 = {NewgradText.getText(),NoteText.getText(),examresult.getID()};
			    sqlmessage checkmessage3 = new sqlmessage("save", checkquery3, checkparams3);
			    chat.accept(checkmessage3);
			    try {
					Select(null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        smallWindow.close();
		    });
		}
	 /**
	  * Opens a small window to write a note for a specific exam result.
	  *
	  * @param primaryStage The primary stage of the application.
	  * @param examresult   The exam result object for which the note is being written.
	  */
	 private void writenotewindow(Stage primaryStage, examresult examresult) {
		    // Create a new stage
		    Stage smallWindow = new Stage();
		    smallWindow.setTitle("Small Window");

		    // Create UI components for the small window
		    Label gradeLabel = new Label("Grade:");
		    Label studentIdLabel = new Label("StudentID:");
		    Label Note = new Label("Note:");
		    Text newGradeTextField = new Text(examresult.getExamResult()); // Set the initial value using Grade
		    Text StudentIDTextField = new Text(examresult.getStudentID()); // Set the initial value using StudentID
		    TextArea NoteText = new TextArea();
		    Button Confirm = new Button("Confirm");
		    Text Error = new Text();
		    gradeLabel.setStyle("-fx-text-fill: blue;");
		    studentIdLabel.setStyle("-fx-text-fill: blue;");
		    Note.setStyle("-fx-text-fill: blue;");
		    Confirm.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
		    // Set the layout for the small window
		    VBox layout = new VBox(15);
		    layout.getChildren().addAll(gradeLabel, newGradeTextField, studentIdLabel, StudentIDTextField,
		    		Note,NoteText,Confirm,Error);
		    layout.setAlignment(Pos.CENTER);
		    layout.setPadding(new Insets(15));
		    // Set the scene for the small window
		    Scene scene = new Scene(layout);
		    FadeTransition fadeInTransition = new FadeTransition(Duration.millis(1000), layout);
		    fadeInTransition.setFromValue(0.0);
		    fadeInTransition.setToValue(1.0);
		    fadeInTransition.play();

		    // Show the small window
		    smallWindow.initOwner(primaryStage);
		    smallWindow.initModality(Modality.APPLICATION_MODAL);
		    smallWindow.setScene(scene);
		    smallWindow.show();
		    // Set the width and height of the stage
		   // smallWindow.setWidth(400); // Set the desired width
		    //smallWindow.setHeight(500); // Set the desired height
		    Confirm.setOnAction(event -> {
		    	if(NoteText.getText().isEmpty()) {
		    		Error.setText("Please enter a valid note");
		    		return;
		    	}
		    	String checkquery3 = "UPDATE examresult SET noteforstudent = ? WHERE ID = ?";
			    Object[] checkparams3 = {NoteText.getText(),examresult.getID()};
			    sqlmessage checkmessage3 = new sqlmessage("save", checkquery3, checkparams3);
			    chat.accept(checkmessage3);
			    System.out.println("Exam Changed");
			    try {
					Select(null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        smallWindow.close();
		    });
		}
	 /**
	  * Sets the status of an exam result to "1" and updates the temporary flag in the student_grad table.
	  *
	  * @param examResult The exam result object for which the status and temporary flag are being updated.
	  */
	 private void setdata(examresult examResult) {
		 String updateQuery = "UPDATE examresult SET status = ? WHERE ID = ?";
		 Object[] updateParams = {"1", examResult.getID()};
		 sqlmessage updateMessage = new sqlmessage("edit", updateQuery, updateParams);
		 chat.accept(updateMessage);
		 String query2 = "UPDATE student_grad SET tmp = ? WHERE StudentId = ?";
     	 Object[] params2 = {1,examResult.getStudentID()};
	     sqlmessage message2 = new sqlmessage("edit", query2, params2);
	     chat.accept(message2); 
		 showAlert("the grade have been send to student");
		 
	 }
	 /**
	  * Returns to the main lecturer view.
	  *
	  * @param event The action event triggered by the "Back" button.
	  * @throws IOException If an error occurs while loading the lecturer main view.
	  */
	    public void back(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/LecturerMain.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
	    }
	    /**
	     * Shows the exam report window and closes the current window.
	     *
	     * @param event The action event triggered by the "Show" button.
	     * @throws IOException If an error occurs while loading the exam report window.
	     */
	    public void Show(ActionEvent event) throws IOException {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lecturer/ExamReprot2.fxml"));
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
	     * Prepares the exam information to be shown.
	     * Retrieves the exam details based on the exam ID stored in the ShowExaminfoLecturer object.
	     * Populates the examqeusLecturer list with the retrieved exam details.
	     */
	    public void preapreExamShow() {
	    	String checkquery1 = "SELECT * FROM exam WHERE ID = ?";
	        Object[] checkparams1 = { ShowExaminfoLecturer.getExamID() };
	        sqlmessage checkmessage1 = new sqlmessage("get", checkquery1, checkparams1);
	        chat.accept(checkmessage1);
	        for (Map<String, Object> row : resultList) {
	            Exam exm = Exam.convertToExam(row);
	            examqeusLecturer.add(exm);
	        } 
	    }
}
