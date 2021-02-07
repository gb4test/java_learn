package ru.stqa.pft.addressbook.notforshare;

public class PhoneContactData {
    private final String home_phone;
    private final String mobile_phone;
    private final String work_phone;
    private final String fax;

    public PhoneContactData(String home_phone, String mobile_phone, String work_phone, String fax) {
        this.home_phone = home_phone;
        this.mobile_phone = mobile_phone;
        this.work_phone = work_phone;
        this.fax = fax;
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
}
