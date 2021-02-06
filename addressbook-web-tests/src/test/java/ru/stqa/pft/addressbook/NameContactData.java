package ru.stqa.pft.addressbook;

public class NameContactData {
    private final String first_name;
    private final String last_name;

    public NameContactData(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
}
