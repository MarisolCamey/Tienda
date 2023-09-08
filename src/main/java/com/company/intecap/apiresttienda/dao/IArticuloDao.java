package com.company.intecap.apiresttienda.dao;

import com.company.intecap.apiresttienda.model.Articulo;
import org.springframework.data.repository.CrudRepository;

public interface IArticuloDao extends CrudRepository <Articulo, Long>{
}
