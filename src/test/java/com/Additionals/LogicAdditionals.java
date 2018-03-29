package com.Additionals;

import com.logic.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LogicAdditionals {
    private LogicAdditionals() {}

    public static List<CriminalCase> getCriminalCases() {
        List<CriminalCase> cases = new ArrayList<>();
        cases.add(getCustomCriminalCase());
        cases.add(getCustomCriminalCase());
        return cases;
    }

    public static CriminalCase getCustomCriminalCase() {
        CriminalCase crCase = new CriminalCase();
        crCase.setCriminalCaseId(1);
        crCase.setCloseDate(null);
        crCase.setCreateDate(null);
        crCase.setClosed(true);
        crCase.setDetectiveId(1);
        Detective det = new Detective();
        det.setManId(1);
        det.setLogin("test");
        det.setHashOfPassword("ferdsfgyujikjhgvbn");
        crCase.setParentDetective(det);
        crCase.setCriminalCaseNumber("API");
        return crCase;
    }

    public static List<Crime> getCrimesList() {
        List<Crime> crimes = new ArrayList<>();
        crimes.add(getCustomCrime());
        crimes.add(getCustomCrime());
        return crimes;
    }

    public static Crime getCustomCrime() {
        Crime crime = new Crime();
        crime.setCrimeId(1);
        crime.setCrimeDate(null);
        crime.setCrimeType("ARSON");
        crime.setCrimePlace("Minsk");
        crime.setCrimeTime(null);
        crime.setDescription("Long long");
        crime.setCriminalCaseId(1);
        return crime;
    }

    public static List<EvidenceOfCrime> getEvidenceOfCrimeList() {
        List<EvidenceOfCrime> list = new ArrayList<>();
        list.add(getCustomEvidenceOfCrime());
        list.add(getCustomEvidenceOfCrime());
        return list;
    }

    public static EvidenceOfCrime getCustomEvidenceOfCrime() {
        EvidenceOfCrime evidenceOfCrime = new EvidenceOfCrime();
        evidenceOfCrime.parentEvidence = getCustomEvidence();
        evidenceOfCrime.setEvidenceType("OBJECT_FROM_CRIME_SCENE");
        evidenceOfCrime.setPhotoPath("photo");
        evidenceOfCrime.setDetails("details");
        evidenceOfCrime.setDateAdded(null);
        return evidenceOfCrime;
    }

    public static Evidence getCustomEvidence() {
        Evidence evidence = new Evidence();
        evidence.setEvidenceId(1);
        evidence.setDescription("bla-bla-bla");
        evidence.setName("name");
        return evidence;
    }

    public static List<Participant> getParticipantList() {
        List<Participant> list = new ArrayList<>();
        list.add(getCustomParticipant());
        list.add(getCustomParticipant());
        return list;
    }

    public static Participant getCustomParticipant() {
        Participant participant = new Participant();
        participant.setParticipantStatus("SPECTATOR");
        participant.setManId(1);
        participant.setSurname("surname");
        participant.setName("name");
        participant.setPhotoPath("photo");
        participant.setHomeAddress("addr");
        participant.setBirthDay(null);
        participant.setWitnessReport("report");
        participant.setAlibi("none");
        participant.setCrimeId(1);
        participant.setDateAdded(null);
        participant.setCrime(getCustomCrime());
        return participant;
    }

    public static List<Man> getManList() {
        List<Man> list = new ArrayList<>();
        list.add(getCustomMan());
        list.add(getCustomMan());
        return list;
    }

    public static Man getCustomMan() {
        Man man = new Man();
        man.setManId(1);
        man.setSurname("surname");
        man.setName("name");
        man.setPhotoPath("photo");
        man.setHomeAddress("addr");
        man.setBirthDay(null);
        return man;
    }

    public static Map<Man,Long> getMapOfManAndTheirCrime() {
        Map<Man,Long> map = new HashMap<>();
        map.put(getCustomMan(), Long.valueOf(2));
        Man man2 = getCustomMan();
        man2.setName("Very other man");
        map.put(man2, Long.valueOf(0));
        return map;
    }
}
