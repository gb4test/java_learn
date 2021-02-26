package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
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
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().choose(index);
        ContactData contact =
                new ContactData().withId(before.get(index).getId())
                        .withFirstname("Modify").withLastname("uy").withAddress("N.n, n54")
                        .withHome_phone("255-25-25").withMobile_phone("4533").withWork_phone("4664")
                        .withFax("4").withEmail("dfsdf@sca.adf").withEmail2("dgagddg.sdg@dsf.fgr")
                        .withEmail3("afdf@dfda.sdv");
        app.contact().modify(contact, false);
        app.contact().update();
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
