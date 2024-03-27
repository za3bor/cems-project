package student;

import javafx.event.ActionEvent;
import static gui.LogIn.Studentinfo;
import static student.StartOnlineExamController.startexam;
import logic.examresult;
import logic.sms;
import logic.CurrentExam;
import logic.CurrentExamSelectedQuestionStudent;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static student.StartOnlineExamController.exam;
import static client.ChatClient.isexsit;
import static client.ChatClient.resultList;
import static client.ChatClient.sqldone;
import static client.ClientUI.chat;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import logic.Question;
import logic.sqlmessage;
import logic.Exam;
import static client.ChatClient.isexsit;
/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * Controller class for the StartOnlineExam2 view.
 */
public class StartOnlineExam2Controller implements Initializable {
	/**
	 * The TableView component for displaying the list of questions.
	 */
	@FXML
    private TableView<Question> id_TableView;
	/**
	 * The TableColumn for displaying the ID of the question.
	 */
    @FXML
    private TableColumn<Question, String> id_IDCol;
    /**
     * The TableColumn for displaying the question text.
     */
    @FXML
    private TableColumn<Question, String> id_QuestionCol;
    /**
     * The TableColumn for displaying the answer section of the question.
     */
    @FXML
    private TableColumn<Question, Void> id_AnswerCol;
    /**
     * The Text component for displaying the total points.
     */
    @FXML
    private Text id_TotalPoint;
    /**
     * The Text component for displaying a note about the ID check.
     */
    @FXML
    private Text id_notecheckid;
    /**
     * The TextField for entering the student ID.
     */
    @FXML
    private TextField id_Studentid;
    /**
     * The Button for submitting the exam.
     */
    @FXML
    private Button id_submet;
    /**
     * The TextArea for entering additional notes.
     */
    @FXML
    private TextArea id_Notes;
    /**
     * The Button for checking the ID.
     */
    @FXML
    private Button id_checkid;
    /**
     * The Label for displaying an identifier.
     */
    @FXML
    private Label label_id;
    /**
     * The Text component for displaying the remaining time.
     */
    @FXML
    private Text id_timeRemaining;
    /**
     * Flag indicating whether the answers have been shuffled.
     */
    private boolean answersShuffled = false;
    /**
     * Flag indicating whether the exam has been submitted.
     */
    private boolean SubmitDone=false;
    /**
     * Temporary flag for internal use.
     */
    private boolean tmp=false;
    /**
     * The thread used for background tasks.
     */
    private Thread thread;
    /**
     * Map to store shuffled questions.
     */
    private Map<String, Question> Shuffel = new HashMap<>();

/**
 * Map to store selected questions for the current exam.
 */
    private Map<String, CurrentExamSelectedQuestionStudent> examselect = new HashMap<>();
    /**
     * List to store the point values of each question.
     */
    private List<String> questionpoint = new ArrayList<>();
    /**
     * List to store all the questions.
     */
    private List<Question> questionList = new ArrayList<>();
    /**
     * List to store the point values of questions that are not selected.
     */
    private List<String> questpoinNotselect = new ArrayList<>();
    /**
     * List to store the exam entries.
     */
    private List<Exam> Examentery = new ArrayList<>();
    /**
     * The total points accumulated.
     */
    public static int point = 0;
    /**
     * Flag indicating a certain condition or state.
     */
    private int flag=0;
    /**
     * The total number of questions in the exam.
     */
    private int TotalQuestion = 0;
    /**
     * List to store the selected questions as an array.
     */
    public static List<String[]> selectedQuestionArray = new ArrayList<>();
    /**
     * The duration of the exam.
     */
    private int examDuration=0;
    /**
     * Flag indicating whether the exam is locked.
     */
    private boolean examLockStatus= false;
    /**
     * The previous duration of the exam.
     */
    private int previousDuration=startexam.get(0).getDuration();
    /**
     * Flag indicating whether a new duration has been set for the exam.
     */
    private boolean havenewduration=false;
    /**
     * The start time of the exam.
     */
    private long startTime;
    /**
     * The end time of the exam.
     */
    private long endTime;
    /**
     * The duration taken for the exam.
     */
    private String DurationTake;
    /**
     * The duration taken for the exam.
     */
    private String FormSubmission;
    /**
     * Flag indicating whether the form has been submitted.
     */
    private boolean Submitform=false;
    
