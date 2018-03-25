package com.DTO;

import java.util.List;

public class ListMenShortedDTO {
    private List<ManShortedDTO> men;

    public ListMenShortedDTO(List<ManShortedDTO> men) {
        this.men = men;
    }

    public List<ManShortedDTO> getMen() {
        return men;
    }

    public void setMen(List<ManShortedDTO> men) {
        this.men = men;
    }
}
