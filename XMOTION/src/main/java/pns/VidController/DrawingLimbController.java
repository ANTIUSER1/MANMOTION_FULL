/**
 * ***********************************************************************************************************************************************
 *
 * Это контроллер для рисования чубрика
 *
 * Содержит:
 * ПОЛЯ
 *
 * @FXML
 * private AnchorPane mainPanel;---   основная панель, на которую кладется все инструменты
 * @FXML private Pane supportPanel;-- паннель для размещения изображения чубрика
 * @FXML private BorderPane borderPanel;--- сюда выкладывается панель для размещения чубрика и панели инструментов -- она кладется на саппорт-панель
 * @FXML private HBox tooltop;-- для размещения инструментов вверху
 * @FXML private HBox toolbottom; -- для инструментов внизу private DataReciever dataReciever = DataReciever.getInstance();-- [hfybntkm исходных данных private
 * ConvertToMan ctoMan = ConvertToMan.getInstance(); преобразователь исходных данных * private MotionHead patternHead; -- патерн головы в двигающейся форме * private
 * Light.Point pt; --- стартовая точка рисования * private boolean openned = false; --- нужен для блокировки многократного создания изображения --- Изображение создается
 * однократно при шевелении мышкой по mainPanel если этого флага не будет, или не будет проверяться его ложность то отрисовка будет криво работать private boolean
 * goFoward = false; ----------- флаг ходьбы вперед private boolean goBack = false; ---------- флаг ходьбы назад
 *
 *
 * МЕТОДЫ
 *
 * @Override public void initialize(URL url, ResourceBundle rb) инициализация
 * @FXML public void showMan() показ чубрика полностью
 * @FXML public void showHead() показ головы
 * @FXML public void showBody() показ тела
 * @FXML public void showHands() показ рук
 *
 * @FXML public void showLegs() показ ног
 * @FXML public void recieveData() преобразование исходных данных к нужным структурам, после этого происходит прорисовка скрытого чубрика Тут проверяется флаг opened
 * @FXML public void forward() установка направления ходьбы вперед и ее запуск
 * @FXML public void backward() установка направления ходьбы назад и ее запуск * private void drawMan() отрисовка скрытого чубрика private void putComponents() { //
 * patternBody = new MotionBody(); раскладывание компонентов private void drawCoords() рисование координатной сетки
 *
 * --------------- команда на ДВИЖЕНИЕ тела, головы, рук, ног дается только, если соотв части видимы private void motionBody() двигаем телом private void motionHands()
 * двигаем руками private void motionHead() двигаем головой private void motionLegs() двигаем ногами private void motion() общие команды на движение
 *
 *
 *
 ***************************************************************************************************************************************************
 */
package pns.VidController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Light;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import pns.VidController.manparts.motions.MotionHead;
import pns.datatools.ConvertToMan;
import pns.datatools.DataReciever;
import pns.start.Main;

/**
 * FXML Controller class
 *
 * @author Movement
 */
public class DrawingLimbController implements Initializable {

    @FXML
    private AnchorPane mainPanel;
    @FXML
    private Pane supportPanel;
    @FXML
    private BorderPane borderPanel;
    @FXML
    private HBox tooltop;
    @FXML
    private HBox toolbottom;
    @FXML
    private TextArea txtArea;
    @FXML
    private Label infoImage;
    @FXML
    public Button pauseBTN;
    @FXML
    public Button runBTN;
    @FXML
    public Button runBackBTN;
    @FXML
    public Button firstBTN;
    @FXML
    public Button endBTN;

    private DataReciever dataReciever = new DataReciever();//DataReciever.getInstance();

    private ConvertToMan ctoMan = new ConvertToMan();//ConvertToMan.getInstance();
    private MotionHead patternHead;//= new MotionHead();

    private Light.Point pt;

    private boolean goFoward = false;
    private boolean goBack = false;

    private double windowWidth = 2 * Main.screenDimFind().getWidth() / 3;
    private double windowHeight = 2 * Main.screenDimFind().getHeight() / 3;

    private String data = "";

    public void setData(String data) {
        this.data = data;
        txtArea.setText(data);
        dataReciever.setData(data);
    }

    public TextArea getTxtArea() {
        return txtArea;
    }

    public void setWindowWidth(double windowWidth) {
        this.windowWidth = windowWidth;
    }

    public void setWindowHeight(double windowHeight) {
        this.windowHeight = windowHeight;
    }

    public void resizeSupporters() {
        supportPanel.getChildren().clear();

        tooltop.setPrefWidth(windowWidth);
        tooltop.setPrefHeight(25);

        toolbottom.setPrefWidth(windowWidth);
        toolbottom.setPrefHeight(25);

        txtArea.setPrefWidth(200);

        borderPanel.setPrefWidth(windowWidth - txtArea.getPrefWidth() - 26);
        borderPanel.setPrefHeight(windowHeight);

        supportPanel.setPrefWidth(borderPanel.getPrefWidth());
        supportPanel.setPrefHeight(windowHeight - (toolbottom.getPrefHeight() + tooltop.getPrefHeight() + 45));

        System.out.println("   ************** txtArea.getPrefWidth()txtArea.getPrefWidth()   " + txtArea.getPrefWidth());

        recieveData();
        drawCoords();
        drawMan();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        runBTN.setDisable(false);
        runBackBTN.setDisable(true);

        pauseBTN.setDisable(true);

        firstBTN.setDisable(true);
        endBTN.setDisable(true);

    }

