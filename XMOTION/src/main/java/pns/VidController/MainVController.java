package pns.VidController;

import draw.ConvertToSegment;
import draw.DrawingController;
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
import pns.VidController.files.OpenFileChoser;

public class MainVController implements Initializable {

    public static Stage stage;

    @FXML
    private Label statusFile;
    @FXML
    private Button closeButton;
    @FXML
    private TextArea txtArea;

    private OpenFileChoser openFileChoser;
    private ConvertToSegment toSegment = ConvertToSegment.getInstance();
    private Tools toolMethods = new Tools();
    //private DrawingController drawingController = DrawingController.getInstance();

    @FXML
    private void closeApp(ActionEvent event) {
        toolMethods.setCycleRunFoward(false);
        DrawingController.taskClose();
        Platform.exit();
    }

    @FXML
    public void openFileOpenDLG() {
        DrawingController.taskClose();

        openFileChoser.fileBroseDLG();
        if (openFileChoser.getSelectedFileContent() != null) {
            statusFile.setText(openFileChoser.getSelectedFileName());
            System.out.println("  openFileChoser.getSelectedFile() len   " + openFileChoser.getSelectedFileName());
            txtArea.setText(openFileChoser.getSelectedFileContent());
            toSegment.convert(openFileChoser.getSelectedFileContent());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openFileChoser = new OpenFileChoser();

    }
}
