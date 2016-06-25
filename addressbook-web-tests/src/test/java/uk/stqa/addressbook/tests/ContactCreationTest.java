package uk.stqa.addressbook.tests;

import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;
import uk.stqa.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("First").withLastName("Last").withAddress("scotland yard").
            withHomeT("123").withMobileT("456").withWorkT("789").withEmail("first.last@dreamcompany.com");
    app.contact().create(contact);
    assertThat(app.contact().getContactConuter(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
