package com.DTO;

public class IdOnlyDTO {
    private long id;

    public IdOnlyDTO(long id) {
        this.id = id;
    }
    public IdOnlyDTO() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
