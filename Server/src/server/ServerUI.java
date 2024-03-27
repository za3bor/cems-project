package server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import java.net.UnknownHostException;

import gui.ServerGUI;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * The ServerUI class represents the user interface for the server application.
 * It extends the JavaFX `Application` class.
 */
public class ServerUI extends Application {
	   /**
     * The ServerGUI instance associated with the server UI.
     */
private static ServerGUI aFrame;

/**
 * The main method that launches the JavaFX application.
 *
 * @param args The command-line arguments.
 */
    public static void main(String args[]) {
        launch(args);
    }
    /**
     * Starts the server UI by creating a new `ServerGUI` instance and displaying it.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     * @throws InvocationTargetException if an exception occurs during the execution of the start method.
     */
    @Override
    public void start(Stage primaryStage) throws InvocationTargetException {
        try {
            aFrame = new ServerGUI(); // create ServerGUI
            aFrame.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs the server on the specified port.
     *
     * @param p The port number to listen on.
     * @throws UnknownHostException if the host is unknown.
     */
    public static void runServer(String p) throws UnknownHostException {
        int port = 0; // Port to listen on

        try {
            port = Integer.parseInt(p); // Set port to specified value
        } catch (Throwable t) {
            System.out.println("ERROR - Could not connect!");
            return; // Exit the method if the port is invalid
        }

        if (isServerRunning(port)) {
            System.out.println("Server is already running on port " + port);
            return; // Exit the method if the server is already running
        }

        CemsServer sv = new CemsServer(port,aFrame);

        try {
            sv.listen(); // Start listening for connections
            System.out.println("Server started on port " + port);
            System.out.println("Host: " + sv.getHost());
            System.out.println("IP address: " + sv.getIP().getHostAddress());
            System.out.println("Status: Running");
        } catch (Exception ex) {
            System.out.println("ERROR - Could not listen for clients!");
        }
    }
    
    /**
     * Checks if a server is already running on the specified port.
     *
     * @param port The port number to check.
     * @return `true` if a server is already running on the port, `false` otherwise.
     */
    private static boolean isServerRunning(int port) {
        try (java.net.ServerSocket socket = new java.net.ServerSocket()) {
            socket.setReuseAddress(true);
            socket.bind(new java.net.InetSocketAddress(port));
            return false;
        } catch (IOException e) {
            return true;
        }
    }

	
}
