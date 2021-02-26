package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (! app.contact().isThereAContact()) {
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
    public void testContactDelete() {
        app.goTo().home();
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().select(index);
        app.contact().delete();
        app.contact().isAlertAccept();
        app.goTo().home();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);


        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
