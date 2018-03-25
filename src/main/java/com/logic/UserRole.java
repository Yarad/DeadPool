package com.logic;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole  implements GrantedAuthority {
    DETECTIVE("Детектив"),  //детектив/следователь
    INSPECTOR("Контролёр"); //человек из контролируующей организации (прокуратуры)

    private final String fieldDescription;

    private UserRole(String value) {
        fieldDescription = value;
    }

    public String getName() {
        return fieldDescription;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
