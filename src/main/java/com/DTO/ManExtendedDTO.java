package com.DTO;

import java.time.LocalDate;
import java.util.List;

public class ManExtendedDTO extends ManInfoDTO {
    private List<ParticipantByManDTO> crimesWithParticipant;

    public ManExtendedDTO(long id, String name, String surname, String photoPath, LocalDate birthday,
                          String homeAddress, List<ParticipantByManDTO> crimesWithParticipant) {
        super(id, name, surname, birthday, homeAddress, photoPath);
        this.crimesWithParticipant = crimesWithParticipant;
    }

    public List<ParticipantByManDTO> getCrimesWithParticipant() {
        return crimesWithParticipant;
    }

    public void setCrimesWithParticipant(List<ParticipantByManDTO> crimesWithParticipant){
        this.crimesWithParticipant = crimesWithParticipant;
    }
}
