package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests {

  @Test
  public void testCreateIssue() throws IOException {
    int oldIssues = getTotal();
    Issue newIssue = new Issue().withSubject("Definite Test issue").withDescription("New test issue");
    createIssue(newIssue);
    int newIssues = getTotal();
    assertEquals(newIssues, oldIssues + 1);
  }

  private Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
        .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  private int getTotal() throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
        .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    int total = parsed.getAsJsonObject().get("total").getAsInt();
    return total;
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("3e705c82933119827aa93e56b6754242", "");
  }

  private int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
        .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
            new BasicNameValuePair("description", newIssue.getDescription())))
        .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }
}