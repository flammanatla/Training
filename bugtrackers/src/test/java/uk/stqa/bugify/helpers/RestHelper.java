package uk.stqa.bugify.helpers;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import uk.stqa.appmanager.ApplicationManager;
import uk.stqa.bugify.model.Issue;

import java.io.IOException;
import java.util.Set;

/**
 * Created by natla on 26/07/2016.
 */
public class RestHelper {
  private ApplicationManager app;

  public RestHelper(ApplicationManager app) {
    this.app = app;
  }

  public Executor getExecutor() {
    return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  }

  public int createIssue(Issue issue) throws IOException {
    String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json").
            bodyForm(new BasicNameValuePair("subject", issue.getSubject()),
                    new BasicNameValuePair("description", issue.getDescription()))).
            returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")).
            returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }

    //{"error":{"code":404,"message":"The specified issue does not exist."}}

//    {"total":1,"page":1,"limit":1,"issues":[{"assignee":{"id":"1","created":"2016-07-26T21:00:04+00:00","updated":"2016-07-26T21:00:04+00:00","firstname":"Demo","lastname":"User","name":"Demo User","email":"demo@bugify.com","username":"demo","notifications":{"creator":true,"assignee":true,"following":true,"commented":true,"mychange":true,"mentioned":true,"allcreates":true},"groups":[],"settings":[],"owner":true,"timezone":"UTC","state":1,"api_key":"LSGjeU4yP1X493ud1hNniA=="},"assignee_id":1,"category_id":0,"created":"2016-07-26T21:00:04+00:00","creator":{"id":"1","created":"2016-07-26T21:00:04+00:00","updated":"2016-07-26T21:00:04+00:00","firstname":"Demo","lastname":"User","name":"Demo User","email":"demo@bugify.com","username":"demo","notifications":{"creator":true,"assignee":true,"following":true,"commented":true,"mychange":true,"mentioned":true,"allcreates":true},"groups":[],"settings":[],"owner":true,"timezone":"UTC","state":1,"api_key":"LSGjeU4yP1X493ud1hNniA=="},"creator_id":1,"description":"Sample issue description.","id":5,"labels":[],"milestone_id":0,"percentage":0,"priority":"2","priority_name":"High","project":{"id":"1","created":"2016-07-26T21:00:03+00:00","updated":"2016-07-26T21:00:03+00:00","name":"Blackbird","slug":"blackbird","categories":[],"description":"","state":"1"},"project_id":1,"related_issue_ids":[],"resolved":"1970-01-01T00:00:00+00:00","state":"1","state_name":"In Progress","subject":"Sample issue 5","updated":"2016-07-26T21:06:18+00:00"}]}
}
