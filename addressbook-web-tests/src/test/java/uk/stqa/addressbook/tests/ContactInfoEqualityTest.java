package uk.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.stqa.addressbook.model.ContactData;
import java.util.stream.Collectors;

import static java.util.Arrays.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by natla on 25/06/2016.
 */
public class ContactInfoEqualityTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().
              withFirstName("First").withLastName("Last").withAddress("scotland yard").
              withHomeT("123").withMobileT("456").withWorkT("789").
              withEmail("first.last@dreamcompany.com").withEmail2("qa@qa.com").withEmail3("qa-test@qa.com"));
    }
  }

  @Test
  public void testContactMainPage(){
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  @Test
  public void testContactDetailsPage() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromDetailsPage = app.contact().infoFromDetailsPage(contact);
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(mergeEditInfo(contactInfoFromEditForm), equalTo(mergeDetailInfo(contactInfoFromDetailsPage)));
  }

  private String mergePhones(ContactData contact) {
    return asList(contact.getHomeT(), contact.getMobileT(), contact.getWorkT())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactInfoEqualityTest::cleanedPhones)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  private String mergeDetailInfo(ContactData contact) {
    return asList(contact.getInfo())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactInfoEqualityTest::cleanedDetails)
            .collect(Collectors.joining(""));
  }

  private String mergeEditInfo(ContactData contact) {
    return asList(contact.getFirstName(), contact.getLastName(), contact.getAddress(),
            contact.getHomeT(), contact.getMobileT(), contact.getWorkT(),
            contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactInfoEqualityTest::cleanedDetails)
            .collect(Collectors.joining(""));
  }

  public static String cleanedPhones(String phones) {
    return phones.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  public static String cleanedDetails(String info) {
    return info.replaceAll("\\s", "").replaceAll("(H|M|W):", "").replaceAll("\\(www.*?\\)", "");
  }
}