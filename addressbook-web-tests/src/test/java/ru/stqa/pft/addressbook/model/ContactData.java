package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name="id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name="firstname")
    private String firstname;

    @Expose
    @Column(name="lastname")
    private String lastname;

    @Expose
    @Column(name="address")
    @Type(type="text")
    private String address;

    @Expose
    @Column(name="home")
    @Type(type="text")
    private String home_phone;

    @Expose
    @Column(name="mobile")
    @Type(type="text")
    private String mobile_phone;

    @Expose
    @Column(name="work")
    @Type(type="text")
    private String work_phone;

    @Expose
    @Column(name="fax")
    @Type(type="text")
    private String fax;

    @Expose
    @Column(name="email")
    @Type(type="text")
    private String email;

    @Expose
    @Column(name="email2")
    @Type(type="text")
    private String email2;

    @Expose
    @Column(name="email3")
    @Type(type="text")
    private String email3;

    @Expose
    @Transient
    private String group;

    @Expose
    @Column(name="photo")
    @Type(type="text")
    private String photo;

    @Transient
    private String allPhones;

    @Transient
    private String allEmails;



    public File getPhoto() { return new File(photo); }

    public int getId() { return id; }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHome_phone() {
        return home_phone;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public String getWork_phone() {
        return work_phone;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getGroup() {
        return group;
    }


    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHome_phone(String home_phone) {
        this.home_phone = home_phone;
        return this;
    }

    public ContactData withMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
        return this;
    }

    public ContactData withWork_phone(String work_phone) {
        this.work_phone = work_phone;
        return this;
    }

    public ContactData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(address, that.address) && Objects.equals(home_phone, that.home_phone) && Objects.equals(mobile_phone, that.mobile_phone) && Objects.equals(work_phone, that.work_phone) && Objects.equals(fax, that.fax) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, address, home_phone, mobile_phone, work_phone, fax,
            email, email2, email3);
    }
}
