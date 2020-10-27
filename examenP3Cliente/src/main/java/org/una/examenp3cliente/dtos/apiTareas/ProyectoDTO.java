/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.dtos.apiTareas;

import java.util.ArrayList;
import java.util.List;
import org.una.examenp3cliente.dtos.apiTareas.TareaDTO;

/**
 *
 * @author colo7
 */
public class ProyectoDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    private List<TareaDTO> listTareas;

    public ProyectoDTO() {
        listTareas = new ArrayList();
    }

    public List<TareaDTO> getListTareas() {
        return listTareas;
    }

    public void setListTareas(List<TareaDTO> listTareas) {
        this.listTareas = listTareas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ProyectoDTO{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", listTareas=" + listTareas + '}';
    }

    

}
