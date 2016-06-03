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
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("First_Edited", "Last_Edited", "Nickname_Edited", "Mrs_Edited", "DreamCompany_Edited", "scotland yard_Edited", "1234567_Edited", "first.last@dreamcompany.com_Edited"));
    app.getContactHelper().updateContact();
    app.getContactHelper().returnToHomePage();
  }
}
