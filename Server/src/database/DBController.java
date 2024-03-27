package database;

import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ocsf.server.ConnectionToClient;

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
 * This class provides methods for interacting with a database.
 * It allows connecting to a database, checking user credentials,
 * saving data, deleting data, retrieving data, editing data, and checking data existence.
 * 
 * @author fadis
 */
public class DBController {
    /**
     * 
     */
    private static Connection conn = null;
    /**
     * Connects to the database using the specified URL, username, and password.
     *
     * @param url      the URL of the database
     * @param username the username for authentication
     * @param password the password for authentication
     */
    public static void ConnectToDB(String url,String username,String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.println("Driver definition succeeded");
        } catch (Exception ex) {
            System.out.println("Driver definition failed");
            ex.printStackTrace();
        }

        try {
            
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("SQL connection succeeded");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }

    /**
     * Checks if the user credentials are valid for the specified role.
     *
     * @param username the username to check
     * @param password the password to check
     * @param role     the role to check against
     * @return true if the user exists and the credentials are valid, false otherwise
     * @throws SQLException if a database access error occurs
     */
    public static boolean checkUserCredentials(String username, String password, String role) throws SQLException {
        boolean userExists = false;

        String query = "SELECT * FROM "+role+" WHERE username = ? AND password = ? ";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userExists = true;
                }
            }
        }

        return userExists;
    }

    /**
     * Closes the connection to the database.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
            System.out.println("SQL connection closed.");
        }
    }
    
    
    /**
     * Saves data into the database.
     *
     * @param sql    the SQL query to execute for saving the data
     * @param params the parameters to be set in the SQL query
     * @param client the client connection to send the result status
     */
    public static void saveData(String sql, Object[] params,ConnectionToClient client) {
        try {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement(sql);
                // Set the parameter values
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }

                // Execute the SQL statement
                statement.executeUpdate();

                System.out.println("Data saved successfully!");
                try {
					client.sendToClient("SqlOperationSuccss");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
                System.out.println("Connection is not established.");
                try {
					client.sendToClient("SqlOperationfail");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes data from the database.
     *
     * @param sql    the SQL query to execute for deleting the data
     * @param params the parameters to be set in the SQL query
     * @param client the client connection to send the result status
     */
    public static void deleteData(String sql, Object[] params,ConnectionToClient client) {
        try {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement(sql);
                // Set the parameter values
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }

                // Execute the SQL statement
                statement.executeUpdate();

                System.out.println("Data deleted successfully!");
                try {
					client.sendToClient("SqlOperationSuccss");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
                System.out.println("Connection is not established.");
                try {
					client.sendToClient("SqlOperationfail");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
   
    
    /**
     * Retrieves data from the database.
     *
     * @param sql    the SQL query to execute for retrieving the data
     * @param params the parameters to be set in the SQL query
     * @param client the client connection to send the retrieved data
     */
    public static void getdata(String sql, Object[] params, ConnectionToClient client) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        try {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement(sql);
                // Set the parameter values
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }

                // Execute the SQL statement
                ResultSet resultSet = statement.executeQuery();

                // Retrieve column names
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                List<String> columnNames = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    columnNames.add(metaData.getColumnName(i));
                }

                // Retrieve data rows
                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (String columnName : columnNames) {
                        row.put(columnName, resultSet.getObject(columnName));
                    }
                    resultList.add(row);
                }

                resultSet.close();
                statement.close();
            } else {
                System.out.println("Connection is not established.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            client.sendToClient(resultList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Edits existing data in the database.
     *
     * @param sql    the SQL query to execute for editing the data
     * @param params the parameters to be set in the SQL query
     * @param client the client connection to send the result status
     */
    public static void editData(String sql, Object[] params,ConnectionToClient client) {
        try {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement(sql);
                // Set the parameter values
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }

                // Execute the SQL statement
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data edited successfully!");
                    try {
						client.sendToClient("SqlOperationSuccss");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                } else {
                    System.out.println("No data was edited.");
                    try {
						client.sendToClient("SqlOperationfail");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            } else {
                System.out.println("Connection is not established.");
                try {
					client.sendToClient("SqlOperationfail");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Checks if the data exists in the database.
     *
     * @param sql     the SQL query to execute for checking data existence
     * @param params  the parameters to be set in the SQL query
     * @param client  the client connection to send the result status
     */
    public static void checkDataExists(String sql, Object[] params, ConnectionToClient client) {
        try {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement(sql);

                // Set the parameter values
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }

                // Execute the SQL statement
                ResultSet rs = statement.executeQuery();

                // Check if data exists
                if (rs.next()) {
                    try {
                        client.sendToClient("DataExist");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }  
                } else {
                    try {
                        client.sendToClient("DataNotExist");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                rs.close();
                statement.close();
            } else {
                System.out.println("Connection is not established.");
                try {
                    client.sendToClient("DataNotExist");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieves data from the MySQL database based on the provided SQL query and parameters.
     *
     * @param sql     the SQL query to execute for retrieving data
     * @param params  the parameters to be set in the SQL query
     * @return a list of maps representing the retrieved data
     */
    public List<Map<String, Object>> getDataFromMySQL(String sql, Object[] params) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            // Set the parameter values
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            // Execute the SQL statement
            try (ResultSet resultSet = statement.executeQuery()) {
                // Retrieve column names
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                List<String> columnNames = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    columnNames.add(metaData.getColumnName(i));
                }

                // Retrieve data rows
                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (String columnName : columnNames) {
                        row.put(columnName, resultSet.getObject(columnName));
                    }
                    resultList.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }
    
    
    /**
     * Executes an SQL query for inserting data into the MySQL database based on the provided SQL query and parameters.
     *
     * @param sql     the SQL query to execute for inserting data
     * @param params  the parameters to be set in the SQL query
     * @return true if the insertion was successful, false otherwise
     */
    public boolean InsertCheckingCopies(String sql, Object[] params) {
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            // Set the parameter values
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            
            // Execute the SQL statement
            int rowsAffected = statement.executeUpdate();

            // Check if any rows were affected
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Closes the ResultSet and PreparedStatement objects.
     *
     * @param rs   the ResultSet to be closed
     * @param stmt the PreparedStatement to be closed
     */
    private void closeResources(ResultSet rs, PreparedStatement stmt) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieves a single column of integers from the MySQL database based on the provided SQL query.
     *
     * @param sqlQuery the SQL query to execute for retrieving the column of integers
     * @return an array of integers containing the retrieved values
     */
    public int[] getrangresult(String sqlQuery) {
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);
             ResultSet rs = stmt.executeQuery()) {
            // Determine the number of rows in the result set
            rs.last();
            int numRows = rs.getRow();
            rs.beforeFirst();
            // Create an array to store the result
            int[] result = new int[numRows];
            // Retrieve the values from the result set and store them in the array
            int index = 0;
            while (rs.next()) {
                result[index++] = rs.getInt(1); // Assuming the result contains a single column of integers
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new int[0]; // Return an empty array if an error occurs
    }
    
}
