package clock;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class ConfigController implements Initializable {

  @FXML private ListView fontList;
  @FXML private ListView sizeList;
  @FXML private ListView colorList;
  @FXML private ListView bgColorList;
  
  private Stage self;
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @FXML
  private void handleOK(ActionEvent event) throws IOException {
    self.hide();
  }
  
  @FXML
  private void handleCancel(ActionEvent event) throws IOException {
    self.hide();
  }

  public void setSelf(Stage self) {
    this.self = self;
  }
}
