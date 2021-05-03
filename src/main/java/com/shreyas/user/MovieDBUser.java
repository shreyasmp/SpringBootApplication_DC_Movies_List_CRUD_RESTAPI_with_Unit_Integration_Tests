package com.shreyas.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shreyas.core.BaseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;

@Entity
public class MovieDBUser extends BaseEntity {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private String firstName;
    private String lastName;
    private String userName;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String[] userRoles;

    public MovieDBUser() {
        super();
    }

    public MovieDBUser(String firstName, String lastName, String userName, String password, String[] userRoles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        setPassword(password);
        this.userRoles = userRoles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String[] getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String[] userRoles) {
        this.userRoles = userRoles;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }
}
