package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().groupPage();
      if (app.db().groups().size() == 0) {
        app.group().create(new GroupData().withName("test").withHeader("test").withFooter("test"));
      }
      Groups groups = app.db().groups();
      app.contact().create(
          new ContactData().withFirstname("CreatedNow").withLastname("rew")
              .withAddress("Desmond Smith\n" +
                  "76 Knockwellan Park\n" +
                  "Londonderry BT47 2JD")
              .withHome_phone("255-25-25").withMobile_phone("45 33").withWork_phone("+4(664) ")
              .withFax("4").withEmail("dfs_df@sca.adf").withEmail2("dg-agddg.sdg@dsf.fgr")
              .withEmail3("afdf@dfda.sdv").inGroup(groups.iterator().next()), true);
    }
    app.goTo().home();
  }

  @Test
  public void testContactAddress() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }
}