package com.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Detective extends Man {
    private String login;
    private String hashOfPassword;
    private String email;
    public List<UserRole> roles = Arrays.asList(UserRole.DETECTIVE);

    public String getLogin() {
        return login;
    }

    public boolean setLogin(String login) {
        if (ifLoginValid(login)) {
            this.login = login;
            return true;
        } else {
            return false;
        }
    }

    public String getHashOfPassword() {
        return hashOfPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean setHashOfPassword(String hashOfPassword) {
        if (ifPasswordValid(hashOfPassword)) {
            this.hashOfPassword = hashOfPassword;
            return true;
        } else {
            return false;
        }
    }

    private boolean ifLoginValid(String login) {
        Pattern p = Pattern.compile("[a-z]+[a-z0-9]+");
        Matcher m = p.matcher(login);
        return m.matches();
    }

    private boolean ifPasswordValid(String password) {
        Pattern p = Pattern.compile("[a-z0-9]+");
        Matcher m = p.matcher(password);
        return m.matches();
    }
}
