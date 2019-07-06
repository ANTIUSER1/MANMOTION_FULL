package pns.VidController;

import datatools.ConvertToSegment;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import pns.VidController.files.OpenDirChoser;
import pns.VidController.files.OpenFileChoser;

public class MainVController implements Initializable {

    private static Stage stage;

    @FXML
    private Label statusFile;
    @FXML
    private Button closeButton;
    @FXML
    private TextArea txtArea;

    private OpenFileChoser openFileChoser;
    private OpenDirChoser openDirChoser;

    private ConvertToSegment ctoSegment = ConvertToSegment.getInstance();
    private MotionTools toolMethods = new MotionTools();

    public static void fixStage(Stage st) {
        stage = st;
    }

    public static Stage getStage() {
        return stage;
    }

    @FXML
    private void closeApp(ActionEvent event) {
        toolMethods.setCycleRunFoward(false);
        DrawingLimbController.taskClose();
        Platform.exit();
    }

    @FXML
    public void openFileOpenDLG() {
        DrawingLimbController.taskClose();

        openFileChoser.fileBroseDLG();
        if (openFileChoser.getSelectedFileContent() != null) {
            statusFile.setText("Opened file  " + openFileChoser.getSelectedFileName());
            //  System.out.println("  openFileChoser.getSelectedFile() len   " + openFileChoser.getSelectedFileName());
            txtArea.setText(openFileChoser.getSelectedFileContent());
            ctoSegment.convert(openFileChoser.getSelectedFileContent());
        }
    }

    @FXML
    public void openDirOpenDLG() {
        DrawingLimbController.taskClose();

        openDirChoser.dirBroseDLG();
        if (openDirChoser.getSelectedFileName() != null) {
            statusFile.setText("Opened folder   " + openDirChoser.getSelectedFileName() + " with " + openDirChoser.getDirContent().size() + " files, including directories ");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openDirChoser = new OpenDirChoser();
        openFileChoser = new OpenFileChoser();
        //       System.out.println("            stage.getWidth()  " + (stage == null));
    }
}
