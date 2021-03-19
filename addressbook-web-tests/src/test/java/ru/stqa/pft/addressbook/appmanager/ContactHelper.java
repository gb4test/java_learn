package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

  public void create(ContactData contact) {
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
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group")))
            .selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void update() {
    click(By.name("update"));
    contactCache = null;
    goToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    delete();
    isAlertAccept();
    contactCache = null;
    goToHome();
  }

  public void addingToGroup(ContactData contact, GroupData group) {
    showAllContacts();
    selectContactById(contact.getId());
    selectGroupInList(group.getId(), group.getName());
    addToGroup();
    goToHome();
  }

  public void deletingFromGroup(ContactData contact, GroupData group) {
    selectGroupFilter(group.getId(), group.getName());
    selectContactById(contact.getId());
    delFromGroup();
  }

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void addNewContact() {
    click(By.linkText("add new"));
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void delete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void isAlertAccept() {
    wd.switchTo().alert().accept();
  }

  public void addToGroup() { wd.findElement(By.name("add")).click(); }

  public void delFromGroup() { wd.findElement(By.name("remove")).click(); }

  //for connection contact to group
  public void selectGroupInList(int id, String groupName) {
    wd.findElement(By.name("to_group")).click();
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupName);
    wd.findElement(By.xpath("(//option[@value='" + id + "'])[2]")).click();
  }

  //choose group for showing connected contacts
  public void selectGroupFilter(int id, String groupName) {
    wd.findElement(By.name("group")).click();
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
    wd.findElement(By.xpath("//option[@value='" + id + "']")).click();
  }

  public void showAllContacts() {
    wd.findElement(By.name("group")).click();
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
    wd.findElement(By.name("group")).click();
  }

  //for edit
  public void selectById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }
  // wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
  // wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
  // wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();


  public void goToHomePage() {
    click(By.linkText("home page"));
  }

  public void goToHome() {
    click(By.linkText("home"));
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));

    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
          .withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
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