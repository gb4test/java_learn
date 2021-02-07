package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests2 extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().addNewContact();
    app.getContactHelper().fillContactForm(new ContactData("ret", "rew", "trry.jfhj, khaf 54",
            "46546", "4533", "4664", "4",
            "dfsdf@sca.adf", "dgagddg.sdg@dsf.fgr", "afdf@dfda.sdv"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();
  }
}