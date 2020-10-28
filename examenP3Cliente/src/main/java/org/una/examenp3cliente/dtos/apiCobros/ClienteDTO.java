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
public class ClienteDTO {

    private Long id;

    private String identificacion;

    private String telefono;

    private String nombre;

    private MembresiaDTO membresiasId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public MembresiaDTO getMembresiasId() {
        return membresiasId;
    }

    public void setMembresiasId(MembresiaDTO membresiasId) {
        this.membresiasId = membresiasId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" + "id=" + id + ", identificacion=" + identificacion + ", telefono=" + telefono + ", membresiasId=" + membresiasId + '}';
    }
}
