package com.company.intecap.apiresttienda.service;

import com.company.intecap.apiresttienda.model.Articulo;
import com.company.intecap.apiresttienda.response.ArticuloResponseRest;
import org.springframework.http.ResponseEntity;

public interface IArticuloService {

    public ResponseEntity<ArticuloResponseRest> buscarArticulos();

    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorId( Long Id);

    public ResponseEntity<ArticuloResponseRest> crearArticulo(Articulo articulo);

    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(Articulo articulo, Long Id);

    public  ResponseEntity<ArticuloResponseRest> eliminarArticulo(Long Id);

}
