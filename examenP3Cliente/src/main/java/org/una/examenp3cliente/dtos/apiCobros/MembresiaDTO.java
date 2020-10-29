/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.dtos.apiCobros;

/**
 *
 * @author cfugu
 */
public class MembresiaDTO {

    private Long id;

    private Double monto;

    private String descripcion;

    private String periodicidad;

    private ClienteDTO clientesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public ClienteDTO getClientesId() {
        return clientesId;
    }

    public void setClientesId(ClienteDTO clientesId) {
        this.clientesId = clientesId;
    }

    @Override
    public String toString() {
        return descripcion ;
    }

}
