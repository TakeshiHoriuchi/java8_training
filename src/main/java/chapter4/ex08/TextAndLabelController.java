package chapter4.ex08;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class TextAndLabelController implements Initializable {

  @FXML private TextField textField;
  @FXML private Label label;
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    label.textProperty().bind(textField.textProperty());
  }
}
