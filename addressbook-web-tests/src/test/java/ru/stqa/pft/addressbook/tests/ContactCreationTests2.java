package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests2 extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    app.group().check();
    app.goTo().home();
  }

  @Test
  public void testContactCreation() {
    Contacts before = app.contact().all();
    ContactData contact =
            new ContactData().withFirstname("CreatedNow").withLastname("rew").withAddress("N.n, n54")
                    .withHome_phone("255-25-25").withMobile_phone("4533").withWork_phone("4664")
                    .withFax("4").withEmail("dfsdf@sca.adf").withEmail2("dgagddg.sdg@dsf.fgr")
                    .withEmail3("afdf@dfda.sdv").withGroup("test");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}