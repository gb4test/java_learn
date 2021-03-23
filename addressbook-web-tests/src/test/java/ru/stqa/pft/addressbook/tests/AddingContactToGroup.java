package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class AddingContactToGroup extends TestBase {

  private ContactData requiredContact;
  private GroupData requiredGroup;

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().contacts().size() == 0) {
      app.goTo().home();
      app.contact().create(
          new ContactData().withFirstname("Now").withLastname("rew").withAddress("N.n, n54")
              .withHome_phone("255-25-25").withMobile_phone("4533").withWork_phone("4664")
              .withFax("4").withEmail("dfsdf@sca.adf").withEmail2("dgagddg.sdg@dsf.fgr")
              .withEmail3("afdf@dfda.sdv").withPhoto(new File("src/test/resources/images.png")));
    }

    Contacts contacts = app.db().contacts();
    ArrayList<Integer> sizeList = new ArrayList<>();

    for (ContactData contact : contacts) {
      sizeList.add(contact.getGroups().size());
    }

    if (app.db().groups().size() == 0 || Collections.min(sizeList) == app.db().groups().size()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("POPS"));
    }
  }


  @Test
  public void testAddingContactToGroup() {

    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();

    for (ContactData contact : contacts) {
      for (GroupData group : groups) {
        if (!contact.getGroups().contains(group)) {
          requiredContact = contact;
          requiredGroup = group;
        }
      }
    }

    app.contact().goToHome();
    app.contact().addingToGroup(requiredContact, requiredGroup);
    app.db().refresh(requiredContact);
    assertThat(requiredContact.getGroups(), hasItem(requiredGroup));
  }
}