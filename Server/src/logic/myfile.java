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
 * Represents a file with associated information and data.
 */
public class myfile implements Serializable {
	   /**
     * The action to perform on the file.
     */
    private String WhatToDo;
    
    /**
     * The name of the file.
     */
    private String fileName;
    
    /**
     * The file path.
     */
    private String path;
    
    /**
     * The file type.
     */
    private String type;
    
    /**
     * The byte array representing the file data.
     */
    private byte[] fileData;

  
    /**
     * Constructs a new myfile object with the specified parameters.
     *
     * @param whatToDo  The action to perform on the file.
     * @param fileName  The name of the file.
     * @param path      The file path.
     * @param type      The file type.
     * @param fileData  The byte array representing the file data.
     */
    public myfile(String whatToDo, String fileName, String path, String type, byte[] fileData) {
		this.WhatToDo = whatToDo;
		this.fileName = fileName;
		this.path = path;
		this.type = type;
		this.fileData = fileData;
	}


    /**
     * Returns the action to perform on the file.
     *
     * @return The action to perform on the file.
     */
	public String getWhatToDo() {
		return WhatToDo;
	}



    /**
     * Sets the action to perform on the file.
     *
     * @param whatToDo The action to set.
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
     * @param fileName The name to set.
     */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	   /**
     * Returns the file path.
     *
     * @return The file path.
     */
	public String getPath() {
		return path;
	}


    /**
     * Sets the file path.
     *
     * @param path The path to set.
     */
	public void setPath(String path) {
		this.path = path;
	}


    /**
     * Returns the file type.
     *
     * @return The file type.
     */
	public String getType() {
		return type;
	}


	   /**
     * Sets the file type.
     *
     * @param type The type to set.
     */
	public void setType(String type) {
		this.type = type;
	}


    /**
     * Returns the byte array representing the file data.
     *
     * @return The file data byte array.
     */
	public byte[] getFileData() {
		return fileData;
	}


    /**
     * Sets the byte array representing the file data.
     *
     * @param fileData The file data byte array to set.
     */
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}


}
