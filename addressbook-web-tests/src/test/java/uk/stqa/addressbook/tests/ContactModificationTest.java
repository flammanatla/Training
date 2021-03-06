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
    app.goTo().HomePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().
              withFirstName("First").withLastName("Last").withAddress("scotland yard").
              withMobileT("1234567").withEmail("first.last@dreamcompany.com").
              withPhoto("src/test/resources/logo.jpeg"));
        }
    }

    @Test
    public void testContactModification(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().
                withId(modifiedContact.getId()).
                withFirstName("First_Edited").withLastName("Last_Edited").withAddress("Дом на курьих ножках").
                withMobileT("321").withEmail("first.last@dreamcompany.net").
                withPhoto("src/test/resources/logo.jpeg");
        app.contact().edit(contact);
        assertThat(app.contact().getContactCounter(), equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListUI();
    }

}
