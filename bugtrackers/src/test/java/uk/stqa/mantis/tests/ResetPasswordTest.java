package uk.stqa.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.stqa.mantis.helpers.HttpSession;
import uk.stqa.mantis.model.MailMessage;
import uk.stqa.mantis.model.UserData;
import uk.stqa.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by natla on 16/07/2016.
 */
public class ResetPasswordTest extends TestBase{

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException, MessagingException {
    app.admin().login(app.getProperty("login"), app.getProperty("password"));
    String newpassword = "reset";

    Users before = app.db().users();
    UserData selectedUser = before.iterator().next();
    UserData user = new UserData().withId(selectedUser.getId()).withUsername(selectedUser.getUsername()).withEmail(selectedUser.getEmail());
    app.admin().resetPassword(user);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = app.user().findConfirmationLink(mailMessages, user.getEmail());
    app.user().finish(confirmationLink, newpassword);

    HttpSession session = app.newSession();
    assertTrue(session.login(selectedUser.getUsername(), newpassword));
    assertTrue(session.isLoggedInAs(selectedUser.getUsername()));
  }

  @AfterMethod
  public void stopMailServer(){
    app.mail().stop();
  }
}
