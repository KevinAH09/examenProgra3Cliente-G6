/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.dtos.apiProvincias;

import java.util.List;
import org.una.examenp3cliente.dtos.apiTareas.DistritoDTO;

/**
 *
 * @author Bosco
 */
public class CantonDTO {
    
    private Long id;

    private String nombreCanton;

    private String codigo;
    
    private List<DistritoDTO> listDistritos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCanton() {
        return nombreCanton;
    }

    public void setNombreCanton(String nombreCanton) {
        this.nombreCanton = nombreCanton;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<DistritoDTO> getListDistritos() {
        return listDistritos;
    }

    public void setListDistritos(List<DistritoDTO> listDistritos) {
        this.listDistritos = listDistritos;
    }
    
    
    
}
