package com.DTO;

public class ParticipantByCrimeDTO {
    private ManShortedDTO man;
    private String status;

    public ParticipantByCrimeDTO(ManShortedDTO man, String status) {
        this.man = man;
        this.status = status;
    }

    public ManShortedDTO getMan() {
        return man;
    }

    public void setMan(ManShortedDTO man) {
        this.man = man;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
