package com.brekhov1.contactshw;

/**
 * Created by Даня on 24.03.2018.
 */

public class Contact {

    long id;
    String name;
    String phoneNr;
    String birthDate;

    public Contact(long id, String name, String phoneNr, String birthDate) {
        this.id = id;
        this.name = name;
        this.phoneNr = phoneNr;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
