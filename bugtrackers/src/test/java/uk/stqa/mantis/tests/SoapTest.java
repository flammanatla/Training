package uk.stqa.mantis.tests;

import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uk.stqa.mantis.model.Issue;
import uk.stqa.mantis.model.Project;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by natla on 25/07/2016.
 */
public class SoapTest extends TestBase{

  @Test()
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    skipIfNotFixed(1);
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(2);
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Summary").withDescription("Description").
            withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }
}
