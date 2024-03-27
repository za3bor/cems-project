package logic;

import java.io.Serializable;
import java.net.InetAddress;
/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * The `Client_info` class represents information about a client.
 * It contains the host, IP address, and status of the client.
 */
public class Client_info implements Serializable {
    /**
     * The host name of the client.
     */
    private String host;

    /**
     * The IP address of the client.
     */
    private InetAddress ip;

    /**
     * The status of the client.
     */
    private String status;

    /**
     * Constructs a `Client_info` object with the specified host, IP address, and status.
     *
     * @param host   The host name of the client.
     * @param ip     The IP address of the client.
     * @param status The status of the client.
     */
    public Client_info(String host, InetAddress ip, String status) {
        this.host = host;
        this.ip = ip;
        this.status = status;
    }
    /**
     * Returns the host name of the client.
     *
     * @return The host name of the client.
     */
    public String getHost() {
        return host;
    }
    /**
     * Sets the host name of the client.
     *
     * @param host The host name of the client.
     */
    public void setHost(String host) {
        this.host = host;
    }
    /**
     * Returns the IP address of the client.
     *
     * @return The IP address of the client.
     */
    public InetAddress getIp() {
        return ip;
    }
    /**
     * Sets the IP address of the client.
     *
     * @param ip The IP address of the client.
     */
    public void setIp(InetAddress ip) {
        this.ip = ip;
    }
    /**
     * Returns the status of the client.
     *
     * @return The status of the client.
     */
    public String getStatus() {
        return status;
    }
    /**
     * Sets the status of the client.
     *
     * @param status The status of the client.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
