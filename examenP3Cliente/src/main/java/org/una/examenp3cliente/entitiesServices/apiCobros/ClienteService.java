/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.entitiesServices.apiCobros;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.una.examenp3cliente.dtos.apiCobros.ClienteDTO;
import org.una.examenp3cliente.dtos.apiTareas.ProyectoDTO;
import org.una.examenp3cliente.entitiesServices.apiTareas.ProyectoService;
import org.una.examenp3cliente.sharedServices.Conection;

/**
 *
 * @author cfugu
 */
public class ClienteService {

    public static List<ClienteDTO> allCliente() {

        List<ClienteDTO> listClienteDTOs = new ArrayList<>();
        try {
            listClienteDTOs = (List<ClienteDTO>) Conection.listFromConnection("clientes/", new TypeToken<ArrayList<ClienteDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listClienteDTOs;
    }

    public static ClienteDTO idCliente(Long id) {

        ClienteDTO ClienteDTO = new ClienteDTO();
        try {
            ClienteDTO = (ClienteDTO) Conection.oneConnection("clientes/" + id, new TypeToken<ClienteDTO>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ClienteDTO;
    }

    public static ClienteDTO createProyecto(ClienteDTO createCliente) {
        ClienteDTO cliente = new ClienteDTO();
        try {
            cliente = (ClienteDTO) Conection.createObjectToConnectionReturnObject("clientes/", createCliente, new TypeToken<ClienteDTO>() {
            }.getType());
        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

    public static int updateProyecto(ClienteDTO updateCliente) {
        int codeResponse = 0;
        try {
            codeResponse = Conection.updateObjectToConnection("clientes/" + updateCliente.getId(), updateCliente);
        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codeResponse;
    }
}
