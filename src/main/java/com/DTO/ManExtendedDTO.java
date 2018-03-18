package com.DTO;

import java.time.LocalDate;
import java.util.List;

public class ManExtendedDTO extends ManShortedDTO {
    private LocalDate birthday;
    private String homeAddress;
    private List<ParticipantByManDTO> crimesWithParticipant;

    public ManExtendedDTO(long id, String name, String surname, String photoPath, LocalDate birthday,
                          String homeAddress, List<ParticipantByManDTO> crimesWithParticipant) {
        super(id, name, surname, photoPath);
        this.birthday = birthday;
        this.homeAddress = homeAddress;
        this.crimesWithParticipant = crimesWithParticipant;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public List<ParticipantByManDTO> getCrimesWithParticipant() {
        return crimesWithParticipant;
    }

    public void setCrimesWithParticipant(List<ParticipantByManDTO> crimesWithParticipant){
        this.crimesWithParticipant = crimesWithParticipant;
    }
}
