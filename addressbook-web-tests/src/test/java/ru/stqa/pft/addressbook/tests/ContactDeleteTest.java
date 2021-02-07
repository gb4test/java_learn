package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTest extends TestBase {

    @Test
    public void testContactDelete() {
        app.getNavigationHelper().goToHome();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().isAlertAccept();
        app.getNavigationHelper().goToHome();
    }
}