    /**
     * Initializes the controller and sets up the initial state of the user interface.
     *
     * @param arg0 The URL of the location used to resolve relative paths for the root object.
     * @param arg1 The ResourceBundle that contains localizable UI strings.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), id_TableView);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    	selectedQuestionArray.clear();
    	questionpoint.clear();
    	questionList.clear();
    	id_Studentid.setText("");
    	point = 0;
    	String checkquery9 = "SELECT DescriptionForStudents FROM exam WHERE ID = ?";
        Object[] checkparams9 = { startexam.get(0).getExamID()};
        sqlmessage checkmessage9 = new sqlmessage("get", checkquery9, checkparams9);
        chat.accept(checkmessage9);
        for (Map<String, Object> row : resultList) {
        	Exam exam = Exam.convertToExam(row);
        	Examentery.add(exam);
        }
    	id_Notes.setText(Examentery.get(0).getDescriptionForStudents());
    	id_Notes.setEditable(false);
    	id_Notes.setStyle("-fx-text-fill: black;");
        retrieveQuestions();
        id_IDCol.setCellValueFactory(new PropertyValueFactory<>("questionNum"));
        id_QuestionCol.setCellValueFactory(new PropertyValueFactory<>("questionText"));
        id_AnswerCol.setCellFactory(createButtonCellFactory());
        id_TableView.setItems(FXCollections.observableArrayList(questionList));
        for (Question question : questionList) {
        	CurrentExamSelectedQuestionStudent select1 = new CurrentExamSelectedQuestionStudent(question.getQuestionNum(),question.getQuestionText(),"No Answer",
        			question.getAnswerCorrect(),"");
        	examselect.put(question.getQuestionNum(),select1 );
        	questpoinNotselect.add(question.getQuestionNum());
        }
        int i=0;
        for (String str : questionpoint) {
        	examselect.get(questpoinNotselect.get(i)).setPoint(str);
        	i++;
        }
        LockStatusThread lockStatusThread = new LockStatusThread();
        thread = new Thread( lockStatusThread);
        thread.setDaemon(true); // Set the thread as a daemon thread to automatically terminate when the application exits
        thread.start();
    }
    
    /**
     * A background thread for continuously checking the lock status and duration updates of the exam.
     */
    public class LockStatusThread extends Task<Void> {
   	 
    	  /**
         * Executes the background task to check the lock status and duration updates of the exam.
         *
         * @return The result of the background task.
         * @throws Exception If an error occurs during the task execution.
         */
    	@Override
   	    protected Void call() throws Exception {
   	        while (true) {
   	        	if(SubmitDone) {
                	break;
                }
   	        	if(Submitform)
   	        		break;
   	            // Check the lock status of the exam in the database
   	            boolean isLocked = isExamLocked();
   	            if (isLocked) {
   	                Platform.runLater(() -> {
   	                    // Display a message or take appropriate action when the exam is locked
   	                    Alert alert = new Alert(AlertType.WARNING);
   	                    alert.setTitle("Exam Locked");
   	                    alert.setHeaderText(null);
   	                    alert.setContentText("The exam has been locked. You can no longer submit.");
   	                    alert.showAndWait();  	                
   	                    //submit.setVisible(false);
   	                });
   	            }
   	            // Check if the duration has been updated in the database
   	            if(!tmp) {
   	            	int examDuration1 = getExamDuration();
   	   	            if ( previousDuration+examDuration1 != previousDuration) {
   	   	                Platform.runLater(() -> {
   	   	                    // Display a message or take appropriate action when the duration has changed
   	   	                    Alert alert = new Alert(AlertType.WARNING);
   	   	                    alert.setTitle("Exam Duration Updated");
   	   	                    alert.setHeaderText(null);
   	   	                    alert.setContentText("The duration of the exam has been updated. Please check the new duration.");
   	   	                    alert.showAndWait();
   	   	                });
   	   	                tmp=true;
   	   	            }
   	            }   
   	            Thread.sleep(5000); // Check every 5 seconds (adjust as needed)
   	        }
				return null;
   	    }

