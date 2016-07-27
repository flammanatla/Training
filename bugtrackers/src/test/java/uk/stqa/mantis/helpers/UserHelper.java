package uk.stqa.mantis.helpers;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import uk.stqa.appmanager.ApplicationManager;
import uk.stqa.mantis.model.MailMessage;

import java.util.List;

/**
 * Created by natla on 16/07/2016.
 */
public class UserHelper extends HelperBase{

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
    VerbalExpression regexp = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regexp.getText(mailMessage.text);
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }
}
