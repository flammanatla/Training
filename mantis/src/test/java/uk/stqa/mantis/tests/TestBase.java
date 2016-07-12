package uk.stqa.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;
import uk.stqa.mantis.appmanager.ApplicationManager;


/**
 * Created by natla on 02/06/2016.
 */
public class TestBase {

  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

}
