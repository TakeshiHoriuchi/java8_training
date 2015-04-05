package chapter9.ex11;

//see http://reluctanthacker.rollett.org/creating-regex-finding-credit-card-numbers-grep
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

public class NumberGrepper {

  public static void main(String args[]) {
    try {
      ProcessBuilder processBuilder = new ProcessBuilder("grep", "-r", "'\\(^\\|[^0-9]\\)\\{1\\}\\([345]\\{1\\}[0-9]\\{3\\}\\|6011\\)\\{1\\}[-]\\?[0-9]\\{4\\}[-]\\?\\ [0-9]\\{2\\}[-]\\?[0-9]\\{2\\}-\\?[0-9]\\{1,4\\}\\($\\|[^0-9]\\)\\{1\\}'", System.getenv("HOME"));
      Process process = processBuilder.start();
      process.waitFor();
      String result = IOUtils.toString(process.getInputStream());
      System.out.println(result);
    } catch (IOException | InterruptedException ex) {
      Logger.getLogger(NumberGrepper.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
