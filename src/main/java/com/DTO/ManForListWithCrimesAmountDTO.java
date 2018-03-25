package com.DTO;

public class ManForListWithCrimesAmountDTO extends ManShortedDTO {
    private long crimesPartAmount;

    public ManForListWithCrimesAmountDTO(long id, String name, String surname, String photoPath, long crimesPartAmount) {
        super(id, name, surname, photoPath);
        this.crimesPartAmount = crimesPartAmount;
    }

    public long getCrimesPartAmount() {
        return crimesPartAmount;
    }

    public void setCrimesPartAmount(long crimesPartAmount) {
        this.crimesPartAmount = crimesPartAmount;
    }
}
