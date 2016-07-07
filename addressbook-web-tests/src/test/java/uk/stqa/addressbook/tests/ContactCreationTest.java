package uk.stqa.addressbook.tests;

import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;
import uk.stqa.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTest extends TestBase {

  @Test(dataProvider = "validContactsFromJSON")
  public void testContactCreation(ContactData contact) {
    app.goTo().HomePage();
    Contacts before = app.db().contacts();
    app.contact().create(contact);
    assertThat(app.contact().getContactCounter(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    verifyContactListUI();
  }

}
