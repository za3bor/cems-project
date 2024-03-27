package gui;



import java.io.IOException;

import client.ClientController;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * Controller class for the IP page of the GUI application.
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 */
public class IpPageController {
	/**
	 * Represents a text field for entering an IP address.
	 */
	@FXML
	private TextField ip_textfield;

	/**
	 * Represents a text field for entering a port number.
	 */
	@FXML
	private TextField port_textfield;

	/**
	 * Represents a button for triggering an action to enter from another computer.
	 */
	@FXML
	private Button enterFromAnotherComputerButton;

	/**
	 * Represents a button for triggering an action to enter as localhost.
	 */
	@FXML
	private Button enterAsLocalhostButton;

	/**
	 * Holds an instance of the ClientUI class.
	 */
	public ClientUI client;
    
   
    /**
     * Event handler for the "Enter from Another Computer" button.
     * Retrieves the entered IP address and port, initializes the client controller,
     * and navigates to the LoginClient page.
     *
     * @param event the ActionEvent triggered by clicking the button
     * @throws IOException if an I/O error occurs while loading the LoginClient.fxml file
     */
    @FXML
    public void handleEnterFromAnotherComputerButton(ActionEvent event) throws IOException {
        String ipAddress = ip_textfield.getText();
        int port = Integer.parseInt(port_textfield.getText());
        client.chat=new ClientController(ipAddress, port);
        client.chat.accept("AddClient");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginClient.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Event handler for the "Enter as Localhost" button.
     * Sets the IP address and port to localhost values, initializes the client controller,
     * and navigates to the LoginClient page.
     *
     * @param event the ActionEvent triggered by clicking the button
     * @throws IOException if an I/O error occurs while loading the LoginClient.fxml file
     */
    @FXML
    public void enterAsLocalhostButton(ActionEvent event) throws IOException {
        String ipAddress = "localhost";
        int port = 5555;
        client.chat=new ClientController(ipAddress, port);
        client.chat.accept("AddClient");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginClient.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Event handler for the exit application button.
     * Exits the application when the button is clicked.
     *
     * @param event the ActionEvent triggered by clicking the button
     * @throws IOException if an I/O error occurs
     */
    @FXML
    public void exitapp(ActionEvent event) throws IOException {
    	System.exit(1);
    }

}

