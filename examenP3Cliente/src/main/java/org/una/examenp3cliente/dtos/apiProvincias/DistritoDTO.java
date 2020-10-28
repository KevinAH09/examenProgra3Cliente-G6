/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.dtos.apiProvincias;

/**
 *
 * @author Bosco
 */
public class DistritoDTO {
    
    private Long id;

    private String nombreDistrito;

    private String codigo;
    
    private CantonDTO cantonId;

    public CantonDTO getCantonId() {
        return cantonId;
    }

    public void setCantonId(CantonDTO cantonId) {
        this.cantonId = cantonId;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
    
}
