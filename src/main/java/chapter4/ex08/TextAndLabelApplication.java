package chapter4.ex08;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TextAndLabelApplication extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = getRoot();
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
  
  static Parent getRoot() {
    try {
      return FXMLLoader.load(TextAndLabelApplication.class.getResource("fxml/TextAndLabel.fxml"));
    } catch (IOException ex) {
      Logger.getLogger(TextAndLabelApplication.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
}
