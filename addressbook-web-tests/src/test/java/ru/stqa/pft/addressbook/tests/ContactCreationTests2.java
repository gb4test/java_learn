package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests2 extends TestBase {

  @Test
  public void testContactCreation() {

    app.getContactHelper().createContact (new ContactData("new", " rew",
            "N.n, n  54", "46546", "4533", "4664", "4",
            "dfsdf@sca.adf", "dgagddg.sdg@dsf.fgr", "afdf@dfda.sdv", "test"), true);
  }
}