package com.views.interfaces;

import com.logic.Crime;

import java.util.List;

public interface IReportView {
    String generateReport(List<Crime> crimes) throws Exception;
}
