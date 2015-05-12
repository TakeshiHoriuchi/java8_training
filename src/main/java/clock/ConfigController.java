package clock;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ConfigController implements Initializable {

    @FXML
    private ListView fontList;
    @FXML
    private ListView sizeList;
    @FXML
    private ListView<String> colorList;
    @FXML
    private ListView<String> bgColorList;

    private Stage self;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColorList(colorList);
        initColorList(bgColorList);
    }
    
    private void initColorList(ListView<String> list) {
        ObservableList<String> data = FXCollections.observableArrayList(
                "chocolate", "salmon", "gold", "coral", "darkorchid",
                "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
                "blueviolet", "brown");

        list.setItems(data);
        list.setCellFactory(li -> new ColorChipCell());
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
    public void setColorHandler(Consumer<Color> colorHandler) {
        colorList.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            colorHandler.accept(Color.web(nv));
        });
    }
    public void setBgColorHandler(Consumer<Color> bgColorHandler) {
        bgColorList.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            bgColorHandler.accept(Color.web(nv));
        });
    }

    private static class ColorChipCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(15, 10);
            if (item != null) {
                rect.setFill(Color.web(item));
                setGraphic(rect);
                setText(item);
            }
        }
    }
}