    @FXML
    public void recieveData() {
        if (ctoMan.getMan() == null) {
            System.out.println("    ctoMan.hashCode()   " + ctoMan.hashCode());
            ctoMan.convert(dataReciever.getData());
            System.out.println("--OOO  000  OOOOOOOOOOO!!");
            patternHead = new MotionHead(ctoMan.getMan());
        }
    }

    @FXML
    public void forward() {
        goFoward = true;
        goBack = false;

        runBTN.setDisable(true);
        runBackBTN.setDisable(true);

        pauseBTN.setDisable(false);

        firstBTN.setDisable(true);
        endBTN.setDisable(true);

        patternHead.removePauseFoward();
        motion();
    }

    @FXML
    public void backward() {
        System.out.println("Do Not Use");
//        goFoward = false;
//        goBack = true;
//
//        runBTN.setDisable(true);
//        runBackBTN.setDisable(true);
//
//        pauseBTN.setDisable(false);
//
//        firstBTN.setDisable(true);
//        endBTN.setDisable(true);
//
//        motion();
    }

    @FXML
    public void pause() {
        runBTN.setDisable(false);
        runBackBTN.setDisable(false);

        pauseBTN.setDisable(true);

        firstBTN.setDisable(false);
        endBTN.setDisable(false);

        patternHead.motionPause();
    }

    @FXML
    public void toStart() {
        patternHead.toStart();
        patternHead.toStart();

        runBTN.setDisable(false);
        runBackBTN.setDisable(true);
        pauseBTN.setDisable(true);
        firstBTN.setDisable(true);
        endBTN.setDisable(true);
    }

    @FXML
    public void toEnd() {
        patternHead.toEnd();

        runBTN.setDisable(false);
        runBackBTN.setDisable(false);
        pauseBTN.setDisable(false);
        firstBTN.setDisable(false);
        endBTN.setDisable(false);

    }

    private void drawMan() {

        pt = patternHead.drawHead(pt);

        supportPanel.getChildren().add(patternHead.getPanel());

    }

    private void putComponents() {

        pt = new Light.Point();
        pt.setX(supportPanel.getPrefWidth() / 2);
        pt.setY(50);
        pt.setZ(0);
    }

    private void drawCoords() {
        pt = new Light.Point();
        pt.setX(windowWidth / 2);
        pt.setY(40);
        System.out.println(" supportPanel.getPrefWidth() " + supportPanel.getPrefWidth());
        double x = 0;
        while (x <= supportPanel.getPrefWidth()) {
            Line line = new Line(x, 0, x, supportPanel.getPrefHeight());
            line.setStroke(Color.CHOCOLATE);
            line.setStrokeWidth(.52);
            x += 15;
            supportPanel.getChildren().add(line);
        }
        double y = 0;
        while (y <= supportPanel.getPrefHeight()) {
            Line line = new Line(0, y, supportPanel.getPrefWidth(), y);
            line.setStroke(Color.CHOCOLATE);
            line.setStrokeWidth(.52);
            y += 15;
            supportPanel.getChildren().add(line);
        }

    }

    //------------
    //
    //----------------
    private void motionBody() {
        if (!(goFoward && goBack)) {
            if (goFoward) {
                patternHead.getPatternBody().motionFoward();
            }
        } else {
            goFoward = goBack = false;
        }
    }

    private void motionHands() {
        if (!(goFoward && goBack)) {
            if (goFoward) {
                System.out.println("FFFoward HAND");
                patternHead.getPatternBody().getPatternHand().motionFoward();
            }
        } else {
            goFoward = goBack = false;
        }
    }

    private void motionHead() {

        if (!(goFoward && goBack)) {
            if (goFoward) {
                patternHead.motionFoward();
            }
            goFoward = goBack = false;
        }
    }

    private void motionLegs() {

        if (!(goFoward && goBack)) {
            if (goFoward) {
                patternHead.getPatternBody().getPatternLeg().motionFoward();
            }
        } else {
            goFoward = goBack = false;
        }
    }

    private void motion() {

        if (patternHead.getPanel().isVisible()) {
            // motion head
            motionHead();
        }
        if (patternHead.getPatternBody().getPanel().isVisible()) {
            // motion body
            motionBody();
        }
        if (patternHead.getPatternBody().getPatternHand().getPanel().isVisible()) {
            // motion hands
            motionHands();
        }
        if (patternHead.getPatternBody().getPatternLeg().getPanel().isVisible()) {
            // motion legs
            motionLegs();
        }
    }

}
