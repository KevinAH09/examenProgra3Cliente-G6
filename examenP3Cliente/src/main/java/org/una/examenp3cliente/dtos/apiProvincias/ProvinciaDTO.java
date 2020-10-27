/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.dtos.apiProvincias;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bosco
 */
public class ProvinciaDTO {
    private Long id;

    private String nombreProvincia;

    private String codigo;
    
    private List<CantonDTO> listCantones;

    public ProvinciaDTO() {
        listCantones = new ArrayList();
    }

    public List<CantonDTO> getListCantones() {
        return listCantones;
    }

    public void setListCantones(List<CantonDTO> listCantones) {
        this.listCantones = listCantones;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "ProvinciaDTO{" + "id=" + id + ", nombreProvincia=" + nombreProvincia + ", codigo=" + codigo + '}';
    }
}
