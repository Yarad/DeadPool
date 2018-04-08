package com.DTO;

public class AddResult {
    private boolean result;
    private long id;

    public AddResult(boolean result, long id) {
        this.result = result;
        this.id = id;
    }

    public AddResult(boolean result) {
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public boolean getResult() {
        return result;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
