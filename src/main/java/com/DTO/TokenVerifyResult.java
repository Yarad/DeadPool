package com.DTO;

public class TokenVerifyResult {
    private boolean isCorrect;
    private String userName;

    public TokenVerifyResult() { }

    public TokenVerifyResult(boolean isCorrect, String userName) {
        this.isCorrect = isCorrect;
        this.userName = userName;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
