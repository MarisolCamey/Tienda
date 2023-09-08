package com.company.intecap.apiresttienda.model;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name="Fabricante")
public class Fabricante {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int clave_fabricante;
    private String nombre;

    public int getClave_fabricante() {
        return clave_fabricante;
    }

    public void setClave_fabricante(int clave_fabricante) {
        this.clave_fabricante = clave_fabricante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
