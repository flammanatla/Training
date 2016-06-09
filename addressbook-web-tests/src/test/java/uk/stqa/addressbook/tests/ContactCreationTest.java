package uk.stqa.addressbook.tests;

import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    ContactData data = new ContactData("First", "Last", "scotland yard", null, "1234567", "first.last@dreamcompany.com");
    app.getContactHelper().createContact(data);
  }

}
