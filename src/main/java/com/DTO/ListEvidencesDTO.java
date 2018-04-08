package com.DTO;

import java.util.List;

public class ListEvidencesDTO {
    private List<EvidenceObjectDTO> evidences;

    public ListEvidencesDTO(List<EvidenceObjectDTO> evidences) {
        this.evidences = evidences;
    }

    public List<EvidenceObjectDTO> getEvidences() {
        return evidences;
    }

    public void setEvidences(List<EvidenceObjectDTO> evidences) {
        this.evidences = evidences;
    }
}
