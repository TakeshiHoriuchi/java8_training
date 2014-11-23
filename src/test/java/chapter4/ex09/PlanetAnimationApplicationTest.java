package chapter4.ex09;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.Assertions;
import org.loadui.testfx.GuiTest;

public class PlanetAnimationApplicationTest extends GuiTest {
  
  @Test
  public void testPlanet_is_exist() {
    Assertions.verifyThat("#planet", planet -> planet.isVisible());
  }

  @Override
  protected Parent getRootNode() {
    return PlanetAnimationApplication.getRoot();
  }

}