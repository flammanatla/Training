package uk.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;
import uk.stqa.addressbook.model.Contacts;
import uk.stqa.addressbook.model.GroupData;
import uk.stqa.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("group").withHeader("header").withFooter("footer"));
        }
    }

    @Test(dataProvider = "validContactsFromJSON")
    public void testContactCreation(ContactData contact) {
        Groups groups = app.db().groups();
        app.goTo().HomePage();
        Contacts before = app.db().contacts();
        app.contact().create(contact.inGroup(groups.iterator().next()));
        assertThat(app.contact().getContactCounter(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        verifyContactListUI();
    }
}
