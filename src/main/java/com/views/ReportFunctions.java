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

    public static String getTitleForCriminalCases(String status) {
        switch(status) {
            case "open":
                return "Расследуемые уголовные дела";
            case "solved":
                return "Раскрытые уголовные дела";
            case "unsolved":
                return "Нераскрытые уголовные дела";
            default:
                return "Все уголовные дела";
        }
    }

    public static String getSheetTitleForCriminalCases(String status) {
        switch(status) {
            case "open":
                return "Отчёт по расследуемым уголовным делам";
            case "solved":
                return "Отчёт по раскрытым уголовным делам";
            case "unsolved":
                return "Отчёт по нераскрытым уголовным делам";
            default:
                return "Отчёт по всем уголовным делам";
        }
    }
}
