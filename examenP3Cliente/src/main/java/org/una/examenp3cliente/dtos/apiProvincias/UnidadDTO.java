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
public class UnidadDTO {
    
    private Long id;
    
    private String nombreUnidad;
    
    private String codigo;
    
    private String tipo;
    
    private Long poblacion;
    
    private Double areaCuadrada;

    public DistritoDTO getDistritoId() {
        return distritoId;
    }

    public void setDistritoId(DistritoDTO distritoId) {
        this.distritoId = distritoId;
    }
    
    private DistritoDTO distritoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(Long poblacion) {
        this.poblacion = poblacion;
    }

    public Double getAreaCuadrada() {
        return areaCuadrada;
    }

    public void setAreaCuadrada(Double areaCuadrada) {
        this.areaCuadrada = areaCuadrada;
    }
    
    
}
