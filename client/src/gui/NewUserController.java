package gui;

import static client.ClientUI.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.manger;
import logic.sqlmessage;
import static client.ChatClient.resultList;
/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * The controller for the New User GUI.
 * Handles user interactions and communicates with the server.
 */
public class NewUserController {
	/**
	 * Represents the user password text field.
	 */
	@FXML
	private TextField userpassword_field;

	/**
	 * Represents the username text field.
	 */
	@FXML
	private TextField username_field;

	/**
	 * Represents the user phone number text field.
	 */
	@FXML
	private TextField userphone_field;

	/**
	 * Represents the user email text field.
	 */
	@FXML
	private TextField useremail_field;

	/**
	 * Represents the user last name text field.
	 */
	@FXML
	private TextField userlast_field;

	/**
	 * Represents the user first name text field.
	 */
	@FXML
	private TextField userfirst_field;

	/**
	 * Represents the user ID text field.
	 */
	@FXML
	private TextField userID_field;

	/**
	 * Represents your ID text field.
	 */
	@FXML
	private TextField yourID_field;

	/**
	 * Represents the send button.
	 */
	@FXML
	private Button send_button;

	/**
	 * Represents the back button.
	 */
	@FXML
	private Button back_button;

	/**
	 * Represents the text for displaying check messages.
	 */
	@FXML
	private Text check_text;

	/**
	 * Represents the combo box for selecting roles.
	 */
	@FXML
	private ComboBox<String> roles_combo;

	/**
	 * Stores the list of manager IDs.
	 */
	private List<String> mangerID = new ArrayList<>();

    /**
     * Initializes the controller.
     * This method is automatically called after the FXML file has been loaded.
     * It sets up the combo box items and opens a connection to the server.
     */    
    public void initialize() {
    	roles_combo.getItems().addAll("Student", "Lecturer", "Department_head");
    	chat.accept("openconnection");
    	
    }
    /**
     * Handles the action when the send button is clicked.
     * Retrieves the input values from the text fields and combo box,
     * performs validations, and sends the data to the server if valid.
     * Displays appropriate messages based on the result.
     *
     * @param event The action event.
     * @throws IOException If an I/O error occurs.
     */
    public void send(ActionEvent event) throws IOException {
    	check_text.setText("");
    	String s = userpassword_field.getText();
    	String s1 = username_field.getText();
    	String s2 = userphone_field.getText();
    	String s3 =useremail_field.getText();
    	String s4 =userlast_field.getText();
    	String s5 =userfirst_field.getText();
    	String s6 =userID_field.getText();
    	String s7 =yourID_field.getText();
        String selectedRole = roles_combo.getValue();

    	
        if (s.isEmpty() || s1.isEmpty()|| s2.isEmpty()|| s3.isEmpty()|| s4.isEmpty()|| s5.isEmpty()
        		|| s6.isEmpty()|| s7.isEmpty() || selectedRole.isEmpty()) {
        	check_text.setText("One of the fields are empty.");
        }
        else{
            String query = "SELECT * FROM manger WHERE ID= ? ";
            Object[] params = {s7};
            sqlmessage message = new sqlmessage("get", query, params);
            chat.accept(message);
    		for (Map<String, Object> row : resultList) {
    			manger man = manger.convertToManger(row);
    			mangerID.add(man.getID());
    		}
            if(mangerID.contains(s7)) {
            	if(selectedRole.equals("Student")) {
            		check_text.setText("Student saves successfully.");
            	}
            	else if (selectedRole.equals("Lecturer")) {
            		check_text.setText("Lecturer saves successfully.");
            	}
            	else if(selectedRole.equals("Department_head")) {
            		check_text.setText("Department head saves successfully.");
            	}
            }
            else {
            	check_text.setText("You don't Have a permission.");
            }
        }

    }
    
    /**
     * Handles the action when the back button is clicked.
     * Redirects the user to the login screen.
     *
     * @param event The action event.
     * @throws IOException If an I/O error occurs.
     */
    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginClient.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
