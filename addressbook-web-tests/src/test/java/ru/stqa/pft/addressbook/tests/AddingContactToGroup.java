package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class AddingContactToGroup extends TestBase {

  private ContactData requiredContact;
  private GroupData requiredGroup;

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test").withHeader("test").withFooter("test"));
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().home();
      app.contact().create(
          new ContactData().withFirstname("Now").withLastname("rew").withAddress("N.n, n54")
              .withHome_phone("255-25-25").withMobile_phone("4533").withWork_phone("4664")
              .withFax("4").withEmail("dfsdf@sca.adf").withEmail2("dgagddg.sdg@dsf.fgr")
              .withEmail3("afdf@dfda.sdv").withPhoto(new File("src/test/resources/images.png")));
    }

    ContactData contact = app.db().contacts().iterator().next();
    GroupData group = app.db().groups().iterator().next();

    if (contact.getGroups().contains(group)) {
      requiredContact = contact;
      requiredGroup = group;
      app.contact().deletingFromGroup(requiredContact, requiredGroup);
    }
  }


  @Test
  public void testAddingContactToGroup() {

    ContactData contact = app.db().contacts().iterator().next();
    Groups groups = app.db().groups();

    for (GroupData group : groups) {
      if (!contact.getGroups().contains(group)) {
        requiredContact = contact;
        requiredGroup = group;
      }
    }

    app.contact().goToHome();
    app.contact().addingToGroup(requiredContact, requiredGroup);
    app.db().refresh(contact);
    assertThat(requiredContact.getGroups(), hasItem(requiredGroup));

  }
}