package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHome();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("new", "t",
                "Florida, 5 avenu, 954-56",
                "1", "11", "111", "1",
                "111@sca.adf", "111.sdg@dsf.fgr", "11@dfda.sdv", null),
                false);
        app.getContactHelper().updateContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
