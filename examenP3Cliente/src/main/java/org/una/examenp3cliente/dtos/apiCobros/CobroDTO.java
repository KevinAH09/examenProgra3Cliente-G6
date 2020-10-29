/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.dtos.apiCobros;

import java.util.Date;

/**
 *
 * @author cfugu
 */
public class CobroDTO {

    private Long id;

    private String periodo;

    private String anno;

    private Double monto;

    private String tipo;

    private ClienteDTO clientesId;

    private Date fechaVencimiento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public ClienteDTO getClientesId() {
        return clientesId;
    }

    public void setClientesId(ClienteDTO clientesId) {
        this.clientesId = clientesId;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    @Override
    public String toString() {
        return "CobroDTO{" + "id=" + id + ", periodo=" + periodo + ", anno=" + anno + ", monto=" + monto + ", tipo=" + tipo + ", clientesId=" + clientesId + ", fechaVencimiento=" + fechaVencimiento + '}';
    }

    

}
