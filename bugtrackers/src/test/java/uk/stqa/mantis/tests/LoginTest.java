package uk.stqa.mantis.tests;

import org.testng.annotations.Test;
import uk.stqa.mantis.helpers.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by natla on 15/07/2016.
 */
public class LoginTest extends TestBase{

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator", "root"));
    assertTrue(session.isLoggedInAs("administrator"));
  }


}
