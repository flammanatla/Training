package uk.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;
import uk.stqa.addressbook.model.Contacts;
import uk.stqa.addressbook.model.GroupData;
import uk.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by natla on 08/07/2016.
 */
public class ContactInGroupsTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().
                    withFirstName("First").withLastName("Last").withAddress("scotland yard").
                    withHomeT("1234567").withEmail("first.last@dreamcompany.com").
                    withPhoto("src/test/resources/logo.jpeg"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("zero"));
        }
        app.goTo().HomePage();
    }

    @Test
    public void testContactAddedInGroup(){
        Contacts contactSetBefore = app.db().contacts();
        ContactData contactBefore = contactSetBefore.iterator().next();

        Groups groupSetBefore = app.db().groups();
        //find group without contactBefore yet
        GroupData selectedGroup = app.contact().findUniqueGroup(contactBefore, groupSetBefore);
        //if no such group found, create new one
        if (selectedGroup == null) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("UniqueGroup"));
            app.goTo().HomePage();
            Groups groupSetAfter = app.db().groups();
            selectedGroup = selectedGroup.withId(groupSetAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        }

        ContactData contact = new ContactData().withId(contactBefore.getId()).
                withFirstName(contactBefore.getFirstName()).withLastName(contactBefore.getLastName()).
                withEmail(contactBefore.getEmail()).withMobileT(contactBefore.getMobileT()).
                inGroup(selectedGroup);
        app.contact().addToGroup(contact);
        Contacts contactSetAfter = app.db().contacts();

        //find contact in contactSet after modification
        ContactData contactAfter = app.contact().findModifiedContact(contactSetAfter, contactBefore);

        assertThat(contactSetAfter, equalTo(contactSetBefore.without(contactBefore).withAdded(contact)));
        //verify contact successfully added to <selectedGroup>
        assertThat(contactAfter.getGroups(), equalTo(contactBefore.getGroups().withAdded(selectedGroup)));
    }

    @Test
    public void testContactDeletedFromGroup(){
        Contacts contactSetBefore = app.db().contacts();
        ContactData contactBefore = contactSetBefore.iterator().next();

        Groups groupSetBefore = app.db().groups();
        GroupData selectedGroup = groupSetBefore.iterator().next();
        ContactData contact = new ContactData().withId(contactBefore.getId()).
                withFirstName(contactBefore.getFirstName()).withLastName(contactBefore.getLastName()).
                withEmail(contactBefore.getEmail()).withMobileT(contactBefore.getMobileT()).
                inGroup(selectedGroup);

        //if contactBefore doesn't belong to selectedGroup, add it:
        if (!contactBefore.getGroups().contains(selectedGroup)) {
            app.contact().addToGroup(contact);
            app.goTo().HomePage();
        }
        app.contact().removeFromGroup(contact);
        Contacts contactSetAfter = app.db().contacts();

        //find contact in contactSet after modification
        ContactData contactAfter = app.contact().findModifiedContact(contactSetAfter, contactBefore);

        assertThat(contactSetAfter, equalTo(contactSetBefore.without(contactBefore).withAdded(contact)));
        //verify contact successfully removed from <selectedGroup>
        assertThat(contactAfter.getGroups(), equalTo(contactBefore.getGroups().without(selectedGroup)));
    }

}
