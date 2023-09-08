package com.company.intecap.apiresttienda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table (name="Articulo")

public class Articulo implements Serializable{

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int clave_articulo;
    private String nombre;
    private int precio;



    @ManyToOne(fetch = FetchType.LAZY) //FetchType.LAZY es para que no se cargue la categoria hasta que se llame (carga perezosa)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //ignora los atributos que no se necesitan(hadler: es para que no se cargue la categoria hasta que se llame)
    private Fabricante fabricante;

    public int getClave_articulo() {
        return clave_articulo;
    }

    public void setClave_articulo(int clave_articulo) {
        this.clave_articulo = clave_articulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }
}
