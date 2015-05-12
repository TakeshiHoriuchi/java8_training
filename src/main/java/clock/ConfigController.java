package clock;

import java.io.IOException;
import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ConfigController implements Initializable {

  @FXML
  private ListView<String> fontList;
  @FXML
  private ListView<Integer> sizeList;
  @FXML
  private ListView<String> colorList;
  @FXML
  private ListView<String> bgColorList;

  private Stage self;
  
  private Consumer<ActionEvent> okHandler = (e) -> {};
  private Consumer<ActionEvent> cancelHandler = (e) -> {};

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    initFontList(fontList);
    sizeList.setItems(FXCollections.observableArrayList(8, 16, 32, 48, 64, 96, 128, 256));
    initColorList(colorList);
    initColorList(bgColorList);
  }
  
  private void initFontList(ListView<String> list) {
    list.setItems(FXCollections.observableList(Font.getFontNames()));
    list.setCellFactory(li -> {
      return new ListCell<String>() {
        @Override
        public void updateItem(String item, boolean empty) {
          super.updateItem(item, empty);
          if (item != null) {
            setText(item);
            setFont(new Font(item, getFont().getSize()));
          }
        }
      };
    });
  }

  private void initColorList(ListView<String> list) {
    list.setItems(FXCollections.observableList(
      Arrays.stream(Color.class.getDeclaredFields()).
      filter(f -> isStatic(f.getModifiers()) && isPublic(f.getModifiers())).
      map(f -> f.getName().toLowerCase()).
      collect(Collectors.toList())
    ));
    
    list.setCellFactory(li -> {
      return new ListCell<String>() {
        @Override
        public void updateItem(String item, boolean empty) {
          super.updateItem(item, empty);
          Rectangle rect = new Rectangle(15, 10);
          if (item != null) {
            rect.setFill(Color.web(item));
            setGraphic(rect);
            setText(item);
          }
        }
      };
    });
  }

  @FXML
  private void handleOK(ActionEvent event) throws IOException {
    okHandler.accept(event);
    self.hide();
  }

  @FXML
  private void handleCancel(ActionEvent event) throws IOException {
    cancelHandler.accept(event);
    self.hide();
  }

  public void setSelf(Stage self) {
    this.self = self;
  }
  
  public void setFontHandler(Consumer<String> handler) {
    fontList.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
      handler.accept(nv);
    });
  }
  
  public void setSizeHandler(IntConsumer handler) {
    sizeList.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
      handler.accept(nv);
    });
  }

  public void setColorHandler(Consumer<Color> handler) {
    colorList.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
      handler.accept(Color.web(nv));
    });
  }

  public void setBgColorHandler(Consumer<Color> handler) {
    bgColorList.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
      handler.accept(Color.web(nv));
    });
  }

  public void setOkHandler(Consumer<ActionEvent> okHandler) {
    this.okHandler = okHandler;
  }

  public void setCancelHandler(Consumer<ActionEvent> cancelHandler) {
    this.cancelHandler = cancelHandler;
  }
}
