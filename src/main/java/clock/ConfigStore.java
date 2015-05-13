package clock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ConfigStore {

  private final Properties properties;

  private static final String FILE_NAME = "setting.properties";
  
  private static File propFile;

  public static ConfigStore load() throws IOException, URISyntaxException {
    Properties p = new Properties();
    try (InputStream in = new FileInputStream(getPropFile())) {
      p.load(in);
    }

    return new ConfigStore(p);
  }
  
  private static File getPropFile() throws URISyntaxException {
    if (propFile != null) return propFile;
    
    URL url = ConfigStore.class.getResource(FILE_NAME);
    if (url != null) {
      propFile = new File(url.toURI());
      return propFile;
    }
    
    url = Thread.currentThread().getContextClassLoader().getResource(FILE_NAME);
    if (url != null) {
      propFile = new File(url.toURI());
      return propFile;
    }
    
    return null;
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

  public double getX() {
    return Double.parseDouble(properties.getProperty("x"));
  }

  public double getY() {
    return Double.parseDouble(properties.getProperty("y"));
  }

  public void setFont(Font f) {
    properties.setProperty("font.size", String.valueOf(f.getSize()));
    properties.setProperty("font.name", f.getName());
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

  public void setX(double x) {
    properties.setProperty("x", String.valueOf(x));
  }

  public void setY(double y) {
    properties.setProperty("y", String.valueOf(y));
  }

  public void save() throws URISyntaxException, IOException {
    try (OutputStream out = new FileOutputStream(getPropFile())) {
      properties.store(out, "");
    }
  }
}
