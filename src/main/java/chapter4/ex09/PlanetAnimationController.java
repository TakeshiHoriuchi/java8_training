package chapter4.ex09;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;


public class PlanetAnimationController implements Initializable {

  @FXML private Circle planet; 
  @FXML private Ellipse orbit;
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setPlanetAnimation();
  }
  
  private void setPlanetAnimation() {
    PathTransition pt = new PathTransition(Duration.seconds(8), orbit);
    pt.setNode(planet);
    pt.setCycleCount(Animation.INDEFINITE);
    pt.interpolatorProperty().set(Interpolator.LINEAR);
    pt.play();
  }
}
