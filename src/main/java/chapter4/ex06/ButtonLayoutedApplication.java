package chapter4.ex06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ButtonLayoutedApplication extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    BorderPane pane = new BorderPane();
    Button top = new Button("Top"); 
    pane.setTop(top);
    BorderPane.setAlignment(top, Pos.TOP_CENTER);
    pane.setLeft(new Button("Left"));
    pane.setCenter(new Button("Center"));
    pane.setRight(new Button("Right"));
    Button bottom = new Button("Buttom");
    pane.setBottom(bottom);
    BorderPane.setAlignment(bottom, Pos.BOTTOM_CENTER);
    
    primaryStage.setScene(new Scene(pane));
    primaryStage.show();
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
