package com.DAO;

import com.DAO.interfaces.IDAOEvidenceOfCrime;
import com.logic.Evidence;
import com.logic.EvidenceOfCrime;

public class DAOEvidenceOfCrime extends DAO implements IDAOEvidenceOfCrime {

    @Override
    public EvidenceOfCrime getEvidenceOfCrime(long crimeId, long evidenceId) {
        EvidenceOfCrime evidenceOfCrime = new EvidenceOfCrime();
        //evidenceOfCrime.parentCrime

        //НУЖЕН GLOABAL ПУЛ DAO
    }
}
