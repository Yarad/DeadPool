package com.DTO;

public class OperationResultDTO {
    private boolean success;

    public OperationResultDTO(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
