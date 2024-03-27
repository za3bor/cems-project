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
 * 
 * Represents a SQL message entity.
 *
 * This class stores information about a SQL message, including the action to be performed (e.g., save, delete, edit, get, check),
 * the SQL query to execute, and the parameters to set in the SQL query.
 *
 * The class provides methods to get and set the properties.
 *
 * Note: This class assumes that the SQL query is already sanitized and properly formatted to prevent SQL injection attacks.
 * Make sure to properly sanitize and validate the input before constructing a `sqlmessage` object.
 *
 * @author fadis
 *
 */
public class sqlmessage implements Serializable {
    /**
     * The action to be performed.
     * Choose one from {save, delete, edit, get, check}.
     */    
	private String whatToDO;
     /**
      * The SQL query to execute.
      */    
	private String query;
    /**
     * The parameters to set in the SQL query.
     */    
	private Object[] parameter;
	   /**
     * Constructs a `sqlmessage` object with the specified parameters.
     *
     * @param whatToDO The action to be performed.
     * @param query The SQL query to execute.
     * @param parameter The parameters to set in the SQL query.
     */
    public sqlmessage(String whatToDO, String query, Object[] parameter) {
        this.whatToDO = whatToDO;
        this.query = query;
        this.parameter = parameter;
    }
    /**
     * Returns the action to be performed.
     *
     * @return The action to be performed.
     */
    public String getWhatToDO() {
        return whatToDO;
    }
    /**
     * Sets the action to be performed.
     *
     * @param whatToDO The action to be performed.
     */
    public void setWhatToDO(String whatToDO) {
        this.whatToDO = whatToDO;
    }
    /**
     * Returns the SQL query to execute.
     *
     * @return The SQL query to execute.
     */
    public String getQuery() {
        return query;
    }
    /**
     * Sets the SQL query to execute.
     *
     * @param query The SQL query to execute.
     */
    public void setQuery(String query) {
        this.query = query;
    }
    /**
     * Returns the parameters to set in the SQL query.
     *
     * @return The parameters to set in the SQL query.
     */
    public Object[] getParameter() {
        return parameter;
    }
    /**
     * Sets the parameters to set in the SQL query.
     *
     * @param parameter The parameters to set in the SQL query.
     */
    public void setParameter(Object[] parameter) {
        this.parameter = parameter;
    }
}
