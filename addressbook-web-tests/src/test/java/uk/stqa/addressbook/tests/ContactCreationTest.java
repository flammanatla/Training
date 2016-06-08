package uk.stqa.addressbook.tests;

import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoAddNewContactPage();
    app.getContactHelper().fillContactForm(new ContactData("First", "Last", "scotland yard", "group", "1234567", "first.last@dreamcompany.com"), true);
    app.getContactHelper().saveContact();
    app.getContactHelper().returnToHomePage();

  }

}
