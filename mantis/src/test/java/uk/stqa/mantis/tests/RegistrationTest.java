package uk.stqa.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import uk.stqa.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by natla on 16/07/2016.
 */
public class RegistrationTest extends TestBase {

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    long salt = new Date().getTime();
    String user = String.format("user%s", salt);
    String email = String.format("user%s@local.local", salt);
    String password = "password";

    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
    VerbalExpression regexp = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regexp.getText(mailMessage.text);
  }

  @AfterMethod
  public void stopMailServer(){
    app.mail().stop();
  }

}
