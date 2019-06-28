/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.files;

import java.io.File;
import javafx.stage.FileChooser;
import pns.api.fileimports.ImportTXT;

/**
 *
 * @author Movement
 */
public class OpenFileChoser {

    private FileChooser fileChooser = new FileChooser();

    private File selectedFile;
    private String selectedFileContent;
    private File selecDir;

    public String getSelectedFile() {
        try {
            return selectedFile.getAbsolutePath();
        } finally {
            return null;
        }
    }

    public String getSelectedFileContent() {
        return selectedFileContent;
    }

    public void fileBroseDLG() {
        fileChooser.setTitle("Open Text Data");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.csv"),
                new FileChooser.ExtensionFilter("Data Files", "*.dat"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        try {
            selectedFile = fileChooser.showOpenDialog(pns.VidController.MainVController.stage);
            selecDir = selectedFile.getParentFile();
            fileChooser.setInitialDirectory(selecDir);
            System.out.println("nnnnnnnnn " + (selectedFile == null));
            fileDataRead();
        } catch (NullPointerException e) {
        }
        fileChooser.getExtensionFilters().clear();
    }

    public void fileDataRead() {
        try {
            ImportTXT importTXT = new ImportTXT(selectedFile.getAbsolutePath());
            selectedFileContent = importTXT.readFile();
        } catch (NullPointerException e) {
        }
    }
}
