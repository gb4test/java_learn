package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void delete() { click(By.xpath("//input[@value='Delete']")); }

    public void selectContactById(int id) { wd.findElement(By.cssSelector("input[value='" + id + "']")).click(); }

    public void isAlertAccept() { wd.switchTo().alert().accept(); }

    public void choose(int id) { wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click(); }

    public void update() {
        click(By.name( "update"));
        contactCache = null;
        goToHomePage();
    }

    public void goToHomePage() { click(By.linkText("home page")); }

    public void goToHome() { click(By.linkText("home")); }

    public void create(ContactData contact, boolean b) {
        addNewContact();
        modify(contact, true);
        submitContactCreation();
        contactCache = null;
        goToHomePage();
    }

    public void modify(ContactData contactData, boolean creation) {
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

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        delete();
        isAlertAccept();
        contactCache = null;
        goToHome();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));

        for (WebElement row : rows) {
            List<WebElement> cells =  row.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
        }
        return new Contacts(contactCache);
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}