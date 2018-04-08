package com.views;

import com.logic.CriminalCase;

import static com.logic.ProjectConstants.JSON_FORMATTER_DATE;

public final class ReportFunctions {
    private ReportFunctions() {}

    public static String getStatusWithData(CriminalCase criminalCase) {
        if (!criminalCase.isClosed()) {
            return "open";
        } else {
            if (criminalCase.getCloseDate() != null) {
                return "solved";
            } else {
                return "not solved";
            }
        }
    }
}
