package com.Additionals;

import com.logic.Crime;
import com.logic.CriminalCase;
import com.logic.Detective;
import com.logic.Man;

import static org.junit.Assert.assertEquals;

public final class ClassEqualsAsserts {
    private ClassEqualsAsserts() {}

    public static void assertManEquals(Man expectedMan, Man actualMan) {
        assertEquals(expectedMan.getManId(), actualMan.getManId());
        assertEquals(expectedMan.getName(), actualMan.getName());
        assertEquals(expectedMan.getPhotoPath(), actualMan.getPhotoPath());
        assertEquals(expectedMan.getSurname(), actualMan.getSurname());
        assertEquals(expectedMan.getBirthDay(), actualMan.getBirthDay());
        assertEquals(expectedMan.getHomeAddress(), actualMan.getHomeAddress());
    }

    public static void assertDetectiveEquals(Detective expected, Detective actual) {
        assertEquals(expected.getManId(), actual.getManId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPhotoPath(), actual.getPhotoPath());
        assertEquals(expected.getSurname(), actual.getSurname());
        assertEquals(expected.getBirthDay(), actual.getBirthDay());
        assertEquals(expected.getHomeAddress(), actual.getHomeAddress());
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
}
