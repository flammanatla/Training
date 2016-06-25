package uk.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;
import uk.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by natla on 04/06/2016.
 */
public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().
              withFirstName("First").withLastName("Last").withAddress("scotland yard").
              withHomeNumber("1234567").withEmail("first.last@dreamcompany.com"));
    }
  }

  @Test
  public void testContactModification(){
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).
            withFirstName("First").withLastName("Last").withAddress("scotland yard").
            withHomeNumber("1234567").withEmail("first.last@dreamcompany.com");
    app.contact().edit(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()));

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(modifiedContact)));
  }

}
