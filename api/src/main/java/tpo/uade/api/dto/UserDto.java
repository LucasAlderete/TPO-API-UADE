package tpo.uade.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public class UserDto implements Serializable {

    @NotBlank(message = "username must not be null nor empty") //TODO -> determine error messages in util.ValidationMessages
    private String username;

    @NotBlank(message = "email must not be null nor empty")
    private String email;

    @NotBlank(message = "password must not be null nor empty")
    private String password;

    @NotNull(message = "birthday must not be null nor empty")
    private LocalDate birthday;

    @NotBlank(message = "name must not be null nor empty")
    private String name;

    @NotBlank(message = "surname must not be null nor empty")
    private String surname;

    public UserDto(String username, String email, String password, LocalDate birthday, String name, String surname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.name = name;
        this.surname = surname;
    }

    public UserDto() {
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
        return "UserDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}




