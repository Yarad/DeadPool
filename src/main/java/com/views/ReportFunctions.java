package com.views;

import com.logic.CriminalCase;

import static com.logic.ProjectConstants.JSON_FORMATTER_DATE;

public final class ReportFunctions {
    private ReportFunctions() {}

    public static String getStatusWithData(CriminalCase criminalCase) {
        if (!criminalCase.isClosed()) {
            return "open; creation date " + criminalCase.getCreateDate().format(JSON_FORMATTER_DATE);
        } else {
            if (criminalCase.getCloseDate() != null) {
                return "solved; creation date " + criminalCase.getCreateDate().format(JSON_FORMATTER_DATE) +
                        ", close date " + criminalCase.getCloseDate().format(JSON_FORMATTER_DATE);
            } else {
                return "not solved; creation date " + criminalCase.getCreateDate().format(JSON_FORMATTER_DATE);
            }
        }
    }
}
