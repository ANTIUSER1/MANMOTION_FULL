/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author Movement
 */
public class OpenDirChoser {

    private DirectoryChooser dirChooser = new DirectoryChooser();
    private String selectedFileName;
    private File selectedDir;
    private List<File> dirContent = new ArrayList<>();

    public String getSelectedFileName() {
        return selectedFileName;
    }

    public File getSelectedDir() {
        return selectedDir;
    }

    public List<File> getDirContent() {
        return dirContent;
    }

    public void dirBroseDLG() {
        dirContent.clear();
        dirChooser.setTitle(" Open Directory to Browse  ");

        selectedDir = dirChooser.showDialog(pns.VidController.MainVController.getStage());
        if (selectedDir != null) {
            File[] fl = selectedDir.listFiles();
            for (File f : fl) {
                dirContent.add(f);
            }
        }
        dirDataRead();
        selectedFileName = selectedDir.getAbsolutePath();

    }

    private void dirDataRead() {

    }

}
