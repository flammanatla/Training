package uk.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import uk.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by natla on 02/06/2016.
 */
public class GroupHelper extends HelperBase{

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getGroupName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  //link appeared after group creation/deletion/modification
  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void selectGroups(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedGroups() {
    click(By.xpath("//div[@id='content']/form/input[5]"));
  }

  public void modifySelectedGroups() {
    click(By.xpath("//div[@id='content']/form/input[6]"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public boolean groupAvailable() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupConuter() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> getGroupList() {
    List<GroupData> groups = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData(id, name, null, null);
      groups.add(group);
    }
    return groups;
  }
}
