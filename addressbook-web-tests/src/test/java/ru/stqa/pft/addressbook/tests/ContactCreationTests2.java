package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests2 extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().checkGroup();
    app.getNavigationHelper().goToHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact (new ContactData("Lui", " rew",
            "N.n, n  54", "46546", "4533", "4664", "4",
            "dfsdf@sca.adf", "dgagddg.sdg@dsf.fgr", "afdf@dfda.sdv", "test"), true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}