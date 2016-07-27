package uk.stqa.bugify.tests;

import org.testng.annotations.Test;
import uk.stqa.bugify.model.Issue;
import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by natla on 26/07/2016.
 */
public class RestTest extends TestBase {

  @Test
  public void testCreateIssue() throws IOException {
    skipIfNotFixed(4);
    Set<Issue> before = app.rest().getIssues();
    Issue issue = new Issue().withSubject("SubjectNM").withDescription("DescriptionNM");
    int issueId = app.rest().createIssue(issue);
    Set<Issue> after = app.rest().getIssues();
    before.add(issue.withId(issueId));
    assertEquals(before, after);
  }
}
