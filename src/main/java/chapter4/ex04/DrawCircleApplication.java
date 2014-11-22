package chapter4.ex04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/*
FXMLのコントローラでSceneを取得する方法がわからなかったのでstart内でレイアウト作成
TestFXでSceneのウィンドウサイズを変更する方法がわからなかったので手動でテスト実施
*/
public class DrawCircleApplication extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    
    Circle circle = new Circle(100, Color.RED);
    Parent root = new Pane(circle);
    Scene scene = new Scene(root, 200, 200);
    primaryStage.setScene(scene);
    
    circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
    circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));
    circle.radiusProperty().bind(
            Bindings.divide(
                    Bindings.min(scene.heightProperty(), scene.widthProperty()),
                    2
            ));
    
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
