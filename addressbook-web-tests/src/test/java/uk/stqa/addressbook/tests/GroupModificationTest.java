package uk.stqa.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import uk.stqa.addressbook.model.GroupData;

/**
 * Created by natla on 03/06/2016.
 */
public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroups();
    app.getGroupHelper().modifySelectedGroups();
    app.getGroupHelper().fillGroupForm(new GroupData("groupEdited", "headerEdited", "footerEdited"));
    app.getGroupHelper().selectFromDropDownList(By.name("group_parent_id"), 1);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }
}
