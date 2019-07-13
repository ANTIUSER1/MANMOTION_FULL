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
    private String selectedFileName;
    private File selecDir;

    public String getSelectedFileName() {
        return selectedFileName;
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
            selectedFile = fileChooser.showOpenDialog(pns.VidController.MainVController.getStage());
            selectedFileName = selectedFile.getAbsolutePath();
            selecDir = selectedFile.getParentFile();
            fileChooser.setInitialDirectory(selecDir);
            fileDataRead();
        } catch (NullPointerException e) {
        }
        fileChooser.getExtensionFilters().clear();
    }

    public void fileDataRead() {
        try {
            ImportTXT importTXT = ImportTXT.getInstance();
            importTXT.setFileName(selectedFile.getAbsolutePath());
            selectedFileContent = importTXT.readFile();

        } catch (NullPointerException e) {
        }
    }
}
