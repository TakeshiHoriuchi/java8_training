package chapter4.ex09;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class PlanetAnimationApplication extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Pane root = getRoot();
    Scene scene = new Scene(root, root.prefWidthProperty().get(), root.prefHeightProperty().get());
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
  
  static Pane getRoot() {
    try {
      return FXMLLoader.load(PlanetAnimationApplication.class.getResource("fxml/PlanetAnimation.fxml"));
    } catch (IOException ex) {
      Logger.getLogger(PlanetAnimationApplication.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
}
