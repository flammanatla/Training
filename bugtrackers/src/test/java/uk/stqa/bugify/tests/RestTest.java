package uk.stqa.bugify.tests;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import uk.stqa.bugify.model.Issue;
import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by natla on 26/07/2016.
 */
public class RestTest extends TestBase {

  @BeforeClass
  public void init(){
    RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
  }

  @Test
  public void testCreateIssue() throws IOException {
    skipIfNotFixed(100);
    Set<Issue> before = app.rest().getIssues();
    Issue issue = new Issue().withSubject("SubjectNM").withDescription("DescriptionNM");
    int issueId = app.rest().createIssue(issue);
    Set<Issue> after = app.rest().getIssues();
    before.add(issue.withId(issueId));
    assertEquals(before, after);
  }
}
