package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTest extends TestBase {

    @Test
    public void testContactDelete() {

        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().checkGroup();
            app.getContactHelper().createContact(new ContactData("Lui", " rew",
                    "N.n, n  54", "46546", "4533", "4664", "4",
                    "dfsdf@sca.adf", "dgagddg.sdg@dsf.fgr", "afdf@dfda.sdv", "test"), true);
        }
        app.getNavigationHelper().goToHome();
        List<ContactData> before = app.getContactHelper().getContactList();
        System.out.println("before.size(): " + before.size());
        app.getContactHelper().selectContact(before.size() - 2);
        app.getContactHelper().deleteContact();
        app.getContactHelper().isAlertAccept();
        app.getNavigationHelper().goToHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
    }
}
