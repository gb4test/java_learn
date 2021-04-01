package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;


public class TestBase {

  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
  }


  public String getStateIssue(int id) {
    String json = RestAssured.get("https://bugify.stqa.ru/api/issues/" + id + ".json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Issue[] issue = new Gson().fromJson(issues, new TypeToken<Issue[]>(){}.getType());
    return issue[0].getState_name();
  }

  public boolean isIssueOpen(int id) {
    String issueState = getStateIssue(id);
    if (issueState.equals("Resolved") || issueState.equals("Closed")) {
      return false;
    }
    return true;
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}