package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome_phone());
        type(By.name("mobile"), contactData.getMobile_phone());
        type(By.name("work"), contactData.getWork_phone());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void selectContact() { click(By.name("selected[]")); }

    public void deleteContact() { click(By.xpath("//input[@value='Delete']")); }

    public void isAlertAccept() { wd.switchTo().alert().accept(); }

    public void initContactModification() { click(By.xpath("//img[@alt='Edit']")); }

    public void updateContactModification() { click(By.name( "update"));}

    public void goToHomePage() { click(By.linkText("home page")); }

    public void createContact(ContactData contact, boolean b) {
        addNewContact();
        fillContactForm(contact, true);
        submitContactCreation();
        goToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void checkContact() {
        if (! isThereAContact()) {
            createContact(new ContactData("ret", " rew",
                    "trry.jfhj, khaf 54",
                    "46546", "4533", "4664", "4",
                    "dfsdf@sca.adf", "dgagddg.sdg@dsf.fgr", "afdf@dfda.sdv", "test"), true);
        }
    }

}