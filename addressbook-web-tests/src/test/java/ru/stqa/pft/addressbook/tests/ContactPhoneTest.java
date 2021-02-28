package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

    @Test
    public void testContactPhones() {
        app.goTo().home();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getHome_phone(), equalTo(cleaned(contactInfoFromEditForm.getHome_phone())));
        assertThat(contact.getMobile_phone(), equalTo(cleaned(contactInfoFromEditForm.getMobile_phone())));
        assertThat(contact.getWork_phone(), equalTo(cleaned(contactInfoFromEditForm.getWork_phone())));
    }

    public String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
