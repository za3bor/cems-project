package server;

import java.util.HashMap;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import logic.Client_info;
import logic.myfile;
import logic.sqlmessage;
import messageProcessor.FileHandler;
import messageProcessor.SqlMessageProcessor;
import messageProcessor.StringMessageProcessor;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import gui.ServerGUI;

/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * The CemsServer class represents the server implementation for the CEMS application.
 */
public class CemsServer extends AbstractServer  {
	   /**
     * The IP address of the server.
     */
    private InetAddress ip;
    /**
     * A HashMap storing client information.
     */
    public static HashMap<String, Client_info> Client_info = new HashMap<>();
    /**
     * The ServerGUI instance associated with the server.
     */
    private ServerGUI serverGUI;
    
    /**
     * Constructs a CemsServer object with the specified port and ServerGUI instance.
     *
     * @param port       The port number for the server.
     * @param serverGUI  The ServerGUI instance associated with the server.
     */
    public CemsServer(int port, ServerGUI serverGUI) {
        super(port);
        try {
            this.ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.serverGUI = serverGUI; // Store the reference to the ServerGUI instance
    }  
    
    /**
     * Handles the received message from a client.
     *
     * @param msg     The message received from the client.
     * @param client  The connection to the client.
     */
    @Override
    public void handleMessageFromClient(Object msg, ConnectionToClient client) {
        if (msg instanceof String) {
        	new StringMessageProcessor().processMessage((String)msg, client, serverGUI, this);  
        }else if (msg instanceof sqlmessage) {
        	new SqlMessageProcessor().processMessage((sqlmessage)msg,serverGUI, this,client);  
        }else if (msg instanceof myfile) {
        	 myfile fileObject = (myfile) msg;
        	 if(fileObject.getWhatToDo().equals("save")) {
        		String examId = fileObject.getFileName();
         	    String filePath = fileObject.getPath();
         	    byte[] fileData = fileObject.getFileData();
         	    String fileType = fileObject.getType();
         	    
         	    // Save the file using the FileHandler class
         	    boolean saveResult = FileHandler.saveFile(filePath, fileData, fileType,fileObject.getFileName());
         	    
         	   if (saveResult) {
                   System.out.println("File saved successfully");
               } else {
                   System.out.println("Failed to save the file");
               }
         	  try {
				client.sendToClient("");
			} catch (IOException e) {
				e.printStackTrace();
			}
        	 }
        	 else if (fileObject.getWhatToDo().equals("get")) {
        		 byte[] fileData = FileHandler.getFileData(fileObject.getPath());
        		 if (fileData != null) {
                     try {
                         client.sendToClient(fileData);
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 } else {
                     System.out.println("File not found");
                 }
        	 }	
        }
    }
    /**
     * Returns the hostname of the server.
     *
     * @return The hostname of the server.
     */
    public String getHost() {
        return ip.getHostName();
    }
    /**
     * Returns the IP address of the server.
     *
     * @return The IP address of the server.
     */
    public InetAddress getIP() {
        return ip;
    }
    
    /**
     * Returns the HashMap storing client information.
     *
     * @return The HashMap storing client information.
     */
    public HashMap<String, Client_info> getClientInfoMap() {
        return Client_info;
    }
}