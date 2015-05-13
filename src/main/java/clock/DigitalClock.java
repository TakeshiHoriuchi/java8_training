package clock;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DigitalClock extends Application {

  private Canvas canvas;
  private ContextMenu menu;
  private Text text;
  private Stage configStage;
  private Color bgColor;
  private Color color;

  private Font currentFont;
  private Color currentBgColor;
  private Color currentColor;
  
  private ConfigStore conf;

  @Override
  public void start(Stage primaryStage) throws Exception {
    conf = ConfigStore.load();
    color = conf.getColor();
    bgColor = conf.getBgColor();

    this.menu = initMenu(primaryStage);
    this.text = initText(conf);
    this.canvas = initCanvas(primaryStage, this.text);
    this.configStage = initConfigStage(primaryStage);
    primaryStage.setX(conf.getX());
    primaryStage.setY(conf.getY());
    primaryStage.initStyle(StageStyle.UNDECORATED);
    primaryStage.setScene(new Scene(new Group(canvas)));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private ContextMenu initMenu(Stage stage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Menu.fxml"));
    ContextMenu menu = loader.load();
    MenuController controller = (MenuController) loader.getController();
    controller.setExitHandler(e -> {
      conf.setFont(text.getFont());
      conf.setColor(color);
      conf.setBgColor(bgColor);
      conf.setX(stage.getX());
      conf.setY(stage.getY());
      try {
        conf.save();
      } catch (URISyntaxException | IOException ex) {
        Logger.getLogger(DigitalClock.class.getName()).log(Level.SEVERE, null, ex);
      }
      stage.close();
    });
    controller.setConfigHandler(e -> {
      currentFont = text.getFont();
      currentBgColor = bgColor;
      currentColor = color;
      configStage.show();
    });
    return menu;
  }

  private Text initText(ConfigStore conf) {
    String drawn = LocalTime.now().format(DateTimeFormatter.ofPattern("88:88:88"));
    Text text = new Text(drawn);
    text.setFont(conf.getFont());
    return text;
  }

  private Canvas initCanvas(Stage stage, Text text) {
    Bounds b = text.getLayoutBounds();
    Canvas canvas = new Canvas();
    setFontAndSize(canvas, text.getFont(), stage);

    final Delta delta = new Delta();
    canvas.setOnMousePressed(e -> {
      if (e.isSecondaryButtonDown())
        menu.show(canvas, e.getScreenX(), e.getScreenY());
      else if (e.isPrimaryButtonDown()) {
        menu.hide();
        delta.x = stage.getX() - e.getScreenX();
        delta.y = stage.getY() - e.getScreenY();
      }
    });
    canvas.setOnMouseDragged(e -> {
      stage.setX(e.getScreenX() + delta.x);
      stage.setY(e.getScreenY() + delta.y);
    });

    new AnimationTimer() {
      @Override
      public void handle(long now) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(bgColor);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(color);
        gc.fillText(LocalTime.now().format(DateTimeFormatter.ofPattern("kk:mm:ss")),
                    0, text.getLayoutBounds().getHeight() * 0.9);
      }
    }.start();

    return canvas;
  }

  private Stage initConfigStage(Stage primaryStage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Config.fxml"));
    Parent parent = loader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(parent));

    ConfigController controller = (ConfigController) loader.getController();
    controller.setSelf(stage);
    controller.setColorHandler(c -> this.color = c);
    controller.setBgColorHandler(c -> this.bgColor = c);
    controller.setSizeHandler(size -> {
      Font font = new Font(text.getFont().getName(), size);
      setFontAndSize(canvas, font, primaryStage);
    });
    controller.setFontHandler(name -> {
      Font font = new Font(name, text.getFont().getSize());
      setFontAndSize(canvas, font, primaryStage);
    });
    controller.setCancelHandler((e) -> {
      setFontAndSize(canvas, currentFont, primaryStage);
      color = currentColor;
      bgColor = currentBgColor;
    });

    return stage;
  }

  private void setFontAndSize(Canvas canvas, Font font, Stage stage) {
    text.setFont(font);
    Bounds b = text.getLayoutBounds();
    canvas.setHeight(b.getHeight() * 1.001);
    canvas.setWidth(b.getWidth() * 1.001);
    stage.setHeight(b.getHeight() * 1.001);
    stage.setWidth(b.getWidth() * 1.001);
    canvas.getGraphicsContext2D().setFont(font);
  }

  private static class Delta {
    double x, y;
  }
}
