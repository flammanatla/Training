package uk.stqa.addressbook.tests;

import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;

/**
 * Created by natla on 03/06/2016.
 */
public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().contactAvailable()) {
      app.getContactHelper().createContact(new ContactData("First", "Last", "scotland yard", null, "1234567", "first.last@dreamcompany.com"), true);
    }
    app.getContactHelper().selectContacts();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().isAlertPresent();
    app.getNavigationHelper().gotoHomePage();
  }
}
