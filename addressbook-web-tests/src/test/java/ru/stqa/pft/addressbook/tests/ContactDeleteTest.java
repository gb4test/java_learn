package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeleteTest extends TestBase {

    @Test
    public void testContactDelete() {
        app.getNavigationHelper().goToHome();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("ret", " rew",
                    "trry.jfhj, khaf 54",
                    "46546", "4533", "4664", "4",
                    "dfsdf@sca.adf", "dgagddg.sdg@dsf.fgr", "afdf@dfda.sdv", "test"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().isAlertAccept();
        app.getNavigationHelper().goToHome();
    }
}
