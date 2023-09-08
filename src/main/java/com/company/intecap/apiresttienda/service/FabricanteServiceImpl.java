package com.company.intecap.apiresttienda.service;


import com.company.intecap.apiresttienda.dao.IFabricanteDao;
import com.company.intecap.apiresttienda.model.Fabricante;
import com.company.intecap.apiresttienda.response.FabricanteResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service //@Service es una anotacion que se utiliza para marcar una clase como un bean de servicio
public class FabricanteServiceImpl implements IFabricanteService {
    private static final Logger log = LoggerFactory.getLogger(FabricanteServiceImpl.class);

    @Autowired
    private IFabricanteDao fabricanteDao;
    @Override
    @Transactional(readOnly= true)
    public ResponseEntity<FabricanteResponseRest> buscarFabricantes() {
        log.info("inicio metodo buscarFabricantes()");
        FabricanteResponseRest response = new FabricanteResponseRest();

        try {
            List<Fabricante> fabricante = (List<Fabricante>) fabricanteDao.findAll();
            response.getFabricanteResponse().setFabricantes(fabricante);
            response.setMetadata("Respuesta Ok","200","respuesta exitosa");

        }catch(Exception e){
            response.setMetadata("Respuesta no Ok","500","error al consultar fabricantes");
            log.error("Error al consultar fabricantes{}", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FabricanteResponseRest> buscarFabricanteId(Long Id) {
        log.info("inicio metodo buscarFabricantePorId()");

        FabricanteResponseRest response= new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();

        try{
            Optional<Fabricante> fabricante= fabricanteDao.findById(Id);
            if(fabricante.isPresent()){
                list.add(fabricante.get());
                response.getFabricanteResponse().setFabricantes(list);
            }else{
                log.error("Error al consultar el fabricante{}", Id);
                response.setMetadata("Respuesta no Ok","500","fabricante no encontrado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND); //error 404
            }

        }catch (Exception e){
            log.error("Error al consultar el fabricante {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al consultar el fabricante");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //error 500
        }

        response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<FabricanteResponseRest>( response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> crearFabricante(Fabricante fabricante) {
        log.info("inicio metodo crear() Fabricante");
        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();
        try{
            Fabricante fabricanteGuardado= fabricanteDao.save(fabricante);
            if (fabricanteGuardado !=null){
                list.add(fabricanteGuardado);
                response.getFabricanteResponse().setFabricantes(list);
            }else{
                log.error("Error al crear el fabricante {}", fabricante.toString());
                response.setMetadata("Respuesta no ok", "500", "Fabricante no crado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.BAD_REQUEST); //error 400

            }

        }catch (Exception e){
            log.error("Error al crear un fabricante {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al crear un fabricante");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //error 500
        }

        response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> actualizarFabricante(Fabricante fabricante, Long Id) {
        log.info("inicio metodo actualizar() Fabricante");

        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();
        try{
            Optional<Fabricante> fabricanteBuscado= fabricanteDao.findById(Id);
            if (fabricanteBuscado.isPresent()){
                fabricanteBuscado.get().setNombre(fabricante.getNombre());

                Fabricante fabricanteActualizado = fabricanteDao.save(fabricanteBuscado.get());
                if (fabricanteActualizado !=null){
                    list.add(fabricanteActualizado);
                    response.getFabricanteResponse().setFabricantes(list);
                }else {
                    log.error("Error al actualizar fabricante {}", fabricante.toString());
                    response.setMetadata("Respuesta no ok", "400", "Fabricante no actualizado");
                    return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.BAD_REQUEST); //error 400
                }
            }else{
                log.error("Error al actualizar articulo {}", fabricante.toString());
                response.setMetadata("Respuesta no ok", "400", "Fabricante no actualizado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.BAD_REQUEST); //error 400
            }

        }catch (Exception e){
            log.error("Error al actualizar fabricante {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "400", "error al actualizar un fabricante");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //error 400
        }

        response.setMetadata("Respuesta ok", "200", "Fabricante Actualizado");
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK); //respuesta exitosa
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(Long Id) {
        log.info("inicio metodo eliminar() Fabricante");
        FabricanteResponseRest response = new FabricanteResponseRest(); //instancia de la clase FabricanteResponseRest

        try {
            Optional<Fabricante> fabricante= fabricanteDao.findById(Id); //busca un fabricante por id

            if(fabricante.isPresent()){ //si el fabricante existe
                fabricanteDao.delete(fabricante.get()); //elimina el fabricante por id
            }else{
                log.error("Error al eliminar fabricante {}", Id);
                response.setMetadata("Respuesta no ok", "400", "Fabricante no eliminado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND); //error 404
            }

        }catch (Exception e){
            log.error("Error al Eliminar un fabricante {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al Eliminar un fabricante");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //error 500

        }
        response.setMetadata("Respuesta ok", "200", "Fabricante Eliminado");
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK); //respuesta exitosa
    }
}
