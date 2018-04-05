package com.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CrimeExtendedDTO extends CrimeShortedDTO {
    private CriminalCaseShortedWithDetectiveDTO criminalCase;
    private String description;
    private LocalTime time;
    private List<ParticipantByCrimeDTO> participants;
    private List<EvidenceOfCrimeShortedDTO> evidencesOfCrime;

    public CrimeExtendedDTO(long id, EnumDTO type, LocalDate date, String place,
                            CriminalCaseShortedWithDetectiveDTO criminalCase, String description,
                            LocalTime time, List<ParticipantByCrimeDTO> participants,
                            List<EvidenceOfCrimeShortedDTO> evidencesOfCrime) {
        super(id, type, date, place);
        this.criminalCase = criminalCase;
        this.description = description;
        this.time = time;
        this.participants = participants;
        this.evidencesOfCrime = evidencesOfCrime;
    }

    public CriminalCaseShortedWithDetectiveDTO getCriminalCase() {
        return criminalCase;
    }

    public void setCriminalCase(CriminalCaseShortedWithDetectiveDTO criminalCase) {
        this.criminalCase = criminalCase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public List<ParticipantByCrimeDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantByCrimeDTO> participants) {
        this.participants = participants;
    }

    public List<EvidenceOfCrimeShortedDTO> getEvidencesOfCrime() {
        return evidencesOfCrime;
    }

    public void setEvidencesOfCrime(List<EvidenceOfCrimeShortedDTO> evidencesOfCrime) {
        this.evidencesOfCrime = evidencesOfCrime;
    }
}
