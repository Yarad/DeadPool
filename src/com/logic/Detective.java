package com.logic;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.*;

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

    public boolean setPassword(String password) {
        if (ifPasswordValid(password)) {
            this.password = password;
            return true;
        } else {
            return false;
        }
    }

    private boolean ifLoginValid(String login) {
        Pattern p = Pattern.compile("[a-z]+");
        Matcher m = p.matcher(login);
        return m.matches();
    }

    private boolean ifPasswordValid(String password) {
        Pattern p = Pattern.compile("[a-z]+");
        Matcher m = p.matcher(password);
        return m.matches();
    }
}
