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

    private DataReciever dataReciever = new DataReciever();//DataReciever.getInstance();

    private ConvertToMan ctoMan = new ConvertToMan();//ConvertToMan.getInstance();
    private MotionHead patternHead = new MotionHead();

    private Light.Point pt;

//    private boolean openned = false;
    private boolean goFoward = false;
    private boolean goBack = false;

    private double windowWidth = 4 * Main.screenDimFind().getWidth() / 5;
    private double windowHeight = 4 * Main.screenDimFind().getHeight() / 5;

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
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEE====>>>>>>>>>>");
        supportPanel.getChildren().clear();

        mainPanel.setPrefWidth(0.55 * Main.screenDimFind().getWidth());
        mainPanel.setPrefHeight(6 * Main.screenDimFind().getHeight() / 7);

        borderPanel.setPrefWidth(0.55 * Main.screenDimFind().getWidth());
        borderPanel.setPrefHeight(6 * Main.screenDimFind().getHeight() / 7);

        tooltop.setPrefWidth(4 * Main.screenDimFind().getWidth() / 5);
        tooltop.setPrefHeight(25);

        toolbottom.setPrefWidth(4 * Main.screenDimFind().getWidth() / 5);
        toolbottom.setPrefHeight(25);

        txtArea.setPrefWidth(200);

        supportPanel.setPrefWidth(windowWidth - 220);
        supportPanel.setPrefHeight(borderPanel.getPrefHeight() - toolbottom.getPrefHeight() - tooltop.getPrefHeight());
        System.out.println("   ************** txtArea.getPrefWidth()txtArea.getPrefWidth()   " + txtArea.getPrefWidth());
        drawCoords();
        drawMan();

        System.out.println("  *******************   SHOW MAN *********************************");
        System.out.println("            patternHead.getPanel().isVisible()  " + patternHead.getPanel().isVisible());
        System.out.println("            patternHead.getPatternBody().getPanel().isVisible() " + patternHead.getPatternBody().getPanel().isVisible());
        System.out.println("            patternHead.getPatternBody().getPatternHand().getPanel().isVisible()  " + patternHead.getPatternBody().getPatternHand().getPanel().isVisible());
        System.out.println("            patternHead.getPatternBody().getPatternLeg().getPanel().isVisible() " + patternHead.getPatternBody().getPatternLeg().getPanel().isVisible());
        System.out.println("  *******************   SHOW MAN *********************************");

        // showMan();
        //recieveData();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void showMan() {
//        patternHead.getPanel().setVisible(true);
//        patternHead.getPatternBody().getPanel().setVisible(true);
//        patternHead.getPatternBody().getPatternHand().getPanel().setVisible(true);
//        patternHead.getPatternBody().getPatternLeg().getPanel().setVisible(true);
        System.out.println("  *******************   SHOW MAN *********************************");
        System.out.println("             supportPanel.isVisible()           " + supportPanel.isVisible());
        System.out.println("            patternHead.getPanel().isVisible()  " + patternHead.getPanel().isVisible());
        System.out.println("            patternHead.getPatternBody().getPanel().isVisible() " + patternHead.getPatternBody().getPanel().isVisible());
        System.out.println("            patternHead.getPatternBody().getPatternHand().getPanel().isVisible()  " + patternHead.getPatternBody().getPatternHand().getPanel().isVisible());
        System.out.println("            patternHead.getPatternBody().getPatternLeg().getPanel().isVisible() " + patternHead.getPatternBody().getPatternLeg().getPanel().isVisible());
        System.out.println("  *******************   SHOW MAN *********************************");
    }

    @FXML
    public void showHead() {
        patternHead.getPanel().setVisible(true);
        patternHead.getPatternBody().getPanel().setVisible(false);
        patternHead.getPatternBody().getPatternHand().getPanel().setVisible(false);
        patternHead.getPatternBody().getPatternLeg().getPanel().setVisible(false);
    }

    @FXML
    public void showBody() {
        patternHead.getPanel().setVisible(false);
        patternHead.getPatternBody().getPanel().setVisible(true);
        patternHead.getPatternBody().getPatternHand().getPanel().setVisible(false);
        patternHead.getPatternBody().getPatternLeg().getPanel().setVisible(false);
    }

    @FXML
    public void showHands() {
        patternHead.getPanel().setVisible(true);
        patternHead.getPatternBody().getPanel().setVisible(true);
        patternHead.getPatternBody().getPatternHand().getPanel().setVisible(true);
        patternHead.getPatternBody().getPatternLeg().getPanel().setVisible(false);
        System.out.println("     supportPanel.isVisible()    " + supportPanel.isVisible());
        System.out.println("       patternHead.getPanel().isVisible()    " + patternHead.getPanel().isVisible());

    }

    @FXML
    public void showLegs() {
        patternHead.getPanel().setVisible(true);
        patternHead.getPatternBody().getPanel().setVisible(true);
        patternHead.getPatternBody().getPatternHand().getPanel().setVisible(false);
        patternHead.getPatternBody().getPatternLeg().getPanel().setVisible(true);
    }

    @FXML
    public void recieveData() {

        if (ctoMan.getMan() == null) {
            System.out.println("    ctoMan.hashCode()   " + ctoMan.hashCode());
            ctoMan.convert(dataReciever.getData());
            System.out.println("--OOO  000  OOOOOOOOOOO!!");
        }
    }

    @FXML
    public void forward() {
        goFoward = true;
        goBack = false;

        motion();
    }

    @FXML
    public void backward() {
        goFoward = false;
        goBack = true;

        motion();
    }

    private void drawMan() {

        pt = patternHead.drawHead(pt);
        supportPanel.getChildren().add(patternHead.getPanel());
        //supportPanel.getChildren().add(patternHead.getPatternBody().getPanel());
    }

    private void putComponents() {

        patternHead = new MotionHead();

        patternHead.getHead().setPatAfter(patternHead.getPatternBody().getPatternHand());

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
        while (x <= mainPanel.getPrefWidth()) {
            Line line = new Line(x, 0, x, mainPanel.getPrefHeight());
            line.setStroke(Color.CHOCOLATE);
            line.setStrokeWidth(.22);
            x += 5;
            mainPanel.getChildren().add(line);
        }
        double y = 0;
        while (y <= supportPanel.getPrefHeight()) {
            Line line = new Line(0, y, mainPanel.getPrefWidth(), y);
            line.setStroke(Color.CHOCOLATE);
            line.setStrokeWidth(.22);
            y += 5;
            mainPanel.getChildren().add(line);
        }

    }

    private void motionBody() {
        if (!(goFoward && goBack)) {
            if (goFoward) {
                patternHead.getPatternBody().motionFoward();
            } else if (goBack) {
                patternHead.getPatternBody().motionBackward();
            }
        } else {
            goFoward = goBack = false;
        }
    }

    private void motionHands() {
        if (!(goFoward && goBack)) {
            if (goFoward) {
                patternHead.getPatternBody().getPatternHand().motionFoward();
            } else if (goBack) {
                patternHead.getPatternBody().getPatternHand().motionBackward();
            }
        } else {
            goFoward = goBack = false;
        }
    }

    private void motionHead() {

        if (!(goFoward && goBack)) {
            if (goFoward) {
                patternHead.motionFoward();
            } else if (goBack) {
                patternHead.motionBackward();
            }
        } else {
            goFoward = goBack = false;
        }
    }

    private void motionLegs() {

        if (!(goFoward && goBack)) {
            if (goFoward) {
                patternHead.getPatternBody().getPatternLeg().motionFoward();
            } else if (goBack) {
                patternHead.getPatternBody().getPatternLeg().motionBackward();
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
