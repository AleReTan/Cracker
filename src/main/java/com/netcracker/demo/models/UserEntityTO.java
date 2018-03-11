package com.netcracker.demo.models;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserEntityTO {

    public static String LOGIN = "login";
    public static String PASSWORD = "password";
    public static String ROLE = "role";

    private String login;
    private String password;
    private String role;

    public UserEntityTO() {
    }

    public UserEntityTO(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
    public UserEntityTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntityTO that = (UserEntityTO) o;
        return Objects.equals(getLogin(), that.getLogin()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getRole(), that.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword(), getRole());
    }

    @Override
    public String toString() {
        return UserEntityTO.class.getSimpleName() +
                " login=" + login +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
