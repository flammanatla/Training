package uk.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import uk.stqa.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(new GroupData("group", "header", "header"));
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1 );
    }

}
