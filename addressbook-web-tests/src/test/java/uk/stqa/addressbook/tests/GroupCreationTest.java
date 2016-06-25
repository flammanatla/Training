package uk.stqa.addressbook.tests;

import org.testng.annotations.Test;
import uk.stqa.addressbook.model.GroupData;
import uk.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("group").withHeader("header").withFooter("footer");
        app.group().create(group);
        assertThat(app.group().getGroupConuter(), equalTo(before.size() + 1));
        Groups after = app.group().all();

        assertThat(after, equalTo(before.withAdded(
                group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}