    	/**
    	 * Checks if the exam is locked based on the lock status in the database.
    	 *
    	 * @return  true if the exam is locked,  false otherwise.
    	 */
   	private boolean isExamLocked() {
       	String query = "SELECT * FROM startexam WHERE ExamID = ? AND IsLocked = ? AND ExamCode = ?";
       	Object [] pram= {startexam.get(0).getExamID(),1,startexam.get(0).getExamCode()};
       	sqlmessage mesaage=new sqlmessage("check",query,pram);
       	chat.accept(mesaage);
       	if (isexsit) {
       		examLockStatus=true;
       	}
           return examLockStatus;
       }
   	/**
   	 * Retrieves the duration of the exam from the database.
   	 *
   	 * @return The duration of the exam.
   	 */
       private int getExamDuration() {
       	String query = "SELECT * FROM sms WHERE exam_id = ? AND response =? ";
       	Object [] pram= {startexam.get(0).getExamID(),"Accept"};
       	sqlmessage mesaage=new sqlmessage("check",query,pram);
       	chat.accept(mesaage);
       	if (isexsit) {
       		sqlmessage mesaage2=new sqlmessage("get",query,pram);
           	chat.accept(mesaage2);
           	 for (Map<String, Object> row : resultList) {
           		 sms sms1 = sms.convertToSMS(row);
           		 examDuration=sms1.getNewDuration()-previousDuration;
           		 System.out.println("the new duration "+sms1.getNewDuration());
           	 }
           	 havenewduration=true;
           	
       	}
           return examDuration;
       }
   }
    /**
     * Retrieves the questions for the exam from the database.
     * Populates the questionList and questionpoint lists with the retrieved data.
     */
    private void retrieveQuestions() {
        questionpoint.clear();
        questionList.clear();
        String[] Questionid = exam.get(0).getPointPerQuestion().split(";");
        for (String string : Questionid) {
			System.out.println(string);
		}
        String checkquery2 = "SELECT * FROM question WHERE QuestionNumber = ?";
        for (int i = 0; i < Questionid.length; i += 2) {
            Object[] checkparams2 = { Questionid[i] };
            sqlmessage checkmessage2 = new sqlmessage("get", checkquery2, checkparams2);
            chat.accept(checkmessage2);
            for (Map<String, Object> row : resultList) {
                Question question = Question.convertToQuestion(row);
                questionList.add(question);
            }
            TotalQuestion++;
            questionpoint.add(Questionid[i + 1]);
        }
        for (Question string : questionList) {
        	System.out.println(string);
		}
    }
    /**
     * Creates a custom cell factory for the answer column in the question table.
     *
     * @return The cell factory for the answer column.
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
                    {
                       
                        //radioButton1.setSelected(true); // Set the first radio button as selected
                        container.getChildren().addAll(questionPointText, radioButton1, radioButton2, radioButton3, radioButton4);
                        container.setSpacing(5);
                        radioButton1.setToggleGroup(toggleGroup);
                        radioButton2.setToggleGroup(toggleGroup);
                        radioButton3.setToggleGroup(toggleGroup);
                        radioButton4.setToggleGroup(toggleGroup);

                        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue != null) {
                                // Radio button is selected
                                Question question = (Question) getTableRow().getItem();
                                if (question != null) {
                                    RadioButton selectedRadioButton = (RadioButton) newValue;
                                    String selectedAnswer = selectedRadioButton.getText();
                                    String[] selectedQuestionInfo = new String[5];
                                    selectedQuestionInfo[0] = question.getQuestionNum();
                                    selectedQuestionInfo[1] = question.getQuestionText();
                                    selectedQuestionInfo[2] = selectedAnswer;
                                    selectedQuestionInfo[3] = question.getAnswerCorrect();
                                    selectedQuestionInfo[4] = questionPointText.getText().replace("Point: ", "");
                                    selectedQuestionArray.add(selectedQuestionInfo);
                                    CurrentExamSelectedQuestionStudent select = new CurrentExamSelectedQuestionStudent(selectedQuestionInfo[0],selectedQuestionInfo[1],
                                            selectedQuestionInfo[2],selectedQuestionInfo[3],selectedQuestionInfo[4]);
                                    if(examselect.containsKey(selectedQuestionInfo)) {
                                        examselect.replace(selectedQuestionInfo[0], select);
                                    }else {
                                        examselect.put(select.getQuestionID(), select);
                                    }
                                    for (Map.Entry<String, CurrentExamSelectedQuestionStudent> entry : examselect.entrySet()) {
                                        String key = entry.getKey();
                                        CurrentExamSelectedQuestionStudent value = entry.getValue();
                                        System.out.println("Key: " + key);
                                        System.out.println("Value: " + value.toString());
                                    }
                                    System.out.println("------------------------------------");

                                }
                            } else {
                                // No radio button is selected
                                System.out.println("No radio button selected");
                            }
                        });

                        updateItem(null, true);
                    }
                    
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || getTableRow() == null) {
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

                                // Clear the selected toggle before updating the radio buttons
                                toggleGroup.selectToggle(null);

                                if (examselect.containsKey(question.getQuestionNum())) {
                                    CurrentExamSelectedQuestionStudent selectedQuestion = examselect.get(question.getQuestionNum());
                                    String selectedAnswer = selectedQuestion.getStudentAnswer();

                                    if (radioButton1.getText().equals(selectedAnswer)) {
                                        toggleGroup.selectToggle(radioButton1);
                                    } else if (radioButton2.getText().equals(selectedAnswer)) {
                                        toggleGroup.selectToggle(radioButton2);
                                    } else if (radioButton3.getText().equals(selectedAnswer)) {
                                        toggleGroup.selectToggle(radioButton3);
                                    } else if (radioButton4.getText().equals(selectedAnswer)) {
                                        toggleGroup.selectToggle(radioButton4);
                                    }
                                }

                                setGraphic(container);

                                // Disable or enable the radio buttons based on the flag
                                radioButton1.setDisable(flag == 0);
                                radioButton2.setDisable(flag == 0);
                                radioButton3.setDisable(flag == 0);
                                radioButton4.setDisable(flag == 0);
                            } else {
                                setGraphic(null);
                            }
                        }
                    }
                };
            }
        };
    }

    /**
     * Handles the event when the check button is clicked.
     *
     * @param event The action event.
     * @throws IOException If an I/O error occurs.
     */
    public void check(ActionEvent event) throws IOException {
    	if(id_Studentid.getText().isEmpty()) {
    		id_notecheckid.setText("Put you ID");
    		return;
    	}
    	String checkquery2 = "SELECT * FROM student WHERE ID = ? AND Username = ?";
        Object[] checkparams2 = {id_Studentid.getText(),Studentinfo.get(0).getUsername() };
        sqlmessage checkmessage2 = new sqlmessage("check", checkquery2, checkparams2);
        chat.accept(checkmessage2);
        if(!isexsit) {
        	id_notecheckid.setText("Uncorrect ID");
        	id_Studentid.setText("");
        	return;
        }
        id_notecheckid.setText("");
        flag = 1;
        // Update the disable property of the radio buttons
        id_TableView.refresh();
        id_Studentid.setVisible(false);
        label_id.setVisible(false);
        id_checkid.setVisible(false);
        TimeConverterThread ThreadTime = new TimeConverterThread(exam.get(0).getDuration(), id_timeRemaining);
        ThreadTime.start();
        startTime = System.currentTimeMillis();
    }
    /**
     * Handles the event when the back button is clicked.
     *
     * @param event The action event.
     * @throws IOException If an I/O error occurs.
     */
    public void Back(ActionEvent event) throws IOException {
    	questionList.clear();
        id_TableView.setItems(FXCollections.observableArrayList(questionList));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/student/Start Online Exam.fxml"));
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
     * A thread class that converts remaining time in seconds to minutes and seconds format and updates the UI.
     */
    public class TimeConverterThread extends Thread {
    	/**
    	 * The total number of minutes for the exam duration.
    	 */
    	private int minutes;
    	/**
    	 * The JavaFX Text object that displays the remaining time.
    	 */
        private Text timeRemainingText;
        /**
         * A flag indicating whether the additional duration has been added to the total time.
         * It is set to true once the additional duration is added to prevent multiple additions.
         */
        private boolean addDurationOneTime;
        
        /**
         * Constructs a TimeConverterThread with the specified minutes and time remaining text.
         *
         * @param minutes           The total number of minutes for the exam duration.
         * @param timeRemainingText The JavaFX Text object to display the remaining time.
         */
        public TimeConverterThread(int minutes, Text timeRemainingText) {
            this.minutes = minutes;
            this.timeRemainingText = timeRemainingText;
            this.addDurationOneTime = false;
        }
        /**
         * Converts the remaining time in seconds to minutes and seconds format and updates the UI.
         * If there is a new duration and it hasn't been added yet, it adds the additional duration.
         * If the exam is locked, it stops the countdown and triggers the submission process.
         */
        @Override
        public void run() {
            int totalSeconds = minutes * 60;
            while (totalSeconds > 0) {
                int remainingMinutes = totalSeconds / 60;
                int remainingSeconds = totalSeconds % 60;

                // Update the UI on the JavaFX Application Thread
                Platform.runLater(() -> {
                    String timeFormat = String.format("%02d:%02d", remainingMinutes, remainingSeconds);
                    timeRemainingText.setText(timeFormat);
                });
                
                if (havenewduration && !addDurationOneTime) {
                    totalSeconds = totalSeconds + examDuration * 60;
                    addDurationOneTime = true;
                }
                if(SubmitDone) {
                	break;
                }
                if(Submitform) {
   	        		break;}
                if (examLockStatus) {
                	Submitform=false;
                    Platform.runLater(() -> {
                        timeRemainingText.setText("00:00");
                    });
           
                    ActionEvent event = new ActionEvent();

                    Platform.runLater(() -> {
                    	if(!Submitform)
                    		Submet(event);
                    });
                    break;
                }
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                totalSeconds--;
            } 
            if (totalSeconds==0) {
            ActionEvent event = new ActionEvent();
            Platform.runLater(() -> {
                timeRemainingText.setText("00:00");
                if(!Submitform)
                	Submet(event);
            });
            //count2();
        }}
    }

    /**
     * Handles the submission of the exam.
     * @param event The event that triggered the submission.
     */
    public void Submet(ActionEvent event) {
        if(event.getSource() instanceof Button)
        	Submitform=true;
    	endTime = System.currentTimeMillis(); // Record the end time
        long elapsedTime = endTime - startTime; // Calculate the elapsed time in milliseconds
        // Convert the elapsed time to the desired format (e.g., minutes and seconds)
        long seconds = elapsedTime / 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        DurationTake = minutes+":"+seconds;
        // Call the submission function or perform any other desired action
    	SubmitDone=true;
    	count2();
    	SubmetExam();
   	 if (sqldone) {
        	System.out.println("The system has submitted your exam.");
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/student/StudentMain.fxml"));
			try {
		        Parent root = loader.load();
		        Scene scene = new Scene(root);
		        Stage stage = new Stage();
		        stage.setScene(scene);
		        stage.show();
		        // Close the current window
		        Stage currentStage = (Stage) id_TableView.getScene().getWindow();
		        currentStage.close();
			} catch (IOException e) {e.printStackTrace();}
        }
        else
        	System.out.println("Submet Failur.Try again!");
    }
    /**
     * Saves the exam result in the database.
     */
    public void SubmetExam() {
    	examresult result = calculateexam();
    	String checkquery3 = "INSERT INTO examresult (ExamID,ExamResult,Subject,Course,ExamAnswer,pointperquestion,rightanswer,lecturerID,date,"
    			+ "time,status,note,StudentID,startexamNum,DurationTake,FormSubmission) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] checkparams3 = {result.getExamID(),result.getExamResult(),result.getSubject(),result.getCourse()
        		,result.getExamAnswer(),result.getPointperquestion(),result.getRightanswer(),result.getLecturerID()
        		,result.getDate(),result.getTime(),result.getStatus(),result.getNote(),result.getStudentID(),result.getStartexamNum(),
        		result.getDurationTake(),result.getFormSubmission()};
        sqlmessage checkmessage3 = new sqlmessage("save", checkquery3, checkparams3);
        System.out.println("Name lucturer "+result.getLecturerID());
        chat.accept(checkmessage3); 
    }
    /**
     * Calculates the exam result based on the selected answers and returns an instance of the `examresult` class.
     * 
     * @return An instance of the `examresult` class representing the calculated exam result.
     */
    public examresult calculateexam() {
    	int ResultPoint=0;
    	StringBuilder ExamAnswer = new StringBuilder();
    	StringBuilder pointperquestion = new StringBuilder();
    	StringBuilder RightAnswer = new StringBuilder();
    	for (Map.Entry<String, CurrentExamSelectedQuestionStudent> exam : examselect.entrySet()) {
    		if(exam.getValue().getStudentAnswer().equals(exam.getValue().getRightAnswer()))
    			ResultPoint+=Integer.parseInt(exam.getValue().getPoint());
    		ExamAnswer.append(exam.getValue().getStudentAnswer());
    		if(exam.getValue().getStudentAnswer().equals(exam.getValue().getRightAnswer()))
    			pointperquestion.append(exam.getValue().getPoint());
    		else
    			pointperquestion.append("0");
    		
    		ExamAnswer.append(";");
    		pointperquestion.append(";");
    		RightAnswer.append(exam.getValue().getRightAnswer());
    		RightAnswer.append(";");
    		
		}
    	ExamAnswer.deleteCharAt(ExamAnswer.length() - 1);
    	pointperquestion.deleteCharAt(pointperquestion.length() - 1);
    	RightAnswer.deleteCharAt(RightAnswer.length() - 1);
    	LocalDate Date = LocalDate.now();
    	//Get Time
    	Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        Time currentTime = new Time(calendar.getTimeInMillis());
    	//Get Date
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        examresult returnexam = examresult.getInstance(0,exam.get(0).getID(),String.valueOf(ResultPoint),exam.get(0).getSubject(),exam.get(0).getCourse(),
    			ExamAnswer.toString(),pointperquestion.toString(),RightAnswer.toString(),startexam.get(0).getLecturerName(),
    			sqlDate.toString(),currentTime.toString(),"0","","",Studentinfo.get(0).getID(),startexam.get(0).getStartnum(),DurationTake,FormSubmission);
        if(!Submitform)
        	returnexam.setFormSubmission("late");
        else
        	returnexam.setFormSubmission("in Time");
        return returnexam;
    	
    }
    
