package uk.stqa.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import uk.stqa.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by natla on 03/06/2016.
 */
public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().groupAvailable()) {
      app.getGroupHelper().createGroup(new GroupData("group", "header", "header"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroups(before.size() - 1);
    app.getGroupHelper().modifySelectedGroups();
    app.getGroupHelper().fillGroupForm(new GroupData("groupEdited", "headerEdited", null));
    app.getGroupHelper().selectFromDropDownList(By.name("group_parent_id"), 0);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());
  }
}
