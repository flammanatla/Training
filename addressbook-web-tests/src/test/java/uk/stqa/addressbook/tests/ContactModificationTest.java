package uk.stqa.addressbook.tests;

import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;

/**
 * Created by natla on 04/06/2016.
 */
public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().contactAvailable()) {
      app.getContactHelper().createContact(new ContactData("First", "Last", "scotland yard", null, "1234567", "first.last@dreamcompany.com"), true);
    }
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("First_Edited", "Last_Edited", "scotland yard_Edited", null, "1234567_Edited", "first.last@dreamcompany.com_Edited"), false);
    app.getContactHelper().updateContact();
    app.getContactHelper().returnToHomePage();
  }
}
