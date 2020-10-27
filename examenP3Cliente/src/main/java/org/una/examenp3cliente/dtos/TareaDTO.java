/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.dtos;

import java.util.Date;
import org.una.examenp3cliente.dtos.ProyectoDTO;

/**
 *
 * @author colo7
 */
public class TareaDTO {

    private Long id;

    private String descripcion;

    private double importancia;

    private double urgencia;

    private double procentajeAvance;

    private ProyectoDTO proyectoId;

    private Date fechaInicio;

    private Date fechaFinalizacion;

    public TareaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getImportancia() {
        return importancia;
    }

    public void setImportancia(double importancia) {
        this.importancia = importancia;
    }

    public double getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(double urgencia) {
        this.urgencia = urgencia;
    }

    public double getProcentajeAvance() {
        return procentajeAvance;
    }

    public void setProcentajeAvance(double procentajeAvance) {
        this.procentajeAvance = procentajeAvance;
    }

    public ProyectoDTO getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(ProyectoDTO proyectoId) {
        this.proyectoId = proyectoId;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

}
