package com.views;

import com.logic.*;
import com.views.interfaces.IReportView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CSVView implements IReportView {
    @Override
    public String generateReportByCrimes(List<Crime> crimes) throws Exception {
        return null;
    }

    @Override
    public String generateReportByCriminalCases(List<CriminalCase> criminalCases) throws Exception {
        return null;
    }

    @Override
    public String generateReportByCrime(Crime crime, List<EvidenceOfCrime> evidencesOfCrime, List<Participant> participants) throws Exception {
        return null;
    }

    @Override
    public String generateReportByMan(Man man, List<Participant> participants) throws Exception {
        return null;
    }

    @Override
    public String generateReportByCriminalCase(CriminalCase criminalCase, List<Crime> crimes) throws Exception {
        return null;
    }

    @Override
    public String generateReportByEvidence(Evidence evidence, List<EvidenceOfCrime> evidenceOfCrimes) throws Exception {
        return null;
    }

    @Override
    public String generateReportByDetective(Detective detective, List<CriminalCase> criminalCases) throws Exception {
        return null;
    }
}