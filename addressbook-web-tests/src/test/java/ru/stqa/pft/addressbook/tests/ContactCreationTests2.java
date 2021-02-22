package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests2 extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().checkGroup();
    app.getNavigationHelper().goToHome();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact (new ContactData("Lui", " rew",
            "N.n, n  54", "46546", "4533", "4664", "4",
            "dfsdf@sca.adf", "dgagddg.sdg@dsf.fgr", "afdf@dfda.sdv", "test"), true);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }
}