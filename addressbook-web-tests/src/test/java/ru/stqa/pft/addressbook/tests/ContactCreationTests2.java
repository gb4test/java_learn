package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests2 extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().checkGroup();
    app.getNavigationHelper().goToHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("CreatedNow", "rew",
            "N.n, n  54", "46546", "4533", "4664", "4",
            "dfsdf@sca.adf", "dgagddg.sdg@dsf.fgr", "afdf@dfda.sdv", "test");
    app.getContactHelper().createContact (contact, true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}