package sample;

import java.io.Serializable;

public class User implements Serializable {
    private int ID;
    private String lastname;
    private String name;
    private String surname;
    private String password;
    private String login;
    private String country;
    private String city;
    private String street;
    private String gender;

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", lastname='" + lastname + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    private String status;


    public User(String lastname, String name, String surname, String password, String login, String country, String city, String street, String gender, String status) {
        this.lastname = lastname;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.login = login;
        this.country = country;
        this.city = city;
        this.street = street;
        this.gender = gender;
        this.status = status;
    }

    public User(int ID, String lastname, String name, String surname, String password, String login, String country, String city, String street, String gender, String status) {
        this.ID = ID;
        this.lastname = lastname;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.login = login;
        this.country = country;
        this.city = city;
        this.street = street;
        this.gender = gender;
        this.status = status;
    }


    public User() {

    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

}

