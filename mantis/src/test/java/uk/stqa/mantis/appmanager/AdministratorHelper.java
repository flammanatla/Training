package uk.stqa.mantis.appmanager;

import org.openqa.selenium.By;
import uk.stqa.mantis.model.UserData;
import uk.stqa.mantis.tests.TestBase;


/**
 * Created by natla on 16/07/2016.
 */
public class AdministratorHelper extends HelperBase{

  public AdministratorHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseURL") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void resetPassword(UserData user) {
    click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_overview_page.php']"));
    click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_user_page.php']"));
    type(By.name("username"), user.getUsername());
    click(By.cssSelector("input[value='Manage User']"));
    click(By.cssSelector("input[value='Reset Password']"));
  }

}
