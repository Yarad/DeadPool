package com.DTO;

public class GenericDTO<T> {
    private boolean success;
    private T result;

    public GenericDTO(boolean success, T result) {
        this.success = success;
        this.result = result;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
