package uk.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import uk.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by natla on 03/06/2016.
 */
public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().groupAvailable()) {
      app.getGroupHelper().createGroup(new GroupData("group", "header", "header"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroups(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
