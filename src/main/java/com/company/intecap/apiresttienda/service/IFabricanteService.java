package com.company.intecap.apiresttienda.service;

import com.company.intecap.apiresttienda.model.Fabricante;
import com.company.intecap.apiresttienda.response.FabricanteResponseRest;
import org.springframework.http.ResponseEntity;

public interface IFabricanteService {

    public ResponseEntity<FabricanteResponseRest> buscarFabricantes();

    public ResponseEntity<FabricanteResponseRest> buscarFabricanteId(Long Id);

    public  ResponseEntity<FabricanteResponseRest> crearFabricante(Fabricante fabricante);

    public ResponseEntity<FabricanteResponseRest> actualizarFabricante(Fabricante fabricante, Long Id);

    public ResponseEntity<FabricanteResponseRest> eliminarFabricante (Long Id);
}
