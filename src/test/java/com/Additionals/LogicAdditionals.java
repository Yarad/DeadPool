package com.Additionals;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.logic.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.format.DateTimeFormatter.ofPattern;

public final class LogicAdditionals {
    private LogicAdditionals() {}

    private static final DateTimeFormatter FORMATTER_DATE = ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMATTER_TIME = ofPattern("HH:mm");
    private static final DateTimeFormatter FORMATTER_DATETIME = ofPattern("yyyy-MM-dd HH:mm");

    private static final LocalDate localDateAdd = LocalDate.of(2012,12,2);
    private static final LocalTime localTimeAdd = LocalTime.of(15,57);
    private static final LocalDateTime localDateTimeAdd = LocalDateTime.of(2014,6,28,17,34);

    public static ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()
                .addSerializer(LocalDate.class, new LocalDateSerializer(FORMATTER_DATE))
                .addSerializer(LocalTime.class, new LocalTimeSerializer(FORMATTER_TIME))
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(FORMATTER_DATETIME))
                .addDeserializer(LocalDate.class, new LocalDateDeserializer(FORMATTER_DATE))
                .addDeserializer(LocalTime.class, new LocalTimeDeserializer(FORMATTER_TIME))
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(FORMATTER_DATETIME)));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public static List<CriminalCase> getCriminalCases() {
        List<CriminalCase> cases = new ArrayList<>();
        cases.add(getCustomCriminalCase());
        cases.add(getCustomCriminalCase());
        return cases;
    }

    public static CriminalCase getCriminalCaseOpen() {
        CriminalCase crCase = getCustomCriminalCase();
        crCase.setCreateDate(localDateAdd);
        crCase.setClosed(false);
        return crCase;
    }

    public static CriminalCase getCriminalCaseSolved() {
        CriminalCase crCase = getCustomCriminalCase();
        crCase.setCloseDate(localDateAdd);
        crCase.setCreateDate(localDateAdd);
        return crCase;
    }

    public static CriminalCase getCriminalCaseUnsolved() {
        CriminalCase crCase = getCustomCriminalCase();
        crCase.setCreateDate(localDateAdd);
        return crCase;
    }

    public static CriminalCase getCustomCriminalCase() {
        CriminalCase crCase = new CriminalCase();
        crCase.setCriminalCaseId(1);
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

    public static Crime getCrimeWithDates() {
        Crime crime = getCustomCrime();
        crime.setCrimeDate(localDateAdd);
        crime.setCrimeTime(localTimeAdd);
        return crime;
    }

    public static Crime getCustomCrime() {
        Crime crime = new Crime();
        crime.setCrimeId(1);
        crime.setCrimeType("ARSON");
        crime.setCrimePlace("Minsk");
        crime.setDescription("Long long");
        crime.setCriminalCaseId(1);
        crime.setParentCriminalCase(getCustomCriminalCase());
        return crime;
    }

    public static List<EvidenceOfCrime> getEvidenceOfCrimeList() {
        List<EvidenceOfCrime> list = new ArrayList<>();
        list.add(getCustomEvidenceOfCrime());
        list.add(getCustomEvidenceOfCrime());
        return list;
    }

    public static EvidenceOfCrime getEvidenceOfCrimeWithDates() {
        EvidenceOfCrime evidenceOfCrime = getCustomEvidenceOfCrime();
        evidenceOfCrime.setDateAdded(localDateTimeAdd);
        return evidenceOfCrime;
    }

    public static EvidenceOfCrime getCustomEvidenceOfCrime() {
        EvidenceOfCrime evidenceOfCrime = new EvidenceOfCrime();
        evidenceOfCrime.setParentEvidence(getCustomEvidence());
        evidenceOfCrime.setParentCrime(getCustomCrime());
        evidenceOfCrime.setEvidenceType("OBJECT_FROM_CRIME_SCENE");
        evidenceOfCrime.setPhotoPath("photo");
        evidenceOfCrime.setDetails("details");
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
        participant.setWitnessReport("report");
        participant.setAlibi("none");
        participant.setCrimeId(1);
        participant.setCrime(getCustomCrime());
        return participant;
    }

    public static Participant getParticipantWithDates() {
        Participant participant = getCustomParticipant();
        participant.setDateAdded(localDateTimeAdd);
        participant.setBirthDay(localDateAdd);
        return participant;
    }

    public static List<Man> getManList() {
        List<Man> list = new ArrayList<>();
        list.add(getCustomMan());
        list.add(getCustomMan());
        return list;
    }

    public static Man getManWithDates() {
        Man man = getCustomMan();
        man.setBirthDay(localDateAdd);
        return man;
    }

    public static Man getCustomMan() {
        Man man = new Man();
        man.setManId(1);
        man.setSurname("surname");
        man.setName("name");
        man.setPhotoPath("photo");
        man.setHomeAddress("addr");
        return man;
    }

    public static Detective getDetectiveWithDates() {
        Detective man = getCustomDetective();
        man.setBirthDay(localDateAdd);
        return man;
    }

    public static Detective getCustomDetective() {
        Detective man = new Detective();
        man.setManId(1);
        man.setSurname("surname");
        man.setName("name");
        man.setPhotoPath("photo");
        man.setHomeAddress("addr");
        man.setLogin("login");
        man.setHashOfPassword("hash");
        man.setEmail("email");
        return man;
    }

    public static Map<Man,Long> getMapOfManAndTheirCrime() {
        Map<Man,Long> map = new HashMap<>();
        map.put(getCustomMan(), 2L);
        Man man2 = getCustomMan();
        man2.setName("Very other man");
        map.put(man2, 0L);
        return map;
    }
}
