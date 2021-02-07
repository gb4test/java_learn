package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String home_phone;
    private final String mobile_phone;
    private final String work_phone;
    private final String fax;
    private final String email;
    private final String email2;
    private final String email3;

    public ContactData(String firstname, String lastname, String address, String home_phone, String mobile_phone, String work_phone, String fax, String email, String email2, String email3) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.home_phone = home_phone;
        this.mobile_phone = mobile_phone;
        this.work_phone = work_phone;
        this.fax = fax;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
    }

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
}