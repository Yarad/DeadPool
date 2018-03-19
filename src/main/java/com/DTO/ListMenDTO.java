package com.DTO;

import java.util.List;

public class ListMenDTO {
    private List<ManForListWithCrimesAmountDTO> men;

    public ListMenDTO(List<ManForListWithCrimesAmountDTO> men) {
        this.men = men;
    }

    public List<ManForListWithCrimesAmountDTO> getMen() {
        return men;
    }

    public void setMen(List<ManForListWithCrimesAmountDTO> men) {
        this.men = men;
    }
}
