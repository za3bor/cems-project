package client;

import logic.myfile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.ChatIF;
import ocsf.client.AbstractClient;
/**
 * The ChatClient class extends the AbstractClient class from the OCSF library
 * and represents a client that communicates with a server using the client-server
 * communication protocol.
 */
public class ChatClient extends AbstractClient {
	  /**
     * Flag indicating if the client is currently checking exam copies.
     */
	public static boolean ExamCheckingCopies=false;
    /**
     * Flag indicating if the client is awaiting a response from the server.
     */
	public static boolean awaitResponse = false;
	   /**
     * Flag indicating if the user is authenticated.
     */
	public static boolean checkuser = false;
    /**
     * Flag indicating if the question was successfully saved.
     */
	public static boolean checkQuestionSave = false;
    /**
     * Flag indicating if the exam was successfully saved.
     */
	public static boolean checkExamSave = false;
    /**
     * Flag indicating if the data exists.
     */
	public static boolean isexsit;
    /**
     * Flag indicating if the SQL operation is done successfully.
     */
	public static boolean sqldone;
    /**
     * Flag indicating if the student's exam is locked.
     */
	public static boolean ExamLockedStudent=true;
    /**
     * List of result maps received from the server.
     */
	public static List<Map<String, Object>> resultList = new ArrayList<>();
    /**
     * The file associated with the client.
     */
	public static myfile Myfile;

    /**
     * Constructs a ChatClient object with the specified host, port, and clientUI.
     *
     * @param host      The host name or IP address of the server.
     * @param port      The port number of the server.
     * @param clientUI  The ChatIF object that provides the UI for the client.
     * @throws IOException If an I/O error occurs when opening the connection.
     */    
	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
        super(host, port);
        openConnection();
    }

    // Instance methods

    /**
     * This method handles all data that comes in from the server.
     *
     * @param msg The message from the server.
     */
    public void handleMessageFromServer(Object msg) {
        awaitResponse = false;
        if (msg instanceof String) {
            System.out.println("--> handleMessageFromServer");
            String message = (String) msg;
            if (message.contains("loginSuccess")) {
                checkuser = true;
            }
            else if (message.contains("DataExist")) {
                isexsit = true;
            } else if (message.contains("DataNotExist")) {
                isexsit = false;
            } else if (message.contains("SqlOperationSuccss")) {
                sqldone = true;
            } else if (message.contains("SqlOperationfail")) {
                sqldone = false;
            }else if(message.contains("checking Exams for")) {
            	ExamCheckingCopies=true;
            }
        } else if (msg instanceof List) {
            List<?> list = (List<?>) msg;
            if (!list.isEmpty() && list.get(0) instanceof Map) {
                resultList.clear();
                for (Object element : list) {
                    if (element instanceof Map) {
                        Map<String, Object> map = (Map<String, Object>) element;
                        resultList.add(map);
                    }
                }
            }
        } else if (msg instanceof byte[]) {
            byte[] message = (byte[]) msg;
            Myfile = new myfile(null, null, null, null, message);
        }
    }

    /**
     * This method handles all data coming from the UI.
     *
     * @param message The message from the UI.
     */
    public void handleMessageFromClientUI(Object message) {
        try {
            awaitResponse = true;
            sendToServer(message);
            while (awaitResponse) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method terminates the client.
     */
    public void quit() {
        try {
            closeConnection();
        } catch (IOException e) {
            // Ignore errors on quit
        }
        System.exit(0);
    }
}
