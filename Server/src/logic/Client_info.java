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
 * The `Client_info` class represents information about a client.
 * It stores details such as the client's host, IP address, and status.
 * 
 * This class implements the `Serializable` interface to allow for object serialization.
 * 
 * The class provides methods to retrieve and set the values of its properties.
 * 
 */
public class Client_info implements Serializable {
	  /**
		 * The serial version UID for serialization.
		 */
	private static final long serialVersionUID = 1L;
	/**
	 * The host of the client.
	 */
	private String host;
	/**
	 * The IP address of the client.
	 */
    private String ip;
    /**
     * The status of the client.
     */
    private String status;

    /**
     * Constructs a new `Client_info` object with the specified values.
     * 
     * @param host    The host of the client.
     * @param ip      The IP address of the client.
     * @param status  The status of the client.
     */
    public Client_info(String host, String ip, String status) {
    	this.ip = ip;
    	this.host = host;
        this.status = status;
    }

    /**
     * Returns the host of the client.
     * 
     * @return The host.
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the host of the client.
     * 
     * @param host The host to set.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Returns the IP address of the client.
     * 
     * @return The IP address.
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets the IP address of the client.
     * 
     * @param ip The IP address to set.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Returns the status of the client.
     * 
     * @return The status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the client.
     * 
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
