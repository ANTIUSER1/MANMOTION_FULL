package pns.VidController;

import draw.ConvertToSegment;
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

    @FXML
    private void closeApp(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void openFileOpenDLG() {
        openFileChoser.fileBroseDLG();
        if (openFileChoser.getSelectedFileContent() != null) {
            statusFile.setText(openFileChoser.getSelectedFile());
            txtArea.setText(openFileChoser.getSelectedFileContent());
            toSegment.convert(openFileChoser.getSelectedFileContent());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openFileChoser = new OpenFileChoser();

    }
}
