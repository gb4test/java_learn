package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailsTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().groupPage();
            if (app.db().groups().size() == 0) {
                app.group().create(new GroupData().withName("test").withHeader("test").withFooter("test"));
            }
            Groups groups = app.db().groups();
            app.contact().create(
                    new ContactData().withFirstname("CreatedNow").withLastname("rew").withAddress("N.n, n54")
                            .withHome_phone("255-25-25").withMobile_phone("45 33").withWork_phone("+4(664) ")
                            .withFax("4").withEmail("dfs_df@sca.adf").withEmail2("dg-agddg.sdg@dsf.fgr")
                            .withEmail3("afdf@dfda.sdv").inGroup(groups.iterator().next()));
        }
        app.goTo().home();
    }

    @Test
    public void testContactEmails() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
