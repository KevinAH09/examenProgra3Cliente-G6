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

    private String tipo;

    private String periodo;

    private String ano;

    private Double monto;

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

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
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

    @Override
    public String toString() {
        return "CobroPendienteDTO{" + "id=" + id + ", tipo=" + tipo + ", periodo=" + periodo + ", ano=" + ano + ", monto=" + monto + ", clientesId=" + clientesId + ", fechaVencimiento=" + fechaVencimiento + '}';
    }
    

}
