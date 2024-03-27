package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * The ClientUI class is responsible for launching the client-side user interface.
 * It extends the JavaFX Application class and provides the start() method to start
 * the JavaFX application.
 */
public class ClientUI extends Application {
	  /**
     * The single instance of the ClientController.
     */
	public static ClientController chat; // Only one instance

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args The command line arguments.
     * @throws Exception If an exception occurs during application launch.
     */
    public static void main(String args[]) throws Exception {
        launch(args);
    }

    /**
     * Starts the JavaFX application by loading the IP_page.fxml file and displaying
     * it in the primary stage.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     * @throws Exception If an exception occurs during the loading or displaying of the UI.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Load the IpPage.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/IP_page.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	
}
