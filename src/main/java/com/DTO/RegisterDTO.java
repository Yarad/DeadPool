package com.DTO;

public class RegisterDTO extends AuthDTO {
    private IdOnlyDTO man;
    private String email;

    public RegisterDTO() { }

    public IdOnlyDTO getMan() {
        return man;
    }

    public void setMan(IdOnlyDTO man) {
        this.man = man;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
