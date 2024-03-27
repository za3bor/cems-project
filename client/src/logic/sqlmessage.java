package logic;

import java.io.Serializable;
/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * Represents an SQL message used for database operations.
 * This class encapsulates the information required to perform SQL operations such as saving, deleting, editing, getting, or checking data.
 */
public class sqlmessage implements Serializable {
    /**
     * The operation to be performed. Choose one from {save, delete, edit, get, check}.
     */
	private String whatToDO;
    /**
     * The SQL query to be executed.
     */
	private String query;
	   /**
     * The parameters to be set in the SQL query.
     */
	private Object[] parameter;
    /**
     * Constructs a new SQL message with the specified parameters.
     *
     * @param whatToDO   The operation to be performed.
     * @param query      The SQL query to be executed.
     * @param parameter  The parameters to be set in the SQL query.
     */
    public sqlmessage(String whatToDO, String query, Object[] parameter) {
        this.whatToDO = whatToDO;
        this.query = query;
        this.parameter = parameter;
    }
    /**
     * Retrieves the operation to be performed.
     *
     * @return The operation to be performed.
     */
    public String getWhatToDO() {
        return whatToDO;
    }
    /**
     * Sets the operation to be performed.
     *
     * @param whatToDO The operation to be performed.
     */
    public void setWhatToDO(String whatToDO) {
        this.whatToDO = whatToDO;
    }
    /**
     * Retrieves the SQL query to be executed.
     *
     * @return The SQL query to be executed.
     */
    public String getQuery() {
        return query;
    }
    /**
     * Sets the SQL query to be executed.
     *
     * @param query The SQL query to be executed.
     */
    public void setQuery(String query) {
        this.query = query;
    }
    /**
     * Retrieves the parameters to be set in the SQL query.
     *
     * @return The parameters to be set in the SQL query.
     */
    public Object[] getParameter() {
        return parameter;
    }
    /**
     * Sets the parameters to be set in the SQL query.
     *
     * @param parameter The parameters to be set in the SQL query.
     */
    public void setParameter(Object[] parameter) {
        this.parameter = parameter;
    }
}