    /**
     * Decrements the count for the current exam. If the count reaches 0, performs additional operations such as deleting the current exam
     * entry, updating the startexam table, and sending relevant messages.
     */
    private void count2() {
   	 String query = "SELECT * FROM currentexam WHERE examid = ?";
		 Object[] pram = {startexam.get(0).getExamID()};
		 sqlmessage message = new sqlmessage("get",query , pram);
		 chat.accept(message);
		 CurrentExam ex=CurrentExam.convertToCurrentExam(resultList.get(0));
		 if((ex.getCount()-1)==0) {
			 String deletequery = "DELETE FROM currentexam WHERE examid = ?";
			 Object[] deletepram = {startexam.get(0).getExamID()};
			 sqlmessage deletemessage = new sqlmessage("delete",deletequery , deletepram);
			 chat.accept(deletemessage);
			 String editquery = "UPDATE startexam SET IsLocked = ? WHERE examid = ?";
			 Object[] editpram = {1,startexam.get(0).getExamID()};
			 sqlmessage editmessage = new sqlmessage("edit",editquery , editpram);
			 chat.accept(editmessage); 
			 String query2 = "SELECT * FROM sms WHERE exam_id = ?";
			 Object[] pram2 = {startexam.get(0).getExamID()};
			 sqlmessage message2 = new sqlmessage("check",query2 , pram2);
			 chat.accept(message2);
			 if(isexsit) {
				 String deletequery2 = "DELETE FROM sms WHERE exam_id = ?";
				 Object[] deletepram2 = {startexam.get(0).getExamID()};
				 sqlmessage deletemessage2 = new sqlmessage("delete",deletequery2 , deletepram2);
				 chat.accept(deletemessage2);
			 }
			 Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					chat.accept("CheckExams;" + startexam.get(0).getExamID());
					chat.accept("ShowExamstatic;" + startexam.get(0).getExamID()+";"+startexam.get(0).getLecturerName());
				}
			});
			 t.start();
		 }
		 else {
        String query2 = "UPDATE currentexam SET count = ? WHERE examid = ?";
        Object[] pram2 = {ex.getCount()-1,startexam.get(0).getExamID()};
		 sqlmessage message2 = new sqlmessage("edit",query2 , pram2);
		 chat.accept(message2);
		 }
   }

}
