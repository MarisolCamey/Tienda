package com.company.intecap.apiresttienda.controllers;
import com.company.intecap.apiresttienda.model.Fabricante;
import com.company.intecap.apiresttienda.response.FabricanteResponseRest;
import com.company.intecap.apiresttienda.service.IFabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@RestController
@RequestMapping("/api/v1")
public class FabricanteRestController {
    @Autowired //inyeccion de dependencias
    private IFabricanteService service;

    @GetMapping("/fabricantes")
    public ResponseEntity<FabricanteResponseRest> buscarFabricantes(){
        return  service.buscarFabricantes();
    }

    @GetMapping("/fabricantes/{id}")
    public ResponseEntity<FabricanteResponseRest>buscarFabricantePorId(@PathVariable Long id){
        return  service.buscarFabricanteId(id);
    }

    @PostMapping("/fabricantes")
    public ResponseEntity<FabricanteResponseRest>crearFabricante(@RequestBody Fabricante request){
        return service.crearFabricante(request);
    }

    @PutMapping("/fabricantes/{id}")
    public ResponseEntity<FabricanteResponseRest>actualizarFabricante(@RequestBody Fabricante request, @PathVariable Long id ){
        return service.actualizarFabricante(request, id);
    }

    @DeleteMapping("/fabricantes/{id}")
    public ResponseEntity<FabricanteResponseRest>eliminarFabricante(@PathVariable Long id){
        return service.eliminarFabricante(id);
    }
}
