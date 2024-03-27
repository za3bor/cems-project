package gui;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import database.DBController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import logic.Client_info;
import server.CemsServer;
import server.ServerUI;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * 
 * The ServerGUI class controls the graphical user interface of the server application.
 * It manages the server connection, server shutdown, and displays server status and client information in a TableView.
 * 
 * This class implements the Initializable interface to initialize the GUI components defined in the associated FXML file.
 * 
 * Note: This class assumes the existence of a ServerGUI.fxml file for defining the layout and components of the GUI.
 * 
 */

public class ServerGUI implements Initializable {
	/**
	 * The TableView component that displays client information.
	 */
    @FXML
    private TableView<Client_info> clientTable;
    /**
     * The TextField component for displaying the IP address of the server.
     */
    @FXML
    private TextField id_IPaddress;
    /**
     * The TextField component for entering the port number for the server connection.
     */
    @FXML
    private TextField id_Port;
    /**
     * The TextField component for entering the database path.
     */
    @FXML
    private TextField id_DB_path;
    /**
     * The TextField component for entering the database username.
     */
    @FXML
    private TextField id_DB_UserName;
    /**
     * The TextField component for entering the database password.
     */
    @FXML
    private TextField id_DB_Password;
    /**
     * The Button component for starting the server connection.
     */
    @FXML
    private Button connectionbutton;
    /**
     * The FontAwesomeIcon component for exiting the application.
     */
    @FXML
    private FontAwesomeIcon exitbutton;
    /**
     * The TableColumn component for displaying the client IP address in the clientTable.
     */
    @FXML
    private TableColumn<Client_info, String> ipColumn;
    /**
     * The TableColumn component for displaying the client hostname in the clientTable.
     */
    @FXML
    private TableColumn<Client_info, String> hostColumn;
    /**
     * The TableColumn component for displaying the client status in the clientTable.
     */
    @FXML
    private TableColumn<Client_info, String> statusColumn;
    /**
     * The Text component for displaying the server connection status message.
     */
    @FXML
    private Text connectedMessage;
    /**
     * The TextArea component for displaying the server console output.
     */
    @FXML
    private TextArea consolearea;
    /**
     * The CemsServer instance representing the server.
     */
    private static CemsServer server=null;
    /**
     * The string variable storing the server port number.
     */
    private String Port;
    /**
     * The string variable storing the database username.
     */
    public static String username;
    /**
     * The string variable storing the database password.
     */
    public static String password;
    /**
     * The string variable storing the database name.
     */
    public static String db_name;
    /**
     * The ConsoleOutput instance used to redirect System.out and System.err to the consolearea.
     */
    private ConsoleOutput consoleOutput;
    /**
     * A boolean variable indicating whether the server is running or not.
     */
    private boolean isServerRunning = false;
    /**
     * The ObservableList used to store and update the client information for the clientTable.
     */
    private ObservableList<Client_info> updatedData = FXCollections.observableArrayList();
    /**
     * The DBController instance used for database operations.
     */
    DBController db = new DBController();
    
    /**
     * The SimpleIntegerProperty used for triggering the refresh of the clientTable.
     */
    public static SimpleIntegerProperty num = new SimpleIntegerProperty(0);
  
    /**
     * Starts the server GUI by loading the associated FXML file and displaying the stage.
     * 
     * @param primaryStage the primary stage of the JavaFX application
     */
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ServerGUI.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Connects the server to the network and starts listening for client connections.
     * This method is called when the connection button is clicked.
     * 
     * @param event the action event
     * @throws Exception if an error occurs while starting the server
     */
    public void connectServer(ActionEvent event) throws Exception {
        try {
            if (isServerRunning) {
                System.out.println("Server is already running.");
                connectedMessage.setText("Server is already running");
                return;
           }
            Port = id_Port.getText();
            username = id_DB_UserName.getText();
            password = id_DB_Password.getText();
            db_name = id_DB_path.getText();
            server = new CemsServer(Integer.valueOf(Port),this);
            id_IPaddress.setText(server.getIP().getHostAddress());
            ServerUI.runServer(Port);
            isServerRunning = true;
            connectedMessage.setText("Server started");
        } catch (Exception e) {
            System.out.println("Error starting server: " + e.getMessage());
            connectedMessage.setText("Server failed to start");
        }
    }
    /**
     * Stops the server and disconnects all client connections.
     * This method is called when the stop server button is clicked.
     * 
     * @param event the action event
     * @throws Exception if an error occurs while stopping the server
     */
    public void StopServer(ActionEvent event) throws Exception {

        try {
            // Stop the server
            if (server != null) {
                server.stopListening();
                server.close();
                server = null;

                // Clear the client table
                clientTable.getItems().clear();
                // Update the UI or show a message indicating that the server has been stopped
                System.out.println("Server stopped successfully.");
                connectedMessage.setText("Server stopped");
                isServerRunning = false;
            } else {
                System.out.println("Server is already stopped or not started.");
                connectedMessage.setText("Server is not running");
            }
        } catch (IOException e) {
            System.out.println("Error stopping server: " + e.getMessage());
            connectedMessage.setText("Error stopping server");
        }
    }
    
    /**
     * Exits the application when the exit button is clicked.
     * 
     * @param event the mouse event
     */
    @FXML
    public void exitApplication(javafx.scene.input.MouseEvent event) 
    {
    	Platform.exit();
        System.exit(0);
    }
    /**
     * Initializes the GUI components and sets their initial values.
     * This method is automatically called by JavaFX after the FXML file has been loaded.
     * 
     * @param arg0 the URL location
     * @param arg1 the resource bundle
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        id_Port.setText("5555");
        id_DB_path.setText("jdbc:mysql://localhost/cems?serverTimezone=Asia/Jerusalem");
        id_DB_UserName.setText("root");
        id_DB_Password.setText("Aa123456");
        consoleOutput = new ConsoleOutput(consolearea);
        System.setOut(consoleOutput);
        System.setErr(consoleOutput);
        ipColumn.setCellValueFactory(new PropertyValueFactory<>("ip")); 
        hostColumn.setCellValueFactory(new PropertyValueFactory<>("host")); 
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        num.addListener((obs, oldValue, newValue) -> {
        	if (newValue.intValue() == 1) {
                refreshTableView();
                num.set(0);
            }});
        
        
    }
    
    
    /**
     * Refreshes the clientTable TableView with the updated client information.
     * This method is called whenever the client information is updated.
     */
    public void refreshTableView() {
        // Clear the existing data
        updatedData.clear();

        // Add your logic to update the data in the updatedData list
        HashMap<String, Client_info> clientInfoMap = server.getClientInfoMap();
        for (Map.Entry<String, Client_info> entry : clientInfoMap.entrySet()) {
            Client_info clientInfo = entry.getValue();
            updatedData.add(clientInfo);
        }

        // Update the table view
        clientTable.setItems(updatedData);
        clientTable.refresh();
    }
}
