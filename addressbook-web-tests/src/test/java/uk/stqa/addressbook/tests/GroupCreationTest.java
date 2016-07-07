package uk.stqa.addressbook.tests;

import org.testng.annotations.Test;
import uk.stqa.addressbook.model.GroupData;
import uk.stqa.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTest extends TestBase {

    @Test(dataProvider = "validGroupsFromJSON")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        app.group().create(group);
        assertThat(app.group().getGroupConuter(), equalTo(before.size() + 1));
        Groups after = app.db().groups();

        assertThat(after, equalTo(before.withAdded(
                group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        verifyGroupListUI();
    }
}
