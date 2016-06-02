package uk.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import uk.stqa.addressbook.model.ContactData;

/**
 * Created by natla on 02/06/2016.
 */
public class ContactHelper extends HelperBase{

  public ContactHelper(FirefoxDriver wd) {
    super (wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void saveContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"),contactData.getTitle());
    type(By.name("company"),contactData.getCompany());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getHomeNumber());
    type(By.name("email"),contactData.getEmail());
  }
}
