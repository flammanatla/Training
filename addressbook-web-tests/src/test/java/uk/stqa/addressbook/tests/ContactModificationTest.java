package uk.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by natla on 04/06/2016.
 */
public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    if (! app.getContactHelper().contactAvailable()) {
      app.getContactHelper().createContact(new ContactData("First", "Last", "scotland yard", null, "1234567", "first.last@dreamcompany.com"));
    }
    app.getContactHelper().editContact(before.size()-1);
    ContactData contact = new ContactData(before.get(before.size() -1).getId(), "First_Edited", "Last_Edited", "scotland yard_Edited", null, "1234567_Edited", "first.last@dreamcompany.com_Edited");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().updateContact();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}
