package com.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Detective extends Man {
    private String login = "";
    private String hashOfPassword = "";
    private String email = "";
    public List<UserRole> roles;// = Arrays.asList(UserRole.DETECTIVE);

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

    public void setHashOfPassword(String hashOfPassword) {
        this.hashOfPassword = hashOfPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private boolean ifLoginValid(String login) {
        Pattern p = Pattern.compile("[a-zA-Z]+[a-zA-Z0-9_]+");
        Matcher m = p.matcher(login);
        return m.matches();
    }
}
