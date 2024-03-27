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
 * Represents a file.
 */
public class myfile implements Serializable {
    /**
     * The action to perform with the file.
     */
    private String WhatToDo;

    /**
     * The name of the file.
     */
    private String fileName;

    /**
     * The path of the file.
     */
    private String path;

    /**
     * The type of the file.
     */
    private String type;

    /**
     * The data of the file.
     */
    private byte[] fileData;

    /**
     * Constructs a new instance of the myfile class.
     *
     * @param whatToDo   The action to perform with the file.
     * @param fileName   The name of the file.
     * @param path       The path of the file.
     * @param type       The type of the file.
     * @param fileData   The data of the file.
     */
    public myfile(String whatToDo, String fileName, String path, String type, byte[] fileData) {
		this.WhatToDo = whatToDo;
		this.fileName = fileName;
		this.path = path;
		this.type = type;
		this.fileData = fileData;
	}


    /**
     * Returns the action to perform with the file.
     *
     * @return The action to perform with the file.
     */
	public String getWhatToDo() {
		return WhatToDo;
	}


    /**
     * Sets the action to perform with the file.
     *
     * @param whatToDo The action to perform with the file.
     */
	public void setWhatToDo(String whatToDo) {
		WhatToDo = whatToDo;
	}


    /**
     * Returns the name of the file.
     *
     * @return The name of the file.
     */
	public String getFileName() {
		return fileName;
	}



    /**
     * Sets the name of the file.
     *
     * @param fileName The name of the file.
     */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


    /**
     * Returns the path of the file.
     *
     * @return The path of the file.
     */
	public String getPath() {
		return path;
	}



    /**
     * Sets the path of the file.
     *
     * @param path The path of the file.
     */
	public void setPath(String path) {
		this.path = path;
	}


	   /**
     * Returns the type of the file.
     *
     * @return The type of the file.
     */
	public String getType() {
		return type;
	}


    /**
     * Sets the type of the file.
     *
     * @param type The type of the file.
     */
	public void setType(String type) {
		this.type = type;
	}


	   /**
     * Returns the data of the file.
     *
     * @return The data of the file.
     */
	public byte[] getFileData() {
		return fileData;
	}


    /**
     * Sets the data of the file.
     *
     * @param fileData The data of the file.
     */
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}


}
