package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

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
    }

    @Test
    public void testContactModification() {
        app.goTo().home();
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        app.contact().choose(modifiedContact.getId());
        ContactData contact =
                new ContactData().withId(modifiedContact.getId())
                        .withFirstname("Modify").withLastname("uy").withAddress("N.n, n54")
                        .withHome_phone("255-25-25").withMobile_phone("4533").withWork_phone("4664")
                        .withFax("4").withEmail("dfsdf@sca.adf").withEmail2("dgagddg.sdg@dsf.fgr")
                        .withEmail3("afdf@dfda.sdv");

        app.contact().modify(contact, false);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
