/**
 * ********************************************************************************************************************************************
 *
 * Это контроллер всего приложения
 * Содержит:
 * ПОЛЯ
 *
 * private static Stage stage;   ---ссылка на окно программы, связанное с классом
 * private Label statusFile;    -- информация об открытом файле (его имя)
 * private TextArea txtArea;   --- содержимое открытого файла
 * private OpenFileChoser openFileChoser;   --- инструмент безопасного открывания файла
 * private OpenDirChoser openDirChoser;      --- инструмент безопасного открывания папки
 * private DrawingLimbController drawingLimbController = new DrawingLimbController();----инструмент рисования запчастей
 * private DataReciever dataReciever = DataReciever.getInstance();  -- [hfybntkm исходных данных
 *
 * МЕТОДЫ
 *
 * public static void fixStage(Stage st)    ---  присваивает значение сейжа (из файла Main)
 * public static Stage getStage()   --- выдает стэйж
 * public TextArea getTxtArea()  --выдает поле с содержимым файла
 *
 * @FXML private void closeApp(ActionEvent event) -- выключает приложение
 * @FXML public void openFileOpenDLG() открывает файл и грузит его в текстарею. Дает команду на преобразование содержимого в нужные структуры
 * @FXML public void openDirOpenDLG() открывает директорию и ничего больше (--- заготовка на будущее)
 * @FXML public void openDrawWindow() throws IOException -- открывает окно для рисования на нем
 * @Override public void initialize(URL url, ResourceBundle rb) *
 *
 **********************************************************************************************************************************************************
 */
package pns.VidController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pns.VidController.files.OpenDirChoser;
import pns.VidController.files.OpenFileChoser;
import pns.datatools.DataReciever;
import pns.start.Main;

public class MainVController implements Initializable {

    private static Stage stage;

    @FXML
    private Label statusFile;

    private OpenFileChoser openFileChoser;
    private OpenDirChoser openDirChoser;

    private DrawingLimbController drawingLimbController = new DrawingLimbController();
    private DataReciever dataReciever = DataReciever.getInstance();
    private Stage drawWindow;

    public static void fixStage(Stage st) {
        stage = st;
    }

    public static Stage getStage() {
        return stage;
    }

    @FXML
    private void closeApp(ActionEvent event) {
        closeTasks();
        System.out.println("App exit start");
        Platform.exit();
        System.out.println("App exit END ");

    }

    @FXML
    public void openFileOpenDLG() {
        openFileChoser.fileBroseDLG();
        if (openFileChoser.getSelectedFileContent() != null) {
            statusFile.setText("Opened file  " + openFileChoser.getSelectedFileName());
            try {
                openDrawWindow();
            } catch (Exception ex) {
                //      Logger.getLogger(MainVController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void openDirOpenDLG() {
        openDirChoser.dirBroseDLG();
        if (openDirChoser.getSelectedFileName() != null) {
            statusFile.setText("Opened folder   " + openDirChoser.getSelectedFileName() + " with " + openDirChoser.getDirContent().size() + " files, including directories ");
        }
    }

    @FXML
    public void openDrawWindow() throws Exception {

        URL location = getClass().getResource("/fxml/DrawWindow.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent rootDraw = fxmlLoader.load();
        Scene scene = new Scene(rootDraw, 600, 400, true);

        drawWindow = new Stage();
        drawWindow.setAlwaysOnTop(true);

        double W = .3 * Main.screenDimFind().getWidth() + 210;
        double H = 0.55 * Main.screenDimFind().getHeight();

        drawWindow.setWidth(W);
        drawWindow.setHeight(H);

        drawWindow.setTitle("Draw Window:  " + statusFile.getText() + "  ... ");
        drawWindow.setScene(scene);
        drawWindow.setResizable(false);
        drawWindow.setMaximized(false);

        drawWindow.show();

        DrawingLimbController ctrl = (DrawingLimbController) fxmlLoader.getController();

        ctrl.setData(openFileChoser.getSelectedFileContent());
        ctrl.setWindowHeight(H);
        ctrl.setWindowWidth(W);

        ctrl.resizeSupporters();
        drawWindow.setOnCloseRequest(event
                -> {
            System.out.println("CLOSING");
            ctrl.closeAllTasks();
            drawWindowCloseEvent(event);
        });
    }

    private void drawWindowCloseEvent(WindowEvent e) {
        System.out.println("   e:  " + e.getEventType().getName());
        closeTasks();
    }

    public void closeTasks() {
        if (drawWindow != null) {
            drawWindow = null;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openDirChoser = new OpenDirChoser();
        openFileChoser = new OpenFileChoser();
        //drawingLimbController
        //       System.out.println("            stage.getWidth()  " + (stage == null));
    }

}
