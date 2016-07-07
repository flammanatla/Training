package uk.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;
import uk.stqa.addressbook.model.Contacts;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by natla on 03/06/2016.
 */
public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().
              withFirstName("First").withLastName("Last").withAddress("scotland yard").
              withHomeT("1234567").withEmail("first.last@dreamcompany.com").
              withPhoto("src/test/resources/logo.jpeg"));
    }
  }

  @Test
  public void testContactDeletion(){
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().HomePage();
    assertThat(app.contact().getContactCounter(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListUI();
  }

}
