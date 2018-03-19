package com.DTO;

public class DetectiveDTO {
    private IdOnlyDTO man;
    private String login;
    private String password;
    private String email;

    public DetectiveDTO(IdOnlyDTO man, String login, String password, String email) {
        this.man = man;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public DetectiveDTO() { }

    public IdOnlyDTO getMan() {
        return man;
    }

    public void setMan(IdOnlyDTO man) {
        this.man = man;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
