package com.DAOTest;

import com.Additionals.AllClassesList;
import com.Additionals.ClassEqualsAsserts;
import com.Additionals.DAOAdditionals;
import com.Additionals.LogicAdditionals;
import com.DAO.DAOCrime;
import com.DAO.DAOEvidence;
import com.DAO.interfaces.IDAOCrime;
import com.DAO.interfaces.IDAOEvidence;
import com.logic.Crime;
import com.logic.Evidence;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class DAOEvidenceTests {
    private static IDAOEvidence daoEvidence;
    private static DAOAdditionals daoAdditionals;

    @BeforeClass
    public static void getDAO() {
        daoAdditionals = new DAOAdditionals();
        daoEvidence = new DAOEvidence();
    }

    @Test
    public void addEvidence_NullInput()  {
        assertEquals(false, daoEvidence.addEvidence(null));
    }

    @Test
    public void updateEvidence_NullInput()  {
        assertEquals(false, daoEvidence.updateEvidence(null));
    }

    @Test
    public void getEvidenceById() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCustomEvidenceToDatabase();

        try {
            Evidence actualEvidence = daoEvidence.getEvidenceById(entities.getEvidence().getEvidenceId());

            ClassEqualsAsserts.assertEvidencesEquals(entities.getEvidence(), actualEvidence);
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getEvidenceById_NoEvidence() {
        Evidence actualEvidence = daoEvidence.getEvidenceById(-1);

        assertNull(actualEvidence);
    }

    @Test
    public void addEvidence() {
        Evidence evidence = LogicAdditionals.getCustomEvidence();

        try {
            boolean actualResult = daoEvidence.addEvidence(evidence);

            assertTrue(actualResult);
            Evidence actualEvidence = daoEvidence.getEvidenceById(evidence.getEvidenceId());
            ClassEqualsAsserts.assertEvidencesEquals(evidence, actualEvidence);
        } finally {
            daoAdditionals.deleteEvidence(evidence);
        }
    }

    @Test
    public void addEvidence_NotCorrectValue() {
        Evidence evidence = LogicAdditionals.getCustomEvidence();
        evidence.setName("PreparedStatement preparedStatement = currConnection.prepareStatement(SELECT * FROM participant JOIN man USING(man_id) WHERE crime_id = ? AND participant.man_id = ?");

        boolean actualResult = daoEvidence.addEvidence(evidence);

        assertFalse(actualResult);
    }

    @Test
    public void updateEvidence() throws Exception {
        AllClassesList entities = new AllClassesList();
        entities.addCustomEvidenceToDatabase();

        entities.getEvidence().setName("new name for update test");
        entities.getEvidence().setDescription("new description for update test");

        try {
            boolean actualResult = daoEvidence.updateEvidence(entities.getEvidence());

            assertTrue(actualResult);
            Evidence actualEvidence = daoEvidence.getEvidenceById(entities.getEvidence().getEvidenceId());
            ClassEqualsAsserts.assertEvidencesEquals(entities.getEvidence(), actualEvidence);
        } finally {
            entities.deleteAllAddedEntities();
        }
    }
}
