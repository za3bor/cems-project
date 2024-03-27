package lecturer;

import java.io.IOException;
import java.util.Map;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.sms;
import logic.sqlmessage;

import static gui.LogIn.Lecturerinfo;
import static client.ChatClient.resultList;
import static client.ClientUI.chat;
/**
 * Controller class for the SMS view.
 * It handles the display and management of SMS data in a table view.
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class SmsController {
    /**
     * The button for navigating back to the main lecturer view.
     */
    @FXML
    private Button backButton;
    /**
     * The button for refreshing the SMS data in the table view.
     */
    @FXML
    private Button refreshButton;
    /**
     * The table view for displaying the SMS data.
     */
    @FXML
    private TableView<sms> sms_table;
    /**
     * The table column for the exam ID in the SMS table.
     * It displays the exam ID associated with each SMS.
     */
    @FXML
    private TableColumn<sms, String> exam_id_column;
    /**
     * The table column for the new duration in the SMS table.
     * It displays the new duration requested in the SMS.
     */
    @FXML
    private TableColumn<sms, Integer> new_duration_column;
    /**
     * The table column for the reason in the SMS table.
     * It displays the reason provided in the SMS.
     */
    @FXML
    private TableColumn<sms, String> reason_column;
    /**
     * The table column for the response in the SMS table.
     * It displays the response given for each SMS.
     */
    @FXML
    private TableColumn<sms, String> response_column;
    /**
     * The list of SMS objects displayed in the table view.
     */
    private ObservableList<sms> smsList = FXCollections.observableArrayList();
    
    /**
     * Initializes the SMS view.
     * It configures the table columns, loads the SMS data, and performs a fade-in animation for the table.
     */
    @FXML
    private void initialize() {
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), sms_table);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        exam_id_column.setCellValueFactory(new PropertyValueFactory<>("examId"));
        new_duration_column.setCellValueFactory(new PropertyValueFactory<>("newDuration"));
        reason_column.setCellValueFactory(new PropertyValueFactory<>("reason"));
        response_column.setCellValueFactory(new PropertyValueFactory<>("response"));

        loadData();
    }
    /**
     * Handles the back button action.
     * It navigates back to the main lecturer view.
     *
     * @param event The action event triggered by the button.
     * @throws IOException If an I/O error occurs while loading the view.
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
    /**
     * Handles the refresh button action.
     * It clears and reloads the SMS data in the table view.
     *
     * @param event The action event triggered by the button.
     * @throws IOException If an I/O error occurs while loading the view.
     */
    @FXML
    public void refresh(ActionEvent event) throws IOException {
        smsList.clear();
        sms_table.getItems().clear();
        sms_table.refresh();

        loadData();
    }
    /**
     * Loads the SMS data from the database and populates the table view.
     * It retrieves the SMS data associated with the current lecturer and displays them in the table.
     */
    private void loadData() {
        String query = "SELECT * FROM sms WHERE lecturer = ?";
        Object[] params = { Lecturerinfo.get(0).getFirstName() };
        sqlmessage message = new sqlmessage("get", query, params);
        chat.accept(message);

        for (Map<String, Object> row : resultList) {
            sms sms1 = sms.convertToSMS(row);
            String response = sms1.getResponse();
            if (response != null && !response.isEmpty()) {
                smsList.add(sms1);
            }
        }

        sms_table.setItems(smsList);
    }
}


