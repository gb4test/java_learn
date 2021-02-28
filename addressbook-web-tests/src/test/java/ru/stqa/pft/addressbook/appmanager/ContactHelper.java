package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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

    public void selectById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
    // { wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click(); }
    // wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
    // wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();

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
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String[] phones = cells.get(5).getText().split("\n");
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
                    .withHome_phone(phones[0]).withMobile_phone(phones[1]).withWork_phone(phones[2]));
        }
        return new Contacts(contactCache);
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        selectById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String fax = wd.findElement(By.name("fax")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withAddress(address).withHome_phone(home).withMobile_phone(mobile)
                .withWork_phone(work).withFax(fax).withEmail(email).withEmail2(email2).withEmail3(email3);
    }
}