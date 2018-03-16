package com.DTO;

import java.util.List;

public class ListCrimesDTO {
    private List<CrimeObjectDTO> crimes;

    public ListCrimesDTO(List<CrimeObjectDTO> crimes) {
        this.crimes = crimes;
    }

    public List<CrimeObjectDTO> getCrimes() {
        return crimes;
    }

    public void setCrimes(List<CrimeObjectDTO> crimes) {
        this.crimes = crimes;
    }
}
