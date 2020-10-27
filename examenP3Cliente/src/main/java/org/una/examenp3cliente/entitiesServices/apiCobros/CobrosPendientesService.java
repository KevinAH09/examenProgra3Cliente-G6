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
import org.una.examenp3cliente.dtos.apiCobros.CobroPendienteDTO;
import org.una.examenp3cliente.dtos.apiCobros.MembresiaDTO;
import org.una.examenp3cliente.sharedServices.Conection;

/**
 *
 * @author cfugu
 */
public class CobrosPendientesService {
     public static List<CobroPendienteDTO> allCobros() {

        List<CobroPendienteDTO> listMembresiaDTOs = new ArrayList<>();
        try {
            listMembresiaDTOs = (List<CobroPendienteDTO>) Conection.listFromConnection("cobros_pendientes/", new TypeToken<ArrayList<CobroPendienteDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(CobrosPendientesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listMembresiaDTOs;
    }

    public static CobroPendienteDTO idCobro(Long id) {

        CobroPendienteDTO CobroPendienteDTO = new CobroPendienteDTO();
        try {
            CobroPendienteDTO = (CobroPendienteDTO) Conection.oneConnection("cobros_pendientes/" + id, new TypeToken<CobroPendienteDTO>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(CobrosPendientesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CobroPendienteDTO;
    }
    public static CobroPendienteDTO createCobros(CobroPendienteDTO createCobros) {
        CobroPendienteDTO cobro = new CobroPendienteDTO();
        try {
            cobro = (CobroPendienteDTO) Conection.createObjectToConnectionReturnObject("cobros_pendientes/", createCobros, new TypeToken<CobroPendienteDTO>() {
            }.getType());
        } catch (IOException ex) {
            Logger.getLogger(CobrosPendientesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cobro;
    }

    public static int updateProyecto(CobroPendienteDTO updateCobro) {
        int codeResponse = 0;
        try {
            codeResponse = Conection.updateObjectToConnection("cobros_pendientes/" + updateCobro.getId(), updateCobro);
        } catch (IOException ex) {
            Logger.getLogger(CobrosPendientesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codeResponse;
    }
}
