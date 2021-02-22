package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeleteTest extends TestBase {

    @Test
    public void testContactDelete() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().checkGroup();
        app.getNavigationHelper().goToHome();
        app.getContactHelper().checkContact();
        app.getNavigationHelper().goToHome();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().isAlertAccept();
        app.getNavigationHelper().goToHome();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }
}
