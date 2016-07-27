package uk.stqa.bugify.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import uk.stqa.appmanager.ApplicationManager;

import java.io.IOException;


/**
 * Created by natla on 02/06/2016.
 */
public class TestBase {

  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.stop();
  }

  boolean isIssueClosed(int issueId) throws IOException {
    HttpResponse response = app.rest().getExecutor().
            execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId))).returnResponse();

    int code = response.getStatusLine().getStatusCode();
    if (code == 404) {
      System.out.println("The specified issue does not exist");
      return true;
    }

    String json = EntityUtils.toString(response.getEntity());
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issue = parsed.getAsJsonObject().getAsJsonArray("issues").get(0);
    String state = issue.getAsJsonObject().getAsJsonPrimitive("state_name").getAsString();
    if (state.equals("Closed")) {
      System.out.println("Issue " + issueId + " is Closed");
      return true;
    }

    return false; //test isn't ready to run yet
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (!isIssueClosed(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
