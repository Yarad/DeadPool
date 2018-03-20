package com.DTO;

public class GenericDTO<T> {
    private boolean error;
    private T result;

    public GenericDTO(boolean error, T result) {
        this.error = error;
        this.result = result;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
