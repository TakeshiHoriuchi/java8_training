package chapter3.ex01;

import java.io.ByteArrayOutputStream;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import org.hamcrest.Matchers;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class LoggerUtilTest {

  private static final Level DEFAULT_LEVEL = Level.INFO;
  private static final String TEST_MESSAGE = "TEST MESSAGE";
  LoggerUtilImpl lui;
  IsEvaluated isEvalBs;
  IsEvaluated isEvalS;

  @Before
  public void setUp() {
    lui = new LoggerUtilImpl();
    isEvalBs = new IsEvaluated();
    isEvalS = new IsEvaluated();
  }

  @Test
  public void testLogIf_when_level_of_argument_is_upper_than_level_of_logger_and_bs_is_true_then_log_is_printed() {
    lui.logIf(DEFAULT_LEVEL, () -> true, () -> TEST_MESSAGE);
    assertThat(lui.getLogMessage(), Matchers.containsString(TEST_MESSAGE));
  }

  @Test
  public void testLogif_when_level_of_argument_is_downer_than_level_of_logger_then_bs_and_s_is_not_evaluated_and_no_log_is_printed() {
    lui.logIf(
            Level.FINE,
            () -> {
              isEvalBs.isEval = true;
              return true;
            },
            () -> {
              isEvalS.isEval = true;
              return TEST_MESSAGE;
            }
    );

    assertFalse(isEvalBs.isEval);
    assertFalse(isEvalS.isEval);
    assertEquals(lui.getLogMessage(), "");
  }

  @Test
  public void testLogIf_when_level_of_arument_is_upper_than_level_of_logger_but_bs_is_false_then_s_is_not_evaluated_and_no_log_is_printed() {
    lui.logIf(
            DEFAULT_LEVEL,
            () -> {
              isEvalBs.isEval = true;
              return false;
            },
            () -> {
              isEvalS.isEval = true;
              return TEST_MESSAGE;
            }
    );

    assertTrue(isEvalBs.isEval);
    assertFalse(isEvalS.isEval);
    assertEquals(lui.getLogMessage(), "");
  }

  class LoggerUtilImpl implements LoggerUtil {

    ByteArrayOutputStream bsResult;
    Logger logger;
    Handler handler;

    public LoggerUtilImpl() {
      bsResult = new ByteArrayOutputStream();
      handler = new StreamHandler(bsResult, new SimpleFormatter());
      logger = Logger.getLogger(LoggerUtilImpl.class.getName());
      logger.addHandler(handler);
    }

    @Override
    public Logger getLogger() {
      return Logger.getLogger(LoggerUtilImpl.class.getName());
    }

    public String getLogMessage() {
      handler.close();
      logger.removeHandler(handler);
      return bsResult.toString();
    }
  }

  class IsEvaluated {
    public boolean isEval = false;
  }
}
