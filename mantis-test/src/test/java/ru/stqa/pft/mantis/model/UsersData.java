package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UsersData {

  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Column(name = "username")
  private String userName;

  @Column(name = "realname")
  private String realName;

  @Column(name = "email")
  private String email;

  public int getId() {
    return id;
  }

  public UsersData withId(int id) {
    this.id = id;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public UsersData withUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getRealName() {
    return realName;
  }

  public UsersData withRealName(String realName) {
    this.realName = realName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UsersData usersData = (UsersData) o;
    return id == usersData.id && Objects.equals(userName, usersData.userName) && Objects.equals(realName, usersData.realName) && Objects.equals(email, usersData.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userName, realName, email);
  }

  public UsersData withEmail(String email) {
    this.email = email;
    return this;
  }
}
