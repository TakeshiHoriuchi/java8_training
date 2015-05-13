package clock;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Properties;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ConfigStore {

    private final Properties properties;
    
    private static final String FILE_NAME = "setting.properties";

    public static ConfigStore load() throws IOException {
        Properties p = new Properties();
        try (InputStream in = ConfigStore.class.getResourceAsStream(FILE_NAME)) {
            p.load(in);
        }

        return new ConfigStore(p);
    }

    private ConfigStore(Properties p) {
        properties = p;
    }

    public Font getFont() {
        double size = Double.parseDouble(properties.getProperty("font.size"));
        String name = properties.getProperty("font.name");
        return new Font(name, size);
    }

    public Color getColor() {
        return new Color(
                Double.parseDouble(properties.getProperty("color.r")),
                Double.parseDouble(properties.getProperty("color.g")),
                Double.parseDouble(properties.getProperty("color.b")),
                Double.parseDouble(properties.getProperty("color.a"))
        );
    }

    public Color getBgColor() {
        return new Color(
                Double.parseDouble(properties.getProperty("bgColor.r")),
                Double.parseDouble(properties.getProperty("bgColor.g")),
                Double.parseDouble(properties.getProperty("bgColor.b")),
                Double.parseDouble(properties.getProperty("bgColor.a"))
        );
    }

    public void setFont(Font f) {
        properties.setProperty("font.size", String.valueOf(f.getSize()));
        properties.setProperty("font.size", f.getName());
    }

    public void setColor(Color c) {
        properties.setProperty("color.r", String.valueOf(c.getRed()));
        properties.setProperty("color.g", String.valueOf(c.getGreen()));
        properties.setProperty("color.b", String.valueOf(c.getBlue()));
        properties.setProperty("color.a", String.valueOf(c.getOpacity()));
    }

    public void setBgColor(Color c) {
        properties.setProperty("bgColor.r", String.valueOf(c.getRed()));
        properties.setProperty("bgColor.g", String.valueOf(c.getGreen()));
        properties.setProperty("bgColor.b", String.valueOf(c.getBlue()));
        properties.setProperty("bgColor.a", String.valueOf(c.getOpacity()));
    }
    
    public void save() throws URISyntaxException, IOException {
        File file = new File(getClass().getResource(FILE_NAME).toURI());
        try (OutputStream out = new FileOutputStream(file)) {
            properties.store(out, "");
        }
    }
}
