package com.company.intecap.apiresttienda.controllers;

import com.company.intecap.apiresttienda.model.Articulo;
import com.company.intecap.apiresttienda.response.ArticuloResponseRest;
import com.company.intecap.apiresttienda.service.IArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ArticuloRestController {
    @Autowired //inyeccion de dependencias
    private IArticuloService service;

    @GetMapping("/articulos")
    public ResponseEntity<ArticuloResponseRest>buscarArticulos(){
        return  service.buscarArticulos();
    }

    @GetMapping("/articulos/{id}")
    public ResponseEntity<ArticuloResponseRest>buscarARticuloPorId(@PathVariable Long id){
        return  service.buscarArticuloPorId(id);
    }

    @PostMapping("/articulos")
    public ResponseEntity<ArticuloResponseRest>crearArticulo(@RequestBody Articulo request){
        return service.crearArticulo(request);
    }

    @PutMapping("/articulos/{id}")
    public ResponseEntity<ArticuloResponseRest>actualizarArticulo(@RequestBody Articulo request, @PathVariable Long id ){
        return service.actualizarArticulo(request, id);
    }

    @DeleteMapping("/articulos/{id}")
    public ResponseEntity<ArticuloResponseRest>eliminarArticulo(@PathVariable Long id){
        return service.eliminarArticulo(id);
    }

}
