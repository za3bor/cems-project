package messageProcessor;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import database.DBController;
import gui.ServerGUI;
import logic.Client_info;
import logic.StartExam;
import logic.examresult;
import ocsf.server.ConnectionToClient;
import server.CemsServer;

/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * This class processes incoming string messages received by the server.
 * It performs various operations based on the content of the message.
 */
public class StringMessageProcessor {
	   /**
     * The database controller used for database operations.
     */
	DBController db = new DBController();
    /**
     * Array to store the result of range values.
     */
	private int[] rangeresult;
    
    /**
     * Processes the incoming message and performs the appropriate action.
     *
     * @param message    The incoming message.
     * @param client     The client connection.
     * @param serverGUI  The server graphical user interface.
     * @param sv         The CemsServer instance.
     */
	public void processMessage(String message, ConnectionToClient client, ServerGUI serverGUI,CemsServer sv) {
        if (message.equals("AddClient")) {
            addClient(client, serverGUI,sv);
        } else if (message.equals("closeClient")) {
            closeClient(client,sv,serverGUI);
        } else if (message.contains("connection")) {
        	try {
				connection(message,serverGUI,client);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }else if(message.contains("ShowExamstatic")){
        	String[] tmp = message.split(";");
        	showexamstatic(tmp[1],tmp[2]);
        	
        }else if(message.contains("CheckExams"))
			try {
				PutTheAnswerIntoMatrix(message,serverGUI,client);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        try {
			client.sendToClient("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Adds a client to the server's client list.
     *
     * @param client     The client connection.
     * @param serverGUI  The server graphical user interface.
     * @param sv         The CemsServer instance.
     */
    private void addClient(ConnectionToClient client, ServerGUI serverGUI,CemsServer sv) {
        String clientHost=null;
		try {
			clientHost = client.getInetAddress().getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String clientIP = client.getInetAddress().getHostAddress();
        if (!sv.Client_info.containsKey(clientIP)) {
        	Client_info clientData = new Client_info(clientHost, clientIP, "connected");
        	sv.Client_info.put(clientIP, clientData);
        	serverGUI.num.set(1);
        
        	}
        if (sv.Client_info.containsKey(clientIP) && sv.Client_info.get(clientIP).getStatus().equals("disconnected")) {
        	Client_info clientData = sv.Client_info.get(clientIP);
            clientData.setHost(clientHost);
            clientData.setStatus("connected");
            sv.Client_info.put(clientIP, clientData);
            serverGUI.num.set(1);      
        }
        try {
			client.sendToClient("done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Closes a client connection and updates the server's client list.
     *
     * @param client     The client connection.
     * @param sv         The CemsServer instance.
     * @param serverGUI  The server graphical user interface.
     */
    private void closeClient(ConnectionToClient client, CemsServer sv, ServerGUI serverGUI) {
    	String clientHost=null;
		try {
			clientHost = client.getInetAddress().getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String clientIP = client.getInetAddress().getHostAddress();

        if (sv.Client_info.containsKey(clientIP)) {
            Client_info clientData = sv.Client_info.get(clientIP);
            clientData.setHost(clientHost);
            clientData.setStatus("disconnected");
            sv.Client_info.put(clientIP, clientData);
            serverGUI.num.set(1);
            try {
				client.sendToClient("done");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    
    /**
     * Handles the connection message from a client.
     *
     * @param msg    The connection message.
     * @param serverGUI  The server graphical user interface.
     * @param client     The client connection.
     * @throws SQLException if there is an error with the database.
     */
    private void connection(String msg,ServerGUI serverGUI,ConnectionToClient client) throws SQLException {
    	DBController db = new DBController();
    	
    	if (msg.equals("openconnection"))
    	{
    		db.ConnectToDB(serverGUI.db_name, serverGUI.username, serverGUI.password);
    	}
    	else if (msg.equals("closeconnection")) {
    		db.closeConnection();
    	}
    	try {
			client.sendToClient("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Processes the answers from a client and updates the exam results.
     *
     * @param InPut    The message containing the answers.
     * @param serverGUI  The server graphical user interface.
     * @param client     The client connection.
     * @throws SQLException if there is an error with the database.
     * @throws IOException  if there is an error with the I/O.
     */
    protected void PutTheAnswerIntoMatrix(String InPut,ServerGUI serverGUI,ConnectionToClient client) throws SQLException, IOException{
    	String[] input = InPut.split(";");
    	String checkquery = "SELECT Startnum FROM startexam WHERE ExamID = ?";
        Object[] checkparams = {input[1]};
        List<Map<String, Object>> ListStartExam = db.getDataFromMySQL(checkquery, checkparams);
        List<StartExam> startExamList = new ArrayList<>();
        for (Map<String, Object> row : ListStartExam) {
        	StartExam startExam = StartExam.convertToStartExam(row);
        	startExamList.add(startExam);
        }
        
        //System.out.println(startExamList.get(0).getStartnum());
        String checkquery2 = "SELECT * FROM examresult WHERE startexamNum = ?";
        Object[] checkparams2 = {startExamList.get(0).getStartnum()};
        List<Map<String, Object>> ListExamresult = db.getDataFromMySQL(checkquery2, checkparams2);
        List<examresult> examresultList = new ArrayList<>();
        for (Map<String, Object> row : ListExamresult) {
        	//System.out.println(row);
        	examresult startExam = examresult.convertToExamResult(row);
        	System.out.println(startExam);
        	examresultList.add(startExam);
        }
        int numofstudent = examresultList.size();
        String[] answer = examresultList.get(0).getRightanswer().split(";");
        int numofquestion = answer.length;
        String[] QuestionKey = examresultList.get(0).getRightanswer().split(";");
        QuestionKey.toString();
        String[][] StudentMatrix = new String[numofstudent][numofquestion];
        for (int i = 0; i < examresultList.size(); i++) {
			String[] studentanswer = examresultList.get(i).getExamAnswer().split(";");
			for (int j = 0; j < studentanswer.length; j++) {
				StudentMatrix[i][j] = studentanswer[j];
			}
		}
        double[][] matrixChecking = calculateCheckingCopies(StudentMatrix,QuestionKey);
        String[] StudentID= new String[matrixChecking.length];
          
        for (int i = 0; i < StudentID.length; i++) 
        	StudentID[i]= examresultList.get(i).getStudentID();
        
        for (int i = 0; i < matrixChecking.length; i++) {     	
        	for (int j = 0; j < matrixChecking.length; j++) {
        		Integer ID = examresultList.get(i).getID();
        		Integer ExamNum = examresultList.get(i).getStartexamNum();
				String Examid = examresultList.get(i).getExamID();
				String sql = "INSERT INTO checkingcopies (ExamResultID, startnum,ExamID,"
						+ "Student1ID,Student2ID,SimilarPrecent) VALUES (?, ?, ?, ?, ?, ?)";
				Object[] param= {ID,ExamNum,Examid,StudentID[i],StudentID[j],matrixChecking[i][j]};
				db.InsertCheckingCopies(sql, param);
			}
		}
        client.sendToClient("checking Exams for " + input[1] +" ID done");
	}
    
    /**
     * Calculates the similarity percentages between pairs of students' answers and constructs a matrix.
     *
     * @param StudentAnswer  The matrix containing students' answers.
     * @param AnswerKey      The array containing the correct answer key.
     * @return               The matrix of similarity percentages.
     */
    public double[][] calculateCheckingCopies(String[][] StudentAnswer, String[] AnswerKey) {
    	int n = StudentAnswer.length;
        int m = StudentAnswer[0].length;
        double[][] matrixChecking = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int similarWrongAnswers = 0;
                int totalSameQuestions = 0;
                for (int k = 0; k < m; k++) {
                    if (!StudentAnswer[i][k].equals(AnswerKey[k]) && StudentAnswer[i][k].equals(StudentAnswer[j][k])&& !StudentAnswer[i][k].equals("No Answer")) {
                        similarWrongAnswers++;
                    }
                    if (StudentAnswer[i][k].equals(StudentAnswer[j][k])) {
                        totalSameQuestions++;
                    }
                }
                double percent;
                if (totalSameQuestions == 0) {
                    percent = 0;
                } else {
                    percent = (double) similarWrongAnswers / m * 100;
                }
                matrixChecking[i][j] = percent;
                matrixChecking[j][i] = percent;
            }
        }
        return matrixChecking;
    }
    /**
     * Shows the exam statistics.
     *
     * @param examId   The exam code.
     * @param lecturerId   The client IP address.
     */
    public void showexamstatic(String examId, String lecturerId) {
        String checkQuery = "SELECT * FROM examresult WHERE ExamID = ? AND lecturerID = ?";
        Object[] checkParams = {examId, lecturerId};
        List<Map<String, Object>> examResultData = db.getDataFromMySQL(checkQuery, checkParams);
        List<examresult> examResultList = new ArrayList<>();
        for (Map<String, Object> row : examResultData) {
            examresult examResult = examresult.convertToExamResult(row);
            examResultList.add(examResult);
        }
        int size = examResultList.size();
        int[] grades = new int[size]; // Initialize grades array with the size of examResultList
        int[] frequency = new int[10]; // Initialize frequency array with 10 elements
        int i = 0;
        for (examresult result : examResultList) {
            String examResult = result.getExamResult();
            int grade = Integer.parseInt(examResult);
            int index = grade / 10; // Determine the range index for the grade
            if (index == 10) {
                frequency[9]++;
            } else {
                frequency[index]++; // Increment the count for the corresponding range
            }
            grades[i] = grade;
            i++;
        }     
        Arrays.sort(grades);
        double median;
        if (grades.length % 2 == 0) {
            median = (grades[grades.length / 2 - 1] + grades[grades.length / 2]) / 2.0;
        } else {
            median = grades[grades.length / 2];
        }
        
        // Calculate average
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        double average = (double) sum / grades.length;
        
        // Find highest grade
        int highestGrade = grades[grades.length - 1];
        
        // Find lowest grade
        int lowestGrade = grades[0];
        
        String sql = "INSERT INTO examstatistic (ExamID, LecturerID, Average, Median, `Lowest Grade`,"
        		+ " `Highest Grade`, `0-9`, `10-19`, `20-29`, `30-39`, `40-49`, `50-59`, `60-69`, `70-79`"
        		+ ", `80-89`, `90-100`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {examId, lecturerId, average, median, lowestGrade,
        		highestGrade, frequency[0], frequency[1], frequency[2], frequency[3], frequency[4], frequency[5], frequency[6], frequency[7], frequency[8], frequency[9]};
        db.InsertCheckingCopies(sql, params);

    }


}


