package chapter4.ex10;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;


public class SimpleWebBrowserController implements Initializable {
  @FXML private WebView webView;
  @FXML private Button buttonBack;
  @FXML private TextField textURL;
  @FXML private Button buttonAccess;
  
  @FXML
  private void handleButtonAccessAction(ActionEvent event) {
    webView.getEngine().load(textURL.getText());
  }
  
  @FXML
  private void handleButtonBackAction(ActionEvent event) {
    webView.getEngine().getHistory().go(-1);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setBackControllAvailabilityConstraint();
  }
  
  private void setBackControllAvailabilityConstraint() {
    ReadOnlyIntegerProperty indexProperty = webView.getEngine().getHistory().currentIndexProperty();
    buttonBack.disableProperty().bind(
            Bindings.createBooleanBinding(() -> indexProperty.get() == 0, indexProperty)
    );
  }
}
