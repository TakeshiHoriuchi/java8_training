package clock;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.configStage = initConfigStage();
    this.menu = initMenu(primaryStage);
    this.text = initText();
    this.canvas = initCanvas(primaryStage, this.menu, this.text);

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
    controller.setExitHandler(e -> stage.close());
    return menu;
  }
  
  private Text initText() {
    String drawn = LocalTime.now().format(DateTimeFormatter.ofPattern("kk:mm:ss"));
    Font font = Font.getDefault();
    Text text = new Text(drawn);
    text.setFont(font);
    return text;
  }

  private Canvas initCanvas(Stage stage, ContextMenu menu, Text text) {

    Bounds b = text.getLayoutBounds();
    Canvas canvas = new Canvas(b.getWidth(), b.getHeight() * 1.1);

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
        text.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("kk:mm:ss")));
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.FORESTGREEN);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.ALICEBLUE);
        gc.fillText(text.getText(),
                    0, gc.getFont().getSize() + 2);
      }
    }.start();
    
    return canvas;
  }

  private Stage initConfigStage() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Config.fxml"));
    Parent parent = loader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(parent));
    
    ConfigController controller = (ConfigController)loader.getController();
    controller.setSelf(stage);
    
    stage.show();
    
    return stage;
  }

  private static class Delta {
    double x, y;
  }
}
