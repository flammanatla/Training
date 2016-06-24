package uk.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().HomePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstName("First").withLastName("Last").withAddress("scotland yard").
            withHomeNumber("1234567").withEmail("first.last@dreamcompany.com");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1 );

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
