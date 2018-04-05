package com.Additionals;

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
}
