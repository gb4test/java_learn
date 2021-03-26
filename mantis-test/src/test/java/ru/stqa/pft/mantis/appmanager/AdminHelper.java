package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UsersData;

public class AdminHelper extends HelperBase {

  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.xpath("//input[@value='Вход']"));
    type(By.name("password"), password);
    click(By.xpath("//input[@value='Вход']"));
  }

  public void logout() {
    wd.findElement(By.xpath("//div[@id='navbar-container']/div[2]/ul/li[3]/a/i[2]")).click();
    wd.findElement(By.linkText("Выход")).click();
  }

  public void resetPassword(UsersData user) {
    goToUserManagPage();
    selectUserById(user.getId());
    pushResetPassword();
  }

  public void changePassword(String confirmationLink, String username, String newPassword) {
    wd.get(confirmationLink);
    type(By.name("realname"), username);
    type(By.name("password"), newPassword);
    type(By.name("password_confirm"), newPassword);
    click(By.xpath("//button/span") );
  }

  private void pushResetPassword() {
    wd.findElement(By.xpath("//input[@value='Сбросить пароль']")).click();
   // wd.findElement(By.linkText("Продолжить")).click();
  }

  private void goToUserManagPage() {
    wd.findElement(By.cssSelector("i.fa.fa-gears.menu-icon")).click();
    wd.findElement(By.linkText("Управление пользователями")).click();
  }

  private void selectUserById(int id) {
    wd.findElement(By.xpath("//a[contains(@href, 'manage_user_edit_page.php?user_id=" + id + "')]")).click();
  }


}




