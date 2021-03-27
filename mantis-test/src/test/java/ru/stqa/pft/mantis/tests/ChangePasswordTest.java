package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UsersData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {

    UsersData user = app.db().users().iterator().next();
    String userName = user.getUserName();
    String newPassword = "newPassword";
    String email = user.getEmail();

    app.admin().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.admin().resetPassword(user);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 15000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.admin().changePassword(confirmationLink, userName, newPassword);
    assertTrue(app.newSession().login(userName, newPassword));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}

