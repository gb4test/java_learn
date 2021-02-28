package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().groupPage();
            app.group().check();
            app.contact().create(
                    new ContactData().withFirstname("CreatedNow").withLastname("rew").withAddress("N.n, n54")
                    .withHome_phone("255-25-25").withMobile_phone("4533").withWork_phone("4664")
                    .withFax("4").withEmail("dfsdf@sca.adf").withEmail2("dgagddg.sdg@dsf.fgr")
                    .withEmail3("afdf@dfda.sdv").withGroup("test"), true);
        }
        app.goTo().home();
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        app.contact().choose(modifiedContact.getId());
        ContactData contact =
                new ContactData().withId(modifiedContact.getId())
                        .withFirstname("Modify").withLastname("uy").withAddress("N.n, n54")
                        .withHome_phone("255-25-25").withMobile_phone("4533").withWork_phone("4664")
                        .withFax("4").withEmail("dfsdf@sca.adf").withEmail2("dgagddg.sdg@dsf.fgr")
                        .withEmail3("afdf@dfda.sdv");

        app.contact().modify(contact, false);
        app.contact().update();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}