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

public class ContactPhoneTest extends TestBase {

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
                            .withFax("4").withEmail("dfsdf@sca.adf").withEmail2("dgagddg.sdg@dsf.fgr")
                            .withEmail3("afdf@dfda.sdv").inGroup(groups.iterator().next()), true);
        }
        app.goTo().home();
    }

    @Test
    public void testContactPhones() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHome_phone(), contact.getMobile_phone(), contact.getWork_phone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}