package uk.stqa.addressbook.tests;

import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoAddContactPage();
        app.getContactHelper().fillContactForm(new ContactData("First", "Last", "Nickname", "Mrs", "DreamCompany", "scotland yard", "1234567", "first.last@dreamcompany.com"));
        app.getContactHelper().saveContact();
        app.getContactHelper().returnToHomePage();
    }

}
