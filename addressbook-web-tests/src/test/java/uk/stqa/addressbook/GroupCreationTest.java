package uk.stqa.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("group", "header", "footer"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
