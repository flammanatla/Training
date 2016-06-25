package uk.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import uk.stqa.addressbook.model.ContactData;
import uk.stqa.addressbook.model.Contacts;
import java.util.List;

/**
 * Created by natla on 02/06/2016.
 */
public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super (wd);
  }

  //link appeared after contact creation/modification
  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void gotoAddNewContactPage() {
    click(By.linkText("add new"));
  }

  public void saveContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean create_or_modify) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());

    if (create_or_modify) {
      String group = contactData.getGroup();
      Select select = new Select(wd.findElement(By.name("new_group")));
      if (group == null) {
        select.selectByIndex(0); //[none] will be selected, help avoid NullPointerException
      } else {
        select.selectByVisibleText(group); // specified in tests group will be selected
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getHomeNumber());
    type(By.name("email"),contactData.getEmail());
  }

  public void selectContacts(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  private void selectContactsById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  private void editSelectedContactById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void editSelectedContact(int index) {
    wd.findElements(By.xpath("//tr[@name='entry']//td[8]")).get(index).click();
  }

  public void updateContact() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void create(ContactData contact) {
    gotoAddNewContactPage();
    fillContactForm(contact, true);
    saveContact();
    returnToHomePage();
  }

  public void edit(ContactData contact) {
    editSelectedContactById(contact.getId());
    fillContactForm(contact, false);
    updateContact();
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactsById(contact.getId());
    deleteSelectedContacts();
    isAlertPresent();
  }

  public boolean contactAvailable() {
    return isElementPresent(By.name("selected[]"));
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element: elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
      contacts.add(contact);
    }
    return contacts;
  }
}
