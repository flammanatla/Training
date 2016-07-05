package uk.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import uk.stqa.addressbook.model.ContactData;
import uk.stqa.addressbook.model.Contacts;

import java.io.File;
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
    attach(By.name("photo"), new File(contactData.getPhoto()));

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
    type(By.name("home"),contactData.getHomeT());
    type(By.name("mobile"),contactData.getMobileT());
    type(By.name("work"),contactData.getWorkT());
    type(By.name("email"),contactData.getEmail());
  }

  public void selectContacts(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  private void selectContactsById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  private void editSelectedContactById(int id) {
    //wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  private void viewSelectedContactById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
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
    contactsCache = null;
    returnToHomePage();
  }

  public void edit(ContactData contact) {
    editSelectedContactById(contact.getId());
    fillContactForm(contact, false);
    updateContact();
    contactsCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactsById(contact.getId());
    deleteSelectedContacts();
    isAlertPresent();
    contactsCache = null;
  }

  public boolean contactAvailable() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCounter() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    editSelectedContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String homeT = wd.findElement(By.name("home")).getAttribute("value");
    String mobileT = wd.findElement(By.name("mobile")).getAttribute("value");
    String workT = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).
            withAddress(address).withHomeT(homeT).withMobileT(mobileT).withWorkT(workT).
            withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public ContactData infoFromDetailsPage(ContactData contact) {
    viewSelectedContactById(contact.getId());
    String content = wd.findElement(By.id("content")).getText();
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withAllInfo(content);
  }

  public Contacts contactsCache = null;

  public Contacts all() {
    if (contactsCache != null) {
      return new Contacts(contactsCache);
    }
    contactsCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element: elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      String address = element.findElement(By.xpath(".//td[4]")).getText();
      String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
      String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).
              withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails).
              withAllInfo(lastName+firstName+address+allEmails+allPhones);
      contactsCache.add(contact);
    }
    return new Contacts(contactsCache);
  }

}
