package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {

        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().checkGroup();
            app.getContactHelper().createContact(new ContactData("Lui", " rew",
                    "N.n, n  54", "46546", "4533", "4664", "4",
                    "dfsdf@sca.adf", "dgagddg.sdg@dsf.fgr", "afdf@dfda.sdv", "test"), true);
        }
        app.getNavigationHelper().goToHome();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModificationById(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "ASDF", "KJHcfdhdhGF",
                "Florida, 5 avenu, 954-56",
                "1", "11", "111", "1",
                "111@sca.adf", "111.sdg@dsf.fgr", "11@dfda.sdv", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContactModification();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact); 
        System.out.println(before);
        System.out.println(after);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
