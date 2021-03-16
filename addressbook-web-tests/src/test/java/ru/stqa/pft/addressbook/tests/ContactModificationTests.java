package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().groupPage();
      if (app.db().groups().size() == 0) {
        app.group().create(new GroupData().withName("test").withHeader("test").withFooter("test"));
      }
      app.contact().create(
          new ContactData().withFirstname("Now").withLastname("rew").withAddress("N.n, n54")
              .withHome_phone("255-25-25").withMobile_phone("4533").withWork_phone("4664")
              .withFax("4").withEmail("dfsdf@sca.adf").withEmail2("dgagddg.sdg@dsf.fgr")
              .withEmail3("afdf@dfda.sdv").withPhoto(new File("src/test/resources/images.png"))
              .withGroup("test"), true);
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().home();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    app.contact().selectById(modifiedContact.getId());
    ContactData contact =
        new ContactData().withId(modifiedContact.getId())
            .withFirstname("Modify").withLastname("uy").withAddress("N.n, n54")
            .withHome_phone("255-25-25").withMobile_phone("4533").withWork_phone("4664")
            .withFax("4").withEmail("dfsdf@sca.adf").withEmail2("dgagddg.sdg@dsf.fgr")
            .withEmail3("afdf@dfda.sdv").withPhoto(new File("src/test/resources/mod.png"));

    app.contact().modify(contact, false);
    app.contact().update();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}