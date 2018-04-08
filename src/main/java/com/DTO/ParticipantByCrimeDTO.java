package com.DTO;

public class ParticipantByCrimeDTO {
    private ManShortedDTO man;
    private EnumDTO status;

    public ParticipantByCrimeDTO(ManShortedDTO man, EnumDTO status) {
        this.man = man;
        this.status = status;
    }

    public ManShortedDTO getMan() {
        return man;
    }

    public void setMan(ManShortedDTO man) {
        this.man = man;
    }

    public EnumDTO getStatus() {
        return status;
    }

    public void setStatus(EnumDTO status) {
        this.status = status;
    }
}
