package messageProcessor;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * The FileHandler class provides methods to handle file operations.
 */
public class FileHandler {
    /**
     * The folder name for lecturer files.
     */
    private static final String LECTURER_FOLDER_NAME = "exam/";
    /**
     * The folder name for student files.
     */
    private static final String STUDENT_FOLDER_NAME = "exam_student/";
    /**
     * Saves a file to the specified file path.
     *
     * @param filePath  The file path to save the file to.
     * @param fileData  The byte array representing the file data.
     * @param fileType  The type of the file (either "lecturer" or "student").
     * @param filename  The name of the file to be saved.
     * @return `true` if the file is saved successfully, `false` otherwise.
     */
    public static boolean saveFile(String filePath, byte[] fileData, String fileType,String filename) {
        String saveFolderPath;

        if (fileType.equals("lecturer")) {
            saveFolderPath = LECTURER_FOLDER_NAME;
        } else if (fileType.equals("student")) {
            saveFolderPath = STUDENT_FOLDER_NAME + filePath;
        } else {
            // Invalid file type
            return false;
        }

        String saveFilePath = saveFolderPath+filename;
        try {
            // Create the save folder if it doesn't exist
            Path saveFolder = Paths.get(saveFolderPath);
            if (!Files.exists(saveFolder)) {
                Files.createDirectories(saveFolder);
            }

            // Create BufferedOutputStream to improve performance
            FileOutputStream fileOutputStream = new FileOutputStream(saveFilePath);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            // Write the file data to the output stream
            bufferedOutputStream.write(fileData);

            // Flush and close the streams
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

            // File saved successfully
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            // Failed to save the file
            return false;
        }
    }

    /**
     * Retrieves the byte array of the file with the specified file name.
     *
     * @param fileName The name of the file to retrieve.
     * @return The byte array representing the file data, or `null` if the file is not found or an error occurs.
     */
    public static byte[] getFileData(String fileName) {
        Path filePath = Paths.get(fileName);
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
