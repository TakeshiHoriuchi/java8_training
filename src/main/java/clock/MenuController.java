package clock;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class MenuController implements Initializable {

  private Consumer<ActionEvent> configHandler;
  private Consumer<ActionEvent> exitHandler;
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  @FXML
  private void handleConfig(ActionEvent event) throws IOException {
    configHandler.accept(event);
  }
  
  @FXML
  private void handleExit(ActionEvent event) throws IOException {
    exitHandler.accept(event);
  }

  public void setConfigHandler(Consumer<ActionEvent> configHandler) {
    this.configHandler = configHandler;
  }

  public void setExitHandler(Consumer<ActionEvent> exitHandler) {
    this.exitHandler = exitHandler;
  }
}
