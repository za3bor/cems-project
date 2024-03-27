package gui;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.OutputStream;
import java.io.PrintStream;
/**
 * 
 * @author Fadi Srour
 * @author Qotiba Mhamed
 * @author Abdallha Amarya 
 * @author Ali Mohsen
 * @author Mohammad khamaisy
 * @author Mohammed yassin
 * @author Fawzi abo zhaya
 * @version 20/6/2023
 * 
 * The ConsoleOutput class extends PrintStream and redirects the output to a TextArea control in a JavaFX application.
 * This allows the application to display console output in the TextArea.
 * 
 * Usage:
 * To redirect console output to a TextArea, create an instance of ConsoleOutput by passing the TextArea control as a parameter.
 * Then, set the newly created ConsoleOutput instance as the System.out stream.
 * 
 * Example:
 * TextArea consoleTextArea = new TextArea();
 * ConsoleOutput consoleOutput = new ConsoleOutput(consoleTextArea);
 * System.setOut(consoleOutput);
 * 
 * Now, any output written to System.out will be displayed in the TextArea control.
 * 
 * Note:
 * This class should be used in a JavaFX application as it relies on the Platform.runLater() method to update the UI.
 * 
 * @author fadis
 */
public class ConsoleOutput extends PrintStream {
	 /**
     * The TextArea control where the console output will be displayed.
     */
    private TextArea consoleArea;
    /**
     * Constructs a new ConsoleOutput instance with the specified TextArea control.
     * 
     * @param consoleArea the TextArea control to display console output
     */
    public ConsoleOutput(TextArea consoleArea) {
        super(new ConsoleOutputStream(consoleArea));
        this.consoleArea = consoleArea;
    }
    /**
     * The ConsoleOutputStream class extends OutputStream and writes the output to the associated TextArea control.
     * It uses the Platform.runLater() method to update the UI from a non-UI thread.
     */
    private static class ConsoleOutputStream extends OutputStream {
        /**
         * The TextArea control where the console output will be displayed.
         */
        private TextArea consoleArea;

        /**
         * Constructs a new ConsoleOutputStream instance with the specified TextArea control.
         * 
         * @param consoleArea the TextArea control to display console output
         */
        public ConsoleOutputStream(TextArea consoleArea) {
            this.consoleArea = consoleArea;
        }
        /**
         * Writes a byte to the output stream.
         * 
         * @param b the byte to write
         */
        @Override
        public void write(int b) {
            Platform.runLater(() -> consoleArea.appendText(String.valueOf((char) b)));
        }
    }
}
