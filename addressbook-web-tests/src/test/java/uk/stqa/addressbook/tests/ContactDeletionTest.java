package uk.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by natla on 03/06/2016.
 */
public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().contactAvailable()) {
      app.getContactHelper().createContact(new ContactData("First", "Last", "scotland yard", null, "1234567", "first.last@dreamcompany.com"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContacts(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().isAlertPresent();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
