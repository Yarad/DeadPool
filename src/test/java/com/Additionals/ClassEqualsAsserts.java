package com.Additionals;

import com.logic.*;

import static org.junit.Assert.assertEquals;

public final class ClassEqualsAsserts {
    private ClassEqualsAsserts() {}

    public static void assertManEquals(Man expected, Man actual) {
        assertEquals(expected.getManId(), actual.getManId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPhotoPath(), actual.getPhotoPath());
        assertEquals(expected.getSurname(), actual.getSurname());
        assertEquals(expected.getBirthDay(), actual.getBirthDay());
        assertEquals(expected.getHomeAddress(), actual.getHomeAddress());
    }

    public static void assertDetectiveEquals(Detective expected, Detective actual) {
        assertManEquals(expected, actual);
        assertEquals(expected.getLogin(), actual.getLogin());
        assertEquals(expected.getHashOfPassword(), actual.getHashOfPassword());
        assertEquals(expected.getEmail(), actual.getEmail());
    }

    public static void assertCriminalCasesEquals(CriminalCase expected, CriminalCase actual) {
        assertEquals(expected.getCriminalCaseId(), actual.getCriminalCaseId());
        assertEquals(expected.getCloseDate(), actual.getCloseDate());
        assertEquals(expected.getCriminalCaseNumber(), actual.getCriminalCaseNumber());
        assertEquals(expected.isClosed(), actual.isClosed());
        assertEquals(expected.getDetectiveId(), actual.getDetectiveId());
        assertEquals(expected.getCreateDate(), actual.getCreateDate());
    }

    public static void assertCrimesEquals(Crime expected, Crime actual) {
        assertEquals(expected.getCriminalCaseId(), actual.getCriminalCaseId());
        assertEquals(expected.getCrimeId(), actual.getCrimeId());
        assertEquals(expected.getCrimeType(), actual.getCrimeType());
        assertEquals(expected.getCrimeDate(), actual.getCrimeDate());
        assertEquals(expected.getCrimePlace(), actual.getCrimePlace());
        assertEquals(expected.getCrimeTime(), actual.getCrimeTime());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    public static void assertEvidencesEquals(Evidence expected, Evidence actual) {
        assertEquals(expected.getEvidenceId(), actual.getEvidenceId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    public static void assertEvidencesOfCrimeEquals(EvidenceOfCrime expected, EvidenceOfCrime actual) {
        assertEquals(expected.getEvidenceId(), actual.getEvidenceId());
        assertEquals(expected.getCrimeId(), actual.getCrimeId());
        assertEquals(expected.getEvidenceType(), actual.getEvidenceType());
        assertEquals(expected.getDateAdded(), actual.getDateAdded());
        assertEquals(expected.getPhotoPath(), actual.getPhotoPath());
        assertEquals(expected.getDetails(), actual.getDetails());
    }

    public static void assertParticipantsEquals(Participant expected, Participant actual) {
        assertManEquals(expected, actual);
        assertEquals(expected.getCrimeId(), actual.getCrimeId());
        assertEquals(expected.getDateAdded(), actual.getDateAdded());
        assertEquals(expected.getParticipantStatus(), actual.getParticipantStatus());
        assertEquals(expected.getAlibi(), actual.getAlibi());
        assertEquals(expected.getWitnessReport(), actual.getWitnessReport());
    }
}
