package tpo.uade.api.model;

//import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

//@Entity
//@Table(name = "USER")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements Serializable {

    //@Id
    //@Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    //@Column(name = "username", nullable = false, unique = true)
    private String username;

    //@Column(name = "email", nullable = false, unique = true)
    private String email;

    //@Column(name = "password", nullable = false)
    private String password;

    //@Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    //@Column(name = "name", nullable = false)
    private String name;

    //@Column(name = "surname", nullable = false)
    private String surname;

    public User(Long userId, String username, String email, String password, LocalDate birthday, String name, String surname) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.name = name;
        this.surname = surname;
    }

    public User() {
        //No-args Constructor
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
