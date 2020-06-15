import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadWrite {

    public static String readFile(String path, MainWindow mainWindow) {
        String message = "";
        try {
            message = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            mainWindow.errorMessage("Error reading file path");
        }
        return message;
    }

    public static String readFile(String path) {
        String message = "";
        try {
            message = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            System.out.println("Error reading file path");
        }
        return message;
    }

    public static void writeFile(String path, String message, MainWindow mainWindow) {
        File file = new File(path);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(message);
        } catch (IOException e) {
            mainWindow.errorMessage("Error writing into file");
        }
    }

    public static void writeFile(String path, String message) {
        File file = new File(path);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(message);
        } catch (IOException e) {
            System.out.println("Error writing into file");
        }
    }

    public static String getPath(MainWindow mainWindow, String actionMessage) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setDialogTitle(actionMessage);
        int result = fileChooser.showOpenDialog(mainWindow);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        } else {
            return null;
        }
    }
}
