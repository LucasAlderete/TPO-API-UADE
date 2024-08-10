package tpo.uade.api.model.frontend;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {

    private String username;

    private String email;

    private String password;

    private LocalDate birthday;

    private String name;

    private String surname;

    public User(String username, String email, String password, LocalDate birthday, String name, String surname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.name = name;
        this.surname = surname;
    }

    public User () {
        //No-args Constructor
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}




