package uk.stqa.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.stqa.addressbook.model.GroupData;
import uk.stqa.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by natla on 03/06/2016.
 */
public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("group").withHeader("header").withFooter("footer"));
    }
  }

  @Test
  public void testGroupDeletion(){
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.group().getGroupConuter(), equalTo(before.size() - 1));
    Groups after = app.db().groups();

    before.remove(deletedGroup);
    Assert.assertEquals(before, after);

    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupListUI();
  }

}
