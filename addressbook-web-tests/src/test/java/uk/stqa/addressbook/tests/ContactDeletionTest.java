package uk.stqa.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by natla on 03/06/2016.
 */
public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContacts();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().isAlertPresent();
    app.getNavigationHelper().gotoHomePage();
  }
}
