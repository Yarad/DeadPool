package com.views;

import com.logic.CriminalCase;
import com.logic.Detective;

public final class ReportFunctions {
    private ReportFunctions() {}

    public static String getDetectiveData(Detective detective) {
        return detective.getSurname() + ", " + detective.getName() + " (" + detective.getLogin() + ")";
    }

    public static String getCriminalCaseStatus(CriminalCase criminalCase) {
        if (!criminalCase.isClosed()) {
            return "открыто";
        } else {
            if (criminalCase.getCloseDate() != null) {
                return "раскрыто";
            } else {
                return "не раскрыто (передано в архив)";
            }
        }
    }
}
