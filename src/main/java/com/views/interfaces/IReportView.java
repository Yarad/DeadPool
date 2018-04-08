package com.views.interfaces;

import com.logic.*;

import java.time.LocalDate;
import java.util.List;

public interface IReportView {
    String generateReportByCrimes(List<Crime> crimes, LocalDate startDate, LocalDate endDate) throws Exception;
    String generateReportByCriminalCases(List<CriminalCase> criminalCases) throws Exception;
    String generateReportByCrime(Crime crime, List<EvidenceOfCrime> evidencesOfCrime, List<Participant> participants) throws Exception;
    String generateReportByMan(Man man, List<Participant> participants) throws Exception;
    String generateReportByCriminalCase(CriminalCase criminalCase, List<Crime> crimes) throws Exception;
    String generateReportByEvidence(Evidence evidence, List<EvidenceOfCrime> evidenceOfCrimes) throws Exception;
    String generateReportByDetective(Detective detective, List<CriminalCase> criminalCases) throws Exception;
}
