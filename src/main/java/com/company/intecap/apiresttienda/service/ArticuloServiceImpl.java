package com.company.intecap.apiresttienda.service;
import com.company.intecap.apiresttienda.dao.IArticuloDao;
import com.company.intecap.apiresttienda.model.Articulo;
import com.company.intecap.apiresttienda.response.ArticuloResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImpl implements IArticuloService{

    private static final Logger log = LoggerFactory.getLogger(ArticuloServiceImpl.class);

   @Autowired
    private IArticuloDao articuloDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ArticuloResponseRest> buscarArticulos() {
        log.info("inicio metodo buscarArticulos()");
        ArticuloResponseRest response = new ArticuloResponseRest();

try {
    List<Articulo> articulo = (List<Articulo>) articuloDao.findAll();
    response.getArticuloResponse().setArticulos(articulo);
    response.setMetadata("Respuesta Ok","200","respuesta exitosa");

}catch(Exception e){
    response.setMetadata("Respuesta no Ok","500","error al consultar articulos");
    log.error("Error al consultar articulos{}", e.getMessage());
    e.getStackTrace();
    return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorId(Long Id) {
        log.info("inicio metodo buscarArticuloPorId()");

        ArticuloResponseRest response= new ArticuloResponseRest();
        List<Articulo> list = new ArrayList<>();

        try{
            Optional<Articulo> articulo= articuloDao.findById(Id);
        if(articulo.isPresent()){
            list.add(articulo.get());
            response.getArticuloResponse().setArticulos(list);
        }else{
            log.error("Error al consultar el articulo{}", Id);
            response.setMetadata("Respuesta no Ok","500","Articulo no encontrado");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.NOT_FOUND); //error 404
        }

        }catch (Exception e){
            log.error("Error al consultar articulo {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al consultar el articulo");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //error 500
        }

        response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<ArticuloResponseRest>( response, HttpStatus.OK);

    }


    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> crearArticulo(Articulo articulo) {
        log.info("inicio metodo crear() Articulo");
        ArticuloResponseRest response = new ArticuloResponseRest();
        List<Articulo> list = new ArrayList<>();
        try{
            Articulo articuloGuardado = articuloDao.save(articulo);
            if (articuloGuardado !=null){
                list.add(articuloGuardado);
                response.getArticuloResponse().setArticulos(list);
            }else{
                log.error("Error al crear el articulo {}", articulo.toString());
                response.setMetadata("Respuesta no ok", "500", "Articulo no crado");
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.BAD_REQUEST); //error 400

            }

        }catch (Exception e){
            log.error("Error al crear un articulo {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al crear un articulo");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //error 500
        }

        response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }
    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(Articulo articulo, Long Id) {
        log.info("inicio metodo actualizar() Articulo");

        ArticuloResponseRest response = new ArticuloResponseRest();
        List<Articulo> list = new ArrayList<>();
    try{
        Optional<Articulo> articuloBuscado= articuloDao.findById(Id);
        if (articuloBuscado.isPresent()){
            articuloBuscado.get().setNombre(articulo.getNombre());
            articuloBuscado.get().setPrecio(articulo.getPrecio());

            Articulo articuloActualizado = articuloDao.save(articuloBuscado.get());
           if (articuloActualizado !=null){
               list.add(articuloActualizado);
               response.getArticuloResponse().setArticulos(list);
           }else {
               log.error("Error al actualizar articulo {}", articulo.toString());
               response.setMetadata("Respuesta no ok", "400", "Articulo no actualizado");
               return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.BAD_REQUEST); //error 400
           }
        }else{
            log.error("Error al actualizar articulo {}", articulo.toString());
            response.setMetadata("Respuesta no ok", "400", "Articulo no actualizado");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.BAD_REQUEST); //error 400
        }

    }catch (Exception e){
        log.error("Error al actualizar articulo {}", e.getMessage());
        response.setMetadata("Respuesta no ok", "400", "error al actualixar un articulo");
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //error 400
    }

        response.setMetadata("Respuesta ok", "200", "Articulo Actualizado");
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK); //respuesta exitosa
    }

    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> eliminarArticulo(Long Id) {
        log.info("inicio metodo eliminar() Articulo");
        ArticuloResponseRest response = new ArticuloResponseRest(); //instancia de la clase ArticuloResponseRest

        try {
            Optional<Articulo> articulo= articuloDao.findById(Id); //busca un articulo por id

            if(articulo.isPresent()){ //si la categoria existe
                articuloDao.delete(articulo.get()); //elimina el articulo por id
            }else{
                log.error("Error al eliminar articulo {}", Id);
                response.setMetadata("Respuesta no ok", "400", "Articulo no eliminado");
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.NOT_FOUND); //error 404
            }

        }catch (Exception e){
            log.error("Error al Eliminar un articulo {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al Eliminar un articulo");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //error 500

        }
        response.setMetadata("Respuesta ok", "200", "Articulo Eliminado");
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK); //respuesta exitosa

    }
}
