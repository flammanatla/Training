package uk.stqa.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase{

  @Test
    public void testContactCreation() {
        gotoAddContactPage();
        fillContactForm(new ContactData("First", "Last", "Nickname", "Mrs", "DreamCompany", "scotland yard", "1234567", "first.last@dreamcompany.com"));
        saveContact();
        returnToHomePage();
    }

}
