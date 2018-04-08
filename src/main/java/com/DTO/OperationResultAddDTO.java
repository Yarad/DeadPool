package com.DTO;

public class OperationResultAddDTO extends OperationResultDTO {
    private long id;

    public OperationResultAddDTO(AddResult result) {
        super(result.getResult());
        this.id = result.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
