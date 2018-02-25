package com.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Detective extends Man {
    private String login;
    private String password;

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

    public String getPassword() {
        return password;
    }

    public boolean setHashOfPassword(String password) {
        if (ifPasswordValid(password)) {
            this.password = password;
            return true;
        } else {
            return false;
        }
    }

    // [S]olid
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
