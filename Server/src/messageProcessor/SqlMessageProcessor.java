package messageProcessor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import database.DBController;
import gui.ServerGUI;
import logic.sqlmessage;
import ocsf.server.ConnectionToClient;
import server.CemsServer;
import java.io.PrintWriter;

/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * The SqlMessageProcessor class processes SQL messages received from clients and performs the corresponding actions on the database.
 */
public class SqlMessageProcessor {
	   /**
     * The number of the current question.
     */
	public static int QuestionNum=0;
    /**
     * The number of the current exam.
     */
	public static int ExamNum=0;
    /**
     * Processes the SQL message and performs the corresponding database operation.
     *
     * @param sql        The SQL message to be processed.
     * @param serverGUI  The server's GUI object.
     * @param sv         The CemsServer object.
     * @param client     The connection to the client.
     */
	public void processMessage(sqlmessage sql,ServerGUI serverGUI,CemsServer sv,ConnectionToClient client)
	{
		
		//INSERT INTO question (
		DBController db = new DBController();
		String WhatTodo=sql.getWhatToDO();
		if (WhatTodo.contentEquals("save")) {
			if(sql.getQuery().contains("INSERT INTO exam (")) {
				String ExamNum = getNextValueForQuestionNumORExam("ExamNum.txt");
				String[] stringArray = new String[sql.getParameter().length];
				for (int i = 0; i < sql.getParameter().length; i++) {
				    stringArray[i] = String.valueOf(sql.getParameter()[i]);
				}
				String FinalID = stringArray[0] + ExamNum;
				//Object[] sourceArray = { "Hello", 123, true };
				Object[] destinationArray = Arrays.copyOf(sql.getParameter(), sql.getParameter().length);
				destinationArray[0]=FinalID;
				sql.setParameter(destinationArray);
			}
			else if(sql.getQuery().contains("INSERT INTO question (")) {
				String ExamNum = getNextValueForQuestionNumORExam("QuestionNum.txt");
				String[] stringArray = new String[sql.getParameter().length];
				for (int i = 0; i < sql.getParameter().length; i++) {
				    stringArray[i] = String.valueOf(sql.getParameter()[i]);
				}
				String FinalID = stringArray[0] + ExamNum;
				//Object[] sourceArray = { "Hello", 123, true };
				Object[] destinationArray = Arrays.copyOf(sql.getParameter(), sql.getParameter().length);
				destinationArray[0]=FinalID;
				sql.setParameter(destinationArray);
			}
			//else if(sql.getQuery().contains("INSERT INTO answer")) {}
			db.saveData(sql.getQuery(), sql.getParameter(),client);
		}
		else if (WhatTodo.contentEquals("delete")) {
			db.deleteData(sql.getQuery(), sql.getParameter(),client);
		}
		else if (WhatTodo.contentEquals("edit")) {
			db.editData(sql.getQuery(), sql.getParameter(),client);
		}
		else if (WhatTodo.contentEquals("check")) {
			db.checkDataExists(sql.getQuery(), sql.getParameter(),client);
		}
		else if(WhatTodo.contentEquals("get")) {
			db.getdata(sql.getQuery(), sql.getParameter(),client);
		}
	}
    /**
     * Processes the SQL message and performs the corresponding database operation.
     *
     * @param e          The object associated with the SQL message.
     * @param sql        The SQL message to be processed.
     * @param serverGUI  The server's GUI object.
     * @param sv         The CemsServer object.
     * @param client     The connection to the client.
     */
	public void processMessage(Object e,sqlmessage sql,ServerGUI serverGUI,CemsServer sv,ConnectionToClient client)
	{
		DBController db = new DBController();
		db.ConnectToDB(serverGUI.db_name, serverGUI.username, serverGUI.password);
		String WhatTodo=sql.getWhatToDO();
		if (WhatTodo.contentEquals("save")) {
			db.saveData(sql.getQuery(), sql.getParameter(),client);
		}
	}
	
    /**
     * Retrieves the next value for the question number or exam number from a file.
     *
     * @param filename The name of the file to read.
     * @return The next value for the question number or exam number.
     */
	public synchronized String getNextValueForQuestionNumORExam(String filename) {
        try {
            File file = new File(filename);
            
            if (!file.exists()) {
                file.createNewFile();
                if(filename.equals("QuestionNum.txt"))
                	QuestionNum = 0;
                else
                	ExamNum = 0;
            } else {
                Scanner scanner = new Scanner(file);
                String line = scanner.nextLine();
                scanner.close();
                if(filename.equals("QuestionNum.txt")) {
                	if (line.isEmpty()) {
                        QuestionNum = 0;
                    } else {
                        QuestionNum = Integer.valueOf(line);
                        QuestionNum++;
                    }
                }
                else {
                	if (line.isEmpty()) {
                		ExamNum = 0;
                    } else {
                    	ExamNum = Integer.valueOf(line);
                    	ExamNum++;
                    }
                }
                
            }
            
            PrintWriter printWriter = new PrintWriter(new FileWriter(file));
            if(filename.equals("QuestionNum.txt"))
            	printWriter.print(String.format("%03d", QuestionNum));
            else
            	printWriter.print(String.format("%02d", ExamNum));
            printWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(filename.equals("QuestionNum.txt"))
        	return String.format("%03d", QuestionNum);
        else
        	return String.format("%02d", ExamNum);
    }
	

	
}
