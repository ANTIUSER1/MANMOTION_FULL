package pns.VidController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pns.VidController.files.OpenDirChoser;
import pns.VidController.files.OpenFileChoser;
import pns.VidController.manparts.motions.MotionHands;
import pns.VidController.manparts.motions.MotionLegs;
import pns.datatools.DataReciever;
import pns.start.Main;

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

    private MotionTools toolMethods = new MotionTools();

    private DrawingLimbController drawingLimbController;
    private DataReciever dataReciever = DataReciever.getInstance();

    public static void fixStage(Stage st) {
        stage = st;
    }

    public static Stage getStage() {
        return stage;
    }

    public TextArea getTxtArea() {
        return txtArea;
    }

    @FXML
    private void closeApp(ActionEvent event) {
        MotionLegs.taskClose();
        MotionHands.taskClose();
        Platform.exit();
    }

    @FXML
    public void openFileOpenDLG() {
//        DrawingLimbController.taskClose();

        openFileChoser.fileBroseDLG();
        if (openFileChoser.getSelectedFileContent() != null) {
            statusFile.setText("Opened file  " + openFileChoser.getSelectedFileName());
            //  System.out.println("  openFileChoser.getSelectedFile() len   " + openFileChoser.getSelectedFileName());
            txtArea.setText(openFileChoser.getSelectedFileContent());
            dataReciever.setData(txtArea.getText());
            //
            try {
                openDrawWindow();
            } catch (IOException ex) {
                Logger.getLogger(MainVController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void openDirOpenDLG() {
//        DrawingLimbController.taskClose();

        openDirChoser.dirBroseDLG();
        if (openDirChoser.getSelectedFileName() != null) {
            statusFile.setText("Opened folder   " + openDirChoser.getSelectedFileName() + " with " + openDirChoser.getDirContent().size() + " files, including directories ");
        }
    }

    @FXML
    public void openDrawWindow() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/DrawWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400, true);

        Stage drawWindow = new Stage();

        drawWindow.setWidth(.44 * Main.screenDimFind().getWidth());
        drawWindow.setHeight(0.73 * Main.screenDimFind().getHeight());

        drawWindow.setTitle("Draw Window");
        drawWindow.setScene(scene);
        drawWindow.initModality(Modality.APPLICATION_MODAL);
        drawWindow.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openDirChoser = new OpenDirChoser();
        openFileChoser = new OpenFileChoser();
        drawingLimbController = new DrawingLimbController();
        //       System.out.println("            stage.getWidth()  " + (stage == null));
    }

}
