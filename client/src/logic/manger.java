package logic;

import java.util.Map;
/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * Represents a manager.
 * This class provides methods to create and convert manager objects.
 * 
 * Note: "manger" might be a typo, it's usually spelled as "manager".
 * Please verify the spelling and adjust the class name accordingly.
 */
public class manger {
    /**
     * The ID of the manager.
     */
    private String ID;
    
    /**
     * The singleton instance of the manger class.
     */
    private static manger instance;
    /**
     * Constructs a new manger object with the given ID.
     *
     * @param ID The ID of the manager.
     */
	private manger(String ID) {
		this.ID=ID;
	}
    /**
     * Retrieves the singleton instance of the manger class.
     *
     * @param ID The ID of the manager.
     * @return The singleton instance of the manger class.
     */
	public static manger getInstance (String ID) {
		 instance = new manger (ID);
	     return instance;

	}
    /**
     * Converts a map into a manger object.
     *
     * @param row The map containing the manger information.
     * @return The converted manger object.
     */
	public static manger convertToManger(Map<String, Object> row) {
		String ID = (String) row.get("ID");
		return new manger(ID);
	}
    /**
     * Retrieves the ID of the manager.
     *
     * @return The ID of the manager.
     */
	public String getID() {
		return ID;
	}
    /**
     * Sets the ID of the manager.
     *
     * @param iD The ID of the manager.
     */
	public void setID(String iD) {
		ID = iD;
	}
}
