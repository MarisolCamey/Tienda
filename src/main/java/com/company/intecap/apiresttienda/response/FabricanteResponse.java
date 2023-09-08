package com.company.intecap.apiresttienda.response;

import com.company.intecap.apiresttienda.model.Fabricante;

import java.util.List;

public class FabricanteResponse {
    private List<Fabricante> fabricantes;

    public List<Fabricante>getFabricantes(){ return fabricantes;}

    public void setFabricantes(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }
}
