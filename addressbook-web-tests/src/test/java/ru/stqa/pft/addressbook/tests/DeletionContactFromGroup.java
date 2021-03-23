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
import static org.hamcrest.Matchers.not;


public class DeletionContactFromGroup extends TestBase {

  private ContactData requiredContact;
  private GroupData requiredGroup;


  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test").withHeader("test").withFooter("test"));
    }

    if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      app.goTo().home();
      app.contact().create(
          new ContactData().withFirstname("Morella").withLastname("Turner")
              .withPhoto(new File("src/test/resources/images.png"))
              .inGroup(groups.iterator().next()));
    }

    Contacts contacts = app.db().contacts();
    ArrayList<Integer> sizeList = new ArrayList<>();

    for (ContactData contact : contacts) {
      sizeList.add(contact.getGroups().size());
    }

    if (Collections.max(sizeList) < app.db().groups().size()) {
      ContactData contact = app.db().contacts().iterator().next();
      GroupData group = app.db().groups().iterator().next();
      app.contact().addingToGroup(contact, group);
    }
  }


  @Test
  public void testDeletionContactToGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();

    for (ContactData contact : contacts) {
      for (GroupData group : groups) {
        if (contact.getGroups().contains(group)) {
          requiredContact = contact;
          requiredGroup = group;
        }
      }
    }

    app.contact().goToHome();
    app.contact().deletingFromGroup(requiredContact, requiredGroup);
    app.db().refresh(requiredContact);
    assertThat(requiredContact.getGroups(), not(hasItem(requiredGroup)));

  }
}