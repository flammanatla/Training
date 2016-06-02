package uk.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by natla on 02/06/2016.
 */
public class NavigationHelper extends HelperBase{


  public NavigationHelper(FirefoxDriver wd) {
    super (wd);
  }

  public void gotoAddContactPage() {
    click(By.linkText("add new"));
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }
}
