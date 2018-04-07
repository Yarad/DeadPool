package com.views;

import com.logic.Crime;
import com.views.interfaces.IReportView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class XLSXView implements IReportView {
    @Override
    public String generateReport(List<Crime> crimes) throws Exception {
        return null;
    }
}
